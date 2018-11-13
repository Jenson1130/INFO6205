/*
 * Copyright (c) 2017. Phasmid Software
 */

package edu.neu.coe.info6205.symbolTable;

import edu.neu.coe.info6205.bqs.Queue;
import edu.neu.coe.info6205.bqs.Queue_Elements;
import edu.neu.coe.info6205.util.PrivateMethodTester;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

@SuppressWarnings("ALL")
public class BSTTest {

    @Test
    public void testSetRoot1() throws Exception {
        BSTSimple<String, Integer> bst = new BSTSimple<>();
        PrivateMethodTester tester = new PrivateMethodTester(bst);
        Class[] classes = {Comparable.class, Object.class};
        BSTSimple.Node node = (BSTSimple.Node) tester.invokePrivateExplicit("makeNode", classes, "X", 42);
        tester.invokePrivate("setRoot", node);
        System.out.println(bst);
    }

    @Test
    public void testSetRoot2() throws Exception {
        BSTSimple<String, Integer> bst = new BSTSimple<>();
        PrivateMethodTester tester = new PrivateMethodTester(bst);
        Class[] classes = {Comparable.class, Object.class};
        BSTSimple.Node nodeX = (BSTSimple.Node) tester.invokePrivateExplicit("makeNode", classes, "X", 42);
        BSTSimple.Node nodeY = (BSTSimple.Node) tester.invokePrivateExplicit("makeNode", classes, "Y", 52);
        BSTSimple.Node nodeZ = (BSTSimple.Node) tester.invokePrivateExplicit("makeNode", classes, "Z", 99);
        nodeY.smaller = nodeX;
        nodeY.larger = nodeZ;
        tester.invokePrivate("setRoot", nodeY);
        System.out.println(bst);
    }

    @Test
    public void testPut0() throws Exception {
        BSTSimple<String, Integer> bst = new BSTSimple<>();
        assertEquals(0, bst.size());
        bst.put("X", 42);
        assertEquals(1, bst.size());
    }

    @Test
    public void testPut1() throws Exception {
        BSTSimple<String, Integer> bst = new BSTSimple<>();
        PrivateMethodTester tester = new PrivateMethodTester(bst);
        Class[] classes = {Comparable.class, Object.class};
        BSTSimple.Node node = (BSTSimple.Node) tester.invokePrivateExplicit("makeNode", classes, "X", 42);
        tester.invokePrivate("setRoot", node);
        bst.put("Y", 99);
        BSTSimple.Node root = (BSTSimple.Node) tester.invokePrivate("getRoot");
        assertEquals("X",root.key);
        assertEquals("Y",root.larger.key);
        assertNull(root.smaller);
        assertEquals(2, bst.size());
    }

    @Test
    public void testPut2() throws Exception {
        BSTSimple<String, Integer> bst = new BSTSimple<>();
        PrivateMethodTester tester = new PrivateMethodTester(bst);
        Class[] classes = {Comparable.class, Object.class};
        BSTSimple.Node node = (BSTSimple.Node) tester.invokePrivateExplicit("makeNode", classes, "Y", 42);
        tester.invokePrivate("setRoot", node);
        bst.put("X", 99);
        bst.put("Z", 37);
        BSTSimple.Node root = (BSTSimple.Node) tester.invokePrivate("getRoot");
        assertEquals("Y",root.key);
        assertEquals("X",root.smaller.key);
        assertEquals("Z",root.larger.key);
        assertEquals(3, bst.size());
    }

    @Test
    public void testPut3() throws Exception {
        BSTdetail<String, Integer> bst = new BSTSimple<>();
        PrivateMethodTester tester = new PrivateMethodTester(bst);
        bst.put("Y", 42);
        BSTSimple.Node root = (BSTSimple.Node) tester.invokePrivate("getRoot");
        assertEquals("Y", root.key);
        assertNull(root.smaller);
        assertNull(root.larger);
        bst.put("X", 99);
        assertEquals("X", root.smaller.key);
        bst.put("Z", 37);
        assertEquals("Z", root.larger.key);
        System.out.println(bst.toString());
        assertEquals(3, bst.size());
    }

    @Test
    public void testPutN() throws Exception {
        BSTdetail<String, Integer> bst = new BSTSimple<>();
        bst.put("Hello", 3);
        bst.put("Goodbye", 5);
        bst.put("Ciao", 8);
        System.out.println(bst);
        assertEquals(3, bst.size());
    }

    @Test
    public void testPutAll() throws Exception {
        final Map<String, Integer> map = new HashMap<>();
        map.put("Hello", 3);
        map.put("Goodbye", 5);
        map.put("Ciao", 6);
        BSTdetail<String, Integer> bst = new BSTSimple<>();
        bst.putAll(map);
        System.out.println(bst);
        assertEquals(map.size(), bst.size());
    }

    @Test
    public void testTraverse() throws Exception {
        BSTSimple<String, Integer> bst = new BSTSimple<>();
        PrivateMethodTester tester = new PrivateMethodTester(bst);
        Class[] classes = {Comparable.class, Object.class};
        BSTSimple.Node node = (BSTSimple.Node) tester.invokePrivateExplicit("makeNode", classes, "Y", 42);
        tester.invokePrivate("setRoot", node);
        bst.put("X", 99);
        bst.put("Z", 37);
        Queue<String> queue = new Queue_Elements<String>();
        bst.inOrderTraverse((w, x) -> { queue.enqueue(w); return null; });
        assertEquals("X",queue.dequeue());
        assertEquals("Y",queue.dequeue());
        assertEquals("Z",queue.dequeue());
        assertTrue(queue.isEmpty());
    }

    @Test
    public void testDelete1() throws Exception {
        BSTSimple<String, Integer> bst = new BSTSimple<>();
        PrivateMethodTester tester = new PrivateMethodTester(bst);
        Class[] classes = {Comparable.class, Object.class};
        BSTSimple.Node node = (BSTSimple.Node) tester.invokePrivateExplicit("makeNode", classes, "X", 42);
        tester.invokePrivate("setRoot", node);
        bst.delete("X");
        assertNull(bst.root);
        assertEquals(0, bst.size());
    }

    @Test
    public void testDelete2() throws Exception {
        BSTSimple<String, Integer> bst = new BSTSimple<>();
        PrivateMethodTester tester = new PrivateMethodTester(bst);
        Class[] classes = {Comparable.class, Object.class};
        BSTSimple.Node node = (BSTSimple.Node) tester.invokePrivateExplicit("makeNode", classes, "X", 42);
        tester.invokePrivate("setRoot", node);
        bst.put("Y",57);
        bst.delete("Y");
        assertNull(bst.root.smaller);
        assertNull(bst.root.larger);
        assertEquals(1, bst.size());
    }

    @Test
    public void testDelete3() throws Exception {
        BSTSimple<String, Integer> bst = new BSTSimple<>();
        PrivateMethodTester tester = new PrivateMethodTester(bst);
        Class[] classes = {Comparable.class, Object.class};
        BSTSimple.Node node = (BSTSimple.Node) tester.invokePrivateExplicit("makeNode", classes, "X", 42);
        tester.invokePrivate("setRoot", node);
        bst.put("W",57);
        bst.delete("W");
        assertNull(bst.root.smaller);
        assertNull(bst.root.larger);
        assertEquals(1, bst.size());
    }

    @Test
    public void testDelete4() throws Exception {
        BSTSimple<String, Integer> bst = new BSTSimple<>();
        PrivateMethodTester tester = new PrivateMethodTester(bst);
        Class[] classes = {Comparable.class, Object.class};
        BSTSimple.Node node = (BSTSimple.Node) tester.invokePrivateExplicit("makeNode", classes, "X", 42);
        tester.invokePrivate("setRoot", node);
        bst.put("W",57);
        bst.delete("A");
        assertEquals(2, bst.size());
    }

    @Test
    public void testTotalDepth() throws Exception{
        BSTdetail<String, Integer> bstSim = new BSTSimple<>();
        bstSim.put("B", 5);
        bstSim.put("A", 3);
        bstSim.put("E", 1);
        bstSim.put("C", 2);
        bstSim.put("D", 8);
        System.out.println(bstSim.size());
        System.out.println(((BSTSimple<String, Integer>) bstSim).root);
        ((BSTSimple<String, Integer>) bstSim).totalDepth(((BSTSimple<String, Integer>) bstSim).root);
        System.out.println(((BSTSimple<String, Integer>) bstSim).total-bstSim.size());
        System.out.println((double)(((BSTSimple<String, Integer>) bstSim).total-bstSim.size())/bstSim.size());

    }

    @Test
    public void testAverageDepth() throws Exception{
        //build the average depth test
        BSTdetail<String, Integer> bstSim = new BSTSimple<>();
        bstSim.put("B", 5);
        bstSim.put("A", 3);
        bstSim.put("E", 1);
        bstSim.put("C", 2);
        bstSim.put("D", 8);
        double t = ((BSTSimple<String, Integer>) bstSim).averageDepth(((BSTSimple<String, Integer>) bstSim).root);
        System.out.println(t);
    }

    @Test
    public void TestInsertionAndDelete() throws Exception{
        //build the bst tree

        int element = 100;


        for(int j =0; j<100; j++){
            BSTSimple<String, Integer> bstSim = new BSTSimple<>();
            Random randomKey = new Random();
            //create random map
            Random random =  new Random();
            final Map<String,Integer> nmapSimple = new HashMap<>();
            int n = 0;
            while (n<element){
                nmapSimple.put(String.valueOf(random.nextInt(element)),random.nextInt(200));
                n++;
            }

            bstSim.putAll(nmapSimple);
            //use the putall function to create bst
            for (int i = 0;i<100000;i++){
                //random boolean
                Random randomBoolean = new Random();
                if (randomBoolean.nextInt(2)==0){
                    //do the insertion function
                    bstSim.put(String.valueOf(randomKey.nextInt(element)),randomKey.nextInt(200));
                }else{
                    //do the deletion function
                    bstSim.delete(String.valueOf(randomKey.nextInt(element)));
                }

            }
            double t = ((BSTSimple<String, Integer>) bstSim).averageDepth(((BSTSimple<String, Integer>) bstSim).root);
            System.out.println("element:   " + element +"  MaxDepth:    " + bstSim.maximumDepth(bstSim.root) + "  AverDepth:    "+t+bstSim.size());

            element += 10;
        }

    }




}