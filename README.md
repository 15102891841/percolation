If you are a Chinese student, please go here to read the Chinese version：https://www.jianshu.com/p/52b8664dd5f6?v=1681714575124

The job requires two classes, one class is percolation to build the percolation model. A class PercolationStats is used as a tool class to count calculation results such as mean, standard deviation, and confidence interval. Here is a brief description of the functions of each method.


![image](https://user-images.githubusercontent.com/42832145/232409089-7eb1bc7f-1184-477b-85d7-ac917aa5f0f2.png)

1) The constructor needs to construct an N*N site. Since the quick-union class has been written in the course, uf can be used directly. But why use two, I will talk about the isfull method later.

![image](https://user-images.githubusercontent.com/42832145/232409366-3dbad902-073e-43bf-8d04-e235bfb3a136.png)

2) open method
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


