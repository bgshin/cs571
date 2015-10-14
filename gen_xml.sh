#!/bin/bash


hidden=$(awk 'BEGIN{for(i=10;i<100;i+=10)print i}') #5

for h in $hidden
do
    outfile1='NNC'$h'_SF.xml'
    outfile2='NNS'$h'_SF.xml'
    echo $outfile1
    echo $outfile2

    pushd . &> /dev/null
    cd ~/works/cs571/src/main/resources/configuration/
    sed 's/<hidden_size>200/<hidden_size>'$h'/' NNC200_SF_S.xml > $outfile1
    sed 's/<hidden_size>200/<hidden_size>'$h'/' NNS200_SF_S.xml > $outfile2
    popd  &> /dev/null

    
done

hidden=$(awk 'BEGIN{for(i=100;i<=2000;i+=100)print i}') #5

for h in $hidden
do

    outfile1='NNC'$h'_SF.xml'
    outfile2='NNS'$h'_SF.xml'
    echo $outfile1
    echo $outfile2


    pushd . &> /dev/null
    cd ~/works/cs571/src/main/resources/configuration/
    sed 's/<hidden_size>200/<hidden_size>'$h'/' NNC200_SF_S.xml > $outfile1
    sed 's/<hidden_size>200/<hidden_size>'$h'/' NNS200_SF_S.xml > $outfile2
    popd  &> /dev/null
done

