import edu.princeton.cs.algs4.WeightedQuickUnionUF;
public class Percolation {
    private int N;
    private WeightedQuickUnionUF uf;
    private WeightedQuickUnionUF uf1;//for verification
    private int[] status;
    private int vituralsite0;
    private int vituralsite1;
    private int numberOfOpenSites;
    public Percolation(int N) {
        this.N=N;
        validate(N);
        this.uf=new WeightedQuickUnionUF(N*N+2);
        this.uf1=new WeightedQuickUnionUF(N*N+2);
        this.status = new int[N*N+2];
        this.numberOfOpenSites=0;
        this.vituralsite0 = N*N;
        this.vituralsite1 = N*N+1;
        for (int a = 0; a < N; a++) {
            uf.union(a,vituralsite0);
            uf1.union(a,vituralsite0);
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
        validate(row,col);
        int index=maps2d_to_1d(row, col);
        if(isOpen(row,col)){return;}
        status[index]=1;
        numberOfOpenSites++;
//        for (int k = 1; k <= N; k++) {
//            for (int m = 1; m <= N; m++) {
//                int index_i = maps2d_to_1d(k,m);
//                if (status[index_i]==1  & Math.abs(row-k)==1 & col==m) {
//                    uf.union(index,index_i);
//                    uf1.union(index,index_i);
//                }
//                if (status[index_i]==1 & Math.abs(col-m)==1 & row==k) {
//                    uf.union(index, index_i);
//                    uf1.union(index,index_i);
//                }
//            }
//        }
        if(row-1>=1 && row-1<=N && status[maps2d_to_1d(row-1,col)]==1)
        {
            uf.union(index,maps2d_to_1d(row-1,col));
            uf1.union(index,maps2d_to_1d(row-1,col));
        }
        if(row+1>=1 && row+1<=N && status[maps2d_to_1d(row+1,col)]==1)
        {
            uf.union(index,maps2d_to_1d(row+1,col));
            uf1.union(index,maps2d_to_1d(row+1,col));
        }
        if(col-1>=1 && col-1<=N && status[maps2d_to_1d(row,col-1)]==1)
        {
            uf.union(index,maps2d_to_1d(row,col-1));
            uf1.union(index,maps2d_to_1d(row,col-1));
        }
        if(col+1>=1 && col+1<=N && status[maps2d_to_1d(row,col+1)]==1)
        {
            uf.union(index,maps2d_to_1d(row,col+1));
            uf1.union(index,maps2d_to_1d(row,col+1));
        }


    }
    public boolean isOpen(int row, int col)
    {
        validate(row,col);
        int index=maps2d_to_1d(row, col);
        if(status[index]==1){return  true;}
        else {return  false;}
    }
    public boolean isFull(int row, int col)
    {
        validate(row,col);
        int index=maps2d_to_1d(row, col);
        if(uf1.find(index)==uf1.find(vituralsite0)&&status[index]==1 ){return true;}
        return  false;
    }
    public int numberOfOpenSites()
    {
        return  numberOfOpenSites;
    }
    public boolean percolates()
    {
        if(N==1 && status[0]==1){return true;}
        if(N==1 && status[0]!=1){return false;}
        if(uf.find(vituralsite0)==uf.find(vituralsite1)){return true;}
        return  false;
    }


    private void validate(int row,int col) {

        if (row <= 0 || row > N) {
            throw new IllegalArgumentException("index " + row + " is not between 0 and " + (N*N));
        }
        if (col <= 0 || col > N) {
            throw new IllegalArgumentException("index " + row + " is not between 0 and " + (N*N));
        }
    }
    private void validate(int index) {

        if (index <= 0){
            throw new IllegalArgumentException("index " + N + " is not correct");
        }
    }


    public static void main(String[] args) {
        Percolation test =new Percolation(5);
        System.out.print("123");
    }
}
