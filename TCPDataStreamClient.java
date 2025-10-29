import java.io.*;
import java.net.*;

public class TCPDataStreamClient {
    public static void main(String[] args) {
        String host = "203.162.10.109";
        int port = 2207;

        try (Socket socket = new Socket(host, port);
             DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
             DataInputStream dis = new DataInputStream(socket.getInputStream())) {

            // Gửi mã sinh viên và mã câu hỏi
            String studentCode = "B21DCCN393;PRUGofD9";
            dos.writeUTF(studentCode);
            dos.flush();
            System.out.println("Sent: " + studentCode);

            // Nhận 2 số từ server
            int a = dis.readInt();
            int b = dis.readInt();
            System.out.println("Received: a = " + a + ", b = " + b);

            // Tính UCLN và gửi lại
            int ucln = findGCD(a, b);
            dos.writeInt(ucln);
            dos.flush();
            System.out.println("Sent UCLN: " + ucln);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static int findGCD(int a, int b) {
        return b == 0 ? a : findGCD(b, a % b);
    }
}