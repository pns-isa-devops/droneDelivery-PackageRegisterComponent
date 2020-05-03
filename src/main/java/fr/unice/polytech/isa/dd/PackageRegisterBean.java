package fr.unice.polytech.isa.dd;

import fr.unice.polytech.isa.dd.entities.Package;
import fr.unice.polytech.isa.dd.entities.Provider;
import fr.unice.polytech.isa.dd.exceptions.AlreadyExistingPackageException;
import fr.unice.polytech.isa.dd.exceptions.UnknownPackageException;
import org.joda.time.DateTime;
import utils.MyDate;

import javax.ejb.EJB;
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
    @EJB private ProviderFinder providerFinder;

    @Override
    public Boolean register(String secret_number, Double w, String dateTime, String provider_name) throws AlreadyExistingPackageException {
        Optional<Package> aPackage = findPackageBySecretNNumberInDatabase(secret_number);
        if(aPackage.isPresent()) throw new AlreadyExistingPackageException(secret_number,provider_name);
        Provider provider_given = providerFinder.findByName(provider_name);
        Package new_package = new Package(secret_number, w, dateTime, provider_given);
        entityManager.persist(new_package);
        return true;
    }

    @Override
    public Package findPackageBySecretNumber(String secret_number) throws UnknownPackageException {
        Optional<Package> packageBySecretNumber = findPackageBySecretNNumberInDatabase(secret_number);
        if(packageBySecretNumber.isPresent()){
            return packageBySecretNumber.get();
        }else throw new UnknownPackageException(secret_number);
    }

    public Optional<Package> findPackageBySecretNNumberInDatabase(String secret_number) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Package> criteria = builder.createQuery(Package.class);
        Root<Package> root =  criteria.from(Package.class);
        criteria.select(root).where(builder.equal(root.get("secretNumber"), secret_number));
        TypedQuery<Package> query = entityManager.createQuery(criteria);
        try {
            return Optional.of(query.getSingleResult());
        } catch (NoResultException nre){
            return Optional.empty();
        }
    }
}
