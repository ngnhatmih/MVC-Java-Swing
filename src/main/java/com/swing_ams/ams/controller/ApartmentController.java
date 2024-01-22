package com.swing_ams.ams.controller;

import com.swing_ams.ams.dao.ApartmentDao;
import com.swing_ams.ams.model.Apartment;
import com.swing_ams.ams.view.ApartmentView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class ApartmentController {
    private ApartmentDao apartmentDao;
    private ApartmentView apartmentView;

    public ApartmentController(ApartmentView view) {
        this.apartmentView = view;
        apartmentDao = new ApartmentDao();

        view.addAddBtnListener(new AddApartmentListener());
        view.addUpdateBtnListener(new EditApartmentListener());
        view.addDeleteBtnListener(new DeleteApartmentListener());
        view.addListApartmentSelectionListener(new ListStudentSelectionListener());
    }

    public void showApartmentView() {
        List<Apartment> apartmentList = apartmentDao.getListApartments();
        apartmentView.setVisible(true);
        apartmentView.showListApartments(apartmentList);
    }

    class AddApartmentListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            Apartment apartment = apartmentView.getApartmentInfo();
            if (apartment != null) {
                apartmentDao.add(apartment);
                apartmentView.showApartment(apartment);
                apartmentView.showListApartments(apartmentDao.getListApartments());
                apartmentView.showMessage("Thêm thành công!");
                apartmentView.clearApartmentInfo();
            }
        }
    }

    class EditApartmentListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            Apartment apartment = apartmentView.getApartmentInfo();
            if (apartment != null) {
                apartmentDao.edit(apartment);
                apartmentView.showApartment(apartment);
                apartmentView.showListApartments(apartmentDao.getListApartments());
                apartmentView.showMessage("Cập nhật thành công!");
                apartmentView.clearApartmentInfo();
            }
        }
    }

    class DeleteApartmentListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            Apartment apartment = apartmentView.getApartmentInfo();
            if (apartment != null) {
                apartmentDao.delete(apartment);
                apartmentView.clearApartmentInfo();
                apartmentView.showListApartments(apartmentDao.getListApartments());
                apartmentView.showMessage("Xóa thành công!");
            }
        }
    }

    class ClearApartmentListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            apartmentView.clearApartmentInfo();
        }
    }
    
    class ListStudentSelectionListener implements ListSelectionListener {
        public void valueChanged(ListSelectionEvent e) {
            apartmentView.fillApartmentFromSelectedRow();
        }
    }

}
