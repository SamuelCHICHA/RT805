import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.xpath.XPathExpressionException;

import org.xml.sax.SAXException;

import carpool.User;
import db.Database;

public class Register extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        if(session.getAttribute("id") == null)
            req.getRequestDispatcher("register.jsp").forward(req, resp);
        else
            resp.sendRedirect(req.getContextPath() + "/");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        HttpSession session = req.getSession();
        Database database = Database.getDatabase();
        String email = req.getParameter("email");
        String name = req.getParameter("name");
        String lastname = req.getParameter("lastname");
        String password = req.getParameter("password");
        if(session.getAttribute("id") == null){
            try {
                User.register(database.read(), email, password, name, lastname);
            } catch (XPathExpressionException | ParserConfigurationException | SAXException | TransformerException e) {
                e.printStackTrace();
            }
        }
        resp.sendRedirect(req.getContextPath() + "/");
    }
}
