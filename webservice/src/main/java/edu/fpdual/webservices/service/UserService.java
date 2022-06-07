package edu.fpdual.webservices.service;


import edu.fpdual.webservices.model.conector.MySQLConnector;
import edu.fpdual.webservices.model.dao.UserDao;
import edu.fpdual.webservices.model.manager.UserManager;


import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class UserService {

    private final UserManager userManager;


    public UserService(UserManager userManager) {
        this.userManager = userManager;
    }


    public UserDao findByName(String name) throws SQLException, ClassNotFoundException {
        try (Connection con = userManager.getConnector().getMySQLConnection()) {

            return userManager.findByName(con, name);

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
        }



    public int insert(UserDao user) throws SQLException,ClassNotFoundException{
        try (Connection con = userManager.getConnector().getMySQLConnection()) {
            return userManager.insert(con,user);


        }

    }


    public UserDao findById(Integer id) throws SQLException, ClassNotFoundException {
        try (Connection con = userManager.getConnector().getMySQLConnection()) {
            return userManager.findById(con, id);
        }
    }

    public boolean delete(UserDao user) throws SQLException, ClassNotFoundException {

        try (Connection con = userManager.getConnector().getMySQLConnection()) {
            return userManager.delete(con,user);
        }
    }

    public boolean update( UserDao user) throws SQLException, ClassNotFoundException {
        try (Connection con = userManager.getConnector().getMySQLConnection()) {
            return userManager.update(con, user);
        }

    }
    public boolean validate(Integer id ) throws SQLException, ClassNotFoundException {
        try (Connection con = userManager.getConnector().getMySQLConnection()) {
            return userManager.validate(con, id);
        }

    }




}
