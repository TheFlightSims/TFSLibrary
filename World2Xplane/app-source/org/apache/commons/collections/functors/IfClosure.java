/*     */ package org.apache.commons.collections.functors;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import org.apache.commons.collections.Closure;
/*     */ import org.apache.commons.collections.Predicate;
/*     */ 
/*     */ public class IfClosure implements Closure, Serializable {
/*     */   private static final long serialVersionUID = 3518477308466486130L;
/*     */   
/*     */   private final Predicate iPredicate;
/*     */   
/*     */   private final Closure iTrueClosure;
/*     */   
/*     */   private final Closure iFalseClosure;
/*     */   
/*     */   public static Closure getInstance(Predicate predicate, Closure trueClosure) {
/*  59 */     return getInstance(predicate, trueClosure, NOPClosure.INSTANCE);
/*     */   }
/*     */   
/*     */   public static Closure getInstance(Predicate predicate, Closure trueClosure, Closure falseClosure) {
/*  72 */     if (predicate == null)
/*  73 */       throw new IllegalArgumentException("Predicate must not be null"); 
/*  75 */     if (trueClosure == null || falseClosure == null)
/*  76 */       throw new IllegalArgumentException("Closures must not be null"); 
/*  78 */     return new IfClosure(predicate, trueClosure, falseClosure);
/*     */   }
/*     */   
/*     */   public IfClosure(Predicate predicate, Closure trueClosure) {
/*  93 */     this(predicate, trueClosure, NOPClosure.INSTANCE);
/*     */   }
/*     */   
/*     */   public IfClosure(Predicate predicate, Closure trueClosure, Closure falseClosure) {
/* 106 */     this.iPredicate = predicate;
/* 107 */     this.iTrueClosure = trueClosure;
/* 108 */     this.iFalseClosure = falseClosure;
/*     */   }
/*     */   
/*     */   public void execute(Object input) {
/* 117 */     if (this.iPredicate.evaluate(input) == true) {
/* 118 */       this.iTrueClosure.execute(input);
/*     */     } else {
/* 120 */       this.iFalseClosure.execute(input);
/*     */     } 
/*     */   }
/*     */   
/*     */   public Predicate getPredicate() {
/* 131 */     return this.iPredicate;
/*     */   }
/*     */   
/*     */   public Closure getTrueClosure() {
/* 141 */     return this.iTrueClosure;
/*     */   }
/*     */   
/*     */   public Closure getFalseClosure() {
/* 151 */     return this.iFalseClosure;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\collections\functors\IfClosure.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */