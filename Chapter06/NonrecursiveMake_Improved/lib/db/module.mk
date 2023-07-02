local_dir := $(subdirectory)
local_lib := $(local_dir)/libdb.a
local_src := $(addprefix $(local_dir)/,playlist.y scanner.l)

$(eval $(call make-library, $(local_lib), $(local_src)))
