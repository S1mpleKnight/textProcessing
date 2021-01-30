package com.epam.jwd.text.chainofresp.impl;

import com.epam.jwd.text.chainofresp.api.BaseTextHandler;
import com.epam.jwd.text.units.api.Unit;
import com.epam.jwd.text.units.impl.Text;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.List;

public class TextHandler implements BaseTextHandler{
    private static final Logger LOGGER = LoggerFactory.getLogger(TextHandler.class);
    private static TextHandler textHandler;
    private final ParagraphHandler paragraphHandler;

    private TextHandler(ParagraphHandler paragraphHandler){
        this.paragraphHandler = paragraphHandler;
    }

    public static TextHandler getTextHandler(){
        if (textHandler == null){
            textHandler = new TextHandler(ParagraphHandler.getParagraphHandler());
        }
        return textHandler;
    }

    @Override
    public List<Unit> handleRequest(List<String> sequence){
        LOGGER.info("Handle text request");
        Text text = new Text();
        List<Unit> paragraphs = this.getParagraphHandler().handleRequest(sequence);
        for (Unit unit : paragraphs){
            text.add(unit);
        }
        return Collections.singletonList(text);
    }

    public ParagraphHandler getParagraphHandler(){
        return paragraphHandler;
    }
}
