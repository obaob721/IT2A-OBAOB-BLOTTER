package testappv2a;

import java.util.Scanner;

public class Main {
    
      public static void main(String[] args) {
       Scanner scanner = new Scanner(System.in);
       String response;
       
       do{
        System.out.println("\n                   BLOTTER RECORDING SYSTEM                  ");
        System.out.println("---------------------- Choose an Entity ----------------------");
        System.out.println("                        1. Citizen");
        System.out.println("                        2. Blotter");
        System.out.println("                        3. Records");
        System.out.println("                        4. Exit");
        System.out.println("---------------------------------------------------------------");
        int choice = 0;

            do {
                System.out.print("Enter Choosen Entity: ");
                if (scanner.hasNextInt()) {
                    choice = scanner.nextInt();
                } else {
                    System.out.println("Invalid Input, Please enter a valid integer.");
                    scanner.next();
                    continue;
                }
                if (!(choice >= 1 && choice <= 4)) {
                    System.out.println("Invalid Entity, Please input integer (1-4) only.");
                }
                
            } while (!(choice >= 1 && choice <= 4));
    
        
        switch (choice) {
            case 1:
                TestAppv2a test = new TestAppv2a(); 
                test.main(args);  
                break;
            case 2:
                Blotter bltt = new Blotter(); 
                bltt.main(args);  
               break;
            case 3:
                Records rcrds = new Records();
                rcrds.main(args);
                break;
            case 4:
                System.out.println("Thank you for using my system!");
                    System.exit(0);
                    break;
            default:
                System.out.println("Invalid choice.");
                break;
        }   
 
        System.out.print("Do you want to continue? (yes/no): ");
            response = scanner.next();
            
            while (!response.equalsIgnoreCase("yes") && !response.equalsIgnoreCase("no")) {
                System.out.print("Invalid Input, Enter Again: ");
                response = scanner.next();
            }
            
        } while (response.equalsIgnoreCase("yes"));
        
        System.out.println("Thank you for using my system!");
    }
        
 }
