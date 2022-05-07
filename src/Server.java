import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server {
    public static void main(String args[])throws Exception{
        ServerSocket ss=new ServerSocket(3333);
        Socket s=ss.accept();
        DataInputStream din=new DataInputStream(s.getInputStream());
        DataOutputStream dout=new DataOutputStream(s.getOutputStream());

        String str="";
        while(true){
            str=din.readUTF();
            System.out.println("client says: "+str);
            String[] splited = str.split("\\s+");
            List<Double> list = new ArrayList<>();
            boolean numeric = true;
            for (String i : splited){
                try {
                    Double num = Double.parseDouble(i);
                    list.add(num);
                } catch (NumberFormatException e) {
                    numeric = false;
                    break;
                }
            }

            if(numeric == true){
                double Sum = 0;
                for (Double i: list) {
                    Sum += i;
                }
                dout.writeUTF(Double.toString(Sum));
            }else{
                dout.writeUTF("Ce ati introdus nu este corect , va rog introduceti numere");
            }

            dout.flush();
        }
        //din.close();
        //s.close();
        //ss.close();
    }
}
