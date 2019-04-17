%{
#include"y.tab.h"
extern yylval;
%}

%%
[\t ]+

is|am|are|were|was|be|being|been|do|does|did|will|would|should|can|could|has|have|had|go|rides|paints	return VERB;

he|she 		return SUB;

poster|bike	return OBJ;

while		return CONJ;

[a-zA-z]+	printf("\n%s: Not Defined",yytext);

%% 
