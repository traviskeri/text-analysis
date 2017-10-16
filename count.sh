#!/bin/bash

cat $1 \
 | tr '.,;!?*+-=\\|/(){}"[\]><'\' ' ' \
 | tr -s ' \n\t' '\n' \
 | tr A-Z a-z \
 | grep '[a-z]' \
 | sort | uniq -c | sort -k 2 | sort -nrs
