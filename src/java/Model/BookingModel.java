/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Controller.BookingDao;
import Controller.bookDentist;
import Domain.Booking;
import Domain.Dentist;
import Domain.Patient;
import Domain.Status;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 *
 * @author katy
 */
@ManagedBean
@SessionScoped
public class BookingModel implements Serializable {

    private Booking booking;
    private Dentist dent;
    private boolean update = false;
    private List<Booking> bookings = new BookingDao().bookingList();
    private List<Booking> bookings1= new bookDentist().dentistHistory();

    @PostConstruct
    public void init(){
        booking=new Booking();
        dent=new Dentist();
    }
    public BookingModel() {
 
    }
    public void acceptAppointment(Booking bl){
        bl.setStatus(Status.approved);
        new BookingDao().updateBooking(bl);
        
    }
    public void deniedAppointment(Booking bl){
        bl.setStatus(Status.denied);
        new BookingDao().updateBooking(bl);
    }

    public List<Booking> getBookings1() {
        return bookings1;
    }

    public void setBookings1(List<Booking> bookings1) {
        this.bookings1 = bookings1;
    }

    
    public Dentist getDent() {
        return dent;
    }

    public void setDent(Dentist dent) {
        this.dent = dent;
    }
    
    

    public Booking getBooking() {
        return booking;
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
    }

    public boolean isUpdate() {
        return update;
    }

    public void setUpdate(boolean update) {
        this.update = update;
    }

    public List<Booking> getBookings() {
        return new BookingDao().bookingList();
    }

    public void setBookings(List<Booking> bookings) {
        this.bookings = bookings;
    }
    
    

    public void makeAppointment(Patient pp) {
        
        String msg = "";
        if (update) {
            booking.setDentist(dent);
            msg = new BookingDao().updateBooking(booking);
            makeAppointmentEnv();
        } else {

            booking.setPatient(pp);
            booking.setDentist(dent);

            msg = new BookingDao().createBooking(booking);
            makeAppointmentEnv();

        }
        bookings = new BookingDao().bookingList();
        FacesContext fc = FacesContext.getCurrentInstance();
        fc.addMessage("message", new FacesMessage(FacesMessage.SEVERITY_INFO, msg, ""));

    }


    public void UpdateAppoointment(Booking b) {
        update = true;
        b = booking;
    }

    public void makeAppointmentEnv() {
        update = false;
        booking = new Booking();
    }

}
