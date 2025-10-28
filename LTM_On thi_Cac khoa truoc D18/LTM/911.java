
import java.io.DataInputStream;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author AD
 */
public class client {
    public static int ucln(int a, int b){
        while(a!=b){
            if(a>b) a-=b;
            else b-=a;
        }
        return a;
    }
    
    public static void main(String[] args) throws IOException {
        Socket socket=new Socket("203.162.10.109", 2207);
        DataInputStream dis=new DataInputStream(socket.getInputStream());
        DataOutputStream dos=new DataOutputStream(socket.getOutputStream());
        String str="B18DCCN408;911";
        dos.writeUTF(str);  
        int a=dis.readInt();
        int b=dis.readInt();
        int u=ucln(a,b);
        int tong=a+b;
        int tich=a*b;
        dos.writeInt(u);
        dos.writeInt(a*b/u);
        dos.writeInt(tong);
        dos.writeInt(tich);
        socket.close();
    }
}
