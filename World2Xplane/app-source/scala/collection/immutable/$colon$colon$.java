/*     */ package scala.collection.immutable;
/*     */ 
/*     */ import scala.Option;
/*     */ import scala.Serializable;
/*     */ import scala.Some;
/*     */ import scala.Tuple2;
/*     */ 
/*     */ public final class $colon$colon$ implements Serializable {
/*     */   public static final $colon$colon$ MODULE$;
/*     */   
/*     */   public final String toString() {
/* 356 */     return "::";
/*     */   }
/*     */   
/*     */   public <B> $colon$colon<B> apply(Object hd, List<B> tl) {
/* 356 */     return new $colon$colon<B>((B)hd, tl);
/*     */   }
/*     */   
/*     */   public <B> Option<Tuple2<B, List<B>>> unapply($colon$colon x$0) {
/* 356 */     return (x$0 == null) ? (Option<Tuple2<B, List<B>>>)scala.None$.MODULE$ : (Option<Tuple2<B, List<B>>>)new Some(new Tuple2(x$0.scala$collection$immutable$$colon$colon$$hd(), x$0.tl()));
/*     */   }
/*     */   
/*     */   private Object readResolve() {
/* 356 */     return MODULE$;
/*     */   }
/*     */   
/*     */   private $colon$colon$() {
/* 356 */     MODULE$ = this;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\immutable\$colon$colon$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */