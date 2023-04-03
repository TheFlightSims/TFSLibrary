/*      */ package org.apache.commons.collections.list;
/*      */ 
/*      */ import java.io.IOException;
/*      */ import java.io.ObjectInputStream;
/*      */ import java.io.ObjectOutputStream;
/*      */ import java.lang.reflect.Array;
/*      */ import java.util.AbstractList;
/*      */ import java.util.Collection;
/*      */ import java.util.ConcurrentModificationException;
/*      */ import java.util.Iterator;
/*      */ import java.util.List;
/*      */ import java.util.ListIterator;
/*      */ import java.util.NoSuchElementException;
/*      */ import org.apache.commons.collections.OrderedIterator;
/*      */ 
/*      */ public abstract class AbstractLinkedList implements List {
/*      */   protected transient Node header;
/*      */   
/*      */   protected transient int size;
/*      */   
/*      */   protected transient int modCount;
/*      */   
/*      */   protected AbstractLinkedList() {}
/*      */   
/*      */   protected AbstractLinkedList(Collection coll) {
/*   89 */     init();
/*   90 */     addAll(coll);
/*      */   }
/*      */   
/*      */   protected void init() {
/*  100 */     this.header = createHeaderNode();
/*      */   }
/*      */   
/*      */   public int size() {
/*  105 */     return this.size;
/*      */   }
/*      */   
/*      */   public boolean isEmpty() {
/*  109 */     return (size() == 0);
/*      */   }
/*      */   
/*      */   public Object get(int index) {
/*  113 */     Node node = getNode(index, false);
/*  114 */     return node.getValue();
/*      */   }
/*      */   
/*      */   public Iterator iterator() {
/*  119 */     return listIterator();
/*      */   }
/*      */   
/*      */   public ListIterator listIterator() {
/*  123 */     return new LinkedListIterator(this, 0);
/*      */   }
/*      */   
/*      */   public ListIterator listIterator(int fromIndex) {
/*  127 */     return new LinkedListIterator(this, fromIndex);
/*      */   }
/*      */   
/*      */   public int indexOf(Object value) {
/*  132 */     int i = 0;
/*  133 */     for (Node node = this.header.next; node != this.header; node = node.next) {
/*  134 */       if (isEqualValue(node.getValue(), value))
/*  135 */         return i; 
/*  137 */       i++;
/*      */     } 
/*  139 */     return -1;
/*      */   }
/*      */   
/*      */   public int lastIndexOf(Object value) {
/*  143 */     int i = this.size - 1;
/*  144 */     for (Node node = this.header.previous; node != this.header; node = node.previous) {
/*  145 */       if (isEqualValue(node.getValue(), value))
/*  146 */         return i; 
/*  148 */       i--;
/*      */     } 
/*  150 */     return -1;
/*      */   }
/*      */   
/*      */   public boolean contains(Object value) {
/*  154 */     return (indexOf(value) != -1);
/*      */   }
/*      */   
/*      */   public boolean containsAll(Collection coll) {
/*  158 */     Iterator it = coll.iterator();
/*  159 */     while (it.hasNext()) {
/*  160 */       if (!contains(it.next()))
/*  161 */         return false; 
/*      */     } 
/*  164 */     return true;
/*      */   }
/*      */   
/*      */   public Object[] toArray() {
/*  169 */     return toArray(new Object[this.size]);
/*      */   }
/*      */   
/*      */   public Object[] toArray(Object[] array) {
/*  174 */     if (array.length < this.size) {
/*  175 */       Class componentType = array.getClass().getComponentType();
/*  176 */       array = (Object[])Array.newInstance(componentType, this.size);
/*      */     } 
/*  179 */     int i = 0;
/*  180 */     for (Node node = this.header.next; node != this.header; node = node.next, i++)
/*  181 */       array[i] = node.getValue(); 
/*  184 */     if (array.length > this.size)
/*  185 */       array[this.size] = null; 
/*  187 */     return array;
/*      */   }
/*      */   
/*      */   public List subList(int fromIndexInclusive, int toIndexExclusive) {
/*  198 */     return new LinkedSubList(this, fromIndexInclusive, toIndexExclusive);
/*      */   }
/*      */   
/*      */   public boolean add(Object value) {
/*  203 */     addLast(value);
/*  204 */     return true;
/*      */   }
/*      */   
/*      */   public void add(int index, Object value) {
/*  208 */     Node node = getNode(index, true);
/*  209 */     addNodeBefore(node, value);
/*      */   }
/*      */   
/*      */   public boolean addAll(Collection coll) {
/*  213 */     return addAll(this.size, coll);
/*      */   }
/*      */   
/*      */   public boolean addAll(int index, Collection coll) {
/*  217 */     Node node = getNode(index, true);
/*  218 */     for (Iterator itr = coll.iterator(); itr.hasNext(); ) {
/*  219 */       Object value = itr.next();
/*  220 */       addNodeBefore(node, value);
/*      */     } 
/*  222 */     return true;
/*      */   }
/*      */   
/*      */   public Object remove(int index) {
/*  227 */     Node node = getNode(index, false);
/*  228 */     Object oldValue = node.getValue();
/*  229 */     removeNode(node);
/*  230 */     return oldValue;
/*      */   }
/*      */   
/*      */   public boolean remove(Object value) {
/*  234 */     for (Node node = this.header.next; node != this.header; node = node.next) {
/*  235 */       if (isEqualValue(node.getValue(), value)) {
/*  236 */         removeNode(node);
/*  237 */         return true;
/*      */       } 
/*      */     } 
/*  240 */     return false;
/*      */   }
/*      */   
/*      */   public boolean removeAll(Collection coll) {
/*  244 */     boolean modified = false;
/*  245 */     Iterator it = iterator();
/*  246 */     while (it.hasNext()) {
/*  247 */       if (coll.contains(it.next())) {
/*  248 */         it.remove();
/*  249 */         modified = true;
/*      */       } 
/*      */     } 
/*  252 */     return modified;
/*      */   }
/*      */   
/*      */   public boolean retainAll(Collection coll) {
/*  257 */     boolean modified = false;
/*  258 */     Iterator it = iterator();
/*  259 */     while (it.hasNext()) {
/*  260 */       if (!coll.contains(it.next())) {
/*  261 */         it.remove();
/*  262 */         modified = true;
/*      */       } 
/*      */     } 
/*  265 */     return modified;
/*      */   }
/*      */   
/*      */   public Object set(int index, Object value) {
/*  269 */     Node node = getNode(index, false);
/*  270 */     Object oldValue = node.getValue();
/*  271 */     updateNode(node, value);
/*  272 */     return oldValue;
/*      */   }
/*      */   
/*      */   public void clear() {
/*  276 */     removeAllNodes();
/*      */   }
/*      */   
/*      */   public Object getFirst() {
/*  281 */     Node node = this.header.next;
/*  282 */     if (node == this.header)
/*  283 */       throw new NoSuchElementException(); 
/*  285 */     return node.getValue();
/*      */   }
/*      */   
/*      */   public Object getLast() {
/*  289 */     Node node = this.header.previous;
/*  290 */     if (node == this.header)
/*  291 */       throw new NoSuchElementException(); 
/*  293 */     return node.getValue();
/*      */   }
/*      */   
/*      */   public boolean addFirst(Object o) {
/*  297 */     addNodeAfter(this.header, o);
/*  298 */     return true;
/*      */   }
/*      */   
/*      */   public boolean addLast(Object o) {
/*  302 */     addNodeBefore(this.header, o);
/*  303 */     return true;
/*      */   }
/*      */   
/*      */   public Object removeFirst() {
/*  307 */     Node node = this.header.next;
/*  308 */     if (node == this.header)
/*  309 */       throw new NoSuchElementException(); 
/*  311 */     Object oldValue = node.getValue();
/*  312 */     removeNode(node);
/*  313 */     return oldValue;
/*      */   }
/*      */   
/*      */   public Object removeLast() {
/*  317 */     Node node = this.header.previous;
/*  318 */     if (node == this.header)
/*  319 */       throw new NoSuchElementException(); 
/*  321 */     Object oldValue = node.getValue();
/*  322 */     removeNode(node);
/*  323 */     return oldValue;
/*      */   }
/*      */   
/*      */   public boolean equals(Object obj) {
/*  328 */     if (obj == this)
/*  329 */       return true; 
/*  331 */     if (!(obj instanceof List))
/*  332 */       return false; 
/*  334 */     List other = (List)obj;
/*  335 */     if (other.size() != size())
/*  336 */       return false; 
/*  338 */     ListIterator it1 = listIterator();
/*  339 */     ListIterator it2 = other.listIterator();
/*  340 */     while (it1.hasNext() && it2.hasNext()) {
/*  341 */       Object o1 = it1.next();
/*  342 */       Object o2 = it2.next();
/*  343 */       if ((o1 == null) ? (o2 == null) : o1.equals(o2))
/*      */         continue; 
/*  344 */       return false;
/*      */     } 
/*  346 */     return (!it1.hasNext() && !it2.hasNext());
/*      */   }
/*      */   
/*      */   public int hashCode() {
/*  350 */     int hashCode = 1;
/*  351 */     Iterator it = iterator();
/*  352 */     while (it.hasNext()) {
/*  353 */       Object obj = it.next();
/*  354 */       hashCode = 31 * hashCode + ((obj == null) ? 0 : obj.hashCode());
/*      */     } 
/*  356 */     return hashCode;
/*      */   }
/*      */   
/*      */   public String toString() {
/*  360 */     if (size() == 0)
/*  361 */       return "[]"; 
/*  363 */     StringBuffer buf = new StringBuffer(16 * size());
/*  364 */     buf.append("[");
/*  366 */     Iterator it = iterator();
/*  367 */     boolean hasNext = it.hasNext();
/*  368 */     while (hasNext) {
/*  369 */       Object value = it.next();
/*  370 */       buf.append((value == this) ? "(this Collection)" : value);
/*  371 */       hasNext = it.hasNext();
/*  372 */       if (hasNext)
/*  373 */         buf.append(", "); 
/*      */     } 
/*  376 */     buf.append("]");
/*  377 */     return buf.toString();
/*      */   }
/*      */   
/*      */   protected boolean isEqualValue(Object value1, Object value2) {
/*  391 */     return (value1 == value2 || (value1 != null && value1.equals(value2)));
/*      */   }
/*      */   
/*      */   protected void updateNode(Node node, Object value) {
/*  403 */     node.setValue(value);
/*      */   }
/*      */   
/*      */   protected Node createHeaderNode() {
/*  414 */     return new Node();
/*      */   }
/*      */   
/*      */   protected Node createNode(Object value) {
/*  425 */     return new Node(value);
/*      */   }
/*      */   
/*      */   protected void addNodeBefore(Node node, Object value) {
/*  440 */     Node newNode = createNode(value);
/*  441 */     addNode(newNode, node);
/*      */   }
/*      */   
/*      */   protected void addNodeAfter(Node node, Object value) {
/*  456 */     Node newNode = createNode(value);
/*  457 */     addNode(newNode, node.next);
/*      */   }
/*      */   
/*      */   protected void addNode(Node nodeToInsert, Node insertBeforeNode) {
/*  468 */     nodeToInsert.next = insertBeforeNode;
/*  469 */     nodeToInsert.previous = insertBeforeNode.previous;
/*  470 */     insertBeforeNode.previous.next = nodeToInsert;
/*  471 */     insertBeforeNode.previous = nodeToInsert;
/*  472 */     this.size++;
/*  473 */     this.modCount++;
/*      */   }
/*      */   
/*      */   protected void removeNode(Node node) {
/*  483 */     node.previous.next = node.next;
/*  484 */     node.next.previous = node.previous;
/*  485 */     this.size--;
/*  486 */     this.modCount++;
/*      */   }
/*      */   
/*      */   protected void removeAllNodes() {
/*  493 */     this.header.next = this.header;
/*  494 */     this.header.previous = this.header;
/*  495 */     this.size = 0;
/*  496 */     this.modCount++;
/*      */   }
/*      */   
/*      */   protected Node getNode(int index, boolean endMarkerAllowed) throws IndexOutOfBoundsException {
/*      */     Node node;
/*  511 */     if (index < 0)
/*  512 */       throw new IndexOutOfBoundsException("Couldn't get the node: index (" + index + ") less than zero."); 
/*  515 */     if (!endMarkerAllowed && index == this.size)
/*  516 */       throw new IndexOutOfBoundsException("Couldn't get the node: index (" + index + ") is the size of the list."); 
/*  519 */     if (index > this.size)
/*  520 */       throw new IndexOutOfBoundsException("Couldn't get the node: index (" + index + ") greater than the size of the " + "list (" + this.size + ")."); 
/*  526 */     if (index < this.size / 2) {
/*  528 */       node = this.header.next;
/*  529 */       for (int currentIndex = 0; currentIndex < index; currentIndex++)
/*  530 */         node = node.next; 
/*      */     } else {
/*  534 */       node = this.header;
/*  535 */       for (int currentIndex = this.size; currentIndex > index; currentIndex--)
/*  536 */         node = node.previous; 
/*      */     } 
/*  539 */     return node;
/*      */   }
/*      */   
/*      */   protected Iterator createSubListIterator(LinkedSubList subList) {
/*  549 */     return createSubListListIterator(subList, 0);
/*      */   }
/*      */   
/*      */   protected ListIterator createSubListListIterator(LinkedSubList subList, int fromIndex) {
/*  559 */     return new LinkedSubListIterator(subList, fromIndex);
/*      */   }
/*      */   
/*      */   protected void doWriteObject(ObjectOutputStream outputStream) throws IOException {
/*  571 */     outputStream.writeInt(size());
/*  572 */     for (Iterator itr = iterator(); itr.hasNext();)
/*  573 */       outputStream.writeObject(itr.next()); 
/*      */   }
/*      */   
/*      */   protected void doReadObject(ObjectInputStream inputStream) throws IOException, ClassNotFoundException {
/*  584 */     init();
/*  585 */     int size = inputStream.readInt();
/*  586 */     for (int i = 0; i < size; i++)
/*  587 */       add(inputStream.readObject()); 
/*      */   }
/*      */   
/*      */   protected static class Node {
/*      */     protected Node previous;
/*      */     
/*      */     protected Node next;
/*      */     
/*      */     protected Object value;
/*      */     
/*      */     protected Node() {
/*  612 */       this.previous = this;
/*  613 */       this.next = this;
/*      */     }
/*      */     
/*      */     protected Node(Object value) {
/*  623 */       this.value = value;
/*      */     }
/*      */     
/*      */     protected Node(Node previous, Node next, Object value) {
/*  635 */       this.previous = previous;
/*  636 */       this.next = next;
/*  637 */       this.value = value;
/*      */     }
/*      */     
/*      */     protected Object getValue() {
/*  647 */       return this.value;
/*      */     }
/*      */     
/*      */     protected void setValue(Object value) {
/*  657 */       this.value = value;
/*      */     }
/*      */     
/*      */     protected Node getPreviousNode() {
/*  667 */       return this.previous;
/*      */     }
/*      */     
/*      */     protected void setPreviousNode(Node previous) {
/*  677 */       this.previous = previous;
/*      */     }
/*      */     
/*      */     protected Node getNextNode() {
/*  687 */       return this.next;
/*      */     }
/*      */     
/*      */     protected void setNextNode(Node next) {
/*  697 */       this.next = next;
/*      */     }
/*      */   }
/*      */   
/*      */   protected static class LinkedListIterator implements ListIterator, OrderedIterator {
/*      */     protected final AbstractLinkedList parent;
/*      */     
/*      */     protected AbstractLinkedList.Node next;
/*      */     
/*      */     protected int nextIndex;
/*      */     
/*      */     protected AbstractLinkedList.Node current;
/*      */     
/*      */     protected int expectedModCount;
/*      */     
/*      */     protected LinkedListIterator(AbstractLinkedList parent, int fromIndex) throws IndexOutOfBoundsException {
/*  747 */       this.parent = parent;
/*  748 */       this.expectedModCount = parent.modCount;
/*  749 */       this.next = parent.getNode(fromIndex, true);
/*  750 */       this.nextIndex = fromIndex;
/*      */     }
/*      */     
/*      */     protected void checkModCount() {
/*  761 */       if (this.parent.modCount != this.expectedModCount)
/*  762 */         throw new ConcurrentModificationException(); 
/*      */     }
/*      */     
/*      */     protected AbstractLinkedList.Node getLastNodeReturned() throws IllegalStateException {
/*  774 */       if (this.current == null)
/*  775 */         throw new IllegalStateException(); 
/*  777 */       return this.current;
/*      */     }
/*      */     
/*      */     public boolean hasNext() {
/*  781 */       return (this.next != this.parent.header);
/*      */     }
/*      */     
/*      */     public Object next() {
/*  785 */       checkModCount();
/*  786 */       if (!hasNext())
/*  787 */         throw new NoSuchElementException("No element at index " + this.nextIndex + "."); 
/*  789 */       Object value = this.next.getValue();
/*  790 */       this.current = this.next;
/*  791 */       this.next = this.next.next;
/*  792 */       this.nextIndex++;
/*  793 */       return value;
/*      */     }
/*      */     
/*      */     public boolean hasPrevious() {
/*  797 */       return (this.next.previous != this.parent.header);
/*      */     }
/*      */     
/*      */     public Object previous() {
/*  801 */       checkModCount();
/*  802 */       if (!hasPrevious())
/*  803 */         throw new NoSuchElementException("Already at start of list."); 
/*  805 */       this.next = this.next.previous;
/*  806 */       Object value = this.next.getValue();
/*  807 */       this.current = this.next;
/*  808 */       this.nextIndex--;
/*  809 */       return value;
/*      */     }
/*      */     
/*      */     public int nextIndex() {
/*  813 */       return this.nextIndex;
/*      */     }
/*      */     
/*      */     public int previousIndex() {
/*  818 */       return nextIndex() - 1;
/*      */     }
/*      */     
/*      */     public void remove() {
/*  822 */       checkModCount();
/*  823 */       if (this.current == this.next) {
/*  825 */         this.next = this.next.next;
/*  826 */         this.parent.removeNode(getLastNodeReturned());
/*      */       } else {
/*  829 */         this.parent.removeNode(getLastNodeReturned());
/*  830 */         this.nextIndex--;
/*      */       } 
/*  832 */       this.current = null;
/*  833 */       this.expectedModCount++;
/*      */     }
/*      */     
/*      */     public void set(Object obj) {
/*  837 */       checkModCount();
/*  838 */       getLastNodeReturned().setValue(obj);
/*      */     }
/*      */     
/*      */     public void add(Object obj) {
/*  842 */       checkModCount();
/*  843 */       this.parent.addNodeBefore(this.next, obj);
/*  844 */       this.current = null;
/*  845 */       this.nextIndex++;
/*  846 */       this.expectedModCount++;
/*      */     }
/*      */   }
/*      */   
/*      */   protected static class LinkedSubListIterator extends LinkedListIterator {
/*      */     protected final AbstractLinkedList.LinkedSubList sub;
/*      */     
/*      */     protected LinkedSubListIterator(AbstractLinkedList.LinkedSubList sub, int startIndex) {
/*  861 */       super(sub.parent, startIndex + sub.offset);
/*  862 */       this.sub = sub;
/*      */     }
/*      */     
/*      */     public boolean hasNext() {
/*  866 */       return (nextIndex() < this.sub.size);
/*      */     }
/*      */     
/*      */     public boolean hasPrevious() {
/*  870 */       return (previousIndex() >= 0);
/*      */     }
/*      */     
/*      */     public int nextIndex() {
/*  874 */       return super.nextIndex() - this.sub.offset;
/*      */     }
/*      */     
/*      */     public void add(Object obj) {
/*  878 */       super.add(obj);
/*  879 */       this.sub.expectedModCount = this.parent.modCount;
/*  880 */       this.sub.size++;
/*      */     }
/*      */     
/*      */     public void remove() {
/*  884 */       super.remove();
/*  885 */       this.sub.expectedModCount = this.parent.modCount;
/*  886 */       this.sub.size--;
/*      */     }
/*      */   }
/*      */   
/*      */   protected static class LinkedSubList extends AbstractList {
/*      */     AbstractLinkedList parent;
/*      */     
/*      */     int offset;
/*      */     
/*      */     int size;
/*      */     
/*      */     int expectedModCount;
/*      */     
/*      */     protected LinkedSubList(AbstractLinkedList parent, int fromIndex, int toIndex) {
/*  905 */       if (fromIndex < 0)
/*  906 */         throw new IndexOutOfBoundsException("fromIndex = " + fromIndex); 
/*  908 */       if (toIndex > parent.size())
/*  909 */         throw new IndexOutOfBoundsException("toIndex = " + toIndex); 
/*  911 */       if (fromIndex > toIndex)
/*  912 */         throw new IllegalArgumentException("fromIndex(" + fromIndex + ") > toIndex(" + toIndex + ")"); 
/*  914 */       this.parent = parent;
/*  915 */       this.offset = fromIndex;
/*  916 */       this.size = toIndex - fromIndex;
/*  917 */       this.expectedModCount = parent.modCount;
/*      */     }
/*      */     
/*      */     public int size() {
/*  921 */       checkModCount();
/*  922 */       return this.size;
/*      */     }
/*      */     
/*      */     public Object get(int index) {
/*  926 */       rangeCheck(index, this.size);
/*  927 */       checkModCount();
/*  928 */       return this.parent.get(index + this.offset);
/*      */     }
/*      */     
/*      */     public void add(int index, Object obj) {
/*  932 */       rangeCheck(index, this.size + 1);
/*  933 */       checkModCount();
/*  934 */       this.parent.add(index + this.offset, obj);
/*  935 */       this.expectedModCount = this.parent.modCount;
/*  936 */       this.size++;
/*  937 */       this.modCount++;
/*      */     }
/*      */     
/*      */     public Object remove(int index) {
/*  941 */       rangeCheck(index, this.size);
/*  942 */       checkModCount();
/*  943 */       Object result = this.parent.remove(index + this.offset);
/*  944 */       this.expectedModCount = this.parent.modCount;
/*  945 */       this.size--;
/*  946 */       this.modCount++;
/*  947 */       return result;
/*      */     }
/*      */     
/*      */     public boolean addAll(Collection coll) {
/*  951 */       return addAll(this.size, coll);
/*      */     }
/*      */     
/*      */     public boolean addAll(int index, Collection coll) {
/*  955 */       rangeCheck(index, this.size + 1);
/*  956 */       int cSize = coll.size();
/*  957 */       if (cSize == 0)
/*  958 */         return false; 
/*  961 */       checkModCount();
/*  962 */       this.parent.addAll(this.offset + index, coll);
/*  963 */       this.expectedModCount = this.parent.modCount;
/*  964 */       this.size += cSize;
/*  965 */       this.modCount++;
/*  966 */       return true;
/*      */     }
/*      */     
/*      */     public Object set(int index, Object obj) {
/*  970 */       rangeCheck(index, this.size);
/*  971 */       checkModCount();
/*  972 */       return this.parent.set(index + this.offset, obj);
/*      */     }
/*      */     
/*      */     public void clear() {
/*  976 */       checkModCount();
/*  977 */       Iterator it = iterator();
/*  978 */       while (it.hasNext()) {
/*  979 */         it.next();
/*  980 */         it.remove();
/*      */       } 
/*      */     }
/*      */     
/*      */     public Iterator iterator() {
/*  985 */       checkModCount();
/*  986 */       return this.parent.createSubListIterator(this);
/*      */     }
/*      */     
/*      */     public ListIterator listIterator(int index) {
/*  990 */       rangeCheck(index, this.size + 1);
/*  991 */       checkModCount();
/*  992 */       return this.parent.createSubListListIterator(this, index);
/*      */     }
/*      */     
/*      */     public List subList(int fromIndexInclusive, int toIndexExclusive) {
/*  996 */       return new LinkedSubList(this.parent, fromIndexInclusive + this.offset, toIndexExclusive + this.offset);
/*      */     }
/*      */     
/*      */     protected void rangeCheck(int index, int beyond) {
/* 1000 */       if (index < 0 || index >= beyond)
/* 1001 */         throw new IndexOutOfBoundsException("Index '" + index + "' out of bounds for size '" + this.size + "'"); 
/*      */     }
/*      */     
/*      */     protected void checkModCount() {
/* 1006 */       if (this.parent.modCount != this.expectedModCount)
/* 1007 */         throw new ConcurrentModificationException(); 
/*      */     }
/*      */   }
/*      */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\collections\list\AbstractLinkedList.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */