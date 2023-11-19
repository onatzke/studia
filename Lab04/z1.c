#include <stdio.h>
int main (){
    unsigned int n;
   printf("Poda liczbę naturalną: ");
   scanf("%u", &n);

   unsigned int suma_kwadratów = 0;

   for( unsigned long i = 1; i <= n; i++ )
   {
    suma_kwadratów += i * i; 
   }

   printf("Suma kwadratów od 1 do %u wynosi: %u\n", n, suma_kwadratów);


   return 0;
}