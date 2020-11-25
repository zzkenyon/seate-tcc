package com.pd.tcc.samples;

import com.pd.tcc.samples.action.DeductTccAction;
import com.pd.tcc.samples.action.AddTccAction;
import io.seata.core.context.RootContext;

import io.seata.spring.annotation.GlobalTransactional;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * The type Tcc transaction service.
 *
 * @author zhangsen
 */
@Service
public class TccTransactionService {

    @Reference(version = "1.0")
    private DeductTccAction deductTccAction;
    @Reference(version = "1.0")
    private AddTccAction addTccAction;


    /**
     * 发起分布式事务
     *
     * @return string string
     */
    @GlobalTransactional
    public boolean doTransfer(int fromUid,int toUid,double amount) {
        //第一个TCC 事务参与者
        System.out.println(RootContext.getXID());
        boolean result = deductTccAction.prepare(null, fromUid,amount);
        if (!result) {
            throw new RuntimeException("deductTccAction failed.");
        }
        result = addTccAction.prepare(null, toUid, amount);
        if (!result) {
            throw new RuntimeException("addTccAction failed.");
        }
        return true;
    }

    /**
     * Do transaction rollback string.
     * @param map the map
     * @return the string
     */
    /*@GlobalTransactional
    public String doTransactionRollback(Map map) {
        //第一个TCC 事务参与者
        boolean result = deductTccAction.prepare(null, 1);
        if (!result) {
            throw new RuntimeException("TccActionOne failed.");
        }
        List list = new ArrayList();
        list.add("c1");
        list.add("c2");
        result = addTccAction.prepare(null, "two", list);
        if (!result) {
            throw new RuntimeException("TccActionTwo failed.");
        }
        map.put("xid", RootContext.getXID());
        throw new RuntimeException("transacton rollback");
    }*/

    /**
     * Sets tcc action one.
     *
     * @param deductTccAction the tcc action one
     */
    public void setDeductTccAction(DeductTccAction deductTccAction) {
        this.deductTccAction = deductTccAction;
    }

    /**
     * Sets tcc action two.
     *
     * @param addTccAction the tcc action two
     */
    public void setAddTccAction(AddTccAction addTccAction) {
        this.addTccAction = addTccAction;
    }

    public void rpcTest() {
        addTccAction.prepare(null,1,20);
    }
}
