package arquillian;


import fr.unice.polytech.isa.dd.PackageFinder;
import fr.unice.polytech.isa.dd.PackageRegisterBean;
import fr.unice.polytech.isa.dd.PackageRegistration;
import fr.unice.polytech.isa.dd.entities.Customer;
import fr.unice.polytech.isa.dd.entities.Database;
import fr.unice.polytech.isa.dd.entities.Delivery;
import fr.unice.polytech.isa.dd.entities.Package;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.ClassLoaderAsset;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.runner.RunWith;

import java.security.Provider;

import static org.junit.Assert.*;

@RunWith(Arquillian.class)
public abstract class AbstractPackageRegisterTest {
    @Deployment
    public static WebArchive createDeployment() {
        return ShrinkWrap.create(WebArchive.class)
                .addAsWebInfResource(EmptyAsset.INSTANCE,"beans.xml")
                .addPackage(PackageRegisterBean.class.getPackage())
                .addPackage(Package.class.getPackage())
                .addPackage(PackageFinder.class.getPackage())
                .addPackage(PackageRegistration.class.getPackage())
                .addPackage(Customer.class.getPackage())
                .addPackage(Provider.class.getPackage())
                .addAsManifestResource(new ClassLoaderAsset("META-INF/persistence.xml"), "persistence.xml")
                ;

    }

}
