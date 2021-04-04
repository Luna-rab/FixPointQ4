.PHONY: all
all: ;

.PHONY: test
test: ChechTest.class WatchLog.class
	java WatchLog Test\Input\input1.txt 1 2 1000| java CheckTest Test\Output\output1_1_2_1000.txt
	java WatchLog Test\Input\input2.txt 1 2 1000| java CheckTest Test\Output\output2_1_2_1000.txt
	java WatchLog Test\Input\input3.txt 1 2 1000| java CheckTest Test\Output\output3_1_2_1000.txt
ChechTest.class:
	javac CheckTest.java
WatchLog.class:
	javac WatchLog.java

.PHONY: clean
clean:
	del /q *.class
	del /q Server\*.class
	del /q Log\*.class