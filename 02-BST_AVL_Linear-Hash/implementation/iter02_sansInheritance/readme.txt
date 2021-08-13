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

