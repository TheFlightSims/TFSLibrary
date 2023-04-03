/*    */ package org.apache.commons.collections.set;
/*    */ 
/*    */ import java.util.Collection;
/*    */ import java.util.Iterator;
/*    */ import java.util.Set;
/*    */ import org.apache.commons.collections.Unmodifiable;
/*    */ import org.apache.commons.collections.iterators.UnmodifiableIterator;
/*    */ 
/*    */ public final class UnmodifiableSet extends AbstractSerializableSetDecorator implements Unmodifiable {
/*    */   private static final long serialVersionUID = 6499119872185240161L;
/*    */   
/*    */   public static Set decorate(Set set) {
/* 50 */     if (set instanceof Unmodifiable)
/* 51 */       return set; 
/* 53 */     return new UnmodifiableSet(set);
/*    */   }
/*    */   
/*    */   private UnmodifiableSet(Set set) {
/* 64 */     super(set);
/*    */   }
/*    */   
/*    */   public Iterator iterator() {
/* 69 */     return UnmodifiableIterator.decorate(getCollection().iterator());
/*    */   }
/*    */   
/*    */   public boolean add(Object object) {
/* 73 */     throw new UnsupportedOperationException();
/*    */   }
/*    */   
/*    */   public boolean addAll(Collection coll) {
/* 77 */     throw new UnsupportedOperationException();
/*    */   }
/*    */   
/*    */   public void clear() {
/* 81 */     throw new UnsupportedOperationException();
/*    */   }
/*    */   
/*    */   public boolean remove(Object object) {
/* 85 */     throw new UnsupportedOperationException();
/*    */   }
/*    */   
/*    */   public boolean removeAll(Collection coll) {
/* 89 */     throw new UnsupportedOperationException();
/*    */   }
/*    */   
/*    */   public boolean retainAll(Collection coll) {
/* 93 */     throw new UnsupportedOperationException();
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\collections\set\UnmodifiableSet.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */