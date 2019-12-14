package abiz.ir.saga;

import abiz.ir.saga.command.Reply;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class Saga {

    @Autowired
    MessageBroker messageBroker;

    public abstract void onCommandReplyed(Reply reply);

}
