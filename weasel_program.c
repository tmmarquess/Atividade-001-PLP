#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <time.h>

#define PHRASE "METHINKS IT IS LIKE A WEASEL"
#define SIZE 28
#define PROBABILITY 5

const char CHARACTERS[] = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', ' '};

int get_score(char reproduced_copy[]){
    int score = 0;
    for(int i = 0; i < SIZE; i++){
        if(reproduced_copy[i] == PHRASE[i]){
            score++;
        }
    }
    return score;
}

char check_rate(char pos){
    if(rand() % 100 < PROBABILITY){
        return CHARACTERS[rand() % 27];
    }
    return pos;
}

int main()
{
    char initial_str[SIZE], random_str[SIZE], reproduced_copy[SIZE], best_fit_copy[SIZE];
    int greatest_score = 0, generation = 1;

    // Seeding the random generator
    srand(time(0));
    // Generating the random String
    for (int i = 0; i < SIZE; i++)
    {
        initial_str[i] = CHARACTERS[rand() % 27];
    }

    strcpy(random_str, initial_str);

    while (greatest_score < 28)
    {
        strcpy(best_fit_copy, "");
        greatest_score = 0;
        // Making 100 copies of the String
        for (int i = 0; i < 100; i++)
        {
            strcpy(reproduced_copy, random_str);

            int str_score = 0;
            // Randomly changing each character in a 5% rate to change
            for (int j = 0; j < SIZE; j++)
            {
                reproduced_copy[j] = check_rate(reproduced_copy[j]);
            }
            printf("--------------------%d------------------------\n", i);
            printf("|%s| <- Most close the final string\n", random_str);
            printf("|%s -> score=%d| <- String mutation\n", reproduced_copy, get_score(reproduced_copy));

            if (get_score(reproduced_copy) > greatest_score)
            {
                greatest_score = get_score(reproduced_copy);
                strcpy(best_fit_copy, reproduced_copy);
            }

            if(greatest_score == SIZE){
                break;
            }
        }

        strcpy(random_str, best_fit_copy);
        printf("Generation %d best candidate: %s (%d)\n", generation, random_str, greatest_score);
        generation++;
    }
}