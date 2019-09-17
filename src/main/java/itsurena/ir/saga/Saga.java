package itsurena.ir.saga;

import itsurena.ir.saga.command.Reply;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class Saga {

    @Autowired
    MessageBroker messageBroker;

    public abstract void onCommandReplyed(Reply reply);

}
