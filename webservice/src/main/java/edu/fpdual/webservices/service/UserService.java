package edu.fpdual.webservices.service;

import edu.fpdual.webservices.model.conector.MySQLConnector;
import edu.fpdual.webservices.model.dao.UserDao;
import edu.fpdual.webservices.model.manager.UserManager;
import edu.fpdual.webservices.model.manager.impl.UserManagerImpl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class UserService {

    private final UserManager userManager;

    public UserService(UserManagerImpl userManager){

        this.userManager = userManager;
    }



    public List<UserDao> findAll() throws SQLException, ClassNotFoundException {
        try (Connection con = new MySQLConnector().getMySQLConnection()) {
            return userManager.findAll(con);
        }
    }


    public boolean delete(Integer id) throws SQLException, ClassNotFoundException {
        try (Connection con = new MySQLConnector().getMySQLConnection()) {
            return userManager.delete(con, id);
        }
    }

    public int register(UserDao user) throws SQLException, ClassNotFoundException {
        try (Connection con = new MySQLConnector().getMySQLConnection()) {
            return userManager.register(con, user);
        }
    }

    public boolean update(UserDao user) throws SQLException, ClassNotFoundException {
        try (Connection con = new MySQLConnector().getMySQLConnection()) {
            return userManager.update(con, user);
        }
    }

    public UserDao findUserByName (String player_name) throws SQLException, ClassNotFoundException {
        try (Connection con = new MySQLConnector().getMySQLConnection()) {
            return userManager.findUserByName(con, player_name);
        }

    }

    public UserDao findById (Integer idplayer)  throws SQLException, ClassNotFoundException{
        try (Connection con = new MySQLConnector().getMySQLConnection()) {
            return userManager.findById(con, idplayer);
        }

    }
}
