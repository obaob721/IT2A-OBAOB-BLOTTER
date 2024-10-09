
package testappv2a;

import java.util.Scanner;


public class TestAppv2a {

        public void addCitizen(){
            
        Scanner sc = new Scanner(System.in);
        config conf = new config();
        
        System.out.print("First Name: ");
        String f_name = sc.next();
        System.out.print("Last Name: ");
        String l_name = sc.next();
        System.out.print("Age: ");
        String age = sc.next();
        System.out.print("Address: ");
        String b_address = sc.next();

        String sql = "INSERT INTO citizen(f_name, l_name, age, b_address) VALUES (?, ?, ?, ?)";


        conf.addRecord(sql, f_name, l_name, age, b_address);
     }
        
       public void viewCitizen() {
          
        String  db = "SELECT * FROM citizen";
        String[]  cap = {"Entry No.", "First Name", "Last Name", "Age", "Address"};
        String[] small = {"Entry_Number", "f_name", "l_name", "age", "b_address"};

        config conf = new config();
        conf.viewRecords(db, cap, small);
    }
    
    private void updateCitizen(){
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter entry num to update: ");
        int id = sc.nextInt();
        
        System.out.print("Enter new age: ");
        int ge = sc.nextInt();
        System.out.print("Enter new address: ");
        String adrs = sc.next();
        
        String qry = "UPDATE citizen SET age = ? , b_address = ? Where Entry_Number = ?";
        
        config conf = new config();
        conf.updateRecord(qry, ge, adrs, id);      
    }

   private void deleteCitizen(){
            Scanner sc = new Scanner(System.in);
         
        System.out.print("Enter Entry Num to delete: ");
        int id = sc.nextInt();
        
        String qry = "Delete FROM citizen WHERE Entry_Number = ?";
        
        config conf = new config();
        conf.deleteRecord(qry, id);    
   }
    public static void main(String[] args) {
       
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
