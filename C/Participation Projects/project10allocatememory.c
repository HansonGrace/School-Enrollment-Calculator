#include <stdio.h>
#include <stdlib.h>
#include <time.h>
/* Participation Porject 06
* COMPLETED 2-13-2025
* 
* Assignment directions:

Function: create_array
Tasks:
Creates an array of integers using malloc
Display an error message and exit the program if the array cannot be allocated
Populate each element with a value in the range [1, 100]
Returns the created array
Input parameter:
size_t with the number of elements to be present in the created array
Other input: None
Returns: int*
Function: print_array
Tasks:
Print the contents of the passed-in array, one number per row
Input parameters:
int* for the array to be printed
size_t for the number of items in the array
Other input: None
Returns: void
Function: main
Tasks:
Calls the create_array function to create an array with 5 integers
Passes the created array to the print_array function to be printed
Input parameters: None
Other input: None
Returns: int


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
int* create_array(size_t size);
//allocate memory for array
void print_array(int* arr, size_t size);

int main() {
    //define size
    size_t ARRAY_SIZE = 5;
    int* arr = create_array(ARRAY_SIZE);
    print_array(arr, ARRAY_SIZE);
    free(arr);
    return 0;
}

int* create_array(size_t size) {
    int* arr = (int*)malloc(size * sizeof(int));
    if (arr == NULL) {
        fprintf(stderr, "Memory allocation failed\n");
        exit(1);
    }

    for (size_t i = 0; i < size; i++) {
        arr[i] = rand() % 100 + 1;
    }

    return arr;
}

void print_array(int* arr, size_t size) {
    for (size_t i = 0; i < size; i++) {
        printf("%d\n", arr[i]);
    }
}
