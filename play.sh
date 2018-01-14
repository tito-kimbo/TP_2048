#!/bin/bash
cd bin

args="3 5 12345678"

echo play orig > test.txt
echo 3 >> test.txt
echo 8 >> test.txt
echo "" >> test.txt

for i in {1..200..1}
do
    echo move up >> test.txt
    echo move right >> test.txt
    echo move down >> test.txt
    echo move left >> test.txt
done

echo exit >> test.txt

cat test.txt | java tp.pr3.Game2048 $args
