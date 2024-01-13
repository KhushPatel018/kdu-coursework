package kdu.homework5.q1;

public class Message {
    private int id;
    private String text;

    public Message(){

    }

    public Message(int id,String text)
    {
        this.text = text;
        this.id = id;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

}
