/*     */ package akka.japi;
/*     */ 
/*     */ import java.util.Iterator;
/*     */ import scala.collection.Seq;
/*     */ import scala.collection.immutable.IndexedSeq;
/*     */ import scala.collection.immutable.Seq;
/*     */ import scala.collection.immutable.VectorBuilder;
/*     */ import scala.reflect.ClassTag;
/*     */ 
/*     */ public final class Util$ {
/*     */   public static final Util$ MODULE$;
/*     */   
/*     */   private Util$() {
/* 190 */     MODULE$ = this;
/*     */   }
/*     */   
/*     */   public <T> ClassTag<T> classTag(Class clazz) {
/* 195 */     return scala.reflect.ClassTag$.MODULE$.apply(clazz);
/*     */   }
/*     */   
/*     */   public Seq<Class<?>> immutableSeq(Class[] arr) {
/* 201 */     return immutableSeq(arr);
/*     */   }
/*     */   
/*     */   public <T> Seq<T> immutableSeq(Object arr) {
/* 206 */     return (arr != null && scala.runtime.ScalaRunTime$.MODULE$.array_length(arr) > 0) ? (Seq<T>)scala.package$.MODULE$.Vector().apply((Seq)scala.Predef$.MODULE$.genericWrapArray(arr)) : (Seq<T>)scala.collection.immutable.Nil$.MODULE$;
/*     */   }
/*     */   
/*     */   public <T> Seq<T> immutableSeq(Iterable iterable) {
/*     */     Seq seq;
/* 212 */     Iterable iterable1 = iterable;
/* 213 */     if (iterable1 instanceof Seq) {
/* 213 */       Iterable iterable2 = iterable1;
/* 213 */       seq = (Seq)iterable2;
/*     */     } else {
/* 215 */       Iterator i = iterable1.iterator();
/* 217 */       VectorBuilder builder = new VectorBuilder();
/*     */       do {
/* 219 */         builder.$plus$eq(i.next());
/* 219 */       } while (i.hasNext());
/* 221 */       seq = (Seq)(i.hasNext() ? builder.result() : 
/* 222 */         akka.util.Collections$EmptyImmutableSeq$.MODULE$);
/*     */     } 
/*     */     return seq;
/*     */   }
/*     */   
/*     */   public <T> Seq<T> immutableSingletonSeq(Object value) {
/* 225 */     Object object = value;
/* 225 */     return (Seq<T>)scala.collection.immutable.Nil$.MODULE$.$colon$colon(object);
/*     */   }
/*     */   
/*     */   public <T> IndexedSeq<T> immutableIndexedSeq(Iterable<T> iterable) {
/* 231 */     return (IndexedSeq<T>)immutableSeq(iterable).toVector();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\japi\Util$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */