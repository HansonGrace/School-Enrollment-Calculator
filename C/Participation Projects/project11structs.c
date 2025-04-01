#include <stdio.h>
#include <stdlib.h>
#include <time.h>
/* Participation Porject 06
* COMPLETED 2-19-2025
* 
* Assignment directions:

Declares 2 structures to store a recipe
A structure named struct ingredient to store each ingredient's name, needed quantity, and measurement type (i.e., "cup", "pounds")
A structure named struct recipe to store the name of the recipe and an array of struct ingredient instances
Creates the required instances to store details on one of your favorite recipes
Prints out the recipe's name and lists each of its ingredients including the quantity needed


*/

/**
 * @brief creates array of ints and gives it random values
 *
 * allocates memory for an array of ints, checks if valid, and gives
 * them random values
 *
 * @param size of elements
 * @return pointer to array
 *
*/
/**
 * @brief A program that creats a recipe with the name, quantity, and measurement types
 * stores different data types and instances
 *
 *
 *
 *
*/
struct ingredient {
    //name, quanity, and type of measurement
    char name[50];
    float quantity;
    char measurement[20];
};

//structure for recipe
struct recipe {
    char name[100];
    struct ingredient ingredients[10];
    int num_ingredients;
};

int main(void)
{
    struct recipe my_recipe = {
        //recipe name
        "Chocolate Cake",
        {
            {"Flour", 2.5, "cups"},
            {"Sugar", 1.5, "cups"},
            {"Cocoa Powder", 0.75, "cups"},
            {"Baking Powder", 1.5, "tsp"},
            {"Eggs", 3, "pieces"},
            {"Butter", 0.5, "cups"},
            {"Milk", 1, "cups"}
        },
        7 //# of ingredients
    };

    printf("Recipe: %s\n", my_recipe.name);
    printf("\nIngredients: \n");

    for (int i = 0; i < my_recipe.num_ingredients; i++) {
        printf("%s: %.2f %s\n",
            my_recipe.ingredients[i].name,
            my_recipe.ingredients[i].quantity,
            my_recipe.ingredients[i].measurement);
    }
    return 0;
}