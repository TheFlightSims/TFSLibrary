/*      */ package org.apache.commons.collections;
/*      */ 
/*      */ import java.io.IOException;
/*      */ import java.io.ObjectInputStream;
/*      */ import java.io.ObjectOutputStream;
/*      */ import java.io.Serializable;
/*      */ import java.lang.ref.WeakReference;
/*      */ import java.lang.reflect.Array;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Collection;
/*      */ import java.util.ConcurrentModificationException;
/*      */ import java.util.Iterator;
/*      */ import java.util.List;
/*      */ import java.util.ListIterator;
/*      */ import java.util.NoSuchElementException;
/*      */ 
/*      */ public class CursorableLinkedList implements List, Serializable {
/*      */   private static final long serialVersionUID = 8836393098519411393L;
/*      */   
/*      */   public boolean add(Object o) {
/*   68 */     insertListable(this._head.prev(), null, o);
/*   69 */     return true;
/*      */   }
/*      */   
/*      */   public void add(int index, Object element) {
/*   88 */     if (index == this._size) {
/*   89 */       add(element);
/*      */     } else {
/*   91 */       if (index < 0 || index > this._size)
/*   92 */         throw new IndexOutOfBoundsException(String.valueOf(index) + " < 0 or " + String.valueOf(index) + " > " + this._size); 
/*   94 */       Listable succ = isEmpty() ? null : getListableAt(index);
/*   95 */       Listable pred = (null == succ) ? null : succ.prev();
/*   96 */       insertListable(pred, succ, element);
/*      */     } 
/*      */   }
/*      */   
/*      */   public boolean addAll(Collection c) {
/*  118 */     if (c.isEmpty())
/*  119 */       return false; 
/*  121 */     Iterator it = c.iterator();
/*  122 */     while (it.hasNext())
/*  123 */       insertListable(this._head.prev(), null, it.next()); 
/*  125 */     return true;
/*      */   }
/*      */   
/*      */   public boolean addAll(int index, Collection c) {
/*  154 */     if (c.isEmpty())
/*  155 */       return false; 
/*  156 */     if (this._size == index || this._size == 0)
/*  157 */       return addAll(c); 
/*  159 */     Listable succ = getListableAt(index);
/*  160 */     Listable pred = (null == succ) ? null : succ.prev();
/*  161 */     Iterator it = c.iterator();
/*  162 */     while (it.hasNext())
/*  163 */       pred = insertListable(pred, succ, it.next()); 
/*  165 */     return true;
/*      */   }
/*      */   
/*      */   public boolean addFirst(Object o) {
/*  177 */     insertListable(null, this._head.next(), o);
/*  178 */     return true;
/*      */   }
/*      */   
/*      */   public boolean addLast(Object o) {
/*  189 */     insertListable(this._head.prev(), null, o);
/*  190 */     return true;
/*      */   }
/*      */   
/*      */   public void clear() {
/*  207 */     Iterator it = iterator();
/*  208 */     while (it.hasNext()) {
/*  209 */       it.next();
/*  210 */       it.remove();
/*      */     } 
/*      */   }
/*      */   
/*      */   public boolean contains(Object o) {
/*  224 */     for (Listable elt = this._head.next(), past = null; null != elt && past != this._head.prev(); elt = (past = elt).next()) {
/*  225 */       if ((null == o && null == elt.value()) || (o != null && o.equals(elt.value())))
/*  227 */         return true; 
/*      */     } 
/*  230 */     return false;
/*      */   }
/*      */   
/*      */   public boolean containsAll(Collection c) {
/*  242 */     Iterator it = c.iterator();
/*  243 */     while (it.hasNext()) {
/*  244 */       if (!contains(it.next()))
/*  245 */         return false; 
/*      */     } 
/*  248 */     return true;
/*      */   }
/*      */   
/*      */   public Cursor cursor() {
/*  277 */     return new Cursor(this, 0);
/*      */   }
/*      */   
/*      */   public Cursor cursor(int i) {
/*  297 */     return new Cursor(this, i);
/*      */   }
/*      */   
/*      */   public boolean equals(Object o) {
/*  315 */     if (o == this)
/*  316 */       return true; 
/*  317 */     if (!(o instanceof List))
/*  318 */       return false; 
/*  320 */     Iterator it = ((List)o).listIterator();
/*  321 */     for (Listable elt = this._head.next(), past = null; null != elt && past != this._head.prev(); elt = (past = elt).next()) {
/*  322 */       if (!it.hasNext() || ((null == elt.value()) ? (null != it.next()) : !elt.value().equals(it.next())))
/*  323 */         return false; 
/*      */     } 
/*  326 */     return !it.hasNext();
/*      */   }
/*      */   
/*      */   public Object get(int index) {
/*  339 */     return getListableAt(index).value();
/*      */   }
/*      */   
/*      */   public Object getFirst() {
/*      */     try {
/*  347 */       return this._head.next().value();
/*  348 */     } catch (NullPointerException e) {
/*  349 */       throw new NoSuchElementException();
/*      */     } 
/*      */   }
/*      */   
/*      */   public Object getLast() {
/*      */     try {
/*  358 */       return this._head.prev().value();
/*  359 */     } catch (NullPointerException e) {
/*  360 */       throw new NoSuchElementException();
/*      */     } 
/*      */   }
/*      */   
/*      */   public int hashCode() {
/*  386 */     int hash = 1;
/*  387 */     for (Listable elt = this._head.next(), past = null; null != elt && past != this._head.prev(); elt = (past = elt).next())
/*  388 */       hash = 31 * hash + ((null == elt.value()) ? 0 : elt.value().hashCode()); 
/*  390 */     return hash;
/*      */   }
/*      */   
/*      */   public int indexOf(Object o) {
/*  405 */     int ndx = 0;
/*  409 */     if (null == o) {
/*  410 */       for (Listable elt = this._head.next(), past = null; null != elt && past != this._head.prev(); elt = (past = elt).next()) {
/*  411 */         if (null == elt.value())
/*  412 */           return ndx; 
/*  414 */         ndx++;
/*      */       } 
/*      */     } else {
/*  418 */       for (Listable elt = this._head.next(), past = null; null != elt && past != this._head.prev(); elt = (past = elt).next()) {
/*  419 */         if (o.equals(elt.value()))
/*  420 */           return ndx; 
/*  422 */         ndx++;
/*      */       } 
/*      */     } 
/*  425 */     return -1;
/*      */   }
/*      */   
/*      */   public boolean isEmpty() {
/*  433 */     return (0 == this._size);
/*      */   }
/*      */   
/*      */   public Iterator iterator() {
/*  441 */     return listIterator(0);
/*      */   }
/*      */   
/*      */   public int lastIndexOf(Object o) {
/*  456 */     int ndx = this._size - 1;
/*  460 */     if (null == o) {
/*  461 */       for (Listable elt = this._head.prev(), past = null; null != elt && past != this._head.next(); elt = (past = elt).prev()) {
/*  462 */         if (null == elt.value())
/*  463 */           return ndx; 
/*  465 */         ndx--;
/*      */       } 
/*      */     } else {
/*  468 */       for (Listable elt = this._head.prev(), past = null; null != elt && past != this._head.next(); elt = (past = elt).prev()) {
/*  469 */         if (o.equals(elt.value()))
/*  470 */           return ndx; 
/*  472 */         ndx--;
/*      */       } 
/*      */     } 
/*  475 */     return -1;
/*      */   }
/*      */   
/*      */   public ListIterator listIterator() {
/*  483 */     return listIterator(0);
/*      */   }
/*      */   
/*      */   public ListIterator listIterator(int index) {
/*  491 */     if (index < 0 || index > this._size)
/*  492 */       throw new IndexOutOfBoundsException(index + " < 0 or > " + this._size); 
/*  494 */     return new ListIter(this, index);
/*      */   }
/*      */   
/*      */   public boolean remove(Object o) {
/*  508 */     for (Listable elt = this._head.next(), past = null; null != elt && past != this._head.prev(); elt = (past = elt).next()) {
/*  509 */       if (null == o && null == elt.value()) {
/*  510 */         removeListable(elt);
/*  511 */         return true;
/*      */       } 
/*  512 */       if (o != null && o.equals(elt.value())) {
/*  513 */         removeListable(elt);
/*  514 */         return true;
/*      */       } 
/*      */     } 
/*  517 */     return false;
/*      */   }
/*      */   
/*      */   public Object remove(int index) {
/*  533 */     Listable elt = getListableAt(index);
/*  534 */     Object ret = elt.value();
/*  535 */     removeListable(elt);
/*  536 */     return ret;
/*      */   }
/*      */   
/*      */   public boolean removeAll(Collection c) {
/*  548 */     if (0 == c.size() || 0 == this._size)
/*  549 */       return false; 
/*  551 */     boolean changed = false;
/*  552 */     Iterator it = iterator();
/*  553 */     while (it.hasNext()) {
/*  554 */       if (c.contains(it.next())) {
/*  555 */         it.remove();
/*  556 */         changed = true;
/*      */       } 
/*      */     } 
/*  559 */     return changed;
/*      */   }
/*      */   
/*      */   public Object removeFirst() {
/*  567 */     if (this._head.next() != null) {
/*  568 */       Object val = this._head.next().value();
/*  569 */       removeListable(this._head.next());
/*  570 */       return val;
/*      */     } 
/*  572 */     throw new NoSuchElementException();
/*      */   }
/*      */   
/*      */   public Object removeLast() {
/*  580 */     if (this._head.prev() != null) {
/*  581 */       Object val = this._head.prev().value();
/*  582 */       removeListable(this._head.prev());
/*  583 */       return val;
/*      */     } 
/*  585 */     throw new NoSuchElementException();
/*      */   }
/*      */   
/*      */   public boolean retainAll(Collection c) {
/*  600 */     boolean changed = false;
/*  601 */     Iterator it = iterator();
/*  602 */     while (it.hasNext()) {
/*  603 */       if (!c.contains(it.next())) {
/*  604 */         it.remove();
/*  605 */         changed = true;
/*      */       } 
/*      */     } 
/*  608 */     return changed;
/*      */   }
/*      */   
/*      */   public Object set(int index, Object element) {
/*  627 */     Listable elt = getListableAt(index);
/*  628 */     Object val = elt.setValue(element);
/*  629 */     broadcastListableChanged(elt);
/*  630 */     return val;
/*      */   }
/*      */   
/*      */   public int size() {
/*  638 */     return this._size;
/*      */   }
/*      */   
/*      */   public Object[] toArray() {
/*  649 */     Object[] array = new Object[this._size];
/*  650 */     int i = 0;
/*  651 */     for (Listable elt = this._head.next(), past = null; null != elt && past != this._head.prev(); elt = (past = elt).next())
/*  652 */       array[i++] = elt.value(); 
/*  654 */     return array;
/*      */   }
/*      */   
/*      */   public Object[] toArray(Object[] a) {
/*  673 */     if (a.length < this._size)
/*  674 */       a = (Object[])Array.newInstance(a.getClass().getComponentType(), this._size); 
/*  676 */     int i = 0;
/*  677 */     for (Listable elt = this._head.next(), past = null; null != elt && past != this._head.prev(); elt = (past = elt).next())
/*  678 */       a[i++] = elt.value(); 
/*  680 */     if (a.length > this._size)
/*  681 */       a[this._size] = null; 
/*  683 */     return a;
/*      */   }
/*      */   
/*      */   public String toString() {
/*  691 */     StringBuffer buf = new StringBuffer();
/*  692 */     buf.append("[");
/*  693 */     for (Listable elt = this._head.next(), past = null; null != elt && past != this._head.prev(); elt = (past = elt).next()) {
/*  694 */       if (this._head.next() != elt)
/*  695 */         buf.append(", "); 
/*  697 */       buf.append(elt.value());
/*      */     } 
/*  699 */     buf.append("]");
/*  700 */     return buf.toString();
/*      */   }
/*      */   
/*      */   public List subList(int i, int j) {
/*  708 */     if (i < 0 || j > this._size || i > j)
/*  709 */       throw new IndexOutOfBoundsException(); 
/*  710 */     if (i == 0 && j == this._size)
/*  711 */       return this; 
/*  713 */     return new CursorableSubList(this, i, j);
/*      */   }
/*      */   
/*      */   protected Listable insertListable(Listable before, Listable after, Object value) {
/*  728 */     this._modCount++;
/*  729 */     this._size++;
/*  730 */     Listable elt = new Listable(before, after, value);
/*  731 */     if (null != before) {
/*  732 */       before.setNext(elt);
/*      */     } else {
/*  734 */       this._head.setNext(elt);
/*      */     } 
/*  737 */     if (null != after) {
/*  738 */       after.setPrev(elt);
/*      */     } else {
/*  740 */       this._head.setPrev(elt);
/*      */     } 
/*  742 */     broadcastListableInserted(elt);
/*  743 */     return elt;
/*      */   }
/*      */   
/*      */   protected void removeListable(Listable elt) {
/*  752 */     this._modCount++;
/*  753 */     this._size--;
/*  754 */     if (this._head.next() == elt)
/*  755 */       this._head.setNext(elt.next()); 
/*  757 */     if (null != elt.next())
/*  758 */       elt.next().setPrev(elt.prev()); 
/*  760 */     if (this._head.prev() == elt)
/*  761 */       this._head.setPrev(elt.prev()); 
/*  763 */     if (null != elt.prev())
/*  764 */       elt.prev().setNext(elt.next()); 
/*  766 */     broadcastListableRemoved(elt);
/*      */   }
/*      */   
/*      */   protected Listable getListableAt(int index) {
/*  778 */     if (index < 0 || index >= this._size)
/*  779 */       throw new IndexOutOfBoundsException(String.valueOf(index) + " < 0 or " + String.valueOf(index) + " >= " + this._size); 
/*  781 */     if (index <= this._size / 2) {
/*  782 */       Listable listable = this._head.next();
/*  783 */       for (int j = 0; j < index; j++)
/*  784 */         listable = listable.next(); 
/*  786 */       return listable;
/*      */     } 
/*  788 */     Listable elt = this._head.prev();
/*  789 */     for (int i = this._size - 1; i > index; i--)
/*  790 */       elt = elt.prev(); 
/*  792 */     return elt;
/*      */   }
/*      */   
/*      */   protected void registerCursor(Cursor cur) {
/*  803 */     for (Iterator it = this._cursors.iterator(); it.hasNext(); ) {
/*  804 */       WeakReference ref = it.next();
/*  805 */       if (ref.get() == null)
/*  806 */         it.remove(); 
/*      */     } 
/*  810 */     this._cursors.add(new WeakReference(cur));
/*      */   }
/*      */   
/*      */   protected void unregisterCursor(Cursor cur) {
/*  818 */     for (Iterator it = this._cursors.iterator(); it.hasNext(); ) {
/*  819 */       WeakReference ref = it.next();
/*  820 */       Cursor cursor = ref.get();
/*  821 */       if (cursor == null) {
/*  825 */         it.remove();
/*      */         continue;
/*      */       } 
/*  827 */       if (cursor == cur) {
/*  828 */         ref.clear();
/*  829 */         it.remove();
/*      */         break;
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   protected void invalidateCursors() {
/*  840 */     Iterator it = this._cursors.iterator();
/*  841 */     while (it.hasNext()) {
/*  842 */       WeakReference ref = it.next();
/*  843 */       Cursor cursor = ref.get();
/*  844 */       if (cursor != null) {
/*  846 */         cursor.invalidate();
/*  847 */         ref.clear();
/*      */       } 
/*  849 */       it.remove();
/*      */     } 
/*      */   }
/*      */   
/*      */   protected void broadcastListableChanged(Listable elt) {
/*  859 */     Iterator it = this._cursors.iterator();
/*  860 */     while (it.hasNext()) {
/*  861 */       WeakReference ref = it.next();
/*  862 */       Cursor cursor = ref.get();
/*  863 */       if (cursor == null) {
/*  864 */         it.remove();
/*      */         continue;
/*      */       } 
/*  866 */       cursor.listableChanged(elt);
/*      */     } 
/*      */   }
/*      */   
/*      */   protected void broadcastListableRemoved(Listable elt) {
/*  876 */     Iterator it = this._cursors.iterator();
/*  877 */     while (it.hasNext()) {
/*  878 */       WeakReference ref = it.next();
/*  879 */       Cursor cursor = ref.get();
/*  880 */       if (cursor == null) {
/*  881 */         it.remove();
/*      */         continue;
/*      */       } 
/*  883 */       cursor.listableRemoved(elt);
/*      */     } 
/*      */   }
/*      */   
/*      */   protected void broadcastListableInserted(Listable elt) {
/*  893 */     Iterator it = this._cursors.iterator();
/*  894 */     while (it.hasNext()) {
/*  895 */       WeakReference ref = it.next();
/*  896 */       Cursor cursor = ref.get();
/*  897 */       if (cursor == null) {
/*  898 */         it.remove();
/*      */         continue;
/*      */       } 
/*  900 */       cursor.listableInserted(elt);
/*      */     } 
/*      */   }
/*      */   
/*      */   private void writeObject(ObjectOutputStream out) throws IOException {
/*  906 */     out.defaultWriteObject();
/*  907 */     out.writeInt(this._size);
/*  908 */     Listable cur = this._head.next();
/*  909 */     while (cur != null) {
/*  910 */       out.writeObject(cur.value());
/*  911 */       cur = cur.next();
/*      */     } 
/*      */   }
/*      */   
/*      */   private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
/*  916 */     in.defaultReadObject();
/*  917 */     this._size = 0;
/*  918 */     this._modCount = 0;
/*  919 */     this._cursors = new ArrayList();
/*  920 */     this._head = new Listable(null, null, null);
/*  921 */     int size = in.readInt();
/*  922 */     for (int i = 0; i < size; i++)
/*  923 */       add(in.readObject()); 
/*      */   }
/*      */   
/*  930 */   protected transient int _size = 0;
/*      */   
/*  944 */   protected transient Listable _head = new Listable(null, null, null);
/*      */   
/*  947 */   protected transient int _modCount = 0;
/*      */   
/*  953 */   protected transient List _cursors = new ArrayList();
/*      */   
/*      */   static class Listable implements Serializable {
/*  958 */     private Listable _prev = null;
/*      */     
/*  959 */     private Listable _next = null;
/*      */     
/*  960 */     private Object _val = null;
/*      */     
/*      */     Listable(Listable prev, Listable next, Object val) {
/*  963 */       this._prev = prev;
/*  964 */       this._next = next;
/*  965 */       this._val = val;
/*      */     }
/*      */     
/*      */     Listable next() {
/*  969 */       return this._next;
/*      */     }
/*      */     
/*      */     Listable prev() {
/*  973 */       return this._prev;
/*      */     }
/*      */     
/*      */     Object value() {
/*  977 */       return this._val;
/*      */     }
/*      */     
/*      */     void setNext(Listable next) {
/*  981 */       this._next = next;
/*      */     }
/*      */     
/*      */     void setPrev(Listable prev) {
/*  985 */       this._prev = prev;
/*      */     }
/*      */     
/*      */     Object setValue(Object val) {
/*  989 */       Object temp = this._val;
/*  990 */       this._val = val;
/*  991 */       return temp;
/*      */     }
/*      */   }
/*      */   
/*      */   class ListIter implements ListIterator {
/*      */     CursorableLinkedList.Listable _cur;
/*      */     
/*      */     CursorableLinkedList.Listable _lastReturned;
/*      */     
/*      */     int _expectedModCount;
/*      */     
/*      */     int _nextIndex;
/*      */     
/*      */     private final CursorableLinkedList this$0;
/*      */     
/*      */     ListIter(CursorableLinkedList this$0, int index) {
/* 1001 */       this.this$0 = this$0;
/*      */       this._cur = null;
/*      */       this._lastReturned = null;
/*      */       this._expectedModCount = this.this$0._modCount;
/*      */       this._nextIndex = 0;
/* 1002 */       if (index == 0) {
/* 1003 */         this._cur = new CursorableLinkedList.Listable(null, this$0._head.next(), null);
/* 1004 */         this._nextIndex = 0;
/* 1005 */       } else if (index == this$0._size) {
/* 1006 */         this._cur = new CursorableLinkedList.Listable(this$0._head.prev(), null, null);
/* 1007 */         this._nextIndex = this$0._size;
/*      */       } else {
/* 1009 */         CursorableLinkedList.Listable temp = this$0.getListableAt(index);
/* 1010 */         this._cur = new CursorableLinkedList.Listable(temp.prev(), temp, null);
/* 1011 */         this._nextIndex = index;
/*      */       } 
/*      */     }
/*      */     
/*      */     public Object previous() {
/* 1016 */       checkForComod();
/* 1017 */       if (!hasPrevious())
/* 1018 */         throw new NoSuchElementException(); 
/* 1020 */       Object ret = this._cur.prev().value();
/* 1021 */       this._lastReturned = this._cur.prev();
/* 1022 */       this._cur.setNext(this._cur.prev());
/* 1023 */       this._cur.setPrev(this._cur.prev().prev());
/* 1024 */       this._nextIndex--;
/* 1025 */       return ret;
/*      */     }
/*      */     
/*      */     public boolean hasNext() {
/* 1030 */       checkForComod();
/* 1031 */       return (null != this._cur.next() && this._cur.prev() != this.this$0._head.prev());
/*      */     }
/*      */     
/*      */     public Object next() {
/* 1035 */       checkForComod();
/* 1036 */       if (!hasNext())
/* 1037 */         throw new NoSuchElementException(); 
/* 1039 */       Object ret = this._cur.next().value();
/* 1040 */       this._lastReturned = this._cur.next();
/* 1041 */       this._cur.setPrev(this._cur.next());
/* 1042 */       this._cur.setNext(this._cur.next().next());
/* 1043 */       this._nextIndex++;
/* 1044 */       return ret;
/*      */     }
/*      */     
/*      */     public int previousIndex() {
/* 1049 */       checkForComod();
/* 1050 */       if (!hasPrevious())
/* 1051 */         return -1; 
/* 1053 */       return this._nextIndex - 1;
/*      */     }
/*      */     
/*      */     public boolean hasPrevious() {
/* 1057 */       checkForComod();
/* 1058 */       return (null != this._cur.prev() && this._cur.next() != this.this$0._head.next());
/*      */     }
/*      */     
/*      */     public void set(Object o) {
/* 1062 */       checkForComod();
/*      */       try {
/* 1064 */         this._lastReturned.setValue(o);
/* 1065 */       } catch (NullPointerException e) {
/* 1066 */         throw new IllegalStateException();
/*      */       } 
/*      */     }
/*      */     
/*      */     public int nextIndex() {
/* 1071 */       checkForComod();
/* 1072 */       if (!hasNext())
/* 1073 */         return this.this$0.size(); 
/* 1075 */       return this._nextIndex;
/*      */     }
/*      */     
/*      */     public void remove() {
/* 1079 */       checkForComod();
/* 1080 */       if (null == this._lastReturned)
/* 1081 */         throw new IllegalStateException(); 
/* 1083 */       this._cur.setNext((this._lastReturned == this.this$0._head.prev()) ? null : this._lastReturned.next());
/* 1084 */       this._cur.setPrev((this._lastReturned == this.this$0._head.next()) ? null : this._lastReturned.prev());
/* 1085 */       this.this$0.removeListable(this._lastReturned);
/* 1086 */       this._lastReturned = null;
/* 1087 */       this._nextIndex--;
/* 1088 */       this._expectedModCount++;
/*      */     }
/*      */     
/*      */     public void add(Object o) {
/* 1093 */       checkForComod();
/* 1094 */       this._cur.setPrev(this.this$0.insertListable(this._cur.prev(), this._cur.next(), o));
/* 1095 */       this._lastReturned = null;
/* 1096 */       this._nextIndex++;
/* 1097 */       this._expectedModCount++;
/*      */     }
/*      */     
/*      */     protected void checkForComod() {
/* 1101 */       if (this._expectedModCount != this.this$0._modCount)
/* 1102 */         throw new ConcurrentModificationException(); 
/*      */     }
/*      */   }
/*      */   
/*      */   public class Cursor extends ListIter implements ListIterator {
/*      */     boolean _valid;
/*      */     
/*      */     private final CursorableLinkedList this$0;
/*      */     
/*      */     Cursor(CursorableLinkedList this$0, int index) {
/* 1111 */       super(this$0, index);
/*      */       this.this$0 = this$0;
/*      */       this._valid = false;
/* 1112 */       this._valid = true;
/* 1113 */       this$0.registerCursor(this);
/*      */     }
/*      */     
/*      */     public int previousIndex() {
/* 1117 */       throw new UnsupportedOperationException();
/*      */     }
/*      */     
/*      */     public int nextIndex() {
/* 1121 */       throw new UnsupportedOperationException();
/*      */     }
/*      */     
/*      */     public void add(Object o) {
/* 1125 */       checkForComod();
/* 1126 */       CursorableLinkedList.Listable elt = this.this$0.insertListable(this._cur.prev(), this._cur.next(), o);
/* 1127 */       this._cur.setPrev(elt);
/* 1128 */       this._cur.setNext(elt.next());
/* 1129 */       this._lastReturned = null;
/* 1130 */       this._nextIndex++;
/* 1131 */       this._expectedModCount++;
/*      */     }
/*      */     
/*      */     protected void listableRemoved(CursorableLinkedList.Listable elt) {
/* 1135 */       if (null == this.this$0._head.prev()) {
/* 1136 */         this._cur.setNext(null);
/* 1137 */       } else if (this._cur.next() == elt) {
/* 1138 */         this._cur.setNext(elt.next());
/*      */       } 
/* 1140 */       if (null == this.this$0._head.next()) {
/* 1141 */         this._cur.setPrev(null);
/* 1142 */       } else if (this._cur.prev() == elt) {
/* 1143 */         this._cur.setPrev(elt.prev());
/*      */       } 
/* 1145 */       if (this._lastReturned == elt)
/* 1146 */         this._lastReturned = null; 
/*      */     }
/*      */     
/*      */     protected void listableInserted(CursorableLinkedList.Listable elt) {
/* 1151 */       if (null == this._cur.next() && null == this._cur.prev()) {
/* 1152 */         this._cur.setNext(elt);
/* 1153 */       } else if (this._cur.prev() == elt.prev()) {
/* 1154 */         this._cur.setNext(elt);
/*      */       } 
/* 1156 */       if (this._cur.next() == elt.next())
/* 1157 */         this._cur.setPrev(elt); 
/* 1159 */       if (this._lastReturned == elt)
/* 1160 */         this._lastReturned = null; 
/*      */     }
/*      */     
/*      */     protected void listableChanged(CursorableLinkedList.Listable elt) {
/* 1165 */       if (this._lastReturned == elt)
/* 1166 */         this._lastReturned = null; 
/*      */     }
/*      */     
/*      */     protected void checkForComod() {
/* 1171 */       if (!this._valid)
/* 1172 */         throw new ConcurrentModificationException(); 
/*      */     }
/*      */     
/*      */     protected void invalidate() {
/* 1177 */       this._valid = false;
/*      */     }
/*      */     
/*      */     public void close() {
/* 1189 */       if (this._valid) {
/* 1190 */         this._valid = false;
/* 1191 */         this.this$0.unregisterCursor(this);
/*      */       } 
/*      */     }
/*      */   }
/*      */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\collections\CursorableLinkedList.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */