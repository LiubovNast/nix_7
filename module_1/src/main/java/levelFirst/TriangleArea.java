package levelFirst;

import static console.InputConsole.getString;

public class TriangleArea {

    public static void findAreaOfTriangle() {
        int[] A = getPoint();
        int[] B = getPoint();
        int[] C = getPoint();

        int a = getLength(A, B);
        int b = getLength(B, C);
        int c = getLength(C, A);
        if ((a + b) > c && (b + c) > a && (a + c) > b) {
            System.out.println("The triangle's area is - " + (double) (a + b + c) / 2);
        } else {
            System.out.println("You inputted invalid sides. It is not triangle.");
            findAreaOfTriangle();
        }
    }

    private static int[] getPoint() {
        System.out.println("Enter point coordinates (e.g. x;y)");
        String temp = getString();
        int[] point = new int[2];
        if (!temp.matches("\\-?\\d+\\;\\-?\\d+")) {
            System.out.println("Illegal input");
            getPoint();
        } else {
            String[] points = temp.split(";");
            for (int i = 0; i < points.length; i++) {
                point[i] = Integer.parseInt(points[i]);
            }
        }
        return point;
    }

    private static int getLength(int[] a, int[] b) {
        int side = (int) Math.sqrt(Math.pow((b[0] - a[0]), 2) + Math.pow((b[1] - a[1]), 2));
        return side;
    }
}
