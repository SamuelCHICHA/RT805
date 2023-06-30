
import carpool.User;
import org.xml.sax.SAXException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;
import java.io.IOException;
import javax.servlet.http.HttpSession;

public class Login extends HttpServlet{
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        if(session.getAttribute("id") == null)
            req.getRequestDispatcher("login.jsp").forward(req, resp);
        else
            resp.sendRedirect(req.getContextPath() + "/");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("mail");
        String password = req.getParameter("password");
        HttpSession session = req.getSession();
        User user;
        if(session.getAttribute("id") == null) {
            try {
                user = User.authenticate(email, password);
                if (user != null) {
                    session.setAttribute("id", user.getId());
                    resp.sendRedirect(req.getContextPath() + "/");
                } else {
                    session.setAttribute("message", "Invalid password");
                    resp.sendRedirect(req.getContextPath() + "/login");
                }
            } catch (ParserConfigurationException | XPathExpressionException | SAXException exception) {
                session.setAttribute("message", "An error occurred : " + exception.getMessage());
                resp.sendRedirect(req.getContextPath() + "/login");
            }
        } else {
            resp.sendRedirect(req.getContextPath() + "/");
        }
    }
}
