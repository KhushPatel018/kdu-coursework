package kdu.homework5.q1;

import kdu.homework5.logging.LoggingSystem;

public class MessageSender implements Runnable{
    private static final LoggingSystem ls = new LoggingSystem();

    private Message message;
    private MessageQueue messageQueue;

    public MessageSender(MessageQueue messageQueue, Message message){
        this.messageQueue = messageQueue;
        this.message = message;
    }

    @Override
    public synchronized void run() {
        messageQueue.add(message);
        ls.logInfo("Message Added with text" + message.getText());
    }
}
