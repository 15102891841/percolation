

public  class quick_find {
    int N;
    int[] id ;
    public quick_find(int N) {
        this.N = N;
        this.id=new int[N];
        for (int i = 0; i < N; i++) {
            id[i] = i;
        }
    }

    public boolean connected(int p, int q) {
        return id[p] == id[q];
    }

    public void union(int p,int q)
    {
        id[p] = id[q];
    }

    public static void main(String[] args) {
        quick_find array = new quick_find(10) ;
        for(int i=0;i<10;i++)
        {System.out.println(array.id[i]);}
        array.union(1,3);
        System.out.println("###################");
        for(int i=0;i<10;i++)
        {System.out.println(array.id[i]);}
       System.out.println(array.connected(1,3));
    }


}



