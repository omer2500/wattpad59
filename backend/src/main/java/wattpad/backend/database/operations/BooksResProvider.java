package wattpad.backend.database.operations;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import wattpad.backend.core.BookInfo;

/**
 * Created by Yarden-PC on 30-Dec-17.
 */

public class BooksResProvider {

    public List<BookInfo> getAllBooksByWattpad(String wattpadId, Connection conn) throws SQLException {
        List<BookInfo> result = new ArrayList<BookInfo>();
        if(wattpadId==null){
            return result;
        }
        ResultSet rs = null;
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement("select * from books where wattpad_id=?");
            ps.setString(1,wattpadId);
            rs= ps.executeQuery();
            while(rs.next()){
                String bookId = rs.getString(1);
                String bookName = rs.getString(2);
                String bookDescription = rs.getString(3);
                Blob imgblob = rs.getBlob(4);
                byte[] img= null;
                if(imgblob!=null){
                    img = imgblob.getBytes(1, (int)imgblob.length());
                }
                String bookContent = rs.getString(5);
                String wattpadID = rs.getString(6);

                BookInfo book = new BookInfo( bookName,  bookDescription,  img,
                        bookContent, wattpadID);
                result.add(book);
            }
        }catch (SQLException e){
            e.printStackTrace();
            throw e;
        }
        finally {
            if(rs!=null){
                rs.close();
            }

            if(ps!=null){
                ps.close();
            }
        }
        return result;
    }

}
