class Solution {
    public int fib(int n) {
        return fib(n, true);
    }
    public int fib(int n, boolean useRecursion){
        if(useRecursion){
            return recursiveFib(n);
        }
        return iterativeFib(n);
    }

    public int recursiveFib(int n){
        if (n <= 1){
            return n;
        }
        return recursiveFib(n - 1) + recursiveFib(n - 2);
    }

    public int iterativeFib(int n){
        if (n <= 1){
            return n;
        }
        int a = 0;
        int b = 1;
        for (int i = 2; i <= n; i++){
            int c = a + b;
            a = b;
            b = c;
        }
        return b;
    }
}