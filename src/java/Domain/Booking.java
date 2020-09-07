/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Domain;

import java.util.Date;
import javax.annotation.PostConstruct;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;

/**
 *
 * @author katy
 */
@Entity
public class Booking {

    @ManyToOne
    private Patient patient;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int bookingId;
    private String reason;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date meetingTime;
    @Enumerated(EnumType.STRING)
    private Status status= Status.pending;
    @ManyToOne
    private Dentist dentist;

    @PostConstruct
    public void dentistInitialization(){
        dentist= new Dentist();
        patient=new Patient();
    }
    
    public Dentist getDentist() {
        return dentist;
    }

    public void setDentist(Dentist dentist) {
        this.dentist = dentist;
    }
    
    

    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    
    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Date getMeetingTime() {
        return meetingTime;
    }

    public void setMeetingTime(Date meetingTime) {
        this.meetingTime = meetingTime;

    }

    public Booking() {
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    } 

}
