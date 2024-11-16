
package testappv2a;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class config {
    
    public static Connection connectDB() {
        Connection con = null;
        try {
            Class.forName("org.sqlite.JDBC"); 
            con = DriverManager.getConnection("jdbc:sqlite:Blotter.db"); 
        } catch (Exception e) {
            System.out.println("Connection Failed: " + e);
        }
        return con;
    }

    
    public void addRecord(String sql, Object... values) {
        try (Connection conn = this.connectDB(); 
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            for (int i = 0; i < values.length; i++) {
               if(values[i] instanceof Integer){
                pstmt.setInt(i+1, (Integer) values[i]);
               }else if (values[i] instanceof Double){
                pstmt.setDouble(i+1, (Double) values[i]);
               }else if (values[i] instanceof Float){
                pstmt.setFloat(i+1, (Float) values[i]);
               }else if (values[i] instanceof Long){
                pstmt.setLong(i+1, (Long) values[i]);
               }else if (values[i] instanceof Boolean){
                pstmt.setBoolean(i+1, (Boolean) values[i]);
               }else if (values[i] instanceof java.util.Date){
                pstmt.setDate(i+1, new java.sql.Date(((java.util.Date)values[i]).getTime()));
               }else if (values[i] instanceof java.sql.Date){
                pstmt.setDate(i+1, (java.sql.Date) values[i]);
               }else if (values[i] instanceof java.sql.Timestamp){
                pstmt.setTimestamp(i+1,(java.sql.Timestamp) values[i]);
               }else{
                pstmt.setString(i + 1, values[i].toString());
            }
         }
            pstmt.executeUpdate();
            System.out.println("Record added successfully!");
        } catch (SQLException e) {
            System.out.println("Error adding record: " + e.getMessage());
        }
    }
   public void viewRecords(String sqlQuery, String[] columnHeaders, String[] columnNames) {

        if (columnHeaders.length != columnNames.length) {
            System.out.println("Error: Mismatch between column headers and column names.");
            return;
        }

        int columnWidth = 20; 
        int totalWidth = (columnWidth + 3) * columnHeaders.length + 1;

        StringBuilder lineSeparatorBuilder = new StringBuilder();

        for (int i = 0; i < totalWidth; i++) {
            lineSeparatorBuilder.append("-");
        }

        String lineSeparator = lineSeparatorBuilder.toString();

        System.out.println(lineSeparator);

        StringBuilder headerRow = new StringBuilder("| ");
        for (String header : columnHeaders) {
            headerRow.append(String.format("%-" + columnWidth + "s | ", header));
        }
        System.out.println(headerRow);
        System.out.println(lineSeparator);

        try (Connection conn = this.connectDB();
             PreparedStatement pstmt = conn.prepareStatement(sqlQuery);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                StringBuilder row = new StringBuilder("| ");
                for (String colName : columnNames) {
                    String value = rs.getString(colName);
                    row.append(String.format("%-" + columnWidth + "s | ", value != null ? value : ""));
                }
                System.out.println(row);
            }

            System.out.println(lineSeparator);

        } catch (SQLException e) {
            System.out.println("Error retrieving records: " + e.getMessage());
        }
        
    }
   

     public void updateRecord(String sql, Object... values) {
        try (Connection conn = this.connectDB(); 
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            for (int i = 0; i < values.length; i++) {
                if (values[i] instanceof Integer) {
                    pstmt.setInt(i + 1, (Integer) values[i]); 
                } else if (values[i] instanceof Double) {
                    pstmt.setDouble(i + 1, (Double) values[i]); 
                } else if (values[i] instanceof Float) {
                    pstmt.setFloat(i + 1, (Float) values[i]); 
                } else if (values[i] instanceof Long) {
                    pstmt.setLong(i + 1, (Long) values[i]); 
                } else if (values[i] instanceof Boolean) {
                    pstmt.setBoolean(i + 1, (Boolean) values[i]); 
                } else if (values[i] instanceof java.util.Date) {
                    pstmt.setDate(i + 1, new java.sql.Date(((java.util.Date) values[i]).getTime())); 
                } else if (values[i] instanceof java.sql.Date) {
                    pstmt.setDate(i + 1, (java.sql.Date) values[i]); 
                } else if (values[i] instanceof java.sql.Timestamp) {
                    pstmt.setTimestamp(i + 1, (java.sql.Timestamp) values[i]); 
                } else {
                    pstmt.setString(i + 1, values[i].toString()); 
                }
            }

            pstmt.executeUpdate();
            System.out.println("Record updated successfully!");
        } catch (SQLException e) {
            System.out.println("Error updating record: " + e.getMessage());
        }
    }
   
     
     public void deleteRecord(String sql, Object... values) {
    try (Connection conn = this.connectDB();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {

        for (int i = 0; i < values.length; i++) {
            if (values[i] instanceof Integer) {
                pstmt.setInt(i + 1, (Integer) values[i]); 
            } else {
                pstmt.setString(i + 1, values[i].toString()); 
            }
        }

        pstmt.executeUpdate();
        System.out.println("Record deleted successfully!");
    } catch (SQLException e) {
        System.out.println("Error deleting record: " + e.getMessage());
    }
}
     private void setPreparedStatementValues(PreparedStatement pstmt, Object... values) throws SQLException{
         for (int i = 0; i < values.length; i++) {
                if (values[i] instanceof Integer) {
                    pstmt.setInt(i + 1, (Integer) values[i]); 
                } else if (values[i] instanceof Double) {
                    pstmt.setDouble(i + 1, (Double) values[i]); 
                } else if (values[i] instanceof Float) {
                    pstmt.setFloat(i + 1, (Float) values[i]); 
                } else if (values[i] instanceof Long) {
                    pstmt.setLong(i + 1, (Long) values[i]); 
                } else if (values[i] instanceof Boolean) {
                    pstmt.setBoolean(i + 1, (Boolean) values[i]); 
                } else if (values[i] instanceof java.util.Date) {
                    pstmt.setDate(i + 1, new java.sql.Date(((java.util.Date) values[i]).getTime())); 
                } else if (values[i] instanceof java.sql.Date) {
                    pstmt.setDate(i + 1, (java.sql.Date) values[i]); 
                } else if (values[i] instanceof java.sql.Timestamp) {
                    pstmt.setTimestamp(i + 1, (java.sql.Timestamp) values[i]); 
                } else {
                    pstmt.setString(i + 1, values[i].toString()); 
                }
            }
     }
     public double getSingleValue(String sql, Object... params){
         double result = 0.0;
         
          try (Connection conn = connectDB();
             PreparedStatement pstmt = conn.prepareStatement(sql)){
              
              setPreparedStatementValues(pstmt, params);
             ResultSet rs = pstmt.executeQuery();
             if(rs.next()){
                 result = rs.getDouble(1);
             }
         
          }catch(SQLException e){
              System.out.println("Error retrieving single value: "+e.getMessage());
          }
           return result;  
             
     }
     public String getSingleString(String query, int id) {
    String result = "";
    try (Connection conn = this.connectDB();
         PreparedStatement pstmt = conn.prepareStatement(query)) {

        pstmt.setInt(1, id);
        ResultSet rs = pstmt.executeQuery();

        if (rs.next()) {
            result = rs.getString(1);
        }
    } catch (SQLException e) {
        System.out.println("Error retrieving string value: " + e.getMessage());
    }
    return result;
}

}
