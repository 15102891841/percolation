// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        int N=100;
        int Numbers=200;
        double sums=0;
        double one_result=0;

        for(int i=0;i<Numbers;i++)
        {
            peocolation_N2 array =new peocolation_N2(N);
            one_result= array.random_event();
            sums=sums+one_result;
            System.out.printf("第%d次实验结果是%4.4f\n",i+1,one_result);
            array = null;

        }
       System.out.printf("平均值为%4.4f\n",sums/Numbers);
        }

}