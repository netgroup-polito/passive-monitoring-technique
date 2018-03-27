#Grafico per i clusters
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
					
dictionary = plt.figure()					
Nodes=[]
ENodes=[]
Edges=[]
EEdges=[]
Labels=[]
dict1={}
dict2={}

input_file=open('statistics.json', 'r')
json_decode=json.load(input_file)

for item in json_decode:
		#print "item : ",item
		Nodes.append(int(item['Nodes']))
		Edges.append(int(item['Edges']))
		ENodes.append(int(item['Extended_Nodes']))
		EEdges.append(int(item['Extended_Edges']))
		Labels.append(item['Name'])
		dict1[item['Name']]=int(item['Nodes'])
		dict2[item['Name']]=int(item['Extended_Nodes'])
		

Nodes, ENodes = (list(t) for t in zip(*sorted(zip(Nodes, ENodes))))

print "-->",Nodes

# calculate polynomial
z = np.polyfit(Nodes, ENodes, 1)
print z
f = np.poly1d(z)

# calculate new x's and y's
x_new=np.logspace(math.log(Nodes[0],10),math.log(Nodes[-1],10), 1000)

y_new = f(x_new)

plt.plot(Nodes,ENodes,'o', x_new, y_new)

plt.xlim([Nodes[0]-1, Nodes[-1] + 1 ])

plt.ylim( (pow(10,1),pow(10,4)) )


plt.yscale('log')
plt.xscale('log')
plt.title('log')

plt.grid(True)

#plt.tight_layout()
plt.grid(axis="y")

plt.ylabel('Logical Nodes',fontsize=18)
plt.xlabel('Physical Nodes',fontsize=18)

plt.title('The relation between physical and logical topology',fontsize=18)
plt.show()


