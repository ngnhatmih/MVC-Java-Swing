package com.swing_ams.ams.dao;

import java.util.ArrayList;
import java.util.List;

import com.swing_ams.ams.model.Apartment;
import com.swing_ams.ams.model.ApartmentXML;
import com.swing_ams.ams.utils.FileUtils;

public class ApartmentDao {
    private static final String APARTMENT_FILE_NAME = "apartment.xml";
    private List<Apartment> listApartments;

    public ApartmentDao() {
        this.listApartments = readListApartments();
    }

    public void writeListApartments(List<Apartment> apartments) {
        ApartmentXML xml = new ApartmentXML();
        xml.setApartments(apartments);
        FileUtils.writeXMLtoFile(APARTMENT_FILE_NAME, xml);
    }

    public List<Apartment> readListApartments() {
        List<Apartment> list = new ArrayList<>();
        ApartmentXML xml = (ApartmentXML) FileUtils.readXMLFile(APARTMENT_FILE_NAME, ApartmentXML.class);
        
        if (xml != null) {
            list = xml.getApartments();
        }
        return list;
    }

    public void add(Apartment apartment) {
        long id = (!listApartments.isEmpty()) ? (listApartments.get(listApartments.size() - 1).getId() + 1) : 1;
        apartment.setId(id);
        listApartments.add(apartment);
        writeListApartments(listApartments);
    }

    public void edit(Apartment apartment) {
        for (Apartment apt : listApartments) {
            if (apt.getId() == apartment.getId()) {
                apt.setBlock(apartment.getBlock());
                apt.setFloor(apartment.getFloor());
                apt.setService(apartment.getService());
                writeListApartments(listApartments);
                break;
            }
        }
    }

    public boolean delete(Apartment apartment) {
        boolean isFound = false;
        int size = listApartments.size();
        for (int i = 0; i < size; i++) {
            if (listApartments.get(i).getId() == apartment.getId()) {
                apartment = listApartments.get(i);
                isFound = true;
                break;
            }
        }
        if (isFound) {
            listApartments.remove(apartment);
            writeListApartments(listApartments);
            return true;
        }
        return false;
    }

    public List<Apartment> getListApartments() {
        return listApartments;
    }

    public void setListApartments(List<Apartment> listApartments) {
        this.listApartments = listApartments;
    }
}
