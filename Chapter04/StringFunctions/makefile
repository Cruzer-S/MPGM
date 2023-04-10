VPATH = src include
CPPFLAGS = -I include

SOURCES = count_words.c	\
	  lexer.c	\
	  counter.c

count_words: counter.o lexer.o -lfl
count_words.o: counter.h
lexer.o: lexer.h

ifneq "$(MAKECMDGOALS)" "clean"
include $(subst .c,.d,$(SOURCES))
endif

%.d: %.c
	$(CC) -M $(CPPFLAGS) $< > $@.$$$$;			\
	sed 's,\($*\)\.o[ :]*,\1.o $@ : ,g' < $@.$$$$ > $@;	\
	rm -f $@.$$$$

clean:
	rm -f count_words *.o *.d *.c
