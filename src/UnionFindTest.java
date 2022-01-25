public class UnionFindTest {
    public static void main(String[] args) {

        UnionFind testUFArray = new UnionFind(30);

        System.out.println("The initial array: \n" + testUFArray);

        testUFArray.union(0,1);
        System.out.println("The array after a union of 0 and 1 (union by height is used)");
        System.out.println(testUFArray);

        testUFArray.union(1,2);
        System.out.println("The array after a union of 1 and 2 (union by height is used)");
        System.out.println(testUFArray);

        testUFArray.union(3,4);
        System.out.println("The array after a union of 3 and 4 (union by height is used)");
        System.out.println(testUFArray);

        testUFArray.union(0,4);
        System.out.println("The array after a union of 0 and 4 (union by height is used)");
        System.out.println(testUFArray);

        testUFArray.union(5,6);
        testUFArray.union(7,8);
        testUFArray.union(6,8);
        testUFArray.union(5,9);
        System.out.println(testUFArray);
        testUFArray.union(3,7);
        System.out.println(testUFArray);
        testUFArray.union(9, 5);

        int[] te = new int[10];
        for (int i: te) {
            System.out.println(i);
        }

    }
}
