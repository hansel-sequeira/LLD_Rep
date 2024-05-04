package BookMyShow.Entitites;

import BookMyShow.Enums.SeatType;

import java.util.Scanner;

public class Payment {
    public void generateInvoice(Show show, Seat seat) {
        PriceConfigurator priceConfigurator = show.getPriceConfigurator();
        int price = (seat.getSeatType() == SeatType.STANDARD) ? priceConfigurator.getStandardPrice()
                : priceConfigurator.getReclinerPrice();
        System.out.println("Your total is: Rs. " + price);

        Scanner sc = new Scanner(System.in);
        System.out.println("Do you wish to pay? (Y|y)");
        char ch = sc.next().charAt(0);
        if(ch == 'Y' || ch == 'y') {
            System.out.println("Payment successful. Redirecting back to ticket manager..");
        } else throw new RuntimeException("Payment processing failed...");
    }
}
