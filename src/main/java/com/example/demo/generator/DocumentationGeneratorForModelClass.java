package com.example.demo.generator;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Venkatesh.Kandula
 *
 * @version 1.0
 *          Create Date: Aug 02,2018
 */

public class DocumentationGeneratorForModelClass {

    public static void main(String[] args) {
        try {
            FileReader reader = new FileReader("C:/MyWorks/src/main/resources/source/modelclass");
            BufferedReader bufferedReader = new BufferedReader(reader);

            String line;

            while ((line = bufferedReader.readLine()) != null) {
                System.out.println(generateSwaggerDocumentation(line));
                System.out.println(line);
            }
            reader.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String generateSwaggerDocumentation(String variable){

        String sentence = convertVariableNameToSentence(variable);
        return "@ApiModelProperty(name = \"" + sentence + "\")";
    }

    public static String convertVariableNameToSentence(String variable){

        variable = variable.split(" ")[2];
        StringBuilder out = new StringBuilder(variable.replace(";",""));
        Pattern p = Pattern.compile("[A-Z]");
        Matcher m = p.matcher(variable);
        int extraFeed = 0;
        while(m.find()){
            if(m.start()!=0){
                out = out.insert(m.start()+extraFeed, " ");
                extraFeed++;
            }
        }
        return formatSentence(out);
    }

    private static String formatSentence(StringBuilder out) {

        String firstChar = out.substring(0,1).toUpperCase();
        return firstChar.concat(out.substring(1).toLowerCase());
    }
}