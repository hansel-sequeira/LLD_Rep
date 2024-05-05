package VendingMachine;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

class Product {
    String productName;
    int id;
    int inventoryCount;
    int cost;
    Product(String productName, int id, int inventoryCount, int cost) {
        this.productName = productName;
        this.id = id;
        this.inventoryCount = inventoryCount;
        this.cost = cost;
    }
}

class VendingMachine {
    List<Product> productTray;
    State currentState;
    VendingMachine(List<Product> productTray) {
        this.productTray = productTray;
        this.currentState = new InitialState(this);
    }
    void setState(State newState) {
        currentState = newState;
    }
}

interface State {
    void printProductTray();
    void chooseProductOption();
    void abort();
    void initiatePayment();
    void dispenseProduct();
}
class InitialState implements State {
     VendingMachine vendingMachine;
     InitialState(VendingMachine vendingMachine) {
         this.vendingMachine = vendingMachine;
     }
     @Override
     public void printProductTray() {
         System.out.println("The contents of the vending machine are: ");
         for(Product p : vendingMachine.productTray)
             System.out.println(p.id + " : " + p.productName + " : Rs. " + p.cost);
         vendingMachine.setState(new ProductChosenState(vendingMachine));
     }
     @Override
     public void chooseProductOption() {
         throw new RuntimeException("No implementation");
     }
     @Override
     public void abort() {
         throw new RuntimeException("No implementation");
     }
     @Override
     public void initiatePayment() {
         throw new RuntimeException("No implementation");
     }
     @Override
     public void dispenseProduct() {
         throw new RuntimeException("No implementation");
     }
 }
class ProductChosenState implements State {
    VendingMachine vendingMachine;
    ProductChosenState(VendingMachine vendingMachine) {
        this.vendingMachine = vendingMachine;
    }
    @Override
    public void printProductTray() {
        throw new RuntimeException("No implementation");
    }
    @Override
    public void chooseProductOption() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the product id: ");
        int reqProduct = sc.nextInt();
        Product p = vendingMachine.productTray.get(reqProduct-1);
        if(p.inventoryCount == 0) {
            System.out.println("Sorry. Not enough inventory available.");
            vendingMachine.setState(new InitialState(vendingMachine));
        } else {
            System.out.println("Product available. Directing to payment phase..");
            vendingMachine.setState(new PaymentState(vendingMachine, p));
        }
    }
    @Override
    public void abort() {
        System.out.println("Aborting.");
        vendingMachine.setState(new InitialState(vendingMachine));
        throw new RuntimeException("No implementation");
    }

    @Override
    public void initiatePayment() {
        throw new RuntimeException("No implementation");
    }

    @Override
    public void dispenseProduct() {
        throw new RuntimeException("No implementation");
    }
}
class PaymentState implements State {
    VendingMachine vendingMachine;
    Product chosenProduct;
    PaymentState(VendingMachine vendingMachine, Product p) {
        this.vendingMachine = vendingMachine;
        this.chosenProduct = p;
    }
    @Override
    public void printProductTray() {
        throw new RuntimeException("No implementation");
    }
    @Override
    public void chooseProductOption() {
        throw new RuntimeException("No implementation");
    }
    @Override
    public void abort() {
        System.out.println("Aborting.");
        vendingMachine.setState(new InitialState(vendingMachine));
        throw new RuntimeException("No implementation");
    }
    public void initiatePayment() {
        System.out.println("Your total is: " + chosenProduct.cost);
        Scanner sc = new Scanner(System.in);
        System.out.println("Input the coins into the slot: ");
        int coins = sc.nextInt();
        if(coins != chosenProduct.cost) {
            System.out.println("Invalid payment. Refunding and aborting");
            abort();
        }
        System.out.println("Payment successful. Redirecting to dispense state");
        vendingMachine.setState(new DispenseState(vendingMachine, chosenProduct));
    }

    @Override
    public void dispenseProduct() {
        throw new RuntimeException("No implementation");
    }
}
class DispenseState implements State {
    VendingMachine vendingMachine;
    Product p;
    DispenseState(VendingMachine vendingMachine, Product p) {
        this.vendingMachine = vendingMachine;
        this.p = p;
    }
    @Override
    public void printProductTray() {
        throw new RuntimeException("No implementation");
    }

    @Override
    public void chooseProductOption() {
        throw new RuntimeException("No implementation");
    }

    @Override
    public void abort() {
        throw new RuntimeException("No implementation");
    }
    @Override
    public void initiatePayment() {
        throw new RuntimeException("No implementation");
    }

    @Override
    public void dispenseProduct() {
        System.out.println("Dispensed: " + p.productName);
        vendingMachine.setState(new InitialState(vendingMachine));
    }
}


public class Main {
    public static void main(String[] args) {
        Product cola = new Product("Cola", 1, 3, 10);
        Product limeSoda = new Product("limeSoda", 2, 2, 5);
        VendingMachine vendingMachine = new VendingMachine(Arrays.asList(cola, limeSoda));
        vendingMachine.currentState.printProductTray();
        vendingMachine.currentState.chooseProductOption();
        vendingMachine.currentState.initiatePayment();
        vendingMachine.currentState.dispenseProduct();
    }
}
