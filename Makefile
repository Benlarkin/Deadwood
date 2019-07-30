JFLAGS = -g
JC = javac
.SUFFIXES: .java .class
.java.class:
	$(JC) $(JFLAGS) $*.java

CLASSES = *.java controller/*.java model/*.java view/*.java

default: classes

classes: $(CLASSES:.java=.class)

addJava:
	git add $(CLASSES)

clean:
	$(RM) *.class model/*.class view/*.class controller/*.class
