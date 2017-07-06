import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
//import java.math.*;
class brute
{
public static void main(String[] args)
{
Scanner sc = new Scanner(System.in);		
System.out.println("Enter the size of the matrix");
int n = sc.nextInt();
int i=0;
int j=0;
int min=1;
int max=50;
float [][]a = new float[n][n];
float [][]b = new float[n][n];
float [][]c = new float[n][n];
for(i=0;i<n;i++)
{
	for(j=0;j<n;j++)
	{
		a[i][j] = (float)(min+(int)(Math.random()*max));
		b[i][j] = (float)(min+(int)(Math.random()*max));
	}
}
long start = System.nanoTime();
c = multiplyRecFinal(a,b,n);
long end = System.nanoTime();
for(i=0;i<n;i++)
{
	for(j=0;j<n;j++)
	{
		System.out.print(c[i][j]+ " ");
	}
        System.out.println("");
}
 //System.out.println("The time taken is "+((end-start)));
System.out.println((end-start)/1000000 + " MilliSeconds");
}
public static void split(float[][]P,float[][]C,int iB,int jB)
	{
		int i2 = iB;
		for(int i1=0;i1< C.length;i1++)
		{
			int j2 = jB;
			for(int j1=0; j1<C.length;j1++)
			{
				C[i1][j1] = P[i2][j2];
				j2++;
			}
			i2++;
		}
	}

public static float[][] add(float[][] a,float[][] b)
	{
		int n = a.length;
		float[][] c = new float[n][n];
		for(int i = 0;i<n;i++)
		{
			for(int j=0;j<n;j++)
				c[i][j] = a[i][j] + b[i][j];
		}
	return c;
	}

public static float[][] sub(float[][] a,float[][] b)
	{
		int n = a.length;
		float[][] c = new float[n][n];
		for(int i = 0;i<n;i++)
		{
			for(int j=0;j<n;j++)
				c[i][j] = a[i][j] - b[i][j];
		}
	return c;
	}
public static void join(float[][]P,float[][]C,int iB,int jB)
	{
		int i2 = iB;
		for(int i1=0;i1< P.length;i1++)
		{
			int j2 = jB;
			for(int j1=0; j1< P.length;j1++)
			{
				C[i2][j2] = P[i1][j1];
				j2++;
			}
			i2++;
		}
	}
public static void sumMatrix(float[][] C, float[][]A, float[][]B,int rowC, int colC)
	{
    		int n=A.length;
    		for(int i =0; i<n; i++)
		{
        		for(int j=0; j<n; j++)  
            			C[i+rowC][j+colC]=A[i][j]+B[i][j];
    		}

	}



	public static float[][] multiplyRecFinal(float[][] A, float[][] B, int n){

    return  multiplyRec(A, B, 0, 0, 0, 0, n);

}


public static float[][] multiplyRec(float[][] A, float[][] B, int rowA, int colA, int rowB, int colB, int size)
	{

    		float[][] C= new float[size][size];

    	if(size==1)
        	C[0][0]= A[rowA][colA]*B[rowB][colB];

    	else
	{
            int newSize= size/2;
                Callable<Void> callable1 = new Callable<Void>()
   {
      @Override
      public Void call() throws Exception
      {
         sumMatrix(C,multiplyRec(A, B, rowA, colA, rowB, colB, newSize),multiplyRec(A, B, rowA, colA+newSize, rowB+ newSize, colB, newSize),0, 0);
         return null;
      }
   };

   Callable<Void> callable2 = new Callable<Void>()
   {
      @Override
      public Void call() throws Exception
      {
         sumMatrix(C,multiplyRec(A, B, rowA, colA, rowB, colB + newSize, newSize),multiplyRec(A, B, rowA, colA+newSize, rowB+ newSize, colB+newSize, newSize),0, newSize);
         return null;
      }
   };

   Callable<Void> callable3 = new Callable<Void>()
   {
      @Override
      public Void call() throws Exception
      {
         sumMatrix(C,multiplyRec(A, B, rowA+ newSize, colA, rowB, colB, newSize), multiplyRec(A, B, rowA+ newSize, colA+newSize, rowB+ newSize, colB, newSize),newSize, 0);
         return null;
      }
   };
   
   Callable<Void> callable4 = new Callable<Void>()
   {
      @Override
      public Void call() throws Exception
      {
         sumMatrix(C,multiplyRec(A, B, rowA+ newSize, colA, rowB, colB+newSize, newSize),multiplyRec(A, B, rowA+ newSize, colA+newSize, rowB+ newSize, colB+newSize, newSize),newSize, newSize);
         return null;
      }
   };

   //add to a list
   List<Callable<Void>> taskList = new ArrayList<Callable<Void>>();
   taskList.add(callable1);
   taskList.add(callable2);
   taskList.add(callable3);
   taskList.add(callable4);

   //create a pool executor with 3 threads
   ExecutorService executor = Executors.newFixedThreadPool(4);

   try
   {
      //start the threads
      List<Future<Void>> futureList = executor.invokeAll(taskList);

      for(Future<Void> voidFuture : futureList)
      {
         try
         {
            //check the status of each future.  get will block until the task
            //completes or the time expires
            voidFuture.get(100, TimeUnit.MILLISECONDS);
         }
         catch(Exception e)
         {
             System.out.println(e);
         }
         

      }


   }
   catch (InterruptedException ie)
   {
      //do something if you care about interruption;
   }

}


        	

    return C;

	}
}
