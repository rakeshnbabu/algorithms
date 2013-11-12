.PHONY: export

CHANGES=$(shell git diff-index --quiet HEAD; echo $$?)

TD=$(shell basename `pwd`)

all: src/*.java
	-mkdir -p bin/
	javac -d bin/ -cp bin/ src/*.java

jar: bin/*.class
	cd bin/
	dx --dex --output=TD2.jar *.class 	
	cd ..

test: all
	cd bin/
	java -jar bin/TD2.jar Test
	cd ..

clean:
	rm -rf bin/*

export: rungitcommit$(CHANGES)
	-mkdir -p bin/
	git archive --format tgz -o ../$(TD).tar.gz HEAD . --prefix=$(TD)/

rungitcommit0:
