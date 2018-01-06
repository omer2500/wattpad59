package wattpad.backend.servlets;

import wattpad.backend.objects.BookInfo;
import wattpad.backend.database.operations.ConnPool;
import wattpad.backend.database.operations.BooksResProvider;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Yarden-PC on 30-Dec-17.
 */

public class WattpadAppResourceServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String reqCode = req.getParameter("req");
        Connection conn = null;
        int reqClient = -1;
        if(reqCode!=null){
            reqClient = Integer.parseInt(reqCode);
            switch (reqClient){
                case 0:{
                    String wattpadId = req.getParameter("wattpad_id");
                    conn = ConnPool.getInstance().getConnection();
                    BooksResProvider booksProvider = new BooksResProvider();
                    try {
                        List<BookInfo> posts= booksProvider.getAllBooksByWattpad(wattpadId, conn);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    ConnPool.getInstance().returnConnection(conn);
                }
            }
        }
    }

}
