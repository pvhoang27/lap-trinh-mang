import java.io.*;
import java.net.Socket;

// Định nghĩa lớp Laptop như yêu cầu bài toán
class Laptop implements Serializable {
    private static final long serialVersionUID = 20150711L;
    private int id;
    private String code;
    private String name;
    private int quantity;

    // Hàm khởi tạo
    public Laptop(int id, String code, String name, int quantity) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.quantity = quantity;
    }

    // Getter và Setter
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Laptop{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", quantity=" + quantity +
                '}';
    }
}

public class TCPClientLaptop {
    public static void main(String[] args) {
        String host = "203.162.10.109"; // Địa chỉ server
        int port = 2209;                // Cổng server
        String studentCode = "B21DCCN393"; // Mã sinh viên
        String qCode = "jo7rvYl0";       // Mã câu hỏi

        try (Socket socket = new Socket(host, port)) {
            System.out.println("[LOG] Connected to server at " + host + ":" + port);

            // Tạo luồng ObjectInputStream và ObjectOutputStream
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());

            // Gửi mã sinh viên và mã câu hỏi
            String request = studentCode + ";" + qCode;
            oos.writeObject(request);
            oos.flush();
            System.out.println("[LOG] Sent to server: " + request);

            // Nhận đối tượng Laptop từ server
            Laptop laptop = (Laptop) ois.readObject();
            System.out.println("[LOG] Received from server: " + laptop);

            // Sửa thông tin bị sai
            // Đảo ngược thứ tự các từ trong tên
            String[] nameParts = laptop.getName().split(" ");
            StringBuilder correctedName = new StringBuilder();
            for (int i = nameParts.length - 1; i >= 0; i--) {
                correctedName.append(nameParts[i]);
                if (i != 0) {
                    correctedName.append(" ");
                }
            }
            laptop.setName(correctedName.toString());

            // Đảo ngược số lượng sản phẩm
            int reversedQuantity = reverseNumber(laptop.getQuantity());
            laptop.setQuantity(reversedQuantity);

            System.out.println("[LOG] Corrected Laptop: " + laptop);

            // Gửi đối tượng Laptop đã sửa lại lên server
            oos.writeObject(laptop);
            oos.flush();
            System.out.println("[LOG] Sent corrected Laptop to server.");

            System.out.println("[LOG] Connection closed.");

        } catch (IOException | ClassNotFoundException e) {
            System.err.println("[ERROR] " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Hàm hỗ trợ đảo ngược số
    private static int reverseNumber(int number) {
        int reversed = 0;
        while (number != 0) {
            reversed = reversed * 10 + number % 10;
            number /= 10;
        }
        return reversed;
    }
}