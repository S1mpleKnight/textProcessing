package com.epam.jwd.text.units.impl;

import com.epam.jwd.text.units.api.Unit;

public class Symbol implements Unit{
    private final Character character;

    public Symbol(Character character){
        this.character = character;
    }

    public Character getCharacter(){
        return character;
    }

    public Boolean isLetter(){
        char symbol = character;
        return ((symbol >= 65) && (symbol <= 90))
                || ((symbol >= 97) && (symbol <= 122));
    }

    @Override
    public void add(Unit anyUnit){
        throw new UnsupportedOperationException("Symbol can not have children");
    }

    @Override
    public Unit getChild(int position){
        throw new UnsupportedOperationException("Symbol do not have children");
    }

    @Override
    public void operation(){

    }

    @Override
    public String toString(){
        return character.toString();
    }
}
