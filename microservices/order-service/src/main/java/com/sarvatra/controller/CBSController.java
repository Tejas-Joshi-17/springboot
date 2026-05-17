package com.sarvatra.controller;

import com.sarvatra.service.CbsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/cbs")
@Slf4j
@RequiredArgsConstructor
public class CBSController {

    private final CbsService cbsService;

    @GetMapping(path = "/payToBeneficiary/{id}")
    private ResponseEntity<String> payToBeneficiary(@PathVariable Long id) {
        return cbsService.initiateCheckStatusForCredit(id);
    }

    @GetMapping(path = "/getTxnHistory")
    private ResponseEntity<String> getTransactionHistory() {
        return cbsService.getTxnHistory();
    }

    // Failure Response :-  If Inventory-service is down
    //    2026-05-13 00:09:28,399 INFO c.s.s.CbsService [http-nio-13016-exec-1] Initiate Check Status Request
    //    2026-05-13 00:09:28,501 WARN o.s.c.l.c.RoundRobinLoadBalancer [http-nio-13016-exec-1] No servers available for service: inventory-service
    //    2026-05-13 00:09:28,508 WARN o.s.c.o.l.FeignBlockingLoadBalancerClient [http-nio-13016-exec-1] Load balancer does not contain an instance for the service inventory-service
    //    2026-05-13 00:09:38,521 INFO c.s.s.CbsService [http-nio-13016-exec-1] Initiate Check Status Request
    //    2026-05-13 00:09:38,524 WARN o.s.c.l.c.RoundRobinLoadBalancer [http-nio-13016-exec-1] No servers available for service: inventory-service
    //    2026-05-13 00:09:38,526 WARN o.s.c.o.l.FeignBlockingLoadBalancerClient [http-nio-13016-exec-1] Load balancer does not contain an instance for the service inventory-service
    //    2026-05-13 00:09:48,527 INFO c.s.s.CbsService [http-nio-13016-exec-1] Initiate Check Status Request
    //    2026-05-13 00:09:48,530 WARN o.s.c.l.c.RoundRobinLoadBalancer [http-nio-13016-exec-1] No servers available for service: inventory-service
    //    2026-05-13 00:09:48,531 WARN o.s.c.o.l.FeignBlockingLoadBalancerClient [http-nio-13016-exec-1] Load balancer does not contain an instance for the service inventory-service
    //    2026-05-13 00:09:48,533 INFO c.s.s.CbsService [http-nio-13016-exec-1] Getting error for transaction - 1 :- [503] during [GET] to [http://inventory-service/api/v1/cbs/credit/1] [InventoryOpenFeignClient#creditToBeneficiaryAccount(Long)]: [Load balancer does not contain an instance for the service inventory-service]

    // Success Response :-  If Inventory-service is up
    //    2026-05-13 00:12:39,988 INFO c.s.s.CbsService [http-nio-13016-exec-1] Initiate Check Status Request


}
