package edu.wctc.nla.bookwebapp2.controller;

import edu.wctc.nla.bookwebapp2.model.Author;
import edu.wctc.nla.bookwebapp2.model.AuthorDAO;
import edu.wctc.nla.bookwebapp2.model.AuthorDaoStrategyTeach;
import edu.wctc.nla.bookwebapp2.model.AuthorService;
import edu.wctc.nla.bookwebapp2.model.ConnPoolAuthorDaoTeach;
import edu.wctc.nla.bookwebapp2.model.DbStrategy;
import edu.wctc.nla.bookwebapp2.model.MySqlDb;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 * The main controller for author-related activities
 *
 * @author jlombardo
 */
@WebServlet(name = "AuthorController", urlPatterns = {"/AuthorController"})
public class AuthorController extends HttpServlet {

    // NO MAGIC NUMBERS!

    private static final String NO_PARAM_ERR_MSG = "No request parameter identified";
    private static final String LIST_PAGE = "/listAuthors.jsp";
    private static final String LIST_ACTION = "list";
    private static final String ADD_ACTION = "create";
    private static final String UPDATE_ACTION = "update";
    private static final String DELETE_ACTION = "delete";
    private static final String ACTION_PARAM = "action";

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String destination = LIST_PAGE;
        String action = request.getParameter(ACTION_PARAM);

        /*
         For now we are hard-coding the strategy objects into this
         controller. In the future we'll auto inject them from a config
         file. Also, the DAO opens/closes a connection on each method call,
         which is not very efficient. In the future we'll learn how to use
         a connection pool to improve this.
         */
        DbStrategy db = new MySqlDb();
        AuthorDAO authDao
                = new AuthorDAO(db, "com.mysql.jdbc.Driver",
                        "jdbc:mysql://localhost:3306/book", "root", "admin");
        AuthorService authService = new AuthorService(authDao);

        try {
            /*
             Here's what the connection pool version looks like.
             */
//            Context ctx = new InitialContext();
//            DataSource ds = (DataSource)ctx.lookup("jdbc/book");
//            AuthorDaoStrategy authDao = new ConnPoolAuthorDao(ds, new MySqlDbStrategy());
//            AuthorService authService = new AuthorService(authDao);

            /*
             Determine what action to take based on a passed in QueryString
             Parameter
             */
            if (action.equals(LIST_ACTION)) {
                List<Author> authors = null;
                authors = authService.getAllAuthors();
                request.setAttribute("authors", authors);
                destination = LIST_PAGE;

            } else if (action.equals(ADD_ACTION)) {
                System.out.println("test");
                List<Author> authors = null;
                String createName = request.getParameter("createName");
                List<String> keyColumns = new ArrayList();
                List<String> valueColumns = new ArrayList();
                DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
                Date date = new Date();
                
                //keyColumns.add("author_id");
                //valueColumns.add("1");
                keyColumns.add("author_name");
                valueColumns.add(createName);
                keyColumns.add("date_created");
                valueColumns.add(dateFormat.format(date));
                System.out.println(valueColumns.toString());
                authors = authService.createRecordPrepareStatement("author", keyColumns, valueColumns);
                request.setAttribute("authors", authors);
                destination = LIST_PAGE;
                // coming soon
            } else if (action.equals(UPDATE_ACTION)) {
                // coming soon
                List<Author> authors = null;
                String updateId = request.getParameter("updateId");
                String updateName = request.getParameter("updateName");
                List<String> keyColumns = new ArrayList();
                List<String> valueColumns = new ArrayList();
                keyColumns.add("author_name");
                valueColumns.add(updateName);
                authors = authService.updateByPrimaryKeyPrepareStatement("author", "author_id", updateId, keyColumns, valueColumns);
                request.setAttribute("authors",authors);
                destination = LIST_PAGE;
            } else if (action.equals(DELETE_ACTION)) {
                List<Author> authors = null;
                String authorId = request.getParameter("deleteId");
                authors = authService.deleteByPrimaryKeyPrepareStatement("author", "author_id", authorId);
                request.setAttribute("authors",authors);
                destination = LIST_PAGE;
                // coming soon
            } else {
                // no param identified in request, must be an error
                request.setAttribute("errMsg", NO_PARAM_ERR_MSG);
                destination = LIST_PAGE;
            }
            
        } catch (Exception e) {
            request.setAttribute("errMsg", e.getCause().getMessage());
        }

        // Forward to destination page
        RequestDispatcher dispatcher
                = getServletContext().getRequestDispatcher(destination);
        dispatcher.forward(request, response);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
