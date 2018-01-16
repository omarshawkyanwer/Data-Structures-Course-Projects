package eg.edu.alexu.csd.datastructure.linkedList.cs48;

import static org.junit.Assert.*;
import org.junit.Test;

import eg.edu.alexu.csd.datastructure.linkedList.ILinkedList;
import eg.edu.alexu.csd.datastructure.linkedList.cs48.SingleLinkedList.Node;
public class TestingLinkedList {
	SingleLinkedList obj1=new SingleLinkedList();
	@Test
	public void testAddingNodesNormally() {	
		obj1.add(50);
		obj1.add(50);
		obj1.add(50);
		assertEquals(50,obj1.get(0));
	}
    SingleLinkedList obj2=new SingleLinkedList();
	@Test
	public void testAddingNodesInMiddle() {	
		obj2.add(0,50);
		obj2.add(1,60);
		obj2.add(2,70);
		obj2.add(3,80);
		obj2.add(4,90);
		assertEquals(80,obj2.get(3));
	}
	SingleLinkedList obj3=new SingleLinkedList();
	@Test
	public void testException(){
		try{
			obj3.add(5);
			obj3.get(30);
			fail("no exception thrown");
		}
		catch (Exception e)
		{
			assertTrue(true);
		}
	}
	SingleLinkedList obj4=new SingleLinkedList();
	@Test
	public void isEqual(){
		obj4.add(10);
		obj4.set(0, 100);
		assertEquals(obj4.get(0),100);
	}
	SingleLinkedList obj5=new SingleLinkedList();
	ILinkedList obj6=new SingleLinkedList();
	@Test
	public void sublist(){
		obj5.add(50);
		obj5.add(60);
		obj5.add(70);
		obj5.add(80);
		obj6=obj5.sublist(0, 2);
		assertTrue(obj6.size()==3 && obj6.get(0)==(Object)50 && obj6.get(1)==(Object)60 && obj6.get(2)==(Object)70);
	}
	SingleLinkedList obj7=new SingleLinkedList();
	@Test
	public void isDeleted(){
		obj7.add(0);
		obj7.add(1);
		obj7.add(2);
		obj7.add(3);//will be removed
		obj7.add(4);
		obj7.remove(3);
		assertTrue(obj7.size()==4 && obj7.get(0)==(Object)0 && obj7.get(1)==(Object)1 && obj7.get(3)==(Object)4);
	}
	SingleLinkedList obj8=new SingleLinkedList();
	@Test
	public void isItcleared(){
		obj8.add(5);
		obj8.add(5);
		obj8.add(5);
		obj8.add(5);
		obj8.add(5);
		obj8.clear();
		assertTrue(obj8.size()==0);
	}
	
	//Double linked list Implementation
	Doublelinkedlist obj9=new Doublelinkedlist();
	@Test
	public void testAddingNodesNormally2() {	
		obj9.add(50);
		obj9.add(50);
		obj9.add(50);
		assertEquals(50,obj9.get(0));
	}
	Doublelinkedlist obj10=new Doublelinkedlist();
	@Test
	public void testAddingNodesInMiddle2() {	
		obj10.add(0,50);
		obj10.add(1,60);
		obj10.add(2,70);
		obj10.add(3,80);
		obj10.add(4,90);
		assertEquals(80,obj10.get(3));
	}
	Doublelinkedlist obj11=new Doublelinkedlist();
	@Test
	public void testException2(){
		try{
			obj11.add(5);
			obj11.get(30);
			fail("no exception thrown");
		}
		catch (Exception e)
		{
			assertTrue(true);
		}
	}
	Doublelinkedlist obj12=new Doublelinkedlist();
	@Test
	public void isEqual2(){
		obj12.add(10);
		obj12.set(0, 100);
		assertEquals(obj12.get(0),100);
	}
	SingleLinkedList obj13=new SingleLinkedList();
	ILinkedList obj14=new SingleLinkedList();
	@Test
	public void sublist2(){
		obj13.add(50);
		obj13.add(60);
		obj13.add(70);
		obj13.add(80);
		obj14=obj13.sublist(0, 2);
		assertTrue(obj14.size()==3 && obj14.get(0)==(Object)50 && obj14.get(1)==(Object)60 && obj14.get(2)==(Object)70);
	}
	Doublelinkedlist obj15=new Doublelinkedlist();
	@Test
	public void isDeleted2(){
		obj15.add(0);
		obj15.add(1);
		obj15.add(2);
		obj15.add(3);//will be removed
		obj15.add(4);
		obj15.remove(3);
		assertTrue(obj15.size()==4 && obj15.get(0)==(Object)0 && obj15.get(1)==(Object)1 && obj15.get(3)==(Object)4);
	}
	Doublelinkedlist obj16=new Doublelinkedlist();
	@Test
	public void isItcleared2(){
		obj16.add(5);
		obj16.add(5);
		obj16.add(5);
		obj16.add(5);
		obj16.add(5);
		obj16.clear();
		assertTrue(obj16.size()==0);
	}
	
}
