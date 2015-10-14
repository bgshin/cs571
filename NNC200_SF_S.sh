#!/bin/bash
#SBATCH --time=08:00:00
#SBATCH --ntasks=1
#SBATCH --cpus-per-task=1
#SBATCH --mem-per-cpu=40000
#SBATCH --nodes=1
#SBATCH --output NNC200_SF_S.txt

ARG='-c src/main/resources/configuration/NNC200_SF_S.xml -t src/main/resources/dat/wsj-dep/trn -d src/main/resources/dat/wsj-dep/tst -te dep -de dep -f 100'

echo '=====================[NNC200_SF_S: NN cube 200, stan+baseline, stanford]======================'

# mvn compile
mvn exec:java -Dexec.mainClass="edu.emory.mathcs.nlp.bin.DEPTrain" -Dexec.args="$ARG"

