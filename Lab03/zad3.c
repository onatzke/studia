#include <stdio.h> 
int liczba1, liczba2;

int main() { 
    printf ("podaj pierwszą liczbę: ");
    scanf ("%d", &liczba1 );
    printf ("podaj drugą liczbę: ");
    scanf ("%d", &liczba2 );

    if (liczba1 > liczba2) {
        printf("wieksza jest pierwsza liczba: %d", liczba1);
    } 
    else {
        printf ("wieksza jest druga liczba: %d" , liczba2);

    }


    return 0;


}
