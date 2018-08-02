/**
 * Copyright(c)2001-2018 First National Bank of Omaha
 * 1620 Dodge Street Omaha, NE 68197, USA
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of
 * First National Bank ("Confidential Information"). You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with First National Bank
 */
package com.example.demo.generator;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author First National Bank of Omaha
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