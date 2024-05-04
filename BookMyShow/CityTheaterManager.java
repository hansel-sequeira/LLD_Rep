package BookMyShow.Entitites;

import BookMyShow.Enums.City;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class CityTheaterManager {
    HashMap<City, List<Theater>> city_theater_map = new HashMap<>();

    public void registerTheater(Theater t) {
        City c = t.getCity();
        if(city_theater_map.get(c) == null)
            city_theater_map.put(c, Arrays.asList(t));
        else city_theater_map.get(c).add(t);
    }

    public List<Theater> getTheaterForCity(City c) {
        return city_theater_map.get(c);
    }

}
