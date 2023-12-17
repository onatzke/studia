
#include <stdio.h>
#include <stdbool.h>
#include <math.h>

bool przeszukiwanie_binarne (int *TAB, int N, int n)
{
    int left = 0, right = N, middle;
    bool result = false;
    while (left <= right) {
        middle = floor((left + right) / 2);
    if (TAB[middle] < n){
        left = middle + 1;
    }
    else if (TAB[middle] > n) {
        right = middle - 1;
    }
    else { 
        result = true;
        break;
        }
    }

    return result;
}


int main ()
{

    int TAB[] = {1, 2, 4, 6, 9}, n;
    int N = sizeof(TAB) / sizeof(TAB[0]);

    
    printf("Podaj n: \n");
    scanf("%d", &n);
    

    bool result = przeszukiwanie_binarne(TAB, N, n);

    if (result) {
        printf("n znajduje sie w tablicy\n");
    }

    else {
        printf("n nie znajduje sie w tablicy\n");
    }




 return 0;   
}