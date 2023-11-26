#include <stdio.h>

void liczby(int n)
{
    if (n > 0) {
        liczby (n-1);
        printf("%d", n);

    }
    
    
}

int main ()
{
    int n;
    printf("Podaj liczbe: \n");
    scanf("%d", &n);



   liczby(n);
   printf("\n");

    return 0;


 }