public class Main implements IArithmeticsMult, IArithmeticsAdd, IArithmeticsDiv, IArithmeticsDiff {

    public static void main(String[] args) {

        System.out.println("Nazwa grupy: Chrum, ID leadera: Tomek008, rola: developer");
        System.out.println("Filip Amro - Github ID: wiezawieza10"); // Zadanie 6 - comment
        System.out.println("Tomasz Nykiel - Github ID: Tomek008");
        System.out.println("Patryk Gajda - Github ID: ImAStudentAtUniversity");
        System.out.println("Jakub Kaniewski - Github ID: Blemm13 ");// TODO: 13.04.2021 New comment from task 6
    }

    @Override
    public double Difference(double A, double B) {
        return A - B;
    }

    @Override
    public double Division(double A, double B) {
        double threshold = 0.000001;
        if (B >= -threshold && B <= threshold) {
            return A / B;
        } else {
            try {
                throw new Exception("Cannot divide by 0!");
            } catch (Exception e) {
                e.printStackTrace();
            }
            return 0;
        }
    }

    @Override
    public double Multiplication ( double A, double B){
        return A * B;
    }

    @Override
    public double Addition ( double A, double B){
        return A + B;
    }
}