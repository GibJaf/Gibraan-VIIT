%{
#include <stdio.h>
%}

%%

[ \t] ;
[0-9]+\.[0-9]+ {printf("Found a floating-point number:",yytext);}
[0-9]+  { printf("Found an integer:",yytext); }
[a-zA-Z0-9]+ { printf("Found a string: ",yytext); }
%%

int yywrap(void){}
int main() {
	// lex through the input
	yylex();

	return 0;
}
