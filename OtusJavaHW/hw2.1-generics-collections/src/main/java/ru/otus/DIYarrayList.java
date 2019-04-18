package ru.otus;

import java.util.*;
import java.util.function.Consumer;

public class DIYarrayList<T> implements List<T> {

	private static final Object[] EMPTY_ELEMENTDATA = null;

	Object[] elementData;
	
	private int size;
	
	int modCount = 0;
	
	public DIYarrayList()
	{
		elementData = new Object[10];
	}
	
    public DIYarrayList(int initialCapacity) {
        if (initialCapacity > 0) {
            this.elementData = new Object[initialCapacity];
            this.size = initialCapacity;
        } else if (initialCapacity == 0) {
            this.elementData = EMPTY_ELEMENTDATA;
        } else {
            throw new IllegalArgumentException("Illegal Capacity: "+
                                               initialCapacity);
        }
    }
	
    private Object[] grow(int capacity) {
    	modCount++;
        return elementData = Arrays.copyOf(elementData, capacity);
    }

    private Object[] grow() {
        return grow(size + 1);
    }
	
	@Override
	public int size() {
		// TODO Auto-generated method stub
		//throw new UnsupportedOperationException();
		return size;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		//throw new UnsupportedOperationException();
		return size == 0;
	}

	@Override
	public boolean contains(Object o) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException();
	}

	@Override
	public Iterator<T> iterator() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException();
	}

	@Override
	public Object[] toArray() {
		// TODO Auto-generated method stub
		//throw new UnsupportedOperationException();
		return Arrays.copyOf(elementData, size);
	}

	@Override
	public <T> T[] toArray(T[] a) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean add(T e) {
		// TODO Auto-generated method stub
		//throw new UnsupportedOperationException();
		
		if(size == elementData.length) 
		{
			elementData = grow();
		}
		
		elementData[size] = e;
		modCount++;
		size++;
        return true;
	}

	@Override
	public boolean remove(Object o) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean addAll(Collection<? extends T> c) {
		// TODO Auto-generated method stub
		//throw new UnsupportedOperationException();
        Object[] a = c.toArray();
        int numNew = a.length;
        if (numNew == 0)
            return false;
        if (numNew > elementData.length - size)
            elementData = grow(size + numNew);
        System.arraycopy(a, 0, elementData, size, numNew);
        modCount++;
        size = size + numNew;
        return true;
	}

	@Override
	public boolean addAll(int index, Collection<? extends T> c) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException();
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException();
	}

	@Override
	@SuppressWarnings("unchecked")
	public T get(int index) {
		// TODO Auto-generated method stub
		//throw new UnsupportedOperationException();
		rangeCheck(index);
		return (T)elementData[index];
	}

	@Override
	@SuppressWarnings("unchecked")
	public T set(int index, T element) {
		// TODO Auto-generated method stub
		//throw new UnsupportedOperationException();
		rangeCheck(index);
        T oldValue = (T)elementData[index];
        elementData[index] = element;
        return oldValue;
	}

	@Override
	public void add(int index, T element) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException();
	}

	@Override
	public T remove(int index) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException();
	}

	@Override
	public int indexOf(Object o) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException();
	}

	@Override
	public int lastIndexOf(Object o) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException();
	}

	@Override
	public ListIterator<T> listIterator() {
		// TODO Auto-generated method stub
		//throw new UnsupportedOperationException();
		return new ListItr(0);
	}

	@Override
	public ListIterator<T> listIterator(int index) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException();
	}

	@Override
	public List<T> subList(int fromIndex, int toIndex) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException();
	}
	
    private void rangeCheck(int index) {
        if (index > size || index < 0)
            throw new IndexOutOfBoundsException("Index: "+index+", Size: "+size);
    }
	
    @Override
    @SuppressWarnings("unchecked")
    public void sort(Comparator<? super T> c) {
        Arrays.sort((T[]) elementData, 0, size, c);
    }
    
    @SuppressWarnings("unchecked")
    static <T> T elementAt(Object[] es, int index) {
        return (T) es[index];
    }
    
    private class Itr implements Iterator<T> {
        int cursor;       // index of next element to return
        int lastRet = -1; // index of last element returned; -1 if no such
        int expectedModCount = modCount;

        // prevent creating a synthetic constructor
        Itr() {}

        public boolean hasNext() {
            return cursor != size;
        }

        @SuppressWarnings("unchecked")
        public T next() {
            checkForComodification();
            int i = cursor;
            if (i >= size)
                throw new NoSuchElementException();
            Object[] elementData = DIYarrayList.this.elementData;
            if (i >= elementData.length)
                throw new ConcurrentModificationException();
            cursor = i + 1;
            return (T) elementData[lastRet = i];
        }

        public void remove() {
            if (lastRet < 0)
                throw new IllegalStateException();
            checkForComodification();

            try {
            	DIYarrayList.this.remove(lastRet);
                cursor = lastRet;
                lastRet = -1;
                expectedModCount = modCount;
            } catch (IndexOutOfBoundsException ex) {
                throw new ConcurrentModificationException();
            }
        }

        @Override
        public void forEachRemaining(Consumer<? super T> action) {
            Objects.requireNonNull(action);
            final int size = DIYarrayList.this.size;
            int i = cursor;
            if (i < size) {
                final Object[] es = elementData;
                if (i >= es.length)
                    throw new ConcurrentModificationException();
                for (; i < size && modCount == expectedModCount; i++)
                    action.accept(elementAt(es, i));
                // update once at end to reduce heap write traffic
                cursor = i;
                lastRet = i - 1;
                checkForComodification();
            }
        }

        final void checkForComodification() {
            if (modCount != expectedModCount)
                throw new ConcurrentModificationException();
        }
    }
 
   
    private class ListItr extends Itr implements ListIterator<T> {
        ListItr(int index) {
            super();
            cursor = index;
        }

        public boolean hasPrevious() {
            return cursor != 0;
        }

        public int nextIndex() {
            return cursor;
        }

        public int previousIndex() {
            return cursor - 1;
        }

        @SuppressWarnings("unchecked")
        public T previous() {
            checkForComodification();
            int i = cursor - 1;
            if (i < 0)
                throw new NoSuchElementException();
            Object[] elementData = DIYarrayList.this.elementData;
            if (i >= elementData.length)
                throw new ConcurrentModificationException();
            cursor = i;
            return (T) elementData[lastRet = i];
        }

        public void set(T e) {
            if (lastRet < 0)
                throw new IllegalStateException();
            checkForComodification();

            try {
            	DIYarrayList.this.set(lastRet, e);
            } catch (IndexOutOfBoundsException ex) {
                throw new ConcurrentModificationException();
            }
        }

        public void add(T e) {
            checkForComodification();

            try {
                int i = cursor;
                DIYarrayList.this.add(i, e);
                cursor = i + 1;
                lastRet = -1;
                expectedModCount = modCount;
            } catch (IndexOutOfBoundsException ex) {
                throw new ConcurrentModificationException();
            }
        }
    }
}
