package edu.fpdual.webservices.service;

import edu.fpdual.webservices.model.dao.Suggestion;
import edu.fpdual.webservices.model.dao.UserDao;
import edu.fpdual.webservices.model.manager.SuggestionsManager;

import java.sql.Connection;
import java.sql.SQLException;

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

    public Suggestion findSuggestion( UserDao user) throws SQLException, ClassNotFoundException {
        try (Connection con = suggestionsManager.getConnector().getMySQLConnection()) {
            return suggestionsManager.findSuggestion(con, user.getPlayer_name());
        }
    }
}