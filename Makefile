.PHONY: all
all: ;

.PHONY: test
test: ChechTest.class WatchLog.class
	java WatchLog Test\Input\input1.txt 1| java CheckTest Test\Output\output1_1.txt
	java WatchLog Test\Input\input1.txt 2| java CheckTest Test\Output\output1_2.txt
	java WatchLog Test\Input\input1.txt 3| java CheckTest Test\Output\output1_3.txt
	java WatchLog Test\Input\input2.txt 1| java CheckTest Test\Output\output2_1.txt
	java WatchLog Test\Input\input2.txt 2| java CheckTest Test\Output\output2_2.txt
	java WatchLog Test\Input\input3.txt 1| java CheckTest Test\Output\output3_1.txt
	java WatchLog Test\Input\input3.txt 2| java CheckTest Test\Output\output3_2.txt
	java WatchLog Test\Input\input3.txt 3| java CheckTest Test\Output\output3_3.txt
	java WatchLog Test\Input\input4.txt 2| java CheckTest Test\Output\output4_2.txt
	java WatchLog Test\Input\input4.txt 3| java CheckTest Test\Output\output4_3.txt
	java WatchLog Test\Input\input4.txt 4| java CheckTest Test\Output\output4_4.txt
	java WatchLog Test\Input\input4.txt 5| java CheckTest Test\Output\output4_5.txt
	java WatchLog Test\Input\input5.txt 2| java CheckTest Test\Output\output5_2.txt
	java WatchLog Test\Input\input6.txt 2| java CheckTest Test\Output\output6_2.txt
ChechTest.class:
	javac CheckTest.java
WatchLog.class:
	javac WatchLog.java

.PHONY: clean
clean:
	del /q *.class