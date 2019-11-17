
#### Shortest Path 

| Algorithm            | Time Complexity  | Space Complexity | Data Structure                                     |
|:---------------------|:-----------------|:-----------------|:---------------------------------------------------|
| Breadth First Search | O(V + E)         | O(V)             | Set for visited node, Queue for unvisited node     |
| Topological Sort     | O(V + E)         | O(V)             | List for the already topologically sorted vertices |
| Dijkstra             | O(V log(V) + E)  | O(V)             | Priority Queue for unvisited nodes                 |
| Bellman Ford         | O(V * E)         | O(V)             |                                                    |

**Note**: 
1. All need a hash table to store the predecessor vertex to trace back shortest path.
2. All need a array to store the (current) computed shortest distance.  