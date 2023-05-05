player := app/player

lib_codec := lib/codec
lib_db := lib/db
lib_ui := lib/ui

libraries := $(lib_codec) $(lib_db) $(lib_ui)

.PHONY: all $(player) $(libraries)
all: $(player)

$(player) $(libraries):
	$(MAKE) --directory=$@ $(TARGET)

$(player): $(libraries)
$(lib_ui): $(lib_db) $(lib_codec)

clean:
	$(MAKE) TARGET=clean

export libraries
