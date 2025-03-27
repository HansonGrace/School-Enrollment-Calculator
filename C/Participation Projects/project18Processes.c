#include <stdio.h>
#include <unistd.h>
#include <sys/types.h>
#include <stdlib.h>
#include <string.h>
#include <errno.h>
#include <sys/wait.h>
#include <signal.h>
/*
* @author Grace Hanson
* @date 03-27-2025
* @brief POSIX C program that forks a new process
*
*/
int value = 1;

void increment_value() {
    value += 1;
    printf("Child process: Incremented value = %d\n", value);
}

void decrement_value() {
    value -= 1;
    printf("Child process: Decremented value = %d\n", value);
}

int main(void)
{
    pid_t pid = fork();
    if (pid < 0)
    {
        printf("[PID %d] Could not fork child process: %s.\n", getpid(), strerror(errno));
        exit(1);
    }
    //child process
    else if (pid == 0) {
        increment_value();
    }
    //parent process
    else {
        wait(NULL);
        decrement_value();
    }
    return 0;
}