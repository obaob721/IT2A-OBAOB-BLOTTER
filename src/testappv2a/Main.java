package testappv2a;

import java.util.Scanner;

public class Main {
    
      public static void main(String[] args) {
       
        Scanner scanner = new Scanner(System.in);
        System.out.println("MAIN CLASS CHOICES");
        System.out.println("1. Citizen");
        System.out.println("2. Blotter");
        System.out.println("3. Records");
        
        System.out.print("Choose a Main Class:");
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
                break;
            default:
                System.out.println("Invalid choice");
                break;
        }

    }
        
    }


