package fr.unice.polytech.isa.dd;

import fr.unice.polytech.isa.dd.entities.Provider;
import org.joda.time.DateTime;
import utils.MyDate;


import javax.ejb.Local;

@Local
public interface PackageRegistration {

     Boolean register(int id, Double w, MyDate d, Provider pro);
}
