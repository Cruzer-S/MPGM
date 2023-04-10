AWK := gawk
KILL := kill
KILL_FLAGS := -s KILL
PS := ps
PS_FLAGS := --format "pid:8=PID fname:8=COMMAND"
PS_FIELDS := "11 47 100"

# $(call kill-program,awk-pattern)
define kill-program
	@$(PS) $(PS_FLAGS) |					\
	$(AWK) '$$2 == "$1" {					\
			printf("Killing $1 (pid: %d)\n", $$1);	\
			system("$(KILL) $(KILL_FLAGS) " $$1)	\
		}'
endef

all:
	$(call kill-program,make)
