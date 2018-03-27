# Testing 2

Applicando l'algoritmo di clustering, sia iterativo che ricorsivo, a tutte le topologie di rete presenti nel dataset The Internet Topology Zoo, è stato notato che nella transizione dal grafico fisico a quello esteso, la dimensione della rete cambia di molto.

Lo script in esame prende in input, per ogni topologia, il numero di nodi fisici ed estesi oppure il numero di edges fisici ed estesi e crea uno scattared graph che mostra chiaramente questa relazione.

Per eseguire lo script che mostra la relazione tra i nodi fisici ed estesi usare il comando:
~~~bash
python ScattaredGraphNodes.py 
~~~
Per eseguire lo script che mostra la relazione tra gli edges fisici ed estesi usare il comando:
~~~bash
python ScattaredGraphEdges.py 
~~~

Entrambi gli script prendono in input un file : statistics.json. Il quale contiene per tutte le topologie presenti nel dataset The Internet Topology Zoo le seguenti informazioni:
1. Nome della topologia
1. Numero di nodi fisici
1. Numero di nodi estesi
1. Numero di edges fisici
1. Numero di edges estesi

Questo file è stato creato mediante lo script: script.sh :
~~~bash
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
~~~

Questo  esegue lo script iterative_clustering.py per ogni file .graphml presente nel dataset The Internet Topolgy Zoo.
1. Crea la cartella Clusters e un file vuoto al suo interno chiamato statistics.json.
1. Esegue interative_clustering.py per ogni file .graphml presente nel dataset The Internet Topolgy Zoo.
1. Prende l'output per ogni iterazione e lo appende a statistics.json.

Eseguendo quindi i due script sopra ottengo i seguenti grafici:
![graph](https://github.com/netgroup-polito/passive-monitoring-technique/blob/master/Testing2/Nodes.png)

![graph](https://github.com/netgroup-polito/passive-monitoring-technique/blob/master/Testing2/Edges.png)
