# March 09 2014
# Version 0.4 by crashmax
# Simple start up script for Tony's World2Xplane

#!/bin/bash


# declare defaults
arch="32"
rootpath="./"
exec="World2XPlane.jar"
maxxm="4"
minxm="2"
minfree="1.6" # used only for abort message text


# get available memory
memusage=`top -n 1 -b | grep "Mem"`
echo "Mem-Info: "$memusage

splitit=${memusage##*"used"}
avmem=`echo $splitit | cut -d" " -f2 | awk '{print substr($0,1,length($0))}'`
avmem=`echo "${avmem//[!0-9]/}"`

	# convert value to GB
	avmem=$(((($avmem/1024)-500)/1024))


# check if enough free memory is available. End script if not.
if [ $avmem \< $minxm ]
then
echo "Sorry, not enough free memory. Min free memory is: "$minfree"GB."" Ending process now..."
exit
fi


# ask for amount of memory or auto calculation
echo ""
echo "How much memory (in GB) should World2Xplane use?"
echo "For example write a 2 for 2GB." "Available memory is: "$avmem"GB"
echo "As this is 32bit only 4GB is usable!"
echo ""
echo "Write a number or press Enter to use computed amount of memory:"
read reponse
usrinput=$reponse

# Check user input. Use auto calculated amount if user entered 0 or enter
if [ "$usrinput" = "" ] || [ "$usrinput" \< $minxm ]
then

echo "Using computed amount of memory (max 4GB)"
echo "Press enter to continue"
read response
	# we only use 4GB even in case there is more available (32bit)
	if [ $avmem \> $maxxm ]
	then
	Xms=$maxxm
	Xmx=$maxxm
	else
	Xms=$avmem
	Xmx=$avmem
	fi


elif [ $usrinput \> $maxxm ] 
then
echo "Max usable memory is 4GB. Using computed amount of memory instead of "$reponse"."
echo "Press enter to start"
read reponse
	# We only can use 4GB
	if [ $avmem \< $maxxm ]
	then
	Xms=$avmem
	Xmx=$avmem
	else
	Xms=$maxxm
	Xmx=$maxxm
	fi

# Use entered value if not > 4GB
else
Xms="$reponse"
Xmx="$reponse"

fi


# start World2Xplane
echo "starting World2Xplane"
java -d$arch -Xms$Xms"g" -Xmx$Xmx"g" -jar $rootpath$exec &


# Just for debugging
# echo "Final xxecutable is: ""java -d"$arch" -Xms"$Xms"g"" -Xmx"$Xmx"g -jar "$rootpath$exec

# end of script
