%{
#include<stdio.h>
%}
%token TYPE VAR SC

%%

statement: TYPE VAR SC {printf(" Variable declaration is valid \n");};

%%

yywrap(){return 0;}

yyerror()
{
printf(" Invalid declaration \n");
}


int main()
{
  printf(" Enter java variable declaration statement : ");
  yyparse();
  yywrap();
}
