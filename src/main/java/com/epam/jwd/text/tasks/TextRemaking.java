package com.epam.jwd.text.tasks;

import com.epam.jwd.text.units.impl.Lexeme;
import com.epam.jwd.text.units.impl.Paragraph;
import com.epam.jwd.text.units.impl.Sentence;
import com.epam.jwd.text.units.impl.Symbol;
import com.epam.jwd.text.units.impl.Text;
import com.epam.jwd.text.util.FileWorker;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TextRemaking{
    private static final String FIRST = "src" + File.separator + "main" + File.separator
            + "resources" + File.separator + "firstTask";
    private static final String SECOND = "src" + File.separator + "main" + File.separator
            + "resources" + File.separator + "secondTask";
    private static final String THIRD = "src" + File.separator + "main" + File.separator
            + "resources" + File.separator + "thirdTask";

    private static List<String> paragraphSortBySize(Text someText){
        return someText.getParagraphs()
                .stream()
                .sorted()
                .map(p -> (p.toString() + "\n"))
                .collect(Collectors.toList());
    }

    public static void firstTask(Text someText){
        FileWorker.writeFile(FIRST, paragraphSortBySize(someText));
    }

    private static List<String> lexemeSortBySize(Text someText){
        List<String> anotherText = new ArrayList<>();
        for (Paragraph paragraph: someText.getParagraphs()){
            for (Sentence sentence: paragraph.getSentences()){
                sentence.setLexemes(sentence.getLexemes()
                        .stream()
                        .sorted()
                        .collect(Collectors.toList()));
            }
            anotherText.add(paragraph.toString());
        }
        return anotherText;
    }

    public static void secondTask(Text someText){
        FileWorker.writeFile(SECOND, lexemeSortBySize(someText));
    }

    private static List<String> lexemeSortByCharIncluded(Text someText, Character character){
        LinkedList<String> list = new LinkedList<>();
        List<Lexeme> lexemes = allLexemes(someText);
        Set<Long> values = valuesOfContainsSymbol(lexemes, character);
        Map<Long, List<String>> map = lexemesListByTimesOfContains(values, lexemes, character);
        List<Long> keys = map.keySet().stream().sorted().collect(Collectors.toList());
        for (Long key: keys){
            list.addFirst(map.get(key)
                    .stream()
                    .map(String::toLowerCase)
                    .sorted()
                    .collect(Collectors.joining(" ")));
        }
        return list;
    }

    private static Map<Long, List<String>> lexemesListByTimesOfContains(Set<Long> values, List<Lexeme> lexemes,
                                                                        Character character){
        Map<Long, List<String>> map = new HashMap<>();
        for (Long value: values){
            List<String> lexemeList = lexemes
                    .stream()
                    .filter(lexeme -> value.equals(numberOfContains(lexeme, character)))
                    .map(Lexeme::toString)
                    .collect(Collectors.toList());
            map.put(value, lexemeList);
        }
        return map;
    }

    private static Set<Long> valuesOfContainsSymbol(List<Lexeme> lexemes, Character character){
        Set<Long> values = new HashSet<>();
        for (Lexeme lexeme: lexemes){
            values.add(numberOfContains(lexeme, character));
        }
        return values;
    }

    private static Long numberOfContains(Lexeme lexeme, Character character){
        return lexeme.getSymbols()
                .stream()
                .map(Symbol::getCharacter)
                .filter(s -> s.equals(character))
                .count();
    }

    private static List<Lexeme> allLexemes(Text someText){
        List<Lexeme> lexemes = new ArrayList<>();
        for (Paragraph paragraph: someText.getParagraphs()){
            for (Sentence sentence: paragraph.getSentences()){
                lexemes.addAll(sentence.getLexemes());
            }
        }
        return lexemes;
    }

    public static void thirdTask(Text someText, Character character){
        FileWorker.writeFile(THIRD, lexemeSortByCharIncluded(someText, character));
    }
}
