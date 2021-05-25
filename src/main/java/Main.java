package main.java;

import java.net.HttpURLConnection;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        int userChoose = 0;
        String currencyCode = "";
        String secondCurrencyCode = "";
        Scanner input = new Scanner(System.in);
        double [] currencyValue;

        while (true) {
            System.out.println("Wybierz opcję:");
            System.out.println("1 - wyznaczenie ilości sesji wzrostowych, spadkowych i bez zmian");
            System.out.println("2 - miary statystyczne wybranej waluty");
            System.out.println("3 - rozkład zmian miesięcznych i kwartalnych dwoch wybranych walut");
            System.out.println("4 - zakoncz dzialanie aplikacji");

            userChoose = input.nextInt();
            input.nextLine();

            if(userChoose == 4 || userChoose == 0){
                break;
            }

            if(userChoose == 1){
                System.out.println("Podaj kod waluty:");
                currencyCode = input.nextLine();
                currencyValue = Parser.getCurrencyWeek(currencyCode);
            }else if(userChoose == 2){
                System.out.println("Podaj kod waluty:");
                currencyCode = input.nextLine();
            }else if(userChoose == 3){
                System.out.println("Podaj kod waluty:");
                currencyCode = input.nextLine();

                System.out.println("Podaj kod waluty:");
                secondCurrencyCode = input.nextLine();
            }
        }
    }
}
