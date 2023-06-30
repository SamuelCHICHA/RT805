package carpool;

import java.time.LocalDateTime;

import org.xml.sax.SAXException;
import java.io.IOException;
import java.util.Objects;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.xpath.XPathExpressionException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import db.Database;

public class User extends Entity {
    private String name;
    private String lastname;
    private String email;
    private boolean isAdmin;
    private String password;

    public User(int id, String name, String lastname, String email, String password, boolean isAdmin, LocalDateTime deletionDateTime)
    {
        super(id, deletionDateTime);
        this.name = name;
        this.lastname = lastname;
        this.email = email;
        this.isAdmin = isAdmin;
        this.password = password;
    }

    public User(int id, String name, String lastname, String email, String password, boolean isAdmin)
    {
        this(id, name, lastname, email, password, isAdmin, null);
    }

    public String getName()
    {
        return this.name;
    }

    public String getLastname()
    {
        return this.lastname;
    }

    public String getEmail()
    {
        return this.email;
    }

    public boolean isAdmin()
    {
        return this.isAdmin;
    }

    @Override
    public Element toXML(Document document) throws ParserConfigurationException
    {
        Element user = super.toXML(document);
        Element name = document.createElement("name");
        Element lastname = document.createElement("lastname");
        Element isAdmin = document.createElement("is_admin");
        Element email = document.createElement("email");
        Element password = document.createElement("password");
        document.renameNode(user, null, "user");
        name.appendChild(document.createTextNode(this.getName()));
        lastname.appendChild(document.createTextNode(this.getLastname()));
        email.appendChild(document.createTextNode(this.getEmail()));
        isAdmin.appendChild(document.createTextNode(this.isAdmin() ? "true" : "false"));
        password.appendChild(document.createTextNode(this.password));
        user.appendChild(name);
        user.appendChild(lastname);
        user.appendChild(email);
        user.appendChild(isAdmin);
        user.appendChild(password);
        return user;
    }

    public static User fromXML(Node node) throws ParserConfigurationException, XPathExpressionException
    {
        Element element = (Element) node;
        int id = Integer.parseInt(element.getElementsByTagName("id").item(0).getTextContent());
        String name = element.getElementsByTagName("name").item(0).getTextContent();
        String lastname = element.getElementsByTagName("lastname").item(0).getTextContent();
        String email = element.getElementsByTagName("email").item(0).getTextContent();
        String password = element.getElementsByTagName("password").item(0).getTextContent();
        boolean is_admin = Objects.equals(element.getElementsByTagName("is_admin").item(0).getTextContent(), "true");
        return new User(id, name, lastname, email, password, is_admin);
    }

    public static User authenticate(String email, String password) throws ParserConfigurationException, XPathExpressionException, SAXException, IOException
    {
        User user = null;
        Database database = Database.getDatabase();
        Document document = database.read();
        Node node = Database.queryOne(document, "/database/users/user[email=\"" + email + "\" and password=\"" + password + "\"]");
        if(node != null)
            user = User.fromXML(node);
        return user;
    }

    public static void register(Document document, String email, String password, String name, String lastname) throws XPathExpressionException, TransformerException, SAXException, IOException
    {
        int latestId, newId;
        Node users;
        Element userElement, idElement, isAdminElement, emailElement, passwordElement, nameElement, lastnameElement;
        latestId = Database.queryOneNumber(document, "/database/users/user[position() = last()]/id/text()");
        newId = latestId + 1;
        userElement = document.createElement("user");
        emailElement = document.createElement("email");
        emailElement.appendChild(document.createTextNode(email.toLowerCase()));
        passwordElement = document.createElement("password");
        passwordElement.appendChild(document.createTextNode(password));
        nameElement = document.createElement("name");
        nameElement.appendChild(document.createTextNode(name.toLowerCase()));
        lastnameElement = document.createElement("lastname");
        lastnameElement.appendChild(document.createTextNode(lastname.toLowerCase()));
        idElement = document.createElement("id");
        idElement.appendChild(document.createTextNode(String.valueOf(newId)));
        isAdminElement = document.createElement("is_admin");
        isAdminElement.appendChild(document.createTextNode("false"));
        users = document.getElementsByTagName("users").item(0);
        userElement.appendChild(idElement);
        userElement.appendChild(nameElement);
        userElement.appendChild(lastnameElement);
        userElement.appendChild(emailElement);
        userElement.appendChild(passwordElement);
        userElement.appendChild(isAdminElement);
        users.appendChild(userElement);
        Database.getDatabase().write(document);
    }
}
