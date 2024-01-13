package kdu.homework5.q1;



import java.util.ArrayList;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        MessageQueue messageQueue = new MessageQueue();
        ArrayList<Thread> senders = new ArrayList<>();
        ArrayList<Thread> receivers = new ArrayList<>();
        for(int i = 0;i < 3;i++)
        {
            senders.add(new Thread(new MessageSender(messageQueue,new Message(i,"Message " + i))));
            receivers.add(new Thread(new MessageReceiver(messageQueue)));
        }
        for(int i = 0;i < 3;i++)
        {
            senders.get(i).start();
            receivers.get(i).start();
        }



    }

}