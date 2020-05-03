package fr.unice.polytech.isa.dd;

import fr.unice.polytech.isa.dd.entities.Provider;
import fr.unice.polytech.isa.dd.exceptions.AlreadyExistingPackageException;
import fr.unice.polytech.isa.dd.exceptions.UnknownProviderException;
import org.joda.time.DateTime;
import utils.MyDate;


import javax.ejb.Local;

@Local
public interface PackageRegistration {

     Boolean register(String secret_number, Double weight, String delivery_date, String provider_name) throws AlreadyExistingPackageException, UnknownProviderException;
}
