package itsurena.ir.saga;

import itsurena.ir.distributedTransaction.NasimService;
import itsurena.ir.distributedTransaction.SimaService;
import itsurena.ir.distributedTransaction.UmsService;
import itsurena.ir.saga.command.Reply;
import itsurena.ir.saga.command.SimaReplyed;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.UUID;

public class OneSaga extends Saga {

    @Autowired
    NasimService nasimService;

    @Autowired
    SimaService simaService;

    @Autowired
    UmsService usmService;


    HashMap<String, OneState> cash;

    public OneState createOneService() {
        String msgId = UUID.randomUUID().toString();
        OneState oneState = new OneState(msgId);
        cash.put(oneState.getServiceId(), oneState);
        simaService.doSomeThing();
        // oneState.wait();
        return oneState;
    }


    public void onCommandReplyed(Reply reply) {
        OneState oneState = cash.get(reply.getMsgId());
        if (reply instanceof SimaReplyed)
            oneState.setSimaReplyed(true);
        try {
            nasimService.doSomeThing();
        } catch (Exception e) {
            e.printStackTrace();
            nasimService.reverseSomeThing();
        }

        if (oneState.getSimaReplyed()) {
            usmService.doSomeThing();
        }
        Integer code = oneState.getUmsResponseCode();

        if (oneState.completed()) {
            cash.remove(oneState);
        }
        //oneState.wait();
    }


}
