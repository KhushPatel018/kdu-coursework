package kdu.homework5.q1;

import kdu.homework5.logging.LoggingSystem;


public class MessageReceiver implements Runnable{
    private static final LoggingSystem ls = new LoggingSystem();
    private MessageQueue messageQueue;

    public MessageReceiver(MessageQueue messageQueue){
        this.messageQueue = messageQueue;
    }
    @Override
    public synchronized void run() {
        Message msg = messageQueue.receive();
        ls.logInfo("Received Message : " +  msg.getId() + " : "+ msg.getText());
    }

}
