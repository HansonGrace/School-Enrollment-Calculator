#include <stdio.h>

/* Participation Project 03
* COMPLETED: 1-26-2025
* Prompts user to enter a floating-point grade of your choice in range [0.0, 100.0]
* Prints the corresponding letter grade from this scale, using code that is as efficient as possible:
* 0 to less than 60: F
* 60 to less than 70: D
* 70 to less than 80: C
* 80 to less than 90: B
* 90 to 100: A
*/
int main(){

    //float variable for inputted grade
    float grade;
    
    printf("Enter a grade point from 0.0 to 100.0");
    scanf("%f", &grade);

    if(grade < 60 && grade >= 0){
        puts("F");
    } else if (grade < 70 && grade >= 0){
        puts("D");
    } else if(grade < 80 && grade >= 0){
        puts("C");      
    } else if(grade < 90 && grade >= 0){
        puts("B");
    } else if(grade <= 100 && grade >= 0){
        puts("A");
    } else{
        puts("Invalid Grade");
    }



}