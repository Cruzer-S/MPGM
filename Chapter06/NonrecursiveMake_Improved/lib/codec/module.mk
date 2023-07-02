local_dir := $(call subdirectory)
local_lib := $(local_dir)/libcodec.a
local_src := $(addprefix $(local_dir)/,codec.c)

$(eval $(call make-library, $(local_lib), $(local_src)))
