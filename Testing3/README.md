# Testing 3

L'obiettivo è quello di esaminare le caratteristiche dei clusters in tre topologie di rete reali:
1. Géant con 40 nodi fisici.
1. Colt Telecom con 152 nodi fisici. 
1. Cogent Communications con 196 nodi fisici. 

Per descrivere le caratteristiche dei clusters è stato utilizzato un box plot. 
In primo luogo viene lanciato lo script "Script100Iterations.py" in questo modo:
~~~bash
python Script100Iterations.py
~~~
Questo script viene fatto eseguire per ogni topologia sopra elencata e per ogni rate (la percetuale di nodi selezionati) 100 volte.
Con un totale di 1000 esecuzioni per topologia.

Lo script consiste nell' applicare ad una topologia specifica questi due script:
1. CompleteIterativeClustering_v5.py
1. CompleteRecursiveClustering_v1.py

Questi sono l'implementazione dell'algoritmo iterativo e ricorsivo per la creazione dei clusters.
Una volta eseguito lo script produce un file output.json in cui sono contenute alcune info:
~~~json
{
        "Extended_graph_nodes": 244, 
        "Monitored_graph_edges": 426, 
        "Monitored_graph_nodes": 24, 
        "Clustering_time": 0.0011801719665527344, 
        "Extended_graph_edges": 642, 
        "info_clusters": [
            {
                "num_monitored_nodes": 24, 
                "num_extended_nodes": 202, 
                "id": 0, 
                "extended_diameter": 29, 
                "monitored_diameter": 1, 
                "num_monitored_edges": 423, 
                "num_extended_edges": 351
            }, 
            {
                "num_monitored_nodes": 2, 
                "num_extended_nodes": 2, 
                "id": 1, 
                "extended_diameter": 1, 
                "monitored_diameter": 1, 
                "num_monitored_edges": 1, 
                "num_extended_edges": 1
            }, 
            {
                "num_monitored_nodes": 2, 
                "num_extended_nodes": 2, 
                "id": 2, 
                "extended_diameter": 1, 
                "monitored_diameter": 1, 
                "num_monitored_edges": 1, 
                "num_extended_edges": 1
            }, 
            {
                "num_monitored_nodes": 2, 
                "num_extended_nodes": 2, 
                "id": 3, 
                "extended_diameter": 1, 
                "monitored_diameter": 1, 
                "num_monitored_edges": 1, 
                "num_extended_edges": 1
            }
        ], 
        "nodes": "10", 
        "num_clusters": 4, 
        "Monitored_edges_time": 0.026520967483520508, 
        "topology": "Geant2012.graphml"
    }
~~~
Questo oggetto json viene generato ad ogni iterazione. 

Quindi in totale avrò, per ogni Topologia, 10 file, uno per ogni rate, che contengono rispettivamente 100 oggetti json simili all'esempio di sopra.

[Test_Clustering_Iter.zip](https://github.com/netgroup-polito/passive-monitoring-technique/blob/master/Testing3/Test_Clustering_Iter.zip) e 
[Test_Clustering_Iter.zip](https://github.com/netgroup-polito/passive-monitoring-technique/blob/master/Testing3/Test_Clustering_Iter.zip) contengono questi file.

Questi file sono utilizzati per disegnare i grafici box plot.

Per generare un grafico basta lanciare lo script in questo modo:
~~~bash
python Box_Plot_1v.py
~~~
Gli script sono 7 in totale:
1. [Box_Plot_1v.py](https://github.com/netgroup-polito/passive-monitoring-technique/blob/master/Testing3/Box_Plot_1v.py): mostra la relazione tra i nodi selezionati e il numero di clusters. 
1. [Box_Plot_2v.py](https://github.com/netgroup-polito/passive-monitoring-technique/blob/master/Testing3/Box_Plot_2v.py): mostra la relazione tra i nodi selezionati e il numero di nodi monitorati.
1. [Box_Plot_3v.py](https://github.com/netgroup-polito/passive-monitoring-technique/blob/master/Testing3/Box_Plot_3v.py): mostra la relazione tra i nodi selezionati e il numero di nodi estesi. 
1. [Box_Plot_4v.py](https://github.com/netgroup-polito/passive-monitoring-technique/blob/master/Testing3/Box_Plot_4v.py): mostra la relazione tra i nodi selezionati e il diametro nel grafo esteso.  
1. [Box_Plot_5v.py](https://github.com/netgroup-polito/passive-monitoring-technique/blob/master/Testing3/Box_Plot_5v.py): mostra la relazione tra i nodi selezionati e il diametro nel monitorato.  
1. [Box_Plot_6v.py](https://github.com/netgroup-polito/passive-monitoring-technique/blob/master/Testing3/Box_Plot_6v.py): mostra la relazione tra i nodi selezionati e il numero di edges nel grafo monitorato.   
1. [Box_Plot_7v.py](https://github.com/netgroup-polito/passive-monitoring-technique/blob/master/Testing3/Box_Plot_7v.py): mostra la relazione tra i nodi selezionati e il numero di edges nel grafo esteso.   

Un esempio di box plot è il seguente che mostra la relazione tra i nodi selezionati e il diametro nel grafo esteso per la topologia Geant:
![graph](https://github.com/netgroup-polito/passive-monitoring-technique/blob/master/Testing3/ExtendedDiameter.PNG)
