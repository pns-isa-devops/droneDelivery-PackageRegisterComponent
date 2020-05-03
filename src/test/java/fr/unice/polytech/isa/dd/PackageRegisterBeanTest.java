package fr.unice.polytech.isa.dd;

import arquillian.AbstractPackageRegisterTest;
import cucumber.runtime.arquillian.CukeSpace;
import fr.unice.polytech.isa.dd.entities.Customer;
import fr.unice.polytech.isa.dd.entities.Package;
import fr.unice.polytech.isa.dd.entities.Provider;
import fr.unice.polytech.isa.dd.exceptions.AlreadyExistingPackageException;
import fr.unice.polytech.isa.dd.exceptions.UnknownPackageException;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.transaction.api.annotation.TransactionMode;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.jruby.util.Pack;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import utils.MyDate;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.*;

import static org.junit.Assert.*;

@RunWith(Arquillian.class)
@Transactional(TransactionMode.COMMIT)
public class PackageRegisterBeanTest extends AbstractPackageRegisterTest {
    @PersistenceContext
    private EntityManager entityManager;
    @Inject
    UserTransaction userTransaction;

    @EJB(name = "package-stateless") private PackageRegistration packageRegistration;
    @EJB(name = "package-stateless") private PackageFinder packageFinder;

    private Provider provider1 = new Provider("Carrefour");
    private Provider provider2 = new Provider("Amazon");
    private Provider provider3 = new Provider("Facebook");
    private Package aPackage1 = new Package();
    private Package aPackage2 = new Package("NN10", 5.0, "13/04/2020 12h30", provider2);
    private Package aPackage3 = new Package("P5", 5.0, "13/04/2020 16h30", provider3);

    @Before
    public void setUp(){
        entityManager.persist(provider1);
        entityManager.persist(provider2);
        entityManager.persist(provider3);

        aPackage1.setSecret_number("10");
    }
    @After
    public void cleanUp() throws SystemException, NotSupportedException, HeuristicRollbackException, HeuristicMixedException, RollbackException {
        userTransaction.begin();

        provider1 = entityManager.merge(provider1);
        entityManager.remove(provider1);
        provider2 = entityManager.merge(provider2);
        entityManager.remove(provider2);
        provider3 = entityManager.merge(provider3);
        entityManager.remove(provider3);

        aPackage2 = entityManager.merge(aPackage2);
        entityManager.remove(aPackage2);
        aPackage3 = entityManager.merge(aPackage3);
        entityManager.remove(aPackage3);

        userTransaction.commit();
    }


    @Test
    public void registerTest() throws Exception {
        packageRegistration.register("10", 5.0, "10/04/2020 11h48", "Carrefour");
        int id = entityManager.find(Package.class,aPackage1.getId()).getId();
        assertEquals(aPackage1.getSecret_number(), entityManager.find(Package.class, id).getSecret_number());

    }

    @Test(expected = AlreadyExistingPackageException.class)
    public void registerExceptionTest() throws AlreadyExistingPackageException, UnknownPackageException {
        packageRegistration.register("10", 15.0, "15/04/2020 11h48", "Zara");
        aPackage1 = packageFinder.findPackageBySecretNumber("10");
        aPackage1 = entityManager.find(Package.class, aPackage1.getId());
        entityManager.remove(aPackage1);
    }

    @Test
    public void findBySecretNumberTest() throws UnknownPackageException {
        entityManager.persist(aPackage2);
        entityManager.persist(aPackage3);

        Package package1 = packageFinder.findPackageBySecretNumber("NN10");
        Package package2 = packageFinder.findPackageBySecretNumber("P5");
        assertEquals(aPackage2, package1);
        assertNotEquals(aPackage2, package2);
    }

    @Test(expected = UnknownPackageException.class)
    public void dosentFoundPackageTest() throws UnknownPackageException{
        packageFinder.findPackageBySecretNumber("ABC");
    }
}

