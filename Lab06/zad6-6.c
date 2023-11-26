#include <stdio.h>

int suma(int n)
{

    if (n == 0) {
        return 0;
    }

    else {
       return (n % 10 + suma(n / 10));
      
    }
    
    
}

int main ()
{
    int n;
    printf("Podaj cyfre: \n");
    scanf("%d", &n);

   printf("Suma cyfr liczby %d wynosi %d: \n", n, suma(n));

    return 0;


 }