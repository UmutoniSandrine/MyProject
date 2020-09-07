/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Domain.Patient;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author katy
 */
public class PatientDao {
    public String createPatient(Patient p){
        Session s = NewHibernateUtil.getSessionFactory().openSession();
        s.beginTransaction();
        s.save(p);
        s.getTransaction().commit();
        s.close();
        return"Thanks For registaring";
    }
    public String deletePatient(Patient p) {
        Session s = NewHibernateUtil.getSessionFactory().openSession();
        s.beginTransaction();
        s.delete(p);
        s.getTransaction().commit();
        s.close();
        return "Patient deleted";
    }

    public String updatePatient(Patient p) {
        Session s = NewHibernateUtil.getSessionFactory().openSession();
        s.beginTransaction();
        s.update(p);
        s.getTransaction().commit();
        s.close();

        return "Patient Updated";

    }

    public List<Patient> allPatients(){
        Session s=NewHibernateUtil.getSessionFactory().openSession();
        Query q = s.createQuery("From Patient");
        List<Patient> li =q.list();
        s.close();
        return li;
    }

    public Patient findByUsername(String username) {
        Session s = NewHibernateUtil.getSessionFactory().openSession();
        Patient patient = (Patient) s.get(Patient.class, username);
        s.close();
        return patient;
    }
    
}
