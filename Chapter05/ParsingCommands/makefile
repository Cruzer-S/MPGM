long-command:
	@echo Line 2: A blank line follows

	@echo Line 4: A shell comment follows
	# A shell comment (leading tab)
	@echo Line 6: A Make comment follows
# A make comment, at the beginning of a line
	@echo Line 8: Indented make comments follows
    # A make comment, indented with leading spaces
        # Another make comment, indented with leading spaces
	@echo Line 11: A conditional follows
    ifdef TERM
	@echo Running on $(TERM)
    endif
	@echo Line 15: A warning "command" follows
	$(warning A warning)
	@echo Line 17: An eval "command" follows
	$(eval $(shell echo Shell echo 1>&2))
