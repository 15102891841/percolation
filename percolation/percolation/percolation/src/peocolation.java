public class peocolation {

    int N;
    int id[];

    public peocolation(int N)
    {
        this.N=N;
        for(int i=0;i<N;i++){id[i]=i;}
    }
    public boolean quick_find(int p,int q)  //快速查询，查询o(1);
    {
     return  id[p] == id [q];
    }




}
