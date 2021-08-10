package Manage.HelperClasses;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MessageTest {
    @Test
    public void testSenderId(){
        Message message = new Message(2,3, "text");
        assertEquals(2,message.getSender_id());
        Message message1 = new Message(-3,2,"fsfd");
        assertEquals(-3,message1.getSender_id());
    }

    @Test
    public void testReceiverId(){
        Message message = new Message(2,3, "text");
        assertEquals(3,message.getReceiver_id());
        Message message1 = new Message(-3,-1,"fsfd");
        assertEquals(-1,message1.getReceiver_id());
    }
    @Test
    public void testGetMessageText(){
        Message message = new Message(2,3, "text");
        assertEquals("text",message.getMessageText());
        Message message1 = new Message(-3,-1,null);
        assertEquals(null,message1.getMessageText());
    }
}
