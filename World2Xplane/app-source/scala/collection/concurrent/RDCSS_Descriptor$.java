/*     */ package scala.collection.concurrent;
/*     */ 
/*     */ import scala.Option;
/*     */ import scala.Serializable;
/*     */ import scala.Some;
/*     */ import scala.Tuple3;
/*     */ 
/*     */ public final class RDCSS_Descriptor$ implements Serializable {
/*     */   public static final RDCSS_Descriptor$ MODULE$;
/*     */   
/*     */   public final String toString() {
/* 613 */     return "RDCSS_Descriptor";
/*     */   }
/*     */   
/*     */   public <K, V> RDCSS_Descriptor<K, V> apply(INode<K, V> old, MainNode<K, V> expectedmain, INode<K, V> nv) {
/* 613 */     return new RDCSS_Descriptor<K, V>(old, expectedmain, nv);
/*     */   }
/*     */   
/*     */   public <K, V> Option<Tuple3<INode<K, V>, MainNode<K, V>, INode<K, V>>> unapply(RDCSS_Descriptor x$0) {
/* 613 */     return (x$0 == null) ? (Option<Tuple3<INode<K, V>, MainNode<K, V>, INode<K, V>>>)scala.None$.MODULE$ : (Option<Tuple3<INode<K, V>, MainNode<K, V>, INode<K, V>>>)new Some(new Tuple3(x$0.old(), x$0.expectedmain(), x$0.nv()));
/*     */   }
/*     */   
/*     */   private Object readResolve() {
/* 613 */     return MODULE$;
/*     */   }
/*     */   
/*     */   private RDCSS_Descriptor$() {
/* 613 */     MODULE$ = this;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\concurrent\RDCSS_Descriptor$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */