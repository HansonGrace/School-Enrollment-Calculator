#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include <string.h>
/* Participation Porject 06
* COMPLETED 3-03-2025
* 
* Assignment directions:

Create a file using the filename provided as an argument when calling the function
Write the integer values in the passed-in array to the file, one number per line
Close the file when finished writing to it

Create an array of at least 2 integer values, either by randomly generating the values or hard-coding them into the source code
Pass the array to the write_array function to be written to the file output.txt

*/

/**
 * @author Grace Hanson
 * @date 2-24-2025
 *
*/

/**
 * @brief Generates a random string of ascii chars
 *
 * creates a string with the specified number of ascii characters
 * randomized from 33-125, string is null terminated
 *
 * @param length number of characters to generate excluding null terminator
 * @return pointer to the generated string (key)
*/
char* generate_key(size_t length) {
    char* key = (char*)malloc((length + 1) * sizeof(char));

    //if memory allocation is successfull or not. if not, throw error
    if (key == NULL) {
        perror("Unable to allocate memory for key");
        exit(EXIT_FAILURE);
    }

    //seeds random # generator 
    srand((unsigned int)time(NULL));

    //fill key with random chars from 33 to 125
    for (size_t i = 0; i < length; i++) {
        //random chars between 33 and 125 
        key[i] = (rand() % (125 - 33 + 1)) + 33;

    }
    //null-terminate the string
    key[length] = '\0';
    return key;
}

/**
 * @brief writes contents of an int array to a file
 *
 *
*/
//function prototype
int write_array(const char* filename, const int* array, size_t size);
//write_array function
//params:char, sint, size_t
int write_array(const char* filename, const int* array, size_t size) {
    FILE* file = fopen(filename, "w");

    //checks if file was successfully opened
    if (file == NULL) {
        //return 1 if there is an error when opening file
        return 1;
    }
    for (size_t i = 0; i < size; i++) {
        //writes each integer in the array into the file
        //fprintf: writes output to a file instead of console
        fprintf(file, "%d\n", array[i]);
    }
    //close out the file
    fclose(file);
    return 0;

}

int main(void)
{

    //creates an arrat of 2 int values hardcoded
    int twoints[2] = { 1, 2 };

    int result = write_array("output.txt", twoints, 2);

    if (result == 0) {
        printf("Array success");
    }
    else {
        printf("Error");
    }

    return 0;
}

