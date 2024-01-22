package main.java.com.swing_ams.ams.dao;

import com.swing_ams.ams.model.Apartment;
import com.swing_ams.ams.model.ApartmentXML;
import com.swing_ams.ams.utils.FileUtils;
import java.util.List;

/**
 *
 * @author ngnhatmih
 */
public class ApartmentDao {
    private static final String FILE_NAME = "apartment.xml";
    private List<Apartment> apartments;
    
    public List<Apartment> getApartments()
    {
        return apartments;
    }
    
    public void loadApartments()
    {
        ApartmentXML xml = (ApartmentXML) FileUtils.readXMLFile(FILE_NAME, ApartmentXML.class);
        
        if (xml != null)
            apartments = xml.getApartments();
    }
    
    public void writeApartments()
    {
        ApartmentXML xml = new ApartmentXML();
        xml.setApartments(apartments);
        FileUtils.writeXMLtoFile(FILE_NAME, xml);
    }
    
    public void addApartment(Apartment ap)
    {
        ap.setId(apartments.size() + 1);
        apartments.add(ap);
        writeApartments();
    }
    
    public void editApartment(Apartment ap)
    {
        
    }
    
    public void deleteApartment(Apartment ap)
    {
        
    }
}