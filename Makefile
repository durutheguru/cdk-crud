# Root Makefile

SUBDIRS := $(wildcard */)

define exec_make
	@for dir in $(SUBDIRS); do \
        if [ `./git_inspect.sh $$dir` -eq 1 ]; then \
            echo "Running in $$dir"; \
            $(MAKE) -C $$dir $(1); \
        else \
            echo "Skipping $$dir as it has no recent Git changes"; \
        fi \
    done
endef

all:
	$(call exec_make,)

test:
	$(call exec_make,test)

version:
	$(call exec_make,version)

