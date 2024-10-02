
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
    
    
    public static void main(String[] args) {
       
        TestAppv2a test= new TestAppv2a();
        Scanner sc = new Scanner(System.in);
       
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
        }
        
    }
    
}
