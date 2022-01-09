# path finder
#this is a project that educates users on path finding algorithms, we included the following algorithms: 
1. A* 

https://user-images.githubusercontent.com/32078486/148701302-e7d01b2c-603f-4b98-9c61-d3a1e559d019.mov


2. Dijkstra 

https://user-images.githubusercontent.com/32078486/148701315-8393b8ee-64ce-4921-bce3-f077ef4bb349.mov



3. Bi-directional

https://user-images.githubusercontent.com/32078486/148701329-b471c521-3e4c-4df6-8185-c33348fe5575.mov

4. Holistic value search (greedy). Does not give the shortest path 

https://user-images.githubusercontent.com/32078486/148701322-75cea36c-a4fb-4fa5-83c9-abb10eb7ede9.mov

5. Breadth first search (BFS) note that BFS can not go diagnal


https://user-images.githubusercontent.com/32078486/148701343-4b1ad568-8104-4b85-a359-ca98a955de61.mov


#To use this application:
1. hold **s** on keyboard to place a starting point 
2. hold **e** on keyboard to place the ending point 
3. hold **b**, and drag the mouse across the grid to create your own obstables to challenge the path finding algorithms. Or simply select "make maze" button to randmoly generate a maze. 
4. select desired algorithms from the drop box and run the find path algorithms using the find path button.
5. one can also **remove obstables** or **remove everything** by selecting the corresponding buttons.



https://user-images.githubusercontent.com/32078486/148701353-7db18d24-37a2-4247-a394-4a1f8700dbd2.mov



To demonstrate the process of the algorithms, we employed the **Observer Design Pattern** to show the exploration of process at each iteration. This is a very effective and concise way to visually demonstrate how each algorithms differ in terms of exploation process and run time. 

addition note :

    if the user desires to slow down the process of the algorithms at each iteration, simply uncomment timer delay code in the update method in the GUI class

    

