package kdu.homework5.q1;

import java.util.ArrayDeque;
import java.util.Queue;

public class MessageQueue {
    private final Queue<Message> messages = new ArrayDeque<>();
    public Queue<Message> getMessages() {
        return messages;
    }

    public void add(Message msg)
    {
        messages.add(msg);
    }

    public synchronized Message receive()
    {
        Message msg = new Message();
        while (true){
            if(!messages.isEmpty()){
                msg =  messages.poll();
                break;
            }
        }
        return msg;
    }


}
