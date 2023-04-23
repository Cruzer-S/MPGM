# $(call collect-names, root-dir, dir-list, suffix-opt)
define collect-names
	echo Making $@ from directory list...
	cd $1;								\
	shopt -s nullglob;						\
	for f in $(foreach file,$2,'$(file)'); do			\
		files=( $$f/$(if $3,*.[$3]));				\
		if (( $${#files[@]} > 0 ));				\
		then							\
			printf '"%s"\n' $${files[@]};			\
		else :; fi;						\
	done
endef

all:
	$(call collect-names,../../Chapter04/StringFunctions/,src include,ch)
