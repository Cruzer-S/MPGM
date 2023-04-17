SPACE := $(subst ,, )

make-path = $(subst \\\\,\$(SPACE),			\
             $(subst :, ,				\
              $(subst $(SPACE),\\\\,			\
               $(subst ::,:.:,				\
	        $(patsubst :%,.:%,			\
	         $(patsubst %:,%:.,$(PATH)))))))

find-program = $(wildcard 				\
		$(addsuffix /$1,			\
		 $(sort	$(call make-path))))
		  

all:
	# total program: $(words $(call find-program,*))
