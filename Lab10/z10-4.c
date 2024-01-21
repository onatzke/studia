#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>



int main() {


    
    FILE *plik1 = fopen("liczby.txt", "r");
    FILE *plikP = fopen("parzyste.txt", "w");
    FILE *plikN = fopen("nieparzyste.txt", "w");



    if (plik1 == NULL || plikP == NULL || plikN == NULL) {
        printf("Nie mozna otowrzyć pliku \n");
        return 1;
    }

    int liczba;

    while (fscanf(plik1, "%d", &liczba) == 1) {
        if (liczba % 2 == 0) {
            printf(plikP, "%d", liczba);
        } 
        else {
            printf (plikN, "%d", liczba);
        }

        
    }

    printf ("Podzial plikow zakonczony pomyslnie");

    return 0;

}
