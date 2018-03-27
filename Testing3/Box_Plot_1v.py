import matplotlib.pyplot as plt
import numpy as np
import plotly.plotly as py
import matplotlib.ticker as ticker
import numpy as np
import json
import sys
import collections
from matplotlib.ticker import MultipleLocator
from math import *
import math 
import sys
import collections
from collections import OrderedDict
from pprint import pprint
from matplotlib.ticker import NullFormatter

#mettere i valori di num_clusters in 10 array 
Rate=[10,20,30,40,50,60,70,80,90,100]
Array10=[]
Array20=[]
Array30=[]
Array40=[]
Array50=[]
Array60=[]
Array70=[]
Array80=[]
Array90=[]
Array100=[]

#monitored graph nodes
Array10_n=[]
Array20_n=[]
Array30_n=[]
Array40_n=[]
Array50_n=[]
Array60_n=[]
Array70_n=[]
Array80_n=[]
Array90_n=[]
Array100_n=[]

Data={}

print "Array with rate :",Rate

input_file_10=open('/home/federica/Desktop/20-11/Geant_iterations/output_totclusters_10.json', 'r')
json_decode=json.load(input_file_10)
for item in json_decode:
		Array10.append(int(item[0]['num_clusters']))
		Array10_n.append(int(item[0]['Monitored_graph_nodes']))
print "Array 10 :",Array10


input_file_20=open('/home/federica/Desktop/20-11/Geant_iterations/output_totclusters_20.json', 'r')
json_decode=json.load(input_file_20)
for item in json_decode:
		Array20.append(int(item[0]['num_clusters']))
		Array20_n.append(int(item[0]['Monitored_graph_nodes']))
print "Array 20 :",Array20


input_file_30=open('/home/federica/Desktop/20-11/Geant_iterations/output_totclusters_30.json', 'r')
json_decode=json.load(input_file_30)
for item in json_decode:
		Array30.append(int(item[0]['num_clusters']))
		Array30_n.append(int(item[0]['Monitored_graph_nodes']))
print "Array 30 :",Array30


input_file_40=open('/home/federica/Desktop/20-11/Geant_iterations/output_totclusters_40.json', 'r')
json_decode=json.load(input_file_40)
for item in json_decode:
		Array40.append(int(item[0]['num_clusters']))
		Array40_n.append(int(item[0]['Monitored_graph_nodes']))
print "Array 40 :",Array40



input_file_50=open('/home/federica/Desktop/20-11/Geant_iterations/output_totclusters_50.json', 'r')
json_decode=json.load(input_file_50)
for item in json_decode:
		Array50.append(int(item[0]['num_clusters']))
		Array50_n.append(int(item[0]['Monitored_graph_nodes']))
print "Array 50 :",Array50



input_file_60=open('/home/federica/Desktop/20-11/Geant_iterations/output_totclusters_60.json', 'r')
json_decode=json.load(input_file_60)
for item in json_decode:
		Array60.append(int(item[0]['num_clusters']))
		Array60_n.append(int(item[0]['Monitored_graph_nodes']))
print "Array 60 :",Array60



input_file_70=open('/home/federica/Desktop/20-11/Geant_iterations/output_totclusters_70.json', 'r')
json_decode=json.load(input_file_70)
for item in json_decode:
		Array70.append(int(item[0]['num_clusters']))
		Array70_n.append(int(item[0]['Monitored_graph_nodes']))
print "Array 70 :",Array70




input_file_80=open('/home/federica/Desktop/20-11/Geant_iterations/output_totclusters_80.json', 'r')
json_decode=json.load(input_file_80)
for item in json_decode:
		Array80.append(int(item[0]['num_clusters']))
		Array80_n.append(int(item[0]['Monitored_graph_nodes']))
print "Array 80 :",Array80




input_file_90=open('/home/federica/Desktop/20-11/Geant_iterations/output_totclusters_90.json', 'r')
json_decode=json.load(input_file_90)
for item in json_decode:
		Array90.append(int(item[0]['num_clusters']))
		Array90_n.append(int(item[0]['Monitored_graph_nodes']))
print "Array 90 :",Array90



input_file_100=open('/home/federica/Desktop/20-11/Geant_iterations/output_totclusters_100.json', 'r')
json_decode=json.load(input_file_100)
for item in json_decode:
		Array100.append(int(item[0]['num_clusters']))
		Array100_n.append(int(item[0]['Monitored_graph_nodes']))
print "Array 100 :",Array100


Tot=[Array10,Array20,Array30,Array40,Array50,Array60,Array70,Array80,Array90,Array100]
print "Tot : ",Tot


Num_clusters=[]
Monitored_Graph_Nodes=[]
Nodes=[]

Nodes.append(Array10_n[0])	
Nodes.append(Array20_n[0])	
Nodes.append(Array30_n[0])	
Nodes.append(Array40_n[0])	
Nodes.append(Array50_n[0])	
Nodes.append(Array60_n[0])	
Nodes.append(Array70_n[0])	
Nodes.append(Array80_n[0])	
Nodes.append(Array90_n[0])	
Nodes.append(Array100_n[0])

for i in Array10:
	
	Num_clusters.append(i)	
for i in Array20:
	
	Num_clusters.append(i)
for i in Array30:
	
	Num_clusters.append(i)
for i in Array40:
	
	Num_clusters.append(i)
for i in Array50:
	
	Num_clusters.append(i)
for i in Array60:
	
	Num_clusters.append(i)
for i in Array70:
	
	Num_clusters.append(i)
for i in Array80:
	
	Num_clusters.append(i)
for i in Array90:
	
	Num_clusters.append(i)
for i in Array100:
	
	Num_clusters.append(i)
	

print "Num_clusters : ",Num_clusters

for i in Array10_n:
	
	Monitored_Graph_Nodes.append(i)	
for i in Array20_n:
	
	Monitored_Graph_Nodes.append(i)	
for i in Array30_n:
	
	Monitored_Graph_Nodes.append(i)	
for i in Array40_n:
	
	Monitored_Graph_Nodes.append(i)	
for i in Array50_n:
	
	Monitored_Graph_Nodes.append(i)	
for i in Array60_n:
	
	Monitored_Graph_Nodes.append(i)	
for i in Array70_n:
	
	Monitored_Graph_Nodes.append(i)	
for i in Array80_n:
	
	Monitored_Graph_Nodes.append(i)	
for i in Array90_n:
	
	Monitored_Graph_Nodes.append(i)	
for i in Array100_n:
	
	Monitored_Graph_Nodes.append(i)	



Monitored_Graph_Nodes,Num_clusters = (list(t) for t in zip(*sorted(zip(Monitored_Graph_Nodes, Num_clusters))))
# calculate polynomial
z = np.polyfit(Monitored_Graph_Nodes,Num_clusters, 3)

print z
f = np.poly1d(z)
# calculate new x's and y's
x_new=np.linspace(min(Monitored_Graph_Nodes),max(Monitored_Graph_Nodes))

y_new = f(x_new)
		
plt.plot(x_new, y_new)



a=Array10_n[0]	
b=Array20_n[0]	
c=Array30_n[0]	
d=Array40_n[0]	
e=Array50_n[0]	
f=Array60_n[0]	
g=Array70_n[0]	
h=Array80_n[0]	
i=Array90_n[0]	
l=Array100_n[0]

print a,b,c,d,e,f,g,h,i,l

plt.boxplot(Tot,positions=Nodes,labels=Rate,widths=50)

orig_stdout=sys.stdout
with open("output_num_clusters_Geant.txt", "w") as f:
	sys.stdout=f

	print a,b,c,d,e,f,g,h,i,l

	for a,b,c,d,e,f,g,h,i,l  in map(None,Array10,Array20,Array30,Array40,Array50,Array60,Array70,Array80,Array90,Array100):
	
		if a is None:
			a=""
		if b is None:
			b=""
		if c is None:
			c=""
		if d is None:
			d=""
		if e is None:
			e=""
		if f is None:
			f=""
		if g is None:
			g=""
		if h is None:
			h=""
		if i is None:
			i=""
		if l is None:
			l=""									
 
		print a,b,c,d,e,f,g,h,i,l


orig_stdout=sys.stdout
with open("output_num_clusters_polyfit_Geant.txt","w") as f1:
	sys.stdout=f1

	for x,y  in map(None,x_new,y_new):
	
		if x is None:
			x=""
		if y is None:
			y=""
		print x,y	
		
		
# control x and y limits
plt.ylim(-20, 750)
plt.xlim(10, None)

plt.ylabel('Number of clusters',fontsize=18)
plt.xlabel('Rate',fontsize=18)
plt.title('The relation between Rate and Number of clusters',fontsize=18)
plt.show()
