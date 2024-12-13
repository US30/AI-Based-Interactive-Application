package com.utkarsh.SpringAi;

import java.io.IOException;
import java.util.List;

import org.springframework.ai.image.ImageResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletResponse;


@RestController
public class GenAiController {
    private final ChatService chatService;
    private final ImageService imageService;
    private final RecipeService recipeService;

    public GenAiController(ChatService chatService, com.utkarsh.SpringAi.ImageService imageService, com.utkarsh.SpringAi.RecipeService recipeService) {
        this.chatService = chatService;
        this.imageService = imageService;
        this.recipeService = recipeService;
    }

    @GetMapping("ask-ai")
     public String getResponce(@RequestParam String prompt) {    
        return chatService.getResponse(prompt);
    }
    @GetMapping("ask-ai-options")
    public String getResponceOptions(@RequestParam String prompt) {    
       return chatService.getResponseOptions(prompt);
    }
//    @GetMapping("generate-image")
//      public void generateImages(HttpServletResponse response,@RequestParam String prompt) throws IOException {    
//         ImageResponse imageResponse = imageService.generateImage(prompt);
//         String imageUrl = imageResponse.getResult().getOutput().getUrl();
//         response.sendRedirect(imageUrl);
//     }

    @GetMapping("generate-image")
    public List<String> generateImages(HttpServletResponse response,
    @RequestParam String prompt,
    @RequestParam (defaultValue = "1") String quality, 
    @RequestParam (defaultValue = "1") int n,
    @RequestParam (defaultValue = "1024") int width,
    @RequestParam (defaultValue = "1024") int height) throws IOException {    
    ImageResponse imageResponse = imageService.generateImage(prompt, quality, n, width, height);
        
    List<String> imageUrls = imageResponse.getResults().stream()
        .map(result -> result.getOutput().getUrl())
        .toList();

        return imageUrls;
    }

    @GetMapping("recipe-creator")
    public String recipeCreator(@RequestParam String ingredients,
    @RequestParam(defaultValue = "indian") String cuisine, 
    @RequestParam(defaultValue="veg") String dietaryRestrictions) {
        return recipeService.createRecipe(ingredients, cuisine, dietaryRestrictions);     
    }
 } 