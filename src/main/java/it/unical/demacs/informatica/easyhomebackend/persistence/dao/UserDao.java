package it.unical.demacs.informatica.easyhomebackend.persistence.dao;


import it.unical.demacs.informatica.easyhomebackend.model.UserRole;
import it.unical.demacs.informatica.easyhomebackend.model.Utente;
import it.unical.demacs.informatica.easyhomebackend.persistence.dto.UserRoleDto;

import java.util.List;

public interface UserDao {


    public Utente findByPrimaryKey(String username);

    public void save(Utente utente);
    public void delete(Utente utente) ;

    List<UserRoleDto> findAllUsernamesAndRoles();

    void changeUserRole(String username, UserRole newRole);
}