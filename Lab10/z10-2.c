#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>

int main() {

    char nazwa_pliku[100];
    char ch;

    printf("Podaj nazwe pliku: ");
    scanf("%s", nazwa_pliku);
    
    FILE *plik = fopen(nazwa_pliku, "r");

    if (plik == NULL) {
        printf("Nie mozna otowrzyć pliku \n");
    }

    while ((ch = fgetc(plik)) != EOF) {

        putchar(ch);
    }

    fclose(plik);

    return 0;

}