local_src := $(subdirectory)/ui.c

$(eval $(call make-library,$(subdirectory)/libui.a,$(local_src)))

$(eval $(compile-rules))

$(subdirectory)/ui.d: lib/db/playlist.h
