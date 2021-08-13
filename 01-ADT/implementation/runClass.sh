#!/bin/bash
cd /mnt/d/Active/School/'Data Structures (Moon Bongki)'/Assignments/01
javac ./*.java
java MainInt ./public/test-all > ./public/test-all.out.1
diff ./public/test-all.out ./public/test-all.out.1