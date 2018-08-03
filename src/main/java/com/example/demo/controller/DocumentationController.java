package com.example.demo.controller;

import com.example.demo.InputType;
import com.example.demo.generator.DocumentationGeneratorModelService;
import com.example.demo.generator.DocumentationGeneratorVariablesService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Venkatesh.Kandula
 *
 * @version 1.0
 *          Create Date: Aug 02,2018
 */

@RestController
@Api()
public class DocumentationController {

    @Autowired
    private DocumentationGeneratorVariablesService documentationGeneratorVariablesService;

    @Autowired
    private DocumentationGeneratorModelService documentationGeneratorModelService;

    @PostMapping(path ="/generate/variables")
    public String generateDocumentationFromVariables(
            @RequestParam(name = "Type of input") InputType inputType,
            @RequestBody String variables){

        if(inputType.equals(InputType.MODEL_CLASS)) {
            return documentationGeneratorModelService.generateSwaggerDocumentation(variables);
        }
        else {
            return documentationGeneratorVariablesService.generateSwaggerDocumentation(variables);
        }
    }
}