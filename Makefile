

# Root Makefile

SUBDIRS := $(wildcard */)


test:
	@for dir in $(SUBDIRS); do \
        if [ `./git_inspect.sh $$dir` -eq 1 ]; then \
          	cd $$dir; \
            $(MAKE) test; \
        else \
            echo "Skipping $$dir as it has no recent Git changes"; \
        fi \
    done




all:
	@for dir in $(SUBDIRS); do \
        if [ `./git_inspect.sh $$dir` -eq 1 ]; then \
            $(MAKE) -C $$dir; \
        else \
            echo "Skipping $$dir as it has no recent Git changes"; \
        fi \
    done


