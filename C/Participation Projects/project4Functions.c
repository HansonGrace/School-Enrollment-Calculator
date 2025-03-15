#include <stdio.h>
/* Participation Project 04
* COMPLETED 1-27-2025

* Provides a function named divide to divide a numerator by a denominator using values passed 
* to it and return the result as a double
*
* Has a main function which:
* Uses a loop to prompt users to enter 5 integers, one at a time
* Sums the entered numbers
* Calls the divide function, passing the sum as the numerator and the number 5 as the denominator
* Prints the result returned by the divide function
*/


//divide function
double divide(int num, int den){

    return(double) num / den;


}
int main() {

    int sum = 0;
    int num;

    //loops through input for 5 numbers
    for(int i = 0; i<5; i++){
        scanf("%d", &num);

        //sum equals all of the numbers added up
        sum += num;
    }

    //uses divide function
    double result = divide(sum, 5);

    printf("%.1f\n", result);

    return 0;
}