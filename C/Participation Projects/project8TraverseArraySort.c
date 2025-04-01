#include <stdio.h>
#include <string.h>
#include <stddef.h>
/* Participation Porject 06
* COMPLETED 2-10-2025
* 
* Assignment directions:
*
Creates an array named foods containing strings for your 5 favorite foods
Each food should be its own char* string literal
Use a single-dimension char*[], where each element contains a char* pointer to one of the favorite foods\


Implements a bubble sort function named bubble_sort that performs an in-place sort on a passed-in array, sorting the values in alphabetical order
Function needs to handle an array of any size
Consider using bubble sort or another simple sorting algorithm
You must implement the sorting algorithm yourself
Prints the contents of the sorted array in your main function

Bubble sort repeatedly traverses the array, comparing each element with the next one to sort the data.

If the elements are not sorted in order, they are swapped.
Over multiple passes, the highest values are pushed to the right and the lowest values move to the left, sorting the results in ascending order.
*/

/**
 * @brief Uses bubble sort and traversing 
 *
 *
 *
 * @return 0 when successful
 *
*/
//function prototype
void bubble_sort(char* arr[], int size);

void bubble_sort(char* arr[], int size) {
    //iterate throughout array
    for (int i = 0; i < size - 1; i++) {
        //traverses array from 0 to size-i-1
        for (int j = 0; j < size - i - 1; j++) {
            //compare elements
            if (strcmp(arr[j], arr[j + 1]) > 0) {
                //swap strings IF in wrong order
                char* temp = arr[j];
                arr[j] = arr[j + 1];
                arr[j + 1] = temp;
            }
        }
    }
}
int main(void)
{
    //array for 5 favorite foods
    char* foods[] = { "pizza", "chicken", "steak", "butter chicken", "white rice" };
    //calculate size of array
    int size = sizeof(foods) / sizeof(foods[0]);

    //print original list
    for (int i = 0; i < size; i++) {
        printf("%s\n", foods[i]);
    }
    //bubble sort the array
    bubble_sort(foods, size);
    //print out bubble sorted list
    for (int i = 0; i < size; i++) {
        printf("%s\n", foods[i]);
    }

    return 0;
}