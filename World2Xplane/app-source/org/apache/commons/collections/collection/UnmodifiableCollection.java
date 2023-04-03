/*    */ package org.apache.commons.collections.collection;
/*    */ 
/*    */ import java.util.Collection;
/*    */ import java.util.Iterator;
/*    */ import org.apache.commons.collections.Unmodifiable;
/*    */ import org.apache.commons.collections.iterators.UnmodifiableIterator;
/*    */ 
/*    */ public final class UnmodifiableCollection extends AbstractSerializableCollectionDecorator implements Unmodifiable {
/*    */   private static final long serialVersionUID = -239892006883819945L;
/*    */   
/*    */   public static Collection decorate(Collection coll) {
/* 52 */     if (coll instanceof Unmodifiable)
/* 53 */       return coll; 
/* 55 */     return new UnmodifiableCollection(coll);
/*    */   }
/*    */   
/*    */   private UnmodifiableCollection(Collection coll) {
/* 66 */     super(coll);
/*    */   }
/*    */   
/*    */   public Iterator iterator() {
/* 71 */     return UnmodifiableIterator.decorate(getCollection().iterator());
/*    */   }
/*    */   
/*    */   public boolean add(Object object) {
/* 75 */     throw new UnsupportedOperationException();
/*    */   }
/*    */   
/*    */   public boolean addAll(Collection coll) {
/* 79 */     throw new UnsupportedOperationException();
/*    */   }
/*    */   
/*    */   public void clear() {
/* 83 */     throw new UnsupportedOperationException();
/*    */   }
/*    */   
/*    */   public boolean remove(Object object) {
/* 87 */     throw new UnsupportedOperationException();
/*    */   }
/*    */   
/*    */   public boolean removeAll(Collection coll) {
/* 91 */     throw new UnsupportedOperationException();
/*    */   }
/*    */   
/*    */   public boolean retainAll(Collection coll) {
/* 95 */     throw new UnsupportedOperationException();
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\collections\collection\UnmodifiableCollection.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */