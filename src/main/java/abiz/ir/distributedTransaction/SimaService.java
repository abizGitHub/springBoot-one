package abiz.ir.distributedTransaction;

import abiz.ir.saga.MessageBroker;
import org.springframework.beans.factory.annotation.Autowired;

public class SimaService {

    @Autowired
    MessageBroker messageBroker;

    public void doSomeThing(){

    }

    public void reverseSomeThing(){

    }
}
