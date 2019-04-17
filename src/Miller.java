import java.util.Scanner;
import java.util.Random;
import java.math.BigInteger;
 
/* MillerRabin */
public class Miller {
    private static Scanner scan;
	
    public boolean isPrime(long n, int interacao)
    {
        
        if (n == 0 || n == 1)
            return false;
        
        if (n == 2)
            return true;
        
        if (n % 2 == 0)
            return false;
 
        long s = n - 1;
        while (s % 2 == 0)
            s /= 2;
 
        Random rand = new Random();
        for (int i = 0; i < interacao; i++)
        {
            long r = Math.abs(rand.nextLong());            
            long a = r % (n - 1) + 1, temp = s;
            long mod = modPow(a, temp, n);
            while (temp != n - 1 && mod != 1 && mod != n - 1)
            {
                mod = mulMod(mod, mod, n);
                temp *= 2;
            }
            
            if (mod != n - 1 && temp % 2 == 0)
                return false;
        }
        return true;        
    }
   
    public long modPow(long a, long b, long c)
    {
        long res = 1;
        for (int i = 0; i < b; i++)
        {
            res *= a;
            res %= c; 
        }
        return res % c;
    }
    
    public long mulMod(long a, long b, long mod) 
    {
        return BigInteger.valueOf(a).multiply(BigInteger.valueOf(b)).mod(BigInteger.valueOf(mod)).longValue();
    }
   
    public static void main (String[] args) 
    {
    	scan = new Scanner(System.in);
        
        Miller mr = new Miller();
        
        System.out.println("Entre com o numero!");
        long num = scan.nextLong();
        
        System.out.println("\n Numero de intercoes:");
        int k = scan.nextInt();
        
        boolean primo = mr.isPrime(num, k);
        if (primo)
            System.out.println("\n"+ num +" � primo");
        else
            System.out.println("\n"+ num +" � composto");
 
    }
}
