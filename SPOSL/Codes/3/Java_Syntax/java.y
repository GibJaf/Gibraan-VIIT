%{
#include<stdio.h>
%}
%token TYPE VAR SC

%%
start:TYPE VAR SC {printf("Valid Declaration\n");};
%%

yywrap(){return 0;}

yyerror()
{
	printf("Error in Declaration of variable\n");
}

main()
{
	printf("\nEnter syntax of variable declaration in java : ");
	yyparse();
	yywrap();
}

