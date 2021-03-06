package com.epam.jwd.text.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class FileWorker{
    private static final Logger LOGGER = LoggerFactory.getLogger(FileWorker.class);
    private static final String INPUT = "src" + File.separator + "main" + File.separator
            + "resources" + File.separator + "input";

    public static List<String> readFile(){
        try{
            LOGGER.info("Reading file");
            return Files.lines(Paths.get(INPUT)).collect(Collectors.toList());
        } catch (IOException e){
            LOGGER.error("File reading error " + e.getLocalizedMessage());
            return Collections.emptyList();
        }
    }

    public static void writeFile(String path, List<String> text){
        LOGGER.info("Writing result");
        File file = new File(path);
        if (!file.exists()){
            try{
                Files.createFile(file.toPath());
            } catch (IOException e){
                LOGGER.error(e.getLocalizedMessage());
            }
        }
        writeText(file, text);
    }

    public static void writeText(File file, List<String> text){
        try (Writer writer = new FileWriter(file)){
            for (String line : text){
                writer.write(line);
                writer.append('\n');
            }
            writer.flush();
        } catch (IOException e){
            LOGGER.error(e.getLocalizedMessage());
        }
    }
}
