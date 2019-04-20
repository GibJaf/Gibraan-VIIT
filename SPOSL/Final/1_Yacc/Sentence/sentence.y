%{
%}

%token SUBJECT VERB OBJECT CONJUNCTION;

%%
sentence: simple|compound
simple : SUBJECT VERB OBJECT {printf("Valid Simple sentence \n");}
compound : simple CONJUNCTION simple {printf("Valid Compound sentence \n");}
%%

yyerror(){
printf("Invalid sentence \n");
}

yywrap(){
}

int main(){
	printf(" Enter sentence : ");
	yyparse();
}
