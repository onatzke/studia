#include <stdio.h>
#include <string.h>

void sortowanie_babelkowe(int *A, int n)
{
    int i = n-1, j;
    while (i != 0) {
        j = 0;
        while (j < i) {
            if (A[j+1] < A[j]) {
                int temp = A[j];
                A[j] = A[j+1];
                A[j+1] = temp;
            }
            j++;
            for (int i = 0; i < n; i++)
            {
                printf("%d", A[i]);
            }

            printf("\n");

        }
        i--;
    }
}

int main() 
{
    int A[] =  {4, 1, 2, 9, 6};
    int n = sizeof(A) / sizeof(A[0]);

    printf("%ld\n", sizeof(A));
    printf("%ld\n", sizeof(A[0]));


    sortowanie_babelkowe(A, n);

   

  
    for (int i = 0; i < n; i++)
    {
        printf("%d\n", A[i]);
    }

    return 0;
}