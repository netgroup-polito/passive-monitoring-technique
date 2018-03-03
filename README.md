# passive-monitoring-technique

## Struttura del progetto

Il progetto è strutturato in due cartelle: **tesislustering** e **Clientrepository**. La prima contiene il Web Service e la Web Application, la seconda l'applicazione client.
Nella cartella [docs](https://github.com/netgroup-polito/passive-monitoring-technique/tree/master/docs) è disponibile la documentazione dettagliata.

Per importare il progetto in Eclipse:

1. Clonare il repository in una cartella sul proprio PC.
1. Dal menu di Eclipse  scegliere **File -> Import-> Existing Maven Project -> Next -> Selezionare la cartella in cui è stato scaricato il progetto -> Finish**.

Attendere qualche minuto che Maven completi la risoluzione delle dipendenze e la build del progetto.

Per eseguire il Web Service in ambiente Tomcat da Eclipse, con click destro sulla cartella del progetto aprire il menu contestuale e scegliere **Run As -> Run on server**.

## Installazione tools e framework

1. **Eclipse IDE for Java EE Developers (Neon Packages)**. Il link per effettuare il download è: https://www.eclipse.org/downloads/packages/release/Neon/3.
1. **Apache Tomcat 7**. Il link per effettuare il download è: https://tomcat.apache.org/download-70.cgi
1. Integrazione di **networkx** in jython per eseguire script in python lato java. Scaricare
networkx: https://networkx.github.io/documentation/networkx-1.10/download.html,
scaricare jython 2.7.0 :http://www.jython.org/downloads.html e python 2.7.0 al
link :https://www.python.org/download/releases/2.7/, estrarre i rispettvi contenuti in
3 cartelle differenti. Aggiungere python 2.7.0 e jython 2.7.0 alle variabili d'ambiene (in
Windows). Su Windows eseguire il comando (jython -m pip install -U pip ) per installare
pip (il gestore dei pacchetti di python). Nella cartella source di networkx eliminare il file
networkx/generators/atlas.py. Nel file networkx/readwrite/gml.py, rimuovere tutti i
riferimenti a lib2to3 (dovuto ad un bug presente in Jython, lib2to3 non è disponibile).
Alle righe 44-46, commentare tutti gli imports from lib2to3 e alla riga 75 cambiare :

~~~python
rtp_fix_unicode = RefactoringTool(['lib2to3.fixes.fix_unicode'],
{'print_function': True})
~~~

in:

~~~python
rtp_fix_unicode = None
~~~

Alla riga 145, nel try-except statement, rimuovere ParseError e TokenError. A questo
punto è necessario tornare nella cartella source ed eseguire il comando: jython/pip
install . A questo punto abbiamo integrato networkx in jython, quindi basta
semplicemente caricare il file.jar in eclipse nel build path del progetto maven.
1. **Jython versione 2.7.0**: prendere il file.jar presente nella cartella jython 2.7.0 (ottenuto dopo l'integrazione di networkx) e
aggiungerlo al Build Path del progetto. Tasto destro sul progetto maven -> Build Path ->
Configure Build Path ->Libraries-> Add External JARs-> Apply->OK.
1. **Hibernate**.
1. **Jersey**: Jersey RESTful Web Services framework.
1. **Jackson**.
1. **Apache Maven**.

## Configurazione database
Per la creazione del database è stato scelto **MySql**. Il link per effettuare il download
è :https://www.mysql.com/it/products/community/. Tra le varie edizioni disponibili è stata scelta
MySQL Community Edition.

Per creare le tabelle necessarie, eseguire le seguenti query:

~~~sql
CREATE TABLE `graphs` (
  `name` varchar(255) NOT NULL,
  `xml_file` blob,
  PRIMARY KEY (`name`)
)
~~~


~~~sql
CREATE TABLE `executions` (
  `id` int(11) NOT NULL,
  `rate` int(11) DEFAULT NULL,
  `num_clusters` int(11) DEFAULT NULL,
  `nodes` varchar(255) DEFAULT NULL,
  `topology` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `topology` (`topology`),
  CONSTRAINT `executions_ibfk_1` FOREIGN KEY (`topology`) REFERENCES `graphs` (`name`)
)
~~~


~~~sql
CREATE TABLE `infoclusters` (
  `id` int(11) DEFAULT NULL,
  `num_monitored_nodes` int(11) DEFAULT NULL,
  `num_extended_nodes` int(11) DEFAULT NULL,
  `num_monitored_edges` int(11) DEFAULT NULL,
  `num_extended_edges` int(11) DEFAULT NULL,
  `monitored_diameter` int(11) DEFAULT NULL,
  `extended_diameter` int(11) DEFAULT NULL,
  `execution_id` int(11) DEFAULT NULL,
  `id_cluster` int(11) NOT NULL,
  `idx` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_cluster`),
  KEY `execution_id` (`execution_id`),
  CONSTRAINT `infoclusters_ibfk_1` FOREIGN KEY (`execution_id`) REFERENCES `executions` (`id`)
)
~~~

## Creazione del jar

E' possibile anche lanciare l'applicazione client dal prompt dei comandi. Basta semplicemente usare l'assemply plugin di Maven per generare il file .jar.
I passi da seguire per generare un executable jar con Maven sono i seguenti:

Per creare un jar eseguibile, non abbiamo bisogno di ulteriori dipendenze nel pom.xml. Abbiamo
solo bisogno di creare un progetto Maven Java e avere almeno una classe con il metodo
principale main(). Bisogna semplicemente aggiungere il plugin Apache Maven Asseebly Plugin nel pom.xml:

~~~xml
        <plugin>
		    <groupId>org.apache.maven.plugins</groupId>
		    <artifactId>maven-assembly-plugin</artifactId>
		    <executions>
		        <execution>
		            <phase>package</phase>
		            <goals>
		                <goal>single</goal>
		            </goals>
		            <configuration>
		                <archive>
		                <manifest>
		                    <mainClass>
		                        it.polito.client.ClusteringClient
		                    </mainClass>
		                </manifest>
		                </archive>
		                <descriptorRefs>
		                    <descriptorRef>jar-with-dependencies</descriptorRef>
		                </descriptorRefs>
		            </configuration>
		        </execution>
		    </executions>
		</plugin>
~~~

Quindi per eseguirlo basta andare nella cartella in cui è contenuto il file jar è lanciare il comando:

~~~bash
java -cp .\client-0.0.1-SNAPSHOT-jar-with-dependencies.jar it.polito.client.ClusteringClient
~~~


.


