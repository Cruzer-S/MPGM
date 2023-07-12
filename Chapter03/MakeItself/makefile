.PHONY: dummy
makefile: dummy
ifneq "$(MAKE_RESTARTS)" "10"
	@echo MAKE_RESTARTS: $(if $(MAKE_RESTARTS),$(MAKE_RESTARTS),0)
	@sleep 1
	@touch $@
endif
