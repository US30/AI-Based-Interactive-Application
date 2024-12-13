import React, { useState } from "react";

function RecipeGenerator() {
    const [ingredients, setIngredients] = useState('');
    const [cuisine, setCuisine] = useState('');
    const [dietaryRestrictions, setDietaryRestrictions] = useState('');
    const [recipe, setRecipe] = useState('');

    const createRecipe = async () => {
        try {
            const response = await fetch(`https://localhost:8080/recipe-creator?ingredients=${ingredients}&dietaryRestrictions=${dietaryRestrictions}&cuisine=${cuisine}}`)
            const data = await response.text();
            console.log(data);
            setRecipe(data);
        } catch (error) {
            console.error("Error generating recipe", error);
        }
    };
    return (
        <div>
            <h1>Create a Recipe</h1>
            <input type="text" placeholder="Enter Ingredients(comma seperated)" value={ingredients} onChange={(e) => setIngredients(e.target.value)}/>
            <input type="text" placeholder="Enter Cuisine Type" value={cuisine} onChange={(e) => setCuisine(e.target.value)}/>
            <input type="text" placeholder="Enter Dietary Restrictions" value={dietaryRestrictions} onChange={(e) => setDietaryRestrictions(e.target.value)}/>
            <button onClick={createRecipe}>Create Recipe</button>
            <div className="output">
                <pre className="recipe-text">{recipe}</pre>
            </div>
        </div>
      
    );
}


export default RecipeGenerator;