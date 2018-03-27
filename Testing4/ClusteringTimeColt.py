import matplotlib.pyplot as plt
import plotly.plotly as py
import matplotlib.ticker as ticker
import numpy as np
import json
from math import *
import math 
import sys
import collections
from collections import OrderedDict
from matplotlib.ticker import MultipleLocator
from pprint import pprint
import numpy as np
import matplotlib.pyplot as plt
from matplotlib.ticker import NullFormatter
from scipy.optimize import curve_fit

				
dictionary = plt.figure()
	
#tempistiche		
Array10_t=[]
Array20_t=[]
Array30_t=[]
Array40_t=[]
Array50_t=[]
Array60_t=[]
Array70_t=[]
Array80_t=[]
Array90_t=[]
Array100_t=[]

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
				
Time=[]
Monitored_Graph_Nodes=[]

input_file_10=open('/home/federica/Desktop/20-11/Test_Clustering_Iter/Colt/Clusters-Iter-Colt-10.json', 'r')
json_decode=json.load(input_file_10)
for item in json_decode:
		Array10_t.append(float(item['Clustering_time']))
		Array10_n.append(int(item['Monitored_graph_nodes']))
print "Array 10 t :",Array10_t
print "Array 10 n :",Array10_n


input_file_20=open('/home/federica/Desktop/20-11/Test_Clustering_Iter/Colt/Clusters-Iter-Colt-20.json', 'r')
json_decode=json.load(input_file_20)
for item in json_decode:
		Array20_t.append(float(item['Clustering_time']))
		Array20_n.append(int(item['Monitored_graph_nodes']))
print "Array 20 t :",Array20_t
print "Array 20 n :",Array20_n


input_file_30=open('/home/federica/Desktop/20-11/Test_Clustering_Iter/Colt/Clusters-Iter-Colt-30.json', 'r')
json_decode=json.load(input_file_30)
for item in json_decode:
		Array30_t.append(float(item['Clustering_time']))
		Array30_n.append(int(item['Monitored_graph_nodes']))
print "Array 30 t :",Array30_t
print "Array 30 n :",Array30_n


input_file_40=open('/home/federica/Desktop/20-11/Test_Clustering_Iter/Colt/Clusters-Iter-Colt-40.json', 'r')
json_decode=json.load(input_file_40)
for item in json_decode:
		Array40_t.append(float(item['Clustering_time']))
		Array40_n.append(int(item['Monitored_graph_nodes']))
print "Array 40 t :",Array40_t
print "Array 40 n :",Array40_n



input_file_50=open('/home/federica/Desktop/20-11/Test_Clustering_Iter/Colt/Clusters-Iter-Colt-50.json', 'r')
json_decode=json.load(input_file_50)
for item in json_decode:
		Array50_t.append(float(item['Clustering_time']))
		Array50_n.append(int(item['Monitored_graph_nodes']))
print "Array 50 t :",Array50_t
print "Array 50 n :",Array50_n



input_file_60=open('/home/federica/Desktop/20-11/Test_Clustering_Iter/Colt/Clusters-Iter-Colt-60.json', 'r')
json_decode=json.load(input_file_60)
for item in json_decode:
		Array60_t.append(float(item['Clustering_time']))
		Array60_n.append(int(item['Monitored_graph_nodes']))
print "Array 60 t :",Array60_t
print "Array 60 n :",Array60_n



input_file_70=open('/home/federica/Desktop/20-11/Test_Clustering_Iter/Colt/Clusters-Iter-Colt-70.json', 'r')
json_decode=json.load(input_file_70)
for item in json_decode:
		Array70_t.append(float(item['Clustering_time']))
		Array70_n.append(int(item['Monitored_graph_nodes']))
print "Array 70 t :",Array70_t
print "Array 70 n :",Array70_n




input_file_80=open('/home/federica/Desktop/20-11/Test_Clustering_Iter/Colt/Clusters-Iter-Colt-80.json', 'r')
json_decode=json.load(input_file_80)
for item in json_decode:
		Array80_t.append(float(item['Clustering_time']))
		Array80_n.append(int(item['Monitored_graph_nodes']))
print "Array 80 t :",Array80_t
print "Array 80 n :",Array80_n




input_file_90=open('/home/federica/Desktop/20-11/Test_Clustering_Iter/Colt/Clusters-Iter-Colt-90.json', 'r')
json_decode=json.load(input_file_90)
for item in json_decode:
		Array90_t.append(float(item['Clustering_time']))
		Array90_n.append(int(item['Monitored_graph_nodes']))
print "Array 90 t :",Array90_t
print "Array 90 n :",Array90_n



input_file_100=open('/home/federica/Desktop/20-11/Test_Clustering_Iter/Colt/Clusters-Iter-Colt-100.json', 'r')
json_decode=json.load(input_file_100)
for item in json_decode:
		Array100_t.append(float(item['Clustering_time']))
		Array100_n.append(int(item['Monitored_graph_nodes']))
print "Array 100 t :",Array100_t
print "Array 100 n :",Array100_n




for i in Array10_t:
	
	Time.append(i)	
for i in Array20_t:
	
	Time.append(i)
for i in Array30_t:
	
	Time.append(i)
for i in Array40_t:
	
	Time.append(i)
for i in Array50_t:
	
	Time.append(i)
for i in Array60_t:
	
	Time.append(i)
for i in Array70_t:
	
	Time.append(i)
for i in Array80_t:
	
	Time.append(i)
for i in Array90_t:
	
	Time.append(i)
for i in Array100_t:
	
	Time.append(i)	


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
	

print "Monitored_Graph_Nodes : ",Monitored_Graph_Nodes


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
	

print "Nodes : ",Nodes


Tot=[]
Tot=[Array10_t,Array20_t,Array30_t,Array40_t,Array50_t,Array60_t,Array70_t,Array80_t,Array90_t,Array100_t]

Monitored_Graph_Nodes,Time = (list(t) for t in zip(*sorted(zip(Monitored_Graph_Nodes, Time))))
# calculate polynomial
z = np.polyfit(Monitored_Graph_Nodes,Time, 3)

print z
f = np.poly1d(z)
# calculate new x's and y's
x_new=np.linspace(min(Monitored_Graph_Nodes),max(Monitored_Graph_Nodes))

y_new = f(x_new)
		
plt.plot(x_new, y_new)


#stampare i contenuti sulle colonne 

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

orig_stdout=sys.stdout
with open("output_colonne_Colt_clustering_time.txt", "w") as f:
	sys.stdout=f

	print a,b,c,d,e,f,g,h,i,l

	for a,b,c,d,e,f,g,h,i,l  in map(None,Array10_t,Array20_t,Array30_t,Array40_t,Array50_t,Array60_t,Array70_t,Array80_t,Array90_t,Array100_t):
	
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
with open("output_Colonne_Colt_clusteringtime_polyfit.txt","w") as f1:
	sys.stdout=f1

	for x,y  in map(None,x_new,y_new):
	
		if x is None:
			x=""
		if y is None:
			y=""
		print x,y		
	

plt.boxplot(Tot,positions=Nodes,widths=30)

plt.xlim(0,800)


plt.grid(True)

#plt.tight_layout()
plt.grid(axis="y")

plt.xlabel('Monitored_graph_nodes',fontsize=18)
plt.ylabel('Clustering_time',fontsize=18)

plt.title('The relation between nodes in the monitored graph and clustering time ',fontsize=18)
plt.show()





