package edu.fpdual.webservices.model.manager;

import edu.fpdual.webservices.model.conector.MySQLConnector;
import edu.fpdual.webservices.model.dao.UserDao;

import java.sql.Connection;
import java.sql.SQLException;

public interface UserManager extends Manager<UserDao,Integer> {

    /**
     * Finds all the entities in the DB based on a list of ids.
     *
     * @param con DB connection
     * @param player_name Entities player_name to search for.
     * @return a {@link UserDao}
     */
     UserDao findUserByName(Connection con, String player_name)  throws SQLException, ClassNotFoundException;

}
