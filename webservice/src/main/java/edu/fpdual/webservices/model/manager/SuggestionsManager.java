package edu.fpdual.webservices.model.manager;

import edu.fpdual.webservices.model.conector.MySQLConnector;
import edu.fpdual.webservices.model.dao.Suggestion;
import edu.fpdual.webservices.model.dao.UserDao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface SuggestionsManager {
    /**
     * Insert a new suggestion on DB
     *
     * @param con DB connection
     * @param text the suggestion to insert
     * @return a {@link Boolean}
     */
    public int insertSuggestion(Connection con, String name, String text)throws SQLException;

    /**
     * Finds all the entities in the DB based on a list of ids.
     *
     * @param con DB connection
     * @param id Entities id to search for.
     * @return a {@link Suggestion}
     */
    public Suggestion findSuggestion (Connection con,Integer id);


    /**
     * Finds all the entities in the DB
     *
     * @param con DB connection
     * @return a {@link Suggestion} of {@link Suggestion}
     */
    List<Suggestion> ListAll(Connection con);


    /**
     * Delete a suggestion by id
     *
     * @param con DB connection
     * @return a {@link Boolean}
     */
    public boolean delete(Connection con, Suggestion sugu) throws SQLException;
    /**
     * update suggestions on DB
     *
     * @param con DB connection
     *
     * @return a {@link boolean}
     */

    public boolean update(Connection con, Suggestion sugu) throws SQLException;
    /**
     * Validate suggestion by id
     * @param con DB connection
     * @param id Entities id to delete.
     * @return a {@link Boolean}
     */
    public boolean validate(Connection con, Integer id) throws SQLException;


    public MySQLConnector getConnector();

}
