/*     */ package org.apache.commons.collections.buffer;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.ObjectOutputStream;
/*     */ import java.io.Serializable;
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ import org.apache.commons.collections.Buffer;
/*     */ import org.apache.commons.collections.Unmodifiable;
/*     */ import org.apache.commons.collections.iterators.UnmodifiableIterator;
/*     */ 
/*     */ public final class UnmodifiableBuffer extends AbstractBufferDecorator implements Unmodifiable, Serializable {
/*     */   private static final long serialVersionUID = 1832948656215393357L;
/*     */   
/*     */   public static Buffer decorate(Buffer buffer) {
/*  57 */     if (buffer instanceof Unmodifiable)
/*  58 */       return buffer; 
/*  60 */     return new UnmodifiableBuffer(buffer);
/*     */   }
/*     */   
/*     */   private UnmodifiableBuffer(Buffer buffer) {
/*  71 */     super(buffer);
/*     */   }
/*     */   
/*     */   private void writeObject(ObjectOutputStream out) throws IOException {
/*  82 */     out.defaultWriteObject();
/*  83 */     out.writeObject(this.collection);
/*     */   }
/*     */   
/*     */   private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
/*  94 */     in.defaultReadObject();
/*  95 */     this.collection = (Collection)in.readObject();
/*     */   }
/*     */   
/*     */   public Iterator iterator() {
/* 100 */     return UnmodifiableIterator.decorate(getCollection().iterator());
/*     */   }
/*     */   
/*     */   public boolean add(Object object) {
/* 104 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public boolean addAll(Collection coll) {
/* 108 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public void clear() {
/* 112 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public boolean remove(Object object) {
/* 116 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public boolean removeAll(Collection coll) {
/* 120 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public boolean retainAll(Collection coll) {
/* 124 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public Object remove() {
/* 129 */     throw new UnsupportedOperationException();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\collections\buffer\UnmodifiableBuffer.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */