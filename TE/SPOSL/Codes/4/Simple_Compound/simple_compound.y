%{
#include<stdio.h>
%}

%token SUB VERB OBJ CONJ

%%
sentence:simple|compound
simple:SUB VERB OBJ {printf("\nValid Simple Sentence");};
compound:simple CONJ simple {printf("\nValid Compound Sentence");};

%%
yywrap()
{
	return 0;
}
yyerror()
{
	printf("\nInvalid Sentence !!!\n");
} 
main()
{
	printf("\nEnter Sentence  : ");
	yyparse();
	yywrap();
}
