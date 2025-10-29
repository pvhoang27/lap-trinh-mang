import java.io.*;
import java.net.*;

public class TCPByteStreamClient {
    public static void main(String[] args) {
        String host = "203.162.10.109";
        int port = 2206;

        try (Socket socket = new Socket(host, port);
             OutputStream os = socket.getOutputStream();
             InputStream is = socket.getInputStream()) {

            // Gửi mã sinh viên và mã câu hỏi
            String request = "B21DCCN393;e2w7u45t";
            os.write(request.getBytes());
            os.flush();
            System.out.println("Sent: " + request);

            // Nhận chuỗi dữ liệu từ server
            byte[] buffer = new byte[1024];
            int length = is.read(buffer);
            String received = new String(buffer, 0, length);
            System.out.println("Received: " + received);

            // Tính tổng các số và gửi lại server
            int sum = 0;
            for (String num : received.split("\\|")) {
                sum += Integer.parseInt(num.trim());
            }
            os.write(String.valueOf(sum).getBytes());
            os.flush();
            System.out.println("Sent Sum: " + sum);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}