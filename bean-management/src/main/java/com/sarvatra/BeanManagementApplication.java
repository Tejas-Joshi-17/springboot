package com.sarvatra;

import com.sarvatra.dto.PaymentService;
import com.sarvatra.notification.NotificationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@SpringBootApplication
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class})
public class BeanManagementApplication implements CommandLineRunner {

	private static final Logger LOGGER = LoggerFactory.getLogger(BeanManagementApplication.class);
	private static final File shutdownDir = new File("bin");
	private static ConfigurableApplicationContext applicationContext;
	private static volatile boolean shuttingDown = false;

	@Autowired
	private PaymentService paymentService1;

	@Autowired
	private PaymentService paymentService2;

	private final NotificationService notificationService;

	@Autowired
	public BeanManagementApplication(@Qualifier("email")  NotificationService notificationService) {
		this.notificationService = notificationService;
	}

	@Autowired
	private Map<String, NotificationService> gatewayService;

	public static void main(String[] args) {
		SpringApplication application = new SpringApplication(BeanManagementApplication.class);
		applicationContext = application.run(args);
		shutdownPoller();
	}

	public static void stopApplication() {
		LOGGER.info("Shutting down application...");
		if (applicationContext != null) {
			LOGGER.info("Shutting down SpringApplication...");
			System.exit(SpringApplication.exit(applicationContext, () -> 0));
		}

		System.exit(0);
	}

	private static void shutdownPoller() {
		Path dir = Paths.get(shutdownDir.getAbsolutePath());
		FileSystem fs = dir.getFileSystem();

		try (WatchService watchService = fs.newWatchService()) {
			dir.register(watchService, StandardWatchEventKinds.ENTRY_CREATE, StandardWatchEventKinds.ENTRY_MODIFY, StandardWatchEventKinds.ENTRY_DELETE);
			while (!shuttingDown) {
				if (!waitForChanges(watchService) || shuttingDown) break;
			}

		} catch (IOException e) {
			LOGGER.info("File Not Found");
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
		stopApplication();
	}

	private static boolean waitForChanges(WatchService service) throws InterruptedException {
		WatchKey key = service.poll(2500, TimeUnit.MILLISECONDS);
		if (key != null) {
			for (WatchEvent<?> ev : key.pollEvents()) {
				if (ev.context().toString().equals(".shutdown")) {
					File file = new File("bin/.shutdown");
					if (file.exists()) {
						shuttingDown = true;
					}
				}
			}
			if (!key.reset()) {
				LOGGER.info("deploy directory no longer valid");
				return false; // deploy directory no longer valid
			}
		}
		return true;
	}

	@Override
	public void run(String... args) throws Exception {
		paymentService1.running();
		paymentService2.running();
		notificationService.sendNotification();
		for (Map.Entry<String, NotificationService> notificationServiceEntry : gatewayService.entrySet()) {
			LOGGER.info("{} -> {}", notificationServiceEntry.getKey(), (notificationServiceEntry.getValue().heartBeat()));
		}

	}
}
