#include <stdio.h>

int main () 
{
    unsigned int a, b;
    printf("Podaj dwie liczby naturalne:\n");
    scanf("%u%u", &a, &b);

    unsigned long long wynik = 1; 

    for(unsigned int i = 0; i < b; i++) {
        wynik *= a;
    }

    printf("Wynik działania %u do potęgi %u wynosi: %llu\n,", a, b, wynik);
    
    return 0;

}