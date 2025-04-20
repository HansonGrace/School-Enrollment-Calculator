/**
* @file hw1_wvuep.c
* @author Grace Hanson
* @brief functions that calculate and estimate enrollment
* growth based on inputs and formulas
*
*/

#include <stdio.h>
#include <stdlib.h>
#include <ctype.h>
#include "hw1_wvuep.h"
#include <math.h>

/**
* @brief returns name of programmer
*
* returns a const string
*
* @return a const string with the programmers name
*/
const char* get_name() {
	return "Grace Hanson";
}

/**
* @brief returns programmers name
*
* returns programmers name as a string
*
* @return a constant string with programmers name
*/
const char* get_programmer_name() {
	return "Grace Hanson";
}

/**
 * @brief prompts user for a target enrollment number for a given year
 *
 * function that repeatedly asks the user for the enrollment target until
 * a valid positive integer is entered it ensures the value is greater than 0
 *
 * @param target_year target year for which the enrollment is being calculated
 * @return target enrollment value entered by the user
 */
int prompt_target_enrollment(int target_year) {
	int target_enrollment = 0;
	char input[100]; ///stores input from the user

	while (1) {
		printf("Enter the enrollment target for the year %d: ", target_year);
		fgets(input, sizeof(input), stdin); ///gets input from the user

		int is_valid = 1; ///checks if input is greater than 0

		///for loop that checks if the input is a valid number
		for (int i = 0; input[i] != '\0' && input[i] != '\n'; i++) {
			if (!isdigit(input[i])) {
				is_valid = 0;
				break;
			}
		}

		///if valid, convert input to an integer
		if (is_valid) {
			target_enrollment = atoi(input);
			if (target_enrollment > 0) {
				break; ///input is valid so break the loop
			}
			else {
				printf("Target must be larger than 0. Try again\n");
			}
		}
		else {
			printf("Invalid input. Please enter a positive number.\n");
		}
	}
	return target_enrollment;
}

/**
 * @brief calculates annual growth rate between two enrollment values
 *
 * function that calculates the growth rate using the formula for annual growth rate
 * based on the initial and target enrollments and their corresponding years
 *
 * @param initial_enrollment the initial enrollment number
 * @param target_enrollment the target enrollment number
 * @param initial_year the year of the initial enrollment
 * @param target_year the target year for enrollment
 * @return the calculated growth rate
 */
double calculate_growth_rate(int initial_enrollment, int target_enrollment, int initial_year, int target_year) {

	if (initial_enrollment <= 0 || target_enrollment <= 0 || target_year <= initial_year) {
		printf("Invalid input values.\n");
		return -1; ///return error if invalid input is provided
	}

	///calculates the # of years between initial and target years
	double years_diff = (double)(target_year - initial_year);

	///calculate using formula for annual growth rate
	double growth_rate = pow((double)target_enrollment / initial_enrollment, 1.0 / years_diff) - 1;

	return growth_rate;
}

/**
 * @brief returns a description based on the growth rate
 *
 * this function returns a description of the required growth rate based on the
 * value of the growth rate the descriptions range from "negative" to "unreasonable"
 *
 * @param growth_rate the calculated growth rate
 * @return string describing the growth rate
 */
const char* get_growth_rate_description(double growth_rate) {
	///returns description based on growth rate
	if (growth_rate < 0) {
		return "negative";
	}
	else if (growth_rate >= 0 && growth_rate < 0.01) {
		return "reasonable";
	}
	else if (growth_rate >= 0.01 && growth_rate < 0.02) {
		return "ambitious";
	}
	else if (growth_rate >= 0.02 && growth_rate < 0.04) {
		return "high";
	}
	else {
		return "unreasonable";
	}
}

/**
 * @brief prints the growth rate and its description
 *
 * function that prints the calculated growth rate as a percentage and its
 * corresponding description (e.g., "reasonable", "unreasonable")
 *
 * @param growth_rate the growth rate to be printed
 */
void print_growth_rate(double growth_rate) {
	///grabs description from get_growth_rate_description function
	const char* description = get_growth_rate_description(growth_rate);

	///prints growth rate as a percent and its description
	printf("The required annual rate of growth, %.1f%%, is %s.\n", growth_rate * 100, description);
}

/**
 * @brief estimates the enrollment in a future year based on the growth rate
 *
 * this function calculates the estimated enrollment in a future year based on the
 * initial enrollment, growth rate, and number of years between the initial and estimate years
 *
 * @param initial_enrollment the initial enrollment number
 * @param growth_rate the annual growth rate
 * @param initial_year the year of the initial enrollment
 * @param estimate_year the future year for which the enrollment estimate is required
 * @return the estimated enrollment in the future year
 */
int calculate_enrollment_estimate(int initial_enrollment, double growth_rate, int initial_year, int estimate_year) {
	///calculates number of years between initial year and  estimate year
	int years_diff = estimate_year - initial_year;
	///estimates the enrollment
	double estimated_enrollment = initial_enrollment * pow(1 + growth_rate, years_diff);

	///rounds to nearest int and returns it
	return(int)round(estimated_enrollment);
}

/**
 * @brief prints the estimated enrollments for each year from the initial year to the end year
 *
 * this function prints the estimated enrollments for each year starting from the
 * initial year up to the end year, based on the initial enrollment and growth rate
 *
 * @param initial_enrollment the initial enrollment number
 * @param growth_rate the annual growth rate
 * @param initial_year the year of the initial enrollment
 * @param end_year the final year for which estimates are to be printed
 */
void print_enrollment_estimates(int initial_enrollment, double growth_rate, int initial_year, int end_year) {

	///for loop to loop through each year from initial to end
	for (int year = initial_year; year <= end_year; year++) {
		///gets estimate for current year
		int enrollment_estimate = calculate_enrollment_estimate(initial_enrollment, growth_rate, initial_year, year);
		///prints year and enrollment estimate
		printf("%d enrollment estimate: %d\n", year, enrollment_estimate);
	}
}
