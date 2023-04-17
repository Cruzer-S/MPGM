EMPTY := 
SPACE := $(EMPTY) $(EMPTY)
COMMA := ,

debug-enter = $(if $(debug_trace),$(warning Entering $0($(echo-args))))
debug-leave = $(if $(debug_trace),$(warning Leaving $0))

echo-args = $(subst $(SPACE),$(COMMA)$(SPACE),$(strip \
		$(foreach n,1 2 3 4 5 6 7 8 9,'$($n)')))

debug_trace := 1

define a
	$(debug-enter)
	@echo $1 $2 $3
	$(debug-leave)
endef

define b
	$(debug-enter)
	$(call a,$1,$2,hi)
	$(debug-leave)
endef

trace-macro:
	$(call b,5,$(MAKE))
