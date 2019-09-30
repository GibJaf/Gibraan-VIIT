%{
#include"y.tab.h"
#include<math.h>
extern yylval;
%}
%%

int |
char |
float |
double |
long |
String return TYPE;

";" return SC;

"," return C;

[a-zA-Z]+[a-zA-Z0-9]* return VAR;

\n return 0;

%%
