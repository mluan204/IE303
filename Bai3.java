import java.util.Arrays;
import java.util.Scanner;
import java.util.Stack;

class Toado {
    int x;
    int y;

    Toado(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

public class Bai3 {

    static Toado p0;

    // Hàm lấy phần tử thứ 2 từ đỉnh của stack
    static Toado nextToTop(Stack<Toado> S) {
        Toado p = S.pop();
        Toado res = S.peek();
        S.push(p);
        return res;
    }

    // Hàm đổi chổ 2 điểm trong mảng
    static void swap(Toado[] arr, int i, int j) {
        Toado temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    // Hàm tính khoảng cách bình phương giữa 2 điểm
    static int distSq(Toado p1, Toado p2) {
        return (p1.x - p2.x) * (p1.x - p2.x) + (p1.y - p2.y) * (p1.y - p2.y);
    }

    // Hàm xác định hướng của 3 điểm
    static int orientation(Toado p, Toado q, Toado r) {
        int val = (q.y - p.y) * (r.x - q.x) - (q.x - p.x) * (r.y - q.y);
        if (val == 0)
            return 0; // thẳng hàng
        return (val > 0) ? 1 : 2; // 1 là quay theo chiều kim đồng hồ, 2 là quay ngược chiều kim đồng hồ
    }

    // So sánh góc cực của 2 điểm với điểm gốc p0
    static int compart(Toado p1, Toado p2) {
        int o = orientation(p0, p1, p2);
        if (o == 0) {
            return (distSq(p0, p2) >= distSq(p0, p1)) ? -1 : 1;
        }
        return (o == 2) ? -1 : 1;
    }

    static void convexHull(Toado[] points, int n) {

        // Tìm điểm có tung độ nhỏ nhất
        int min = 0;
        for (int i = 1; i < n; i++) {
            if (points[i].y < points[min].y || (points[i].y == points[min].y && points[i].x < points[min].x)) {
                min = i;
            }
        }
        // Đưa điểm đó lên đầu mảng
        swap(points, 0, min);
        p0 = points[0];

        // Sắp xếp các điểm theo góc cực của p0
        Arrays.sort(points, 1, n, Bai3::compart);

        // Loại bỏ điểm thẳng hàng, chỉ giữ điểm xa nhất
        int m = 1;
        for (int i = 1; i < n; i++) {
            while (i < n - 1 && orientation(p0, points[i], points[i + 1]) == 0) {
                i++;
            }
            points[m] = points[i];
            m++;
        }

        // Nếu số điểm ít hơn 3 thì không tạo được lớp lồi
        if (m < 3)
            return;

        // Khởi tạo 3 điểm đầu tiên vào stack
        Stack<Toado> S = new Stack<>();
        S.push(points[0]);
        S.push(points[1]);
        S.push(points[2]);

        // Xử lý các điểm còn lại
        for (int i = 3; i < m; i++) {
            while (S.size() > 1 && orientation(nextToTop(S), S.peek(), points[i]) != 2) {
                S.pop();
            }
            S.push(points[i]);
        }

        // In kết quả
        while (!S.isEmpty()) {
            Toado p = S.pop();
            System.out.println(p.x + " " + p.y);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Nhap so diem: ");
        int n = sc.nextInt();
        Toado[] points = new Toado[n];
        for (int i = 0; i < n; i++) {
            int x = sc.nextInt();
            int y = sc.nextInt();
            points[i] = new Toado(x, y);
        }
        convexHull(points, n);
        sc.close();
    }

}