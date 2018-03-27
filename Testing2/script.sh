
#!/usr/bin/bash

OUT_FOLDER="Clusters" 
mkdir -p "$OUT_FOLDER"
touch "${OUT_FOLDER}/statistics.txt"


printf [ > "${OUT_FOLDER}/statistics.json" 

for FILEIN in *.graphml ; do
    F_JSON="$(basename "$FILEIN" .graphml).json"
    #F_STATS="$(basename "$FILEIN" .graphml)_stats.txt"

    ./iterative_clustering.py "$FILEIN"
    
    mv clusters.json "${OUT_FOLDER}/${F_JSON}" 
    cat statistics.json | sed 's/\[//' | sed 's/\]//' >> "${OUT_FOLDER}/statistics.json"
    tr -d '\n' <  Clusters/statistics.json > temp.json
    cat temp.json > Clusters/statistics.json 
    printf , >> "${OUT_FOLDER}/statistics.json"
    
    
    cat statistics.txt >> "${OUT_FOLDER}/statistics.txt"
    rm statistics.txt
done
cat "${OUT_FOLDER}/statistics.json"  | sed '$ s/.$//' > temp.json 
cat temp.json > "${OUT_FOLDER}/statistics.json" 
rm temp.json
printf ] >> "${OUT_FOLDER}/statistics.json"
