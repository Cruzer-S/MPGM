SPACE := $(subst ,, )

PATH := $(strip $(subst ::,:.:,$(patsubst :%,.:%,$(patsubst %:,%:.,$(PATH)))))

SPATH := $(sort $(subst :, ,$(subst $(SPACE),\\,$(PATH))))

find-program = $(wildcard $(subst \\,\$(SPACE),$(addsuffix /$1,$(SPATH))))

all:
	########################################################################
	# PATH: $(PATH) 
	########################################################################
	# SPATH: $(SPATH)
	########################################################################
	# total program: $(words $(call find-program,*))
	########################################################################
