package ATM;

import java.util.*;
enum Currency {
    THOUSAND,
    HUNDRED
}

class ATM {
    State currentState;
    ATM() {
        currentState = new InitialState(this);
    }
    void setCurrentState(State newState) {
        this.currentState = newState;
    }
}

class User {
    int userId;
    int balance;
    User(int userId, int balance) {
        this.balance = balance;
        this.userId = userId;
    }
}

abstract class State {
    void acceptCard(User user) {
        throw new RuntimeException("Unimplemented operation.");
    }
    void inputPin() {
        throw new RuntimeException("Unimplemented operation.");
    }
    void performOperation() {
        throw new RuntimeException("Unimplemented operation.");
    }
    void withdrawCash() {
        throw new RuntimeException("Unimplemented operation.");
    }
    void showBalance() {
        throw new RuntimeException("Unimplemented operation.");
    }
    void abort() {
        System.out.println("Back to the initial state.");

    }
}

class InitialState extends State {
    ATM atm;
    InitialState(ATM atm) {
        this.atm = atm;
    }
    @Override
    void acceptCard(User user) {
        System.out.println("Hi: " + user.userId + " Enter your card..");
        System.out.println("Directing you to the validation phase..");
        atm.setCurrentState(new ValidateAndChooseOperationState(atm, user));
        atm.currentState.inputPin();
    }

}

class ValidateAndChooseOperationState extends State {
    ATM atm;
    User user;
    ValidateAndChooseOperationState(ATM atm, User user) {
        this.atm = atm;
        this.user = user;
    }

    @Override
    void abort() {
        System.out.println("Back to the initial state");
        atm.setCurrentState(new InitialState(atm));
    }

    @Override
    void inputPin() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter your pincode: ");
        int pin = sc.nextInt();
        if( pin != 100 ) {
            System.out.println("Incorrect pin. Redirecting to the initial state");
            abort();
            return;
        }
        System.out.println("1. Withdraw");
        System.out.println("2. Show balance");
        System.out.println("Enter your choice: ");
        int choice = sc.nextInt();
        switch(choice) {
            case 1: atm.setCurrentState(new WithdrawCashState(atm, user));
                    atm.currentState.withdrawCash();
                    break;
            case 2: atm.setCurrentState(new DisplayBalance(atm, user));
                    atm.currentState.withdrawCash();
                    break;
        }
    }
}

abstract class ChainOfResponsibility {
    ChainOfResponsibility nextHandler;
    ChainOfResponsibility(ChainOfResponsibility nextHandler) {
        this.nextHandler = nextHandler;
    }
    void dispenseCash(int amount) {
        nextHandler.dispenseCash(amount);
    }
}

class ThousandCurrencyHandler extends ChainOfResponsibility{
    ThousandCurrencyHandler(ChainOfResponsibility nextHandler) {
        super(nextHandler);
    }
    void dispenseCash(int amount) {
        while(amount >= 1000) {
            System.out.println("Dispensed a 1000 note.");
            amount -= 1000;
        }
        if (amount != 0) {
            super.dispenseCash(amount);
        }
    }
}
class HundredCurrencyHandler extends ChainOfResponsibility{
    HundredCurrencyHandler(ChainOfResponsibility nextHandler) {
        super(nextHandler);
    }
    void dispenseCash(int amount) {
        while(amount >= 100) {
            System.out.println("Dispensed a 100 note.");
            amount -= 100;
        }
    }
}

// implementation of chain of responsibility principle.
class WithdrawCashState extends State {
    ATM atm;
    User user;
    WithdrawCashState(ATM atm, User user) {
        this.atm = atm;
        this.user = user;
    }
    @Override
    void withdrawCash() {
        int requiredAmt = 0;
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter required cash: ");
        requiredAmt = sc.nextInt();
        if(requiredAmt > user.balance) {
            System.out.println("Insufficient funds. Redirecting to initial state");
            atm.setCurrentState(new InitialState(atm));
            return;
        }
        ChainOfResponsibility chainOfResponsibility = new ThousandCurrencyHandler(new HundredCurrencyHandler(null));
        chainOfResponsibility.dispenseCash(requiredAmt);
        System.out.println("Operation done. Redirecting to the initial state.");
        atm.setCurrentState(new InitialState(atm));
    }
}

class DisplayBalance extends State {
    ATM atm;
    User user;
    DisplayBalance(ATM atm, User user) {
        this.atm = atm;
        this.user = user;
    }
    @Override
    void showBalance() {
        System.out.println("Your balance is: " + user.balance);
        System.out.println("Operation done. Redirecting to initial state.");
        atm.setCurrentState(new InitialState(atm));
    }
}


public class Main {
    public static void main(String[] args) {
        User user = new User(1, 2500);
        ATM atm = new ATM();
        atm.currentState.acceptCard(user);
    }
}
