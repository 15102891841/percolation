import java.util.ArrayList;
import java.util.ListIterator;
import java.util.Random;

public class peocolation_N2 {
    //做了合树优化，还没做路径压
    //随机数还没去重，并且校验通路还不合理
    //要生成不重复的随机数
    int N;
    int[][] id;
    int[][] sz;
    int[][] status;//0代表堵塞，1代表打开
    int[] vituralsite0;
    int[] vituralsite1;

    public peocolation_N2(int N) {
        this.N = N;
        this.id = new int[N + 1][N + 1];
        this.sz = new int[N + 1][N + 1];
        this.status = new int[N + 1][N + 1];
        this.vituralsite0 = new int[2];
        this.vituralsite1 = new int[2];
        this.vituralsite0[0] = 0;
        this.vituralsite0[1] = N;
        this.vituralsite1[0] = N - 1;
        this.vituralsite1[1] = N;
        for (int j = 0; j < N + 1; j++) {
            for (int i = 0; i < N + 1; i++) {
                id[j][i] = (N + 1) * j + i;
                sz[j][i]=1;
            }    //

        }
//        this.id[0][N] = 0;   //上表面的虚拟连接点
//        this.id[N - 1][N] = N * N - 1;  //下表面的虚拟连接点
        int[] p = {0, N};
        int[] q = {N - 1, N};
        int[] medium = new int[2];
        medium[0] = 0;
        for (int a = 0; a < N; a++) {
            medium[1] = a;
            union(p, medium);
        }


        medium[0] = N - 1;
        for (int a = 0; a < N; a++) {
            medium[1] = a;
            union(q, medium);
        }
    }

    public int maps2d_to_1d(int x,int y)
    {
        int result=x*(N+1)+y;
        return result;
    }
    public int[] maps1d_to_2d(int result)
    {
     int[] a=new int[2];
     a[0]=result/(N+1);
     a[1]=result%(N+1);
     return  a;
    }
    public int get_root(int[] p) {
//        int medium_b;
//        int[] medium = new int[2];
//        medium[0] = p[0];
//        medium[1] = p[1];
//        while (id[medium[0]][medium[1]] != medium[0] * (N + 1) + medium[1]) {
//            medium_b = id[medium[0]][medium[1]];
//            medium[0] = medium_b / (N + 1);
//            medium[1] = medium_b % (N + 1);
//        return (medium[0] * (N + 1) + medium[1]);
        int p0=p[0];int p1=p[1];
        int[] buffer = new int[2];
        int pId=maps2d_to_1d(p0,p1);
        while(pId!=id[p0][p1])
        {
            pId=id[p0][p1];
           buffer= maps1d_to_2d(pId);
           p0=buffer[0];p1=buffer[1];


        }
        return pId;
        }



    public boolean connected(int[] p, int[] q) {
        int i = get_root(p);
        int j = get_root(q);
        return i == j;
    }

    public void union(int[] p, int[] q) {
        int[] medium = new int[4];
        int i = get_root(p);
        int j = get_root(q);
       int[] I=maps1d_to_2d(i);
       int[] J=maps1d_to_2d(j);
        if(i==j){return;}
        if (sz[I[0]][I[1]] > sz[J[0]][J[1]]) {
            id[J[0]][J[1]]=i;

        }
        if (sz[I[0]][I[1]] < sz[J[0]][J[1]]) { id[I[0]][I[1]]=j;}
        if (sz[I[0]][I[1]] == sz[J[0]][J[1]])
        {
            id[J[0]][J[1]]=i;sz[I[0]][I[1]]++;
        }
    }
    public double random_event() {
        double overwhelmed_times = 0;
        Random r = new Random();
        int r1;
        int r2;
        int[] random = new int[2];
        int[] buffer = new int[2];
        outer:
        while (true) {
            r1 = r.nextInt(N);
            r2 = r.nextInt(N);
            for (int j = 0; j < N; j++) {
                for (int i = 0; i < N; i++) {
                    if (i == r1 & j == r2 & status[i][j] == 1) {
                        continue outer;
                    }
                }
            }
            status[r1][r2] = 1;
            overwhelmed_times++;
            for (int k = 0; k < N; k++) {
                for (int m = 0; m < N; m++) {
                    buffer[0] = m;
                    buffer[1] = k;
                    random[0] = r1;
                    random[1] = r2;
                    if (status[m][k] == 1 & Math.abs(r1 - m) == 1 & r2 == k) {
                        union(random, buffer);
                    }
                    if (Math.abs(r2 - k) == 1 & r1 == m & status[m][k] == 1) {
                        union(random, buffer);
                    }
                }
            }
            if (connected(vituralsite0, vituralsite1) == true) {
                return (overwhelmed_times / (N * N));
            }
        }
    }
    public static void main(String[] args) {
        int N = 10;
        peocolation_N2 array_test = new peocolation_N2(N);
        int[] buffer = new int[2];
        buffer[0] = 0;
        int[] p = {0, 0};
        int[] q = {N - 1, 0};
        for (int i = 0; i < N; i++) {
            buffer[1] = i;
            System.out.println(array_test.connected(buffer, p));
        }


    }
}



