define ten-times
  TESTS += $1
  .PHONY: $1
  $1:
	@echo $(MAKE) --no-print-directory --silent $2; \
	time $(MAKE) --no-print-directory --silent $2; \
	time $(MAKE) --no-print-directory --silent $2; \
	time $(MAKE) --no-print-directory --silent $2; \
	time $(MAKE) --no-print-directory --silent $2; \
	time $(MAKE) --no-print-directory --silent $2; \
	time $(MAKE) --no-print-directory --silent $2; \
	time $(MAKE) --no-print-directory --silent $2; \
	time $(MAKE) --no-print-directory --silent $2; \
	time $(MAKE) --no-print-directory --silent $2; \
	time $(MAKE) --no-print-directory --silent $2;
endef

.PHONY: all
all:

$(eval $(call ten-times, make-bash, -f make-bash.mk))
$(eval $(call ten-times, assign, -f assign.mk))
$(eval $(call ten-times, subst, -f subst.mk))
$(eval $(call ten-times, subst-long, -f subst-long.mk))
$(eval $(call ten-times, sed, -f sed.mk))
$(eval $(call ten-times, subshell, -f subshell.mk))

all: $(TESTS)
