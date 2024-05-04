package BookMyShow;

import BookMyShow.Entitites.Customer;

import java.util.Scanner;





public class BookMyShow {


    public static void main(String[] args) {

        Utility u = new Utility();
        u.initialize();;
        Customer c1 = new Customer("Customer A");

        Scanner sc = new Scanner(System.in);
        while(true) {

            u.run(c1);
            System.out.println("Press N | n to quit.");
            char ch = sc.next().charAt(0);
            if(ch == 'N' || ch == 'n') {
                System.out.println("Goodbye");
                break;
            }
        }
    }
}
