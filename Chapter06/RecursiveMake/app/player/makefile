lib_dir := $(addprefix ../../,$(libraries))

program := player

sources := player.c
libraries := -ldb -lcodec -lui

vpath %.a $(lib_dir)

all: $(program)

include ../../common.mk

$(program): $(objects) $(libraries)
