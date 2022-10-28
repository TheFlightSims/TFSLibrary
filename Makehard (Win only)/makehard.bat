@echo on
title Make obj hard
cd /d %~dp0
set /a x = "0"
goto start


:start
set /a x = %x% + 1
echo %x%
readline index.txt %x% > temp.txt
set /p y=<temp.txt
echo "%y%"
addline "%y%" 4 "HARD_WALL concrete"
goto start