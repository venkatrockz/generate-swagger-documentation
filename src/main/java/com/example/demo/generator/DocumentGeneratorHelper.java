package com.example.demo.generator;

import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * @author Venkatesh.Kandula
 *
 * @version 1.0
 *          Create Date: Aug 02,2018
 */

@Component
public class DocumentGeneratorHelper {

    public List<String> generateSentence(String[] variables) {

        return Arrays.asList(variables)
                .stream()
                .map(this::convertVariableNameToSentence)
                .map(this::formatSentence)
                .collect(Collectors.toList());
    }

    public String getSwaggerAnnotation(String sentence) {

        return "@ApiModelProperty(name = \"" + sentence + "\")";
    }

    public String convertVariableNameToSentence(String variable) {

        StringBuilder out = new StringBuilder(variable);
        Pattern p = Pattern.compile("[A-Z]");
        Matcher m = p.matcher(variable);
        int extraFeed = 0;
        while (m.find()) {
            if (m.start() != 0) {
                out = out.insert(m.start() + extraFeed, " ");
                extraFeed++;
            }
        }
        return out.toString();
    }

    public String formatSentence(String out) {

        String firstChar = out.substring(0, 1).toUpperCase();
        return firstChar.concat(out.substring(1).toLowerCase());
    }
}