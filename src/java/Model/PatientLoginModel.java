
package Model;


import Controller.PatientDao;
import Domain.Patient;
import Domain.Util;
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
public class PatientLoginModel{
    private String username;
    private String password;
    
    private Patient patient = new Patient();

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    
    
    
    
    
    public String signin(){
        patient = new PatientDao().findByUsername(username);
        System.out.println(patient.getFirstName());
        if(patient!=null && patient.getPassword().equals(password)){
          
            HttpSession session =(HttpSession)FacesContext.getCurrentInstance().
                    getExternalContext().getSession(false);
            session.setAttribute("aunthicatedUSer", patient);
            return"view";
        }else{
            FacesMessage msg = new FacesMessage("Invalid Username or Password");
            FacesContext.getCurrentInstance().addMessage(null, msg);
            return "patientloginform";
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
        return "patientloginform.xhtml?faces-redirect=true";
    
    }
    
     
}
     

