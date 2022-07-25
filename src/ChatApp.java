import java.util.Scanner;

public class ChatApp {


    public static void main(String[] args) {
        Peer p1=new Peer(1000);
        Peer p2=new Peer(5000);
        p1.connect(p2);
        p1.sendMessage("Saket: How are you");
        p2.connect(p1);
        p2.sendMessage("shubham: I am fine");
        p2.receiveMessage();
        p1.receiveMessage();
   }
}
