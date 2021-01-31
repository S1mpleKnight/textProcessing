package com.epam.jwd.text.chainofresp.impl;

import com.epam.jwd.text.chainofresp.api.BaseTextHandler;
import com.epam.jwd.text.units.api.Unit;
import com.epam.jwd.text.units.impl.Lexeme;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LexemeHandler implements BaseTextHandler{
    private static final Logger LOGGER = LoggerFactory.getLogger(LexemeHandler.class);
    private static LexemeHandler lexemeHandler;
    private final SymbolHandler symbolHandler;

    private LexemeHandler(){
        this.symbolHandler = SymbolHandler.getSentenceHandler();
    }

    public static LexemeHandler getLexemeHandler(){
        if (lexemeHandler == null){
            lexemeHandler = new LexemeHandler();
        }
        return lexemeHandler;
    }

    @Override
    public List<Unit> handleRequest(List<String> sequence){
        LOGGER.info("Handle lexeme request");
        List<Unit> lexemesList = new ArrayList<>();
        Lexeme lexeme;
        for (String text : sequence){
            lexeme = new Lexeme();
            List<Unit> symbols = this.symbolHandler
                    .handleRequest(Arrays.asList(text.trim().split("")));
            for (Unit symbol : symbols){
                lexeme.add(symbol);
            }
            lexemesList.add(lexeme);
        }
        return lexemesList;
    }
}
