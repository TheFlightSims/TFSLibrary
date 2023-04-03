/*     */ package akka.util;
/*     */ 
/*     */ import java.nio.ByteBuffer;
/*     */ import scala.Serializable;
/*     */ import scala.collection.Seq;
/*     */ import scala.collection.TraversableOnce;
/*     */ import scala.collection.generic.CanBuildFrom;
/*     */ import scala.collection.mutable.Builder;
/*     */ import scala.math.Integral;
/*     */ import scala.runtime.AbstractFunction1;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ 
/*     */ public final class ByteString$ {
/*     */   public static final ByteString$ MODULE$;
/*     */   
/*     */   private final ByteString empty;
/*     */   
/*     */   private final CanBuildFrom<TraversableOnce<Object>, Object, ByteString> canBuildFrom;
/*     */   
/*     */   private ByteString$() {
/*  17 */     MODULE$ = this;
/*  77 */     this.empty = CompactByteString$.MODULE$.apply((byte[])scala.Array$.MODULE$.empty(scala.reflect.ClassTag$.MODULE$.Byte()));
/*  81 */     this.canBuildFrom = 
/*  82 */       new ByteString.$anon$2();
/*     */   }
/*     */   
/*     */   public ByteString apply(byte[] bytes) {
/*     */     return CompactByteString$.MODULE$.apply(bytes);
/*     */   }
/*     */   
/*     */   public ByteString apply(Seq<Object> bytes) {
/*     */     return CompactByteString$.MODULE$.apply(bytes);
/*     */   }
/*     */   
/*     */   public <T> ByteString apply(Seq<?> bytes, Integral<?> num) {
/*     */     return CompactByteString$.MODULE$.apply(bytes, num);
/*     */   }
/*     */   
/*     */   public ByteString apply(ByteBuffer bytes) {
/*     */     return CompactByteString$.MODULE$.apply(bytes);
/*     */   }
/*     */   
/*     */   public ByteString apply(String string) {
/*     */     return apply(string, "UTF-8");
/*     */   }
/*     */   
/*     */   public ByteString apply(String string, String charset) {
/*     */     return CompactByteString$.MODULE$.apply(string, charset);
/*     */   }
/*     */   
/*     */   public ByteString fromArray(byte[] array) {
/*     */     return apply(array);
/*     */   }
/*     */   
/*     */   public ByteString fromArray(byte[] array, int offset, int length) {
/*     */     return CompactByteString$.MODULE$.fromArray(array, offset, length);
/*     */   }
/*     */   
/*     */   public ByteString fromString(String string) {
/*     */     return apply(string);
/*     */   }
/*     */   
/*     */   public ByteString fromString(String string, String charset) {
/*     */     return apply(string, charset);
/*     */   }
/*     */   
/*     */   public ByteString fromByteBuffer(ByteBuffer buffer) {
/*     */     return apply(buffer);
/*     */   }
/*     */   
/*     */   public ByteString empty() {
/*     */     return this.empty;
/*     */   }
/*     */   
/*     */   public ByteStringBuilder newBuilder() {
/*     */     return new ByteStringBuilder();
/*     */   }
/*     */   
/*     */   public CanBuildFrom<TraversableOnce<Object>, Object, ByteString> canBuildFrom() {
/*     */     return this.canBuildFrom;
/*     */   }
/*     */   
/*     */   public static class $anon$2 implements CanBuildFrom<TraversableOnce<Object>, Object, ByteString> {
/*     */     public ByteStringBuilder apply(TraversableOnce ignore) {
/*  83 */       return ByteString$.MODULE$.newBuilder();
/*     */     }
/*     */     
/*     */     public ByteStringBuilder apply() {
/*  84 */       return ByteString$.MODULE$.newBuilder();
/*     */     }
/*     */   }
/*     */   
/*     */   public class ByteString$$anonfun$mapI$1 extends AbstractFunction1<Object, Object> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final byte apply(int x$8) {
/* 402 */       return (byte)x$8;
/*     */     }
/*     */     
/*     */     public ByteString$$anonfun$mapI$1(ByteString $outer) {}
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akk\\util\ByteString$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */