/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package semesterproject;

public class terminal1 
{
    public static void main(String[] args)
    {
        String s = "tuseeq";
        
        String b = s.substring(1);
        
        s = s.toUpperCase();
        String a = s.substring(0,1);
        b  = a+b;
        System.out.println(b);
        
    }
    
}
