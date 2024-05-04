package BookMyShow.Entitites;

import BookMyShow.Enums.SeatStatus;
import BookMyShow.Enums.SeatType;

import java.util.ArrayList;
import java.util.List;

public class Hall {
    int hallId;
    List<Seat> seats;
    public Hall(int hallId, int seatCount) {
        this.hallId = hallId;
        seats = new ArrayList<>();
        for(int i=0;i<seatCount/2;i++)
            seats.add(new Seat(i, SeatType.STANDARD));
        for(int i=seatCount/2;i<seatCount;i++)
            seats.add(new Seat(i, SeatType.RECLINE));
    }

    public List<Seat> getFreeSeatDetails() {
        List<Seat> result = new ArrayList<>();
        for(Seat seat : seats) {
            if(seat.seatStatus == SeatStatus.FREE)
                result.add(seat);
        }
        return result;
    }

}
