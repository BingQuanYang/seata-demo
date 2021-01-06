package com.smart.order.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author ybq
 * @date 2020/12/16 22:40
 */
@FeignClient("store-server")
public interface StoreService {
    @PutMapping("/update")
    int dec(@RequestParam long productId, @RequestParam int quantity);
}
