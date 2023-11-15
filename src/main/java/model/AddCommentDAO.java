package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AddCommentDAO {
    public AddCommentDAO(Board bo) {
        if (bo.getName().isEmpty()) {
            bo.setName("Anonymous");
        }
        if (bo.getComment().isEmpty()) {
            bo.setComment("no comment");
        }

        final String jdbcId = "id";
        final String jdbcPass = "password";
        final String jdbcUrl = "jdbc:mysql://localhost:3306/dbname?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=JST";

        Connection con = null;

        try {
            con = DriverManager.getConnection(jdbcUrl, jdbcId, jdbcPass);

            System.out.println("Connected...");

            try {
                PreparedStatement ps = con.prepareStatement("INSERT INTO board(name, comment) VALUES (?, ?)");

                ps.setString(1, bo.getName());
                ps.setString(2, bo.getComment());

                // send stationery
                int r = ps.executeUpdate();

                if(r != 0) {
                    System.out.println(r + "Added writing.");
                } else {
                    System.out.println("Couldn't write");
                }
                ps.close();
            } catch(SQLException e) {
                e.printStackTrace();
            } finally {
                // Lost database connection
                if(con != null) {
                    try {
                        con.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Connection failed");
        }
    }
}
