import levelFirst.ArrayOfNumbers;
import levelFirst.MoveOfKnight;
import levelFirst.TriangleArea;
import levelSecond.ValidString;
import levelThird.GameLife;

public class ModuleTest {

    public static void main(String[] args) {
        System.out.println("==== LEVEL 1 ====");
        System.out.println("==== Task  1 ====");
        ArrayOfNumbers.getUniqueNumberFromArray();

        System.out.println("==== Task  2 ====");
        MoveOfKnight.canKnightMove();

        System.out.println("==== Task  3 ====");
        TriangleArea.findAreaOfTriangle();

        System.out.println("==== LEVEL 2 ====");
        System.out.println("==== Task  1 ====");
        ValidString.isValidString();

        System.out.println("==== LEVEL 3 ====");
        System.out.println("==== Task  1 ====");
        GameLife.startLife();
    }
}
