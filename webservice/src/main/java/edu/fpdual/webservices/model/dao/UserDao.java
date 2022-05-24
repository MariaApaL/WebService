package edu.fpdual.webservices.model.dao;

import lombok.Getter;
import lombok.Setter;

import java.sql.ResultSet;
import java.sql.SQLException;

@Setter
@Getter

public class UserDao implements Comparable<UserDao> {

        int idplayer;
        String player_name;
        String password;
        int num_game;


        public UserDao(){

        }

    public UserDao(ResultSet result) {
        try {
            this.idplayer = result.getInt("idplayer");
            this.player_name = result.getString("player_name");
            this.password = result.getString("password");
            this.num_game = result.getInt("num_game");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    @Override
    public int compareTo(UserDao o) {
        return this.player_name.compareTo(o.getPlayer_name());
    }
}
