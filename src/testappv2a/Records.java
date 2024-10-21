
package testappv2a;

import java.util.Scanner;

public class Records {
   
    public void addRecord() {
        Scanner sc = new Scanner(System.in);
        config conf = new config();

        System.out.print("Enter Citizen ID: ");
        int c_id = sc.nextInt();
        System.out.print("Enter Blotter ID: ");
        int b_id = sc.nextInt();
        System.out.print("Number of Offense: ");
        int r_offense = sc.nextInt();
        System.out.print("Enter Status (done/ongoing/new): ");
        String r_status = sc.next();
        sc.nextLine();
        System.out.print("Enter Date Settled: ");
        String r_datesettled = sc.nextLine();
        
        String sql = "INSERT INTO record (c_id, b_id, r_offense, r_status, r_datesettled) VALUES (?, ?, ?, ?, ?)";

        conf.addRecord(sql, c_id, b_id, r_offense, r_status, r_datesettled);   
    }
    
    private void viewRecord() {
        String query = "SELECT * FROM record";
        String[] headers = {"RECORD ID", "CITIZEN ID", "BLOTTER ID", "NUMBER OF OFFENSE", "STATUS", "DATE SETTLED"};
        String[] columns = {"r_id", "c_id", "b_id", "r_offense", "r_status", "r_datesettled"};

        config conf = new config();
        conf.viewRecords(query, headers, columns);
    }
    
    private void updateRecord(){
    
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Record ID to update: ");
        int id = sc.nextInt();
        
        System.out.print("Number of Offense: ");
        int r_offense = sc.nextInt();
        System.out.print("Enter new Status(done/ongoing/new): ");
        String r_status = sc.next();
        sc.nextLine(); 
        System.out.print("Enter new Date Settled: ");
        String r_datesettled = sc.nextLine();
         
        String query = "UPDATE record SET r_offense = ?, r_status = ?, r_datesettled = ? WHERE r_id = ?";
        
        config conf = new config();
        conf.updateRecord(query, r_offense, r_status, r_datesettled, id);
        
    }
    
    private void deleteRecord(){
    
        Scanner sc = new Scanner (System.in);
        
        System.out.print("Enter Record ID to delete: ");
        int id = sc.nextInt();
        
        String query = "Delete FROM record WHERE r_id = ?";
       
        config conf = new config();
        conf.deleteRecord(query, id);

    }
    
    public void main(String[] args) {
        
        Records r = new Records();
        Scanner input = new Scanner(System.in);
       
        
        do{    
            System.out.println("1. ADD");
            System.out.println("2. UPDATE");
            System.out.println("3. DELETE");
            System.out.println("4. VIEW");
            System.out.println("5. EXIT");

            System.out.print("Enter Action: ");
            int action = input.nextInt();

            switch(action){
                case 1:
                    r.addRecord();
                break; 
                case 2:
                    r.viewRecord();
                    r.updateRecord();
                break;
                case 3:
                    r.viewRecord();
                    r.deleteRecord();
                    r.viewRecord();
                    break;
                case 4:
                    r.viewRecord();
                break;
                case 5:
                    return;
            }
            
            
        }while(true);
            
    }
        
}


