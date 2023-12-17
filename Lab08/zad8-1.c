//WEJŚCIE: Tablica TAB[1..N], element n
// WYJŚCIE: True jeśli n znajduje się w TAB, False w przeciwnym przypadku

// index = 1
// wynik = False
// Dopóki index <= N wykonuj:
   //Jeśli TAB[index] == n to:
      //wynik = True
   //index = index + 1
// Wypisz wynik

#include <stdio.h>
#include <stdbool.h>

bool przeszukiwanie_liniowe (int *TAB, int n, int N)
{
    int index = 1;
    int wynik = false;
    while (index <= N) {
    if (TAB[index] == N){
        wynik = true;
    }
    index++;
    }

    if (wynik) {
        printf("wynik: %d\n", wynik); 
    }

    else {
        printf("n nie nalezy do do tablicy.\n");
    }
}


int main ()
{

    int TAB[] = {1, 2, 4, 6, 9}, n;
    int N = sizeof(TAB) / sizeof(TAB[0]);
    
    
    printf("Podaj n: \n");
    scanf("%d", &n);


    przeszukiwanie_liniowe(TAB, n, N);




 return 0;   
}