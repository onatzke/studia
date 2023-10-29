#include<stdio.h>

int main(){

    char znak;
    int odpowiedz, kod;

    printf("jeśli chcesz podać znak ASCII wpisz 1 a jesli chesz wpisać kod ASCII wpisz 2:\n");
    scanf ("%d" , &odpowiedz );

    if (odpowiedz == 1) {
        printf ("Wpisz dowolny znak:\n" );
        scanf (" %c" , &znak);
        if ((znak >= 'a' && znak <= 'z' ) || (znak >= 'A' && znak <= 'Z')) {
        printf("%c jest literą alfabetu.\n" , znak); }
    

    } else if (odpowiedz == 2) {
        printf ("wpisz dowolny kod ASCII:\n");
        scanf ("%d" , &kod);
        if ((kod >= 97 && kod <= 122) || (kod >=65 && kod <= 90)) {
            printf ("%d jest literą alfabetu.\n", kod);
        } 
        else {
            printf ("%d nie jest literą alfabetu.\n" , kod);

        }
    }
    else  { 
         printf ("Niepoprawnie wybrany kod.");

        }

        return 0;

    


}