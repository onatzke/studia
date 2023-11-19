#include <stdio.h>
int main ()
{
    unsigned int a, b, c = 0, wynik = 1;
    printf("Podaj dwie liczby naturalne:\n");
    scanf("%u%u", &a, &b);

    while(wynik < b) {
        wynik *= a;
        c++;
    }

    printf("c wynosi: %u\n", c);

    return 0;

    
}