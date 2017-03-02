#!/usr/bin/expect -f
spawn ssh mbaxjzm3@csf2.itservices.manchester.ac.uk
expect "password: "
send "**********\r"
expect "$ "
send "cd /mnt/iusers01/zk01/mbaxjzm3/\r"
expect "$ "
send "./fetch_file_redqueen.sh\r"
sleep 25
expect "$ "
send "scp /mnt/iusers01/zk01/mbaxjzm3/job_duration.txt mengz@rs0.cs.man.ac.uk:/home/MSC11/mengz/Desktop/tests/\r"
expect "password: "
send "**********\r"
sleep 5
expect "$ "
send "exit\r"