/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import dataaccess.RoleDB;
import models.Role;

/**
 *
 * @author lynnh
 */
public class RoleService {

    public Role getRole(int roleID) throws Exception {
        RoleDB roleDB = new RoleDB();
        Role role = roleDB.getRole(roleID);
        return role;

    }

}
