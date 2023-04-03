/*     */ package com.google.protobuf;
/*     */ 
/*     */ import java.io.ByteArrayInputStream;
/*     */ import java.io.ByteArrayOutputStream;
/*     */ import java.io.FilterOutputStream;
/*     */ import java.io.InputStream;
/*     */ import java.io.UnsupportedEncodingException;
/*     */ import java.nio.ByteBuffer;
/*     */ import java.util.List;
/*     */ 
/*     */ public final class ByteString {
/*     */   private final byte[] bytes;
/*     */   
/*     */   private ByteString(byte[] bytes) {
/* 277 */     this.hash = 0;
/*     */     this.bytes = bytes;
/*     */   }
/*     */   
/*     */   public byte byteAt(int index) {
/*     */     return this.bytes[index];
/*     */   }
/*     */   
/*     */   public int size() {
/*     */     return this.bytes.length;
/*     */   }
/*     */   
/*     */   public boolean isEmpty() {
/*     */     return (this.bytes.length == 0);
/*     */   }
/*     */   
/*     */   public static final ByteString EMPTY = new ByteString(new byte[0]);
/*     */   
/*     */   private volatile int hash;
/*     */   
/*     */   public static ByteString copyFrom(byte[] bytes, int offset, int size) {
/*     */     byte[] copy = new byte[size];
/*     */     System.arraycopy(bytes, offset, copy, 0, size);
/*     */     return new ByteString(copy);
/*     */   }
/*     */   
/*     */   public static ByteString copyFrom(byte[] bytes) {
/*     */     return copyFrom(bytes, 0, bytes.length);
/*     */   }
/*     */   
/*     */   public static ByteString copyFrom(ByteBuffer bytes, int size) {
/*     */     byte[] copy = new byte[size];
/*     */     bytes.get(copy);
/*     */     return new ByteString(copy);
/*     */   }
/*     */   
/*     */   public static ByteString copyFrom(ByteBuffer bytes) {
/*     */     return copyFrom(bytes, bytes.remaining());
/*     */   }
/*     */   
/*     */   public static ByteString copyFrom(String text, String charsetName) throws UnsupportedEncodingException {
/*     */     return new ByteString(text.getBytes(charsetName));
/*     */   }
/*     */   
/*     */   public static ByteString copyFromUtf8(String text) {
/*     */     try {
/*     */       return new ByteString(text.getBytes("UTF-8"));
/*     */     } catch (UnsupportedEncodingException e) {
/*     */       throw new RuntimeException("UTF-8 not supported?", e);
/*     */     } 
/*     */   }
/*     */   
/*     */   public static ByteString copyFrom(List<ByteString> list) {
/*     */     if (list.size() == 0)
/*     */       return EMPTY; 
/*     */     if (list.size() == 1)
/*     */       return list.get(0); 
/*     */     int size = 0;
/*     */     for (ByteString str : list)
/*     */       size += str.size(); 
/*     */     byte[] bytes = new byte[size];
/*     */     int pos = 0;
/*     */     for (ByteString str : list) {
/*     */       System.arraycopy(str.bytes, 0, bytes, pos, str.size());
/*     */       pos += str.size();
/*     */     } 
/*     */     return new ByteString(bytes);
/*     */   }
/*     */   
/*     */   public void copyTo(byte[] target, int offset) {
/*     */     System.arraycopy(this.bytes, 0, target, offset, this.bytes.length);
/*     */   }
/*     */   
/*     */   public void copyTo(byte[] target, int sourceOffset, int targetOffset, int size) {
/*     */     System.arraycopy(this.bytes, sourceOffset, target, targetOffset, size);
/*     */   }
/*     */   
/*     */   public void copyTo(ByteBuffer target) {
/*     */     target.put(this.bytes, 0, this.bytes.length);
/*     */   }
/*     */   
/*     */   public byte[] toByteArray() {
/*     */     int size = this.bytes.length;
/*     */     byte[] copy = new byte[size];
/*     */     System.arraycopy(this.bytes, 0, copy, 0, size);
/*     */     return copy;
/*     */   }
/*     */   
/*     */   public ByteBuffer asReadOnlyByteBuffer() {
/*     */     ByteBuffer byteBuffer = ByteBuffer.wrap(this.bytes);
/*     */     return byteBuffer.asReadOnlyBuffer();
/*     */   }
/*     */   
/*     */   public String toString(String charsetName) throws UnsupportedEncodingException {
/*     */     return new String(this.bytes, charsetName);
/*     */   }
/*     */   
/*     */   public String toStringUtf8() {
/*     */     try {
/*     */       return new String(this.bytes, "UTF-8");
/*     */     } catch (UnsupportedEncodingException e) {
/*     */       throw new RuntimeException("UTF-8 not supported?", e);
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean equals(Object o) {
/*     */     if (o == this)
/*     */       return true; 
/*     */     if (!(o instanceof ByteString))
/*     */       return false; 
/*     */     ByteString other = (ByteString)o;
/*     */     int size = this.bytes.length;
/*     */     if (size != other.bytes.length)
/*     */       return false; 
/*     */     byte[] thisBytes = this.bytes;
/*     */     byte[] otherBytes = other.bytes;
/*     */     for (int i = 0; i < size; i++) {
/*     */       if (thisBytes[i] != otherBytes[i])
/*     */         return false; 
/*     */     } 
/*     */     return true;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 281 */     int h = this.hash;
/* 283 */     if (h == 0) {
/* 284 */       byte[] thisBytes = this.bytes;
/* 285 */       int size = this.bytes.length;
/* 287 */       h = size;
/* 288 */       for (int i = 0; i < size; i++)
/* 289 */         h = h * 31 + thisBytes[i]; 
/* 291 */       if (h == 0)
/* 292 */         h = 1; 
/* 295 */       this.hash = h;
/*     */     } 
/* 298 */     return h;
/*     */   }
/*     */   
/*     */   public InputStream newInput() {
/* 308 */     return new ByteArrayInputStream(this.bytes);
/*     */   }
/*     */   
/*     */   public CodedInputStream newCodedInput() {
/* 319 */     return CodedInputStream.newInstance(this.bytes);
/*     */   }
/*     */   
/*     */   public static Output newOutput(int initialCapacity) {
/* 329 */     return new Output(new ByteArrayOutputStream(initialCapacity));
/*     */   }
/*     */   
/*     */   public static Output newOutput() {
/* 336 */     return newOutput(32);
/*     */   }
/*     */   
/*     */   public static final class Output extends FilterOutputStream {
/*     */     private final ByteArrayOutputStream bout;
/*     */     
/*     */     private Output(ByteArrayOutputStream bout) {
/* 350 */       super(bout);
/* 351 */       this.bout = bout;
/*     */     }
/*     */     
/*     */     public ByteString toByteString() {
/* 358 */       byte[] byteArray = this.bout.toByteArray();
/* 359 */       return new ByteString(byteArray);
/*     */     }
/*     */   }
/*     */   
/*     */   static CodedBuilder newCodedBuilder(int size) {
/* 377 */     return new CodedBuilder(size);
/*     */   }
/*     */   
/*     */   static final class CodedBuilder {
/*     */     private final CodedOutputStream output;
/*     */     
/*     */     private final byte[] buffer;
/*     */     
/*     */     private CodedBuilder(int size) {
/* 386 */       this.buffer = new byte[size];
/* 387 */       this.output = CodedOutputStream.newInstance(this.buffer);
/*     */     }
/*     */     
/*     */     public ByteString build() {
/* 391 */       this.output.checkNoSpaceLeft();
/* 396 */       return new ByteString(this.buffer);
/*     */     }
/*     */     
/*     */     public CodedOutputStream getCodedOutput() {
/* 400 */       return this.output;
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\google\protobuf\ByteString.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */