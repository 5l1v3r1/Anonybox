import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;
public class client_demo {

    public static void main(String[] args) {
                try{
                    String ip = args[0];
                    String key = args[1];
             Socket s = new Socket(ip,2468);
             DataInputStream dis = new DataInputStream(s.getInputStream());
             DataOutputStream dos = new DataOutputStream(s.getOutputStream());
             BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
             String serverMsg, clientMsg;
             do{
                System.out.println("CLIENT: ");
                clientMsg = br.readLine();
                String send = AES.encrypt(clientMsg,key);
                dos.writeUTF(send);
                dos.flush();
                serverMsg = dis.readUTF();
                String msg = "";
                System.out.println(serverMsg);
                msg = AES.decrypt(serverMsg,key);
                System.out.println("SERVER: "+msg);
            }
            while(true);
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
        }

}
}
