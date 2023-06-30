import java.io.IOException;
import java.time.LocalDateTime;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.xpath.XPathExpressionException;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import carpool.User;
import db.Database;

public class RideRequest extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        if(req.getSession().getAttribute("id") != null)
            req.getRequestDispatcher("ride_request.jsp").forward(req, resp);
        else
            resp.sendRedirect(req.getContextPath() + "/login");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Database database;
        Document document;
        LocalDateTime startDate = req.getParameter("start_date"), endDate = LocalDateTime. req.getParameter("end_date");
        String startPoint = req.getParameter("start_point"), endPoint = req.getParameter("end_point");
        if(req.getSession().getAttribute("id") != null)
        {
            database = Database.getDatabase();
            try {
                document = database.read();
                carpool.RideRequest.create(document, req.getSession().getAttribute("id"), , getServletInfo(), null, null);
                database.write(document);
            } catch (ParserConfigurationException | SAXException | TransformerException | XPathExpressionException e) {
                e.printStackTrace();
            }
        }
        else
        {
            resp.sendRedirect(req.getContextPath() + "/login");
        }
    }
}
