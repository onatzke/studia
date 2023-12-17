#include <stdio.h>
#include <string.h>


void sortowanie_babelkowe (int *A, int n)
{
    int i = n, j;
    while (i != 0) {
        j = 0;
        while (j < 1) {
            if (A[j+1] < A[j]) {
                int temp = A[j];
                A[j] = A[j+1];
                A[j+1] = temp;
            }
            j++;
        }
        i--;
    }
}


int main()
{

    int A[5] = {4, 1, 2, 9, 6};
    int n = 5;
 

    sortowanie_babelkowe (A, n);

    int posortowana_A[n];

    memcpy(posortowana_A, A, sizeof(A));

    
    
    for (int i = 0; i < n; ++i) {
        
        printf("%d\n", posortowana_A[i]);
        
    }

 return 0;   

}