package com.epam.jwd.text.tasks;

import com.epam.jwd.text.chainofresp.impl.TextHandler;
import com.epam.jwd.text.units.api.Unit;
import com.epam.jwd.text.units.impl.Text;
import com.epam.jwd.text.util.FileWorker;
import org.apache.logging.log4j.core.util.Assert;

import java.io.File;
import java.util.List;

class TextRemakingTest{

    @org.junit.jupiter.api.Test
    void firstTask(){
        TextHandler textHandler = TextHandler.getTextHandler();
        List<Unit> list = textHandler.handleRequest(FileWorker.readFile());
        Assert.isNonEmpty(list);
        List<String> stringList = TextRemaking.paragraphSortBySize((Text) list.get(0));
        Assert.isNonEmpty(stringList);
        FileWorker.writeText(new File("src" + File.separator + "main" + File.separator
                + "resources" + File.separator + "firstTask"), stringList);
    }

    @org.junit.jupiter.api.Test
    void secondTask(){
        TextHandler textHandler = TextHandler.getTextHandler();
        List<Unit> list = textHandler.handleRequest(FileWorker.readFile());
        Assert.isNonEmpty(list);
        List<String> stringList = TextRemaking.lexemeSortBySize((Text) list.get(0));
        Assert.isNonEmpty(stringList);
        FileWorker.writeText(new File("src" + File.separator + "main" + File.separator
                + "resources" + File.separator + "secondTask"), stringList);
    }

    @org.junit.jupiter.api.Test
    void thirdTask(){
        TextHandler textHandler = TextHandler.getTextHandler();
        List<Unit> list = textHandler.handleRequest(FileWorker.readFile());
        Assert.isNonEmpty(list);
        List<String> stringList = TextRemaking.lexemeSortByCharIncluded((Text) list.get(0), 'a');
        Assert.isNonEmpty(stringList);
        FileWorker.writeText(new File("src" + File.separator + "main" + File.separator
                + "resources" + File.separator + "thirdTask"), stringList);

    }
}