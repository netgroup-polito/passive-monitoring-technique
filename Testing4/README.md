# Testing 4

Questi test permettono di valutare due tempi:
1. Clustering Time: tempo necessario per generare i clusters a partire da una data topologia.
1. Monitored Edges Time: tempo necessario per calcolare gli edges nel grafo monitorato.

Per generare i grafici il comando da eseguire è:
~~~bash
python ClusteringTimeColt.py
~~~
Gli script per il clustering time sono 3, rispettivamente uno per ogni topologia:
1. ClusteringTimeCogent.py	
1. ClusteringTimeColt.py	
1. ClusteringTimeGeant.py

Le informazioni sono state prese dai file json generati applicando l'algoritmo iterativo 
[files_iter](https://github.com/netgroup-polito/passive-monitoring-technique/blob/master/Testing3/Test_Clustering_Iter.zip). Per usare i json generati applicando l'algoritmo ricorsivo [files_rec](https://github.com/netgroup-polito/passive-monitoring-technique/blob/master/Testing3/Test_Clustering_Rec.zip) basta semplicemente cambiare il percorso dei file nello script.

Gli script per il monitored edges time sono 3, rispettimanete uno per ogni topologia:
1. MonitoredEdgesTimeCogent.py	
1. MonitoredEdgesTimeColt.py	
1. MonitoredEdgesTimeGeant.py

Un esempio di box plot è il seguente:
![graph](https://github.com/netgroup-polito/passive-monitoring-technique/blob/master/Testing4/Cogent.PNG)
