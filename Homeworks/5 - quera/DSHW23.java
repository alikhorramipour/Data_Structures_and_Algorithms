import java.util.*;
import java.io.*;

class DSHW23 {
    public static void main(String args[]) {
        InputReader input = new InputReader(System.in);
        int n = input.readInt();
        int q = input.readInt();
        ArrayList<Pair> edges = new ArrayList();
        Graph graph = new Graph(n);
        PrintWriter out = new PrintWriter(System.out);
        for (int i = 0; i < n - 1; i++) {
            int temp = input.readInt();
            int temp2 = input.readInt();
            graph.addEdge(temp, temp2);
        }
        for (int i = 0; i < q; i++) {
            int temp = input.readInt();
            int temp2 = input.readInt();
            if (temp == temp2) {
                out.println(graph.exitNodes);
                continue;
            }
            if (graph.exitNodes == 0) {
                out.println(0);
                continue;
            }
            if (graph.vertices.get(temp - 1).isExit)
                graph.exitNodes -= 1;
            graph.BFS(temp2 - 1, temp - 1);
            out.println(graph.exitNodes);
        }
        out.flush();
        out.close();

    }
}

class Node {
    int key;
    boolean isExit;
    boolean visited = false;
    ArrayList<Node> adjList = new ArrayList<>();

    Node(int key) {
        this.key = key;
        this.isExit = true;
    }
}

// class Pair {

//     int number1;
//     int number2;

//     Pair(int num1, int num2) {
//         number1 = num1;
//         number2 = num2;
//     }

//     public static boolean duplicateFinder(ArrayList<Pair> arr, int num1, int num2) {
//         for (int i = 0; i < arr.size(); i++) {
//             if (arr.get(i).number1 == num1 && arr.get(i).number2 == num2)
//                 return true;
//         }
//         return false;
//     }
// }

class Graph {

    ArrayList<Node> vertices = new ArrayList<>();
    Queue<Integer> queue = new LinkedList<Integer>();
    int exitNodes;

    Graph(int n) {
        exitNodes = n;
        for (int i = 0; i < n; i++)
            vertices.add(new Node(vertices.size()));
    }

    public void addEdge(int key1, int key2) {
        vertices.get(key1 - 1).adjList.add(vertices.get(key2 - 1));
        vertices.get(key2 - 1).adjList.add(vertices.get(key1 - 1));
    }

    public void BFS(int u, int v) {
        // for (int i = 0; i < vertices.size(); i++) {
        //     vertices.get(i).visited = false;
        // }
        // vertices.get(u).visited = true;
        vertices.get(v).visited = true;
        vertices.get(v).isExit = false;
        queue.add(v);
        while (!queue.isEmpty()) {
            int s = queue.poll();
            for (int i = 0; i < vertices.get(s).adjList.size(); i++) {
                Node temp = vertices.get(s).adjList.get(i);
                if (!temp.visited && temp.isExit && temp.key != u) {
                    temp.visited = true;
                    queue.add(temp.key);
                    exitNodes--;
                    temp.isExit = false;
                }
            }
        }
    }

    // public void twoWayBFS(int u, int v) {
    //     int s;
    //     for (int i = 0; i < vertices.size(); i++) {
    //         vertices.get(i).visited = false;
    //         vertices.get(i).visited2 = false;
    //     }
    //     vertices.get(u).visited = true;
    //     vertices.get(v).visited2 = true;
    //     vertices.get(v).isExit = false;
    //     // vertices.get(u).color = "red";
    //     // vertices.get(v).color = "blue";
    //     Queue<Integer> queue = new LinkedList<Integer>();
    //     Queue<Integer> queue2 = new LinkedList<Integer>();
    //     queue.add(u);
    //     queue2.add(v);
    //     while (!queue2.isEmpty()) {
    //         if (!queue.isEmpty()) {
    //             s = queue.poll();
    //             for (int i = 0; i < vertices.get(s).adjList.size(); i++) {
    //                 Node temp = vertices.get(s).adjList.get(i);
    //                 if (!temp.isExit)
    //                     continue;
    //                 if (!temp.visited2 && !temp.visited) {
    //                     temp.visited = true;
    //                     queue.add(temp.key);
    //                 }
    //             }
    //         }
    //         // System.out.println("queue 1 ===> " + queue);

    //         if (!queue2.isEmpty()) {
    //             s = queue2.poll();
    //             for (int i = 0; i < vertices.get(s).adjList.size(); i++) {
    //                 Node temp = vertices.get(s).adjList.get(i);
    //                 if (!temp.isExit)
    //                     continue;
    //                 if (!temp.visited2 && !temp.visited) {
    //                     temp.visited2 = true;
    //                     if (temp.isExit)
    //                         exitNodes--;
    //                     temp.isExit = false;
    //                     queue2.add(temp.key);
    //                 }
    //             }
    //         }
    //         // if (common != -1) {
    //         // s = queue.poll();
    //         // for (int i = 0; i < vertices.get(s).adjList.size(); i++) {
    //         // Node temp = vertices.get(s).adjList.get(i);
    //         // if (!temp.visited2) {
    //         // temp.visited2 = true;
    //         // temp.distance = vertices.get(s).distance + 1;
    //         // }
    //         // }
    //         // }
    //         // System.out.println("queue 2 ===> " + queue2);
    //         // if (common == -1)
    //         // common = commonElementInTwoQueues(queue, queue2);
    //     }
    // }

    // public void newTwoWayBFS(int u, int v) {
    //     int s;
    //     for (int i = 0; i < vertices.size(); i++) {
    //         vertices.get(i).visited = false;
    //         vertices.get(i).visited2 = false;
    //     }
    //     vertices.get(u).visited = true;
    //     vertices.get(v).visited2 = true;
    //     Queue<Integer> queue = new LinkedList<Integer>();
    //     queue.add(u);
    //     queue.add(v);
    //     while (!queue.isEmpty()) {
    //         s = queue.poll();
    //         for (int i = 0; i < vertices.get(s).adjList.size(); i++) {
    //             Node temp = vertices.get(s).adjList.get(i);
    //             if (!temp.visited2 && !temp.visited) {
    //                 if (vertices.get(s).visited)
    //                     temp.visited = true;
    //                 else {
    //                     temp.visited2 = true;
    //                     if (temp.isExit)
    //                         exitNodes--;
    //                     temp.isExit = false;
    //                 }
    //                 queue.add(temp.key);
    //             }
    //         }
    //     }

    // }

    // public int commonElementInTwoQueues(Queue<Integer> queue1, Queue<Integer> queue2) {
    //     Queue<Integer> q1 = new LinkedList<>(queue1);
    //     Queue<Integer> q2 = new LinkedList<>(queue2);
    //     if (q1.isEmpty())
    //         return -1;
    //     int temp = q1.poll();
    //     if (q2.contains(temp))
    //         return temp;
    //     else
    //         return commonElementInTwoQueues(q1, q2);
    // }

    // @Override
    // public String toString() {
    //     return vertices + "";
    // }

}

class InputReader {

    private InputStream stream;
    private byte[] buf = new byte[1024];
    private int curChar;
    private int numChars;
    private SpaceCharFilter filter;

    public InputReader(InputStream stream) {
        this.stream = stream;
    }

    public int read() {
        if (numChars == -1)
            throw new InputMismatchException();
        if (curChar >= numChars) {
            curChar = 0;
            try {
                numChars = stream.read(buf);
            } catch (IOException e) {
                throw new InputMismatchException();
            }
            if (numChars <= 0)
                return -1;
        }
        return buf[curChar++];
    }

    public int readInt() {
        int c = read();
        while (isSpaceChar(c))
            c = read();
        int sgn = 1;
        if (c == '-') {
            sgn = -1;
            c = read();
        }
        int res = 0;
        do {
            if (c < '0' || c > '9')
                throw new InputMismatchException();
            res *= 10;
            res += c - '0';
            c = read();
        } while (!isSpaceChar(c));
        return res * sgn;
    }

    public String readString() {
        int c = read();
        while (isSpaceChar(c))
            c = read();
        StringBuilder res = new StringBuilder();
        do {
            res.appendCodePoint(c);
            c = read();
        } while (!isSpaceChar(c));
        return res.toString();
    }

    public boolean isSpaceChar(int c) {
        if (filter != null)
            return filter.isSpaceChar(c);
        return c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == -1;
    }

    public String next() {
        return readString();
    }

    public interface SpaceCharFilter {
        public boolean isSpaceChar(int ch);
    }
}