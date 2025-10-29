/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author hoang
 */
import java.io.*;
import java.net.*;
import java.util.*;
public class TEGL64pg {
    public static void main(String[] args) {
        String host = "203.162.10.109";
        int port = 2206;
        String studentCode = "B21DCCN393";  // 👉 sửa thành mã sinh viên của bạn
        String qCode = "TEGL64pg";          // mã câu hỏi
        
        try (Socket socket = new Socket()) {
            // Thiết lập timeout 5s
            socket.connect(new InetSocketAddress(host, port), 5000);
            socket.setSoTimeout(5000);

            System.out.println("✅ Đã kết nối tới server!");

            // Luồng vào/ra dạng byte
            InputStream in = socket.getInputStream();
            OutputStream out = socket.getOutputStream();

            // a. Gửi mã sinh viên và mã câu hỏi
            String sendMsg = studentCode + ";" + qCode;
            out.write(sendMsg.getBytes());
            out.flush();
            System.out.println("➡️ Gửi: " + sendMsg);

            // b. Nhận dữ liệu từ server
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            String data = reader.readLine();  // đọc 1 dòng dữ liệu số
            System.out.println("⬅️ Nhận dữ liệu: " + data);

            // c. Tìm chuỗi con tăng dần dài nhất
            String result = findLongestIncreasingSubsequence(data);
            System.out.println("📤 Kết quả gửi lại: " + result);

            // Gửi kết quả lên server
            out.write(result.getBytes());
            out.flush();

            // d. Đóng kết nối
            socket.close();
            System.out.println("🔚 Đã đóng kết nối.");

        } catch (SocketTimeoutException e) {
            System.err.println("⏰ Timeout khi kết nối hoặc nhận dữ liệu!");
        } catch (IOException e) {
            System.err.println("❌ Lỗi I/O: " + e.getMessage());
        }
    }

    // Hàm tìm chuỗi con tăng dần liên tiếp dài nhất
    private static String findLongestIncreasingSubsequence(String input) {
        String[] parts = input.trim().split(",");
        List<Integer> numbers = new ArrayList<>();
        for (String p : parts) {
            try {
                numbers.add(Integer.parseInt(p.trim()));
            } catch (NumberFormatException e) {
                // bỏ qua phần tử không hợp lệ
            }
        }

        int maxLen = 1, curLen = 1, startIdx = 0, bestStart = 0;
        for (int i = 1; i < numbers.size(); i++) {
            if (numbers.get(i) > numbers.get(i - 1)) {
                curLen++;
                if (curLen > maxLen) {
                    maxLen = curLen;
                    bestStart = startIdx;
                }
            } else {
                curLen = 1;
                startIdx = i;
            }
        }

        List<Integer> sub = numbers.subList(bestStart, bestStart + maxLen);
        String seq = sub.toString().replace("[", "").replace("]", "").replace(" ", "");
        return seq + ";" + maxLen;
    }
}
