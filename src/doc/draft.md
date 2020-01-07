Topological sort for DAG
One solution is to solve in O(VE) time using Bellman–Ford. If there are no negative weight cycles, then we can solve in O(E + VLogV) time using Dijkstra’s algorithm. 

Since the graph is unweighted, we can solve this problem in O(V + E) time. 

Moving down the complexity of the graph,

- No limitation at all - Bellman ford
- Can have cycle, no negative weighted edges - Dijkstra's algorithm
- No cycle, can have negative weighted edges, DAG - Topological sort + BFS 
- No weight on edges, can have cycle - BFS

Usually there are two parts of a coding interview – coming up with the right approach and writing solid code, it’s always better to figure out which is your bottleneck before the optimization.

If you are slow to provide a solution, it’s very likely that you don’t have enough practice. Once you have worked on tons of coding questions, you’ll come up with the right approach within minutes. In this case, I don’t have any better suggestion than practicing as much as you can.

If you find yourself slow to finish coding, you should really write solid code for every question you practice with. I’ve seen so many candidates who came up with an approach quickly but failed to finish the code within the whole session. Do write solid code (NO PSUEDO CODE!) while practicing. It’s totally different from “solving in your mind”.

Tip #1 – Clear mind
It may sound counterintuitive, but it’s never too late to write your solution until you have a concrete idea in your mind. Sometimes I felt like I’ve spent too much time thinking and couldn’t wait to start coding my solution, which was the disaster. Probably someone can think while coding, but it never works for me.

Instead, what I found more effective is to start coding only after I’m confident enough about my approach. I would first discuss my solution with the interviewer, which could verify my idea to some extent. I might start coding later than average people, but I’m pretty sure that my solution works and I won’t waste time writing or even drawing randomly on the whiteboard.

A good way to check if your idea is concrete enough is to see if you can transform your idea into pseudo code. You don’t need to write it down, but if you find a specific part of the logic is not that clear, you’d better think more about it.