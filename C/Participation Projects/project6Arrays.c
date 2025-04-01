#include <stdio.h>

/* Participation Porject 06
* COMPLETED 2-3-2025
* 
* Assignment directions:
* Creates an integer array named numbers populated with 10 hard-coded values of your choice
* Implements a bubble sort function named bubble_sort that performs an in-place sort on a passed-in array, sorting
* the values in ascending order
*
* Function needs to handle an array of any size
* You must implement the sorting algorithm yourself
* Prints the contents of the sorted array in your main function
* 
* Bubble sort repeatedly traverses the array, comparing each element with the next one to sort the data.
* If the elements are not sorted in order, they are swapped.
* Over multiple passes, the highest values are pushed to the right and the lowest values move to the left, sorting the
* results in ascending order.

/

/**
 * @brief returns both the normal array and bubble sorted array
 *
 * uses the bubble sort algorithm to sort given hardcoded integers
 * @param arr array of integers to be sorted
 * @param n number of elements in the array
 *
*/
void bubble_sort(int arr[], int n);
void print_array(int arr[], int n);

void bubble_sort(int arr[], int n) {
    int i, j, temp;

    //loop over array elements
    for (i = 0; i < n - 1; i++) {
        //last i elements are sorted so dont compare them
        for (j = 0; j < n - i - 1; j++) {
            //compare adjacent elements
            if (arr[j] > arr[j + 1]) {
                //swap if current element is greater than the next
                temp = arr[j];
                arr[j] = arr[j + 1];
                arr[j + 1] = temp;
            }

        }

    }
}
void print_array(int arr[], int n) {
    for (int i = 0; i < n; i++) {
        printf("%d ", arr[i]);
    }
    printf("\n");
}
int main(void)
{
    int numbers[10] = { 23, 1, 56, 12, 78, 45, 3, 90, 65, 34 };
    print_array(numbers, 10);

    //bubble sort array
    bubble_sort(numbers, 10);
    print_array(numbers, 10);
    return 0;
}
}