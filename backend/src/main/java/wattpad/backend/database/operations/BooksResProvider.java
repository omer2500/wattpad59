package wattpad.backend.database.operations;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import wattpad.backend.objects.BookInfo;
import wattpad.backend.objects.WattpadInfo;

/**
 * Created by Yarden-PC on 30-Dec-17.
 */

public class BooksResProvider {

    private static final String update_sql = "UPDATE books SET name=?, description=?, content=?, image=? WHERE book_id=?;";

    private static final String select_sql = "SELECT * FROM  books WHERE book_id=?;";

    private static final String select_category_sql = "SELECT * FROM  books WHERE wattpad_id=?;";

    private static final String select_img_sql = "SELECT image FROM  books WHERE book_id=?;";

    private static final String insert_sql = "INSERT INTO books (book_id, name, description, content, image, wattpad_id) VALUES (?, ?, ?, ?, ?, ?);";

    private static final String delete_sql = "DELETE FROM books WHERE book_id=?;";

    private static final String delete_all_sql_by_id = "DELETE FROM books WHERE wattpad_id=?;"; // delete books by wattpad_id

    private static final String select_all_sql_by_id = "SELECT * FROM books WHERE wattpad_id=?;"; // get books of wattpad_id

    private static final String select_all_sql = "SELECT * FROM books;"; // get all books


    public List<BookInfo> getAllBooks(Connection conn) throws SQLException {

        List<BookInfo> results = new ArrayList<BookInfo>();

        ResultSet rs = null;
        PreparedStatement ps = null;
        try {

            ps = conn.prepareStatement(select_all_sql);

            rs = ps.executeQuery();

            while (rs.next()) {

                String bookId = rs.getString(1);
                String bookName = rs.getString(2);
                String bookDescription = rs.getString(3);
                String bookContent = rs.getString(4);

                java.sql.Blob imageBlob = rs.getBlob(5);
                byte[] image = null;
                if (imageBlob != null) {
                    image = imageBlob.getBytes(1, (int) imageBlob.length());
                }


                String wattpadID = rs.getString(6);
                BookInfo bookInfo = new BookInfo(bookId, bookName, bookDescription, image,
                        bookContent);

                results.add(bookInfo);

            }

        } catch (SQLException e) {
            throw e;

        } catch (Throwable e) {
            e.printStackTrace();

        } finally {
            if (rs != null) {
                try {
                    rs.close();
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

        return results;
    }


    public List<BookInfo> getAllBooksByCategory(Connection conn, String category) throws SQLException {

        List<BookInfo> results = new ArrayList<BookInfo>();

        ResultSet rs = null;
        PreparedStatement ps = null;
        try {

            ps = conn.prepareStatement(select_category_sql);

            ps.setString(1, category);

            rs = ps.executeQuery();

            while (rs.next()) {

                String bookId = rs.getString(1);
                String bookName = rs.getString(2);
                String bookDescription = rs.getString(3);
                String bookContent = rs.getString(4);

                java.sql.Blob imageBlob = rs.getBlob(5);
                byte[] image = null;
                if (imageBlob != null) {
                    image = imageBlob.getBytes(1, (int) imageBlob.length());
                }


                String wattpadID = rs.getString(6);
                BookInfo bookInfo = new BookInfo(bookId, bookName, bookDescription, image,
                        bookContent);

                results.add(bookInfo);

            }

        } catch (SQLException e) {
            throw e;

        } catch (Throwable e) {
            e.printStackTrace();

        } finally {
            if (rs != null) {
                try {
                    rs.close();
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

        return results;
    }






    public List<BookInfo> getAllBooksByWattpad(WattpadInfo wattpadInfo, Connection conn)
            throws SQLException {

        List<BookInfo> results = new ArrayList<BookInfo>();

        if (wattpadInfo == null) {
            return results;
        }

        ResultSet rs = null;
        PreparedStatement ps = null;
        try {

            ps = conn.prepareStatement(select_all_sql_by_id);

            ps.setString(1, wattpadInfo.getId());

            rs = ps.executeQuery();

            while (rs.next()) {

                String bookId = rs.getString(1);
                String bookName = rs.getString(2);
                String bookDescription = rs.getString(3);
                String bookContent = rs.getString(4);

                java.sql.Blob imageBlob = rs.getBlob(5);
                byte[] image = null;
                if (imageBlob != null) {
                    image = imageBlob.getBytes(1, (int) imageBlob.length());
                }


                String wattpadID = rs.getString(6);
                BookInfo bookInfo = new BookInfo(bookId, bookName, bookDescription, image,
                        bookContent);

                results.add(bookInfo);

            }

        } catch (SQLException e) {
            throw e;

        } catch (Throwable e) {
            e.printStackTrace();

        } finally {
            if (rs != null) {
                try {
                    rs.close();
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

        return results;
    }

    public byte[] getImage(String bookId, Connection conn)
            throws SQLException {

        byte[] result = null;

        ResultSet rs = null;
        PreparedStatement ps = null;
        try {

            ps = conn.prepareStatement(select_img_sql);

            ps.setString(1, bookId);

            rs = ps.executeQuery();

            while (rs.next()) {

                java.sql.Blob imageBlob = rs.getBlob(1);
                if (imageBlob != null) {
                    result = imageBlob.getBytes(1, (int) imageBlob.length());
                }
            }

        } catch (SQLException e) {
            throw e;

        } catch (Throwable e) {
            e.printStackTrace();

        } finally {
            if (rs != null) {
                try {
                    rs.close();
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

    public boolean insertBookInfo(BookInfo obj, Connection conn) throws SQLException{

        boolean result = false;
        ResultSet rs = null;
        ResultSet rs1 = null;
        PreparedStatement ps = null;
        PreparedStatement stt = null;

        try {

            String book_id = obj.getId();
            String name = obj.getName();
            String description = obj.getDescription();
            byte[] imageBytes = obj.getImage();
            String content = obj.getContent();
            String wattpad_id = obj.getWattpadId();

            if (imageBytes == null) {
                try {
                    imageBytes = getImage(book_id, conn);
                }catch (Throwable e){
                    System.out.println("no image for book "+ name);
                }
            }



            stt = (PreparedStatement) conn.prepareStatement(select_sql);
            stt.setString(1, book_id);

            if (stt.execute()) {
                rs1 = stt.getResultSet();
                if (rs1.next()) {
                    // its execute update
                    ps = (PreparedStatement) conn.prepareStatement(update_sql);

                    ps.setString(1, book_id);
                    ps.setString(2, name);
                    ps.setString(3, description);
                    ps.setString(4, content);

                    if (imageBytes != null) {
                        InputStream is = new ByteArrayInputStream(imageBytes);
                        ps.setBlob(5, is);

                    } else {

                        ps.setNull(5, Types.BLOB);
                    }





                    // where
                    ps.setString(6, wattpad_id);

                    ps.execute();

                    result = true;

                } else {

                    // its execute insert
                    ps = (PreparedStatement) conn.prepareStatement(insert_sql);
                    ps.setString(1, book_id);
                    ps.setString(2, name);
                    ps.setString(3, description);
                    ps.setString(4, content);

                    if (imageBytes != null) {
                        InputStream is = new ByteArrayInputStream(imageBytes);
                        ps.setBlob(5, is);

                    } else {

                        ps.setNull(5, Types.BLOB);
                    }

                    ps.setString(6, wattpad_id);

                    ps.execute();

                    result = true;

                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
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

    public boolean deleteAllBooksByWattpad(WattpadInfo wattpadInfo, Connection conn)
            throws SQLException {

        boolean result = false;

        PreparedStatement ps = null;

        try {

            ps = (PreparedStatement) conn.prepareStatement(delete_all_sql_by_id);

            ps.setString(1, wattpadInfo.getId());

            ps.execute();

            result = true;

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

    public boolean deleteBook(BookInfo obj, Connection conn) throws SQLException {

        boolean result = false;
        PreparedStatement ps = null;

        try {

            if (obj != null) {

                ps = (PreparedStatement) conn.prepareStatement(delete_sql);

                String id = obj.getId();

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
