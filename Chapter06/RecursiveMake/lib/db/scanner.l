%{
#include <db/scanner.h>
#include <db/playlist.h>
%}

%option noyywrap

%%
playlist	return T_PLAYLIST;
track		return T_TRACK;
\"[^\"]+\"	return atoi(yytext);
.
\n

