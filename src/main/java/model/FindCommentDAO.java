package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FindCommentDAO {
    public List<Board> findComment() {
        // store the comments
        List<Board> list = new ArrayList<>();

        final String jdbcId = "id";
        final String jdbcPass = "password";
        final String jdbcUrl = "jdbc:mysql://localhost:3306/dbname?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=JST";

        Connection con = null;

        try {
            con = DriverManager.getConnection(jdbcUrl, jdbcId, jdbcPass);
            System.out.println("Connected ...");

            try {
                Statement st = con.createStatement();
                String sql = "SELECT * FROM board";

                try {
                    // send sql
                    ResultSet rs = st.executeQuery(sql);

                    while (rs.next()) {
                        Board bo = new Board();
                        bo.setId(rs.getInt("id"));
                        bo.setName(rs.getString("name"));
                        bo.setComment(rs.getString("comment"));
                        bo.setTime(rs.getTimestamp("time"));

                        list.add(bo);
                    }
                    rs.close();
                    st.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                // lost connection with database
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
            return null;
        }
        return list;
    }
}
