package edu.fpdual.webservices.model.manager;

import edu.fpdual.webservices.model.conector.MySQLConnector;


import java.sql.Connection;
import java.sql.SQLException;

public interface SuggestionsManager {
    /**
     * Insert a new suggestion on DB
     *
     * @param con DB connection
     * @param text the suggestion to insert
     * @return a {@link Boolean}
     */
    public int insertSuggestion(Connection con, String name, String text)throws SQLException;

    public MySQLConnector getConnector();

}
