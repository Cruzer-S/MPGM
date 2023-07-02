local_dir := $(call subdirectory)
local_lib := $(local_dir)/libui.a
local_src := $(addprefix $(local_dir)/,ui.c)

$(eval $(call make-library, $(local_lib), $(local_src)))
