import edu.princeton.cs.algs4.WeightedQuickUnionUF;
public class Percolation {
    private int N;
    private WeightedQuickUnionUF uf;
    private int[] status;
    private int vituralsite0;
    private int vituralsite1;
    private int numberOfOpenSites;
    public Percolation(int N) {
        this.N=N;
        this.uf=new WeightedQuickUnionUF(N*N+2);
        this.status = new int[N*N+2];
        this.numberOfOpenSites=0;
        this.vituralsite0 = N*N;
        this.vituralsite1 = N*N+1;
        for (int a = 0; a < N; a++) {
            uf.union(a,vituralsite0);
        }
        for (int a = N*N-N; a < N*N; a++) {
            uf.union(a,vituralsite1);
        }
    }

    private int maps2d_to_1d(int x,int y)
    {
        int result=N*(x-1)+(y-1);
        return result;
    }
    private int[] maps1d_to_2d(int result)
    {
        int[] a=new int[2];
        a[0]=(result/(N))+1;
        a[1]=(result%(N))+1;
        return  a;
    }


    public void open(int row, int col)
    {
        int index=maps2d_to_1d(row, col);
        validate(index);
        status[index]= 1;
        numberOfOpenSites ++;
        for (int k = 1; k <= N; k++) {
            for (int m = 1; m <= N; m++) {
                int index_i = maps2d_to_1d(k,m);
                if (status[index_i]==1  & Math.abs(row-k)==1 & col==m) {
                    uf.union(index,index_i);
                }
                if (status[index_i]==1 & Math.abs(col-m)==1 & row==k) {
                    uf.union(index, index_i);
                }
            }
        }



    }
    public boolean isOpen(int row, int col)
    {
        int index=maps2d_to_1d(row, col);
        validate(index);
        if(status[index]==1){return  true;}
        else {return  false;}
    }
    public boolean isFull(int row, int col)
    {
        int index=maps2d_to_1d(row, col);
        validate(index);
        return  (uf.find(index)==uf.find(vituralsite0)& status[index]==1 );
    }
    public int numberOfOpenSites()
    {
        return  numberOfOpenSites;
    }
    public boolean percolates()
    {
        return  uf.find(N*N)==uf.find(N*N+1);
    }


    private void validate(int p) {

        if (p < 0 || p >= N*N) {
            throw new IllegalArgumentException("index " + p + " is not between 0 and " + (N*N));
        }
    }

}
