/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Domain.Dentist;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author katy
 */
public class DentistDao {
    public String CreateDentist(Dentist dentist){
        Session s = NewHibernateUtil.getSessionFactory().openSession();
        s.beginTransaction();
        s.save(dentist);
        s.getTransaction().commit();
        s.close();
        return "Done";
           
    }
    public String deleteDentist(Dentist dentist){
        Session s =NewHibernateUtil.getSessionFactory().openSession();
        s.beginTransaction();
        s.delete(dentist);
        s.getTransaction().commit();
        s.close();
        return"deleted";
        
    }  
    public String updateDentist(Dentist dentist){
        Session s = NewHibernateUtil.getSessionFactory().openSession();
        s.beginTransaction();
        s.update(dentist);
        s.getTransaction().commit();
        s.close();
        return "Updated";
    }
    
    public List<Dentist> allDentists(){
        Session s = NewHibernateUtil.getSessionFactory().openSession();
        Query q = s.createQuery("from Dentist");
        List<Dentist> li = q.list();
        s.close();
        return li;
        
    }
     public Dentist findByUsername(String username) {
        Session s = NewHibernateUtil.getSessionFactory().openSession();
        Dentist Dentist = (Dentist) s.get(Dentist.class, username);
        s.close();
        return Dentist;
    }
     

     
  
}
