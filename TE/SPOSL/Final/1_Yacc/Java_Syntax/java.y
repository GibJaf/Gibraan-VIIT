%{
#include<stdio.h>
%}
%token TYPE VAR SC C

%%

start : single|multiple
single : TYPE VAR SC {printf("Valid Declaration\n");};
multiple: TYPE VAR mul SC {printf("Valid Declaration\n");};
mul : C VAR | C VAR mul;
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

