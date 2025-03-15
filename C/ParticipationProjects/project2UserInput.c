#include <stdio.h>

/* Participation Project 02
* COMPLETED: 1-26-2025
* Prompts the user to input two different floating-point numbers
* Displays a message telling users to enter two numbers, one per line
* Use the double type for more accurate results than with the float type
* Multiplies the numbers
* Prints the result, formatted to have 3 decimal places
*/

int main(){

    //variables for the two different floating-point numbers
    double numberone;
    double numbertwo;

    //prompts user to enter the first number
    print("Enter a number: \n");
    scanf("%lf", &numberone);

    //prompts user to enter the second number
    printf("Enter the second number: \n");
    scanf("%lf", &numbertwo);

    //variable that multiplies the two entered numbers
    double result = numberone * numbertwo;

    //prints result formatted to three decimal places
    printf("The result is %.3f\n", result);


    return 0;
}