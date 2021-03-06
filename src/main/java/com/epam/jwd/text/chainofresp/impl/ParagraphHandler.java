package com.epam.jwd.text.chainofresp.impl;

import com.epam.jwd.text.chainofresp.api.BaseTextHandler;
import com.epam.jwd.text.units.api.Unit;
import com.epam.jwd.text.units.impl.Paragraph;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ParagraphHandler implements BaseTextHandler{
    private static final Logger LOGGER = LoggerFactory.getLogger(ParagraphHandler.class);
    private static final String SENTENCE_PARSING_EXPR = "(\\.)|(\\?)|(\\!)|(\\.\\.\\.)";
    private static ParagraphHandler paragraphHandler;
    private final SentenceHandler sentenceHandler;


    private ParagraphHandler(){
        this.sentenceHandler = SentenceHandler.getSentenceHandler();
    }

    public static ParagraphHandler getParagraphHandler(){
        if (paragraphHandler == null){
            paragraphHandler = new ParagraphHandler();
        }
        return paragraphHandler;
    }

    @Override
    public List<Unit> handleRequest(List<String> sequence){
        LOGGER.info("Handle paragraph request");
        List<Unit> paragraphsList = new ArrayList<>();
        Paragraph paragraph;
        for (String text : sequence){
            paragraph = new Paragraph();
            paragraphsList.add(paragraph);
            List<Unit> sentences = this.sentenceHandler
                    .handleRequest(Arrays.asList(text.trim().split(SENTENCE_PARSING_EXPR)));
            for (Unit sentence : sentences){
                paragraph.add(sentence);
            }
        }
        return paragraphsList;
    }
}
