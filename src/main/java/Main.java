import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        boolean isRunning = true;
        int userChoose = 0;
        String currencyCode = "";
        String secondCurrencyCode = "";
        Scanner input = new Scanner(System.in);
        double[] currencyValue;

        while (isRunning) {
            System.out.println("Wybierz opcję:");
            System.out.println("1 - wyznaczenie ilości sesji wzrostowych, spadkowych i bez zmian");
            System.out.println("2 - miary statystyczne wybranej waluty");
            System.out.println("3 - rozkład zmian miesięcznych i kwartalnych dwoch wybranych walut");
            System.out.println("4 - zakoncz dzialanie aplikacji");

            try{
                userChoose = input.nextInt();
                input.nextLine();
            }
            catch(InputMismatchException err){
                System.out.println("Nieprawidłowa komenda.");
                input.nextLine();
                continue;
            }

            switch (userChoose) {
                case 1:
                    System.out.println("Podaj kod waluty:");
                    currencyCode = input.nextLine();
                    try{
                        currencyValue = Parser.getCurrency(currencyCode, "week");
                    }
                    catch(Exception error){
                        System.out.println("Podano nieprawidłowe dane.");
                    }
                    break;
                case 2:
                    System.out.println("Podaj kod waluty:");
                    currencyCode = input.nextLine();
                    break;
                case 3:
                    System.out.println("Podaj kod waluty:");
                    currencyCode = input.nextLine();
                    System.out.println("Podaj kod waluty:");
                    secondCurrencyCode = input.nextLine();
                    break;
                case 4:
                    isRunning = false;
                    break;
                default:
                    System.out.println("Nieprawidłowa komenda.");
                    break;
            }
        }
        input.close();
    }
}
