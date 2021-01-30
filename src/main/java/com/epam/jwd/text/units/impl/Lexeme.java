package com.epam.jwd.text.units.impl;

import com.epam.jwd.text.units.api.Unit;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Lexeme implements Unit{
    private List<Symbol> symbols;


    public List<Symbol> getSymbols(){
        return symbols;
    }

    public void setSymbols(List<Symbol> symbols){
        this.symbols = symbols;
    }

    public Lexeme(){
        symbols = new ArrayList<>();
    }

    @Override
    public void add(Unit anyUnit){

    }

    @Override
    public Unit getChild(int position){
        return symbols.get(position);
    }

    @Override
    public void operation(){

    }

    @Override
    public String toString(){
        return symbols.stream().map(Symbol::toString).collect(Collectors.joining(""));
    }
}
