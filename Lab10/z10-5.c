#include <stdio.h>



void prostokat (char znak, int czy_pusty, int wys, int szer) {
    for (int i = 0; i < wys; i++) {
        for (int j = 0; j < szer; j++) {
            if (czy_pusty == 0 || i == 0 || i == wys - 1 || j == 0 || j == szer - 1) {
                printf("%c", znak);
            } else {

            printf(" ");

            }
        }

        printf ("\n");
    }
}


int main() {


    
    FILE* plik = fopen("conf.txt", "r");
     


    if (plik == NULL) {
        printf("Nie mozna otworzyć pliku \n");
        return 1;
    }

    char znak;
    int czy_pusty, wys, szer;

    fscanf(plik, "%c %d %d %d", &znak, &czy_pusty, &wys, &szer);

    fclose(plik);

    prostokat(znak, czy_pusty, wys, szer);

    return 0;

}
