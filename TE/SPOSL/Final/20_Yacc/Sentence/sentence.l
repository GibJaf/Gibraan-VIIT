%{
#include "y.tab.h"
%}

%%
\n return 0;

I|he|she|i|they return SUBJECT;

ride|rides|paint|paints return VERB;

bike|poster return OBJECT;

while|and|but return CONJUNCTION;

.

%%
