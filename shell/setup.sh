#!/usr/bin/env bash
# start mysql
echo 'start mysql server'
mysql.server restart
# start elasticsearch
echo 'start elasticsearch'
sh $PWD/elasticsearch/elasticsearch-5.4.3/bin/elasticsearch