#include <stdio.h>


int sortuj(int *a, int *b, int *c)
{
    int temp;
    if (*a > *b) {

        int temp = *a;
        *a = *b;
        *b = temp; 
            
    }

    if (*b > *c) {

        int temp = *c;
        *b = *c;
        *c = temp; 

        if (*a > *b) {

        int temp = *a;
        *a = *b;
        *b = temp; 
        }
       
    }

    

}

int main()
{
    int a, b, c;
    
    printf("Podaj 3 liczby oddzielone spacjami: ");
    scanf("%d %d %d", &a, &b, &c);

    sortuj(&a, &b, &c);

    printf("Po posortowaniu: %d %d %d \n", a, b, c);


    return 0;

    }