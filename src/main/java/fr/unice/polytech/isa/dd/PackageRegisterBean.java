package fr.unice.polytech.isa.dd;

import fr.unice.polytech.isa.dd.entities.Package;
import fr.unice.polytech.isa.dd.entities.Provider;
import org.joda.time.DateTime;
import utils.MyDate;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.Optional;
import java.util.Objects;



@Stateless(name = "package-stateless")
public class PackageRegisterBean implements PackageRegistration, PackageFinder {

    @PersistenceContext private EntityManager entityManager;

    @Override
    public Boolean register(String id, Double w, String dateTime, Provider provider) {
        Optional<Package> p = findPackageById(id);
        if(p.isPresent()) return false;
        Package new_package = new Package(id, w, dateTime, provider);
        entityManager.persist(new_package);
        return true;
    }


    @Override
    public Package findById(String id) {
        Optional<Package> packageById = findPackageById(id);
        return packageById.orElse(null);
    }

    public Optional<Package> findPackageById(String id) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Package> criteria = builder.createQuery(Package.class);
        Root<Package> root =  criteria.from(Package.class);
        criteria.select(root).where(builder.equal(root.get("secretNumber"), id));
        TypedQuery<Package> query = entityManager.createQuery(criteria);
        try {
            return Optional.of(query.getSingleResult());
        } catch (NoResultException nre){
            return Optional.empty();
        }
    }
}
