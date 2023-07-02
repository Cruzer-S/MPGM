local_dir := $(call subdirectory)
local_pgm := $(local_dir)/player
local_src := $(addprefix $(local_dir)/,player.c)
local_objs := $(subst .c,.o,$(local_src))

sources += $(local_src)
programs += $(local_pgm)

$(local_pgm): $(local_objs) $(libraries)
