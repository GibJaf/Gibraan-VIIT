%{
unsigned int WordCount=0 , CharCount=0 , LineCount=0;
%}

word [^ \t\n]+
eol \n

%%

{word} { WordCount++; CharCount+=yyleng;}
{eol} { LineCount++; CharCount++;}
.   CharCount++;

%%

int main(){
    yyin= fopen("file3.txt","r");
    yylex();
    printf("Character Count = %d\n",CharCount);
    printf("Word Count = %d\n",WordCount);
    printf("Line Count = %d\n",LineCount);
}

