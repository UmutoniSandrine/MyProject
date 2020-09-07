/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Controller.BookingDao;
import Controller.DentistDao;
import Domain.Booking;
import Domain.Dentist;
import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author katy
 */
@ManagedBean
@SessionScoped
public class DentistsModel implements Serializable{
     private Dentist dent = new Dentist();
     private List<Dentist> dentists = new DentistDao().allDentists();
     private boolean update;

    public Dentist getDent() {
        return dent;
    }

    public void setDent(Dentist dent) {
        this.dent = dent;
    }

    public List<Dentist> allDentists() {
        return new DentistDao().allDentists();
    }

    public void setDentists(List<Dentist> dentists) {
        this.dentists = dentists;
    }
    public String registerDentist(){
        String msg ="";
        if(update){
            msg = new DentistDao().updateDentist(dent);
            saveDentists();
        }else{
            msg = new DentistDao().CreateDentist(dent);
            saveDentists();
        }
        dentists= new DentistDao().allDentists();
        FacesContext fc = FacesContext.getCurrentInstance();
        fc.addMessage("message", new FacesMessage(FacesMessage.SEVERITY_INFO, msg,""));
        return "dentistLoginForm.xhtml?faces-redirect=true";
    }
    
  
    
    
    public void delete(Dentist dentist){
        String msg = new DentistDao().deleteDentist(dentist);
        dentists = new DentistDao().allDentists();
        FacesContext fc = FacesContext.getCurrentInstance();
        fc.addMessage("message",new FacesMessage(FacesMessage.SEVERITY_INFO, msg, ""));
    }
    
    public void updateDentistsEnv(Dentist dentist){
        update = true;
        dent = dentist;
    }
    public void saveDentists(){
        update = false;
        dent = new Dentist();
    }

    public boolean isUpdate() {
        return update;
    }

    public void setUpdate(boolean update) {
        this.update = update;
    }
     
    
}
