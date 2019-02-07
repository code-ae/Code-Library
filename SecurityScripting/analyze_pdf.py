#!/bin/bash
# For use in the REMnux distro (Ubuntu)

PDF_FILE=$1  # File to be analyzed
echo $PDF_FILE

peepdf.py $PDF_FILE

python pdf-analyzer.py $PDF_FILE

echo "Use pdf-analyzer.py -o <object#> for for deep dive"
