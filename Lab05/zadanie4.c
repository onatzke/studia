#include <stdio.h>
#include <string.h>
#define SIZE 50


int main() 
{
    char napis[20];
    int i, dlugosc;



    printf("Podaj slowo: ");
    scanf("%s", napis);

    dlugosc = strlen(napis);

    for (i = 0; i < dlugosc / 2; i++){
        if (napis[i] != napis[dlugosc - i - 1]) {
            printf("Slowo nie jest palindromem\n", napis); 

            return 0;
        }
    }

   printf("Slowo jest palindromem\n");

    return 1;



}

