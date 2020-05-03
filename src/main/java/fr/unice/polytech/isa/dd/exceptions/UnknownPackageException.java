package fr.unice.polytech.isa.dd.exceptions;

import javax.xml.ws.WebFault;
import java.io.Serializable;

@WebFault(targetNamespace = "http://www.polytech.unice.fr/si/4a/isa/dd/packageService")
public class UnknownPackageException extends Exception implements Serializable {

    private String secret_number_not_found;

    public UnknownPackageException(String secret_number){
        super("This package : " +secret_number+ " doesn't exist");
        this.secret_number_not_found = secret_number;
    }

    public String getSecret_number_not_found() {
        return secret_number_not_found;
    }

    public void setSecret_number_not_found(String secret_number_not_found) {
        this.secret_number_not_found = secret_number_not_found;
    }
}
