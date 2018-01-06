package wattpad.backend.database.operations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import wattpad.backend.objects.WattpadInfo;

/**
 * Created by Yarden-PC on 06-Jan-18.
 */

public class WattpadResProvider {

    private static final String update_sql = "UPDATE wattpads SET password=? WHERE id=?;";

    private static final String select_sql = "SELECT * FROM  wattpads WHERE id=?;";

    private static final String insert_sql = "INSERT INTO wattpads (id, password) VALUES (?,?);";

    private static final String delete_sql = "DELETE FROM wattpads WHERE id=?;";

    //private static final String select_all_sql = "SELECT * FROM wattpads;";


    public boolean insertTumbler(WattpadInfo obj, Connection conn) {

        boolean result = false;
        ResultSet rs = null;
        ResultSet rs1 = null;
        PreparedStatement ps = null;
        PreparedStatement stt = null;

        try {

            String id = obj.getId();
            String password = obj.getPassword();

            stt = (PreparedStatement) conn.prepareStatement(select_sql);
            stt.setString(1, id);


            if (stt.execute()) {
                rs1 = stt.getResultSet();
                if (rs1.next()) {

                    // its execute update
                    ps = (PreparedStatement) conn.prepareStatement(update_sql);
                    ps.setString(1, password);
                    // where
                    ps.setString(2, id);
                    ps.execute();
                    result = true;
                } else {

                    // its execute insert
                    ps = (PreparedStatement) conn.prepareStatement(insert_sql);
                    ps.setString(1,id);
                    ps.setString(2, password);
                    ps.execute();
                    result = true;

                }
            }


        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            }
            if (rs1 != null) {
                try {
                    rs1.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            }

            if (stt != null) {
                try {
                    stt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            }

            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return result;

    }


    public boolean deleteWattpad(WattpadInfo obj,
                                 Connection conn) throws SQLException {

        boolean result = false;
        PreparedStatement ps = null;


        try {

            if (obj != null) {

                String id = obj.getId();

                BooksResProvider itemResProvider = new BooksResProvider();
                itemResProvider.deleteAllBooksByWattpad(new WattpadInfo(id), conn);

                ps = (PreparedStatement) conn.prepareStatement(delete_sql);


                ps.setString(1, id);

                ps.execute();

                result = true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {

            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {

                    e.printStackTrace();
                }
            }
        }

        return result;
    }



}
