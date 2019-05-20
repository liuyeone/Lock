package com.example.lock.controller;

import com.example.lock.annotation.RetryOnOptimisticLockingFailure;
import com.example.lock.entity.AccountWallet;
import com.example.lock.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
public class TestController {

    @Autowired
    private TestService testService;

    /**
     * 针对业务系统高并发-----修改用户钱包数据余额，采用乐观锁
     */
    @RetryOnOptimisticLockingFailure
    @RequestMapping("walleroptimisticlock")
    public String walleroptimisticlock(@RequestParam String openId,
                                       @RequestParam String openType,
                                       @RequestParam String amount) throws Exception {
        String result = "";
        AccountWallet wallet = testService.selectByOpenId(openId);

        // 用户操作金额
        BigDecimal cash = BigDecimal.valueOf(Double.parseDouble(amount));
        cash.doubleValue();
        cash.floatValue();

        if (Integer.parseInt(openType) == 1) {
            wallet.setUserAmount(wallet.getUserAmount().add(cash));
        } else if (Integer.parseInt(openType) == 2) {
            wallet.setUserAmount(wallet.getUserAmount().subtract(cash));
        }

        Integer target = testService.updateAccountWallet(wallet);

        result = target == 1 ? "success" : "fail";
        return result;
    }
}
