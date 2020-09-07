/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Controller.BookingDao;
import Controller.PatientDao;
import Domain.Booking;
import Domain.Patient;
import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author katy
 */
@ManagedBean
@SessionScoped
public class PatientModel implements Serializable{
    
    private Patient patient = new Patient();
    private List<Patient> patients= new PatientDao().allPatients();
    private boolean update;

    public PatientModel(){
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public List<Patient> getPatients() {
        return patients;
    }

    public void setPatients(List<Patient> patients) {
        this.patients = patients;
    }

    public boolean isUpdate() {
        return update;
    }

    public void setUpdate(boolean update) {
        this.update = update;
    }
  
    
    public String register(){
        String msg;
        
        if(update){
            msg = new PatientDao().updatePatient(patient);
            makeRegistration();
        }else{
            msg= new PatientDao().createPatient(patient);
            makeRegistration();
        }
        patients = new PatientDao().allPatients();
        FacesContext fc = FacesContext.getCurrentInstance();
        fc.addMessage("message", new FacesMessage(FacesMessage.SEVERITY_INFO, msg, ""));
        fc.addMessage("message", new FacesMessage(FacesMessage.SEVERITY_INFO, "Thanks for registration", ""));
        
        return "patientloginform.xhtml?faces-redirect=true";
    }
    
    public List<Booking> statusView(){
        return new BookingDao().patientHistory();
    }
    
  
    
    public void delete(Patient p){
        String msg = new PatientDao().deletePatient(p);
        patients = new PatientDao().allPatients();
        FacesContext fc = FacesContext.getCurrentInstance();
        fc.addMessage("message",new FacesMessage(FacesMessage.SEVERITY_INFO, msg, ""));
    }

  
    public void updateEnvironment(Patient p){
        update=true;
        patient =p;
    }
    
  public void makeRegistration(){
      update= false;
      patient=new Patient();
  }  
  public List<Patient> allPatient(){
      return new PatientDao().allPatients();
  }
}
