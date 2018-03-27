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

Data={}
print "Array with rate :",Rate

input_file_10=open('/home/federica/Desktop/20-11/Colt_iterations/output_totclusters_10.json', 'r')
json_decode=json.load(input_file_10)
for item in json_decode:
			print "item : ",item[0]['info_clusters']
			print "len : ",len(item[0]['info_clusters'])
			for x in range(len(item[0]['info_clusters'])):
				print "item x: ",item[0]['info_clusters'][x]['num_monitored_edges']				
				Array10.append(int(item[0]['info_clusters'][x]['num_monitored_edges']))
print "Array 10 :",Array10


input_file_20=open('/home/federica/Desktop/20-11/Colt_iterations/output_totclusters_20.json', 'r')
json_decode=json.load(input_file_20)
for item in json_decode:
			print "item : ",item[0]['info_clusters']
			print "len : ",len(item[0]['info_clusters'])
			for x in range(len(item[0]['info_clusters'])):
				print "item x: ",item[0]['info_clusters'][x]['num_monitored_edges']		
				Array20.append(int(item[0]['info_clusters'][x]['num_monitored_edges']))
print "Array 20 :",Array20


input_file_30=open('/home/federica/Desktop/20-11/Colt_iterations/output_totclusters_30.json', 'r')
json_decode=json.load(input_file_30)
for item in json_decode:
			print "item : ",item[0]['info_clusters']
			print "len : ",len(item[0]['info_clusters'])
			for x in range(len(item[0]['info_clusters'])):
				print "item x: ",item[0]['info_clusters'][x]['num_monitored_edges']	
				Array30.append(int(item[0]['info_clusters'][x]['num_monitored_edges']))
print "Array 30 :",Array30


input_file_40=open('/home/federica/Desktop/20-11/Colt_iterations/output_totclusters_40.json', 'r')
json_decode=json.load(input_file_40)
for item in json_decode:
			print "item : ",item[0]['info_clusters']
			print "len : ",len(item[0]['info_clusters'])
			for x in range(len(item[0]['info_clusters'])):
				print "item x: ",item[0]['info_clusters'][x]['num_monitored_edges']	
				Array40.append(int(item[0]['info_clusters'][x]['num_monitored_edges']))
print "Array 40 :",Array40



input_file_50=open('/home/federica/Desktop/20-11/Colt_iterations/output_totclusters_50.json', 'r')
json_decode=json.load(input_file_50)
for item in json_decode:
			print "item : ",item[0]['info_clusters']
			print "len : ",len(item[0]['info_clusters'])
			for x in range(len(item[0]['info_clusters'])):
				print "item x: ",item[0]['info_clusters'][x]['num_monitored_edges']		
				Array50.append(int(item[0]['info_clusters'][x]['num_monitored_edges']))
print "Array 50 :",Array50



input_file_60=open('/home/federica/Desktop/20-11/Colt_iterations/output_totclusters_60.json', 'r')
json_decode=json.load(input_file_60)
for item in json_decode:
			print "item : ",item[0]['info_clusters']
			print "len : ",len(item[0]['info_clusters'])
			for x in range(len(item[0]['info_clusters'])):
				print "item x: ",item[0]['info_clusters'][x]['num_monitored_edges']			
				Array60.append(int(item[0]['info_clusters'][x]['num_monitored_edges']))
print "Array 60 :",Array60



input_file_70=open('/home/federica/Desktop/20-11/Colt_iterations/output_totclusters_70.json', 'r')
json_decode=json.load(input_file_70)
for item in json_decode:
			print "item : ",item[0]['info_clusters']
			print "len : ",len(item[0]['info_clusters'])
			for x in range(len(item[0]['info_clusters'])):
				print "item x: ",item[0]['info_clusters'][x]['num_monitored_edges']		
				Array70.append(int(item[0]['info_clusters'][x]['num_monitored_edges']))
print "Array 70 :",Array70




input_file_80=open('/home/federica/Desktop/20-11/Colt_iterations/output_totclusters_80.json', 'r')
json_decode=json.load(input_file_80)
for item in json_decode:
			print "item : ",item[0]['info_clusters']
			print "len : ",len(item[0]['info_clusters'])
			for x in range(len(item[0]['info_clusters'])):
				print "item x: ",item[0]['info_clusters'][x]['num_monitored_edges']		
				Array80.append(int(item[0]['info_clusters'][x]['num_monitored_edges']))
print "Array 80 :",Array80




input_file_90=open('/home/federica/Desktop/20-11/Colt_iterations/output_totclusters_90.json', 'r')
json_decode=json.load(input_file_90)
for item in json_decode:
			print "item : ",item[0]['info_clusters']
			print "len : ",len(item[0]['info_clusters'])
			for x in range(len(item[0]['info_clusters'])):
				print "item x: ",item[0]['info_clusters'][x]['num_monitored_edges']		
				Array90.append(int(item[0]['info_clusters'][x]['num_monitored_edges']))
print "Array 90 :",Array90



input_file_100=open('/home/federica/Desktop/20-11/Colt_iterations/output_totclusters_100.json', 'r')
json_decode=json.load(input_file_100)
for item in json_decode:
			print "item : ",item[0]['info_clusters']
			print "len : ",len(item[0]['info_clusters'])
			for x in range(len(item[0]['info_clusters'])):
				print "item x: ",item[0]['info_clusters'][x]['num_monitored_edges']		
				Array100.append(int(item[0]['info_clusters'][x]['num_monitored_edges']))
print "Array 100 :",Array100


Tot=[Array10,Array20,Array30,Array40,Array50,Array60,Array70,Array80,Array90,Array100]
print "Tot : ",Tot


orig_stdout=sys.stdout
f=open('output_edges_monitored_Colt.txt','w')
sys.stdout=f


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




plt.boxplot(Tot,labels=Rate)

plt.ylabel('Number of edges in the monitored graph',fontsize=18)
plt.xlabel('Rate',fontsize=18)
plt.title('The relation between Rate and number of edges in the monitored graph',fontsize=18)
plt.show()
