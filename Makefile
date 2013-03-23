.PHONY: all, compile, run, open

SRC := ./src/org/dozeneyes/GenerateLevelRows.java

COMPILE_CP := lib/jexcelapi/jxl.jar 
RUNTIME_CP := build:lib/jexcelapi/jxl.jar 


compile:
	javac -classpath $(COMPILE_CP) -d build/ $(SRC)

run: 
	java -classpath $(RUNTIME_CP) org.dozeneyes.GenerateLevelRows

open:
	open gen/*.xls

all: compile, run, open
