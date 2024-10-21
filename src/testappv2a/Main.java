package testappv2a;

import java.util.Scanner;

public class Main {
    
      public static void main(String[] args) {
       Scanner scanner = new Scanner(System.in);
       String resp;
       
       do{
           
        System.out.println("---------------------- Choose an Entity ----------------------");
        System.out.println("1. Citizen");
        System.out.println("2. Blotter");
        System.out.println("3. Records");
        System.out.println("4. Exit");
        
        System.out.print("Enter Choosen Entity: ");
        int choice = scanner.nextInt();
        
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
                System.out.println("Thank for using my system");
                System.exit(0);
                break;
            default:
                System.out.println("Invalid choice");
                break;
        }
        System.out.print("Do you want to continue?: ");
         resp = scanner.next();

       }while(resp.equalsIgnoreCase("yes"));
        System.out.println("Thank you!");
    }
        
 }


