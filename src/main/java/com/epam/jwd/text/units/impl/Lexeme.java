package com.epam.jwd.text.units.impl;

import com.epam.jwd.text.units.api.Unit;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Lexeme implements Unit, Comparable<Lexeme>{
    private List<Symbol> symbols;


    public Lexeme(){
        symbols = new ArrayList<>();
    }

    public List<Symbol> getSymbols(){
        return symbols;
    }

    public void setSymbols(List<Symbol> symbols){
        this.symbols = symbols;
    }

    @Override
    public void add(Unit anyUnit){
        symbols.add((Symbol) anyUnit);
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

    @Override
    public int compareTo(Lexeme lexeme){
        return Integer.compare(this.symbols.size(), lexeme.symbols.size());
    }
}
