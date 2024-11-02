
package testappv2a;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Records {
    public void main(String[] args) {
        
        Records r = new Records();
        Scanner input = new Scanner(System.in);
        String resp;
        
        do{    
            System.out.println("--------------------------------");
            System.out.println("1. ADD RECORD");
            System.out.println("2. UPDATE RECORD");
            System.out.println("3. DELETE RECORD");
            System.out.println("4. VIEW RECORD");
            System.out.println("5. EXIT");

            System.out.print("Enter Action: ");
            int action = input.nextInt();

            switch(action){
                case 1:
                    r.addRecord();
                break; 
                case 2:
                    
                break;
                case 3:
                    
                    break;
                case 4:
                    r.viewRecord();
                break;
                case 5:
                
               break;
        }
         System.out.print("Do you want to continue?: ");
         resp = input.next();
         
     }while(resp.equalsIgnoreCase("yes"));
            
    }
        
   
    public void addRecord() {
        Scanner sc = new Scanner(System.in);
        config conf = new config();
        
        TestAppv2a test = new TestAppv2a(); 
        test.viewCitizen();
        
        System.out.print("Enter Citizen ID: ");
        int c_id = sc.nextInt();
        
        String csql = "Select c_id FROM citizen WHERE c_id = ?";
        while(conf.getSingleValue(csql, c_id)== 0){
            System.out.print("Citizen doesn't exist, Select Again: ");
            c_id = sc.nextInt();
        }
        
        Blotter bltt = new Blotter(); 
        bltt.viewBlotter();
        
        System.out.print("Enter Blotter ID: ");
        int b_id = sc.nextInt();
        
        String bsql = "Select b_id FROM blotter WHERE b_id = ?";
        while(conf.getSingleValue(bsql, b_id)== 0){
            System.out.print("Blotter doesn't exist, Select Again: ");
            b_id = sc.nextInt();
        }
        
        System.out.print("Number of Offense: ");
        int r_offense = sc.nextInt();
        
        System.out.print("Enter Status (done/ongoing/new): ");
        String r_status = sc.next();
       
        while(!r_status.equals("new") && !r_status.equals("done") && !r_status.equals("ongoing")){
            System.out.print("Invalid Status, Try Again: ");
            r_status = sc.next();
    }
        
        LocalDate currdate = LocalDate.now();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        String date = currdate.format(format);
        
        
        String sql = "INSERT INTO record (c_id, b_id, r_offense, r_status, r_datesettled) VALUES (?, ?, ?, ?, ?)";

        conf.addRecord(sql, c_id, b_id, r_offense, r_status, date);   
    }
    
    private void viewRecord() {
        String query = "SELECT r_id, b_reported, c_lname, b_sus, b_incident, b_location, r_status , r_datesettled "
                + "FROM record LEFT JOIN citizen ON citizen.c_id = record.c_id "
                + "LEFT JOIN blotter ON blotter.b_id = record.b_id";
        
        String[] headers = {"RECORD ID", "DATE REPORTED", "CITIZEN", "SUSPECT", " INCIDENT", "LOCATION", "STATUS", "DATE SETTLED"};
        String[] columns = {"r_id", "b_reported", "c_lname", "b_sus", "b_incident", "b_location", "r_status", "r_datesettled"};

        config conf = new config();
        conf.viewRecords(query, headers, columns);
    }
       
}
