#include <stdio.h>
#include <stdbool.h>
#include <string.h>

bool palindrom(char* napis, int pierwszalitera, int ostatnia) {
    if (pierwszalitera >= ostatnia) {
        return true;
    }
    if (napis[pierwszalitera] != napis[ostatnia]) {
    return false;
    }
    return palindrom(napis, pierwszalitera +1, ostatnia -1);

}

int main() 
{
    char napis[50];

    printf("Podaj slowo: ");
    scanf("%s", napis);

    int dlugosc = strlen(napis);

   if (palindrom(napis, 0, dlugosc - 1)) {
    printf("%s jest palindromem \n", napis);
   }
   else {
    printf("%s nie jest palindromem \n", napis);
   }
    
}