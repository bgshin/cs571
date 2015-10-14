#!/bin/bash


# hidden=$(awk 'BEGIN{for(i=10;i<=90;i+=10)print i}') #5


# for h in $hidden
# do
#     b="NNC"$h"_SF"
#     echo $b
#     sbatch -o ./log/$b.txt run_template.sh $b

#     b="NNS"$h"_SF"
#     echo $b
#     sbatch -o ./log/$b.txt run_template.sh $b
# done


hidden=$(awk 'BEGIN{for(i=100;i<=2000;i+=100)print i}') #5


for h in $hidden
do
    b="NNC"$h"_SF"
    echo $b
    sbatch -o ./log/$b.txt run_template.sh $b

    b="NNS"$h"_SF"
    echo $b
    sbatch -o ./log/$b.txt run_template.sh $b
done



