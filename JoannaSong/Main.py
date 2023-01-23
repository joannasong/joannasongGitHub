import os


def createGrid(location):
    board = (f"|{location[1]}|{location[2]}|{location[3]}|\n"
             f"|{location[4]}|{location[5]}|{location[6]}|\n"
             f"|{location[7]}|{location[8]}|{location[9]}|")
    print(board)


def checkTurn(turn):
    if turn % 2 == 0:
        return "O"
    else:
        return "X"


def checkWin(location):
    # check for horizontal wins
    if (location[1] == location[2] == location[3]) \
            or (location[4] == location[5] == location[6]) \
            or (location[7] == location[8] == location[9]):
        return True

    # check for vertical wins
    elif (location[1] == location[4] == location[7]) \
            or (location[2] == location[5] == location[8]) \
            or (location[3] == location[6] == location[9]):
        return True

    # check for diagonal wins
    elif (location[1] == location[5] == location[9]) \
            or (location[3] == location[5] == location[7]):
        return True

    else: return False


location = {1: "1", 2: "2", 3: "3", 4: "4", 5: "5", 6: "6", 7: "7", 8: "8", 9: "9"}
playing = True
finished = False
turn = 0
lastTurn = -1

while playing:
    # resets screen
    os.system("cls" if os.name == "nt" else "clear")

    # displays which player's turn it is and gives instructions
    print("Player " + str(checkTurn(turn + 1)) + "'s turn: Pick a spot or enter 'q' to quit")
    createGrid(location)

    # gets user's move
    move = input()
    if move == "q":
        print("Thanks for playing!")
        playing = False

    # check if player entered a valid number between 1 and 9
    elif str.isdigit(move) and int(move) in location:
        # check if there is already an 'X' or 'O' occupying that space
        if not location[int(move)] in {"X", "O"}:
            # player entered a valid input; updates the board
            turn += 1
            location[int(move)] = checkTurn(turn)
        else:
            print("Invalid spot selected. Please select another")
    else:
        print("Invalid spot selected. Please select another")

    # check if the game is over and if someone won
    if checkWin(location): playing, finished = False, True
    if turn > 8: playing = False

# print results
os.system("cls" if os.name == "nt" else "clear")
createGrid(location)

# if there is a winner print which player won
if finished:
    if checkTurn(turn) == "X": print("Player X Wins!")
    else: print("Player O wins!")

# if game ended in tie
else: print("Tie!")

# finish process
print("Thanks for playing!")