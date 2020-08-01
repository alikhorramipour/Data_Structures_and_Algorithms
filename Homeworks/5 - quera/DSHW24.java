import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

class DSHW24 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        ArrayList<Integer> arr = new ArrayList<>();
        ArrayList<Object> arr2 = new ArrayList<>();
        int n = input.nextInt();
        for (int i = 0; i < n; i++) {
            int n1 = input.nextInt();
            int n2 = input.nextInt();
            arr2.add(new Object(n1, n2));
        }
        for (int i = 0; i < arr2.size(); i++) {
            if (arr2.get(i).n1 == 1) {
                arr.add(arr2.get(i).n2);
                printFirstAboveAverageItem(arr);
            } else if (arr2.get(i).n1 == 2) {
                arr.remove(arr.indexOf(arr2.get(i).n2));
                printFirstAboveAverageItem(arr);
            }
            // System.out.println(arr);
            // System.out.println("-------------------------");
        }

    }

    public static void printFirstAboveAverageItem(ArrayList<Integer> arr) {
        // System.out.println("reached here");
        Collections.sort(arr);
        for (int i = 0; i < arr.size(); i++) {
            if ((double) arr.get(i) >= findAverage(arr)) {
                System.out.println(arr.get(i));
                break;
            }
        }
        // return 0;
    }

    public static double findAverage(ArrayList<Integer> arr) {
        double sum = 0;
        for (int i = 0; i < arr.size(); i++)
            sum += arr.get(i);
        return sum / arr.size();
    }
}

class Object {

    int n1;
    int n2;

    Object(int n1, int n2) {
        this.n1 = n1;
        this.n2 = n2;
    }
}
