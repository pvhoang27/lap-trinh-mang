/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minh.ptit.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.input.KeyCode;

/**
 *
 * @author AD
 */
public class client {
    public static void main(String[] args) {
        try {
            InetAddress inetAddress=InetAddress.getByName("203.162.10.109");
            int port=2207;
            DatagramSocket client = new DatagramSocket();
            byte[] sendmsg=new byte[1024];
            byte[] receivemsg=new byte[1024];
            String s=";B18DCCN408;802";
            sendmsg=s.getBytes();
            DatagramPacket sendDatagramPacket=new DatagramPacket(sendmsg, sendmsg.length, inetAddress, port);
            client.send(sendDatagramPacket);
            DatagramPacket receiveDatagramPacket=new DatagramPacket(receivemsg, receivemsg.length);
            client.receive(receiveDatagramPacket);
            String receivedat = new String(receiveDatagramPacket.getData());
            String[] qreceive=receivedat.split(";");
            int n=Integer.parseInt(qreceive[1].trim());
            String[] a=(qreceive[2].split(","));
            int l=a.length;
            int[] b=new int[l];
            for(int i=0;i<l;i++){
                b[i]=Integer.parseInt(a[i].trim());
            }
            String send2=qreceive[0]+";";
            for(int i=1;i<=n;i++){
                int flag=0;
                for (int j = 0; j < l; j++) {
                    if(i==b[j]){
                        flag=1;
                    }
                }
                if(flag==0) send2+=i+",";

            }
            byte[] send2da=send2.getBytes();
            DatagramPacket sendDatagramPacket2=new DatagramPacket(send2da, send2da.length, inetAddress, port);
            client.send(sendDatagramPacket2);
            client.close();
        } catch (UnknownHostException ex) {
            Logger.getLogger(client.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SocketException ex) {
            Logger.getLogger(client.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
