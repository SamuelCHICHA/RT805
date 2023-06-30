package carpool;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.ParserConfigurationException;
import java.time.LocalDateTime;

public abstract class Ride extends Entity {
    private String startPoint;
    private LocalDateTime startDateTime;
    private String endPoint;
    private LocalDateTime endDateTime;

    public Ride(int id, String startPoint, LocalDateTime startDateTime, String endPoint, LocalDateTime endDateTime, LocalDateTime deletionDateTime)
    {
        super(id, deletionDateTime);
        this.startPoint = startPoint;
        this.startDateTime = startDateTime;
        this.endPoint = endPoint;
        this.endDateTime = endDateTime;
    }

    public String getStartPoint()
    {
        return this.startPoint;
    }

    public LocalDateTime getStartDateTime()
    {
        return this.startDateTime;
    }

    public String getEndPoint()
    {
        return this.endPoint;
    }

    public LocalDateTime getEndDateTime()
    {
        return this.endDateTime;
    }

    public Element toXML(Document document) throws ParserConfigurationException
    {
        Element element = super.toXML(document);
        Element startPointElt = document.createElement("start_point");
        Element endPointElt = document.createElement("end_point");
        Element startDateElt = document.createElement("start_date");
        Element endDateElt = document.createElement("end_date");
        startPointElt.appendChild(document.createTextNode(this.getStartPoint()));
        endPointElt.appendChild(document.createTextNode(this.getEndPoint()));
        startDateElt.appendChild(document.createTextNode(this.getStartDateTime().toString()));
        endDateElt.appendChild(document.createTextNode(this.getEndDateTime().toString()));
        element.appendChild(startPointElt);
        element.appendChild(endPointElt);
        element.appendChild(startDateElt);
        element.appendChild(endDateElt);
        return element;
    }
}
