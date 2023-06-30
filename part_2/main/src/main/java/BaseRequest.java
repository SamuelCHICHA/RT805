import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;

import org.xml.sax.SAXException;

import carpool.User;

public class BaseRequest extends HttpServletRequestWrapper
{

    public BaseRequest(HttpServletRequest request) {
        super(request);
    }

    @Override
    public void login(String username, String password) throws ServletException
    {
        User user;
        try {
            user = User.authenticate(username, password);
            if(user != null)
            {
                this.getSession().setAttribute("user_id", user.getId());
            }
        } catch (XPathExpressionException | ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
    }
}