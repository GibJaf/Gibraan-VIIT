%{
#include"y.tab.h"
#include<math.h>
extern yylval;
%}
%%

int |
char |
float |
String return TYPE;

";" return SC;

[a-zA-Z]+[a-zA-Z0-9]* return VAR;

%%
