#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include <string.h>
/* Participation Porject 06
* COMPLETED 2-24-2025
* 
* Assignment directions:

Create a string of the requested number of randomly selected printable characters, terminated by a \0 null terminator
Create a new null-terminated string that contains the result of XORing each byte of the input string with the corresponding byte of the key

Function: print_hex
Specify a cleartext (unencrypted) string that you wish to encrypt using a stream cipher
Call the generate_key function to generate a key with the same number of characters as your unencrypted string
Print the generated key
Use the perform_xor function, passing it your unencrypted text and key, to generate an encrypted string
Use the print_hex function to print the encrypted string
Use the perform_xor function, passing it your encrypted text and key, to generate an unencrypted string

project implements a stream cipher


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
 * @brief XORs the input string with the key to perform en/decryption
 *
 * generates a new string where each byte of the string is XORd with corresponding byte of key
 * results in a new string
 *
 * @param input Input string to XOR
 * @param key key used for XORING
 * @return pointer to new XOR string
 *
*/
char* perform_xor(const char* input, const char* key, size_t length) {
    char* result = (char*)malloc((length + 1) * sizeof(char));

    if (result == NULL) {
        perror("Unablle to allocate memory");
        exit(EXIT_FAILURE);
    }
    for (size_t i = 0; i < length; i++) {
        //XOR operation
        result[i] = input[i] ^ key[i];
    }

    //null-terminate result string
    result[length] = '\0';

    return result;

}

/**
 * @brief prints out input string as 2-digit hexadecimal values
 *
 * function that prints each character in the string as a 2digit hexadecimal value
 * null terminator isnt printed
 *
 * @param input the string to print in hexadecimal
 * @param length number of characters in the input string
 *
*/

void print_hex(const char* input, size_t length) {
    //prints out each char of input string as a 2 digit hex value
    for (size_t i = 0; i < length; ++i) {
        printf("%02X ", (unsigned char)input[i]);
    }
    printf("\n");
}

/**
 * @brief main function that performs the encryption and decryption
 *
 * specifies a cleartext string to encrypt and generates a random key
 * performs the XOR operation
 *
 * @return integer indicating exit status
 *
*/

int main(void)
{
    const char* cleartext = "Secret";

    //generate key same length as the string
    size_t cleartext_length = strlen(cleartext);
    char* key = generate_key(cleartext_length);

    //prints out generated key
    printf("Key: %s\n", key);

    //encrypts the cleartext using XOR
    char* encrypted_text = perform_xor(cleartext, key, cleartext_length);

    printf("Encrypted (Hex): ");
    print_hex(encrypted_text, cleartext_length);

    //decrypt encrypted text, needs to match original cleartext
    char* decrypted_text = perform_xor(encrypted_text, key, cleartext_length);

    //prints out decrypted text
    printf("Decrypted: %s\n", decrypted_text);

    //frees out allocated memory
    free(key);
    free(encrypted_text);
    free(decrypted_text);

    return 0;
}