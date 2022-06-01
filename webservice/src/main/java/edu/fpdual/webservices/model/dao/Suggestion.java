package edu.fpdual.webservices.model.dao;

import lombok.Getter;
import lombok.Setter;

import java.sql.ResultSet;
import java.sql.SQLException;

@Setter
@Getter

public class Suggestion implements Comparable<Suggestion> {

    int idSuggestion;
    String player_name;
    String suggestion;



    public Suggestion(){

    }

    public Suggestion(ResultSet result) {
        try {
            this.idSuggestion = result.getInt("idSuggestion");
            this.player_name = result.getString("player_name");
            this.suggestion = result.getString("suggestion");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    @Override
    public int compareTo(Suggestion o) {
        return 0;
    }
}
