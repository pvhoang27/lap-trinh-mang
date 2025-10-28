/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.DatagramSocket;
import java.net.Socket;
import java.util.HashMap;
import sun.net.www.content.audio.x_aiff;

/**
 *
 * @author ThaiKaka
 */
public class Bai721{
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("203.162.10.109", 2208);
        BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        
        bw.write(",721");
        
        String s = br.readLine();
        String b = Xuly(s);
        bw.write(b);
        bw.flush();
        System.out.println(b);
        
        bw.close();
        br.close();
        socket.close();
        
        
        
    }
        static String Xuly(String s)
    {
       String a ="";
       HashMap<Character, Integer> map = new HashMap<Character, Integer>();
       s = s.replaceAll("\\s+","");       
       char[] charArray = s.toCharArray();
        for (char c : charArray)
        {
            if(map.containsKey(c))
            {
                map.put(c, map.get(c)+1);
            }
            else
            {
                  map.put(c, 1);
            }
        }
        int m = 0;
        for (HashMap.Entry<Character, Integer> entry : map.entrySet()) {
            if(m < entry.getValue()) m = entry.getValue();
           
        }
         for (HashMap.Entry<Character, Integer> entry : map.entrySet()) {
             if( m == entry.getValue())  a = a+entry.getKey()+":"+entry.getValue()+",";
             
        }
       
        return a;
    } 
}
