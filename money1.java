
import java.util.Scanner;

class Expense {
    String item;
    double amount;


    Expense(String item, double amount) {
        this.item = item;
        this.amount = amount;
    }

    void displayExpense() {
        System.out.println(item + " : Rs." + amount);
    }
}

class BudgetExpense extends Expense {

    BudgetExpense(String item, double amount) {
        super(item, amount);
    }

    // Method overriding
    @Override
    void displayExpense() {
        System.out.println("Expense: " + item + " - Rs." + amount);
    }
}

public class money1 {

    static Scanner sc = new Scanner(System.in);

    // Method to read a valid amount
    static double readAmount(String message) {
        double amount;

        while (true) {
            System.out.print(message);

            try {
                amount = Double.parseDouble(sc.nextLine());

                if (amount >= 0) {
                    return amount;
                } else {
                    System.out.println("Amount cannot be negative.");
                }

            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
    }

    // Method to calculate total expenses
    static double calculateTotal(Expense[] expenses, int count) {
        double total = 0;

        for (int i = 0; i < count; i++) {
            total = total + expenses[i].amount;
        }

        return total;
    }

    // Method overloading
    static double calculateTotal(Expense[] expenses) {
        int count = 0;

        while (count < expenses.length && expenses[count] != null) {
            count++;
        }

        return calculateTotal(expenses, count);
    }

    // Method to display the report
    static void displayReport(double budget, double total) {
        double balance = budget - total;

        System.out.println("\n Monthly Report");
        System.out.println("Budget: Rs." + budget);
        System.out.println("Total expenses: Rs." + total);

        if (balance > 0) {
            System.out.println("Money left: Rs." + balance);
            System.out.println("You are within your budget.");

        } else if (balance == 0) {
            System.out.println("You used all your budget.");
            System.out.println("You reached your budget limit exactly.");

        } else {
            System.out.println("Your budget limit has been crossed!");
            System.out.println("You spent extra: Rs." + (-balance));
        }
    }

    public static void main(String[] args) {

        double budget = readAmount("Enter your monthly budget: ");

        Expense[] expenses = new Expense[20];
        int count = 0;

        while (count < expenses.length) {

            System.out.print("Enter what you spent on: ");
            String item = sc.nextLine();

            double amount = readAmount("Enter how much you spent: ");

            expenses[count] = new BudgetExpense(item, amount);
            count++;

            if (count < expenses.length) {
                System.out.print(
                    "Do you want to enter another expense? (yes/no): "
                );

                String answer = sc.nextLine();

                if (answer.equalsIgnoreCase("no")) {
                    break;
                }
            }
        }

        System.out.println("\nExpense Details ");

        for (int i = 0; i < count; i++) {
            expenses[i].displayExpense();
        }

        double total = calculateTotal(expenses);
        displayReport(budget, total);

        sc.close();
    }
}
