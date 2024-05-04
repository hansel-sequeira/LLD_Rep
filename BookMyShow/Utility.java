package BookMyShow;


import BookMyShow.Entitites.*;
import BookMyShow.Enums.City;
import BookMyShow.Enums.Genre;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Scanner;


public class Utility {
    CityTheaterManager cityTheaterManager = new CityTheaterManager();
    public void initialize() {
        Movie m1 = new Movie("Movie A", 100, Genre.COMEDY);
        Hall h1 = new Hall(0, 10);
        Theater t1 = new Theater(0, Arrays.asList(h1), City.BENGALURU);
        cityTheaterManager.registerTheater(t1);
        PriceConfigurator priceConfigurator = new PriceConfigurator(100, 200);
        Show s1 = new Show(0,m1,new Date(),t1,h1,priceConfigurator);
    }

    public void run(Customer c) {
        List<Theater> theaterList = cityTheaterManager.getTheaterForCity(City.BENGALURU);
        System.out.println("Theater list: ");
        for(Theater t: theaterList) {
            System.out.println("id: " + t.getId());
            t.getTheaterManager().printAllShows();
        }
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter theater you want: ");
        int theaterNum = sc.nextInt();
        System.out.println("Enter show you want: ");
        int showNum = sc.nextInt();
        Theater t = theaterList.get(theaterNum);
        t.getTheaterManager().bookShow(showNum, c);
    }
}

