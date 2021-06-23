import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {

        boolean isRunning = true;
        int userChoose = 0;
        String currencyCode = "";
        String timePeriod = "";
        String secondCurrencyCode = "";
        Scanner input = new Scanner(System.in);
        double[] currencyValue;
        double [] secondCurrencyValue;

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

                    System.out.println("Podaj okres czasu:");
                    timePeriod = input.nextLine();

                    currencyValue = Parser.getCurrency(currencyCode,timePeriod);
                    if(currencyValue == null){
                        continue;
                    }

                    System.out.println("Liczba sesji wzrostowych: " + AnalysisData.upSession(currencyValue));
                    System.out.println("Liczba sesji bez zmian: " + AnalysisData.stableSession(currencyValue));
                    System.out.println("Liczba sesji spadkowych: " + AnalysisData.downSession(currencyValue));
                    System.out.println("**************************");
                    break;

                case 2:
                    System.out.println("Podaj kod waluty:");
                    currencyCode = input.nextLine();

                    System.out.println("Podaj okres czasu:");
                    timePeriod = input.nextLine();

                    currencyValue = Parser.getCurrency(currencyCode,timePeriod);
                    if(currencyValue == null){
                        continue;
                    }

                    System.out.println("Mediana: " + AnalysisData.getMedian(currencyValue));
                    System.out.println("Dominanta: " + AnalysisData.getDominant(currencyValue));
                    System.out.println(("Odchylenie standardowe: " + AnalysisData.getStandardDeviation(currencyValue)));
                    System.out.println("Współczynnik zmienności: " + AnalysisData.coefficientOfVariation(currencyValue));
                    System.out.println("**************************");
                    break;

                case 3:
                    System.out.println("Podaj kod pierwszej waluty:");
                    currencyCode = input.nextLine();

                    System.out.println("Podaj kod drugiej waluty:");
                    secondCurrencyCode = input.nextLine();

                    System.out.println("Podaj okres czasu:");
                    timePeriod = input.nextLine();

                    currencyValue = Parser.getCurrency(currencyCode,timePeriod);
                    secondCurrencyValue = Parser.getCurrency(secondCurrencyCode, timePeriod);
                    if(currencyValue == null || secondCurrencyValue == null){
                        continue;
                    }

                    int [] res = AnalysisData.distributionChanges(currencyValue, secondCurrencyValue);
                    AnalysisData.printDistributionChanges(res);
                    System.out.println("**************************");
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
