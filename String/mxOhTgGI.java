    /*
     * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
     * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
     */

    //[Mã câu hỏi (qCode): mxOhTgGI].  Một chương trình server cho phép giao tiếp qua giao thức UDP tại cổng 2208. Yêu cầu là xây dựng một chương trình client trao đổi thông tin với server theo kịch bản:
    //a. Gửi thông điệp là một chuỗi chứa mã sinh viên và mã câu hỏi theo định dạng “;studentCode;qCode”. Ví dụ: “;B15DCCN000;XbYdNZ3”.
    //
    //b. Nhận thông điệp là một chuỗi từ server theo định dạng “requestId;b1,b2”, trong đó:
    //    requestId là chuỗi ngẫu nhiên duy nhất.
    //    b1 là số nhị phân thứ nhất
    //    b2 là số nhị phân thứ hai.
    //Ví dụ: requestId;0100011111001101,1101000111110101
    //c. Thực hiện tính tổng hai số nhị phân nhận được, chuyển về dạng thập phân và gửi lên server theo định dạng “requestId;sum”
    //Kết quả: requestId;72130 
    //d. Đóng socket và kết thúc chương trình.
    package String;

    /**
     *
     * @author hoang
     */
    import java.util.*;
    import java.net.*;
    import java.io.*;
    import java.math.BigInteger;
    public class mxOhTgGI {
        public static void main(String[] args) throws Exception{
            // khai bao socket + luong 
            DatagramSocket socket = new DatagramSocket(); // socket 
            InetAddress sA = InetAddress.getByName("203.162.10.109"); // server Address
            int sP = 2208 ; // server Port

            // gui msv + qcode nho la ; dang trc 
            String code = ";B21DCCN393;mxOhTgGI"; // dang string

            // tạo packet gửi :  code .get byte vi dang là dạng string , độ dài , sA, port
            DatagramPacket dpGui = new DatagramPacket(code.getBytes(), code.length(), sA, sP); 
            socket.send(dpGui); // giờ mới là gửi đi

            // nhận data về 
            byte[] buffer = new byte[1024] ;// tạo mảng byte để nhận data về , chỉ cần 1024 là đủ 
            DatagramPacket dpNhan = new DatagramPacket(buffer, buffer.length); // khai báo packet nhận buffer + chiều dài
            socket.receive(dpNhan);// socket nhận

            //xử lý data đó  về dạng string  nó sẽ bao gồm reqID + 2 cái đề bài cho 
            String s1 = new String(dpNhan.getData(), 0 , dpNhan.getLength()).trim();
            // in ra để nhìn thử 
            System.out.println(s1); //ví dụ nó ra như này 8U5P9ny3;100100100001,11011100111010
            // giờ tôi cần chia chúng ra 
            String[] a1 = s1.split(";" , 2); // chia làm 2 nửa , reqID vs 2 chuỗi kia đã
            String rI = a1[0]; // requestID là phần tử đầu tiên
            String [] a2 = a1[1].split(",",2); // chia tiếp chuỗi kia vì chúng có 2 số 
            String b1 = a2[0] , b2 = a2[1]; // done
            System.out.println(b1);
            System.out.println(b2);
            // xử lý bài : cộng 2 số này ra số thập phân 
            // ban đầu b1 và b2 dang là string , ta ép chúng về là số nhị phân đã
            BigInteger n1 = new BigInteger(b1 ,2 );// bản chất khi ép về biginteger là ép về số thập phân luôn
            System.out.println(n1);
            BigInteger n2 = new BigInteger(b2,2 ); // bản chất khi ép về biginteger là ép về số thập phân luôn
            System.out.println(n2);
            // sau đó ta cộng chúng lại
            BigInteger sum = n1.add(n2);
            System.out.println(sum);
            // gộp chúng lại để thành chuỗi gửi đi
            String result = rI + ";" + sum.toString(); // chuyển sum về string để gộp
            // gửi kết quả về server
            byte [] output = result.getBytes(); // chuyển chuỗi về byte để gửi
            DatagramPacket dpGui2 = new DatagramPacket(output, output.length, sA, sP);
            socket.send(dpGui2); // gửi đi
            // không cần đóng socket vẫn AC trên hệ thống
        }
    }
    //[Mã câu hỏi (qCode): mxOhTgGI].  Một chương trình server cho phép giao tiếp qua giao thức UDP tại cổng 2208. Yêu cầu là xây dựng một chương trình client trao đổi thông tin với server theo kịch bản:
    //a. Gửi thông điệp là một chuỗi chứa mã sinh viên và mã câu hỏi theo định dạng “;studentCode;qCode”. Ví dụ: “;B15DCCN000;XbYdNZ3”.
    //
    //b. Nhận thông điệp là một chuỗi từ server theo định dạng “requestId;b1,b2”, trong đó:
    //    requestId là chuỗi ngẫu nhiên duy nhất.
    //    b1 là số nhị phân thứ nhất
    //    b2 là số nhị phân thứ hai.
    //Ví dụ: requestId;0100011111001101,1101000111110101
    //c. Thực hiện tính tổng hai số nhị phân nhận được, chuyển về dạng thập phân và gửi lên server theo định dạng “requestId;sum”
    //Kết quả: requestId;72130 
    //d. Đóng socket và kết thúc chương trình.
