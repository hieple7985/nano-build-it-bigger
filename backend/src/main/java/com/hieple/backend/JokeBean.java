package com.hieple.backend;

import com.hieple.jokes.Joker;

public class JokeBean {

    private Joker joker;

    public JokeBean() {
        joker = new Joker();
    }

    public String getJoke() {
        return joker.getRandomJoke();
    }
}