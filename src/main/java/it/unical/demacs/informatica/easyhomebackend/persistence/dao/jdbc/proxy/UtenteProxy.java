package it.unical.demacs.informatica.easyhomebackend.persistence.dao.jdbc.proxy;

import it.unical.demacs.informatica.easyhomebackend.model.Immobile;
import it.unical.demacs.informatica.easyhomebackend.model.Messaggio;
import it.unical.demacs.informatica.easyhomebackend.model.UserRole;
import it.unical.demacs.informatica.easyhomebackend.model.Utente;
import it.unical.demacs.informatica.easyhomebackend.persistence.DBManager;

import java.util.List;

public class UtenteProxy extends Utente {

    public UtenteProxy(String username, String password, UserRole role, String firstName, String lastName, String birthdate, String province, String city, String country, String email, String phoneNumber, String address, String gender, String cap, String id) {
        super(username, password, role, firstName,lastName,birthdate,province,city,country,email,phoneNumber,address,gender,cap,id);
    }

    @Override
    public List<Immobile> getImmobili() {
        if(this.immobili==null){
            this.immobili = DBManager.getInstance().getImmobileDao().findByUserId(this.getUsername());
        }
        return this.immobili;
    }
}
