package dataStructures;

import java.io.Serializable;

/**
 * Doubly linked list Implementation 
 * @author AED  Team
 * @author Alexandre Peres 61615
 * @version 1.0
 * @param <E> Generic Element
 * 
 */
public class DoubleList<E> implements List<E>
{   

	/**
	 * Serial Version UID of the Class
	 */
    static final long serialVersionUID = 0L;

    /**
     *  Node at the head of the list.
     */
    protected DoubleListNode<E> head;

    /**
     * Node at the tail of the list.
     */
    protected DoubleListNode<E> tail;

    /**
     * Number of elements in the list.
     */
    protected int currentSize;

    /**
     * Constructor of an empty double linked list.
     * head and tail are initialized as null.
     * currentSize is initialized as 0.
     */
    public DoubleList( )
    {
        head = null;
        tail = null;
        currentSize = 0;
    }


    @Override
    public boolean isEmpty( )
    {  
        return currentSize == 0;
    }


    @Override
    public int size( )
    {
        return currentSize;
    }


    @Override
    public Iterator<E> iterator( )
    {
        return new DoubleListIterator<E>(head, tail);
    }


    @Override
    public E getFirst( ) throws EmptyListException
    {  
        if ( this.isEmpty() )
            throw new EmptyListException();

        return head.getElement();
    }


    @Override
    public E getLast( ) throws EmptyListException
    {
        if ( this.isEmpty() )
            throw new EmptyListException();

    	return tail.getElement();
    }


    /**
     * Returns the node at the specified position in the list.
     * Pre-condition: position ranges from 0 to currentSize-1.
     * @param position - position of list element to be returned
     * @return DoubleListNode<E> at position
     */
    protected DoubleListNode<E> getNode( int position ) 
    {
        DoubleListNode<E> node;

        if ( position <= ( currentSize - 1 ) / 2 )
        {
            node = head;
            for ( int i = 0; i < position; i++ )
                node = node.getNext();
        }
        else
        {
            node = tail;
            for ( int i = currentSize - 1; i > position; i-- )
                node = node.getPrevious();

        }
        return node;
    }


    @Override    
    public E get( int position ) throws InvalidPositionException
    {
        if ( position < 0 || position >= currentSize )
            throw new InvalidPositionException();

        if (position == 0)
            return getFirst();
        if (position == currentSize-1)
            return getLast();

        return getNode(position).getElement();
    }


    @Override
    public int find( E element )
    {
        DoubleListNode<E> node = head;
        int position = 0;
        while ( node != null && !node.getElement().equals(element) )
        {
            node = node.getNext();
            position++;
        }
        if ( node == null )
            return -1;
        else
            return position;
    }


    @Override
    public void addFirst( E element )
    {
        DoubleListNode<E> newNode = new DoubleListNode<E>(element, null, head);
        if ( this.isEmpty() )
            tail = newNode;
        else
            head.setPrevious(newNode);
        head = newNode;
        currentSize++;
    }


    @Override
    public void addLast( E element )
    {
        DoubleListNode<E> newNode = new DoubleListNode<E>(element, tail, null);
        if ( this.isEmpty() )
            head = newNode;
        else
            tail.setNext(newNode);
        tail = newNode;
        currentSize++;
    }


    /**
     * Inserts the specified element at the specified position in the list.
     * Pre-condition: position ranges from 1 to currentSize-1.
     * @param position - middle position for insertion of element
     * @param element - element to be inserted at middle position
     */
    protected void addMiddle( int position, E element )
    {
        DoubleListNode<E> prevNode = this.getNode(position - 1);
        DoubleListNode<E> nextNode = prevNode.getNext();
        DoubleListNode<E> newNode = new DoubleListNode<E>(element, prevNode, nextNode);

        currentSize++;
        prevNode.setNext(newNode);
        nextNode.setPrevious(newNode);
    }


    @Override
    public void add( int position, E element ) throws InvalidPositionException
    {
        if ( position < 0 || position > currentSize )
            throw new InvalidPositionException();

        if ( position == 0 )
            this.addFirst(element);
        else if ( position == currentSize )
            this.addLast(element);
        else
            this.addMiddle(position, element);
    }


    /**
     * Removes the first node in the list.
     * Pre-condition: the list is not empty.
     */
    protected void removeFirstNode( )
    {
        head = head.getNext();
        if ( head == null )
            tail = null;
        else
            head.setPrevious(null);
        currentSize--;
    }


    @Override
    public E removeFirst( ) throws EmptyListException
    {
        if( this.isEmpty() )
            throw new EmptyListException();

        E element = head.getElement();
        this.removeFirstNode();
    	return element;
    }


    /**
     * Removes the last node in the list.
     * Pre-condition: the list is not empty.
     */
    protected void removeLastNode( )
    {
        tail = tail.getPrevious();
        if ( tail == null )
            head = null;
        else
            tail.setNext(null);
        currentSize--;
    }


    @Override
    public E removeLast( ) throws EmptyListException
    {
        if ( this.isEmpty() )
            throw new EmptyListException();

        E element = tail.getElement();
        this.removeLastNode();
        return element;
    }


    /**
     * Removes the specified node from the list.
     * Pre-condition: the node is neither the head nor the tail of the list.
     * @param node - middle node to be removed
     */
    protected void removeMiddleNode( DoubleListNode<E> node )
    {
        DoubleListNode<E> prevNode = node.getPrevious();
        DoubleListNode<E> nextNode = node.getNext();

        prevNode.setNext(nextNode);
        nextNode.setPrevious(prevNode);
        currentSize--;

    }


    @Override
    public E remove( int position ) throws InvalidPositionException
    {
        if ( position < 0 || position >= currentSize )
            throw new InvalidPositionException();

        if ( position == 0 )
            return this.removeFirst();
        else if ( position == currentSize - 1 )
            return this.removeLast();
        else 
        {
            DoubleListNode<E> node = getNode(position);
            E element = node.getElement();
            this.removeMiddleNode(node);
        	return element;
        }
    }


    /**
     * Returns the node with the first occurrence of the specified element
     * in the list, if the list contains the element.
     * Otherwise, returns null.
     * @param element - element to be searched
     * @return DoubleListNode<E> where element was found, null if not found 
     */
    protected DoubleListNode<E> findNode( E element )
    {
    	return this.getNode(find(element));
    }


    @Override
    public boolean remove( E element )
    {
        DoubleListNode<E> node = this.findNode(element);
        if ( node == null )
            return false;
        else
        {
            if ( node == head )
                this.removeFirstNode();
            else if ( node == tail )
                this.removeLastNode();
            else
                this.removeMiddleNode(node);
            return true;
        }
    }


    /**
     * Removes all of the elements from the specified list and
     * inserts them at the end of the list (in proper sequence).
     * @param list - list to be appended to the end of this
     */
    public void append( DoubleList<E> list )
    {
        if(!list.isEmpty()){
            if(this.isEmpty()){
                head = list.head;
                tail = list.tail;
            } else {
                tail.setNext(list.head);
                list.head.setPrevious(tail);
                tail = list.tail;
            }

            currentSize += list.size();

            list.head = null;
            list.tail = null;
            list.currentSize = 0;
        }

    }


}   


