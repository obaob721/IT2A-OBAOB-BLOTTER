
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

        System.out.print("Enter Action: ");
        int action = sc.nextInt();
        
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
                
               break;
        }
         System.out.print("Do you want to continue?: ");
         resp = sc.next();
         
     }while(resp.equalsIgnoreCase("yes"));
     
    }
     public void addBlotter(){
            
        Scanner sc = new Scanner(System.in);
        config conf = new config();
        

        System.out.print("Complainant's FullName: ");
        String b_fname = sc.nextLine();
        
        System.out.print("Incident Type: ");
        String b_incident = sc.next();
        
        LocalDate currdate = LocalDate.now();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        String date = currdate.format(format);
        
        System.out.print("Location: ");
        String b_location = sc.next();
        
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
        int id = sc.nextInt();
        
        while(conf.getSingleValue("Select b_id FROM blotter WHERE b_id = ?",id)==0){
            System.out.println("ID doesn't exist!");
            System.out.print("Select Blotter ID Again: ");
            id = sc.nextInt();
        }
        sc.nextLine();
        
        System.out.print("Enter new Complainant's FullName: ");
        String b_fname = sc.nextLine();
  
        System.out.print("Enter new Incident Type: ");
        String b_incident = sc.next();
        
        LocalDate currdate = LocalDate.now();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        String date = currdate.format(format);
     
        System.out.print("Enter new Location: ");
        String b_location = sc.next();
        
        String qry = "UPDATE blotter SET b_fname = ?, b_incident = ? , b_reported = ?, b_location = ?, b_status = 'ongoing' Where b_id = ?";
     
        conf.updateRecord(qry, b_fname,b_incident,date,b_location, id);      
    }

   private void deleteBlotter(){
        Scanner sc = new Scanner(System.in);
        config conf = new config();
         
        System.out.print("Enter Blotter ID to delete: ");
        int id = sc.nextInt();
        
         while(conf.getSingleValue("Select b_id FROM blotter WHERE b_id = ?",id)==0){
            System.out.println("ID doesn't exist!");
            System.out.print("Select Blotter ID Again: ");
            id = sc.nextInt();
        }
        
        String qry = "Delete FROM blotter WHERE b_id = ?";
        
        conf.deleteRecord(qry, id);    
   }
  
}
