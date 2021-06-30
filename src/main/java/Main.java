import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    static int userChoose;
    static String currencyCode;
    static String timePeriod;
    static String secondCurrencyCode;
    static double[] currencyValue;
    static double [] secondCurrencyValue;

    public static void main(String[] args){
        boolean isRunning = true;
        Scanner input = new Scanner(System.in);
        while (isRunning) {
            System.out.println("Wybierz opcję:");
            System.out.println("1 - wyznaczenie ilości sesji wzrostowych, spadkowych i bez zmian");
            System.out.println("2 - miary statystyczne wybranej waluty");
            System.out.println("3 - rozkład zmian miesięcznych i kwartalnych dwoch wybranych walut");
            System.out.println("4 - zakończ działanie aplikacji");

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
                    if (determineSessions(input) == -1)
                        continue;
                    else
                        break;

                case 2:
                    if (statisticalMeasures(input) == -1)
                        continue;
                    else
                        break;

                case 3:
                    if (distributionOfChanges(input) == -1)
                        continue;
                    else
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

    static private int determineSessions(Scanner input){
        System.out.println("Podaj kod waluty:");
        currencyCode = input.nextLine();

        System.out.println("Podaj okres czasu:");
        timePeriod = input.nextLine();

        currencyValue = Parser.getCurrency(currencyCode,timePeriod);
        if(currencyValue == null){
            System.out.println("Podano nieprawidłowe dane.");
            return -1;
        }

        System.out.println("Liczba sesji wzrostowych: " + AnalysisData.upSession(currencyValue));
        System.out.println("Liczba sesji bez zmian: " + AnalysisData.stableSession(currencyValue));
        System.out.println("Liczba sesji spadkowych: " + AnalysisData.downSession(currencyValue));
        System.out.println("**************************");
        return 0;
    }

    static private int statisticalMeasures(Scanner input){
        System.out.println("Podaj kod waluty:");
        currencyCode = input.nextLine();

        System.out.println("Podaj okres czasu:");
        timePeriod = input.nextLine();

        currencyValue = Parser.getCurrency(currencyCode,timePeriod);
        if(currencyValue == null){
            System.out.println("Podano nieprawidłowe dane.");
            return -1;
        }

        System.out.println("Mediana: " + AnalysisData.getMedian(currencyValue));
        System.out.println("Dominanta: " + AnalysisData.getDominant(currencyValue));
        System.out.println(("Odchylenie standardowe: " + AnalysisData.getStandardDeviation(currencyValue)));
        System.out.println("Współczynnik zmienności: " + AnalysisData.coefficientOfVariation(currencyValue));
        System.out.println("**************************");
        return 0;
    }

    static private int distributionOfChanges(Scanner input){
        System.out.println("Podaj kod pierwszej waluty:");
        currencyCode = input.nextLine();

        System.out.println("Podaj kod drugiej waluty:");
        secondCurrencyCode = input.nextLine();

        System.out.println("Podaj okres czasu:");
        timePeriod = input.nextLine();

        currencyValue = Parser.getCurrency(currencyCode,timePeriod);
        secondCurrencyValue = Parser.getCurrency(secondCurrencyCode, timePeriod);
        if(currencyValue == null || secondCurrencyValue == null){
            System.out.println("Podano nieprawidłowe dane.");
            return -1;
        }

        int [] res = AnalysisData.distributionChanges(currencyValue, secondCurrencyValue);
        try {
            AnalysisData.printDistributionChanges(res);
        }
        catch (NullPointerException err){
            System.out.println(err.getMessage());
            return -1;
        }
        System.out.println("**************************");
        return 0;
    }
}
