package tests;

import static org.junit.Assert.*;
import artauctions.*;
import dataStructures.AVLTree;
import dataStructures.Entry;
import dataStructures.Iterator;
import dataStructures.OrderedDictionary;
import org.junit.Test;

public class ProjectTests {

    @Test
    public void testOrderWorks(){
        OrderedDictionary<Work,Work> works = new AVLTree<>();

        Artist artist1 = new ArtistClass("artist1","artist1",21,"tt@gm.cim","The Artist");
        WorkPrivate work1 = new WorkClass("ABC",artist1,2001,"Aaaaa");
        work1.sellArtWork(15);
        assertEquals(15, work1.getHighestSaleValue());

        works.insert(work1,work1);
        assertEquals(1, works.size());

        Iterator<Entry<Work, Work>> it = works.iterator();
        assertTrue(it.hasNext());
        assertEquals(it.next().getValue(),work1);
    }

    @Test
    public void testOrderWorks2(){
        OrderedDictionary<Work,Work> works = new AVLTree<>();

        Artist artist1 = new ArtistClass("artist1","artist1",21,"tt@gm.cim","The Artist");
        WorkPrivate work1 = new WorkClass("ABC",artist1,2001,"Aaaaa");
        work1.sellArtWork(15);
        WorkPrivate work2 = new WorkClass("ABC",artist1,2001,"Aba");
        work2.sellArtWork(15);
        WorkPrivate work3 = new WorkClass("ABC",artist1,2001,"work3");
        work3.sellArtWork(100);
        WorkPrivate work4 = new WorkClass("ABC",artist1,2001,"work4");
        work4.sellArtWork(7);
        WorkPrivate work5 = new WorkClass("ABC",artist1,2001,"work5");
        work5.sellArtWork(2);
        WorkPrivate work6 = new WorkClass("ABC",artist1,2001,"wokr6");
        work6.sellArtWork(200);

        works.insert(work1,work1);
        works.insert(work2,work2);
        works.insert(work3,work3);
        works.insert(work4,work4);
        works.insert(work5,work5);
        works.insert(work6,work6);


        Iterator<Entry<Work, Work>> it = works.iterator();
        assertTrue(it.hasNext());
        assertEquals(it.next().getValue(),work6);
        assertTrue(it.hasNext());
        assertEquals(it.next().getValue(),work3);
        assertTrue(it.hasNext());
        assertEquals(it.next().getValue(),work1);
        assertTrue(it.hasNext());
        assertEquals(it.next().getValue(),work2);
        assertTrue(it.hasNext());
        assertEquals(it.next().getValue(),work4);
        assertTrue(it.hasNext());
        assertEquals(it.next().getValue(),work5);
        assertFalse(it.hasNext());

        works.remove(work5);
        work5.sellArtWork(500);
        works.insert(work5,work5);

        Iterator<Entry<Work, Work>> it2 = works.iterator();
        assertEquals(it2.next().getValue(),work5);
    }
}
