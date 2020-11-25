package com.pd.tcc.samples;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * The type Sofa rpc tcc transaction application.
 *
 * @author zhangsen
 */
@SpringBootApplication
@RestController
public class TccBusinessApp {

    @Autowired
    private TccTransactionService tccTransactionService;

    public static void main(String[] args)  {
        SpringApplication.run(TccBusinessApp.class, args);
    }

    @GetMapping(value = "/rpc-test")
    public void rpcTest(){
        tccTransactionService.rpcTest();
    }

    @GetMapping(value = "/tansfer")
    public void tcc() throws InterruptedException {
        tccTransactionService.doTransfer(1,2,1000);
    }

//    private void transactionCommitDemo() throws InterruptedException {
//        String txId = tccTransactionService.doTransactionCommit();
//        System.out.println(txId);
//        Assert.isTrue(StringUtils.isNotBlank(txId), "事务开启失败");
//
//        System.out.println("transaction commit demo finish.");
//    }
//
//    private void transactionRollbackDemo() throws InterruptedException {
//        Map map = new HashMap(16);
//        try {
//            tccTransactionService.doTransactionRollback(map);
//            Assert.isTrue(false, "分布式事务未回滚");
//        } catch (Throwable t) {
//            Assert.isTrue(true, "分布式事务异常回滚");
//        }
//        String txId = (String)map.get("xid");
//
//        System.out.println("transaction rollback demo finish.");
//    }
}

