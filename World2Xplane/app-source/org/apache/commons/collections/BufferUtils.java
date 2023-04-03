/*     */ package org.apache.commons.collections;
/*     */ 
/*     */ import org.apache.commons.collections.buffer.BlockingBuffer;
/*     */ import org.apache.commons.collections.buffer.BoundedBuffer;
/*     */ import org.apache.commons.collections.buffer.PredicatedBuffer;
/*     */ import org.apache.commons.collections.buffer.SynchronizedBuffer;
/*     */ import org.apache.commons.collections.buffer.TransformedBuffer;
/*     */ import org.apache.commons.collections.buffer.TypedBuffer;
/*     */ import org.apache.commons.collections.buffer.UnmodifiableBuffer;
/*     */ 
/*     */ public class BufferUtils {
/*  41 */   public static final Buffer EMPTY_BUFFER = UnmodifiableBuffer.decorate(new ArrayStack(1));
/*     */   
/*     */   public static Buffer synchronizedBuffer(Buffer buffer) {
/*  71 */     return SynchronizedBuffer.decorate(buffer);
/*     */   }
/*     */   
/*     */   public static Buffer blockingBuffer(Buffer buffer) {
/*  87 */     return BlockingBuffer.decorate(buffer);
/*     */   }
/*     */   
/*     */   public static Buffer blockingBuffer(Buffer buffer, long timeoutMillis) {
/* 105 */     return BlockingBuffer.decorate(buffer, timeoutMillis);
/*     */   }
/*     */   
/*     */   public static Buffer boundedBuffer(Buffer buffer, int maximumSize) {
/* 122 */     return (Buffer)BoundedBuffer.decorate(buffer, maximumSize);
/*     */   }
/*     */   
/*     */   public static Buffer boundedBuffer(Buffer buffer, int maximumSize, long timeoutMillis) {
/* 140 */     return (Buffer)BoundedBuffer.decorate(buffer, maximumSize, timeoutMillis);
/*     */   }
/*     */   
/*     */   public static Buffer unmodifiableBuffer(Buffer buffer) {
/* 151 */     return UnmodifiableBuffer.decorate(buffer);
/*     */   }
/*     */   
/*     */   public static Buffer predicatedBuffer(Buffer buffer, Predicate predicate) {
/* 168 */     return PredicatedBuffer.decorate(buffer, predicate);
/*     */   }
/*     */   
/*     */   public static Buffer typedBuffer(Buffer buffer, Class type) {
/* 182 */     return TypedBuffer.decorate(buffer, type);
/*     */   }
/*     */   
/*     */   public static Buffer transformedBuffer(Buffer buffer, Transformer transformer) {
/* 198 */     return TransformedBuffer.decorate(buffer, transformer);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\collections\BufferUtils.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */