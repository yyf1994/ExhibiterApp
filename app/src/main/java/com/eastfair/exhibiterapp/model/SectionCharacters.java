package com.eastfair.exhibiterapp.model;

import java.util.List;

/**
 * Created by aspsine on 15/9/4.
 */
public class SectionCharacters {

    private List<Characters> characters;

    private List<Section> sections;

    public List<Characters> getCharacters() {
        return characters;
    }

    public void setCharacters(List<Characters> characters) {
        this.characters = characters;
    }

    public List<Section> getSections() {
        return sections;
    }

    public void setSections(List<Section> sections) {
        this.sections = sections;
    }
}
