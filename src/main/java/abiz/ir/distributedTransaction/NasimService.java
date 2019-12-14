package abiz.ir.distributedTransaction;

import abiz.ir.saga.MessageBroker;
import abiz.ir.saga.command.SimaReplyed;
import org.springframework.beans.factory.annotation.Autowired;

public class NasimService {

    @Autowired
    MessageBroker messageBroker;

    public void doSomeThing() throws Exception {
        // receive data
        messageBroker.receive(new SimaReplyed(""));

    }

    public void reverseSomeThing() {

    }


}
