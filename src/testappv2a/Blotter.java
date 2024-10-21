
package testappv2a;

import java.util.Scanner;

public class Blotter {
     public void addBlotter(){
            
        Scanner sc = new Scanner(System.in);
        config conf = new config();
        
        sc.nextLine();

        System.out.print("Complainant's FullName: ");
        String b_fname = sc.nextLine();
        System.out.print("Suspect Name: ");
        String b_sus = sc.nextLine();
        System.out.print("Incident Type: ");
        String b_incident = sc.next();
        System.out.print("Date & Time Reported: ");
        String b_reported = sc.next();
        System.out.print("Location: ");
        String b_location = sc.next();
     
        String sql = "INSERT INTO blotter(b_fname, b_sus, b_incident, b_reported, b_location) VALUES (?, ?, ?, ?, ?)";

        conf.addRecord(sql, b_fname, b_sus, b_incident, b_reported, b_location);
     }
        
       public void viewBlotter() {
          
        String  db = "SELECT * FROM blotter";
        String[]  cap = {"BLOTTER ID", "COMPLAINANT'S NAME", "SUSPECT NAME", "INCIDENT TYPE", "DATE & TIME REPORTED", "LOCATION"};
        String[] small = {"b_id", "b_fname", "b_sus", "b_incident", "b_reported", "b_location"};

        config conf = new config();
        conf.viewRecords(db, cap, small);
    }
    
    private void updateBlotter(){
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter Blotter ID to update: ");
        int id = sc.nextInt();
        
        sc.nextLine();
        
        System.out.print("Enter new Complainant's FullName: ");
        String b_fname = sc.nextLine();
        System.out.print("Enter new Suspect Name: ");
        String b_sus = sc.nextLine();
        System.out.print("Enter new Incident Type: ");
        String b_incident = sc.next();
        System.out.print("Enter new Date & Time Reported: ");
        String b_reported = sc.next();
        sc.nextLine();
        System.out.print("Enter new Location: ");
        String b_location = sc.next();
        
        String qry = "UPDATE blotter SET b_fname = ? , b_sus = ? , b_incident = ? , b_reported = ?, b_location = ? Where b_id = ?";
        
        config conf = new config();
        conf.updateRecord(qry, b_fname, b_sus,b_incident,b_reported,b_location, id);      
    }

   private void deleteBlotter(){
            Scanner sc = new Scanner(System.in);
         
        System.out.print("Enter Blotter ID to delete: ");
        int id = sc.nextInt();
        
        String qry = "Delete FROM blotter WHERE b_id = ?";
        
        config conf = new config();
        conf.deleteRecord(qry, id);    
   }
    public void main(String[] args) {
       
        Blotter test= new Blotter();
        Scanner sc = new Scanner(System.in);
       
       
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
         
     }while (true);
        
    }
    
}
