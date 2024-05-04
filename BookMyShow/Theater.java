package BookMyShow.Entitites;

import BookMyShow.Enums.City;

import java.util.List;

public class Theater {
        int id;
        City city;
        List<Hall> hallList;
        TheaterManager theaterManager;

        public Theater(int id, List<Hall> hallList, City city) {
            theaterManager = new TheaterManager(hallList);
            this.id = id;
            this.city = city;
            this.hallList = hallList;
        }

        public City getCity() {
            return this.city;
        }

        public int getId() {
            return id;
        }

        public TheaterManager getTheaterManager() {
            return theaterManager;
        }

}
