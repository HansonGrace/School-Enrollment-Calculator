#include <stdio.h>
#include <string.h>
#include <stddef.h>
/* Participation Porject 06
* COMPLETED 2-13-2025
* 
* Assignment directions:
*Creates int variables for width, length, and area of a room
Creates int* pointers to the width, length, and area variables
Prompts users to enter the width and length of a room, using the pointers when storing input
Use one or more calls to the scanf function to gather input
Calculates the area (width × length) of the room, using the pointers for accessing the width, length, and area values
Prints the width, length, and area, using pointers to the values
Use a single call to the printf function for printing the results
*/

#include <stdio.h>
/**
 * @brief Calculates area of a room
 *
 * prompts user to input width and length and prints out
 * correct room dimensions
 * @return 0 when successfull on execution
 *
 *
*/

int main(void)
{
    int width;
    int length;
    int area;

    int* ptrLength = &length;
    int* ptrArea = &area;
    int* ptrWidth = &width;


    scanf("%d", ptrWidth);
    scanf("%d", ptrLength);

    *ptrArea = (*ptrWidth) * (*ptrLength);

    printf("%d %d %d\n", *ptrWidth, *ptrLength, *ptrArea);

    return 0;
}