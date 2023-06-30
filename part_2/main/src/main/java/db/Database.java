package db;

import java.io.File;
import java.io.IOException;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class Database {
    private static final String path = "db.xml";
    private static final String schemaPath = "db.xml";
    private static final String printConfigPath = "print.xslt";
    private static Database database;
    private File file;
    private File schemaFile;
    private File printConfigFile;

    private Database(){
        this.file = new File(Database.path);
        this.schemaFile = new File(Database.schemaPath);
        this.printConfigFile = new File(Database.printConfigPath);
    }

    public static Database getDatabase(){
        if(Database.database == null)
            Database.database = new Database();
        return Database.database;
    }

    public void write(Document document) throws TransformerException, SAXException, IOException
    {
        // SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        TransformerFactory factory = TransformerFactory.newInstance();
        Transformer transformer = factory.newTransformer(new StreamSource(this.printConfigFile));
        DOMSource source = new DOMSource(document);
        StreamResult result = new StreamResult(this.file);
        // Schema schema = schemaFactory.newSchema(this.schemaFile);
        // Validator validator = schema.newValidator();
        // validator.validate(source);
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
        transformer.transform(source, result);
    }

    public Document read() throws ParserConfigurationException, SAXException, IOException
    {
        // Parse the XML as a DOM Document.
        DocumentBuilder builder;
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setIgnoringComments(true);
        factory.setIgnoringElementContentWhitespace(true);
        builder = factory.newDocumentBuilder();
        return builder.parse(this.file);
    }

    public static NodeList queryAll(Document document, String query) throws XPathExpressionException
    {
        XPathFactory xPathfactory = XPathFactory.newInstance();
        XPath xpath = xPathfactory.newXPath();
        XPathExpression expr = xpath.compile(query);
        return (NodeList)expr.evaluate(document, XPathConstants.NODESET);
    }

    public static Node queryOne(Document document, String query) throws XPathExpressionException
    {
        XPathFactory xPathfactory = XPathFactory.newInstance();
        XPath xpath = xPathfactory.newXPath();
        XPathExpression expr = xpath.compile(query);
        return (Node)expr.evaluate(document, XPathConstants.NODE);
    }

    public static int queryOneNumber(Document document, String query) throws XPathExpressionException
    {
        XPathFactory xPathfactory = XPathFactory.newInstance();
        XPath xpath = xPathfactory.newXPath();
        XPathExpression expr = xpath.compile(query);
        return ((Double)expr.evaluate(document, XPathConstants.NUMBER)).intValue();
    }
}
