count_words: count_words.o lexer.o -lfl
	gcc count_words.o lexer.o -lfl -o count_words

count_words.o: count_words.c
	gcc -c count_words.c

# lexer.c: lexer.l
# 	flex -t lexer.l > lexer.c
lexer.c: lexer_improved.l
	flex -t lexer_improved.l > lexer.c
