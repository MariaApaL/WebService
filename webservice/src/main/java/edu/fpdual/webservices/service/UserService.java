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

    public boolean findUser(String name) throws SQLException, ClassNotFoundException {
        try (Connection con = userManager.getConnector().getMySQLConnection()) {

            return userManager.findUser(con, name);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }
        }



    public int insertUserReg(String username, String password, String mail) throws SQLException,ClassNotFoundException{
        try (Connection con = userManager.getConnector().getMySQLConnection()) {
            return userManager.insertUser(con, username, password, mail);


        }

    }


    public boolean newGame(String name) throws SQLException, ClassNotFoundException{

        try(Connection con=userManager.getConnector().getMySQLConnection()){
            return userManager.updateNumGame(con, name);
        }


    }

    public boolean deleteUser(String name) throws SQLException, ClassNotFoundException {

        try (Connection con = userManager.getConnector().getMySQLConnection()) {
            return userManager.deleteUser(con,name);
        }
    }

    public List ranking() throws SQLException, ClassNotFoundException{
        try (Connection con = userManager.getConnector().getMySQLConnection()) {
            return userManager.ranking(con);
        }
    }

    public int numGame(String name) throws SQLException, ClassNotFoundException{
        try (Connection con = userManager.getConnector().getMySQLConnection()) {
            return userManager.numGame(con, name);
        }
    }


    public boolean updatePassword( String contraseña, String name) throws SQLException, ClassNotFoundException {
        try (Connection con = userManager.getConnector().getMySQLConnection()) {
            return userManager.updatePassword(con, contraseña, name);
        }

    }
    public boolean validateUser( UserDao user) throws SQLException, ClassNotFoundException {
        try (Connection con = userManager.getConnector().getMySQLConnection()) {
            return userManager.validateUser(con, user.getPassword(),user.getPlayer_name());
        }

    }

    public String getMail(String name) throws SQLException, ClassNotFoundException {
        try (Connection con = userManager.getConnector().getMySQLConnection()) {
            return userManager.getMail(con, name);
        }

    }




}
