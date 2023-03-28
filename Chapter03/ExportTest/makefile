ifneq ($(MAKELEVEL), 1)
ifdef EXPORT_TO_CHILD
export export_var = I\'m exported
else
export_var = I\'m not exported
endif
endif

all:
	@echo export_var = $(export_var)
ifneq ($(MAKELEVEL), 1)
	$(MAKE)
endif
