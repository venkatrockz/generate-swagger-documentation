package com.example.demo.generator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Venkatesh.Kandula
 *
 * @version 1.0
 *          Create Date: Aug 02,2018
 */

@Component
public class DocumentationGeneratorVariablesService {

    @Autowired
    private DocumentGeneratorHelper helper;

    public String generateSwaggerDocumentation(String variable) {

        StringBuilder output = new StringBuilder();
        String[] variables = variable.split("\n");
        List<String> sentences = helper.generateSentence(variables);
        for (int i = 0; i < variables.length; i++) {

            output.append(helper.getSwaggerAnnotation(sentences.get(i)));
            output.append("\n");
            output.append("private String " + variables[i] + ";");
            output.append("\n");
        }
        return output.toString();
    }
}