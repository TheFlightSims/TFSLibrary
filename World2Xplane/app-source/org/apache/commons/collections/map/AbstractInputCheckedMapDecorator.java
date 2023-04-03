/*     */ package org.apache.commons.collections.map;
/*     */ 
/*     */ import java.lang.reflect.Array;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import org.apache.commons.collections.iterators.AbstractIteratorDecorator;
/*     */ import org.apache.commons.collections.keyvalue.AbstractMapEntryDecorator;
/*     */ import org.apache.commons.collections.set.AbstractSetDecorator;
/*     */ 
/*     */ abstract class AbstractInputCheckedMapDecorator extends AbstractMapDecorator {
/*     */   protected AbstractInputCheckedMapDecorator() {}
/*     */   
/*     */   protected AbstractInputCheckedMapDecorator(Map map) {
/*  64 */     super(map);
/*     */   }
/*     */   
/*     */   protected abstract Object checkSetValue(Object paramObject);
/*     */   
/*     */   protected boolean isSetValueChecking() {
/*  95 */     return true;
/*     */   }
/*     */   
/*     */   public Set entrySet() {
/* 100 */     if (isSetValueChecking())
/* 101 */       return (Set)new EntrySet(this.map.entrySet(), this); 
/* 103 */     return this.map.entrySet();
/*     */   }
/*     */   
/*     */   static class EntrySet extends AbstractSetDecorator {
/*     */     private final AbstractInputCheckedMapDecorator parent;
/*     */     
/*     */     protected EntrySet(Set set, AbstractInputCheckedMapDecorator parent) {
/* 117 */       super(set);
/* 118 */       this.parent = parent;
/*     */     }
/*     */     
/*     */     public Iterator iterator() {
/* 122 */       return (Iterator)new AbstractInputCheckedMapDecorator.EntrySetIterator(this.collection.iterator(), this.parent);
/*     */     }
/*     */     
/*     */     public Object[] toArray() {
/* 126 */       Object[] array = this.collection.toArray();
/* 127 */       for (int i = 0; i < array.length; i++)
/* 128 */         array[i] = new AbstractInputCheckedMapDecorator.MapEntry((Map.Entry)array[i], this.parent); 
/* 130 */       return array;
/*     */     }
/*     */     
/*     */     public Object[] toArray(Object[] array) {
/* 134 */       Object[] result = array;
/* 135 */       if (array.length > 0)
/* 138 */         result = (Object[])Array.newInstance(array.getClass().getComponentType(), 0); 
/* 140 */       result = this.collection.toArray(result);
/* 141 */       for (int i = 0; i < result.length; i++)
/* 142 */         result[i] = new AbstractInputCheckedMapDecorator.MapEntry((Map.Entry)result[i], this.parent); 
/* 146 */       if (result.length > array.length)
/* 147 */         return result; 
/* 151 */       System.arraycopy(result, 0, array, 0, result.length);
/* 152 */       if (array.length > result.length)
/* 153 */         array[result.length] = null; 
/* 155 */       return array;
/*     */     }
/*     */   }
/*     */   
/*     */   static class EntrySetIterator extends AbstractIteratorDecorator {
/*     */     private final AbstractInputCheckedMapDecorator parent;
/*     */     
/*     */     protected EntrySetIterator(Iterator iterator, AbstractInputCheckedMapDecorator parent) {
/* 168 */       super(iterator);
/* 169 */       this.parent = parent;
/*     */     }
/*     */     
/*     */     public Object next() {
/* 173 */       Map.Entry entry = this.iterator.next();
/* 174 */       return new AbstractInputCheckedMapDecorator.MapEntry(entry, this.parent);
/*     */     }
/*     */   }
/*     */   
/*     */   static class MapEntry extends AbstractMapEntryDecorator {
/*     */     private final AbstractInputCheckedMapDecorator parent;
/*     */     
/*     */     protected MapEntry(Map.Entry entry, AbstractInputCheckedMapDecorator parent) {
/* 187 */       super(entry);
/* 188 */       this.parent = parent;
/*     */     }
/*     */     
/*     */     public Object setValue(Object value) {
/* 192 */       value = this.parent.checkSetValue(value);
/* 193 */       return this.entry.setValue(value);
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\collections\map\AbstractInputCheckedMapDecorator.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */