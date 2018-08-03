package com.example.demo.generator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Venkatesh.Kandula
 * @version 1.0
 * Create Date: Aug 02,2018
 */

@Component
public class DocumentationGeneratorModelService {

    @Autowired
    private DocumentGeneratorHelper helper;

    public String generateSwaggerDocumentation(String variable) {

        StringBuilder output = new StringBuilder();
        String[] modelVariables = variable.split("\n");
        String[] updatedVariables = modelVariables;
        for(int j=0; j<modelVariables.length; j++){
            updatedVariables[j] = modelVariables[j].replace(";","").split(" ")[2];
        }
        List<String> sentences = helper.generateSentence(modelVariables);
        for (int i = 0; i < modelVariables.length; i++) {

            output.append(helper.getSwaggerAnnotation(sentences.get(i))+"\n");
            output.append("private String " + modelVariables[i] + ";"+"\n");
        }
        return output.toString();
    }
}