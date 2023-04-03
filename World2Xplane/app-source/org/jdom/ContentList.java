/*     */ package org.jdom;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.AbstractList;
/*     */ import java.util.Collection;
/*     */ import java.util.ConcurrentModificationException;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.ListIterator;
/*     */ import java.util.NoSuchElementException;
/*     */ import org.jdom.filter.Filter;
/*     */ 
/*     */ final class ContentList extends AbstractList implements Serializable {
/*     */   private static final String CVS_ID = "@(#) $RCSfile: ContentList.java,v $ $Revision: 1.42 $ $Date: 2007/11/10 05:28:58 $ $Name:  $";
/*     */   
/*     */   private static final long serialVersionUID = 1L;
/*     */   
/*     */   private static final int INITIAL_ARRAY_SIZE = 5;
/*     */   
/*     */   private Content[] elementData;
/*     */   
/*     */   private int size;
/*     */   
/*     */   private Parent parent;
/*     */   
/*     */   ContentList(Parent parent) {
/*  98 */     this.parent = parent;
/*     */   }
/*     */   
/*     */   final void uncheckedAddContent(Content c) {
/* 108 */     c.parent = this.parent;
/* 109 */     ensureCapacity(this.size + 1);
/* 110 */     this.elementData[this.size++] = c;
/* 111 */     this.modCount++;
/*     */   }
/*     */   
/*     */   public void add(int index, Object obj) {
/* 124 */     if (obj == null)
/* 125 */       throw new IllegalAddException("Cannot add null object"); 
/* 127 */     if (obj instanceof String)
/* 128 */       obj = new Text(obj.toString()); 
/* 130 */     if (obj instanceof Content) {
/* 131 */       add(index, (Content)obj);
/*     */     } else {
/* 133 */       throw new IllegalAddException("Class " + obj.getClass().getName() + " is of unrecognized type and cannot be added");
/*     */     } 
/*     */   }
/*     */   
/*     */   private void documentCanContain(int index, Content child) throws IllegalAddException {
/* 143 */     if (child instanceof Element) {
/* 144 */       if (indexOfFirstElement() >= 0)
/* 145 */         throw new IllegalAddException("Cannot add a second root element, only one is allowed"); 
/* 148 */       if (indexOfDocType() >= index)
/* 149 */         throw new IllegalAddException("A root element cannot be added before the DocType"); 
/*     */     } 
/* 153 */     if (child instanceof DocType) {
/* 154 */       if (indexOfDocType() >= 0)
/* 155 */         throw new IllegalAddException("Cannot add a second doctype, only one is allowed"); 
/* 158 */       int firstElt = indexOfFirstElement();
/* 159 */       if (firstElt != -1 && firstElt < index)
/* 160 */         throw new IllegalAddException("A DocType cannot be added after the root element"); 
/*     */     } 
/* 164 */     if (child instanceof CDATA)
/* 165 */       throw new IllegalAddException("A CDATA is not allowed at the document root"); 
/* 168 */     if (child instanceof Text)
/* 169 */       throw new IllegalAddException("A Text is not allowed at the document root"); 
/* 172 */     if (child instanceof EntityRef)
/* 173 */       throw new IllegalAddException("An EntityRef is not allowed at the document root"); 
/*     */   }
/*     */   
/*     */   private static void elementCanContain(int index, Content child) throws IllegalAddException {
/* 178 */     if (child instanceof DocType)
/* 179 */       throw new IllegalAddException("A DocType is not allowed except at the document level"); 
/*     */   }
/*     */   
/*     */   void add(int index, Content child) {
/* 192 */     if (child == null)
/* 193 */       throw new IllegalAddException("Cannot add null object"); 
/* 195 */     if (this.parent instanceof Document) {
/* 196 */       documentCanContain(index, child);
/*     */     } else {
/* 199 */       elementCanContain(index, child);
/*     */     } 
/* 202 */     if (child.getParent() != null) {
/* 203 */       Parent p = child.getParent();
/* 204 */       if (p instanceof Document)
/* 205 */         throw new IllegalAddException((Element)child, "The Content already has an existing parent document"); 
/* 209 */       throw new IllegalAddException("The Content already has an existing parent \"" + ((Element)p).getQualifiedName() + "\"");
/*     */     } 
/* 215 */     if (child == this.parent)
/* 216 */       throw new IllegalAddException("The Element cannot be added to itself"); 
/* 221 */     if (this.parent instanceof Element && child instanceof Element && ((Element)child).isAncestor((Element)this.parent))
/* 223 */       throw new IllegalAddException("The Element cannot be added as a descendent of itself"); 
/* 227 */     if (index < 0 || index > this.size)
/* 228 */       throw new IndexOutOfBoundsException("Index: " + index + " Size: " + size()); 
/* 232 */     child.setParent(this.parent);
/* 234 */     ensureCapacity(this.size + 1);
/* 235 */     if (index == this.size) {
/* 236 */       this.elementData[this.size++] = child;
/*     */     } else {
/* 238 */       System.arraycopy(this.elementData, index, this.elementData, index + 1, this.size - index);
/* 239 */       this.elementData[index] = child;
/* 240 */       this.size++;
/*     */     } 
/* 242 */     this.modCount++;
/*     */   }
/*     */   
/*     */   public boolean addAll(Collection collection) {
/* 253 */     return addAll(size(), collection);
/*     */   }
/*     */   
/*     */   public boolean addAll(int index, Collection collection) {
/* 268 */     if (index < 0 || index > this.size)
/* 269 */       throw new IndexOutOfBoundsException("Index: " + index + " Size: " + size()); 
/* 273 */     if (collection == null || collection.size() == 0)
/* 274 */       return false; 
/* 276 */     ensureCapacity(size() + collection.size());
/* 278 */     int count = 0;
/*     */     try {
/* 280 */       Iterator i = collection.iterator();
/* 281 */       while (i.hasNext()) {
/* 282 */         Object obj = i.next();
/* 283 */         add(index + count, obj);
/* 284 */         count++;
/*     */       } 
/* 287 */     } catch (RuntimeException exception) {
/* 288 */       for (int i = 0; i < count; i++)
/* 289 */         remove(index); 
/* 291 */       throw exception;
/*     */     } 
/* 294 */     return true;
/*     */   }
/*     */   
/*     */   public void clear() {
/* 301 */     if (this.elementData != null) {
/* 302 */       for (int i = 0; i < this.size; i++) {
/* 303 */         Content obj = this.elementData[i];
/* 304 */         removeParent(obj);
/*     */       } 
/* 306 */       this.elementData = null;
/* 307 */       this.size = 0;
/*     */     } 
/* 309 */     this.modCount++;
/*     */   }
/*     */   
/*     */   void clearAndSet(Collection collection) {
/* 320 */     Content[] old = this.elementData;
/* 321 */     int oldSize = this.size;
/* 323 */     this.elementData = null;
/* 324 */     this.size = 0;
/* 326 */     if (collection != null && collection.size() != 0) {
/* 327 */       ensureCapacity(collection.size());
/*     */       try {
/* 329 */         addAll(0, collection);
/* 331 */       } catch (RuntimeException exception) {
/* 332 */         this.elementData = old;
/* 333 */         this.size = oldSize;
/* 334 */         throw exception;
/*     */       } 
/*     */     } 
/* 338 */     if (old != null)
/* 339 */       for (int i = 0; i < oldSize; i++)
/* 340 */         removeParent(old[i]);  
/* 343 */     this.modCount++;
/*     */   }
/*     */   
/*     */   void ensureCapacity(int minCapacity) {
/* 354 */     if (this.elementData == null) {
/* 355 */       this.elementData = new Content[Math.max(minCapacity, 5)];
/*     */     } else {
/* 357 */       int oldCapacity = this.elementData.length;
/* 358 */       if (minCapacity > oldCapacity) {
/* 359 */         Content[] arrayOfContent = this.elementData;
/* 360 */         int newCapacity = oldCapacity * 3 / 2 + 1;
/* 361 */         if (newCapacity < minCapacity)
/* 362 */           newCapacity = minCapacity; 
/* 363 */         this.elementData = new Content[newCapacity];
/* 364 */         System.arraycopy(arrayOfContent, 0, this.elementData, 0, this.size);
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public Object get(int index) {
/* 376 */     if (index < 0 || index >= this.size)
/* 377 */       throw new IndexOutOfBoundsException("Index: " + index + " Size: " + size()); 
/* 380 */     return this.elementData[index];
/*     */   }
/*     */   
/*     */   List getView(Filter filter) {
/* 390 */     return new FilterList(this, filter);
/*     */   }
/*     */   
/*     */   int indexOfFirstElement() {
/* 401 */     if (this.elementData != null)
/* 402 */       for (int i = 0; i < this.size; i++) {
/* 403 */         if (this.elementData[i] instanceof Element)
/* 404 */           return i; 
/*     */       }  
/* 408 */     return -1;
/*     */   }
/*     */   
/*     */   int indexOfDocType() {
/* 419 */     if (this.elementData != null)
/* 420 */       for (int i = 0; i < this.size; i++) {
/* 421 */         if (this.elementData[i] instanceof DocType)
/* 422 */           return i; 
/*     */       }  
/* 426 */     return -1;
/*     */   }
/*     */   
/*     */   public Object remove(int index) {
/* 436 */     if (index < 0 || index >= this.size)
/* 437 */       throw new IndexOutOfBoundsException("Index: " + index + " Size: " + size()); 
/* 440 */     Content old = this.elementData[index];
/* 441 */     removeParent(old);
/* 442 */     int numMoved = this.size - index - 1;
/* 443 */     if (numMoved > 0)
/* 444 */       System.arraycopy(this.elementData, index + 1, this.elementData, index, numMoved); 
/* 445 */     this.elementData[--this.size] = null;
/* 446 */     this.modCount++;
/* 447 */     return old;
/*     */   }
/*     */   
/*     */   private static void removeParent(Content c) {
/* 453 */     c.setParent(null);
/*     */   }
/*     */   
/*     */   public Object set(int index, Object obj) {
/* 466 */     if (index < 0 || index >= this.size)
/* 467 */       throw new IndexOutOfBoundsException("Index: " + index + " Size: " + size()); 
/* 470 */     if (obj instanceof Element && this.parent instanceof Document) {
/* 471 */       int root = indexOfFirstElement();
/* 472 */       if (root >= 0 && root != index)
/* 473 */         throw new IllegalAddException("Cannot add a second root element, only one is allowed"); 
/*     */     } 
/* 478 */     if (obj instanceof DocType && this.parent instanceof Document) {
/* 479 */       int docTypeIndex = indexOfDocType();
/* 480 */       if (docTypeIndex >= 0 && docTypeIndex != index)
/* 481 */         throw new IllegalAddException("Cannot add a second doctype, only one is allowed"); 
/*     */     } 
/* 486 */     Object old = remove(index);
/*     */     try {
/* 488 */       add(index, obj);
/* 490 */     } catch (RuntimeException exception) {
/* 491 */       add(index, old);
/* 492 */       throw exception;
/*     */     } 
/* 494 */     return old;
/*     */   }
/*     */   
/*     */   public int size() {
/* 503 */     return this.size;
/*     */   }
/*     */   
/*     */   public String toString() {
/* 512 */     return super.toString();
/*     */   }
/*     */   
/*     */   private int getModCount() {
/* 517 */     return this.modCount;
/*     */   }
/*     */   
/*     */   class FilterList extends AbstractList implements Serializable {
/*     */     Filter filter;
/*     */     
/*     */     int count;
/*     */     
/*     */     int expected;
/*     */     
/*     */     private final ContentList this$0;
/*     */     
/*     */     FilterList(ContentList this$0, Filter filter) {
/* 549 */       this.this$0 = this$0;
/*     */       this.count = 0;
/*     */       this.expected = -1;
/* 550 */       this.filter = filter;
/*     */     }
/*     */     
/*     */     public void add(int index, Object obj) {
/* 563 */       if (this.filter.matches(obj)) {
/* 564 */         int adjusted = getAdjustedIndex(index);
/* 565 */         this.this$0.add(adjusted, obj);
/* 566 */         this.expected++;
/* 567 */         this.count++;
/*     */       } else {
/* 569 */         throw new IllegalAddException("Filter won't allow the " + obj.getClass().getName() + " '" + obj + "' to be added to the list");
/*     */       } 
/*     */     }
/*     */     
/*     */     public Object get(int index) {
/* 581 */       int adjusted = getAdjustedIndex(index);
/* 582 */       return this.this$0.get(adjusted);
/*     */     }
/*     */     
/*     */     public Iterator iterator() {
/* 586 */       return new ContentList.FilterListIterator(this.this$0, this.filter, 0);
/*     */     }
/*     */     
/*     */     public ListIterator listIterator() {
/* 590 */       return new ContentList.FilterListIterator(this.this$0, this.filter, 0);
/*     */     }
/*     */     
/*     */     public ListIterator listIterator(int index) {
/* 594 */       return new ContentList.FilterListIterator(this.this$0, this.filter, index);
/*     */     }
/*     */     
/*     */     public Object remove(int index) {
/* 604 */       int adjusted = getAdjustedIndex(index);
/* 605 */       Object old = this.this$0.get(adjusted);
/* 606 */       if (this.filter.matches(old)) {
/* 607 */         old = this.this$0.remove(adjusted);
/* 608 */         this.expected++;
/* 609 */         this.count--;
/*     */       } else {
/* 612 */         throw new IllegalAddException("Filter won't allow the " + old.getClass().getName() + " '" + old + "' (index " + index + ") to be removed");
/*     */       } 
/* 617 */       return old;
/*     */     }
/*     */     
/*     */     public Object set(int index, Object obj) {
/* 630 */       Object old = null;
/* 631 */       if (this.filter.matches(obj)) {
/* 632 */         int adjusted = getAdjustedIndex(index);
/* 633 */         old = this.this$0.get(adjusted);
/* 634 */         if (!this.filter.matches(old))
/* 635 */           throw new IllegalAddException("Filter won't allow the " + old.getClass().getName() + " '" + old + "' (index " + index + ") to be removed"); 
/* 640 */         old = this.this$0.set(adjusted, obj);
/* 641 */         this.expected += 2;
/*     */       } else {
/* 644 */         throw new IllegalAddException("Filter won't allow index " + index + " to be set to " + obj.getClass().getName());
/*     */       } 
/* 648 */       return old;
/*     */     }
/*     */     
/*     */     public int size() {
/* 664 */       if (this.expected == this.this$0.getModCount())
/* 665 */         return this.count; 
/* 668 */       this.count = 0;
/* 669 */       for (int i = 0; i < this.this$0.size(); i++) {
/* 670 */         Object obj = this.this$0.elementData[i];
/* 671 */         if (this.filter.matches(obj))
/* 672 */           this.count++; 
/*     */       } 
/* 675 */       this.expected = this.this$0.getModCount();
/* 676 */       return this.count;
/*     */     }
/*     */     
/*     */     private final int getAdjustedIndex(int index) {
/* 686 */       int adjusted = 0;
/* 687 */       for (int i = 0; i < this.this$0.size; i++) {
/* 688 */         Object obj = this.this$0.elementData[i];
/* 689 */         if (this.filter.matches(obj)) {
/* 690 */           if (index == adjusted)
/* 691 */             return i; 
/* 693 */           adjusted++;
/*     */         } 
/*     */       } 
/* 697 */       if (index == adjusted)
/* 698 */         return this.this$0.size; 
/* 701 */       return this.this$0.size + 1;
/*     */     }
/*     */   }
/*     */   
/*     */   class FilterListIterator implements ListIterator {
/*     */     Filter filter;
/*     */     
/*     */     private boolean forward;
/*     */     
/*     */     private boolean canremove;
/*     */     
/*     */     private boolean canset;
/*     */     
/*     */     private int cursor;
/*     */     
/*     */     private int tmpcursor;
/*     */     
/*     */     private int index;
/*     */     
/*     */     private int expected;
/*     */     
/*     */     private int fsize;
/*     */     
/*     */     private final ContentList this$0;
/*     */     
/*     */     FilterListIterator(ContentList this$0, Filter filter, int start) {
/* 736 */       this.this$0 = this$0;
/*     */       this.forward = false;
/*     */       this.canremove = false;
/*     */       this.canset = false;
/*     */       this.cursor = -1;
/*     */       this.tmpcursor = -1;
/*     */       this.index = -1;
/*     */       this.expected = -1;
/*     */       this.fsize = 0;
/* 737 */       this.filter = filter;
/* 738 */       this.expected = this$0.getModCount();
/* 741 */       this.forward = false;
/* 743 */       if (start < 0)
/* 744 */         throw new IndexOutOfBoundsException("Index: " + start); 
/* 748 */       this.fsize = 0;
/* 751 */       for (int i = 0; i < this$0.size(); i++) {
/* 752 */         if (filter.matches(this$0.get(i))) {
/* 753 */           if (start == this.fsize) {
/* 755 */             this.cursor = i;
/* 757 */             this.index = this.fsize;
/*     */           } 
/* 759 */           this.fsize++;
/*     */         } 
/*     */       } 
/* 763 */       if (start > this.fsize)
/* 764 */         throw new IndexOutOfBoundsException("Index: " + start + " Size: " + this.fsize); 
/* 766 */       if (this.cursor == -1) {
/* 775 */         this.cursor = this$0.size();
/* 776 */         this.index = this.fsize;
/*     */       } 
/*     */     }
/*     */     
/*     */     public boolean hasNext() {
/* 785 */       return (nextIndex() < this.fsize);
/*     */     }
/*     */     
/*     */     public Object next() {
/* 792 */       if (!hasNext())
/* 793 */         throw new NoSuchElementException("next() is beyond the end of the Iterator"); 
/* 794 */       this.index = nextIndex();
/* 795 */       this.cursor = this.tmpcursor;
/* 796 */       this.forward = true;
/* 797 */       this.canremove = true;
/* 798 */       this.canset = true;
/* 799 */       return this.this$0.get(this.cursor);
/*     */     }
/*     */     
/*     */     public boolean hasPrevious() {
/* 807 */       return (previousIndex() >= 0);
/*     */     }
/*     */     
/*     */     public Object previous() {
/* 814 */       if (!hasPrevious())
/* 815 */         throw new NoSuchElementException("previous() is before the start of the Iterator"); 
/* 816 */       this.index = previousIndex();
/* 817 */       this.cursor = this.tmpcursor;
/* 818 */       this.forward = false;
/* 819 */       this.canremove = true;
/* 820 */       this.canset = true;
/* 821 */       return this.this$0.get(this.cursor);
/*     */     }
/*     */     
/*     */     public int nextIndex() {
/* 829 */       checkConcurrentModification();
/* 830 */       if (this.forward) {
/* 832 */         for (int i = this.cursor + 1; i < this.this$0.size(); i++) {
/* 833 */           if (this.filter.matches(this.this$0.get(i))) {
/* 834 */             this.tmpcursor = i;
/* 835 */             return this.index + 1;
/*     */           } 
/*     */         } 
/* 840 */         this.tmpcursor = this.this$0.size();
/* 841 */         return this.index + 1;
/*     */       } 
/* 846 */       this.tmpcursor = this.cursor;
/* 847 */       return this.index;
/*     */     }
/*     */     
/*     */     public int previousIndex() {
/* 856 */       checkConcurrentModification();
/* 857 */       if (!this.forward) {
/* 859 */         for (int i = this.cursor - 1; i >= 0; i--) {
/* 860 */           if (this.filter.matches(this.this$0.get(i))) {
/* 861 */             this.tmpcursor = i;
/* 862 */             return this.index - 1;
/*     */           } 
/*     */         } 
/* 867 */         this.tmpcursor = -1;
/* 868 */         return this.index - 1;
/*     */       } 
/* 873 */       this.tmpcursor = this.cursor;
/* 874 */       return this.index;
/*     */     }
/*     */     
/*     */     public void add(Object obj) {
/* 881 */       if (!this.filter.matches(obj))
/* 882 */         throw new IllegalAddException("Filter won't allow the " + obj.getClass().getName() + " '" + obj + "' to be added to the list"); 
/* 888 */       nextIndex();
/* 891 */       this.this$0.add(this.tmpcursor, obj);
/* 892 */       this.expected = this.this$0.getModCount();
/* 893 */       this.canremove = this.canset = false;
/* 895 */       if (this.forward) {
/* 896 */         this.index++;
/*     */       } else {
/* 898 */         this.forward = true;
/*     */       } 
/* 900 */       this.fsize++;
/* 901 */       this.cursor = this.tmpcursor;
/*     */     }
/*     */     
/*     */     public void remove() {
/* 909 */       if (!this.canremove)
/* 910 */         throw new IllegalStateException("Can not remove an element unless either next() or previous() has been called since the last remove()"); 
/* 918 */       boolean dir = this.forward;
/* 919 */       this.forward = true;
/*     */       try {
/* 921 */         nextIndex();
/* 922 */         this.this$0.remove(this.cursor);
/*     */       } finally {
/* 924 */         this.forward = dir;
/*     */       } 
/* 926 */       this.cursor = this.tmpcursor - 1;
/* 927 */       this.expected = this.this$0.getModCount();
/* 929 */       this.forward = false;
/* 930 */       this.canremove = false;
/* 931 */       this.canset = false;
/* 932 */       this.fsize--;
/*     */     }
/*     */     
/*     */     public void set(Object obj) {
/* 940 */       if (!this.canset)
/* 941 */         throw new IllegalStateException("Can not set an element unless either next() or previous() has been called since the last remove() or set()"); 
/* 944 */       checkConcurrentModification();
/* 946 */       if (!this.filter.matches(obj))
/* 947 */         throw new IllegalAddException("Filter won't allow index " + this.index + " to be set to " + obj.getClass().getName()); 
/* 951 */       this.this$0.set(this.cursor, obj);
/* 952 */       this.expected = this.this$0.getModCount();
/*     */     }
/*     */     
/*     */     private void checkConcurrentModification() {
/* 960 */       if (this.expected != this.this$0.getModCount())
/* 961 */         throw new ConcurrentModificationException(); 
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jdom\ContentList.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */