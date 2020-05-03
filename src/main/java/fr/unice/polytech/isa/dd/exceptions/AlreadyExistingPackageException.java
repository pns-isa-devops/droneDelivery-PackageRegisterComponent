package fr.unice.polytech.isa.dd.exceptions;

import javax.xml.ws.WebFault;
import java.io.Serializable;

@WebFault(targetNamespace = "http://www.polytech.unice.fr/si/4a/isa/dd/packageService")
public class AlreadyExistingPackageException extends Exception implements Serializable {

    private String conflic_secret_number;
    private String conflic_provider_name;

    public AlreadyExistingPackageException(String secret_number, String provider_name){
        super("This package : "+ secret_number + " from " + provider_name + "already exists");
        this.conflic_provider_name = secret_number;
        this.conflic_secret_number = provider_name;
    }

    public String getConflic_provider_name() {
        return conflic_provider_name;
    }

    public String getConflic_secret_number() {
        return conflic_secret_number;
    }

    public void setConflic_provider_name(String conflic_provider_name) {
        this.conflic_provider_name = conflic_provider_name;
    }

    public void setConflic_secret_number(String conflic_secret_number) {
        this.conflic_secret_number = conflic_secret_number;
    }

}
