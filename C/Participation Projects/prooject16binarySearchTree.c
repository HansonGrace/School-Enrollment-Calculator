#include <stdio.h>
#include <stdlib.h>
#include <string.h>
/* Participation Porject 06
* COMPLETED 3-13-2025
* 
* Assignment directions:

Structure: struct node
Function: insert
Function: print_tree_postorder
Function: find_max

Function: main
Tasks:
Create a new binary search tree
Stores at least 5 hard-coded strings in the binary search tree
Prints a post-order traversal of the tree
*/

/**
 * @author Grace Hanson
 * @date 3-13-2025
 *
*/

/**
 *
 * @brief binary search tree in C
 * post order traversal
 *
*/

/**
 * @struct node
*/
struct node {
    char value[50];
    struct node* leftPtr;
    struct node* rightPtr;
};

/**
 * @brief Creates a new BST node
 * @param value The value to store in the node
 * @return Pointer to the newly created node, or NULL if memory allocation doesnt work
 */
struct node* create_node(char* value) {
    struct node* newNode = (struct node*)malloc(sizeof(struct node));
    if (!newNode) return NULL;
    strcpy(newNode->value, value);
    newNode->leftPtr = newNode->rightPtr = NULL;
    return newNode;
}
/**
 * @brief Inserts a value into the BST
 *
 * The insertion is performed case-insensitively, ensuring no duplicate values exist
 *
 * @param root Pointer to the root of the tree to insert into
 * @param value The value to be inserted
 * @return 0 if insertion is successful, -1 if not
 */
 // Function to insert a value into the BST
int insert(struct node** root, char* value) {
    if (*root == NULL) {
        *root = create_node(value);
        return (*root) ? 0 : -1;
    }
    int cmp = strcasecmp(value, (*root)->value);
    if (cmp < 0) {
        return insert(&((*root)->leftPtr), value);
    }
    else if (cmp > 0) {
        return insert(&((*root)->rightPtr), value);
    }
    return -1; //dup value
}
/**
 * @brief Performs a post-order traversal of the BST and prints each node's value.
 **/
void print_tree_postorder(struct node* root) {
    if (root == NULL) return;
    print_tree_postorder(root->leftPtr);
    print_tree_postorder(root->rightPtr);
    printf("%s\n", root->value);
}

char* find_max(struct node* root) {
    if (root == NULL) return NULL;
    while (root->rightPtr) {
        root = root->rightPtr;
    }
    return root->value;
}

int main(void)
{

    return 0;
}