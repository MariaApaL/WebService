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


    public UserDao findUser(String name) throws SQLException, ClassNotFoundException {
        try (Connection con = userManager.getConnector().getMySQLConnection()) {

            return userManager.findUser(con, name);

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
        }



    public int insertUserReg(UserDao user) throws SQLException,ClassNotFoundException{
        try (Connection con = userManager.getConnector().getMySQLConnection()) {
            return userManager.insertUser(con,user);


        }

    }


    public UserDao findById(Integer id) throws SQLException, ClassNotFoundException {
        try (Connection con = userManager.getConnector().getMySQLConnection()) {
            return userManager.findById(con, id);
        }
    }

    public boolean deleteUser(UserDao user) throws SQLException, ClassNotFoundException {

        try (Connection con = userManager.getConnector().getMySQLConnection()) {
            return userManager.deleteUser(con,user);
        }
    }

    public boolean update( UserDao user) throws SQLException, ClassNotFoundException {
        try (Connection con = userManager.getConnector().getMySQLConnection()) {
            return userManager.update(con, user);
        }

    }
    public boolean validateUser(Integer id ) throws SQLException, ClassNotFoundException {
        try (Connection con = userManager.getConnector().getMySQLConnection()) {
            return userManager.validateUser(con, id);
        }

    }




}
