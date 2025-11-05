package test;

public class hecoso2sanghe16 {
  public static void main(String[] args) {
    String s = "1101101";
    // chuyển từ cơ số 2 sang thập phân
    int d2 = Integer.parseInt(s, 2);
    System.out.println(d2);
    //chuyển từ cơ số thập phân sang cơ số 16
    String hexString = Integer.toHexString(d2);
    System.out.println(hexString);
  }
}
