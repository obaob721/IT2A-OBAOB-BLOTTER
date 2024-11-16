package testappv2a;

import java.util.Scanner;

public class Main {
    
      public static void main(String[] args) {
       Scanner scanner = new Scanner(System.in);
       boolean exit = true;
       
       do{
           
        System.out.println("---------------------- Choose an Entity ----------------------");
        System.out.println("1. Citizen");
        System.out.println("2. Blotter");
        System.out.println("3. Records");
        System.out.println("4. Exit");
        
        System.out.print("Enter Choosen Entity: ");
        int choice = scanner.nextInt();
        while (choice <= 0) {
        System.out.print("Must be positive between 1-4. Enter again: ");
        choice = scanner.nextInt();
    }
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
                System.out.print("Exit Selected... type 'yes' to continue: ");
                String resp = scanner.next();
                if(resp.equalsIgnoreCase("yes")){
                    exit = false;
                }
                break;
            default:
                System.out.println("Invalid choice.");
                break;
        }   
 
       }while(exit);
    }
        
 }


