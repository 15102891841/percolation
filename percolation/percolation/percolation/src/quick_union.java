public class quick_union {
    int N;
    int[] id ;
    public quick_union(int N) {
        this.N = N;
        this.id=new int[N];
        for (int i = 0; i < N; i++) {
            id[i] = i;
        }
    }

    public boolean connected(int p, int q) {
        int i = get_root(p);
        int j = get_root(q);
        return i == j;
    }

    public void union(int p,int q)
    {
        int i = get_root(p);
        int j = get_root(q);
        id [i] = j;
    }

   public int get_root(int i)
   {
       while( i != id[i] ){ i = id[i];}
       return i ;
   }
    public static void main(String[] args) {
        quick_union array = new quick_union(10);
        for(int i=0;i<10;i++)
        {System.out.println(array.id[i]);}
        array.union(4,3);
        array.union(3,8);
        array.union(6,5);
        array.union(9,4);
        array.union(2,1);

        System.out.println("###################");
        for(int i=0;i<10;i++)
        {System.out.println(array.id[i]);}
        System.out.println(array.connected(1,3));
    }

}
