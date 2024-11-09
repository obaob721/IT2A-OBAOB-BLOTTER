
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
            System.out.println("4. VIEW ALL RECORD");
            System.out.println("5. RECORD BY ID");

            System.out.print("Enter Action: ");
            int action = input.nextInt();

            switch(action){
                case 1:
                    r.addRecord();
                break; 
                case 2:
                    r.viewAllRecord();
                    r.updateRecord();
                break;
                case 3:
                    r.viewAllRecord();
                    r.deleteRecord();
                    break;
                case 4:
                    r.viewAllRecord();
                break;
                case 5:
                    r.RecordByID();
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
        String currentIncident = conf.getSingleString("SELECT b_incident FROM blotter WHERE b_id = ?", b_id);
        System.out.print("Add new incident: ");
        String newIncident = sc.next();

        String updatedIncident = currentIncident + ", " + newIncident;
        
        String updateIncidentSql = "UPDATE blotter SET b_incident = ? WHERE b_id = ?";
        conf.updateRecord(updateIncidentSql, updatedIncident, b_id);
        System.out.print("Number of Offense: ");
        int r_offense = sc.nextInt();
        
        String r_status = "cleared";
        
        LocalDate currdate = LocalDate.now();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        String date = currdate.format(format);
        
        
        String sql = "INSERT INTO record (c_id, b_id, r_offense, r_status, r_datesettled) VALUES (?, ?, ?, ?, ?)";

        conf.addRecord(sql, c_id, b_id, r_offense, r_status, date);   
    }
    
    private void viewAllRecord() {
        String query = "SELECT record.r_id, record.r_datesettled, record.r_status, blotter.b_incident,blotter.b_reported,citizen.c_lname "
             + "FROM record "
             + "LEFT JOIN blotter ON blotter.b_id = record.b_id "
             + "LEFT JOIN citizen ON citizen.c_id = record.c_id";

String[] headers = {"RECORD ID", "DATE REPORTED" ,"CITIZEN'S LAST NAME", "INCIDENT TYPE" ,"DATE SETTLED", "STATUS"};
String[] columns = {"r_id", "b_reported","c_lname","b_incident", "r_datesettled", "r_status"};


        config conf = new config();
        conf.viewRecords(query, headers, columns);
    }
       
   private void updateRecord(){
    
        Scanner sc = new Scanner(System.in);
        config conf = new config();

        System.out.print("Enter Record ID to update: ");
        int id = sc.nextInt();
        while(conf.getSingleValue("Select r_id FROM record WHERE r_id = ?",id)==0){
            System.out.println("ID doesn't exist!");
            System.out.print("Select ID Again: ");
            id = sc.nextInt();
        }
        
        sc.nextLine(); 
        System.out.print("Enter new Num of Offense: ");
        String r_offense = sc.nextLine();
        
        String r_status = "cleared";
        
        String query = "UPDATE record SET r_offense = ?, r_status = ? WHERE r_id = ?";
        
        conf.updateRecord(query, r_offense,r_status,id);
        
    }
private void deleteRecord(){
    
        Scanner sc = new Scanner (System.in);
        config conf = new config();

        System.out.print("Enter Record ID to delete: ");
        int id = sc.nextInt();
        while(conf.getSingleValue("Select r_id FROM record WHERE r_id = ?",id)==0){
            System.out.println("ID doesn't exist!");
            System.out.print("Select ID Again: ");
            id = sc.nextInt();
        }
        
        String query = "Delete FROM record WHERE r_id = ?";
       
        conf.deleteRecord(query, id);
    }
private void RecordByID() {
    Scanner sc = new Scanner(System.in);
    config conf = new config();
    TestAppv2a test = new TestAppv2a();
    
    test.viewCitizen();
    System.out.print("Enter Citizen ID to search: ");
    int id = sc.nextInt();
    
    while (conf.getSingleValue("SELECT c_id FROM citizen WHERE c_id = ?", id) == 0) {
        System.out.print("ID doesn't exist, please enter again: ");
        id = sc.nextInt();
    }
    
    String query = "SELECT record.r_id, blotter.b_reported, blotter.b_fname, citizen.c_lname, "
                 + "blotter.b_incident, blotter.b_location, record.r_datesettled, record.r_status "
                 + "FROM record "
                 + "JOIN citizen ON record.r_id = citizen.c_id "
                 + "JOIN blotter ON record.b_id = blotter.b_id "
                 + "WHERE record.r_id = " + id;
    
    String[] headers = {"RECORD ID", "DATE REPORTED", "COMPLAINANT", "CITIZEN LAST NAME", 
                        "INCIDENT", "LOCATION", "DATE SETTLED", "STATUS"};
    String[] columns = {"r_id", "b_reported", "b_fname", "c_lname", 
                        "b_incident", "b_location", "r_datesettled", "r_status"};

    conf.viewRecords(query, headers, columns);
}

        
}
