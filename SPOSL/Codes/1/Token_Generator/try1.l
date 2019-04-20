%{


%}


%%

[\t ]+

I |
is |
whatever |
am |
a |
genius {printf("%s: is a good word !\n",yytext);}

[a-zA-Z]+ {printf("%s: is NOT good word !\n",yytext);}

.|\n {ECHO;}

%%

int main()
{
    yyin= fopen("file2.txt","r");
    yylex();
}

int yywrap()
{

}
