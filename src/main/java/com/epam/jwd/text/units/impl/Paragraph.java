package com.epam.jwd.text.units.impl;

import com.epam.jwd.text.units.api.Unit;

import java.util.ArrayList;
import java.util.List;

public class Paragraph implements Unit{
    private List<Sentence> sentences;

    public Paragraph(){
        sentences = new ArrayList<>();
    }

    public List<Sentence> getSentences(){
        return sentences;
    }

    public void setSentences(List<Sentence> sentences){
        this.sentences = sentences;
    }

    @Override
    public void add(Unit anyUnit){

    }

    @Override
    public Unit getChild(int position){
        return null;
    }

    @Override
    public void operation(){

    }
}
