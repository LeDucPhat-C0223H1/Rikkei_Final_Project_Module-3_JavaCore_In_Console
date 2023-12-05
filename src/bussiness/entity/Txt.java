package bussiness.entity;

public class Txt {
    int rollno;
    String name;
    static String college = "Bưu Chính Viễn Thông";

    static void change() {
        // Thay đổi thuộc tính static (thuộc tính chung của tất cả các đối tượng)
        college = "Đại Học Công Nghệ";
    }

    Txt(int r, String n) {
        rollno = r;
        name = n;
    }

    void display() {
        System.out.println(rollno + " - " + name + " - " + college);
    }

    public static void main(String args[]) {
        Txt.change();

        Txt s1 = new Txt(111, "Thông");
        Txt s2 = new Txt(222, "Minh");
        Txt s3 = new Txt(333, "Anh");

        s1.display();
        s2.display();
        s3.display();
    }
}