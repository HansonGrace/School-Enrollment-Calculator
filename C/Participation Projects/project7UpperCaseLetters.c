#include <stdio.h>
#include <stdlib.h>
#include <ctype.h>
/* Participation Porject 06
* COMPLETED 2-5-2025
* 
* Assignment directions:
*
* Prompts the user to enter tex
* You must display a message with instructions
* Use the fgets function to read-in the user input
* Converts the input entirely to uppercase letters
* Prints the resulting string
/

/**
 * @brief convert any inputted string to uppercase
 *
 * Allows a user to input any string of text, and used a loop to
 * convert all characters to uppercase, and then prints the result.
 *
 * @return 0 when successful
 *
*/
int main(void)
{

    //array for user input
    char userinput[1000];

    printf("Enter any text: ");

    //fgets function, makes sure to stay at size of array
    fgets(userinput, sizeof(userinput), stdin);
    //converts input string to uppercase string using toupper
    //loop through the string
    for (int i = 0; userinput[i] != '\0'; i++) {
        userinput[i] = toupper(userinput[i]);

    }
    //print uppercase letters
    printf(userinput);


    return 0;
}