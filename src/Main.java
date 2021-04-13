public class Main implements IArithmeticsDiv{
    public static void main(String[] args) {
        System.out.println("Nazwa grupy: Chrum, ID leadera: Tomek008, rola: developer");
        System.out.println("Filip Amro - Github ID: wiezawieza10");
        System.out.println("Tomasz Nykiel - Github ID: Tomek008");
        System.out.println("Patryk Gajda - Github ID: ImAStudentAtUniversity");
        System.out.println("Jakub Kaniewski - Github ID: Blemm13 ");
    }

    @Override
    public double Division(double A, double B) {

        double threshold = 0.000001;
        if( B >= -threshold && B <= threshold ){
            return A/B;
        }
        else {
            try {
                throw new Exception("Cannot divide by 0!");
            } catch (Exception e) {
                e.printStackTrace();
            }
            return 0;
        }
    }
}