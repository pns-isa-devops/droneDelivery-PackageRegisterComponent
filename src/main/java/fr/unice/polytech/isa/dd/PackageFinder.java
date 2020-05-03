package fr.unice.polytech.isa.dd;

import fr.unice.polytech.isa.dd.entities.Package;
import fr.unice.polytech.isa.dd.exceptions.UnknownPackageException;

import javax.ejb.Local;

@Local
public interface PackageFinder {

     Package findPackageBySecretNumber(String secret_number) throws UnknownPackageException;
}
