package com.samsung.kaliyev.satpreparation;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

import java.util.HashMap;
import java.util.Map;

@IgnoreExtraProperties
public class User {
    public String username;
    public String email;
    public int word_score;
    public int rank;

    public User() {

    }

    public User(String username, String email, int word_score, int rank) {
        this.username = username;
        this.email = email;
        this.word_score = word_score;
        this.rank = rank;
    }

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("username", username);
        result.put("email", email);
        result.put("word_score", word_score);
        result.put("rank", rank);

        return result;
    }
}
