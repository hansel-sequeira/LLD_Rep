package BookMyShow.Entitites;

import java.util.Date;

public class Show {
    int id;
    Movie movie;
    Hall hall;
    PriceConfigurator priceConfigurator;
    Date showDateTime;
    Theater theater;
    public Show(int id, Movie movie, Date showDateTime, Theater theater, Hall hall, PriceConfigurator priceConfigurator) {
        this.id = id;
        this.movie = movie;
        this.showDateTime = showDateTime;
        this.hall = hall;
        this.theater = theater;
        this.priceConfigurator = priceConfigurator;

        theater.getTheaterManager().registerShow(this, hall);
    }

    public void printShowDetails() {
        System.out.println("Show ID: " + id + " for Movie: " + movie.movieName + " at : " + showDateTime);
    }

    PriceConfigurator getPriceConfigurator() {
        return priceConfigurator;
    }

    public Hall getHall() {
        return hall;
    }

}
