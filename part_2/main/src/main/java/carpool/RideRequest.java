package carpool;

import java.io.IOException;
import java.time.LocalDateTime;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.xpath.XPathExpressionException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import db.Database;

public class RideRequest extends Ride{
    private User passenger;

    public RideRequest(int id, String startPoint, LocalDateTime startDateTime, String endPoint, LocalDateTime endDateTime, User passenger, LocalDateTime deletionDateTime)
    {
        super(id, startPoint, startDateTime, endPoint, endDateTime, deletionDateTime);
        this.passenger = passenger;
    }

    public User getPassenger()
    {
        return this.passenger;
    }

    public static void create(Document document) {
        
    }

    @Override
    public Element toXML(Document document) throws ParserConfigurationException {
        Element rideRequestElt = super.toXML(document);
        Element passengerElt = document.createElement("passenger");
        document.renameNode(rideRequestElt, null, "ride_request");
        passengerElt.appendChild(document.createTextNode(String.valueOf(this.getPassenger().getId())));
        rideRequestElt.appendChild(passengerElt);
        return rideRequestElt;
    }

    public Entity fromXML(Node node) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'fromXML'");
    }

    public static void create(Document document, int passengerId, String startPoint, String endPoint, LocalDateTime startDateTime, LocalDateTime endDateTime) throws XPathExpressionException, TransformerException, SAXException, IOException
    {
        int latestId, newId;
        Node rideRequestsElement;
        Element rideRequestElement, idElement, endDateElement, passengerIdElement, startPointElement, endPointElement, startDateElement;
        latestId = Database.queryOneNumber(document, "/database/ride_requests/ride_request[position() = last()]/id/text()");
        newId = latestId + 1;
        rideRequestElement = document.createElement("ride_request");
        passengerIdElement = document.createElement("passenger_id");
        passengerIdElement.appendChild(document.createTextNode(String.valueOf(passengerId)));
        startPointElement = document.createElement("start_point");
        startPointElement.appendChild(document.createTextNode(startPoint.toLowerCase()));
        endPointElement = document.createElement("end_point");
        endPointElement.appendChild(document.createTextNode(endPoint.toLowerCase()));
        startDateElement = document.createElement("start_date");
        startDateElement.appendChild(document.createTextNode(startDateTime.toString()));
        idElement = document.createElement("id");
        idElement.appendChild(document.createTextNode(String.valueOf(newId)));
        endDateElement = document.createElement("end_date");
        endDateElement.appendChild(document.createTextNode(endDateTime.toString()));
        rideRequestsElement = document.getElementsByTagName("ride_requests").item(0);
        rideRequestElement.appendChild(idElement);
        rideRequestElement.appendChild(passengerIdElement);
        rideRequestElement.appendChild(startDateElement);
        rideRequestElement.appendChild(endPointElement);
        rideRequestElement.appendChild(startPointElement);
        rideRequestElement.appendChild(endDateElement);
        rideRequestsElement.appendChild(rideRequestElement);
        Database.getDatabase().write(document);
    }
}
