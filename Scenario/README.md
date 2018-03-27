
# Testing Scenario

## Struttura 

Nella cartella seguente sono presenti i seguenti file :
1. Controller.py
1. Net_Mininet.py
1. Client.py
1. Server.py

Controller.py è l'implemetazione della tecnica di Marcatura Alternata Multipunto. Il file seguente è stato applicato alla topologia Geant2012.graphml. Per cambiare topologia basta semplicemente modificare il nome del file .graphml nella funzione python
~~~python
get_labels_nodes()
~~~
e cambiare le variabili globali definite inizialmente che specificano la dimensione della topologia. 
Questo script va lanciato in questo modo. Innanzitutto è necessario scaricare pox. IL link per effettuare il download è : https://github.com/noxrepo/pox. Una volta scaricato posizionarsi nelle sottocartella pox/pox/forwarding e caricare lo script Controller.py.

Per eseguire lo script usare il comando:
~~~bash
python ~/pox/pox.py forwarding.Controller
~~~

Net_Mininet.py è lo script che permette di emulare una topologia di rete reale usando Mininet Emulator.
Per poter lanciare il medesimo script è necassario scaricare mininet e networkx. 
1. Il link per effettuare il download di mininet è :http://mininet.org/download/.
1. Il link per effettuare il download di networkx è :https://networkx.github.io/documentation/networkx-1.10/download.html.

Per eseguire lo script usare il comando:
~~~bash
python Net_Mininet.py
~~~

Nel caso in esame lo script Net_Mininet.py legge le informazioni riguardo la topologia dal file Geant2012.graphml. Per emulare una topologia di rete diversa basta semplicemente cambiare il nome del file .graphml nella funzione
~~~python
G = nx.read_graphml('Geant2012.graphml',str)
~~~
In questo modo è possibile emulare topologie di reti differenti.

Gli ultimi due scripts servono come Packet Loss Tester, sono usati per verificare l'accuratezza delle misure fatte dal Controller. Vengono lanciati all'interno del file Net_Mininet.py in questo modo:


~~~python
H1=arrayHost[0]
H1.cmd('rm -r /tmp/pnpm')
H1.cmd('screen -d -m python -m trace -t /home/fmesolella/Desktop/Server.py'+" "+str(H1.IP())+" ")
			
for h in net.hosts:	
	arrayIpHost.remove(h.IP())
	s=""
	for h1 in arrayIpHost:
		s=s+h1+" "
	arrayIpHost.append(h.IP())
	h.cmd('python /home/fmesolella/Desktop/Client.py'+" "+str(H1.IP())+" "+str(h.IP())+" "+str(s)+" >> error.log 2>&1 &")
~~~

Il primo, Client.py è un generatore di pacchetti UDP. Il secondo, Server.py, è un packets collector. Raccoglie i report ricevuti da tutti i generatori di pacchetti e scrive in un file (summary.dat) il numero di pacchetti inviati e ricevuti.

Il programma Controller.py invece viene lanciato in parallelo e dopo la lettura dei contatori in input e output di ogni switch, scrive i risultati in un file results.json. I valori presenti nei due file vengono confrontati per determinare l'accuracy delle misure fatte dal Controller.
