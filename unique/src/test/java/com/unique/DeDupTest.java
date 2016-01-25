/**
 *------------------------------------------------------------------------------------
 * @since 01/22/2016
 * @author  Madhupama Palyam
 * This Test class is used check the Arrays with no duplicates
 *------------------------------------------------------------------------------------
 */
package com.unique;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class DeDupTest {

    DeDup deDup = new DeDup(); //JMock obj | spring test bean autowiring is appropriate. But want to keep it simple for demo
    int[] randomIntegers = {1,2,34,34,25,1,45,3,26,85,4,34,86,25,43,2,1,10000,11,16,19,1,18,4,9,3,
            20,17,8,15,6,2,5,10,14,12,13,7,8,9,1,2,15,12,18,10,14,20,17,16,3,6,19,
            13,5,11,4,7,19,16,5,9,12,3,20,7,15,17,10,6,1,8,18,4,14,13,2,11};   
    
    @Before
    public void setUpBefore() {
        //build test method specific data.  
    }
    
    @Test  
    public void LinkedHashSetTest() {
        try {

            Integer[] outResult = deDup.usingLinkedHashSet(randomIntegers);
            Assert.assertNotNull(outResult);
            Assert.assertTrue(outResult.length < randomIntegers.length );
            Assert.assertTrue(outResult.length == 28);
            
        } catch (Exception e) {
            Assert.fail();
        }
    }
    
    @Test  
    public void usingArrayListTest() {
        try {

            Integer[] outResult = deDup.usingArrayList(randomIntegers);
            Assert.assertNotNull(outResult);
            Assert.assertTrue(outResult.length < randomIntegers.length );
            Assert.assertTrue(outResult.length == 28);
            
        } catch (Exception e) {
            Assert.fail();
        }
    }
    
    @Test  
    public void usingArrayTest() {
        try {

            int[] outResult = deDup.usingArrays(randomIntegers);
            Assert.assertNotNull(outResult);
            Assert.assertTrue(outResult.length == randomIntegers.length );
            Assert.assertTrue(outResult[28] == 0);
            
        } catch (Exception e) {
            Assert.fail();
        }
    }
    
    @Test  
    public void usingHashMapTest() {
        try {

            Integer[] outResult = deDup.usingHashMap(randomIntegers);
            Assert.assertNotNull(outResult);
            Assert.assertTrue(outResult.length < randomIntegers.length );
            Assert.assertTrue(outResult.length == 28);
            
        } catch (Exception e) {
            Assert.fail();
        }
    }

}
