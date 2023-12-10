#include <stdio.h>


int zmienna_globalna;

int funkcja(int zmienna_globalna, int zmienna_lokalna) 
{

    zmienna_globalna = 1;
    zmienna_lokalna = 2;

    printf("Adres zmiennej globalbej po przypisaniu wartosci 1: %p\n", &zmienna_globalna);
    printf("Adres zmiennej lokalnej po przypisaniu wartosci 2: %p\n", &zmienna_lokalna);


}

int main() 
{

    int zmienna_lokalna = zmienna_globalna;

    printf("Adres zmiennej globalbej: %p\n", &zmienna_globalna);
    printf("Adres zmiennej lokalnej: %p\n", &zmienna_lokalna);


    funkcja (zmienna_globalna, zmienna_lokalna);



return 0;

}
