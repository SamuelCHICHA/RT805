import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import carpool.User;

import java.io.IOException;
import db.Database;


public class Home extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Database database;
        Document document;
        User user;
        Node node;
        if(req.getSession().getAttribute("id") != null){
            database = Database.getDatabase();
            try {
                document = database.read();
                node = Database.queryOne(document, "/database/users/user[id='" + req.getSession().getAttribute("id") + "']");
                if(node != null){
                    user = User.fromXML(node);
                    req.setAttribute("user", user);
                } else {
                    assert(node != null);
                }
            } catch (XPathExpressionException | ParserConfigurationException | SAXException e) {
                e.printStackTrace();
            }
        }
        req.getRequestDispatcher("home.jsp").forward(req, resp);
    }

}