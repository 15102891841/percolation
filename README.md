If you are a Chinese student, please go here to read the Chinese version：https://www.jianshu.com/p/52b8664dd5f6?v=1681714575124

The job requires two classes, one class is percolation to build the percolation model. A class PercolationStats is used as a tool class to count calculation results such as mean, standard deviation, and confidence interval. Here is a brief description of the functions of each method.


![image](https://user-images.githubusercontent.com/42832145/232409089-7eb1bc7f-1184-477b-85d7-ac917aa5f0f2.png)

###1) The constructor needs to construct an N*N site. Since the quick-union class has been written in the course, uf can be used directly. But why use two, I will talk about the isfull method later.

![image](https://user-images.githubusercontent.com/42832145/232409366-3dbad902-073e-43bf-8d04-e235bfb3a136.png)

###2) open method
The open method refers to the method of randomly opening a non-repeated site 1 after initializing the N*N site 1
The figure below uses a 3X3 site as an example, because the required method requires input parameters (row, col), that is, row and column. So here is a problem of coordinate conversion, because the coordinates of the point in the upper left corner are required to be (1,1), and the array of uf starts counting from 0, so pay attention here. For example (2, 2) represents the site 4

![image](https://user-images.githubusercontent.com/42832145/232409565-ad9b609b-15d0-47e5-a279-e602f754d85a.png)

So here are two private methods written, row, col to one-dimensional coordinates. and one-dimensional coordinates to row, col

```
    private int maps2d_to_1d(int x,int y)   //横纵坐标转一维
    {
        int result=N*(x-1)+(y-1);
        return result;
    }
    private int[] maps1d_to_2d(int result)   //一维数组的index转横纵坐标
    {
        int[] a=new int[2];
        a[0]=(result/(N))+1;
        a[1]=(result%(N))+1;
        return  a;
    }
```
A one-dimensional array int status[] is designed here to describe the opening and closing status of the site. 0 means off, 1 means on. It should be noted that the random number may be repeated, so it is necessary to check whether the site is enabled, and if it is enabled, just return. If you have not opened it before, enter the following operation, set the corresponding status to 1, and add one to numberOfOpenSites.
The following 4 ifs are to judge whether the four positions of the site are also open, and if they are also open, they will be connected. It can be seen that the communication status of the two ufs is completely synchronized.

```
public void open(int row, int col)
    {
        validate(row,col);
        int index=maps2d_to_1d(row, col);
        if(isOpen(row,col)){return;}
        status[index]=1;
        numberOfOpenSites++;
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
```

###3） isopen
Verify that the site is already open. It's very simple, just check whether the status[i] corresponding to the site is 1
```
public boolean isOpen(int row, int col)
    {
        validate(row,col);
        int index=maps2d_to_1d(row, col);
        if(status[index]==1){return  true;}
        else {return  false;}
    }
```
###3) isfull method
The full state is a state defined by the job, which refers to whether it can penetrate from the top layer to this point. In the course, we learned that two points p and q that cannot exist are virtualized on the uppermost layer and the lowermost layer respectively. p connects every site on the top layer; q connects every site on the bottom layer. In the end, you only need to ping p and q to see if the whole system is infiltrated.
So naturally we have to verify whether the change point is full. We only need to ping this point and p to see if it is connected. If it is connected, it is naturally full.
But this creates the problem of back wash, as shown on the left in the figure below, because the pq is connected, and the lower layers are all connected during initialization, so the part circled in red is also connected.
But according to the requirements, these places are no longer full.
Most of the implementation ideas are to construct two uf. A uf with a pq design. The second uf2 is, as long as p is connected to the upper layer, the bottom layer should not be connected. We use uf when verifying the connectivity of the entire system, and use uf2 when verifying whether a certain point is full. This idea is also adopted by the author here.
The author has always had a misunderstanding when I was studying, thinking that backflow will affect the entire system to judge whether it is infiltrated. In fact, backflow will only affect whether a certain point is full or not.
So that's why two uf's are needed.
```
public boolean isFull(int row, int col)
    {
        validate(row,col);
        int index=maps2d_to_1d(row, col);
        if(uf1.find(index)==uf1.find(vituralsite0)&&status[index]==1 ){return true;}
        return  false;
    }
```
###4）percolates
It is very simple, that is, to ping the pq two points of uf. Here, the author uses vituralsite0 to represent the upper virtual point, and vituralsite1 to represent the lowermost virtual point.
```public boolean percolates()
    {
        if(N==1 && status[0]==1){return true;}
        if(N==1 && status[0]!=1){return false;}
        if(uf.find(vituralsite0)==uf.find(vituralsite1)){return true;}
        return  false;
    }
```
