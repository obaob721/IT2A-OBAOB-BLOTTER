package testappv2a;

import java.util.Scanner;

public class TestAppv2a {
    
     public void main(String[] args) {
       
        TestAppv2a test= new TestAppv2a();
        Scanner sc = new Scanner(System.in);
        String resp;
       
     do{   
        System.out.println("--------------------------------");
        System.out.println("1. ADD CITIZEN");
        System.out.println("2. UPDATE CITIZEN");
        System.out.println("3. DELETE CITIZEN");
        System.out.println("4. VIEW CITIZEN");
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
                test.addCitizen();
                break;
            case 2:
                test.viewCitizen();
                test.updateCitizen();
                test.viewCitizen();
                break;
            case 3:
                test.viewCitizen();
                test.deleteCitizen();
                test.viewCitizen();
               break;
            case 4:
                test.viewCitizen();
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

       public void addCitizen() {
        Scanner sc = new Scanner(System.in);
        config conf = new config();
        
        String c_fname;
        while (true) {
            System.out.print("First Name: ");
            c_fname = sc.nextLine();
            if (c_fname.matches("[a-zA-Z\\s]+")){
                break;
            }
            System.out.println("Invalid input. Please enter a valid first name (letters only).");
        }
        
        String c_lname;
        while (true) {
            System.out.print("Last Name: ");
            c_lname = sc.nextLine();
            if (c_lname.matches("[a-zA-Z\\s]+")) {
                break;
            }
            System.out.println("Invalid input. Please enter a valid last name (letters only).");
        }
        
        int c_age;
        while (true) {
            System.out.print("Age: ");
            if (sc.hasNextInt()) {
                c_age = sc.nextInt();
                sc.nextLine();
                if (c_age >= 1) {
                    break;
                } else {
                    System.out.println("Invalid input. Age must be a positive integer.");
                }
            } else {
                sc.nextLine();
                System.out.println("Invalid input. Please enter a valid integer.");
            }
        }
        
        String c_address;
        while (true) {
            System.out.print("Address: ");
            c_address = sc.nextLine();
            if (c_address.matches("[a-zA-Z\\s]+")) {
                break;
            }
            System.out.println("Invalid input. Please enter a valid address (letters only).");
        }
        
        String c_pnumber;
        while (true) {
            System.out.print("Phone Number: ");
            c_pnumber = sc.nextLine();
            if (c_pnumber.matches("\\d{11}")) {
                break;
            }
            System.out.println("Invalid input. Please enter a valid 11-digit phone number.");
        }
        
        String sql = "INSERT INTO citizen(c_fname, c_lname, c_age, c_address, c_pnumber) VALUES (?, ?, ?, ?, ?)";
        conf.addRecord(sql, c_fname, c_lname, c_age, c_address, c_pnumber);
        
    }    
       public void viewCitizen() {
          
        String  db = "SELECT * FROM citizen";
        String[]  cap = {"CITIZEN ID", "FIRST NAME", "LAST NAME", "AGE", "ADDRESS", "PHONE NUMBER"};
        String[] small = {"c_id", "c_fname", "c_lname", "c_age", "c_address", "c_pnumber"};

        config conf = new config();
        conf.viewRecords(db, cap, small);
    }
    
    private void updateCitizen(){
        Scanner sc = new Scanner(System.in);
        config conf = new config();
        
        System.out.print("Enter Citizen ID to update: ");
        int c_id;
        
        while (true) {

            if (sc.hasNextInt()) {
                c_id = sc.nextInt();

                if (conf.getSingleValue("SELECT c_id FROM citizen WHERE c_id = ?", c_id) != 0) {
                    break;
                } else {
                    System.out.print("ID doesn't exist, Select Again: ");
                }
            } else {

                System.out.print("Invalid Input, Enter Again: ");
                sc.next();
            }
        }
        
        int ge;
        while (true) {
            System.out.print("New Age: ");
            if (sc.hasNextInt()) {
                ge = sc.nextInt();
                sc.nextLine();
                if (ge >= 1) {
                    break;
                } else {
                    System.out.println("Invalid input. Age must be a positive integer.");
                }
            } else {
                sc.nextLine();
                System.out.println("Invalid input. Please enter a valid integer.");
            }
        }
        
        String adrs;
        while (true) {
            System.out.print("New Address: ");
            adrs = sc.nextLine();
            if (adrs.matches("[a-zA-Z\\s]+")) {
                break;
            }
            System.out.println("Invalid input. Please enter a valid address (letters only).");
        }
        
        String pnum;
        while (true) {
            System.out.print("New Phone Number: ");
            pnum = sc.nextLine();
            if (pnum.matches("\\d{11}")) {
                break;
            }
            System.out.println("Invalid input. Please enter a valid 11-digit phone number.");
        }        
        
        
        String qry = "UPDATE citizen SET c_age = ? , c_address = ? , c_pnumber = ? Where c_id = ?";
     
        conf.updateRecord(qry, ge, adrs,pnum, c_id);      
    }

   private void deleteCitizen(){
        Scanner sc = new Scanner(System.in);
        config conf = new config();
        
        System.out.print("Enter Citizen ID to delete: ");
        int c_id;
        
        while (true) {

            if (sc.hasNextInt()) {
                c_id = sc.nextInt();

                if (conf.getSingleValue("SELECT c_id FROM citizen WHERE c_id = ?", c_id) != 0) {
                    break;
                } else {
                    System.out.print("ID doesn't exist, Select Again: ");
                }
            } else {

                System.out.print("Invalid Input, Enter Again: ");
                sc.next();
            }
        }
        
        String qry = "Delete FROM citizen WHERE c_id = ?";
       
        conf.deleteRecord(qry, c_id);    
   }
   
}