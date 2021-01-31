package com.epam.jwd.text.units.impl;

import com.epam.jwd.text.units.api.Unit;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Text implements Unit{
    private List<Paragraph> paragraphs;

    public Text(){
        paragraphs = new ArrayList<>();
    }

    public void setParagraphs(List<Paragraph> paragraphs){
        this.paragraphs = paragraphs;
    }

    public List<Paragraph> getParagraphs(){
        return paragraphs;
    }

    @Override
    public void add(Unit anyUnit){
        paragraphs.add((Paragraph) anyUnit);
    }

    @Override
    public Unit getChild(int position){
        return null;
    }

    @Override
    public void operation(){

    }

    @Override
    public String toString(){
        return paragraphs.stream().map(Paragraph::toString).collect(Collectors.joining(".\n"));
    }
}
