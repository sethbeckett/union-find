class UnionFind {
    private int [] arr;

    public UnionFind(int size) {
        arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = -1;
        }
    }

    /**
     * finds parent of an index, implemented with path compression
     * @param num the number which we're finding
     * @return the num's root, and recursively update parents for path compression
     */
    public int find(int num) {
        if (arr[num] < 0) return num;
        else {
            arr[num] = find(arr[num]);
            return arr[num];
        }
    }

    /**
     * joins two sets in the array, implemented smart union by rank. arr[root] is the parent/root of root
     * @param num1 an element of the first set to be joined, where
     * @param num2 an element of the second set to be joined
     */
    public void union(int num1, int num2) {
        int root1 = find(num1);
        int root2 = find(num2);

        if (root1 == root2) {
//            System.out.println("They're already in the same set!!");
            return;
        }

        if (arr[root2] < arr[root1]) arr[root1] = root2; //point root1 to root2 if root2 is higher rank (deeper)

        else {
            if (arr[root1] == arr[root2]) arr[root1]--; //increase rank by 1 if they're the same rank, otherwise rank of bigger won't change
            arr[root2] = root1; //make two a child of one
        }
    }

    @Override
    public String toString() {
        String arrString = "Union/Find Array\n";
//        arrString += "Index | Root\n";
        for (int i=0; i < arr.length; i++) {
//            arrString += "----------\n";
            arrString += "Idx: " +i + " | Parent: " + arr[i] + "\n";
        }
        return arrString;
    }


}
