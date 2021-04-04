.PHONY: all
all: ;

.PHONY: test
test: ChechTest.class WatchLog.class
	java WatchLog Test\Input\input1.txt 1 2 100| java CheckTest Test\Output\output1_1_2_100.txt
	java WatchLog Test\Input\input2.txt 1 2 100| java CheckTest Test\Output\output2_1_2_100.txt
	java WatchLog Test\Input\input3.txt 1 2 100| java CheckTest Test\Output\output3_1_2_100.txt
	java WatchLog Test\Input\input4.txt 1 2 100| java CheckTest Test\Output\output4_1_2_100.txt
	java WatchLog Test\Input\input5.txt 1 2 100| java CheckTest Test\Output\output5_1_2_100.txt
	java WatchLog Test\Input\input5.txt 1 3 100| java CheckTest Test\Output\output5_1_3_100.txt
ChechTest.class:
	javac CheckTest.java
WatchLog.class:
	javac WatchLog.java

.PHONY: clean
clean:
	del /q *.class
	del /q Server\*.class
	del /q Log\*.class