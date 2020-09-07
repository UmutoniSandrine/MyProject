/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;


import Controller.DentistDao;
import Domain.Dentist;
import Domain.Util;
import java.io.Serializable;
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
public class DentistloginModel implements Serializable{
    private String username;
    private String password;
    
    private Dentist dentist = new Dentist();

    public Dentist getDent() {
        return dentist;
    }

    public void setDent(Dentist dentist) {
        this.dentist = dentist;
    }
     public String signin(){
        dentist = new DentistDao().findByUsername(username);
        System.out.println("##**"+dentist.getFirstName());
        if(dentist!=null && dentist.getPassword().equals(password)){
          
            HttpSession session =(HttpSession)FacesContext.getCurrentInstance().
                    getExternalContext().getSession(false);
            session.setAttribute("aunthicatedDentist", dentist);
            return"appointmentLists";
        }else{
            FacesMessage msg = new FacesMessage("Invalid Username or Password");
            FacesContext.getCurrentInstance().addMessage(null, msg);
            return "dentistLoginForm";
        }
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public String logout(){
        HttpSession session = Util.getSession();
        session.invalidate();
        return "dentistLoginForm.xhtml?faces-redirect=true";
    
    }
    
    
}
