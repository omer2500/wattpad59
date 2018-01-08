package wattpad.backend.servlets;

import wattpad.backend.objects.BookInfo;
import wattpad.backend.database.operations.ConnPool;
import wattpad.backend.database.operations.BooksResProvider;
import wattpad.backend.objects.WattpadInfo;
import wattpad.backend.utils.FilesUtils;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Yarden-PC on 30-Dec-17.
 */

public class WattpadAppResourceServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    // ========
    private static final int GET_ALL_BOOKS_JSON_REQ = 0;
    //private static final int INSERT_WATTPAD_REQ = 1;
    //private static final int DELETE_WATTPAD_REQ = 2;
    //private static final int INSERT_BOOK_REQ = 3;

    private static final int DELETE_BOOK_REQ = 3;
    private static final int GET_BOOK_IMAGE_REQ = 4;

    private static final int GET_BOOKS_OF_WATTPAD_JSON_REQ = 5;

    private static final String WATTPAD_ID = "wattpad_id";
    //private static final String WATTPAD_PASS = "wattpad_pass";

    private static final String RESOURCE_FAIL_TAG = "{\"result_code\":0}";
    private static final String RESOURCE_SUCCESS_TAG = "{\"result_code\":1}";

    private static final String BOOK_ID = "book_id";
    private static final String BOOK_NAME = "book_name";
    private static final String BOOK_DESCRIPTION = "book_description";
    private static final String BOOK_CONTENT = "book_content";


    private static final String REQ = "req";

    //public static final int DB_RETRY_TIMES = 5;


    public void init(ServletConfig config) throws ServletException {

        super.init();

        String tmp = config.getServletContext().getInitParameter("localAppDir");
        if (tmp != null) {
            FilesUtils.appDirName = config.getServletContext().getRealPath(tmp);
            System.out.println(FilesUtils.appDirName);

        }

    }

    protected void service(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        String respPage = null;
        String userReq = req.getParameter(REQ);
        Connection conn = null;
        //int retry = DB_RETRY_TIMES;

        if (userReq != null) {

            int reqNo = Integer.valueOf(userReq);
            System.out.println("WattpadAppResourceServlet:: req code ==>" + reqNo);
            //while (retry > 0) {

            try {

                switch (reqNo) {

                    // == folder apis
                    case GET_ALL_BOOKS_JSON_REQ: {

                        conn = ConnPool.getInstance().getConnection();
                        BooksResProvider booksResProvider = new BooksResProvider();
                        List<BookInfo> booksList = booksResProvider
                                .getAllBooks(conn);
                        String resultJson = BookInfo.toJson(booksList);

                        if (resultJson != null && !resultJson.isEmpty()) {
                            respPage = resultJson;
                            resp.addHeader("Content-Type",
                                    "application/json; charset=UTF-8");
                            PrintWriter pw = resp.getWriter();
                            pw.write(respPage);
                        } else {
                            resp.sendError(404);
                        }

                        //retry = 0;
                        break;
                    }

//					case INSERT_TUMBLER_REQ: {
//						String id = req.getParameter(TUMBLER_ID);
//						String title = req.getParameter(TUMBLER_PASS);
//						respPage = RESOURCE_FAIL_TAG;
//						resp.addHeader("Content-Type",
//								"application/json; charset=UTF-8");
//						conn = ConnPool.getInstance().getConnection();
//						TumblerResProvider tumblerResProvider = new TumblerResProvider();
//
//						TumblerInfo tumblerInfo = new TumblerInfo(id, title);
//						if (tumblerResProvider.insertTumbler(tumblerInfo, conn)) {
//							respPage = RESOURCE_SUCCESS_TAG;
//						}
//						PrintWriter pw = resp.getWriter();
//						pw.write(respPage);
//
//						//retry = 0;
//						break;
//					}
//
//					case DELETE_TUMBLER_REQ: {
//						String id = req.getParameter(TUMBLER_ID);
//						respPage = RESOURCE_FAIL_TAG;
//						resp.addHeader("Content-Type",
//								"application/json; charset=UTF-8");
//						conn = ConnPool.getInstance().getConnection();
//						TumblerResProvider tumblerResProvider = new TumblerResProvider();
//
//						TumblerInfo tumblerInfo = new TumblerInfo(id);
//						if (tumblerResProvider.deleteTumbler(tumblerInfo, conn)) {
//							respPage = RESOURCE_SUCCESS_TAG;
//						}
//						PrintWriter pw = resp.getWriter();
//						pw.write(respPage);
//
//						//retry = 0;
//						break;
//					}
//
//						case INSERT_BOOK_REQ: {
//							String id = req.getParameter(BOOK_ID);
//
//							String name = req.getParameter(BOOK_NAME);
// 							String description = req.getParameter(BOOK_DESCRIPTION);
//
//							String content = req.getParameter(BOOK_CONTENT);
//
//							String wattpadId = req.getParameter(WATTPAD_ID);
//
//
//							respPage = RESOURCE_FAIL_TAG;
//							resp.addHeader("Content-Type",
//									"application/json; charset=UTF-8");
//							conn = ConnPool.getInstance().getConnection();
//							PostsResProvider postsResProvider = new PostsResProvider();
//
//							PostInfo post = new PostInfo(id);
//							post.setTitle(title);
//							post.setContent(content);
//							post.setTumblerId(tumblerId);
//							post.setTag(tag);
//
//							if (postsResProvider.insertPostInfo(post, conn)) {
//								respPage = RESOURCE_SUCCESS_TAG;
//							}
//							PrintWriter pw = resp.getWriter();
//							pw.write(respPage);
//
//							//retry = 0;
//							break;
//						}

                    case DELETE_BOOK_REQ: {
                        String id = req.getParameter(BOOK_ID);
                        respPage = RESOURCE_FAIL_TAG;
                        resp.addHeader("Content-Type",
                                "application/json; charset=UTF-8");
                        conn = ConnPool.getInstance().getConnection();
                        BooksResProvider booksResProvider = new BooksResProvider();
                        BookInfo bookInfo = new BookInfo(id);
                        if (booksResProvider.deleteBook(bookInfo, conn)) {
                            respPage = RESOURCE_SUCCESS_TAG;
                        }
                        PrintWriter pw = resp.getWriter();
                        pw.write(respPage);

                        //retry = 0;
                        break;
                    }

                    case GET_BOOKS_OF_WATTPAD_JSON_REQ: {

                        String id = req.getParameter(WATTPAD_ID);
                        conn = ConnPool.getInstance().getConnection();
                        BooksResProvider booksResProvider = new BooksResProvider();
                        WattpadInfo wattpadInfo = new WattpadInfo(id);
                        List<BookInfo> itemsList = booksResProvider.getAllBooksByWattpad(
                                wattpadInfo, conn);
                        String resultJson = BookInfo.toJson(itemsList);

                        if (resultJson != null && !resultJson.isEmpty()) {
                            respPage = resultJson;
                            resp.addHeader("Content-Type",
                                    "application/json; charset=UTF-8");
                            PrintWriter pw = resp.getWriter();
                            pw.write(respPage);
                        } else {
                            resp.sendError(404);
                        }

                        //retry = 0;
                        break;
                    }

                    case GET_BOOK_IMAGE_REQ: {
                        String id = req.getParameter(BOOK_ID);
                        respPage = RESOURCE_FAIL_TAG;

                        conn = ConnPool.getInstance().getConnection();
                        BooksResProvider booksResProvider = new BooksResProvider();

                        byte[] imgBlob = booksResProvider.getImage(id, conn);

                        if (imgBlob != null && imgBlob.length > 0) {
                            resp.setContentType("application/x-download");
                            resp.setHeader("Content-disposition", "attachment; filename=" + "book.png");
                            ServletOutputStream os = resp.getOutputStream();
                            os.write(imgBlob);
                        } else {
                            resp.sendError(404);
                        }

                        //retry = 0;
                        break;
                    }



                    default:
                        //retry = 0;
                }

            } catch (SQLException e) {
                e.printStackTrace();
                //	retry--;
            } catch (Throwable t) {
                t.printStackTrace();
                //	retry = 0;
            } finally {
                if (conn != null) {
                    ConnPool.getInstance().returnConnection(conn);
                }
            }
            //}
        }

    }

}
