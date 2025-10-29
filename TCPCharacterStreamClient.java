import java.io.*;
import java.net.*;

public class TCPCharacterStreamClient {
    public static void main(String[] args) {
        String host = "203.162.10.109";
        int port = 2208;

        try (Socket socket = new Socket(host, port);
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
             BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

            // Gửi mã sinh viên và câu hỏi
            String request = "B21DCCN393;Bvc2CIF1";
            writer.write(request);
            writer.newLine();
            writer.flush();
            System.out.println("Sent: " + request);

            // Nhận chuỗi từ server
            String response = reader.readLine();
            System.out.println("Received: " + response);

            // Loại bỏ ký tự đặc biệt và gửi lại
            String cleanedString = response.replaceAll("[^a-zA-Z0-9]", "");
            writer.write(cleanedString);
            writer.newLine();
            writer.flush();
            System.out.println("Sent Cleaned String: " + cleanedString);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}