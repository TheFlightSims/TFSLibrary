/*      */ package org.apache.commons.pool.impl;
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
/*      */ class CursorableLinkedList implements List, Serializable {
/*      */   private static final long serialVersionUID = 8836393098519411393L;
/*      */   
/*      */   public boolean add(Object o) {
/*   76 */     insertListable(this._head.prev(), null, o);
/*   77 */     return true;
/*      */   }
/*      */   
/*      */   public void add(int index, Object element) {
/*   96 */     if (index == this._size) {
/*   97 */       add(element);
/*      */     } else {
/*   99 */       if (index < 0 || index > this._size)
/*  100 */         throw new IndexOutOfBoundsException(String.valueOf(index) + " < 0 or " + String.valueOf(index) + " > " + this._size); 
/*  102 */       Listable succ = isEmpty() ? null : getListableAt(index);
/*  103 */       Listable pred = (null == succ) ? null : succ.prev();
/*  104 */       insertListable(pred, succ, element);
/*      */     } 
/*      */   }
/*      */   
/*      */   public boolean addAll(Collection c) {
/*  126 */     if (c.isEmpty())
/*  127 */       return false; 
/*  129 */     Iterator it = c.iterator();
/*  130 */     while (it.hasNext())
/*  131 */       insertListable(this._head.prev(), null, it.next()); 
/*  133 */     return true;
/*      */   }
/*      */   
/*      */   public boolean addAll(int index, Collection c) {
/*  162 */     if (c.isEmpty())
/*  163 */       return false; 
/*  164 */     if (this._size == index || this._size == 0)
/*  165 */       return addAll(c); 
/*  167 */     Listable succ = getListableAt(index);
/*  168 */     Listable pred = (null == succ) ? null : succ.prev();
/*  169 */     Iterator it = c.iterator();
/*  170 */     while (it.hasNext())
/*  171 */       pred = insertListable(pred, succ, it.next()); 
/*  173 */     return true;
/*      */   }
/*      */   
/*      */   public boolean addFirst(Object o) {
/*  185 */     insertListable(null, this._head.next(), o);
/*  186 */     return true;
/*      */   }
/*      */   
/*      */   public boolean addLast(Object o) {
/*  197 */     insertListable(this._head.prev(), null, o);
/*  198 */     return true;
/*      */   }
/*      */   
/*      */   public void clear() {
/*  215 */     Iterator it = iterator();
/*  216 */     while (it.hasNext()) {
/*  217 */       it.next();
/*  218 */       it.remove();
/*      */     } 
/*      */   }
/*      */   
/*      */   public boolean contains(Object o) {
/*  232 */     for (Listable elt = this._head.next(), past = null; null != elt && past != this._head.prev(); elt = (past = elt).next()) {
/*  233 */       if ((null == o && null == elt.value()) || (o != null && o.equals(elt.value())))
/*  235 */         return true; 
/*      */     } 
/*  238 */     return false;
/*      */   }
/*      */   
/*      */   public boolean containsAll(Collection c) {
/*  250 */     Iterator it = c.iterator();
/*  251 */     while (it.hasNext()) {
/*  252 */       if (!contains(it.next()))
/*  253 */         return false; 
/*      */     } 
/*  256 */     return true;
/*      */   }
/*      */   
/*      */   public Cursor cursor() {
/*  285 */     return new Cursor(this, 0);
/*      */   }
/*      */   
/*      */   public Cursor cursor(int i) {
/*  305 */     return new Cursor(this, i);
/*      */   }
/*      */   
/*      */   public boolean equals(Object o) {
/*  323 */     if (o == this)
/*  324 */       return true; 
/*  325 */     if (!(o instanceof List))
/*  326 */       return false; 
/*  328 */     Iterator it = ((List)o).listIterator();
/*  329 */     for (Listable elt = this._head.next(), past = null; null != elt && past != this._head.prev(); elt = (past = elt).next()) {
/*  330 */       if (!it.hasNext() || ((null == elt.value()) ? (null != it.next()) : !elt.value().equals(it.next())))
/*  331 */         return false; 
/*      */     } 
/*  334 */     return !it.hasNext();
/*      */   }
/*      */   
/*      */   public Object get(int index) {
/*  347 */     return getListableAt(index).value();
/*      */   }
/*      */   
/*      */   public Object getFirst() {
/*      */     try {
/*  355 */       return this._head.next().value();
/*  356 */     } catch (NullPointerException e) {
/*  357 */       throw new NoSuchElementException();
/*      */     } 
/*      */   }
/*      */   
/*      */   public Object getLast() {
/*      */     try {
/*  366 */       return this._head.prev().value();
/*  367 */     } catch (NullPointerException e) {
/*  368 */       throw new NoSuchElementException();
/*      */     } 
/*      */   }
/*      */   
/*      */   public int hashCode() {
/*  394 */     int hash = 1;
/*  395 */     for (Listable elt = this._head.next(), past = null; null != elt && past != this._head.prev(); elt = (past = elt).next())
/*  396 */       hash = 31 * hash + ((null == elt.value()) ? 0 : elt.value().hashCode()); 
/*  398 */     return hash;
/*      */   }
/*      */   
/*      */   public int indexOf(Object o) {
/*  413 */     int ndx = 0;
/*  417 */     if (null == o) {
/*  418 */       for (Listable elt = this._head.next(), past = null; null != elt && past != this._head.prev(); elt = (past = elt).next()) {
/*  419 */         if (null == elt.value())
/*  420 */           return ndx; 
/*  422 */         ndx++;
/*      */       } 
/*      */     } else {
/*  426 */       for (Listable elt = this._head.next(), past = null; null != elt && past != this._head.prev(); elt = (past = elt).next()) {
/*  427 */         if (o.equals(elt.value()))
/*  428 */           return ndx; 
/*  430 */         ndx++;
/*      */       } 
/*      */     } 
/*  433 */     return -1;
/*      */   }
/*      */   
/*      */   public boolean isEmpty() {
/*  441 */     return (0 == this._size);
/*      */   }
/*      */   
/*      */   public Iterator iterator() {
/*  449 */     return listIterator(0);
/*      */   }
/*      */   
/*      */   public int lastIndexOf(Object o) {
/*  464 */     int ndx = this._size - 1;
/*  468 */     if (null == o) {
/*  469 */       for (Listable elt = this._head.prev(), past = null; null != elt && past != this._head.next(); elt = (past = elt).prev()) {
/*  470 */         if (null == elt.value())
/*  471 */           return ndx; 
/*  473 */         ndx--;
/*      */       } 
/*      */     } else {
/*  476 */       for (Listable elt = this._head.prev(), past = null; null != elt && past != this._head.next(); elt = (past = elt).prev()) {
/*  477 */         if (o.equals(elt.value()))
/*  478 */           return ndx; 
/*  480 */         ndx--;
/*      */       } 
/*      */     } 
/*  483 */     return -1;
/*      */   }
/*      */   
/*      */   public ListIterator listIterator() {
/*  491 */     return listIterator(0);
/*      */   }
/*      */   
/*      */   public ListIterator listIterator(int index) {
/*  499 */     if (index < 0 || index > this._size)
/*  500 */       throw new IndexOutOfBoundsException(index + " < 0 or > " + this._size); 
/*  502 */     return new ListIter(this, index);
/*      */   }
/*      */   
/*      */   public boolean remove(Object o) {
/*  516 */     for (Listable elt = this._head.next(), past = null; null != elt && past != this._head.prev(); elt = (past = elt).next()) {
/*  517 */       if (null == o && null == elt.value()) {
/*  518 */         removeListable(elt);
/*  519 */         return true;
/*      */       } 
/*  520 */       if (o != null && o.equals(elt.value())) {
/*  521 */         removeListable(elt);
/*  522 */         return true;
/*      */       } 
/*      */     } 
/*  525 */     return false;
/*      */   }
/*      */   
/*      */   public Object remove(int index) {
/*  541 */     Listable elt = getListableAt(index);
/*  542 */     Object ret = elt.value();
/*  543 */     removeListable(elt);
/*  544 */     return ret;
/*      */   }
/*      */   
/*      */   public boolean removeAll(Collection c) {
/*  556 */     if (0 == c.size() || 0 == this._size)
/*  557 */       return false; 
/*  559 */     boolean changed = false;
/*  560 */     Iterator it = iterator();
/*  561 */     while (it.hasNext()) {
/*  562 */       if (c.contains(it.next())) {
/*  563 */         it.remove();
/*  564 */         changed = true;
/*      */       } 
/*      */     } 
/*  567 */     return changed;
/*      */   }
/*      */   
/*      */   public Object removeFirst() {
/*  575 */     if (this._head.next() != null) {
/*  576 */       Object val = this._head.next().value();
/*  577 */       removeListable(this._head.next());
/*  578 */       return val;
/*      */     } 
/*  580 */     throw new NoSuchElementException();
/*      */   }
/*      */   
/*      */   public Object removeLast() {
/*  588 */     if (this._head.prev() != null) {
/*  589 */       Object val = this._head.prev().value();
/*  590 */       removeListable(this._head.prev());
/*  591 */       return val;
/*      */     } 
/*  593 */     throw new NoSuchElementException();
/*      */   }
/*      */   
/*      */   public boolean retainAll(Collection c) {
/*  608 */     boolean changed = false;
/*  609 */     Iterator it = iterator();
/*  610 */     while (it.hasNext()) {
/*  611 */       if (!c.contains(it.next())) {
/*  612 */         it.remove();
/*  613 */         changed = true;
/*      */       } 
/*      */     } 
/*  616 */     return changed;
/*      */   }
/*      */   
/*      */   public Object set(int index, Object element) {
/*  635 */     Listable elt = getListableAt(index);
/*  636 */     Object val = elt.setValue(element);
/*  637 */     broadcastListableChanged(elt);
/*  638 */     return val;
/*      */   }
/*      */   
/*      */   public int size() {
/*  646 */     return this._size;
/*      */   }
/*      */   
/*      */   public Object[] toArray() {
/*  657 */     Object[] array = new Object[this._size];
/*  658 */     int i = 0;
/*  659 */     for (Listable elt = this._head.next(), past = null; null != elt && past != this._head.prev(); elt = (past = elt).next())
/*  660 */       array[i++] = elt.value(); 
/*  662 */     return array;
/*      */   }
/*      */   
/*      */   public Object[] toArray(Object[] a) {
/*  681 */     if (a.length < this._size)
/*  682 */       a = (Object[])Array.newInstance(a.getClass().getComponentType(), this._size); 
/*  684 */     int i = 0;
/*  685 */     for (Listable elt = this._head.next(), past = null; null != elt && past != this._head.prev(); elt = (past = elt).next())
/*  686 */       a[i++] = elt.value(); 
/*  688 */     if (a.length > this._size)
/*  689 */       a[this._size] = null; 
/*  691 */     return a;
/*      */   }
/*      */   
/*      */   public String toString() {
/*  699 */     StringBuffer buf = new StringBuffer();
/*  700 */     buf.append("[");
/*  701 */     for (Listable elt = this._head.next(), past = null; null != elt && past != this._head.prev(); elt = (past = elt).next()) {
/*  702 */       if (this._head.next() != elt)
/*  703 */         buf.append(", "); 
/*  705 */       buf.append(elt.value());
/*      */     } 
/*  707 */     buf.append("]");
/*  708 */     return buf.toString();
/*      */   }
/*      */   
/*      */   public List subList(int i, int j) {
/*  716 */     if (i < 0 || j > this._size || i > j)
/*  717 */       throw new IndexOutOfBoundsException(); 
/*  718 */     if (i == 0 && j == this._size)
/*  719 */       return this; 
/*  721 */     return new CursorableSubList(this, i, j);
/*      */   }
/*      */   
/*      */   protected Listable insertListable(Listable before, Listable after, Object value) {
/*  736 */     this._modCount++;
/*  737 */     this._size++;
/*  738 */     Listable elt = new Listable(before, after, value);
/*  739 */     if (null != before) {
/*  740 */       before.setNext(elt);
/*      */     } else {
/*  742 */       this._head.setNext(elt);
/*      */     } 
/*  745 */     if (null != after) {
/*  746 */       after.setPrev(elt);
/*      */     } else {
/*  748 */       this._head.setPrev(elt);
/*      */     } 
/*  750 */     broadcastListableInserted(elt);
/*  751 */     return elt;
/*      */   }
/*      */   
/*      */   protected void removeListable(Listable elt) {
/*  760 */     this._modCount++;
/*  761 */     this._size--;
/*  762 */     if (this._head.next() == elt)
/*  763 */       this._head.setNext(elt.next()); 
/*  765 */     if (null != elt.next())
/*  766 */       elt.next().setPrev(elt.prev()); 
/*  768 */     if (this._head.prev() == elt)
/*  769 */       this._head.setPrev(elt.prev()); 
/*  771 */     if (null != elt.prev())
/*  772 */       elt.prev().setNext(elt.next()); 
/*  774 */     broadcastListableRemoved(elt);
/*      */   }
/*      */   
/*      */   protected Listable getListableAt(int index) {
/*  786 */     if (index < 0 || index >= this._size)
/*  787 */       throw new IndexOutOfBoundsException(String.valueOf(index) + " < 0 or " + String.valueOf(index) + " >= " + this._size); 
/*  789 */     if (index <= this._size / 2) {
/*  790 */       Listable listable = this._head.next();
/*  791 */       for (int j = 0; j < index; j++)
/*  792 */         listable = listable.next(); 
/*  794 */       return listable;
/*      */     } 
/*  796 */     Listable elt = this._head.prev();
/*  797 */     for (int i = this._size - 1; i > index; i--)
/*  798 */       elt = elt.prev(); 
/*  800 */     return elt;
/*      */   }
/*      */   
/*      */   protected void registerCursor(Cursor cur) {
/*  811 */     for (Iterator it = this._cursors.iterator(); it.hasNext(); ) {
/*  812 */       WeakReference ref = it.next();
/*  813 */       if (ref.get() == null)
/*  814 */         it.remove(); 
/*      */     } 
/*  818 */     this._cursors.add(new WeakReference(cur));
/*      */   }
/*      */   
/*      */   protected void unregisterCursor(Cursor cur) {
/*  826 */     for (Iterator it = this._cursors.iterator(); it.hasNext(); ) {
/*  827 */       WeakReference ref = it.next();
/*  828 */       Cursor cursor = ref.get();
/*  829 */       if (cursor == null) {
/*  833 */         it.remove();
/*      */         continue;
/*      */       } 
/*  835 */       if (cursor == cur) {
/*  836 */         ref.clear();
/*  837 */         it.remove();
/*      */         break;
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   protected void invalidateCursors() {
/*  848 */     Iterator it = this._cursors.iterator();
/*  849 */     while (it.hasNext()) {
/*  850 */       WeakReference ref = it.next();
/*  851 */       Cursor cursor = ref.get();
/*  852 */       if (cursor != null) {
/*  854 */         cursor.invalidate();
/*  855 */         ref.clear();
/*      */       } 
/*  857 */       it.remove();
/*      */     } 
/*      */   }
/*      */   
/*      */   protected void broadcastListableChanged(Listable elt) {
/*  867 */     Iterator it = this._cursors.iterator();
/*  868 */     while (it.hasNext()) {
/*  869 */       WeakReference ref = it.next();
/*  870 */       Cursor cursor = ref.get();
/*  871 */       if (cursor == null) {
/*  872 */         it.remove();
/*      */         continue;
/*      */       } 
/*  874 */       cursor.listableChanged(elt);
/*      */     } 
/*      */   }
/*      */   
/*      */   protected void broadcastListableRemoved(Listable elt) {
/*  884 */     Iterator it = this._cursors.iterator();
/*  885 */     while (it.hasNext()) {
/*  886 */       WeakReference ref = it.next();
/*  887 */       Cursor cursor = ref.get();
/*  888 */       if (cursor == null) {
/*  889 */         it.remove();
/*      */         continue;
/*      */       } 
/*  891 */       cursor.listableRemoved(elt);
/*      */     } 
/*      */   }
/*      */   
/*      */   protected void broadcastListableInserted(Listable elt) {
/*  901 */     Iterator it = this._cursors.iterator();
/*  902 */     while (it.hasNext()) {
/*  903 */       WeakReference ref = it.next();
/*  904 */       Cursor cursor = ref.get();
/*  905 */       if (cursor == null) {
/*  906 */         it.remove();
/*      */         continue;
/*      */       } 
/*  908 */       cursor.listableInserted(elt);
/*      */     } 
/*      */   }
/*      */   
/*      */   private void writeObject(ObjectOutputStream out) throws IOException {
/*  914 */     out.defaultWriteObject();
/*  915 */     out.writeInt(this._size);
/*  916 */     Listable cur = this._head.next();
/*  917 */     while (cur != null) {
/*  918 */       out.writeObject(cur.value());
/*  919 */       cur = cur.next();
/*      */     } 
/*      */   }
/*      */   
/*      */   private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
/*  924 */     in.defaultReadObject();
/*  925 */     this._size = 0;
/*  926 */     this._modCount = 0;
/*  927 */     this._cursors = new ArrayList();
/*  928 */     this._head = new Listable(null, null, null);
/*  929 */     int size = in.readInt();
/*  930 */     for (int i = 0; i < size; i++)
/*  931 */       add(in.readObject()); 
/*      */   }
/*      */   
/*  938 */   protected transient int _size = 0;
/*      */   
/*  952 */   protected transient Listable _head = new Listable(null, null, null);
/*      */   
/*  955 */   protected transient int _modCount = 0;
/*      */   
/*  961 */   protected transient List _cursors = new ArrayList();
/*      */   
/*      */   static class Listable implements Serializable {
/*  966 */     private Listable _prev = null;
/*      */     
/*  967 */     private Listable _next = null;
/*      */     
/*  968 */     private Object _val = null;
/*      */     
/*      */     Listable(Listable prev, Listable next, Object val) {
/*  971 */       this._prev = prev;
/*  972 */       this._next = next;
/*  973 */       this._val = val;
/*      */     }
/*      */     
/*      */     Listable next() {
/*  977 */       return this._next;
/*      */     }
/*      */     
/*      */     Listable prev() {
/*  981 */       return this._prev;
/*      */     }
/*      */     
/*      */     Object value() {
/*  985 */       return this._val;
/*      */     }
/*      */     
/*      */     void setNext(Listable next) {
/*  989 */       this._next = next;
/*      */     }
/*      */     
/*      */     void setPrev(Listable prev) {
/*  993 */       this._prev = prev;
/*      */     }
/*      */     
/*      */     Object setValue(Object val) {
/*  997 */       Object temp = this._val;
/*  998 */       this._val = val;
/*  999 */       return temp;
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
/* 1009 */       this.this$0 = this$0;
/*      */       this._cur = null;
/*      */       this._lastReturned = null;
/*      */       this._expectedModCount = this.this$0._modCount;
/*      */       this._nextIndex = 0;
/* 1010 */       if (index == 0) {
/* 1011 */         this._cur = new CursorableLinkedList.Listable(null, this$0._head.next(), null);
/* 1012 */         this._nextIndex = 0;
/* 1013 */       } else if (index == this$0._size) {
/* 1014 */         this._cur = new CursorableLinkedList.Listable(this$0._head.prev(), null, null);
/* 1015 */         this._nextIndex = this$0._size;
/*      */       } else {
/* 1017 */         CursorableLinkedList.Listable temp = this$0.getListableAt(index);
/* 1018 */         this._cur = new CursorableLinkedList.Listable(temp.prev(), temp, null);
/* 1019 */         this._nextIndex = index;
/*      */       } 
/*      */     }
/*      */     
/*      */     public Object previous() {
/* 1024 */       checkForComod();
/* 1025 */       if (!hasPrevious())
/* 1026 */         throw new NoSuchElementException(); 
/* 1028 */       Object ret = this._cur.prev().value();
/* 1029 */       this._lastReturned = this._cur.prev();
/* 1030 */       this._cur.setNext(this._cur.prev());
/* 1031 */       this._cur.setPrev(this._cur.prev().prev());
/* 1032 */       this._nextIndex--;
/* 1033 */       return ret;
/*      */     }
/*      */     
/*      */     public boolean hasNext() {
/* 1038 */       checkForComod();
/* 1039 */       return (null != this._cur.next() && this._cur.prev() != this.this$0._head.prev());
/*      */     }
/*      */     
/*      */     public Object next() {
/* 1043 */       checkForComod();
/* 1044 */       if (!hasNext())
/* 1045 */         throw new NoSuchElementException(); 
/* 1047 */       Object ret = this._cur.next().value();
/* 1048 */       this._lastReturned = this._cur.next();
/* 1049 */       this._cur.setPrev(this._cur.next());
/* 1050 */       this._cur.setNext(this._cur.next().next());
/* 1051 */       this._nextIndex++;
/* 1052 */       return ret;
/*      */     }
/*      */     
/*      */     public int previousIndex() {
/* 1057 */       checkForComod();
/* 1058 */       if (!hasPrevious())
/* 1059 */         return -1; 
/* 1061 */       return this._nextIndex - 1;
/*      */     }
/*      */     
/*      */     public boolean hasPrevious() {
/* 1065 */       checkForComod();
/* 1066 */       return (null != this._cur.prev() && this._cur.next() != this.this$0._head.next());
/*      */     }
/*      */     
/*      */     public void set(Object o) {
/* 1070 */       checkForComod();
/*      */       try {
/* 1072 */         this._lastReturned.setValue(o);
/* 1073 */       } catch (NullPointerException e) {
/* 1074 */         throw new IllegalStateException();
/*      */       } 
/*      */     }
/*      */     
/*      */     public int nextIndex() {
/* 1079 */       checkForComod();
/* 1080 */       if (!hasNext())
/* 1081 */         return this.this$0.size(); 
/* 1083 */       return this._nextIndex;
/*      */     }
/*      */     
/*      */     public void remove() {
/* 1087 */       checkForComod();
/* 1088 */       if (null == this._lastReturned)
/* 1089 */         throw new IllegalStateException(); 
/* 1091 */       this._cur.setNext((this._lastReturned == this.this$0._head.prev()) ? null : this._lastReturned.next());
/* 1092 */       this._cur.setPrev((this._lastReturned == this.this$0._head.next()) ? null : this._lastReturned.prev());
/* 1093 */       this.this$0.removeListable(this._lastReturned);
/* 1094 */       this._lastReturned = null;
/* 1095 */       this._nextIndex--;
/* 1096 */       this._expectedModCount++;
/*      */     }
/*      */     
/*      */     public void add(Object o) {
/* 1101 */       checkForComod();
/* 1102 */       this._cur.setPrev(this.this$0.insertListable(this._cur.prev(), this._cur.next(), o));
/* 1103 */       this._lastReturned = null;
/* 1104 */       this._nextIndex++;
/* 1105 */       this._expectedModCount++;
/*      */     }
/*      */     
/*      */     protected void checkForComod() {
/* 1109 */       if (this._expectedModCount != this.this$0._modCount)
/* 1110 */         throw new ConcurrentModificationException(); 
/*      */     }
/*      */   }
/*      */   
/*      */   public class Cursor extends ListIter implements ListIterator {
/*      */     boolean _valid;
/*      */     
/*      */     private final CursorableLinkedList this$0;
/*      */     
/*      */     Cursor(CursorableLinkedList this$0, int index) {
/* 1119 */       super(this$0, index);
/*      */       this.this$0 = this$0;
/*      */       this._valid = false;
/* 1120 */       this._valid = true;
/* 1121 */       this$0.registerCursor(this);
/*      */     }
/*      */     
/*      */     public int previousIndex() {
/* 1125 */       throw new UnsupportedOperationException();
/*      */     }
/*      */     
/*      */     public int nextIndex() {
/* 1129 */       throw new UnsupportedOperationException();
/*      */     }
/*      */     
/*      */     public void add(Object o) {
/* 1133 */       checkForComod();
/* 1134 */       CursorableLinkedList.Listable elt = this.this$0.insertListable(this._cur.prev(), this._cur.next(), o);
/* 1135 */       this._cur.setPrev(elt);
/* 1136 */       this._cur.setNext(elt.next());
/* 1137 */       this._lastReturned = null;
/* 1138 */       this._nextIndex++;
/* 1139 */       this._expectedModCount++;
/*      */     }
/*      */     
/*      */     protected void listableRemoved(CursorableLinkedList.Listable elt) {
/* 1143 */       if (null == this.this$0._head.prev()) {
/* 1144 */         this._cur.setNext(null);
/* 1145 */       } else if (this._cur.next() == elt) {
/* 1146 */         this._cur.setNext(elt.next());
/*      */       } 
/* 1148 */       if (null == this.this$0._head.next()) {
/* 1149 */         this._cur.setPrev(null);
/* 1150 */       } else if (this._cur.prev() == elt) {
/* 1151 */         this._cur.setPrev(elt.prev());
/*      */       } 
/* 1153 */       if (this._lastReturned == elt)
/* 1154 */         this._lastReturned = null; 
/*      */     }
/*      */     
/*      */     protected void listableInserted(CursorableLinkedList.Listable elt) {
/* 1159 */       if (null == this._cur.next() && null == this._cur.prev()) {
/* 1160 */         this._cur.setNext(elt);
/* 1161 */       } else if (this._cur.prev() == elt.prev()) {
/* 1162 */         this._cur.setNext(elt);
/*      */       } 
/* 1164 */       if (this._cur.next() == elt.next())
/* 1165 */         this._cur.setPrev(elt); 
/* 1167 */       if (this._lastReturned == elt)
/* 1168 */         this._lastReturned = null; 
/*      */     }
/*      */     
/*      */     protected void listableChanged(CursorableLinkedList.Listable elt) {
/* 1173 */       if (this._lastReturned == elt)
/* 1174 */         this._lastReturned = null; 
/*      */     }
/*      */     
/*      */     protected void checkForComod() {
/* 1179 */       if (!this._valid)
/* 1180 */         throw new ConcurrentModificationException(); 
/*      */     }
/*      */     
/*      */     protected void invalidate() {
/* 1185 */       this._valid = false;
/*      */     }
/*      */     
/*      */     public void close() {
/* 1197 */       if (this._valid) {
/* 1198 */         this._valid = false;
/* 1199 */         this.this$0.unregisterCursor(this);
/*      */       } 
/*      */     }
/*      */   }
/*      */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\pool\impl\CursorableLinkedList.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */