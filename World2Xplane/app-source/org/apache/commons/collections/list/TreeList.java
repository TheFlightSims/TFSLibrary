/*     */ package org.apache.commons.collections.list;
/*     */ 
/*     */ import java.util.AbstractList;
/*     */ import java.util.Collection;
/*     */ import java.util.ConcurrentModificationException;
/*     */ import java.util.Iterator;
/*     */ import java.util.ListIterator;
/*     */ import java.util.NoSuchElementException;
/*     */ import org.apache.commons.collections.OrderedIterator;
/*     */ 
/*     */ public class TreeList extends AbstractList {
/*     */   private AVLNode root;
/*     */   
/*     */   private int size;
/*     */   
/*     */   public TreeList() {}
/*     */   
/*     */   public TreeList(Collection coll) {
/*  87 */     addAll(coll);
/*     */   }
/*     */   
/*     */   public Object get(int index) {
/*  98 */     checkInterval(index, 0, size() - 1);
/*  99 */     return this.root.get(index).getValue();
/*     */   }
/*     */   
/*     */   public int size() {
/* 108 */     return this.size;
/*     */   }
/*     */   
/*     */   public Iterator iterator() {
/* 118 */     return listIterator(0);
/*     */   }
/*     */   
/*     */   public ListIterator listIterator() {
/* 128 */     return listIterator(0);
/*     */   }
/*     */   
/*     */   public ListIterator listIterator(int fromIndex) {
/* 140 */     checkInterval(fromIndex, 0, size());
/* 141 */     return new TreeListIterator(this, fromIndex);
/*     */   }
/*     */   
/*     */   public int indexOf(Object object) {
/* 151 */     if (this.root == null)
/* 152 */       return -1; 
/* 154 */     return this.root.indexOf(object, this.root.relativePosition);
/*     */   }
/*     */   
/*     */   public boolean contains(Object object) {
/* 163 */     return (indexOf(object) >= 0);
/*     */   }
/*     */   
/*     */   public Object[] toArray() {
/* 173 */     Object[] array = new Object[size()];
/* 174 */     if (this.root != null)
/* 175 */       this.root.toArray(array, this.root.relativePosition); 
/* 177 */     return array;
/*     */   }
/*     */   
/*     */   public void add(int index, Object obj) {
/* 188 */     this.modCount++;
/* 189 */     checkInterval(index, 0, size());
/* 190 */     if (this.root == null) {
/* 191 */       this.root = new AVLNode(index, obj, null, null);
/*     */     } else {
/* 193 */       this.root = this.root.insert(index, obj);
/*     */     } 
/* 195 */     this.size++;
/*     */   }
/*     */   
/*     */   public Object set(int index, Object obj) {
/* 207 */     checkInterval(index, 0, size() - 1);
/* 208 */     AVLNode node = this.root.get(index);
/* 209 */     Object result = node.value;
/* 210 */     node.setValue(obj);
/* 211 */     return result;
/*     */   }
/*     */   
/*     */   public Object remove(int index) {
/* 221 */     this.modCount++;
/* 222 */     checkInterval(index, 0, size() - 1);
/* 223 */     Object result = get(index);
/* 224 */     this.root = this.root.remove(index);
/* 225 */     this.size--;
/* 226 */     return result;
/*     */   }
/*     */   
/*     */   public void clear() {
/* 233 */     this.modCount++;
/* 234 */     this.root = null;
/* 235 */     this.size = 0;
/*     */   }
/*     */   
/*     */   private void checkInterval(int index, int startIndex, int endIndex) {
/* 248 */     if (index < startIndex || index > endIndex)
/* 249 */       throw new IndexOutOfBoundsException("Invalid index:" + index + ", size=" + size()); 
/*     */   }
/*     */   
/*     */   static class AVLNode {
/*     */     private AVLNode left;
/*     */     
/*     */     private boolean leftIsPrevious;
/*     */     
/*     */     private AVLNode right;
/*     */     
/*     */     private boolean rightIsNext;
/*     */     
/*     */     private int height;
/*     */     
/*     */     private int relativePosition;
/*     */     
/*     */     private Object value;
/*     */     
/*     */     private AVLNode(int relativePosition, Object obj, AVLNode rightFollower, AVLNode leftFollower) {
/* 291 */       this.relativePosition = relativePosition;
/* 292 */       this.value = obj;
/* 293 */       this.rightIsNext = true;
/* 294 */       this.leftIsPrevious = true;
/* 295 */       this.right = rightFollower;
/* 296 */       this.left = leftFollower;
/*     */     }
/*     */     
/*     */     Object getValue() {
/* 305 */       return this.value;
/*     */     }
/*     */     
/*     */     void setValue(Object obj) {
/* 314 */       this.value = obj;
/*     */     }
/*     */     
/*     */     AVLNode get(int index) {
/* 322 */       int indexRelativeToMe = index - this.relativePosition;
/* 324 */       if (indexRelativeToMe == 0)
/* 325 */         return this; 
/* 328 */       AVLNode nextNode = (indexRelativeToMe < 0) ? getLeftSubTree() : getRightSubTree();
/* 329 */       if (nextNode == null)
/* 330 */         return null; 
/* 332 */       return nextNode.get(indexRelativeToMe);
/*     */     }
/*     */     
/*     */     int indexOf(Object object, int index) {
/* 339 */       if (getLeftSubTree() != null) {
/* 340 */         int result = this.left.indexOf(object, index + this.left.relativePosition);
/* 341 */         if (result != -1)
/* 342 */           return result; 
/*     */       } 
/* 345 */       if ((this.value == null) ? (this.value == object) : this.value.equals(object))
/* 346 */         return index; 
/* 348 */       if (getRightSubTree() != null)
/* 349 */         return this.right.indexOf(object, index + this.right.relativePosition); 
/* 351 */       return -1;
/*     */     }
/*     */     
/*     */     void toArray(Object[] array, int index) {
/* 361 */       array[index] = this.value;
/* 362 */       if (getLeftSubTree() != null)
/* 363 */         this.left.toArray(array, index + this.left.relativePosition); 
/* 365 */       if (getRightSubTree() != null)
/* 366 */         this.right.toArray(array, index + this.right.relativePosition); 
/*     */     }
/*     */     
/*     */     AVLNode next() {
/* 376 */       if (this.rightIsNext || this.right == null)
/* 377 */         return this.right; 
/* 379 */       return this.right.min();
/*     */     }
/*     */     
/*     */     AVLNode previous() {
/* 388 */       if (this.leftIsPrevious || this.left == null)
/* 389 */         return this.left; 
/* 391 */       return this.left.max();
/*     */     }
/*     */     
/*     */     AVLNode insert(int index, Object obj) {
/* 402 */       int indexRelativeToMe = index - this.relativePosition;
/* 404 */       if (indexRelativeToMe <= 0)
/* 405 */         return insertOnLeft(indexRelativeToMe, obj); 
/* 407 */       return insertOnRight(indexRelativeToMe, obj);
/*     */     }
/*     */     
/*     */     private AVLNode insertOnLeft(int indexRelativeToMe, Object obj) {
/* 412 */       AVLNode ret = this;
/* 414 */       if (getLeftSubTree() == null) {
/* 415 */         setLeft(new AVLNode(-1, obj, this, this.left), null);
/*     */       } else {
/* 417 */         setLeft(this.left.insert(indexRelativeToMe, obj), null);
/*     */       } 
/* 420 */       if (this.relativePosition >= 0)
/* 421 */         this.relativePosition++; 
/* 423 */       ret = balance();
/* 424 */       recalcHeight();
/* 425 */       return ret;
/*     */     }
/*     */     
/*     */     private AVLNode insertOnRight(int indexRelativeToMe, Object obj) {
/* 429 */       AVLNode ret = this;
/* 431 */       if (getRightSubTree() == null) {
/* 432 */         setRight(new AVLNode(1, obj, this.right, this), null);
/*     */       } else {
/* 434 */         setRight(this.right.insert(indexRelativeToMe, obj), null);
/*     */       } 
/* 436 */       if (this.relativePosition < 0)
/* 437 */         this.relativePosition--; 
/* 439 */       ret = balance();
/* 440 */       recalcHeight();
/* 441 */       return ret;
/*     */     }
/*     */     
/*     */     private AVLNode getLeftSubTree() {
/* 449 */       return this.leftIsPrevious ? null : this.left;
/*     */     }
/*     */     
/*     */     private AVLNode getRightSubTree() {
/* 456 */       return this.rightIsNext ? null : this.right;
/*     */     }
/*     */     
/*     */     private AVLNode max() {
/* 465 */       return (getRightSubTree() == null) ? this : this.right.max();
/*     */     }
/*     */     
/*     */     private AVLNode min() {
/* 474 */       return (getLeftSubTree() == null) ? this : this.left.min();
/*     */     }
/*     */     
/*     */     AVLNode remove(int index) {
/* 484 */       int indexRelativeToMe = index - this.relativePosition;
/* 486 */       if (indexRelativeToMe == 0)
/* 487 */         return removeSelf(); 
/* 489 */       if (indexRelativeToMe > 0) {
/* 490 */         setRight(this.right.remove(indexRelativeToMe), this.right.right);
/* 491 */         if (this.relativePosition < 0)
/* 492 */           this.relativePosition++; 
/*     */       } else {
/* 495 */         setLeft(this.left.remove(indexRelativeToMe), this.left.left);
/* 496 */         if (this.relativePosition > 0)
/* 497 */           this.relativePosition--; 
/*     */       } 
/* 500 */       recalcHeight();
/* 501 */       return balance();
/*     */     }
/*     */     
/*     */     private AVLNode removeMax() {
/* 505 */       if (getRightSubTree() == null)
/* 506 */         return removeSelf(); 
/* 508 */       setRight(this.right.removeMax(), this.right.right);
/* 509 */       if (this.relativePosition < 0)
/* 510 */         this.relativePosition++; 
/* 512 */       recalcHeight();
/* 513 */       return balance();
/*     */     }
/*     */     
/*     */     private AVLNode removeMin() {
/* 517 */       if (getLeftSubTree() == null)
/* 518 */         return removeSelf(); 
/* 520 */       setLeft(this.left.removeMin(), this.left.left);
/* 521 */       if (this.relativePosition > 0)
/* 522 */         this.relativePosition--; 
/* 524 */       recalcHeight();
/* 525 */       return balance();
/*     */     }
/*     */     
/*     */     private AVLNode removeSelf() {
/* 534 */       if (getRightSubTree() == null && getLeftSubTree() == null)
/* 535 */         return null; 
/* 537 */       if (getRightSubTree() == null) {
/* 538 */         if (this.relativePosition > 0)
/* 539 */           this.left.relativePosition += this.relativePosition + ((this.relativePosition > 0) ? 0 : 1); 
/* 541 */         this.left.max().setRight(null, this.right);
/* 542 */         return this.left;
/*     */       } 
/* 544 */       if (getLeftSubTree() == null) {
/* 545 */         this.right.relativePosition += this.relativePosition - ((this.relativePosition < 0) ? 0 : 1);
/* 546 */         this.right.min().setLeft(null, this.left);
/* 547 */         return this.right;
/*     */       } 
/* 550 */       if (heightRightMinusLeft() > 0) {
/* 552 */         AVLNode rightMin = this.right.min();
/* 553 */         this.value = rightMin.value;
/* 554 */         if (this.leftIsPrevious)
/* 555 */           this.left = rightMin.left; 
/* 557 */         this.right = this.right.removeMin();
/* 558 */         if (this.relativePosition < 0)
/* 559 */           this.relativePosition++; 
/*     */       } else {
/* 563 */         AVLNode leftMax = this.left.max();
/* 564 */         this.value = leftMax.value;
/* 565 */         if (this.rightIsNext)
/* 566 */           this.right = leftMax.right; 
/* 568 */         AVLNode leftPrevious = this.left.left;
/* 569 */         this.left = this.left.removeMax();
/* 570 */         if (this.left == null) {
/* 573 */           this.left = leftPrevious;
/* 574 */           this.leftIsPrevious = true;
/*     */         } 
/* 576 */         if (this.relativePosition > 0)
/* 577 */           this.relativePosition--; 
/*     */       } 
/* 580 */       recalcHeight();
/* 581 */       return this;
/*     */     }
/*     */     
/*     */     private AVLNode balance() {
/* 589 */       switch (heightRightMinusLeft()) {
/*     */         case -1:
/*     */         case 0:
/*     */         case 1:
/* 593 */           return this;
/*     */         case -2:
/* 595 */           if (this.left.heightRightMinusLeft() > 0)
/* 596 */             setLeft(this.left.rotateLeft(), null); 
/* 598 */           return rotateRight();
/*     */         case 2:
/* 600 */           if (this.right.heightRightMinusLeft() < 0)
/* 601 */             setRight(this.right.rotateRight(), null); 
/* 603 */           return rotateLeft();
/*     */       } 
/* 605 */       throw new RuntimeException("tree inconsistent!");
/*     */     }
/*     */     
/*     */     private int getOffset(AVLNode node) {
/* 613 */       if (node == null)
/* 614 */         return 0; 
/* 616 */       return node.relativePosition;
/*     */     }
/*     */     
/*     */     private int setOffset(AVLNode node, int newOffest) {
/* 623 */       if (node == null)
/* 624 */         return 0; 
/* 626 */       int oldOffset = getOffset(node);
/* 627 */       node.relativePosition = newOffest;
/* 628 */       return oldOffset;
/*     */     }
/*     */     
/*     */     private void recalcHeight() {
/* 635 */       this.height = Math.max((getLeftSubTree() == null) ? -1 : (getLeftSubTree()).height, (getRightSubTree() == null) ? -1 : (getRightSubTree()).height) + 1;
/*     */     }
/*     */     
/*     */     private int getHeight(AVLNode node) {
/* 644 */       return (node == null) ? -1 : node.height;
/*     */     }
/*     */     
/*     */     private int heightRightMinusLeft() {
/* 651 */       return getHeight(getRightSubTree()) - getHeight(getLeftSubTree());
/*     */     }
/*     */     
/*     */     private AVLNode rotateLeft() {
/* 655 */       AVLNode newTop = this.right;
/* 656 */       AVLNode movedNode = getRightSubTree().getLeftSubTree();
/* 658 */       int newTopPosition = this.relativePosition + getOffset(newTop);
/* 659 */       int myNewPosition = -newTop.relativePosition;
/* 660 */       int movedPosition = getOffset(newTop) + getOffset(movedNode);
/* 662 */       setRight(movedNode, newTop);
/* 663 */       newTop.setLeft(this, null);
/* 665 */       setOffset(newTop, newTopPosition);
/* 666 */       setOffset(this, myNewPosition);
/* 667 */       setOffset(movedNode, movedPosition);
/* 668 */       return newTop;
/*     */     }
/*     */     
/*     */     private AVLNode rotateRight() {
/* 672 */       AVLNode newTop = this.left;
/* 673 */       AVLNode movedNode = getLeftSubTree().getRightSubTree();
/* 675 */       int newTopPosition = this.relativePosition + getOffset(newTop);
/* 676 */       int myNewPosition = -newTop.relativePosition;
/* 677 */       int movedPosition = getOffset(newTop) + getOffset(movedNode);
/* 679 */       setLeft(movedNode, newTop);
/* 680 */       newTop.setRight(this, null);
/* 682 */       setOffset(newTop, newTopPosition);
/* 683 */       setOffset(this, myNewPosition);
/* 684 */       setOffset(movedNode, movedPosition);
/* 685 */       return newTop;
/*     */     }
/*     */     
/*     */     private void setLeft(AVLNode node, AVLNode previous) {
/* 695 */       this.leftIsPrevious = (node == null);
/* 696 */       this.left = this.leftIsPrevious ? previous : node;
/* 697 */       recalcHeight();
/*     */     }
/*     */     
/*     */     private void setRight(AVLNode node, AVLNode next) {
/* 707 */       this.rightIsNext = (node == null);
/* 708 */       this.right = this.rightIsNext ? next : node;
/* 709 */       recalcHeight();
/*     */     }
/*     */     
/*     */     public String toString() {
/* 768 */       return "AVLNode(" + this.relativePosition + "," + ((this.left != null) ? 1 : 0) + "," + this.value + "," + ((getRightSubTree() != null) ? 1 : 0) + ", faedelung " + this.rightIsNext + " )";
/*     */     }
/*     */   }
/*     */   
/*     */   static class TreeListIterator implements ListIterator, OrderedIterator {
/*     */     protected final TreeList parent;
/*     */     
/*     */     protected TreeList.AVLNode next;
/*     */     
/*     */     protected int nextIndex;
/*     */     
/*     */     protected TreeList.AVLNode current;
/*     */     
/*     */     protected int currentIndex;
/*     */     
/*     */     protected int expectedModCount;
/*     */     
/*     */     protected TreeListIterator(TreeList parent, int fromIndex) throws IndexOutOfBoundsException {
/* 812 */       this.parent = parent;
/* 813 */       this.expectedModCount = parent.modCount;
/* 814 */       this.next = (parent.root == null) ? null : parent.root.get(fromIndex);
/* 815 */       this.nextIndex = fromIndex;
/* 816 */       this.currentIndex = -1;
/*     */     }
/*     */     
/*     */     protected void checkModCount() {
/* 827 */       if (this.parent.modCount != this.expectedModCount)
/* 828 */         throw new ConcurrentModificationException(); 
/*     */     }
/*     */     
/*     */     public boolean hasNext() {
/* 833 */       return (this.nextIndex < this.parent.size());
/*     */     }
/*     */     
/*     */     public Object next() {
/* 837 */       checkModCount();
/* 838 */       if (!hasNext())
/* 839 */         throw new NoSuchElementException("No element at index " + this.nextIndex + "."); 
/* 841 */       if (this.next == null)
/* 842 */         this.next = this.parent.root.get(this.nextIndex); 
/* 844 */       Object value = this.next.getValue();
/* 845 */       this.current = this.next;
/* 846 */       this.currentIndex = this.nextIndex++;
/* 847 */       this.next = this.next.next();
/* 848 */       return value;
/*     */     }
/*     */     
/*     */     public boolean hasPrevious() {
/* 852 */       return (this.nextIndex > 0);
/*     */     }
/*     */     
/*     */     public Object previous() {
/* 856 */       checkModCount();
/* 857 */       if (!hasPrevious())
/* 858 */         throw new NoSuchElementException("Already at start of list."); 
/* 860 */       if (this.next == null) {
/* 861 */         this.next = this.parent.root.get(this.nextIndex - 1);
/*     */       } else {
/* 863 */         this.next = this.next.previous();
/*     */       } 
/* 865 */       Object value = this.next.getValue();
/* 866 */       this.current = this.next;
/* 867 */       this.currentIndex = --this.nextIndex;
/* 868 */       return value;
/*     */     }
/*     */     
/*     */     public int nextIndex() {
/* 872 */       return this.nextIndex;
/*     */     }
/*     */     
/*     */     public int previousIndex() {
/* 876 */       return nextIndex() - 1;
/*     */     }
/*     */     
/*     */     public void remove() {
/* 880 */       checkModCount();
/* 881 */       if (this.currentIndex == -1)
/* 882 */         throw new IllegalStateException(); 
/* 884 */       if (this.nextIndex == this.currentIndex) {
/* 886 */         this.next = this.next.next();
/* 887 */         this.parent.remove(this.currentIndex);
/*     */       } else {
/* 890 */         this.parent.remove(this.currentIndex);
/* 891 */         this.nextIndex--;
/*     */       } 
/* 893 */       this.current = null;
/* 894 */       this.currentIndex = -1;
/* 895 */       this.expectedModCount++;
/*     */     }
/*     */     
/*     */     public void set(Object obj) {
/* 899 */       checkModCount();
/* 900 */       if (this.current == null)
/* 901 */         throw new IllegalStateException(); 
/* 903 */       this.current.setValue(obj);
/*     */     }
/*     */     
/*     */     public void add(Object obj) {
/* 907 */       checkModCount();
/* 908 */       this.parent.add(this.nextIndex, obj);
/* 909 */       this.current = null;
/* 910 */       this.currentIndex = -1;
/* 911 */       this.nextIndex++;
/* 912 */       this.expectedModCount++;
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\collections\list\TreeList.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */