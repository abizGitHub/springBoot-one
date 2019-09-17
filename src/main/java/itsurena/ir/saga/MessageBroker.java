package itsurena.ir.saga;

import itsurena.ir.saga.command.Reply;

import java.util.HashMap;

public class MessageBroker {

    HashMap<String, Saga> cash;

    public MessageBroker() {
        cash = new HashMap<>();
    }

    public void send(Saga oneSaga, String msgId) {
        cash.put(msgId, oneSaga);
    }

    public void receive(Reply reply) {
        Saga oneSaga = cash.get(reply.getMsgId());
        oneSaga.onCommandReplyed(reply);
    }

}
