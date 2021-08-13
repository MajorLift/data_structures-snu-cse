#!/bin/csh -f
#
# Enter 'csh readme-qtypes-G.txt' at the Unix shell prompt and
# it will run all test cases by query types separately in a batch.

unset noclobber		# to enable overwriting an existing file

set moviefile=G.txt
set queryfile=G.q200.txt
set base=`basename "$queryfile" .txt`

@ qtype = 1
while ( $qtype < 10 )
        set qfile = "${base}-q${qtype}.txt"
        set ofile = "${base}-q${qtype}.out"
	echo " ... running Query Type ${qtype}: $moviefile $qfile $ofile"
	grep "^${qtype}" $queryfile > $qfile
	java MainBacon $moviefile $qfile > $ofile
	rm $qfile
        @ qtype++
end

