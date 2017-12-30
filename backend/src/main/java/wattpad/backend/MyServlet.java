/*
   For step-by-step instructions on connecting your Android application to this backend module,
   see "App Engine Java Servlet Module" template documentation at
   https://github.com/GoogleCloudPlatform/gradle-appengine-templates/tree/master/HelloWorld
*/

package wattpad.backend;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.*;

public class MyServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String reqCode =req.getParameter("req");
        String userName = req.getParameter("user");
        String userPass = req.getParameter("pass");
        resp.setContentType("text/plain");
        PrintWriter pw = resp.getWriter();

        if(reqCode==null || userName == null || userPass == null){
            resp.sendError(404);
        }
        else {
            pw.println("req= " + reqCode + " name= " + userName + " ," + " pass= " + userPass);
        }
    }
}
