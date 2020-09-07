/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Domain.Booking;
import Domain.Dentist;
import Domain.Patient;
import java.util.List;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author katy
 */
public class BookingDao {
     public String createBooking(Booking b){
        Session s = NewHibernateUtil.getSessionFactory().openSession();
        s.beginTransaction();
        s.save(b);
        s.getTransaction().commit();
        s.close();
        return"Thanks ";
    }
    public String deleteBooking(Booking b) {
        Session s = NewHibernateUtil.getSessionFactory().openSession();
        s.beginTransaction();
        s.delete(b);
        s.getTransaction().commit();
        s.close();
        return " deleted";
    }

    public String updateBooking(Booking b) {
        Session s = NewHibernateUtil.getSessionFactory().openSession();
        s.beginTransaction();
        s.update(b);
        s.getTransaction().commit();
        s.close();

        return " Updated";

    }

    public List<Booking>bookingList() {
    
            Session s = NewHibernateUtil.getSessionFactory().openSession();
            Query q = s.createQuery("from Booking");
            List<Booking> li = q.list();
            s.close();
        return li;
    
    }
    
    public List<Booking>patientHistory() {
        HttpSession session =(HttpSession)FacesContext.getCurrentInstance().
                    getExternalContext().getSession(true);
            Patient patient = (Patient) session.getAttribute("aunthicatedUSer");
            String username= patient.getUsername();
            System.out.println("USERNAME:"+username);
            Session s = NewHibernateUtil.getSessionFactory().openSession();
            //SQLQuery sq = s.createSQLQuery("");
//            Query q1 = s.createSQLQuery("select * from Booking b where b.patient=:usernamee");
//            q1.setParameter("usernamee", username);
        List<Booking> li = (List<Booking>)s.createCriteria(Booking.class).add(Restrictions.eq("patient.username",username)).list();
                  s.close();
        return li;
    
    }
//    public List<Booking> dentistHistory(){
//        HttpSession session =(HttpSession) FacesContext.getCurrentInstance().
//                getExternalContext().getSession(true);
//        Dentist dentist = (Dentist) session.getAttribute("aunthicatedUSer");
//        String username  = dentist.getUsername();
//       // System.out.println("Username:"+username);
//        Session s = NewHibernateUtil.getSessionFactory().openSession();
//        List<Booking> li =(List<Booking>)s.createCriteria(Booking.class).
//                add(Restrictions.eq("dentist .username",username)).list();
//        s.close();
//        return li;
//    }

}
