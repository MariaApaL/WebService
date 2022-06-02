package edu.fpdual.webservices.service;


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

/*
    public boolean newGame(String name) throws SQLException, ClassNotFoundException{

        try(Connection con=userManager.getConnector().getMySQLConnection()){
            return userManager.updateNumGame(con, name);
        }


    }
*/
    public boolean deleteUser(UserDao user) throws SQLException, ClassNotFoundException {

        try (Connection con = userManager.getConnector().getMySQLConnection()) {
            return userManager.deleteUser(con,user);
        }
    }
/*
    public int numGame(String name) throws SQLException, ClassNotFoundException{
        try (Connection con = userManager.getConnector().getMySQLConnection()) {
            return userManager.numGame(con, name);
        }
    }

*/
    public boolean update( UserDao user) throws SQLException, ClassNotFoundException {
        try (Connection con = userManager.getConnector().getMySQLConnection()) {
            return userManager.update(con, user);
        }

    }
    public boolean validateUser(String name, String password) throws SQLException, ClassNotFoundException {
        try (Connection con = userManager.getConnector().getMySQLConnection()) {
            return userManager.validateUser(con, name,password);
        }

    }




}
