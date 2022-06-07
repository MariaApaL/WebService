package edu.fpdual.webservices.model.manager;

import edu.fpdual.webservices.model.conector.MySQLConnector;
import edu.fpdual.webservices.model.dao.UserDao;


import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface UserManager extends Manager<UserDao,Integer> {
    /**
     * Validates that the user exist.
     *
     * @param con DB connection
     * @param player_name The entity to check
     * @return a {@link Boolean}
     */
     UserDao findByName(Connection con, String player_name);

}

