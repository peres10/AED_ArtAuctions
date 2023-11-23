package tests;

import dataStructures.*;
import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.*;

public class BSTTest {

	@Test
	public void insertTest() {
		OrderedDictionary<Integer, Integer> bst = new BinarySearchTree<>();

		bst.insert(0,1);
		bst.insert(10,2);
		bst.insert(2,10);

		assertEquals(bst.size(),3);
	}

	@Test
	public void iteratorTest() {
		OrderedDictionary<Integer, Integer> bst = new BinarySearchTree<>();

		bst.insert(0,1);
		bst.insert(10,2);
		bst.insert(2,10);
		bst.insert(26,7);
		bst.insert(345,2);
		bst.insert(87,10);
		bst.insert(-4,1);
		bst.insert(1,2);
		bst.insert(534,10);

		assertEquals(bst.size(),9);

		Iterator<Entry<Integer,Integer>> it = bst.iterator();
		assertTrue(it.hasNext());
		assertTrue(it.next().getKey() == -4);
		assertTrue(it.next().getKey() == 0);
		assertTrue(it.next().getKey() == 1);
		assertTrue(it.next().getKey() == 2);
		assertTrue(it.next().getKey() == 10);
		assertTrue(it.next().getKey() == 26);
		assertTrue(it.next().getKey() == 87);
		assertTrue(it.next().getKey() == 345);
		assertTrue(it.next().getKey() == 534);
		assertFalse(it.hasNext());
	}

	@Test
	public void testInsertionAndSize() {
		OrderedDictionary<Integer, Integer> bst = new BinarySearchTree<>();

		assertTrue(bst.isEmpty());
		assertEquals(bst.size(), 0);

		bst.insert(10, 1);
		bst.insert(5, 2);
		bst.insert(15, 3);

		assertFalse(bst.isEmpty());
		assertEquals(bst.size(), 3);
	}

	@Test
	public void testSearch() {
		OrderedDictionary<Integer, Integer> bst = new BinarySearchTree<>();

		assertNull(bst.find(10));

		bst.insert(10, 1);
		bst.insert(5, 2);
		bst.insert(15, 3);

		assertEquals(bst.find(10).intValue(), 1);
		assertEquals(bst.find(5).intValue(), 2);
		assertEquals(bst.find(15).intValue(), 3);
		assertNull(bst.find(20));
	}

	@Test
	public void testDeletion() {
		OrderedDictionary<Integer, Integer> bst = new BinarySearchTree<>();

		assertTrue(bst.remove(10) == null);

		bst.insert(10, 1);
		bst.insert(5, 2);
		bst.insert(15, 3);

		assertTrue(bst.remove(10) == 1);
		assertNull(bst.find(10));
		assertEquals(bst.size(), 2);

		assertTrue(bst.remove(20) == null);
		assertEquals(bst.size(), 2);
	}

	@Test
	public void testIteration() {
		OrderedDictionary<Integer, Integer> bst = new BinarySearchTree<>();

		bst.insert(10, 1);
		bst.insert(5, 2);
		bst.insert(15, 3);

		Iterator<Entry<Integer, Integer>> it = bst.iterator();
		assertTrue(it.hasNext());
		assertEquals(it.next().getKey().intValue(), 5);
		assertTrue(it.hasNext());
		assertEquals(it.next().getKey().intValue(), 10);
		assertTrue(it.hasNext());
		assertEquals(it.next().getKey().intValue(), 15);
		assertFalse(it.hasNext());
	}

	@Test
	public void testFullIteration() {
		OrderedDictionary<Integer, Integer> bst = new BinarySearchTree<>();

		bst.insert(10, 1);
		bst.insert(5, 2);
		bst.insert(15, 3);

		Iterator<Entry<Integer, Integer>> it = bst.iterator();
		int[] expectedKeys = { 5, 10, 15 };
		for (int expectedKey : expectedKeys) {
			assertTrue(it.hasNext());
			assertEquals(it.next().getKey().intValue(), expectedKey);
		}
		assertFalse(it.hasNext());
	}

	@Test
	public void testInsertionAndIteration() {
		OrderedDictionary<Integer, Integer> bst = new BinarySearchTree<>();

		// Insert a large number of values
		int numValues = 1000;
		for (int i = 0; i < numValues; i++) {
			bst.insert(i, i * 2);
		}

		// Check the size of the tree
		assertEquals(bst.size(), numValues);

		// Iterate through all values and ensure they are in ascending order
		Iterator<Entry<Integer, Integer>> it = bst.iterator();
		for (int i = 0; i < numValues; i++) {
			assertTrue(it.hasNext());
			assertEquals(it.next().getKey().intValue(), i);
		}
		assertFalse(it.hasNext());
	}

	@Test
	public void AVLinsertTest() {
		OrderedDictionary<Integer, Integer> bst = new AVLTree<>();

		bst.insert(0,1);
		bst.insert(10,2);
		bst.insert(2,10);

		assertEquals(bst.size(),3);
	}

	@Test
	public void AVLiteratorTest() {
		OrderedDictionary<Integer, Integer> bst = new AVLTree<>();

		bst.insert(0,1);
		bst.insert(10,2);
		bst.insert(2,10);
		bst.insert(26,7);
		bst.insert(345,2);
		bst.insert(87,10);
		bst.insert(-4,1);
		bst.insert(1,2);
		bst.insert(534,10);

		assertEquals(bst.size(),9);

		Iterator<Entry<Integer,Integer>> it = bst.iterator();
		assertTrue(it.hasNext());
		assertTrue(it.next().getKey() == -4);
		assertTrue(it.next().getKey() == 0);
		assertTrue(it.next().getKey() == 1);
		assertTrue(it.next().getKey() == 2);
		assertTrue(it.next().getKey() == 10);
		assertTrue(it.next().getKey() == 26);
		assertTrue(it.next().getKey() == 87);
		assertTrue(it.next().getKey() == 345);
		assertTrue(it.next().getKey() == 534);
		assertFalse(it.hasNext());
	}

	@Test
	public void AVLtestInsertionAndSize() {
		OrderedDictionary<Integer, Integer> bst = new AVLTree<>();

		assertTrue(bst.isEmpty());
		assertEquals(bst.size(), 0);

		bst.insert(10, 1);
		bst.insert(5, 2);
		bst.insert(15, 3);

		assertFalse(bst.isEmpty());
		assertEquals(bst.size(), 3);
	}

	@Test
	public void AVLtestSearch() {
		OrderedDictionary<Integer, Integer> bst = new AVLTree<>();

		assertNull(bst.find(10));

		bst.insert(10, 1);
		bst.insert(5, 2);
		bst.insert(15, 3);

		assertEquals(bst.find(10).intValue(), 1);
		assertEquals(bst.find(5).intValue(), 2);
		assertEquals(bst.find(15).intValue(), 3);
		assertNull(bst.find(20));
	}

	@Test
	public void AVLtestDeletion() {
		OrderedDictionary<Integer, Integer> bst = new AVLTree<>();

		assertTrue(bst.remove(10) == null);

		bst.insert(10, 1);
		bst.insert(5, 2);
		bst.insert(15, 3);

		assertTrue(bst.remove(10) == 1);
		assertNull(bst.find(10));
		assertEquals(bst.size(), 2);

		assertTrue(bst.remove(20) == null);
		assertEquals(bst.size(), 2);
	}

	@Test
	public void AVLtestIteration() {
		OrderedDictionary<Integer, Integer> bst = new AVLTree<>();

		bst.insert(10, 1);
		bst.insert(5, 2);
		bst.insert(15, 3);

		Iterator<Entry<Integer, Integer>> it = bst.iterator();
		assertTrue(it.hasNext());
		assertEquals(it.next().getKey().intValue(), 5);
		assertTrue(it.hasNext());
		assertEquals(it.next().getKey().intValue(), 10);
		assertTrue(it.hasNext());
		assertEquals(it.next().getKey().intValue(), 15);
		assertFalse(it.hasNext());
	}

	@Test
	public void AVLtestFullIteration() {
		OrderedDictionary<Integer, Integer> bst = new AVLTree<>();

		bst.insert(10, 1);
		bst.insert(5, 2);
		bst.insert(15, 3);

		Iterator<Entry<Integer, Integer>> it = bst.iterator();
		int[] expectedKeys = { 5, 10, 15 };
		for (int expectedKey : expectedKeys) {
			assertTrue(it.hasNext());
			assertEquals(it.next().getKey().intValue(), expectedKey);
		}
		assertFalse(it.hasNext());
	}

	@Test
	public void AVLtestInsertionAndIteration() {
		OrderedDictionary<Integer, Integer> bst = new AVLTree<>();

		// Insert a large number of values
		int numValues = 1000;
		for (int i = 0; i < numValues; i++) {
			bst.insert(i, i * 2);
		}

		// Check the size of the tree
		assertEquals(bst.size(), numValues);

		// Iterate through all values and ensure they are in ascending order
		Iterator<Entry<Integer, Integer>> it = bst.iterator();
		for (int i = 0; i < numValues; i++) {
			assertTrue(it.hasNext());
			assertEquals(it.next().getKey().intValue(), i);
		}
		assertFalse(it.hasNext());
	}

	@Test
	public void reverseIterator(){
		BinarySearchTree<Integer, Integer> bst = new BinarySearchTree<>();

		/*bst.insert(1,1);
		bst.insert(2,1);
		bst.insert(3,1);
		bst.insert(6,1);
		bst.insert(7,1);
		bst.insert(9,1);
		bst.insert(123,1);

		//Iterator<Entry<Integer, Integer>> it = bst.reverseIterator();
		assertTrue(it.hasNext());
		assertEquals(it.next().getKey().intValue(), 123);
		assertTrue(it.hasNext());
		assertEquals(it.next().getKey().intValue(), 9);
		assertTrue(it.hasNext());
		assertEquals(it.next().getKey().intValue(), 7);
		assertTrue(it.hasNext());
		assertEquals(it.next().getKey().intValue(), 6);
		assertTrue(it.hasNext());
		assertEquals(it.next().getKey().intValue(), 3);
		assertTrue(it.hasNext());
		assertEquals(it.next().getKey().intValue(), 2);
		assertTrue(it.hasNext());
		assertEquals(it.next().getKey().intValue(), 1);
		assertFalse(it.hasNext());*/
	}
}
