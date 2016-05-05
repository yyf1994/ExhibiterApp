package com.eastfair.exhibitorapp.model;

import java.util.List;

/**
 * Created by aspsine on 15/9/4.
 */
public class Section {
    private String name;
    private List<Characters> characters;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Characters> getCharacters() {
        return characters;
    }

    public void setCharacters(List<Characters> characters) {
        this.characters = characters;
    }
}
