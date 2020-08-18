package machine;

import java.util.Scanner;

public class CoffeeMachine {
    static long money = 550, water = 400, milk = 540, beans = 120, cups = 9;
    static int recent;
    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        while(true) {
            System.out.println("Write action (buy, fill, take, remaining, exit):");
            String action = sc.next().toLowerCase();
            switch(action) {
                case "buy":
                    buy();
                    break;
                case "fill":
                    fill();
                    break;
                case "take":
                    take();
                    break;
                case "remaining":
                    displayResourcesStatus();
                    break;
                case "exit":
                    System.exit(0);
                    break;

            }
        }
    }
    
    private static void displayResourcesStatus() {
        System.out.print("The coffee machine has:\n");
        System.out.printf("%d of water \n", water);
        System.out.printf("%d of milk \n", milk);
        System.out.printf("%d of coffee beans \n", beans);
        System.out.printf("%d of disposable cups \n", cups);
        System.out.printf("$%d of money \n", money);
    }
    
    private static void fill() {
        System.out.print("Write how many ml of water do you want to add:\n");
        water += sc.nextInt();
        System.out.print("Write how many ml of milk do you want to add:\n");
        milk += sc.nextInt();
        System.out.print("Write how many grams of coffee beans do you want to add:\n");
        beans += sc.nextInt();
        System.out.print("Write how many disposable cups of coffee do you want to add:\n");
        cups += sc.nextInt();
        sc.nextLine();
    }
    
    private static void buy() {
        System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino:");
        switch(sc.next()) {
            case "back":
                break;
            case "1":
                if (enoughCoffee(250,0,16) == -1) {
                    break;
                }
                recent = 1;
                money += 4;
                cups -= 1;
                break;
            case "2":
                if (enoughCoffee(350,75,20) == -1) {
                    break;
                }
                recent = 2;
                money += 7;
                cups -= 1;
                break;
            case "3":
                if (enoughCoffee(200,100,12) == -1) {
                    break;
                }
                recent = 3;
                money += 6;
                cups -= 1;
                break;                
        }
    }
    
    private static void take() {
        System.out.printf("I gave you $%d\n",money);
        money = 0;
    }
    
    private static int enoughCoffee(int w, int m, int b) { 
        if (water < w) {
            System.out.print("\nSorry, not enough water!\n");
            return -1;
        } else if (milk < m) {
            System.out.print("\nSorry, not enough milk!\n");
            return -1;
        } else if (beans < b) {
            System.out.print("\nSorry, not enough beans!\n");
            return -1;
        } else {
            System.out.print("\nI have enough resources, making you a coffee!\n");
            water -= w;
            milk -= m;
            beans -= b;
        }
        return 1; 
    }
}