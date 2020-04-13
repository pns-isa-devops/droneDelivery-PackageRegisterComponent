package fr.unice.polytech.isa.dd;

import arquillian.AbstractPackageRegisterTest;
import cucumber.runtime.arquillian.CukeSpace;
import fr.unice.polytech.isa.dd.entities.Customer;
import fr.unice.polytech.isa.dd.entities.Package;
import fr.unice.polytech.isa.dd.entities.Provider;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.transaction.api.annotation.TransactionMode;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.junit.Test;
import org.junit.runner.RunWith;
import utils.MyDate;

import javax.ejb.EJB;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static org.junit.Assert.*;

@RunWith(Arquillian.class)
@Transactional(TransactionMode.COMMIT)
public class PackageRegisterBeanTest extends AbstractPackageRegisterTest {
    @PersistenceContext
    private EntityManager entityManager;

    @EJB(name = "package-stateless") private PackageRegistration packageRegistration;
    @EJB(name = "package-stateless") private PackageFinder packageFinder;
    @EJB(name = "provider-stateless") private ProviderRegistration providerRegistration;
    @EJB(name = "provider-stateless") private ProviderFinder providerFinder;


    @Test
    public void register() throws Exception {
        Provider pro1 = new Provider("Carrefour");
        providerRegistration.register("Carrefour");
        Provider pro = providerFinder.findByName("Carrefour");
        Package p = new Package("10", 5.0, "10/04/2020 11h48", pro);
        packageRegistration.register("10", 5.0, "10/04/2020 11h48", pro);
        int id = packageFinder.findById("10").getId();
        assertEquals(p.getSecret_number(), entityManager.find(Package.class, id).getSecret_number());

    }

    @Test
    public void findById() {

        //Provider pro1 = new Provider("Facebook");

        providerRegistration.register("Amazon");
        providerRegistration.register("Facebook");

        Provider pro = providerFinder.findByName("Amazon");
        Provider pro1 = providerFinder.findByName("Facebook");

        Package pp = new Package("NN10", 5.0, "13/04/2020 12h30", pro);

        packageRegistration.register("NN10", 5.0, "13/04/2020 12h30", pro);
        packageRegistration.register("P5", 5.0, "13/04/2020 16h30", pro1);

        Package p1 = packageFinder.findById("NN10");
        Package p2 = packageFinder.findById("P5");
        assertEquals(pp, p1);
        assertNotEquals(pp, p2);


    }
}

