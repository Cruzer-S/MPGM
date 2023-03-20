#include <stdio.h>
#include <counter.h>

int main(int argc, char *argv[])
{
	int counts[4];

	counter(counts);
	for (int i = 0; i < 4; i++)
		printf("%d ", counts[i]);
	putchar('\n');

	return 0;
}
