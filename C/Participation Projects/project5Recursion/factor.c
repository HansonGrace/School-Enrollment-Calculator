#include <stdio.h>
#include "factor.h"

void print_factors(int n, int i){
//base case
//stop recursion when i is larger than n
if (i>n)
return;

//IF i is a factor of N
if( n % i == 0){

    //print factors
    //prints out in descending order
    print_factors(n, i+1);
    printf("%d\n", i);
} else {
//if not a factor N go to the next number
print_factors(n, i+1);

}

}