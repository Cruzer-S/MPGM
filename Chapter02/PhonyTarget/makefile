.PHONY: make-documentation
make-documentation: df

df:
	@df -kH . | awk 'NR == 2 { printf("%s available\n", $$4) }'
