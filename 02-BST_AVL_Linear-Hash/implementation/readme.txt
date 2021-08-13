# Enter "sh readme.txt" at the Linux command prompt to execute
# the following test cases in batch. Look into the .out files for
# more detailed results from executions.

java MainSearch 1000words.txt 1000words.txt 128 > 1000words.out
grep -v ^"\[" 1000words.out | grep -v "not found"

java MainSearch 1000words.txt 2000words.txt 128 > 1000words2.out
grep -v ^"\[" 1000words2.out | grep -v "not found"

java MainSearch 2000words.txt 2000words2.txt 128 > 2000words.out
grep -v ^"\[" 2000words.out | grep -v "not found"

java MainSearch 40kwords.txt darwin.txt 128 > 40kwords.out
grep -v ^"\[" 40kwords.out | grep -v "not found"

java MainSearch darwin.txt 40kwords.txt 128 > darwin.out
grep -v ^"\[" darwin.out | grep -v "not found"

java MainSearch darwin.txt 40kwords.txt 8192 > darwin-8k.out
grep -v ^"\[" darwin-8k.out | grep -v "not found"

# 
# go home safely
# and without any sago
# that is me 
# i told director that he should use korean as much as he could
# so it was my duck
# understand?
# you hamster
# 
# when you arrive text memy english very good very best
# jjiburi jjaburi
# 
# very satisfied
# 
# jogyonim
# 
# give me a plus
# this is a threat
# if you not
# i will find you
# i will hunt you down
# and eat you alive
# i am very dangerous MainSearch
# 
# jongsun
# i will miss you youyou
# i will cry cry cry
# you you
# i will cry
# yo you
# 
# <<byebye train?>></byebye>
# 
# station byebye
# bus bye bye
# station train
# so fast
# ssing ssing
# i am in station
# cry cry you you
# you in train
# ssing ssing
# i will miss you
# i bus ssins ssing
# byebye train
# 