

# Main Makefile

SUBDIRS := $(wildcard */)

all:
	@for dir in $(SUBDIRS); do \
        if [ `./git_inspect.sh $$dir` -eq 1 ]; then \
            $(MAKE) -C $$dir; \
        else \
            echo "Skipping $$dir as it has no recent Git changes"; \
        fi \
    done
