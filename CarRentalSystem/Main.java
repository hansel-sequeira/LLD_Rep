package CarRentalSystem;

import java.util.*;

class User {
    int userID;
    String driversLicenseID;
    public User(int userID, String driversLicenseID) {
        this.userID = userID;
        this.driversLicenseID = driversLicenseID;
    }
}
enum VehicleType {
    SEDAN,
    SUV,
    BIKE
}
class Vehicle {
    int vehicleId;
    String make;
    String model;
    int mileage;
    VehicleType vehicleType;
    Vehicle(int vehicleId, String make, String model, int mileage, VehicleType vehicleType) {
        this.vehicleId = vehicleId;
        this.make = make;
        this.model = model;
        this.mileage = mileage;
        this.vehicleType = vehicleType;
    }

}
enum City {
    MUMBAI,
    BENGALURU
}
class Address {
    City city;
    String locationAddress;
    int zipCode;
    Address(City city, String locationAddress, int zipCode) {
        this.city = city;
        this.locationAddress = locationAddress;
        this.zipCode = zipCode;
    }

}
enum LeaseType {
    HOUR,
    WEEK,
    MONTH
}
class CostConfigurator {
    int hourlyCost;
    int weeklyCost;
    int monthlyCost;
    CostConfigurator(int hourlyCost, int weeklyCost, int monthlyCost) {
        this.hourlyCost = hourlyCost;
        this.weeklyCost = weeklyCost;
        this.monthlyCost = monthlyCost;
    }
}
class RentalPosting {
    Vehicle vehicle;
    CostConfigurator costConfigurator;
    Address address;
    RentalPosting(Vehicle vehicle, CostConfigurator costConfigurator, Address address) {
        this.vehicle = vehicle;
        this.costConfigurator = costConfigurator;
        this.address = address;
    }
}
enum ReservationStatus {
    PENDING,
    BOOKED,
    FAILED
}
class ReservationDetails {
    RentalPosting rentalPosting;
    User user;
    ReservationStatus reservationStatus;
    ReservationDetails(User user, RentalPosting rentalPosting, ReservationStatus reservationStatus) {
        this.user = user;
        this.rentalPosting = rentalPosting;
        this.reservationStatus = reservationStatus;
    }
    void setReservationStatus(ReservationStatus reservationStatus) {
        this.reservationStatus = reservationStatus;
    }
}
class Payment {
    static void generateInvoice(RentalPosting rentalPosting, LeaseType leaseType, int leaseDuration) {
        int cost = 0;
        CostConfigurator costConfigurator = rentalPosting.costConfigurator;
        switch(leaseType) {
            case HOUR: cost = costConfigurator.hourlyCost * leaseDuration;
                        break;
            case WEEK: cost = costConfigurator.weeklyCost * leaseDuration;
                        break;
            case MONTH: cost = costConfigurator.monthlyCost * leaseDuration;
                        break;
        }

        System.out.println("The total cost is: " + cost);
        System.out.println("Enter Y|y for payment: ");
        Scanner sc = new Scanner(System.in);
        char ch = sc.next().charAt(0);
        if(ch == 'Y' || ch == 'y') {
            System.out.println("Payment successful! Redirecting back to inventory manager");
        } else throw new RuntimeException("Payment failed!");

    }
}
class LocationInventoryManager{
    City city;
    List<RentalPosting> availableVehicles;
    List<ReservationDetails> reservationDetails;
    LocationInventoryManager(City city) {
        this.city = city;
        reservationDetails = new ArrayList<>();
        availableVehicles = new ArrayList<>();
    }
    void printAvailableVehicles() {
        for(RentalPosting rentalPosting : availableVehicles)
            System.out.println(rentalPosting.vehicle.vehicleId + " " + rentalPosting.vehicle.make);
    }
    void rentVehicle(User user, int postingNumber, LeaseType leaseType, int leaseDuration) {
        RentalPosting rentalPosting = availableVehicles.get(postingNumber);

        ReservationDetails reservationDetail = new ReservationDetails(user, rentalPosting, ReservationStatus.PENDING);
        //first check if payment is successful, then remove the vehicle posting.
        try {
            Payment.generateInvoice(rentalPosting, leaseType, leaseDuration);
            System.out.println("You have successfully reserved this vehicle");
            reservationDetail.setReservationStatus(ReservationStatus.BOOKED);
            availableVehicles.remove(rentalPosting);
        } catch (Exception e) {
            System.out.println("Booking unsuccessful.");
            reservationDetail.setReservationStatus(ReservationStatus.FAILED);
        }
        reservationDetails.add(reservationDetail);
    }
}

class BookingManager {
    List<LocationInventoryManager> locationInventoryManagers;
    BookingManager() {
        locationInventoryManagers = new ArrayList<>();
    }
    void addLocationInventoryManager(LocationInventoryManager locationInventoryManager) {
        locationInventoryManagers.add(locationInventoryManager);
    }

    void runner(User u, City c) {
        LocationInventoryManager locationInventoryManager = getInventoryBasisLocation(c);
        locationInventoryManager.printAvailableVehicles();
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter your choice: ");
        int ch = sc.nextInt();
        //assumption: lease type is weeks and duration is 2.
        locationInventoryManager.rentVehicle(u, ch-1, LeaseType.WEEK, 2);
    }
    LocationInventoryManager getInventoryBasisLocation(City city) {
        for(LocationInventoryManager l : locationInventoryManagers) {
            if(l.city == city)
                return l;
        }
        throw new RuntimeException("No vehicle found for the city.");
    }
}



public class Main {
    public static void main(String[] args) {
        User u1 = new User(1, "KA-1");
        Vehicle v1 = new Vehicle(1, "Toyota", "RAV4", 1000, VehicleType.SUV);
        Address a1 = new Address(City.BENGALURU, "BLR", 12101);
        CostConfigurator c1 = new CostConfigurator(100, 500, 1500);
        RentalPosting r1 = new RentalPosting(v1, c1, a1);
        LocationInventoryManager l1 = new LocationInventoryManager(City.BENGALURU);
        l1.availableVehicles.add(r1);
        BookingManager b1 = new BookingManager();
        b1.addLocationInventoryManager(l1);
        //assumption: city being called is Bengaluru.
        b1.runner(u1, City.BENGALURU);

    }
}
