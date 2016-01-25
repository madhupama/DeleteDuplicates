package com.unique; // enable as per folder structure

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
1.  Given the following class, write 3 methods that can be used to return an array that has no duplicates.
2.  You should define a method signature that you feel is appropriate to the problem.
3.  We would prefer to see three implementations (one that should take into consideration #4 below) and an explanation of what use-cases are suitable to each implementation 
4.  What if we need to retain the original order?
5.  What are the positives and negatives of your solution?
a.  Can you implement it another way so as to avoid the negatives?
6.  Your solution should be testable and �production ready.�
7.  Your solution should be posted to a public github repo that SEI can clone.
 */

/**
 * *********** * *********** * *********** * *********** * *********** * *********** * *********** * *********** * *********** * ***********
 * @author : Madhupama Palyam
 * 
 * @see  : Using APIs of jdk 1.8, commented equivalent code also provided if running in lower jdks.
 *         Includes maven pom to build & run the project, Also the junit testcases as part of build as per continuous integration standards.
 *         This class is also providing the main method so as to be run as java application, just for execution flexibility only (this is not standard practice). 
 *         
 * @Notes :  System outs & comment lines in code just for demo purposes and convey of messages by author. 
 *           every method lists the pros and cons in the comments section above the method signature.
 *           executeAllApproaches - this method runs the three implementaions in the psvm.
 
 ************* Approach Idea Summary ************ 
             * 1. LinkedHashSet approach 
             * 2. ArrayList approach
             * 3. Arrays approach
             * 4. Map key set approach

 * *********** * *********** * *********** * *********** * *********** * *********** * *********** * *********** * *********** * ***********             
 */

public class DeDup {
    
    public static int[] randomIntegers = {1,2,34,34,25,1,45,3,26,85,4,34,86,25,43,2,1,10000,11,16,19,1,18,4,9,3,
                                   20,17,8,15,6,2,5,10,14,12,13,7,8,9,1,2,15,12,18,10,14,20,17,16,3,6,19,
                                   13,5,11,4,7,19,16,5,9,12,3,20,7,15,17,10,6,1,8,18,4,14,13,2,11};   

    
    public static void main(String [] args) {
        DeDup deDup = new DeDup();
        deDup.executeAllApproaches(randomIntegers);
    }
    

    public void executeAllApproaches(int[] randomIntegers) {
        System.out.println("Print Original array :- " + Arrays.toString(randomIntegers));
        long methodStartTime = System.currentTimeMillis();
        
        System.out.println("\nusing LinkedHashSet :- " + Arrays.toString(usingLinkedHashSet(randomIntegers))  
                +" Time msec  :- "+ (System.currentTimeMillis() - methodStartTime));
        
        methodStartTime = System.currentTimeMillis(); 
        System.out.println("using ArrayList     :- " + Arrays.toString(usingArrayList(randomIntegers)) 
                +" Time msec  :- "+ (System.currentTimeMillis() - methodStartTime));
        
        methodStartTime = System.currentTimeMillis(); 
        System.out.println("using Arrays        :- " + Arrays.toString(usingArrays(randomIntegers)) 
                +" Time msec  :- "+ (System.currentTimeMillis() - methodStartTime));
        
        methodStartTime = System.currentTimeMillis(); 
        System.out.println("using HashMap       :- " + Arrays.toString(usingHashMap(randomIntegers)) 
                +" Time msec  :- "+ (System.currentTimeMillis() - methodStartTime));

    }
   
    
    /**
     * ***** LinkedHashSet approach maintains the insertion order ******
     * + Standard API leveraged
     * + unique & maintains the insertion order  
     * + Less lines of code
     * + sets are little faster compared to map implementations, Sorted implementation & uniqueness advantages. 
     * - not thread safe -  addition synchronization steps for concurrent env.
     * - Sets consumes more memory than Lists & significantly slower in iteration
     * 
     *  When a data structure needs to maintain the Uniques with insertion order then any of the LinkedXXX implementaions can be used like - LinkedHashSet, LinkedHashMap. 
     *  For the current uses case since its a single dimentional array LinkedHashSet is sufficient. 
     *  
     *  Practical use cases - Store data insertion orders as returned from db.
     *                      - Maps in user data Cache implementations. 
     *  
     */
    public Integer[] usingLinkedHashSet(int[] randomIntegers) {
     
        Set integerLnkHshSet = new LinkedHashSet();
        Collections.addAll(integerLnkHshSet, Arrays.stream(randomIntegers).boxed().toArray(Integer[]::new)); // jdk 1.8
        //jdk other
        /* 
        for(Integer i : randomIntegers){
             integerLnkHshSet.add(i);
        } //doing it old-fashioned way.
         */
        
        return (Integer[]) integerLnkHshSet.toArray(new Integer[0]);
    }
    
    /**
     * ***** ArrayList approach  ******
     * + Standard API leveraged - Lists maintains the insertion order
     * + Single loop to load & check unique.
     * + Dynamic size growth when compared to Array.
     * - not thread safe -  addition synchronization steps for concurrent env.
     
     *  Practical use cases - To keep Default Sort orders for any comparator implementations. 
     * 
     */
    public Integer[] usingArrayList(int[] randomIntegers) {

        List<Integer> numberList = new ArrayList<Integer>();
        for (int i : randomIntegers) {
           if (!numberList.contains(i)) {
              numberList.add(i);
            }
        }
        return numberList.toArray(new Integer[0]);
    }
    
    /**
     * ***** Arrays approach ****** 
     * - more code, more memory & unused memory - Array needs to initialize the size,
     *   this forces the declaration of a full size output array though data loaded after filtering unique can be lesser.
     * - Loop time more due to read & then compare for duplicates.
     * - Its a primitive way, larger collection sizes performance sufferers - Not advisable.            
     */
    
    public int[] usingArrays(int[] randomIntegers){
        int result[] = new int[randomIntegers.length], j=0;
        for (int i : randomIntegers) {
            if(!isExists(result, i))
                result[j++] = i;
        }
        return result;
    }
    private static boolean isExists(int[] array, int value){
        for (int i : array) {
            if(i==value)
                return true;
        }
        return false;
    }
    
    /** 
     * *********** Map approach ************
     * + unique & maintains the insertion order 
     * + Standard API Leveraged - hashing principle internally set implementation.
     * + <Key Value > has retrival advantages, log(n) traverse time for a key. 
     * - maps are little slower compared to sets implementaions.
     * - for this example map not used to full extent, only keys set extracted to obtain single dimension values.
     * - not thread safe -  addition synchronization steps to achieve it | use lock free ConcurrentSkipListSet. 
     * 
     * Practical use cases - store UserId Based information, uniques userId, fast fetch & traversing advantages. 
     */
    public Integer[] usingHashMap(int[] randomIntegers) {
        Map<Integer, Integer> integerHshMap = new LinkedHashMap<Integer, Integer>();
        for(Integer i : randomIntegers){
            integerHshMap.put(i, i);
        }
        return (Integer[]) integerHshMap.keySet().toArray(new Integer[0]);
    }
     
 }

