#include <stdio.h>

void funkcja(int *adres1, int *adres2)
{

    int temp = *adres1;
    *adres1 = *adres2;
    *adres2 = temp;

}

int main()
{
    int zmienna1 = 3, zmienna2 = 5;

    printf("Przed wywołaniem funkcji: %d, %d\n", zmienna1, zmienna2);

    funkcja(&zmienna1, &zmienna2);
    printf("Po wywolaniu funkcji: %d, %d\n", zmienna1, zmienna2);
    
    return 0;

    }