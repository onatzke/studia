#include <stdio.h>

int main()
{
    int tablica [10];

    printf("Podaj elementy tablicy 10 elementowej:\n");
    for (int i = 0; i < 10; i++)
    {
        scanf("%d", &tablica[i]);
    }

    for (int i = 0; i < 10; i++)
    {
        printf("%p\n", &tablica[1]);
    }

    return 0;

}