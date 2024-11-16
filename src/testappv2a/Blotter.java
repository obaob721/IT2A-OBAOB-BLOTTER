package testappv2a;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Blotter {
    
    public void main(String[] args) {
       
        Blotter test= new Blotter();
        Scanner sc = new Scanner(System.in);
        String resp;
       
     do{   
        System.out.println("--------------------------------");
        System.out.println("1. ADD BLOTTER");
        System.out.println("2. UPDATE BLOTTER");
        System.out.println("3. DELETE BLOTTER");
        System.out.println("4. VIEW BLOTTER");
        System.out.println("5. EXIT");

        int action = 1;
        
        while (true) {
                System.out.print("Enter Action: ");
                
                if (sc.hasNextInt()) {
                    action = sc.nextInt();
                    
                    if (action >= 1 && action <= 5) {
                        break;
                    } else {
                        System.out.println("Invalid Action. Enter number (1-5) only.");
                    }
                } else {
                    System.out.println("Invalid Input. Must be an Integer.");
                    sc.next();
                }
            }
        
        switch(action){
            case 1:
                test.addBlotter();
                break;
            case 2:
                test.viewBlotter();
                test.updateBlotter();
                test.viewBlotter();
                break;
            case 3:
                test.viewBlotter();
                test.deleteBlotter();
                test.viewBlotter();
               break;
            case 4:
                test.viewBlotter();
                break;
           case 5:
                return;

        }
        
         System.out.print("Do you want to continue? (yes/no): ");
            resp = sc.next();
            
            while (!resp.equalsIgnoreCase("yes") && !resp.equalsIgnoreCase("no")) {
                System.out.print("Invalid Input, Enter Again: ");
                resp = sc.next();
            }
            
        }while(resp.equalsIgnoreCase("yes"));
        
        System.out.println("Thank you for using my system!");
        System.exit(0);     
    }
    
     public void addBlotter(){
            
        Scanner sc = new Scanner(System.in);
        config conf = new config();
        
        String b_fname;
        while (true) {
            System.out.print("Complainant's FullName: ");
            b_fname = sc.nextLine();
            if (b_fname.matches("[a-zA-Z\\s]+")){
                break;
            }
            System.out.println("Invalid input. Please enter a valid name (letters only).");
        }        
        
        String b_incident;
        while (true) {
            System.out.print("Incident Type: ");
            b_incident = sc.nextLine();
            if (b_incident.matches("[a-zA-Z\\s]+")){
                break;
            }
            System.out.println("Invalid input. Please enter a valid incident type (letters only).");
        }  
        
        LocalDate currdate = LocalDate.now();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        String date = currdate.format(format);
        
        String b_location;
        while (true) {
            System.out.print("Location: ");
            b_location = sc.nextLine();
            if (b_location.matches("[a-zA-Z\\s]+")){
                break;
            }
            System.out.println("Invalid input. Please enter a valid location (letters only).");
        }  
        
        String b_status = "ongoing"; 
     
        String sql = "INSERT INTO blotter(b_fname, b_incident, b_reported, b_location, b_status) VALUES (?, ?, ?, ?, ?)";

        conf.addRecord(sql, b_fname, b_incident, date, b_location, b_status);
     }
        
       public void viewBlotter() {
          
        String  db = "SELECT * FROM blotter ";
        
        String[]  cap = {"BLOTTER ID", "COMPLAINANT'S NAME", "INCIDENT TYPE", "DATE REPORTED", "LOCATION","STATUS"};
        String[] small = {"b_id", "b_fname", "b_incident", "b_reported", "b_location", "b_status"};

        config conf = new config();
        conf.viewRecords(db, cap, small);
    }
    
    private void updateBlotter(){
        Scanner sc = new Scanner(System.in);
        config conf = new config();
        
        System.out.print("Enter Blotter ID to update: ");
        int b_id;
        
        while (true) {

            if (sc.hasNextInt()) {
                b_id = sc.nextInt();

                if (conf.getSingleValue("SELECT b_id FROM blotter WHERE b_id = ?", b_id) != 0) {
                    break;
                } else {
                    System.out.print("ID doesn't exist, Select Again: ");
                }
            } else {

                System.out.print("Invalid Input, Enter Again: ");
                sc.next();
            }
        }
        
        sc.nextLine();
        
        String b_fname;
        while (true) {
            System.out.print("New Complainant's FullName: ");
            b_fname = sc.nextLine();
            if (b_fname.matches("[a-zA-Z\\s]+")){
                break;
            }
            System.out.println("Invalid input. Please enter a valid name (letters only).");
        }        
        
        String b_incident;
        while (true) {
            System.out.print("New Incident Type: ");
            b_incident = sc.nextLine();
            if (b_incident.matches("[a-zA-Z\\s]+")){
                break;
            }
            System.out.println("Invalid input. Please enter a valid incident type (letters only).");
        }  
        
        LocalDate currdate = LocalDate.now();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        String date = currdate.format(format);
        
        String b_location;
        while (true) {
            System.out.print("New Location: ");
            b_location = sc.nextLine();
            if (b_location.matches("[a-zA-Z\\s]+")){
                break;
            }
            System.out.println("Invalid input. Please enter a valid location (letters only).");
        } 
        
        String qry = "UPDATE blotter SET b_fname = ?, b_incident = ? , b_reported = ?, b_location = ?, b_status = 'ongoing' Where b_id = ?";
     
        conf.updateRecord(qry, b_fname,b_incident,date,b_location, b_id);      
    }

   private void deleteBlotter(){
        Scanner sc = new Scanner(System.in);
        config conf = new config();
         
        System.out.print("Enter Blotter ID to delete: ");
        int b_id;
        
        while (true) {

            if (sc.hasNextInt()) {
                b_id = sc.nextInt();

                if (conf.getSingleValue("SELECT b_id FROM blotter WHERE b_id = ?", b_id) != 0) {
                    break;
                } else {
                    System.out.print("ID doesn't exist, Select Again: ");
                }
            } else {

                System.out.print("Invalid Input, Enter Again: ");
                sc.next();
            }
        }
        
        String qry = "Delete FROM blotter WHERE b_id = ?";
        
        conf.deleteRecord(qry, b_id);    
   }
  
}
