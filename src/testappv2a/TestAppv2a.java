
package testappv2a;

import java.util.Scanner;


public class TestAppv2a {

        public void addCitizen(){
            
        Scanner sc = new Scanner(System.in);
        config conf = new config();
        
        System.out.print("First Name: ");
        String c_fname = sc.next();
        System.out.print("Last Name: ");
        String c_lname = sc.next();
        System.out.print("Age: ");
        String c_age = sc.next();
        System.out.print("Address: ");
        String c_address = sc.next();
        System.out.print("Civil Status: ");
        String c_civilstatus = sc.next();
     
        String sql = "INSERT INTO citizen(c_fname, c_lname, c_age, c_address, c_civilstatus) VALUES (?, ?, ?, ?, ?)";

        conf.addRecord(sql, c_fname, c_lname, c_age, c_address, c_civilstatus);
     }
        
       public void viewCitizen() {
          
        String  db = "SELECT * FROM citizen";
        String[]  cap = {"CITIZEN ID", "FIRST NAME", "LAST NAME", "AGE", "ADDRESS", "CIVIL STATUS"};
        String[] small = {"c_id", "c_fname", "c_lname", "c_age", "c_address", "c_civilstatus"};

        config conf = new config();
        conf.viewRecords(db, cap, small);
    }
    
    private void updateCitizen(){
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter Citizen ID to update: ");
        int id = sc.nextInt();
        
        System.out.print("Enter new age: ");
        int ge = sc.nextInt();
        System.out.print("Enter new address: ");
        String adrs = sc.next();
        System.out.print("Enter new civil status: ");
        String cvs = sc.next();
        
        String qry = "UPDATE citizen SET c_age = ? , c_address = ? , c_civilstatus = ? Where c_id = ?";
        
        config conf = new config();
        conf.updateRecord(qry, ge, adrs,cvs, id);      
    }

   private void deleteCitizen(){
            Scanner sc = new Scanner(System.in);
         
        System.out.print("Enter Citizen ID to delete: ");
        int id = sc.nextInt();
        
        String qry = "Delete FROM citizen WHERE c_id = ?";
        
        config conf = new config();
        conf.deleteRecord(qry, id);    
   }
    public void main(String[] args) {
       
        TestAppv2a test= new TestAppv2a();
        Scanner sc = new Scanner(System.in);
       
        String resp;
     do{   
         
        System.out.println("1. ADD");
        System.out.println("2. UPDATE");
        System.out.println("3. DELETE");
        System.out.println("4. VIEW");
        System.out.println("5. EXIT");

        System.out.print("Enter Actions: ");
        int action = sc.nextInt();
        
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
        }
         System.out.print("Do you want to continue?: ");
         resp = sc.next();
         
     }while (resp.equalsIgnoreCase("yes"));
        System.out.println("Thank you!");
    }
    
}
