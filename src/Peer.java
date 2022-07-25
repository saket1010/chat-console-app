import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class Peer {

    private String ip="127.0.0.1";
    private int port;
    private Socket sendsocket=null;

    private Socket recivesocket=null;
    private ServerSocket server=null;
    private DataOutputStream out=null;
    private DataInputStream in	 = null;
    private Peer connectedpeer;

    Peer(int port)
    {
        this.port=port;
        try {
            server = new ServerSocket(port);
        }catch (Exception e) {
            System.out.println(e);
        }
    }

    public boolean connect(Peer peer)
    {
        try
        {
            this.sendsocket = new Socket(peer.ip, peer.port);
            System.out.println("Connected");
            // sends output to the socket
            this.out = new DataOutputStream(this.sendsocket.getOutputStream());
            this.connectedpeer=peer;
            return true;
        }
        catch(UnknownHostException u)
        {
            System.out.println(u);
        }
        catch(IOException i) {
            System.out.println(i);
        }
        return  false;
    }
    public boolean connect(String ip,int port)
    {
        try
        {
            this.sendsocket = new Socket(ip,port);
            System.out.println("Connected");
            // sends output to the socket
            this.out = new DataOutputStream(this.sendsocket.getOutputStream());
            return true;
        }
        catch(UnknownHostException u)
        {
            System.out.println(u);
        }
        catch(IOException i) {
            System.out.println(i);
        }
        return  false;
    }


    public boolean sendMessage(String message)
    {
        try {
            this.out.writeUTF(message);
            return true;
        }catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    public void receiveMessage()
    {
        try {
            this.recivesocket = server.accept();
            in = new DataInputStream(new BufferedInputStream(recivesocket.getInputStream()));
            System.out.println(in.readUTF());
        }catch (Exception e)
        {
            System.out.println(e);
        }
    }
}
