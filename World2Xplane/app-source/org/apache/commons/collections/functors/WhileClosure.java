/*     */ package org.apache.commons.collections.functors;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import org.apache.commons.collections.Closure;
/*     */ import org.apache.commons.collections.Predicate;
/*     */ 
/*     */ public class WhileClosure implements Closure, Serializable {
/*     */   private static final long serialVersionUID = -3110538116913760108L;
/*     */   
/*     */   private final Predicate iPredicate;
/*     */   
/*     */   private final Closure iClosure;
/*     */   
/*     */   private final boolean iDoLoop;
/*     */   
/*     */   public static Closure getInstance(Predicate predicate, Closure closure, boolean doLoop) {
/*  55 */     if (predicate == null)
/*  56 */       throw new IllegalArgumentException("Predicate must not be null"); 
/*  58 */     if (closure == null)
/*  59 */       throw new IllegalArgumentException("Closure must not be null"); 
/*  61 */     return new WhileClosure(predicate, closure, doLoop);
/*     */   }
/*     */   
/*     */   public WhileClosure(Predicate predicate, Closure closure, boolean doLoop) {
/*  74 */     this.iPredicate = predicate;
/*  75 */     this.iClosure = closure;
/*  76 */     this.iDoLoop = doLoop;
/*     */   }
/*     */   
/*     */   public void execute(Object input) {
/*  85 */     if (this.iDoLoop)
/*  86 */       this.iClosure.execute(input); 
/*  88 */     while (this.iPredicate.evaluate(input))
/*  89 */       this.iClosure.execute(input); 
/*     */   }
/*     */   
/*     */   public Predicate getPredicate() {
/* 100 */     return this.iPredicate;
/*     */   }
/*     */   
/*     */   public Closure getClosure() {
/* 110 */     return this.iClosure;
/*     */   }
/*     */   
/*     */   public boolean isDoLoop() {
/* 120 */     return this.iDoLoop;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\collections\functors\WhileClosure.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */