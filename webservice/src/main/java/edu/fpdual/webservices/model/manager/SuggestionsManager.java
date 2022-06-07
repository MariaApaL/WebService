package edu.fpdual.webservices.model.manager;

import edu.fpdual.webservices.model.conector.MySQLConnector;
import edu.fpdual.webservices.model.dao.Suggestion;
import edu.fpdual.webservices.model.dao.UserDao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface SuggestionsManager extends Manager<Suggestion,Integer>{
    /**
     * Insert a new suggestion on DB
     *
     * @param con DB connection
     * @param text the suggestion to insert
     * @return a {@link Boolean}
     */
    public int insertSuggestion(Connection con, String name, String text)throws SQLException;


    /**
     * Finds all the entities in the DB
     *
     * @param con DB connection
     * @return a {@link Suggestion} of {@link Suggestion}
     */
    List<Suggestion> ListAll(Connection con);


}
