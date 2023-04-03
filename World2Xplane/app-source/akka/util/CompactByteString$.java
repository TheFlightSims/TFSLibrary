/*     */ package akka.util;
/*     */ 
/*     */ import java.nio.ByteBuffer;
/*     */ import scala.Function1;
/*     */ import scala.Serializable;
/*     */ import scala.collection.Seq;
/*     */ import scala.math.Integral;
/*     */ import scala.runtime.AbstractFunction1;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ 
/*     */ public final class CompactByteString$ implements Serializable {
/*     */   public static final CompactByteString$ MODULE$;
/*     */   
/*     */   private final CompactByteString empty;
/*     */   
/*     */   private Object readResolve() {
/* 405 */     return MODULE$;
/*     */   }
/*     */   
/*     */   private CompactByteString$() {
/* 405 */     MODULE$ = this;
/* 470 */     this.empty = ByteString.ByteString1C$.MODULE$.apply((byte[])scala.Array$.MODULE$.empty(scala.reflect.ClassTag$.MODULE$.Byte()));
/*     */   }
/*     */   
/*     */   public CompactByteString apply(byte[] bytes) {
/*     */     return scala.Predef$.MODULE$.byteArrayOps(bytes).isEmpty() ? empty() : ByteString.ByteString1C$.MODULE$.apply((byte[])bytes.clone());
/*     */   }
/*     */   
/*     */   public CompactByteString apply(Seq bytes) {
/*     */     byte[] ar = new byte[bytes.size()];
/*     */     bytes.copyToArray(ar);
/*     */     return bytes.isEmpty() ? empty() : apply(ar);
/*     */   }
/*     */   
/*     */   public <T> CompactByteString apply(Seq bytes, Integral num) {
/*     */     return bytes.isEmpty() ? empty() : ByteString.ByteString1C$.MODULE$.apply((byte[])bytes.map((Function1)new CompactByteString$$anonfun$apply$4(num), scala.collection.package$.MODULE$.breakOut(scala.Array$.MODULE$.canBuildFrom(scala.reflect.ClassTag$.MODULE$.Byte()))));
/*     */   }
/*     */   
/*     */   public static class CompactByteString$$anonfun$apply$4 extends AbstractFunction1<T, Object> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final Integral num$1;
/*     */     
/*     */     public final byte apply(Object x) {
/*     */       return (byte)this.num$1.toInt(x);
/*     */     }
/*     */     
/*     */     public CompactByteString$$anonfun$apply$4(Integral num$1) {}
/*     */   }
/*     */   
/*     */   public CompactByteString apply(ByteBuffer bytes) {
/*     */     byte[] ar = new byte[bytes.remaining()];
/*     */     bytes.get(ar);
/*     */     return (bytes.remaining() < 1) ? empty() : ByteString.ByteString1C$.MODULE$.apply(ar);
/*     */   }
/*     */   
/*     */   public CompactByteString apply(String string) {
/*     */     return apply(string, "UTF-8");
/*     */   }
/*     */   
/*     */   public CompactByteString apply(String string, String charset) {
/*     */     return string.isEmpty() ? empty() : ByteString.ByteString1C$.MODULE$.apply(string.getBytes(charset));
/*     */   }
/*     */   
/*     */   public CompactByteString fromArray(byte[] array, int offset, int length) {
/*     */     int copyOffset = scala.math.package$.MODULE$.max(offset, 0);
/*     */     int copyLength = scala.math.package$.MODULE$.max(scala.math.package$.MODULE$.min(array.length - copyOffset, length), 0);
/*     */     byte[] copyArray = new byte[copyLength];
/*     */     scala.Array$.MODULE$.copy(array, copyOffset, copyArray, 0, copyLength);
/*     */     return (copyLength == 0) ? empty() : ByteString.ByteString1C$.MODULE$.apply(copyArray);
/*     */   }
/*     */   
/*     */   public CompactByteString empty() {
/* 470 */     return this.empty;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akk\\util\CompactByteString$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */