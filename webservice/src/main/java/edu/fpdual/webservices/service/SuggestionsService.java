package edu.fpdual.webservices.service;

import edu.fpdual.webservices.model.dao.Suggestion;
import edu.fpdual.webservices.model.dao.UserDao;
import edu.fpdual.webservices.model.manager.SuggestionsManager;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class SuggestionsService {

    private final SuggestionsManager suggestionsManager;


    public SuggestionsService(SuggestionsManager suggestionManager) {

        this.suggestionsManager = suggestionManager;
    }


    public int insertSuggestion(String name, String text) throws SQLException, ClassNotFoundException {

        try (Connection con = suggestionsManager.getConnector().getMySQLConnection()) {
            return suggestionsManager.insertSuggestion(con, name, text);


        }

    }

    public Suggestion findById(Integer id) throws SQLException, ClassNotFoundException {
        try (Connection con = suggestionsManager.getConnector().getMySQLConnection()) {
            return suggestionsManager.findById(con, id);
        }
    }

    public List<Suggestion> ListAll() throws SQLException, ClassNotFoundException {
        try (Connection con = suggestionsManager.getConnector().getMySQLConnection()) {
            return suggestionsManager.ListAll(con);
        }
    }


    public boolean delete(Suggestion sugu) throws SQLException, ClassNotFoundException {

        try (Connection con = suggestionsManager.getConnector().getMySQLConnection()) {
            return suggestionsManager.delete(con,sugu);
        }
    }

    public boolean update( Suggestion sugu) throws SQLException, ClassNotFoundException {
        try (Connection con = suggestionsManager.getConnector().getMySQLConnection()) {
            return suggestionsManager.update(con, sugu);
        }

    }
    public boolean validate(Integer id ) throws SQLException, ClassNotFoundException {
        try (Connection con = suggestionsManager.getConnector().getMySQLConnection()) {
            return suggestionsManager.validate(con, id);
        }

    }
}