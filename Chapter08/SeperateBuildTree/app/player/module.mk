local_pgm := $(subdirectory)/player
local_src := $(subdirectory)/player.c
local_objs := $(call source-to-object,$(local_src))

sources += $(local_src)
programs += $(local_pgm)

$(local_pgm): $(local_objs) $(libraries)
