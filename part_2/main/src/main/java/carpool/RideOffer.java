package carpool;

import java.util.Arrays;
import java.time.LocalDateTime;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class RideOffer extends Ride{
    private User driver;
    private List<User> passengers;
    private int nbSeats;

    public RideOffer(int id, String startPoint, LocalDateTime startLocalDateTimeTime, String endPoint, LocalDateTime endLocalDateTimeTime, User driver, User[] passengers, int nbSeats, LocalDateTime deletionLocalDateTimeTime)
    {
        super(id, startPoint, startLocalDateTimeTime, endPoint, endLocalDateTimeTime, deletionLocalDateTimeTime);
        this.driver = driver;
        this.passengers = Arrays.asList(passengers);
        // this.passengers = new ArrayList<User>();
        // Collections.addAll(this.passengers, passengers);
        this.nbSeats = nbSeats;
    }

    public RideOffer(int id, String startPoint, LocalDateTime startLocalDateTimeTime, String endPoint, LocalDateTime endLocalDateTimeTime, User driver, User[] passengers, int nbSeats)
    {
        this(id, startPoint, startLocalDateTimeTime, endPoint, endLocalDateTimeTime, driver, passengers, nbSeats, null);
    }

    public User getDriver()
    {
        return this.driver;
    }

    public List<User> getPassengers()
    {
        return this.passengers;
    }

    public int getNbSeats()
    {
        return this.nbSeats;
    }

    @Override
    public Element toXML(Document document) throws ParserConfigurationException {
        Element rideOfferElt = super.toXML(document);
        Element driverElt = document.createElement("driver");
        Element nbSeatsElt = document.createElement("nb_seat");
        Element passengersElt = document.createElement("passengers");
        Element passengerElt;
        document.renameNode(rideOfferElt, null, "ride_offer");
        driverElt.appendChild(document.createTextNode(String.valueOf(this.getDriver().getId())));
        rideOfferElt.appendChild(driverElt);
        nbSeatsElt.appendChild(document.createTextNode(String.valueOf(this.getNbSeats())));
        rideOfferElt.appendChild(nbSeatsElt);
        for(User passenger: this.getPassengers())
        {
            passengerElt = document.createElement("passenger_id");
            passengerElt.appendChild(document.createTextNode(String.valueOf(passenger.getId())));
            passengersElt.appendChild(passengerElt);
        }
        rideOfferElt.appendChild(passengersElt);
        return rideOfferElt;
    }

    public Entity fromXML(Node node) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'fromXML'");
    }
}
