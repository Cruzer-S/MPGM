source-to-object = $(subst .c,.o,$(filter %.c,$1))	\
				   $(subst .y,.o,$(filter %.y,$1))	\
				   $(subst .l,.o,$(filter %.l,$1))

subdirectory = $(patsubst %/module.mk,%,							\
			   $(word $(words $(MAKEFILE_LIST)),$(MAKEFILE_LIST)))

# $(call make-library, library-name, source-file-list) 
define make-library
libraries += $1
sources += $2

$1: $(call source-to-object,$2)
	$(AR) $(ARFLAGS) $$@ $$^
endef

generate-source = $(subst .y,.h,$(filter %.y,$1)) \
				  $(subst .y,.c,$(filter %.y,$1)) \
				  $(subst .l,.c,$(filter %.l,$1))

modules := lib/codec lib/db lib/ui app/player

programs :=
sources :=
libraries :=
extra_clean :=

objects = $(call source-to-object,$(sources))
dependencies = $(subst .o,.d,$(objects))

include_dirs := lib include
CPPFLAGS += $(addprefix -I,$(include_dirs))

vpath %.h $(include_dirs)

MV := mv -f
RM := rm -f
SED := sed

all:

include $(addsuffix /module.mk,$(modules))

.PHONY: all
all: $(programs)

.PHONY: libraries
libraries: $(libraries)

.PHONY: clean
clean:
	@echo $(call generate-source,$(sources))
	$(RM) $(objects) $(programs)				\
		  $(libraries) $(dependencies)			\
		  $(call generate-source,$(sources))

ifneq "$(MAKECMDGOALS)" "clean"
include $(dependencies)
endif

%.c %.h: %.y
	$(YACC.y) -d $<
	$(MV) y.tab.c $*.c
	$(MV) y.tab.h $*.h

%.d: %.c
	$(CC) $(CFLAGS) $(CPPFLAGS) $(TARGET_ARCH) -MG -M $< | \
	$(SED) 's,\($(notdir $*)\.o\) *:,$(dir $@)\1 $@: ,' > $@.tmp
	$(MV) $@.tmp $@
