public class Bai2 {
    public static void main(String[] args) {
        double r = 1.0;
        double pi = 0.0;
        int diemtrongvongtron = 0;

        int n = 100;
        for (int i = 0; i < n; i++) {
            double x = Math.random() * 2 - 1;
            double y = Math.random() * 2 - 1;
            if (x * x + y * y <= r * r) {
                diemtrongvongtron++;
            }
        }

        pi = 4.0 * diemtrongvongtron / n; // theo công thức Monte Carlo
        System.out.println("Gia tri xap xi cua pi la: " + pi);

    }
}
