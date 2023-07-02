space := $(empty) $(empty)

space-to-question = $(subst $(space),?,$1)
question-to-space = $(subst ?,$(space),$1)

$(call space-to-question,foo bar): $(call space-to-question,bar baz)
	touch "$(call question-to-space,$@)"

$(call space-to-question,bar baz):
	touch "$(call question-to-space,$@)"

clean:
	rm -f 'bar baz' 'foo bar'
