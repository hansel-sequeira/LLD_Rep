package BookMyShow.Entitites;

import BookMyShow.Enums.SeatStatus;
import BookMyShow.Enums.SeatType;

public class Seat {
    int seatId;
    SeatType seatType;
    SeatStatus seatStatus;

    public int getSeatId() {
        return seatId;
    }

    public SeatType getSeatType() {
        return seatType;
    }

    public SeatStatus getSeatStatus() {
        return seatStatus;
    }

    public Seat(int seatId, SeatType seatType) {
        this.seatId = seatId;
        this.seatType = seatType;
        this.seatStatus = SeatStatus.FREE;
    }

    public void setSeatStatus(SeatStatus seatStatus) {
        this.seatStatus = seatStatus;
    }

}
