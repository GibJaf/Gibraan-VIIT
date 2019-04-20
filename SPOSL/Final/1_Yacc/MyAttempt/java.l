%{
#include "y.tab.h"
extern int yylval;
%}

%%

int |
float |
char |
String  {return TYPE;}

";"	{return SC;}

[a-zA-Z]+[a-zA-Z0-9]* {return VAR;}


%%
