package com.epam.jwd.text.chainofresp.impl;

import com.epam.jwd.text.chainofresp.api.BaseTextHandler;
import com.epam.jwd.text.units.api.Unit;
import com.epam.jwd.text.units.impl.Sentence;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SentenceHandler implements BaseTextHandler{
    private static final Logger LOGGER = LoggerFactory.getLogger(SentenceHandler.class);
    private static final String LEXEME_PARSING_EXPR = "(\\s+)|(,\\s+)";
    private static SentenceHandler sentenceHandler;
    private final LexemeHandler lexemeHandler;

    private SentenceHandler(){
        this.lexemeHandler = LexemeHandler.getLexemeHandler();
    }

    public static SentenceHandler getSentenceHandler(){
        if (sentenceHandler == null){
            sentenceHandler = new SentenceHandler();
        }
        return sentenceHandler;
    }

    @Override
    public List<Unit> handleRequest(List<String> sequence){
        LOGGER.info("Handle sentence request");
        List<Unit> sentencesList = new ArrayList<>();
        Sentence sentence;
        for (String text : sequence){
            sentence = new Sentence();
            sentencesList.add(sentence);
            List<Unit> lexemes = this.lexemeHandler
                    .handleRequest(Arrays.asList(text.trim().split(LEXEME_PARSING_EXPR)));
            for (Unit lexeme : lexemes){
                sentence.add(lexeme);
            }
        }
        return sentencesList;
    }
}
