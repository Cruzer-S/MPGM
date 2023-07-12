VPATH		= src include deps
CPPFLAGS	= -I include

SOURCES		= count_words.c \
		  lexer.c	\
		  counter.c

count_words: counter.o lexer.o -lfl
count_words.o: counter.h
counter.o: counter.h lexer.h
lexer.o: lexer.h

%.d: %.c
	@set -e; rm -f $@;					\
	$(CC) -M $(CPPFLAGS) $< > $@.$$$$;			\
	sed 's,\($*\)\.o[ :]*,\1.o $@: ,g' < $@.$$$$ > deps/$@;	\
	rm -f $@.$$$$

.PHONY: clean
clean:
	rm -f deps/* *.o count_words

-include $(subst .c,.d,$(SOURCES))
