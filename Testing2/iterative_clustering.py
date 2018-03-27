#!/usr/bin/env python

import sys
import networkx as nx
import json

def clustering(edges,n):
    # complexity m+ m * (sum_(i from 1 to m) m-i) = m+ (m* (m-1)/2) = O(m^2)
    # in the worst case (when every cluster has only one link)

    Atilde=[[] for x in range(n)]

    #O(m)
    for edge in edges:
        Atilde[edge[0]].append(edge)

    for i in range(n):
        if len(Atilde[i])>0:

            for Atilde_j in Atilde[i]:
            
                sec=Atilde_j[1]
    
                for ii in range(i+1,len(Atilde)):
                    if len(Atilde[ii])>0:
                        for Atilde_jj in Atilde[ii]:

                            if Atilde_jj[1]==sec:
                                Atilde[i].extend(Atilde[ii])
                                Atilde[ii]=[]
                                break
    # Remove empty clusters
    Atilde=filter(None, Atilde)
    return Atilde

if __name__ == "__main__":

    # Sanity check
    if len(sys.argv)!=2:
        print "usage: %s graphml_file"%sys.argv[0]
        exit()
	
    # Import graph
    graph=nx.read_graphml(sys.argv[1],str)
    
    with open('statistics.txt', 'w') as f:
		f.write("File : "+str(sys.argv[1])+ '\n')
		
			
    
		print "File : ",str(sys.argv[1])
		print ("Graph %s: %d nodes, %d edges"
	          %(graph.name, nx.number_of_nodes(graph), nx.number_of_edges(graph)))
		f.write("Graph Original :"+"Nodes : "+str(nx.number_of_nodes(graph))+" "+"Edges : "+str(nx.number_of_edges(graph))+ '\n')      
	
	    # Adapt to networkx version
		if float(nx.__version__)>=2.0:
			edges_iter=graph.edges
			nodes_iter=graph.nodes
		else:
			edges_iter=graph.edges_iter
			nodes_iter=graph.nodes_iter
	
		# Node labels
		geolabels=['' for i in range(nx.number_of_nodes(graph))]
		
		for node in nodes_iter(data=True):
			geolabels[int(node[0])]=node[1]['label']
		
		# Setup input and output interfaces
		# Setup external links
		in_interfaces={}
		out_interfaces={}
		nodes=[]
		edges=[]
		n=0
		
		for edge in edges_iter(data=True):
			x=int(edge[0])
			y=int(edge[1])
		
			if x not in out_interfaces:
				out_interfaces[x]={}
			if y not in out_interfaces:
				out_interfaces[y]={}
			if x not in in_interfaces:
				in_interfaces[x]={}
			if y not in in_interfaces:
				in_interfaces[y]={}
		
			out_interfaces[x][y]=n
			out_interfaces[y][x]=n+1
			in_interfaces[x][y]=n+2
			in_interfaces[y][x]=n+3
			nodes.append((x,y,"out"))
			nodes.append((y,x,"out"))
			nodes.append((x,y,"in"))
			nodes.append((y,x,"in"))
			edges.extend([(n,n+3),(n+1,n+2)])
			n+=4
		
		# Setup internal links
		for node in in_interfaces:
			for intf1 in in_interfaces[node]:
				in_intf=in_interfaces[node][intf1]
				for intf2 in out_interfaces[node]:
					out_intf=out_interfaces[node][intf2]
					edges.append((in_intf,out_intf))
				
		print ("Extended graph: %d nodes, %d edges"
			  %(n,len(edges)))
			  
		f.write("Extended Graph :"+"Nodes : "+str(n)+" "+"Edges : "+str(len(edges))+'\n')      
		
			
			
		# Clustering
		clusters=clustering(edges,n)
		
		# Dump clusters
		dump=[]
		
		for cl in clusters:
			labeled_cl=[]
			raw_cl=[]
			for ll in cl:
				src=nodes[ll[0]]
				dst=nodes[ll[1]]
				src_label="%s-%s-%s"%(geolabels[src[0]],geolabels[src[1]],src[2])
				dst_label="%s-%s-%s"%(geolabels[dst[0]],geolabels[dst[1]],dst[2])
				labeled_cl.append("(%s,%s),"%(src_label,dst_label))
				raw_cl.append((src,dst))
			dump.append({"raw":raw_cl,"labeled":labeled_cl,"size":len(cl)})
		
		with open('clusters.json', 'w') as cfile:
			json.dump(dump, cfile, indent=4)
		
		print "Clusters: %d"%len(clusters)
		f.write("Clusters : "+str(len(clusters))+ '\n') 
		
		Dump=[]
		Dump.append({"Name":str(sys.argv[1]),"Nodes":str(nx.number_of_nodes(graph)),"Edges":str(nx.number_of_edges(graph)),"Extended_Nodes":str(n),"Extended_Edges":str(len(edges)),"Clusters":str(len(clusters))})
		print "Dump : ",Dump	
		with open('statistics.json', 'w') as cfile:
			json.dump(Dump, cfile, indent=4)
		
		
		
		
