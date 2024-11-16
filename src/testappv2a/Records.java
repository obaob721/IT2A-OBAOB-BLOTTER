package testappv2a;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Records {
    
    public void main(String[] args) {
        
        Records r = new Records();
        Scanner sc = new Scanner(System.in);
        String resp;
        
        do{    
            System.out.println("--------------------------------");
            System.out.println("1. ADD RECORD");
            System.out.println("2. UPDATE RECORD");
            System.out.println("3. DELETE RECORD");
            System.out.println("4. VIEW ALL RECORD");
            System.out.println("5. SEARCH CITIZEN RECORD");
            System.out.println("6. VIEW RECORD BY ID");
            System.out.println("7. EXIT");
            
        int action = 1;
        
        while (true) {
                System.out.print("Enter Action: ");
                
                if (sc.hasNextInt()) {
                    action = sc.nextInt();
                    
                    if (action >= 1 && action <= 7) {
                        break;
                    } else {
                        System.out.println("Invalid Action. Enter number (1-7) only.");
                    }
                } else {
                    System.out.println("Invalid Input. Must be an Integer.");
                    sc.next();
                }
            }

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
                    r.searchCitizenRecord();
               break;
                case 6:
                    r.viewAllRecord();
                    r.viewRecordById();
               break;
                case 7: 
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
        
   
    public void addRecord() {
        Scanner sc = new Scanner(System.in);
        config conf = new config();
        
        TestAppv2a test = new TestAppv2a(); 
        test.viewCitizen();
        
        System.out.print("Enter Citizen ID: ");
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
        
        Blotter bltt = new Blotter(); 
        bltt.viewBlotter();
        
        System.out.print("Enter Blotter ID: ");
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

        
        int r_offense;
        while (true) {
            System.out.print("Number of Offense: ");
            if (sc.hasNextInt()) {
                r_offense = sc.nextInt();
                sc.nextLine();
                if (r_offense >= 1) {
                    break;
                } else {
                    System.out.println("Invalid input. Offense must be a positive integer.");
                }
            } else {
                sc.nextLine();
                System.out.println("Invalid input. Please enter a valid integer.");
            }
        }
        
        String r_status = "cleared";
        
        LocalDate currdate = LocalDate.now();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        String date = currdate.format(format);
        
        
        String sql = "INSERT INTO record (c_id, b_id, r_offense, r_status, r_datesettled) VALUES (?, ?, ?, ?, ?)";

        conf.addRecord(sql, c_id, b_id, r_offense, r_status, date);   
    }
       
   private void updateRecord(){
    
        Scanner sc = new Scanner(System.in);
        config conf = new config();

        System.out.print("Enter Record ID to update: ");
        int r_id;
        
        while (true) {

            if (sc.hasNextInt()) {
                r_id = sc.nextInt();

                if (conf.getSingleValue("SELECT r_id FROM record WHERE r_id = ?", r_id) != 0) {
                    break;
                } else {
                    System.out.print("ID doesn't exist, Select Again: ");
                }
            } else {

                System.out.print("Invalid Input, Enter Again: ");
                sc.next();
            }
        }

        
        int r_offense;
        while (true) {
            System.out.print("Enter new number of offense: ");
            if (sc.hasNextInt()) {
                r_offense = sc.nextInt();
                sc.nextLine();
                if (r_offense >= 1) {
                    break;
                } else {
                    System.out.println("Invalid input. Offense must be a positive integer.");
                }
            } else {
                sc.nextLine();
                System.out.println("Invalid input. Please enter a valid integer.");
            }
        }
        
        
        String r_status = "cleared";
        
        LocalDate currdate = LocalDate.now();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        String date = currdate.format(format);
        
        String query = "UPDATE record SET r_offense = ?, r_status = ?, r_datesettled = ? WHERE r_id = ?";
        
        conf.updateRecord(query, r_offense,r_status, date,r_id);
        
    }
private void deleteRecord(){
    
        Scanner sc = new Scanner (System.in);
        config conf = new config();

        System.out.print("Enter Record ID to delete: ");
        int rec_id;
        
        while (true) {

            if (sc.hasNextInt()) {
                rec_id = sc.nextInt();

                if (conf.getSingleValue("SELECT r_id FROM record WHERE r_id = ?", rec_id) != 0) {
                    break;
                } else {
                    System.out.print("ID doesn't exist, Select Again: ");
                }
            } else {

                System.out.print("Invalid Input, Enter Again: ");
                sc.next();
            }
        }
        
        String query = "Delete FROM record WHERE r_id = ?";
       
        conf.deleteRecord(query, rec_id);
    }

    public void viewAllRecord() {
        String query = "SELECT record.r_id, record.r_datesettled, record.r_status, blotter.b_incident, " +
                       "blotter.b_reported, blotter.b_fname " +
                       "FROM record " +
                       "LEFT JOIN blotter ON blotter.b_id = record.b_id ";
                       
        String[] headers = {"RECORD ID", "DATE REPORTED", "COMPLAINANT'S NAME", "DATE SETTLED", "STATUS"};
        String[] columns = {"r_id", "b_reported", "b_fname", "r_datesettled", "r_status"};

        config conf = new config();
        conf.viewRecords(query, headers, columns);
    }

    public void searchCitizenRecord() {
        TestAppv2a test = new TestAppv2a();
        test.viewCitizen();
        config conf = new config();
        
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Citizen ID to search: ");
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
        
        String query = "SELECT citizen.c_id, citizen.c_lname, blotter.b_incident, blotter.b_reported, record.r_offense " +
                       "FROM citizen " +
                       "LEFT JOIN record ON citizen.c_id = record.c_id " +
                       "LEFT JOIN blotter ON record.b_id = blotter.b_id " +
                       "WHERE citizen.c_id = " + c_id;

        String[] headers = {"CITIZEN ID", "CITIZEN_LNAME", "INCIDENT", "B_REPORTED", "NUMBER OF OFFENSE"};
        String[] columns = {"c_id", "c_lname", "b_incident", "b_reported", "r_offense"};
        
        conf.viewRecords(query, headers, columns);
        
        
        String ttlOffense = "SELECT SUM(r_offense) AS total_offense FROM record WHERE c_id = ?";
        double sum = conf.getSingleValue(ttlOffense, c_id);

        System.out.println("\n-------------------------------------------------------------");
        System.out.println("TOTAL NUMBER OF OFFENSE: " + (sum > 0 ? sum : "No offenses recorded"));
        System.out.println("-------------------------------------------------------------\n");
      
    }

    public void viewRecordById() {
    Scanner sc = new Scanner(System.in);
    config conf = new config();
    
    System.out.print("Enter Record ID to view: ");
    int r_id;
    
    while (true) {

            if (sc.hasNextInt()) {
                r_id = sc.nextInt();

                if (conf.getSingleValue("SELECT r_id FROM record WHERE r_id = ?", r_id) != 0) {
                    break;
                } else {
                    System.out.print("ID doesn't exist, Select Again: ");
                }
            } else {

                System.out.print("Invalid Input, Enter Again: ");
                sc.next();
            }
        }

    String query = "SELECT record.r_id, blotter.b_reported, blotter.b_fname, " +
                   "citizen.c_lname, blotter.b_incident, blotter.b_location, " +
                   "record.r_status, record.r_datesettled " +
                   "FROM record " +
                   "LEFT JOIN blotter ON blotter.b_id = record.b_id " +
                   "LEFT JOIN citizen ON citizen.c_id = record.c_id " +
                   "WHERE record.r_id = " + r_id;

    String[] headers = {"RECORD ID", "DATE REPORTED", "COMPLAINANT'S NAME", "SUSPECT NAME", "INCIDENT", 
                        "LOCATION", "STATUS", "DATE SETTLED"};
    String[] columns = {"r_id", "b_reported", "b_fname", "c_lname", "b_incident", 
                        "b_location", "r_status", "r_datesettled"};

    
    conf.viewRecords(query, headers, columns);
}


}
