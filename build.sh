find src -name '*.java' > sources.txt
javac -d bin @sources.txt
