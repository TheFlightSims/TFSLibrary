/*     */ package org.apache.commons.collections.map;
/*     */ 
/*     */ import java.lang.reflect.Array;
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import org.apache.commons.collections.Unmodifiable;
/*     */ import org.apache.commons.collections.iterators.AbstractIteratorDecorator;
/*     */ import org.apache.commons.collections.keyvalue.AbstractMapEntryDecorator;
/*     */ import org.apache.commons.collections.set.AbstractSetDecorator;
/*     */ 
/*     */ public final class UnmodifiableEntrySet extends AbstractSetDecorator implements Unmodifiable {
/*     */   public static Set decorate(Set set) {
/*  48 */     if (set instanceof Unmodifiable)
/*  49 */       return set; 
/*  51 */     return (Set)new UnmodifiableEntrySet(set);
/*     */   }
/*     */   
/*     */   private UnmodifiableEntrySet(Set set) {
/*  62 */     super(set);
/*     */   }
/*     */   
/*     */   public boolean add(Object object) {
/*  67 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public boolean addAll(Collection coll) {
/*  71 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public void clear() {
/*  75 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public boolean remove(Object object) {
/*  79 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public boolean removeAll(Collection coll) {
/*  83 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public boolean retainAll(Collection coll) {
/*  87 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public Iterator iterator() {
/*  92 */     return (Iterator)new UnmodifiableEntrySetIterator(this.collection.iterator());
/*     */   }
/*     */   
/*     */   public Object[] toArray() {
/*  96 */     Object[] array = this.collection.toArray();
/*  97 */     for (int i = 0; i < array.length; i++)
/*  98 */       array[i] = new UnmodifiableEntry((Map.Entry)array[i]); 
/* 100 */     return array;
/*     */   }
/*     */   
/*     */   public Object[] toArray(Object[] array) {
/* 104 */     Object[] result = array;
/* 105 */     if (array.length > 0)
/* 108 */       result = (Object[])Array.newInstance(array.getClass().getComponentType(), 0); 
/* 110 */     result = this.collection.toArray(result);
/* 111 */     for (int i = 0; i < result.length; i++)
/* 112 */       result[i] = new UnmodifiableEntry((Map.Entry)result[i]); 
/* 116 */     if (result.length > array.length)
/* 117 */       return result; 
/* 121 */     System.arraycopy(result, 0, array, 0, result.length);
/* 122 */     if (array.length > result.length)
/* 123 */       array[result.length] = null; 
/* 125 */     return array;
/*     */   }
/*     */   
/*     */   static final class UnmodifiableEntrySetIterator extends AbstractIteratorDecorator {
/*     */     protected UnmodifiableEntrySetIterator(Iterator iterator) {
/* 135 */       super(iterator);
/*     */     }
/*     */     
/*     */     public Object next() {
/* 139 */       Map.Entry entry = this.iterator.next();
/* 140 */       return new UnmodifiableEntrySet.UnmodifiableEntry(entry);
/*     */     }
/*     */     
/*     */     public void remove() {
/* 144 */       throw new UnsupportedOperationException();
/*     */     }
/*     */   }
/*     */   
/*     */   static final class UnmodifiableEntry extends AbstractMapEntryDecorator {
/*     */     protected UnmodifiableEntry(Map.Entry entry) {
/* 155 */       super(entry);
/*     */     }
/*     */     
/*     */     public Object setValue(Object obj) {
/* 159 */       throw new UnsupportedOperationException();
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\collections\map\UnmodifiableEntrySet.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */