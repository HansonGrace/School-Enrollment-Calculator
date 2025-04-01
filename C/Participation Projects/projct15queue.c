#include <stdio.h>
#include <stdlib.h>
#include <limits.h>
/* Participation Porject 06
* COMPLETED 3-11-2025
* 
* Assignment directions:

Structure: struct node
Structure: struct queue
Function: create_queue
Function: enqueue
Function: dequeue
unction: main
Tasks:
Create a new queue with a capacity of 5 items
Attempt to insert at least 6 values in the queue, each containing a randomly generated or hard-coded int value
Dequeue each item stored in the queue, printing each value on its own line
*/

/**
 * @author Grace Hanson
 * @date 3-11-2025
 *
*/

/**
 * @brief shows a node in the queue
 *
 */
 // Node structure
struct node {
    int value;          // Value to be stored in the node
    struct node* nextPtr; // Pointer to the next node
};

// Queue structure
/**
 * @brief Represents a queue
 *
 */
struct queue {
    struct node* frontPtr; // Pointer to the front (dequeue end) of the queue
    struct node* rearPtr;  // Pointer to the rear (enqueue end) of the queue
    int capacity;          // Maximum capacity of the queue
    int size;              // Current size of the queue
};

// Create and initialize a new queue
struct queue* create_queue(int capacity) {
    struct queue* q = (struct queue*)malloc(sizeof(struct queue));
    if (q == NULL) {
        printf("Memory allocation failed!\n");
        return NULL;
    }
    q->frontPtr = NULL;
    q->rearPtr = NULL;
    q->capacity = capacity;
    q->size = 0;
    return q;
}

// Enqueue: Adds a new value to the rear of the queue
int enqueue(struct queue* q, int value) {
    if (q->size >= q->capacity) {
        return -1;  // Queue is full
    }

    struct node* newNode = (struct node*)malloc(sizeof(struct node));
    if (newNode == NULL) {
        return -1;  // Memory allocation failed
    }

    newNode->value = value;
    newNode->nextPtr = NULL;

    if (q->rearPtr == NULL) {
        // If the queue is empty, both front and rear are the new node
        q->frontPtr = newNode;
    }
    else {
        // Link the current rear node to the new node
        q->rearPtr->nextPtr = newNode;
    }

    // Update rear to the new node
    q->rearPtr = newNode;
    q->size++;  // Increment the size of the queue
    return 0;  // Success
}

// Dequeue: Removes and returns the value from the front of the queue
int dequeue(struct queue* q) {
    if (q->size == 0) {
        return INT_MIN;  // Queue is empty
    }

    struct node* temp = q->frontPtr;  // Get the node at the front
    int value = temp->value;  // Store the value to return
    q->frontPtr = q->frontPtr->nextPtr;  // Move front pointer to the next node

    if (q->frontPtr == NULL) {
        // If the queue becomes empty, reset rear pointer
        q->rearPtr = NULL;
    }

    free(temp);  // Free the memory for the dequeued node
    q->size--;  // Decrease the size of the queue
    return value;  // Return the value dequeued
}

int main(void) {
    // Create a queue with a capacity of 5
    struct queue* q = create_queue(5);

    if (q == NULL) {
        return -1;  // If queue creation failed, exit
    }

    // Attempt to enqueue 6 values (more than the queue's capacity)
    for (int i = 0; i < 6; i++) {
        int result = enqueue(q, i * 10);  // Enqueue multiples of 10
        if (result == -1) {
            printf("Failed to enqueue: Queue is full or memory allocation failed\n");
        }
    }

    // Dequeue all items and print them
    printf("Dequeuing items:\n");
    int value;
    while ((value = dequeue(q)) != INT_MIN) {
        printf("%d\n", value);
    }

    // Test enqueuing again after dequeueing
    printf("Enqueuing more items after dequeuing:\n");
    for (int i = 6; i < 10; i++) {
        int result = enqueue(q, i * 10);  // Enqueue multiples of 10
        if (result == -1) {
            printf("Failed to enqueue: Queue is full or memory allocation failed\n");
        }
    }

    // Dequeue and print the new items
    printf("Dequeuing new items:\n");
    while ((value = dequeue(q)) != INT_MIN) {
        printf("%d\n", value);
    }

    // Clean up
    free(q);  // Free memory for the queue structure itself
    return 0;
}
