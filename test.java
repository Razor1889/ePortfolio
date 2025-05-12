import java.util.Scanner;
public class test 
{
    
    public static void main (String args[])
    {
        Scanner input = new Scanner(System.in);
        input.close();
        String originalString = "hello";
        char[] charArray = originalString.toCharArray();
    
        int left = 0;
        int right = charArray.length - 1;
    
        while (left < right) 
        {
            // Swap characters
            char temp = charArray[left];
            charArray[left] = charArray[right];
            charArray[right] = temp;
    
            // Move pointers towards the center
            left++;
            right--;
        }
    }//main

    public static boolean prime (int number)
    {
        boolean isPrime = true;
        if (number <= 1) 
        {
            isPrime = false;
        } else {
            for (int i = 2; i <= Math.sqrt(number); i++) {
                if (number % i == 0) {
                    isPrime = false;
                    break;
                }
            }
        }
        return isPrime;
    }

}//class


