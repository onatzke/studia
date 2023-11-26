#include <stdio.h>

int binarna(int n)
{

    if (n == 0) {
        return 0;
    }

    else {
       return (n % 2 + 10 * binarna(n / 2));
      
    }
    
    
}

int main ()
{
    int n;
    printf("Podaj liczbe dziesietna: \n");
    scanf("%d", &n);

   printf("Liczba dzisietna %d w systemie binarnym: %d \n", n, binarna(n));

    return 0;


 }