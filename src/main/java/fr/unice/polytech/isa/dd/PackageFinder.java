package fr.unice.polytech.isa.dd;

import fr.unice.polytech.isa.dd.entities.Package;

import javax.ejb.Local;

@Local
public interface PackageFinder {

     Package findById(String id);
}
