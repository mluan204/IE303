import java.util.Scanner;

public class Bai1 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Nhap vao ban kinh r: ");
        double r = sc.nextDouble();
        double dientich;

        int n = 10000;
        int diemtrongvongtron = 0;

        for (int i = 0; i < n; i++) {
            double x = Math.random() * 2 * r - r; // từ -r tới r
            double y = Math.random() * 2 * r - r; // từ -r tới r
            if (x * x + y * y <= r * r) {
                diemtrongvongtron++;
            }
        }

        dientich = 4.0 * r * r * diemtrongvongtron / n; // theo công thức Monte Carlo
        System.out.println("Dien tich xap xi cua hinh tron la: " + dientich);

        sc.close();
    }
}
