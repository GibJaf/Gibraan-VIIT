%{
/*
*Program to generate tokens
*/


%}

%%
[\t ]+  			                  /* ignore whitespace */
[\n ]                                 /* ignore newine */
[a-zA-z0-9]+ 			                  {printf("\n%s",yytext);}
['{','}''(',')','.','"',';']          {printf("\n%s",yytext);}
%%

int main()
{
	yyin= fopen("file1.txt","r");
	printf("\nTOKENS:\n");
	yylex();
	printf("\n");
}


int yywrap(){

}
