/*     */ package org.apache.commons.collections.list;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.ObjectOutputStream;
/*     */ import java.io.Serializable;
/*     */ import java.lang.ref.WeakReference;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.ConcurrentModificationException;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.ListIterator;
/*     */ 
/*     */ public class CursorableLinkedList extends AbstractLinkedList implements Serializable {
/*     */   private static final long serialVersionUID = 8836393098519411393L;
/*     */   
/*  69 */   protected transient List cursors = new ArrayList();
/*     */   
/*     */   public CursorableLinkedList() {
/*  77 */     init();
/*     */   }
/*     */   
/*     */   public CursorableLinkedList(Collection coll) {
/*  86 */     super(coll);
/*     */   }
/*     */   
/*     */   protected void init() {
/*  94 */     super.init();
/*  95 */     this.cursors = new ArrayList();
/*     */   }
/*     */   
/*     */   public Iterator iterator() {
/* 109 */     return super.listIterator(0);
/*     */   }
/*     */   
/*     */   public ListIterator listIterator() {
/* 128 */     return cursor(0);
/*     */   }
/*     */   
/*     */   public ListIterator listIterator(int fromIndex) {
/* 148 */     return cursor(fromIndex);
/*     */   }
/*     */   
/*     */   public Cursor cursor() {
/* 175 */     return cursor(0);
/*     */   }
/*     */   
/*     */   public Cursor cursor(int fromIndex) {
/* 206 */     Cursor cursor = new Cursor(this, fromIndex);
/* 207 */     registerCursor(cursor);
/* 208 */     return cursor;
/*     */   }
/*     */   
/*     */   protected void updateNode(AbstractLinkedList.Node node, Object value) {
/* 221 */     super.updateNode(node, value);
/* 222 */     broadcastNodeChanged(node);
/*     */   }
/*     */   
/*     */   protected void addNode(AbstractLinkedList.Node nodeToInsert, AbstractLinkedList.Node insertBeforeNode) {
/* 233 */     super.addNode(nodeToInsert, insertBeforeNode);
/* 234 */     broadcastNodeInserted(nodeToInsert);
/*     */   }
/*     */   
/*     */   protected void removeNode(AbstractLinkedList.Node node) {
/* 244 */     super.removeNode(node);
/* 245 */     broadcastNodeRemoved(node);
/*     */   }
/*     */   
/*     */   protected void removeAllNodes() {
/* 252 */     if (size() > 0) {
/* 254 */       Iterator it = iterator();
/* 255 */       while (it.hasNext()) {
/* 256 */         it.next();
/* 257 */         it.remove();
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   protected void registerCursor(Cursor cursor) {
/* 271 */     for (Iterator it = this.cursors.iterator(); it.hasNext(); ) {
/* 272 */       WeakReference ref = it.next();
/* 273 */       if (ref.get() == null)
/* 274 */         it.remove(); 
/*     */     } 
/* 277 */     this.cursors.add(new WeakReference(cursor));
/*     */   }
/*     */   
/*     */   protected void unregisterCursor(Cursor cursor) {
/* 286 */     for (Iterator it = this.cursors.iterator(); it.hasNext(); ) {
/* 287 */       WeakReference ref = it.next();
/* 288 */       Cursor cur = ref.get();
/* 289 */       if (cur == null) {
/* 293 */         it.remove();
/*     */         continue;
/*     */       } 
/* 295 */       if (cur == cursor) {
/* 296 */         ref.clear();
/* 297 */         it.remove();
/*     */         break;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   protected void broadcastNodeChanged(AbstractLinkedList.Node node) {
/* 311 */     Iterator it = this.cursors.iterator();
/* 312 */     while (it.hasNext()) {
/* 313 */       WeakReference ref = it.next();
/* 314 */       Cursor cursor = ref.get();
/* 315 */       if (cursor == null) {
/* 316 */         it.remove();
/*     */         continue;
/*     */       } 
/* 318 */       cursor.nodeChanged(node);
/*     */     } 
/*     */   }
/*     */   
/*     */   protected void broadcastNodeRemoved(AbstractLinkedList.Node node) {
/* 330 */     Iterator it = this.cursors.iterator();
/* 331 */     while (it.hasNext()) {
/* 332 */       WeakReference ref = it.next();
/* 333 */       Cursor cursor = ref.get();
/* 334 */       if (cursor == null) {
/* 335 */         it.remove();
/*     */         continue;
/*     */       } 
/* 337 */       cursor.nodeRemoved(node);
/*     */     } 
/*     */   }
/*     */   
/*     */   protected void broadcastNodeInserted(AbstractLinkedList.Node node) {
/* 349 */     Iterator it = this.cursors.iterator();
/* 350 */     while (it.hasNext()) {
/* 351 */       WeakReference ref = it.next();
/* 352 */       Cursor cursor = ref.get();
/* 353 */       if (cursor == null) {
/* 354 */         it.remove();
/*     */         continue;
/*     */       } 
/* 356 */       cursor.nodeInserted(node);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void writeObject(ObjectOutputStream out) throws IOException {
/* 366 */     out.defaultWriteObject();
/* 367 */     doWriteObject(out);
/*     */   }
/*     */   
/*     */   private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
/* 374 */     in.defaultReadObject();
/* 375 */     doReadObject(in);
/*     */   }
/*     */   
/*     */   protected ListIterator createSubListListIterator(AbstractLinkedList.LinkedSubList subList, int fromIndex) {
/* 386 */     SubCursor cursor = new SubCursor(subList, fromIndex);
/* 387 */     registerCursor(cursor);
/* 388 */     return cursor;
/*     */   }
/*     */   
/*     */   public static class Cursor extends AbstractLinkedList.LinkedListIterator {
/*     */     boolean valid = true;
/*     */     
/*     */     boolean nextIndexValid = true;
/*     */     
/*     */     boolean currentRemovedByAnother = false;
/*     */     
/*     */     protected Cursor(CursorableLinkedList parent, int index) {
/* 410 */       super(parent, index);
/* 411 */       this.valid = true;
/*     */     }
/*     */     
/*     */     public void remove() {
/* 428 */       if (this.current != null || !this.currentRemovedByAnother) {
/* 434 */         checkModCount();
/* 435 */         this.parent.removeNode(getLastNodeReturned());
/*     */       } 
/* 437 */       this.currentRemovedByAnother = false;
/*     */     }
/*     */     
/*     */     public void add(Object obj) {
/* 448 */       super.add(obj);
/* 451 */       this.next = this.next.next;
/*     */     }
/*     */     
/*     */     public int nextIndex() {
/* 465 */       if (!this.nextIndexValid) {
/* 466 */         if (this.next == this.parent.header) {
/* 467 */           this.nextIndex = this.parent.size();
/*     */         } else {
/* 469 */           int pos = 0;
/* 470 */           AbstractLinkedList.Node temp = this.parent.header.next;
/* 471 */           while (temp != this.next) {
/* 472 */             pos++;
/* 473 */             temp = temp.next;
/*     */           } 
/* 475 */           this.nextIndex = pos;
/*     */         } 
/* 477 */         this.nextIndexValid = true;
/*     */       } 
/* 479 */       return this.nextIndex;
/*     */     }
/*     */     
/*     */     protected void nodeChanged(AbstractLinkedList.Node node) {}
/*     */     
/*     */     protected void nodeRemoved(AbstractLinkedList.Node node) {
/* 497 */       if (node == this.next && node == this.current) {
/* 499 */         this.next = node.next;
/* 500 */         this.current = null;
/* 501 */         this.currentRemovedByAnother = true;
/* 502 */       } else if (node == this.next) {
/* 505 */         this.next = node.next;
/* 506 */         this.currentRemovedByAnother = false;
/* 507 */       } else if (node == this.current) {
/* 510 */         this.current = null;
/* 511 */         this.currentRemovedByAnother = true;
/* 512 */         this.nextIndex--;
/*     */       } else {
/* 514 */         this.nextIndexValid = false;
/* 515 */         this.currentRemovedByAnother = false;
/*     */       } 
/*     */     }
/*     */     
/*     */     protected void nodeInserted(AbstractLinkedList.Node node) {
/* 525 */       if (node.previous == this.current) {
/* 526 */         this.next = node;
/* 527 */       } else if (this.next.previous == node) {
/* 528 */         this.next = node;
/*     */       } else {
/* 530 */         this.nextIndexValid = false;
/*     */       } 
/*     */     }
/*     */     
/*     */     protected void checkModCount() {
/* 538 */       if (!this.valid)
/* 539 */         throw new ConcurrentModificationException("Cursor closed"); 
/*     */     }
/*     */     
/*     */     public void close() {
/* 552 */       if (this.valid) {
/* 553 */         ((CursorableLinkedList)this.parent).unregisterCursor(this);
/* 554 */         this.valid = false;
/*     */       } 
/*     */     }
/*     */   }
/*     */   
/*     */   protected static class SubCursor extends Cursor {
/*     */     protected final AbstractLinkedList.LinkedSubList sub;
/*     */     
/*     */     protected SubCursor(AbstractLinkedList.LinkedSubList sub, int index) {
/* 576 */       super((CursorableLinkedList)sub.parent, index + sub.offset);
/* 577 */       this.sub = sub;
/*     */     }
/*     */     
/*     */     public boolean hasNext() {
/* 581 */       return (nextIndex() < this.sub.size);
/*     */     }
/*     */     
/*     */     public boolean hasPrevious() {
/* 585 */       return (previousIndex() >= 0);
/*     */     }
/*     */     
/*     */     public int nextIndex() {
/* 589 */       return super.nextIndex() - this.sub.offset;
/*     */     }
/*     */     
/*     */     public void add(Object obj) {
/* 593 */       super.add(obj);
/* 594 */       this.sub.expectedModCount = this.parent.modCount;
/* 595 */       this.sub.size++;
/*     */     }
/*     */     
/*     */     public void remove() {
/* 599 */       super.remove();
/* 600 */       this.sub.expectedModCount = this.parent.modCount;
/* 601 */       this.sub.size--;
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\collections\list\CursorableLinkedList.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */