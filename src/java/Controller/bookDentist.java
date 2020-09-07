/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Domain.Booking;
import Domain.Dentist;
import java.util.ArrayList;
import java.util.List;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 *
 * @author bitwayiki
 */
public class bookDentist {
    
     public List dentistHistory(){
        List<Booking> bookings = new BookingDao().bookingList();
         System.out.println("##"+bookings.size());
        List<Booking> bk = new ArrayList<>();
        HttpSession session =(HttpSession) FacesContext.getCurrentInstance().
                getExternalContext().getSession(true);
        Dentist dentist = (Dentist) session.getAttribute("aunthicatedDentist");
        String f = dentist.getFirstName();
        String l =dentist.getLastName();
        for (Booking booking1 : bookings) {
            if(booking1.getDentist().getFirstName().equalsIgnoreCase(f) && 
                    booking1.getDentist().getLastName().equalsIgnoreCase(l)){
                bk.add(booking1);
            }
            
            
        }
        return bk;
    
}
    
        
//    }
    
}
