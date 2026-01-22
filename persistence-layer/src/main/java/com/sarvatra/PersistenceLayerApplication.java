package com.sarvatra;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ConfigurableApplicationContext;

import java.io.File;
import java.nio.file.*;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;

@SpringBootApplication
@EnableConfigurationProperties
//@EnableAutoConfiguration(exclude = DataSourceAutoConfiguration.class)
public class PersistenceLayerApplication {

	private static final Logger LOGGER = LoggerFactory.getLogger(PersistenceLayerApplication.class);
	private static final File shutdownDir = new File("bin");
	private static final Map<String, String> appInfo = new HashMap<>();
	private static ConfigurableApplicationContext applicationContext;
	private static volatile boolean shuttingDown = false;

	public static void main(String[] args) {

		SpringApplication application = new SpringApplication(PersistenceLayerApplication.class);
		application.setBannerMode(Banner.Mode.CONSOLE);
		applicationContext = application.run(args);
		LOGGER.info("*************************************************************************************************");
		populateAppInfo();
		LOGGER.info("*************************************************************************************************");
		LOGGER.info("Services Started!");
		shutdownPoller();
	}

	private static void populateAppInfo() {
		try {
			ResourceBundle buildinfo = ResourceBundle.getBundle("buildinfo");
			parseBundle(buildinfo);

			for (Map.Entry<String, String> entry : appInfo.entrySet()) {
				LOGGER.info("{} : {}", entry.getKey(), entry.getValue());
			}
		} catch (Exception ex) {
			// Left Intentionally
		}
	}

	private static void parseBundle(ResourceBundle bundle) {
		for (String k : bundle.keySet()) {
			appInfo.put(k, bundle.getString(k));
		}
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
		} catch (Exception e) {
			LOGGER.info("File Not Found");
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
}
