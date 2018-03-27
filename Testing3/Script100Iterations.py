import sys
import json
import random
import subprocess
import networkx as nx

i=0
dump=[]
data={}
returndata={}

for topo in ["Geant2012", "Colt", "Cogentco"]:
    for j in [10,20,30,40,50,60,70,80,90,100]:
   
        results=[]

        for i in range(100):

            print "Iteration number : ",i
            arg2=str(i)
            
            #esecuzione dello script
            if subprocess.call([sys.executable,"CompleteIterativeClustering_v5.py",topo+".graphml",str(j)]) != 0:
                exit(1)
            
            with open('totclusters.json', 'r') as cfile:
                data=json.load(cfile);

            results.append(data[0])

        with open("Clusters-Iter-"+topo+"-"+str(j)+".json", 'w') as cfile:
            json.dump(results,cfile,indent=4);

for topo in ["Geant2012", "Colt", "Cogentco"]:
    for j in [10,20,30,40,50,60,70,80,90,100]:
    
        results=[]

        for i in range(100):

            print "Iteration number : ",i
            arg2=str(i)
            
            #esecuzione dello script
            if subprocess.call([sys.executable,"CompleteRecursiveClustering_v1.py",topo+".graphml",str(j)]) != 0:
                exit(1)
            
            with open('totclusters.json', 'r') as cfile:
                data=json.load(cfile);

            results.append(data[0])

        with open("Clusters-Rec-"+topo+"-"+str(j)+".json", 'w') as cfile:
            json.dump(results,cfile,indent=4);
