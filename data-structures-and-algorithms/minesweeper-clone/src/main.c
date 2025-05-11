#include <stdbool.h>
#include <string.h>
#include <stdlib.h>
#include <stdio.h>

#define MAX_BUFFER_SIZE 100

#define SUCCESS 0
#define ERROR -1

#define BEGINNER 1
#define INTERMEDIATE 2
#define EXPERT 3

#define BEGINNER_HEIGHT 9
#define BEGINNER_WIDTH 9
#define BEGINNER_MINES 10

#define INTERMEDIATE_HEIGHT 16
#define INTERMEDIATE_WIDTH 16
#define INTERMEDIATE_MINES 40

#define EXPERT_HEIGHT 16
#define EXPERT_WIDTH 30
#define EXPERT_MINES 99

typedef struct
{
    int adjacentMines;
    bool isRevealed;
    bool isFlagged;
    bool hasMine;
} Square;

typedef struct
{
    Square **squares;
    int height;
    int width;
    int mines;
} Grid;

void initializeGrid(Grid *grid, int difficulty);
void displayGrid(const Grid *grid, bool isRevealed);

int getNumber();
bool isNumber(char *str);

int main()
{
    Grid grid;

    while (true)
    {
        printf(
            "Select a difficulty:\n"
            "                    \n"
            "[ 1 ] Beginner      \n"
            "[ 2 ] Intermediate  \n"
            "[ 3 ] Expert        \n"
            "                    \n");

        switch (getNumber())
        {
        case BEGINNER:
            initializeGrid(&grid, BEGINNER);
            break;
        case INTERMEDIATE:
            initializeGrid(&grid, INTERMEDIATE);
            break;
        case EXPERT:
            initializeGrid(&grid, EXPERT);
            break;
        default:
            printf("\n[ Error ] Invalid input. Try again.\n\n");
            continue;
        }

        break;
    }

    return 0;
}

void initializeGrid(Grid *grid, int difficulty)
{
    int height, width, mines;

    switch (difficulty)
    {
    case BEGINNER:
        height = BEGINNER_HEIGHT;
        width = BEGINNER_WIDTH;
        mines = BEGINNER_MINES;
        break;
    case INTERMEDIATE:
        height = INTERMEDIATE_HEIGHT;
        width = INTERMEDIATE_WIDTH;
        mines = INTERMEDIATE_MINES;
        break;
    case EXPERT:
        height = EXPERT_HEIGHT;
        width = EXPERT_WIDTH;
        mines = EXPERT_MINES;
        break;
    }

    grid->height = height;
    grid->width = width;
    grid->mines = mines;

    grid->squares = (Square **)malloc(height * sizeof(Square *));
    for (int i = 0; i < height; i++)
    {
        grid->squares[i] = (Square *)malloc(width * sizeof(Square));
    }

    for (int i = 0; i < height; i++)
    {
        for (int j = 0; j < width; j++)
        {
            grid->squares[i][j].adjacentMines = 0;
            grid->squares[i][j].isRevealed = false;
            grid->squares[i][j].isFlagged = false;
            grid->squares[i][j].hasMine = false;
        }
    }
}

void displayGrid(const Grid *grid, bool isRevealed)
{
    int height, width;

    height = grid->height;
    width = grid->width;

    printf("\n");

    for (int i = 0; i < width; i++)
    {
        printf("+---");
    }

    printf("+\n");

    for (int i = 0; i < height; i++)
    {
        for (int j = 0; j < width; j++)
        {
            printf("| %d ", grid->squares[i][j].adjacentMines);
        }

        printf("|\n");

        for (int j = 0; j < width; j++)
        {
            printf("+---");
        }

        printf("+\n");
    }
}

int getNumber()
{
    char str[MAX_BUFFER_SIZE];
    bool hasNewline;

    printf("> ");
    fgets(str, sizeof(str), stdin);

    for (int i = 0; str[i] != '\0'; i++)
    {
        if (str[i] == '\n')
        {
            hasNewline = true;
            str[i] = '\0';
            break;
        }
    }

    if (!hasNewline)
    {
        while (getchar() != '\n')
            ;
    }

    if (isNumber(str))
    {
        return atoi(str);
    }

    return ERROR;
}

bool isNumber(char *str)
{
    if (*str == '\0')
    {
        return false;
    }

    while (*str != '\0')
    {
        if (*str < '0' || *str > '9')
        {
            return false;
        }
        str++;
    }

    return true;
}