package algorithms_4th_java.chapter4.graph;

import algorithms_4th_java.chapter4.graph.Graph;
import algorithms_4th_java.chapter4.graph.SymbolGraph;
import algorithms_4th_java.chapter4.graph.bfs.BreadthFirstPaths;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/**
 * @author TerenceG
 * @classname DegreeOfSeparation
 * @description 间隔的度数 4.1.7.4
 * @lastmodifydate 2021/7/20
 */
public class DegreeOfSeparation {
    public static void main(String[] args) {
        SymbolGraph sg=new SymbolGraph(args[0],args[1]);
        Graph G=sg.G();
        String source =args[2];
        if(!sg.contains(source)){
            StdOut.println(source+" not in database.");
            return ;
        }

        int s=sg.index(source);
        BreadthFirstPaths bfs=new BreadthFirstPaths(G,s);

        while(!StdIn.isEmpty()){
            String sink=StdIn.readLine();
            if(sg.contains(sink)){
                int t=sg.index(sink);
                if(bfs.hasPathTo(t)){
                    for(int v:bfs.pathTo(t)){
                        StdOut.println(" "+sg.name(v));
                    }
                }else{
                    StdOut.println("Not connected.");
                }
            }else{
                StdOut.println("Not in database.");
            }
        }
    }
}