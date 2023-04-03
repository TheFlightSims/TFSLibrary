/*     */ package gnu.trove.list.linked;
/*     */ 
/*     */ import gnu.trove.list.TLinkable;
/*     */ import gnu.trove.procedure.TObjectProcedure;
/*     */ import java.io.Externalizable;
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInput;
/*     */ import java.io.ObjectOutput;
/*     */ import java.lang.reflect.Array;
/*     */ import java.util.AbstractSequentialList;
/*     */ import java.util.ListIterator;
/*     */ import java.util.NoSuchElementException;
/*     */ 
/*     */ public class TLinkedList<T extends TLinkable<T>> extends AbstractSequentialList<T> implements Externalizable {
/*     */   static final long serialVersionUID = 1L;
/*     */   
/*     */   protected T _head;
/*     */   
/*     */   protected T _tail;
/*     */   
/*  78 */   protected int _size = 0;
/*     */   
/*     */   public ListIterator<T> listIterator(int index) {
/* 100 */     return new IteratorImpl(index);
/*     */   }
/*     */   
/*     */   public int size() {
/* 110 */     return this._size;
/*     */   }
/*     */   
/*     */   public void add(int index, T linkable) {
/* 123 */     if (index < 0 || index > size())
/* 124 */       throw new IndexOutOfBoundsException("index:" + index); 
/* 126 */     insert(index, linkable);
/*     */   }
/*     */   
/*     */   public boolean add(T linkable) {
/* 137 */     insert(this._size, linkable);
/* 138 */     return true;
/*     */   }
/*     */   
/*     */   public void addFirst(T linkable) {
/* 148 */     insert(0, linkable);
/*     */   }
/*     */   
/*     */   public void addLast(T linkable) {
/* 158 */     insert(size(), linkable);
/*     */   }
/*     */   
/*     */   public void clear() {
/* 164 */     if (null != this._head) {
/* 165 */       TLinkable<T> link = this._head.getNext();
/* 166 */       for (; link != null; 
/* 167 */         link = link.getNext()) {
/* 168 */         TLinkable<T> prev = link.getPrevious();
/* 169 */         prev.setNext(null);
/* 170 */         link.setPrevious(null);
/*     */       } 
/* 172 */       this._head = this._tail = null;
/*     */     } 
/* 174 */     this._size = 0;
/*     */   }
/*     */   
/*     */   public Object[] toArray() {
/* 190 */     Object[] o = new Object[this._size];
/* 191 */     int i = 0;
/* 192 */     for (T t = this._head; t != null; tLinkable = t.getNext()) {
/*     */       TLinkable tLinkable;
/* 193 */       o[i++] = t;
/*     */     } 
/* 195 */     return o;
/*     */   }
/*     */   
/*     */   public Object[] toUnlinkedArray() {
/* 210 */     Object[] o = new Object[this._size];
/* 211 */     int i = 0;
/* 212 */     for (T t = this._head; t != null; i++) {
/* 213 */       o[i] = t;
/* 214 */       T t1 = t;
/* 215 */       TLinkable tLinkable = t.getNext();
/* 216 */       t1.setNext(null);
/* 217 */       t1.setPrevious(null);
/*     */     } 
/* 219 */     this._size = 0;
/* 220 */     this._head = this._tail = null;
/* 221 */     return o;
/*     */   }
/*     */   
/*     */   public T[] toUnlinkedArray(T[] a) {
/*     */     TLinkable[] arrayOfTLinkable;
/* 233 */     int size = size();
/* 234 */     if (a.length < size)
/* 235 */       arrayOfTLinkable = (TLinkable[])Array.newInstance(a.getClass().getComponentType(), size); 
/* 238 */     int i = 0;
/* 239 */     for (T link = this._head; link != null; i++) {
/* 240 */       arrayOfTLinkable[i] = (TLinkable)link;
/* 241 */       T tmp = link;
/* 242 */       TLinkable tLinkable = link.getNext();
/* 243 */       tmp.setNext(null);
/* 244 */       tmp.setPrevious(null);
/*     */     } 
/* 246 */     this._size = 0;
/* 247 */     this._head = this._tail = null;
/* 248 */     return (T[])arrayOfTLinkable;
/*     */   }
/*     */   
/*     */   public boolean contains(Object o) {
/* 259 */     for (T t = this._head; t != null; tLinkable = t.getNext()) {
/*     */       TLinkable tLinkable;
/* 260 */       if (o.equals(t))
/* 261 */         return true; 
/*     */     } 
/* 264 */     return false;
/*     */   }
/*     */   
/*     */   public T get(int index) {
/*     */     TLinkable tLinkable;
/* 273 */     if (index < 0 || index >= this._size)
/* 274 */       throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + this._size); 
/* 278 */     if (index > this._size >> 1) {
/* 279 */       int i = this._size - 1;
/* 280 */       T t = this._tail;
/* 282 */       while (i > index) {
/* 283 */         tLinkable = t.getPrevious();
/* 284 */         i--;
/*     */       } 
/* 287 */       return (T)tLinkable;
/*     */     } 
/* 289 */     int position = 0;
/* 290 */     T node = this._head;
/* 292 */     while (position < index) {
/* 293 */       tLinkable = node.getNext();
/* 294 */       position++;
/*     */     } 
/* 297 */     return (T)tLinkable;
/*     */   }
/*     */   
/*     */   public T getFirst() {
/* 308 */     return this._head;
/*     */   }
/*     */   
/*     */   public T getLast() {
/* 318 */     return this._tail;
/*     */   }
/*     */   
/*     */   public T getNext(T current) {
/* 339 */     return (T)current.getNext();
/*     */   }
/*     */   
/*     */   public T getPrevious(T current) {
/* 360 */     return (T)current.getPrevious();
/*     */   }
/*     */   
/*     */   public T removeFirst() {
/* 371 */     T o = this._head;
/* 373 */     if (o == null)
/* 374 */       return null; 
/* 377 */     TLinkable tLinkable = o.getNext();
/* 378 */     o.setNext(null);
/* 380 */     if (null != tLinkable)
/* 381 */       tLinkable.setPrevious(null); 
/* 384 */     this._head = (T)tLinkable;
/* 385 */     if (--this._size == 0)
/* 386 */       this._tail = null; 
/* 388 */     return o;
/*     */   }
/*     */   
/*     */   public T removeLast() {
/* 399 */     T o = this._tail;
/* 401 */     if (o == null)
/* 402 */       return null; 
/* 405 */     TLinkable tLinkable = o.getPrevious();
/* 406 */     o.setPrevious(null);
/* 408 */     if (null != tLinkable)
/* 409 */       tLinkable.setNext(null); 
/* 411 */     this._tail = (T)tLinkable;
/* 412 */     if (--this._size == 0)
/* 413 */       this._head = null; 
/* 415 */     return o;
/*     */   }
/*     */   
/*     */   protected void insert(int index, T linkable) {
/* 428 */     if (this._size == 0) {
/* 429 */       this._head = this._tail = linkable;
/* 430 */     } else if (index == 0) {
/* 431 */       linkable.setNext((TLinkable)this._head);
/* 432 */       this._head.setPrevious((TLinkable)linkable);
/* 433 */       this._head = linkable;
/* 434 */     } else if (index == this._size) {
/* 435 */       this._tail.setNext((TLinkable)linkable);
/* 436 */       linkable.setPrevious((TLinkable)this._tail);
/* 437 */       this._tail = linkable;
/*     */     } else {
/* 439 */       T node = get(index);
/* 441 */       TLinkable tLinkable = node.getPrevious();
/* 442 */       if (tLinkable != null)
/* 443 */         tLinkable.setNext((TLinkable)linkable); 
/* 446 */       linkable.setPrevious(tLinkable);
/* 447 */       linkable.setNext((TLinkable)node);
/* 448 */       node.setPrevious((TLinkable)linkable);
/*     */     } 
/* 450 */     this._size++;
/*     */   }
/*     */   
/*     */   public boolean remove(Object o) {
/* 465 */     if (o instanceof TLinkable) {
/* 467 */       TLinkable<T> link = (TLinkable<T>)o;
/* 469 */       TLinkable tLinkable1 = link.getPrevious();
/* 470 */       TLinkable tLinkable2 = link.getNext();
/* 472 */       if (tLinkable2 == null && tLinkable1 == null) {
/* 476 */         if (o != this._head)
/* 477 */           return false; 
/* 480 */         this._head = this._tail = null;
/* 481 */       } else if (tLinkable2 == null) {
/* 483 */         link.setPrevious(null);
/* 484 */         tLinkable1.setNext(null);
/* 485 */         this._tail = (T)tLinkable1;
/* 486 */       } else if (tLinkable1 == null) {
/* 488 */         link.setNext(null);
/* 489 */         tLinkable2.setPrevious(null);
/* 490 */         this._head = (T)tLinkable2;
/*     */       } else {
/* 492 */         tLinkable1.setNext(tLinkable2);
/* 493 */         tLinkable2.setPrevious(tLinkable1);
/* 494 */         link.setNext(null);
/* 495 */         link.setPrevious(null);
/*     */       } 
/* 498 */       this._size--;
/* 499 */       return true;
/*     */     } 
/* 501 */     return false;
/*     */   }
/*     */   
/*     */   public void addBefore(T current, T newElement) {
/* 516 */     if (current == this._head) {
/* 517 */       addFirst(newElement);
/* 518 */     } else if (current == null) {
/* 519 */       addLast(newElement);
/*     */     } else {
/* 521 */       TLinkable tLinkable = current.getPrevious();
/* 522 */       newElement.setNext((TLinkable)current);
/* 523 */       tLinkable.setNext((TLinkable)newElement);
/* 524 */       newElement.setPrevious(tLinkable);
/* 525 */       current.setPrevious((TLinkable)newElement);
/* 526 */       this._size++;
/*     */     } 
/*     */   }
/*     */   
/*     */   public void addAfter(T current, T newElement) {
/* 541 */     if (current == this._tail) {
/* 542 */       addLast(newElement);
/* 543 */     } else if (current == null) {
/* 544 */       addFirst(newElement);
/*     */     } else {
/* 546 */       TLinkable tLinkable = current.getNext();
/* 547 */       newElement.setPrevious((TLinkable)current);
/* 548 */       newElement.setNext(tLinkable);
/* 549 */       current.setNext((TLinkable)newElement);
/* 550 */       tLinkable.setPrevious((TLinkable)newElement);
/* 551 */       this._size++;
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean forEachValue(TObjectProcedure<T> procedure) {
/* 565 */     T node = this._head;
/* 566 */     while (node != null) {
/* 567 */       boolean keep_going = procedure.execute(node);
/* 568 */       if (!keep_going)
/* 569 */         return false; 
/* 572 */       TLinkable tLinkable = node.getNext();
/*     */     } 
/* 575 */     return true;
/*     */   }
/*     */   
/*     */   public void writeExternal(ObjectOutput out) throws IOException {
/* 581 */     out.writeByte(0);
/* 584 */     out.writeInt(this._size);
/* 587 */     out.writeObject(this._head);
/* 590 */     out.writeObject(this._tail);
/*     */   }
/*     */   
/*     */   public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
/* 599 */     in.readByte();
/* 602 */     this._size = in.readInt();
/* 605 */     this._head = (T)in.readObject();
/* 608 */     this._tail = (T)in.readObject();
/*     */   }
/*     */   
/*     */   protected final class IteratorImpl implements ListIterator<T> {
/* 615 */     private int _nextIndex = 0;
/*     */     
/*     */     private T _next;
/*     */     
/*     */     private T _lastReturned;
/*     */     
/*     */     IteratorImpl(int position) {
/* 628 */       if (position < 0 || position > TLinkedList.this._size)
/* 629 */         throw new IndexOutOfBoundsException(); 
/* 632 */       this._nextIndex = position;
/* 633 */       if (position == 0) {
/* 634 */         this._next = TLinkedList.this._head;
/* 635 */       } else if (position == TLinkedList.this._size) {
/* 636 */         this._next = null;
/* 637 */       } else if (position < TLinkedList.this._size >> 1) {
/* 638 */         int pos = 0;
/* 639 */         for (this._next = TLinkedList.this._head; pos < position; pos++)
/* 640 */           this._next = (T)this._next.getNext(); 
/*     */       } else {
/* 643 */         int pos = TLinkedList.this._size - 1;
/* 644 */         for (this._next = TLinkedList.this._tail; pos > position; pos--)
/* 645 */           this._next = (T)this._next.getPrevious(); 
/*     */       } 
/*     */     }
/*     */     
/*     */     public final void add(T linkable) {
/* 658 */       this._lastReturned = null;
/* 659 */       this._nextIndex++;
/* 661 */       if (TLinkedList.this._size == 0) {
/* 662 */         TLinkedList.this.add(linkable);
/*     */       } else {
/* 664 */         TLinkedList.this.addBefore(this._next, linkable);
/*     */       } 
/*     */     }
/*     */     
/*     */     public final boolean hasNext() {
/* 675 */       return (this._nextIndex != TLinkedList.this._size);
/*     */     }
/*     */     
/*     */     public final boolean hasPrevious() {
/* 685 */       return (this._nextIndex != 0);
/*     */     }
/*     */     
/*     */     public final T next() {
/* 698 */       if (this._nextIndex == TLinkedList.this._size)
/* 699 */         throw new NoSuchElementException(); 
/* 702 */       this._lastReturned = this._next;
/* 703 */       this._next = (T)this._next.getNext();
/* 704 */       this._nextIndex++;
/* 705 */       return this._lastReturned;
/*     */     }
/*     */     
/*     */     public final int nextIndex() {
/* 716 */       return this._nextIndex;
/*     */     }
/*     */     
/*     */     public final T previous() {
/* 729 */       if (this._nextIndex == 0)
/* 730 */         throw new NoSuchElementException(); 
/* 733 */       if (this._nextIndex == TLinkedList.this._size) {
/* 734 */         this._lastReturned = this._next = TLinkedList.this._tail;
/*     */       } else {
/* 736 */         this._lastReturned = this._next = (T)this._next.getPrevious();
/*     */       } 
/* 739 */       this._nextIndex--;
/* 740 */       return this._lastReturned;
/*     */     }
/*     */     
/*     */     public final int previousIndex() {
/* 750 */       return this._nextIndex - 1;
/*     */     }
/*     */     
/*     */     public final void remove() {
/* 764 */       if (this._lastReturned == null)
/* 765 */         throw new IllegalStateException("must invoke next or previous before invoking remove"); 
/* 768 */       if (this._lastReturned != this._next)
/* 769 */         this._nextIndex--; 
/* 771 */       this._next = (T)this._lastReturned.getNext();
/* 772 */       TLinkedList.this.remove(this._lastReturned);
/* 773 */       this._lastReturned = null;
/*     */     }
/*     */     
/*     */     public final void set(T linkable) {
/* 784 */       if (this._lastReturned == null)
/* 785 */         throw new IllegalStateException(); 
/* 788 */       swap(this._lastReturned, linkable);
/* 789 */       this._lastReturned = linkable;
/*     */     }
/*     */     
/*     */     private void swap(T from, T to) {
/* 800 */       TLinkable tLinkable1 = from.getPrevious();
/* 801 */       TLinkable tLinkable2 = from.getNext();
/* 803 */       TLinkable tLinkable3 = to.getPrevious();
/* 804 */       TLinkable tLinkable4 = to.getNext();
/* 807 */       if (tLinkable2 == to) {
/* 808 */         if (tLinkable1 != null)
/* 808 */           tLinkable1.setNext((TLinkable)to); 
/* 809 */         to.setPrevious(tLinkable1);
/* 810 */         to.setNext((TLinkable)from);
/* 811 */         from.setPrevious((TLinkable)to);
/* 812 */         from.setNext(tLinkable4);
/* 813 */         if (tLinkable4 != null)
/* 813 */           tLinkable4.setPrevious((TLinkable)from); 
/* 816 */       } else if (tLinkable4 == from) {
/* 817 */         if (tLinkable3 != null)
/* 817 */           tLinkable3.setNext((TLinkable)to); 
/* 818 */         to.setPrevious((TLinkable)from);
/* 819 */         to.setNext(tLinkable2);
/* 820 */         from.setPrevious(tLinkable3);
/* 821 */         from.setNext((TLinkable)to);
/* 822 */         if (tLinkable2 != null)
/* 822 */           tLinkable2.setPrevious((TLinkable)to); 
/*     */       } else {
/* 825 */         from.setNext(tLinkable4);
/* 826 */         from.setPrevious(tLinkable3);
/* 827 */         if (tLinkable3 != null)
/* 827 */           tLinkable3.setNext((TLinkable)from); 
/* 828 */         if (tLinkable4 != null)
/* 828 */           tLinkable4.setPrevious((TLinkable)from); 
/* 830 */         to.setNext(tLinkable2);
/* 831 */         to.setPrevious(tLinkable1);
/* 832 */         if (tLinkable1 != null)
/* 832 */           tLinkable1.setNext((TLinkable)to); 
/* 833 */         if (tLinkable2 != null)
/* 833 */           tLinkable2.setPrevious((TLinkable)to); 
/*     */       } 
/* 836 */       if (TLinkedList.this._head == from) {
/* 836 */         TLinkedList.this._head = to;
/* 837 */       } else if (TLinkedList.this._head == to) {
/* 837 */         TLinkedList.this._head = from;
/*     */       } 
/* 839 */       if (TLinkedList.this._tail == from) {
/* 839 */         TLinkedList.this._tail = to;
/* 840 */       } else if (TLinkedList.this._tail == to) {
/* 840 */         TLinkedList.this._tail = from;
/*     */       } 
/* 842 */       if (this._lastReturned == from) {
/* 842 */         this._lastReturned = to;
/* 843 */       } else if (this._lastReturned == to) {
/* 843 */         this._lastReturned = from;
/*     */       } 
/* 845 */       if (this._next == from) {
/* 845 */         this._next = to;
/* 846 */       } else if (this._next == to) {
/* 846 */         this._next = from;
/*     */       } 
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\gnu\trove\list\linked\TLinkedList.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */