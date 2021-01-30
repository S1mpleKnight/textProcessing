package com.epam.jwd.text.units.impl;

import com.epam.jwd.text.units.api.Unit;

import java.util.ArrayList;
import java.util.List;

public class Sentence implements Unit{
    private List<Lexeme> lexemes;

    public Sentence(){
        lexemes = new ArrayList<>();
    }

    public List<Lexeme> getLexemes(){
        return lexemes;
    }

    public void setLexemes(List<Lexeme> lexemes){
        this.lexemes = lexemes;
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
