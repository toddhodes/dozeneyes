.PHONY: all, compile, run, open

SRC := ./src/org/dozeneyes/*.java

COMPILE_CP := lib/jexcelapi/jxl.jar 
RUNTIME_CP := build:lib/jexcelapi/jxl.jar 


compile:
	javac -classpath $(COMPILE_CP) -d build/ $(SRC)

run: compile
	java -classpath $(RUNTIME_CP) org.dozeneyes.GenerateLevelRows 1 2 3 4 5

open: run
	open gen/*.xls

all: compile, run, open
