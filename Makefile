.PHONY: all
all: ;

.PHONY: test
test: ChechTest.class WatchLog.class
	java WatchLog Test\Input\input1.txt | java CheckTest Test\Output\output1.txt
	java WatchLog Test\Input\input2.txt | java CheckTest Test\Output\output2.txt
	java WatchLog Test\Input\input3.txt | java CheckTest Test\Output\output3.txt
	java WatchLog Test\Input\input4.txt | java CheckTest Test\Output\output4.txt
ChechTest.class:
	javac CheckTest.java
WatchLog.class:
	javac WatchLog.java

.PHONY: clean
clean:
	del /q *.class