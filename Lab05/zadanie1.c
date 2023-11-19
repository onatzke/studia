#include <stdio.h>

int main() 
{
    float liczba1, liczba2, wynik;
    char wybor;

    printf("Podaj pierwszą liczbę: ");
    scanf("%f", &liczba1);
    printf("Podaj drugą liczbę: ");
    scanf("%f", &liczba2);

    printf("wpisz odpowiednia liczbe, zeby wybrac dzialanie\n");
    printf("1 - dodawanie\n");
    printf("2 - odejmowanie\n");
    printf("3 - mnożenie\n");
    printf("4 - dzielenie\n");
    scanf(" %c" , &wybor);


    switch (wybor) {
        case '1':
        wynik = liczba1 +liczba2;
        printf("%.2f + %.2f = %.2f\n", liczba1, liczba2, wynik);
        break;
        case '2':
        wynik = liczba1 - liczba2;
        printf("%.2f - %.2f = %.2f\n", liczba1, liczba2, wynik);
        break;
        case '3':
        wynik = liczba1 * liczba2;
        printf("%.2f * %.2f = %.2f\n", liczba1, liczba2, wynik);
        break;
        case '4':
        if (liczba2 !=0) {
            wynik = liczba1 / liczba2;
        printf("%.2f / %.2f = %.2f\n", liczba1, liczba2, wynik);
        }
        else {
            printf("Nie mozna dzielic przez 0\n");
        }
        break;
    }



    return 0;
}