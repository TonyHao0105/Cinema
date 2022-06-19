package cinema;

import java.text.DecimalFormat;
import java.util.Objects;
import java.util.Scanner;

public class Cinema {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the number of rows:");
        int rows = scanner.nextInt();
        System.out.println("Enter the number of seats in each row:");
        int seatsInRow = scanner.nextInt();
        int purchasedTicket = 0;
        int currentIncome = 0;
        int totalIncome;
        if (rows * seatsInRow <= 60) {
            totalIncome = rows * seatsInRow * 10;
        } else {
            totalIncome = rows * seatsInRow * 10 - (rows - rows / 2) * seatsInRow * 2;
        }

        String[][] seats = new String[rows + 1][seatsInRow + 1];
        for (int i = 0; i < seats.length; i++) {
            seats[i][0] = String.valueOf(i);
        }
        for (int i = 0; i < seats[0].length; i++) {
            seats[0][i] = String.valueOf(i);
        }
        for (int i = 1; i < seats.length; i++) {
            for (int j = 1; j < seats[0].length; j++) {
                seats[i][j] = "S";
            }
        }
        seats[0][0] = " ";

        int action;

        System.out.println("""
                1. Show the seats
                2. Buy a ticket
                3. Statistics
                0. Exit""");

        action = scanner.nextInt();
        while (action != 0) {
            switch (action) {
                case 1 -> {
                    System.out.println("Cinema:");
                    for (String[] seat : seats) {
                        for (String s : seat) {
                            System.out.print(s + " ");
                        }
                        System.out.println();
                    }
                }
                case 2-> {
                    System.out.println("Enter a row number:");
                    int rowNumber = scanner.nextInt();
                    System.out.println("Enter a seat number in that row:");
                    int numberInRow = scanner.nextInt();
                    int ticketPrice;
                    boolean input = true;
                    while (input) {
                        if (rowNumber <= 0 || rowNumber > seats.length - 1 ||
                                numberInRow <= 0 || numberInRow > seats[0].length - 1) {
                            System.out.println("Wrong input!");
                            System.out.println("Enter a row number:");
                            rowNumber = scanner.nextInt();
                            System.out.println("Enter a seat number in that row:");
                            numberInRow = scanner.nextInt();
                            continue;
                        }
                        if (Objects.equals(seats[rowNumber][numberInRow], "B")) {
                            System.out.println("That ticket has already been purchased!");
                            System.out.println("Enter a row number:");
                            rowNumber = scanner.nextInt();
                            System.out.println("Enter a seat number in that row:");
                            numberInRow = scanner.nextInt();
                            continue;
                        }
                        input = false;
                    }
                    if (rows * seatsInRow > 60 && rowNumber > rows / 2) {
                        ticketPrice = 8;
                    } else {
                        ticketPrice = 10;
                    }
                    currentIncome += ticketPrice;
                    System.out.printf("Ticket price: $%d\n", ticketPrice);
                    seats[rowNumber][numberInRow] = "B";
                    purchasedTicket++;
                }
                case 3-> {
                    double percentage = (double) purchasedTicket / ((seats.length - 1) * (seats[0].length - 1)) * 100;
                    DecimalFormat df = new DecimalFormat("0.00");
                    System.out.printf("Number of purchased tickets: %d\n", purchasedTicket);
                    System.out.println("Percentage: " + df.format(percentage) + "%");
                    System.out.printf("Current income: $%d\nTotal income: $%d\n", currentIncome, totalIncome);
                }
            }
            System.out.println("""
                1. Show the seats
                2. Buy a ticket
                3. Statistics
                0. Exit""");
            action = scanner.nextInt();
        }
    }
}