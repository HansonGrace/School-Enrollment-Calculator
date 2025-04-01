#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include <string.h>
/* Participation Porject 06
* COMPLETED 3-06-2025
* 
* Assignment directions:

Structure: struct node
Function: insert_at_head
Function: insert_at_head
Function: main
Tasks:
Create an empty singly-linked list
Insert at least 3 new nodes at the head of the list, each containing a randomly generated or hard-coded int value
Pass the list to the print_contents function to be display on-screen
*/

/**
 * @author Grace Hanson
 * @date 3-06-2025
 *
*/

/**
 * @brief defines single node in the list
*/
struct node {
    int value;
    struct node* nextPtr;

};

/**
 * @bried inserts new node with given value at the head
 *
 * @param headPtr points to the head of the node
 * @param value value stored in new node
 *
*/
void insert_at_head(struct node** headPtr, int value) {
    //allocate memory
    struct node* newPtr = malloc(sizeof(struct node));
    //check if mem allocation is successful
    if (newPtr == NULL) {
        //if failed print fail msg
        printf("Faied to allocate memory\n");
    }

    //initialize new nodes
    newPtr->value = value;
    //point to current node head
    newPtr->nextPtr = *headPtr;

    //update head pointer to the new node
    *headPtr = newPtr;
}

void print_contents(struct node* head) {
    struct node* current = head;

    //traverse and print each node value
    while (current != NULL) {
        printf("%d\n", current->value);
        current = current->nextPtr;
    }
}

int main(void)
{

    return 0;
}