# path finder

open set --> all nodes we are considering ---> every nodes that we might make the next step from 
for each node --. keep track of current best score, estimated total score and current best previous node 

two different scores:
1. score to get from one node to next 
2. second score give estimate of cost from any node to estimation 

at the VERY star 
open set is the start node 

at EACH iteration : 
1. select node from open set that has lowest estimated total score 
2. remove this node from open set
3. then add all of the nodes we can reach from that removed node 
