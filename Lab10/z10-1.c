#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>

int main() {

    char* katalog_domowy = getenv("HOME");
    char sciezka_pliku[256];
    sprintf(sciezka_pliku, "%s/plik.txt", katalog_domowy);
    
    if (access(sciezka_pliku, F_OK) != -1) {
        printf ("plik.txt istnieje w katalogu domowym \n");

    }
    else {
        printf("plik.txt nie istnieje w katalogu domowym \n");
    }

    return 0;

}