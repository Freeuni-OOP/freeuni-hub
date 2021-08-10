package Manage.HelperClasses;

public class Message {

    private String messageText;
    private int sender_id;
    private int receiver_id;
    public Message(int sender_id, int receiver_id, String messageText){
        this.sender_id = sender_id;
        this.messageText = messageText;
        this.receiver_id =receiver_id;
    }

    public int getSender_id(){
        return sender_id;
    }

    public int getReceiver_id() {
        return receiver_id;
    }
    public String getMessageText(){
        return messageText;
    }
}
