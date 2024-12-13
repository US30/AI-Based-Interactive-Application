package com.utkarsh.SpringAi;

import java.util.Map;

import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.stereotype.Service;
@Service
public class RecipeService {
    public final ChatModel chatModel;

    public RecipeService(ChatModel chatModel) {
        this.chatModel = chatModel;
    }
    public String createRecipe(String ingredients,
    String cuisine, 
    String dietaryRestrictions) {
        var template = """
        I want to crate a recipe using the following ingredients: {ingredients}.
        The Cusine type I prefer is {cuisine}.
        Please comdien to the following dietary restrictions: {dietaryRestrictions}.
        Please provide me a with a detailed recipe including title, list of ingredients, cooking time, and instructions.
        """;
    PromptTemplate promptTemplate = new PromptTemplate(template);
    Map<String, Object> params = Map.of("ingredients", ingredients, "cuisine", cuisine, "dietaryRestrictions", dietaryRestrictions);
    Prompt prompt = promptTemplate.create(params);
    return chatModel.call(prompt).getResult().getOutput().getContent();
}   
}