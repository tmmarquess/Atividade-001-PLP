import random

PHRASE = "METHINKS IT IS LIKE A WEASEL"
SIZE = 28
PROBABILITY = 5
CHARACTERS = ['A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', ' ']

def get_score(reproduced_copy) -> int:
    score = 0
    for letter in range(SIZE):
        if reproduced_copy[letter] == PHRASE[letter]:
            score += 1
    return score


def check_rate(pos) -> str:
    if random.randint(0, 99) < PROBABILITY:
        return random.choice(CHARACTERS)
    return pos


def convert_list_to_str(list: list) -> str:
    string = str()
    for letter in list:
        string += letter
    return string


initial_str = str() 
random_str = str()
reproduced_copy = str()
best_fit_copy = str()

greatest_score = 0
generation = 1

for i in range(SIZE):
    initial_str += random.choice(CHARACTERS)

random_str = initial_str

while greatest_score < 28:
    best_fit_copy = ""
    greatest_score = 0

    for i in range(100):
        reproduced_copy = list(random_str)

        for j in range(SIZE):
            reproduced_copy[j] = check_rate(reproduced_copy[j])

        print(f"--------------------{i}------------------------")
        print(f"|{convert_list_to_str(random_str)}| <- Most close the final string")
        print(f"|{convert_list_to_str(reproduced_copy)} -> score={get_score(reproduced_copy)}| <- String mutation")

        if get_score(reproduced_copy) > greatest_score:
            greatest_score = get_score(reproduced_copy)
            best_fit_copy = reproduced_copy

        if greatest_score == SIZE:
            break

    random_str = best_fit_copy
    print(f"Generation {generation} best candidate: {convert_list_to_str(random_str)} ({greatest_score})")
    generation+=1

