package kdu.homework5.q2;

import kdu.homework5.q1.Message;
import kdu.homework5.q1.MessageQueue;
import kdu.homework5.q1.MessageReceiver;
import kdu.homework5.q1.MessageSender;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    public static void main(String[] args) {
        ExecutorService senderThreadPool = Executors.newFixedThreadPool(3);

        ExecutorService receiverThreadPool = Executors.newFixedThreadPool(3);

        MessageQueue messageQueue = new MessageQueue();


        for(int i = 0; i < 5;i++)
        {
            Message message = new Message(i,"Message " + i);
            senderThreadPool.submit(new MessageSender(messageQueue,message));
            receiverThreadPool.submit(new MessageReceiver(messageQueue));

        }
    }

}
