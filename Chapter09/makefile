VERSION_NUMBER := 1.0
COMPANY := mythos

# Locate of trees.
SOURCE_DIR := src
OUTPUT_DIR := classes

# Unix tools
AWK := awk
FIND := /bin/find
MKDIR := mkdir -p
RM := rm -rf

# Path to support tools
JAVA_HOME := /usr

# Java tools
JAVA := $(JAVA_HOME)/bin/java
JAVAC := $(JAVA_HOME)/bin/javac

JFLAGS := -sourcepath $(SOURCE_DIR)	\
		  -d $(OUTPUT_DIR)			\
		  -source 17

JVMFLAGS := -ea -esa -Xfuture

JVM := $(JAVA) $(JVMFLAGS)

JAR := $(JAVA_HOME)/bin/jar
JARFLAGS := cf

JAVADOC := $(JAVA_HOME)/bin/javadoc
JDFLAGS := -sourcepath $(SOURCE_DIR)	\
		   -d $(OUTPUT_DIR)				\
		   -link http://java.sun.com/products/jdk/17/docs/api

# Jars
JUNIT_JAR := ./jars/junit-platform-console-standalone-1.9.3.jar

DEPENDENCIES := $(JUNIT_JAR)

class_path := OUTPUT_DIR			\
			  JUNIT_JAR

space := $(empty) $(empty)
# $(shell build-classpath, variable-list)
define build-classpath
$(strip									\
$(patsubst :%,%,						\
$(subst : ,:,							\
$(strip									\
$(foreach j,$1,$(call get-file,$j):)))))
endef

# $(call get-file, variable-name)
define get-file
$(strip $($1) $(if $(call file-exists-eval,$1),,	\
$(warning The file referenced by variable			\
'$1' ($($1) cannot be found))))
endef

# $(call file-exists-eval, variable-name)
define file-exists-eval
$(strip $(if $($1),,				\
$(warning '$1' has no value))		\
$(wildcard $($1)))
endef

# $(call brief-help, makefile)
define brief-help
$(AWK) '$$1 ~ /^[^.$$][-A-Za-z0-9]*:/					\
	{ print substr($$1, 1, length($$1)-1) }' $1	|	\
sort | pr -T -w 80 -4
endef

# $(call file-exists, wildcard-pattern)
file-exists = $(wildcard $1)

# $(call check-file, file-list)
define check-file
$(foreach f, $1,					\
$(if $(call file-exists, $($f)),,	\
$(warning $f ($($f)) is missing)))
endef

# $(call make-temp-dir, root-opt)
define make-temp-dir
$(shell mktemp -dt $(if $1,$1,make).XXXXXXXXXX)
endef

# MANIFEST_TEMPLATE - Manifest input to m4 macro processor
MANIFEST_TEMPLATE := src/manifest/manifest.mf
TMP_JAR_DIR := $(call make-temp-dir)
TMP_MANIFEST := $(TMP_JAR_DIR)/manifest.mf

# $(call add-manifest, jar, jar-name, manifest-file-opt)
define add-manifest
$(RM) $(dir $(TMP_MANIFEST))
$(MKDIR) $(dir $(TMP_MANIFEST))
m4 --defin=NAME="$(notdir $2)"					\
	--define=IMPL_VERSION=$(VERSION_NUMBER)		\
	--define=SPEC_VERSION=$(VERSION_NUMBER)		\
	$(if $3,$3,$(MANIFEST_TEMPLATE))			\
	> $(TMP_MANIFEST)
$(JAR) -ufm $1 $(TMP_MANIFEST)
$(RM) $(dir $(TMP_MANIFEST))
endef

# $(call make-jar,jar-variable-prefix)
define make-jar
.PHONY: $1 $$($1_name)
$1: $($1_name)
$$($1_name):
	cd $(OUTPUT_DIR);	\
	$(JAR) $(JARFLAGS) $$(notdir $$@) $$($1_packages)
	$$(call add-manifest,$$@,$$($1_name),$$($1_manifest))
endef

# Set the CLASSPATH
export CLASSPATH := $(call build-classpath, $(class_path))

# make-directories - Ensure output directory exists.
make-directories := $(shell $(MKDIR) $(OUTPUT_DIR); \
							$(MKDIR) ./jars)

# help - The default goal
.PHONY: help
help:
	@$(call brief-help, $(CURDIR)/[Mm]akefile)

# all - Perform all tasks for a complete build
all: compile jars javadoc

# all_javas - Temp file for holding source file list
all_javas := $(OUTPUT_DIR)/all.javas

# compile - Compile the source
.PHONY: compile
compile: $(DEPENDENCIES)
compile: $(all_javas)
	$(JAVAC) $(JFLAGS) @$<

# all_javas - Gather source file list
.INTERMEDIATE: $(all_javas)
$(all_javas):
	$(FIND) $(SOURCE_DIR) -name '*.java' > $@

# jar_list - List of all jars to create
jar_list := comparison_compactor_jar

# jars - Create all jars
.PHONY: jars
jars: $(jar_list)

# ComparisonCompactor - Create the $(ComparisonCompactor)
comparison_compactor_jar_name := $(OUTPUT_DIR)/ComparisonCompactor.jar
comparison_compactor_jar_manifest := src/com/$(COMPANY)/manifest/ComparisonCompactor.mf
comparison_compactor_jar_packages := com/$(COMPANY)/ComparisonCompactor.class

# Create an explicit rule for each jar
# $(foreach j, $(jar_list), $(eval $(call make-jar,$j)))
$(eval $(call make-jar,comparison_compactor_jar))
# $(eval $(call make-jar,ui_jar))

$(JUNIT_JAR):
	cd ./jars/ && wget https://repo1.maven.org/maven2/org/junit/platform/junit-platform-console-standalone/1.9.3/junit-platform-console-standalone-1.9.3.jar

# javadoc - Generate the Java doc from sources
.PHONY: javadoc
javadoc: $(all_javas)
	$(JAVADOC) $(JDFLAGS) @$<

.PHONY: clean
clean:
	$(RM) $(OUTPUT_DIR)

.PHONY: classpath
classpath:
	@echo CLASSPATH='$(CLASSPATH)'

.PHONY: check-config
check-config:
	@echo Checking configuration...
	$(call check-file, $(class_path) JAVA_HOME)

.PHONY: run
run:
	@java -cp $(OUTPUT_DIR) com.$(COMPANY).Main

.PHONY: test
test:
	@java -jar jars/junit-platform-console-standalone-1.9.3.jar -cp classes/ --select-package com.mythos

.PHONY: print
print:
	$(foreach v, $(V),	\
		$(warning $v = $($v)))
