package carpool;

import java.time.LocalDateTime;

import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public abstract class Entity
{
    private int id;
    private LocalDateTime deletionDateTime;

    public Entity(int id, LocalDateTime deletionDateTime)
    {
        this.id = id;
        this.deletionDateTime = deletionDateTime;
    }

    public void delete()
    {
        this.deletionDateTime = LocalDateTime.now();
    }

    public Element toXML(Document document) throws ParserConfigurationException
    {
        Element element = document.createElement("root");
        Element idElt = document.createElement("id");
        Element deletionDateElt;
        idElt.appendChild(document.createTextNode(String.valueOf(this.getId())));
        if(this.isDeleted())
        {
            deletionDateElt = document.createElement("deletion_date");
            deletionDateElt.appendChild(document.createTextNode(this.getDeletionDateTime().toString()));
            element.appendChild(deletionDateElt);
        }
        element.appendChild(idElt);
        document.appendChild(element);
        return element;
    }

    public int getId()
    {
        return this.id;
    }

    public LocalDateTime getDeletionDateTime()
    {
        return this.deletionDateTime;
    }

    public boolean isDeleted()
    {
        return this.deletionDateTime != null;
    }
}