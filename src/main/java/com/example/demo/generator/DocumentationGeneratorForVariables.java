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
public class DocumentationGeneratorForVariables {

    public String generateSwaggerDocumentation(String variable){

        StringBuilder output = new StringBuilder();
        String[] variables = variable.split(",");
        List<String> sentences = generateSentence(variables);
        for(int i=0; i< variables.length; i++){

            output.append(getSwaggerAnnotation(sentences.get(i)));
            output.append("\n");
            output.append("private String " + variables[i] +";");
            output.append("\n");
        }
        return output.toString();
    }

    private List<String> generateSentence(String[] variables) {

        return Arrays.asList(variables)
                .stream()
                .map(this :: convertVariableNameToSentence)
                .map(this :: formatSentence)
                .collect(Collectors.toList());
    }

    private String getSwaggerAnnotation(String sentence) {

        return "@ApiModelProperty(name = \"" + sentence + "\")";
    }

    private String convertVariableNameToSentence(String variable){

        StringBuilder out = new StringBuilder(variable);
        Pattern p = Pattern.compile("[A-Z]");
        Matcher m = p.matcher(variable);
        int extraFeed = 0;
        while(m.find()){
            if(m.start()!=0){
                out = out.insert(m.start()+extraFeed, " ");
                extraFeed++;
            }
        }
        return out.toString();
    }

    private String formatSentence(String out) {

        String firstChar = out.substring(0,1).toUpperCase();
        return firstChar.concat(out.substring(1).toLowerCase());
    }
}