/*     */ package scala;
/*     */ 
/*     */ public final class Some$ implements Serializable {
/*     */   public static final Some$ MODULE$;
/*     */   
/*     */   public final String toString() {
/* 300 */     return "Some";
/*     */   }
/*     */   
/*     */   public <A> Some<A> apply(Object x) {
/* 300 */     return new Some<A>((A)x);
/*     */   }
/*     */   
/*     */   public <A> Option<A> unapply(Some<A> x$0) {
/* 300 */     return (x$0 == null) ? None$.MODULE$ : new Some<A>(x$0.x());
/*     */   }
/*     */   
/*     */   private Object readResolve() {
/* 300 */     return MODULE$;
/*     */   }
/*     */   
/*     */   private Some$() {
/* 300 */     MODULE$ = this;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\Some$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */