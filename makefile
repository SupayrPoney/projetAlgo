jc=javac
JFLAGS = -Xlint

all: Cercle.class Graphe.class Main.class

Cercle.class: Cercle.java
	$(jc) $(CFLAGS) Cercle.java 

Graphe.class: Graphe.java
	$(jc) $(CFLAGS) Graphe.java

Main.class: Main.java
	$(jc) $(CFLAGS) Main.java 