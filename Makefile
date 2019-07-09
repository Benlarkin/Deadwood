JFLAGS = -g
JC = javac
.SUFFIXES: .java .class
.java.class:
	$(JC) $(JFLAGS) $*.java

CLASSES = \
	Banker.java \
	Board.java \
	Card.java \
	CastingOffice.java \
	Deadwood.java \
	Dice.java \
	MovieSet.java \
	Player.java \
	Role.java \
	Room.java \
	Timer.java \
	Trailers.java

default: classes

classes: $(CLASSES:.java=.class)

clean:
	$(RM) *.class
