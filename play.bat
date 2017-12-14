@echo off
SETLOCAL EnableDelayedExpansion
CD bin

set NL=^


set NL=^^^%NL%%NL%^%NL%%NL%

set myVar=8 16 2341299898
set myMoves=move up!NL!move right!NL!move down!NL!move left!NL!

for /l %%x in (1,1,400) do (set myMoves=!myMoves!move up!NL!move right!NL!move down!NL!move left!NL!)

(echo play fib%NL%3%NL%9%NL%2341299898%NL%
echo %myMoves%
echo %myMoves%
echo %myMoves%
echo exit) > test.txt

type test.txt | java tp.pr2.Game2048 %myVar%

set /p= Press "ENTER" to continue...