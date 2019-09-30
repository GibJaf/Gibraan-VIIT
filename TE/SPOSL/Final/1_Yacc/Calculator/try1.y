%{
#include<stdio.h>
%}

%token NAME NUMBER
%%

statement: NAME '=' expression
    |      expression           {printf("= %d\n",$1);}
    ;

expression: expression '+' NUMBER {$$ = $1 + $3;}
    |       expression '-' NUMBER {$$ = $1 - $3;}
    |       expression '*' NUMBER {$$ = $1 * $3;}
    |       expression '/' NUMBER
                    { if($3 == 0)
                        yyerror(" Divide by zero error.");
                      else
                        $$ = $1 / $3; }

    |       NUMBER                {$$ = $1;}
    ;

%%

yywrap(){ return 0; }

yyerror()
{
	printf(" Not a valid expression ");
}

main()
{
	printf("\n Enter arithmetic expression to evaluate : ");
	yyparse();
	yywrap();
}
