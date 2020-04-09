package fr.unice.polytech.isa.dd;

import fr.unice.polytech.isa.dd.entities.Provider;
import org.joda.time.DateTime;


import javax.ejb.Local;

@Local
public interface PackageRegistration {

     void register(int id, Double w, DateTime d, Provider pro);
}
