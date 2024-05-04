package BookMyShow.Entitites;

import BookMyShow.Enums.SeatStatus;

import java.util.*;

public class TheaterManager {
    public List<Show> showsInTheater;
    Map<Show, List<Seat>> show_seats_map;
    Map<Show, Hall> show_hall_map;

    Map<Show, List<Customer>> show_customer_map;

    public TheaterManager(List<Hall> hallList) {
        showsInTheater = new ArrayList<>();
        show_seats_map = new HashMap<>();
        show_hall_map = new HashMap<>();
        show_customer_map = new HashMap<>();
    }


    public void registerShow(Show show, Hall hall) {
        System.out.println("AAA");
        showsInTheater.add(show);
        show_hall_map.put(show, hall);
    }

    public void printAllShows() {
        System.out.println("List of all shows currently playing...");
        for(Show s : showsInTheater) {
            s.printShowDetails();
        }
    }

    public void bookShow(int showNumber, Customer customer) {
        Show show = showsInTheater.get(showNumber);
        Seat chosenSeat = null;
        try {
            // first try to find free seats for this show.
            Hall hallForShow = show.getHall();
            List<Seat> freeSeats = hallForShow.getFreeSeatDetails();
            if(freeSeats.size() == 0) {
                System.out.println("Sorry, all seats booked for this show!");
                return;
            }
            System.out.println("List of free seats: ");
            for(Seat s: freeSeats)
                System.out.println("Seat id: " + s.getSeatId() + "\t|\t" + "Type: " + s.getSeatType());
            Scanner sc = new Scanner(System.in);
            System.out.println("Enter the seat number: ");;
            int seatNumber = sc.nextInt();
            for(Seat s: freeSeats)
                if(s.getSeatId() == seatNumber) {
                    chosenSeat = s;
                    break;
                }
            chosenSeat.setSeatStatus(SeatStatus.RESERVED);

            System.out.println("Redirecting you to payment details...");
            Payment p = new Payment(); // ideally create a singleton instance out of this.
            p.generateInvoice(show, chosenSeat);

            System.out.println("You have been successfully booked for this show.");
            if(show_customer_map.get(show) == null)
                show_customer_map.put(show, Arrays.asList(customer));
            else show_customer_map.get(show).add(customer);
        } catch(Exception e) {
            System.out.println("Booking failed");
            if(chosenSeat != null) {
                chosenSeat.setSeatStatus(SeatStatus.FREE);
            }
        }
    }


}
