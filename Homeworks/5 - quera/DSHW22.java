import java.io.*;
import java.util.*;

class DSHW22 {
    public static void main(String args[]) {

        Graph graph = new Graph();
        InputReader input = new InputReader(System.in);
        PrintWriter out = new PrintWriter(System.out);
        int n = input.readInt();
        int k = input.readInt();
        int m = input.readInt();
        for (int i = 0; i < n; i++)
            graph.addVertex();
        for (int i = 0; i < k; i++) {
            int temp = input.readInt();
            graph.setNodeToHospital(temp);
        }
        for (int i = 0; i < m; i++) {
            int temp = input.readInt();
            int temp2 = input.readInt();
            graph.addEdge(temp, temp2);
        }
        // long tt3 = System.currentTimeMillis();
        // System.out.println(graph.vertices);
        graph.simultaneousBFS(graph.hospitals);
        // long tt4 = System.currentTimeMillis();
        for (int i = 0; i < graph.vertices.size(); i++) {
            int value;
            if (graph.vertices.get(i).distanceToClosestHospital == 1000000)
                value = n;
            else
                value = graph.vertices.get(i).distanceToClosestHospital;
            out.print(value);
            out.print(" ");
        }
        out.flush();
        out.close();
        // long tt2 = System.currentTimeMillis();
        // System.out.println();
        // System.out.println(tt2 - tt);
        // System.out.println(tt4 - tt3);

    }
}

class Node {
    int key;
    boolean isHospital;
    boolean visited = false;
    String nearestHospital = new String();
    int distanceToClosestHospital = 1000000;
    ArrayList<Node> adjList = new ArrayList<>();

    Node(int key) {
        this.key = key;
        this.isHospital = false;
    }

    public void setIsHapital() {
        this.isHospital = true;
    }

    @Override
    public String toString() {
        return "*" + key + nearestHospital + "*" + " - " + distanceToClosestHospital;
    }
}

class Graph {

    ArrayList<Node> vertices = new ArrayList<>();
    ArrayList<Integer> hospitals = new ArrayList<>();

    public void addVertex() {
        vertices.add(new Node(vertices.size()));
    }

    public void addEdge(int key1, int key2) {
        vertices.get(key1 - 1).adjList.add(vertices.get(key2 - 1));
        vertices.get(key2 - 1).adjList.add(vertices.get(key1 - 1));
    }

    public void setNodeToHospital(int key) {
        hospitals.add(key - 1);
        vertices.get(key - 1).setIsHapital();
    }

    public void simultaneousBFS(ArrayList<Integer> hospitals) {

        // ArrayList<LinkedList<Integer>> queues = new ArrayList<>();
        Queue<Integer> queue = new LinkedList<Integer>();
        for (int i = 0; i < vertices.size(); i++)
            vertices.get(i).visited = false;
        for (int i = 0; i < hospitals.size(); i++) {
            // queues.add(new LinkedList<Integer>());
            vertices.get(hospitals.get(i)).distanceToClosestHospital = 0;
            vertices.get(hospitals.get(i)).visited = true;
            // queues.get(i).add(hospitals.get(i));
            queue.add(hospitals.get(i));
        }
        // int level = 0;
        // int k = -1;
        int s;
        while (!queue.isEmpty()) {
            // System.out.println("here");
            // k = (k + 1) % (queues.size());

            // System.out.println("-----------------------------");
            // System.out.println("queue " + (hospitals.get(k) + 1) + " " + queues.get(k));

            // while (vertices.get(queues.get(k).peek()).distanceToClosestHospital == level)
            // {
            s = queue.poll();
            for (int i = 0; i < vertices.get(s).adjList.size(); i++) {
                Node temp = vertices.get(s).adjList.get(i);
                if (!temp.visited) {
                    temp.visited = true;
                    // temp.nearestHospital = "h" + (hospitals.get(k) + 1);
                    queue.add(temp.key);
                    temp.distanceToClosestHospital = vertices.get(s).distanceToClosestHospital + 1;
                }
            }
            // level++;
            // System.out.println(vertices);
            // System.out.println("-----------------------------");
        }
    }

    public boolean isEmpty(ArrayList<LinkedList<Integer>> queues) {
        for (int i = 0; i < queues.size(); i++) {
            if (!queues.get(i).isEmpty()) {
                // System.out.println("isn't empty");
                return false;
            }
        }
        // System.out.println("is empty");
        return true;
    }

    // public int minimumLevelOfQueues(ArrayList<LinkedList<Integer>> queues) {
    //     ArrayList<Integer> arr = new ArrayList<>();
    //     for (int i = 0; i < queues.size(); i++) {
    //         arr.add(vertices.get(queues.get(i).peek()).distanceToClosestHospital);
    //     }
    //     Collections.sort(arr);
    //     return vearr.get(0)
    // }

    @Override
    public String toString() {
        return vertices + "";
    }

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

class OutputWriter {
    private final PrintWriter writer;

    public OutputWriter(OutputStream outputStream) {
        writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(outputStream)));
    }

    public OutputWriter(Writer writer) {
        this.writer = new PrintWriter(writer);
    }

    public void print(Object... objects) {
        for (int i = 0; i < objects.length; i++) {
            if (i != 0)
                writer.print(' ');
            writer.print(objects[i]);
        }
    }

    public void printLine(Object... objects) {
        print(objects);
        writer.println();
    }

    public void close() {
        writer.close();
    }

    public void flush() {
        writer.flush();
    }

}

class IOUtils {

    public static int[] readIntArray(InputReader in, int size) {
        int[] array = new int[size];
        for (int i = 0; i < size; i++)
            array[i] = in.readInt();
        return array;
    }

}