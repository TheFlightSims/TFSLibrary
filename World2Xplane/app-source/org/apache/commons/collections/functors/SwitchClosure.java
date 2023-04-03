/*     */ package org.apache.commons.collections.functors;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ import org.apache.commons.collections.Closure;
/*     */ import org.apache.commons.collections.Predicate;
/*     */ 
/*     */ public class SwitchClosure implements Closure, Serializable {
/*     */   private static final long serialVersionUID = 3518477308466486130L;
/*     */   
/*     */   private final Predicate[] iPredicates;
/*     */   
/*     */   private final Closure[] iClosures;
/*     */   
/*     */   private final Closure iDefault;
/*     */   
/*     */   public static Closure getInstance(Predicate[] predicates, Closure[] closures, Closure defaultClosure) {
/*  58 */     FunctorUtils.validate(predicates);
/*  59 */     FunctorUtils.validate(closures);
/*  60 */     if (predicates.length != closures.length)
/*  61 */       throw new IllegalArgumentException("The predicate and closure arrays must be the same size"); 
/*  63 */     if (predicates.length == 0)
/*  64 */       return (defaultClosure == null) ? NOPClosure.INSTANCE : defaultClosure; 
/*  66 */     predicates = FunctorUtils.copy(predicates);
/*  67 */     closures = FunctorUtils.copy(closures);
/*  68 */     return new SwitchClosure(predicates, closures, defaultClosure);
/*     */   }
/*     */   
/*     */   public static Closure getInstance(Map predicatesAndClosures) {
/*  89 */     Closure[] closures = null;
/*  90 */     Predicate[] preds = null;
/*  91 */     if (predicatesAndClosures == null)
/*  92 */       throw new IllegalArgumentException("The predicate and closure map must not be null"); 
/*  94 */     if (predicatesAndClosures.size() == 0)
/*  95 */       return NOPClosure.INSTANCE; 
/*  98 */     Closure defaultClosure = (Closure)predicatesAndClosures.remove(null);
/*  99 */     int size = predicatesAndClosures.size();
/* 100 */     if (size == 0)
/* 101 */       return (defaultClosure == null) ? NOPClosure.INSTANCE : defaultClosure; 
/* 103 */     closures = new Closure[size];
/* 104 */     preds = new Predicate[size];
/* 105 */     int i = 0;
/* 106 */     for (Iterator it = predicatesAndClosures.entrySet().iterator(); it.hasNext(); ) {
/* 107 */       Map.Entry entry = it.next();
/* 108 */       preds[i] = (Predicate)entry.getKey();
/* 109 */       closures[i] = (Closure)entry.getValue();
/* 110 */       i++;
/*     */     } 
/* 112 */     return new SwitchClosure(preds, closures, defaultClosure);
/*     */   }
/*     */   
/*     */   public SwitchClosure(Predicate[] predicates, Closure[] closures, Closure defaultClosure) {
/* 125 */     this.iPredicates = predicates;
/* 126 */     this.iClosures = closures;
/* 127 */     this.iDefault = (defaultClosure == null) ? NOPClosure.INSTANCE : defaultClosure;
/*     */   }
/*     */   
/*     */   public void execute(Object input) {
/* 136 */     for (int i = 0; i < this.iPredicates.length; i++) {
/* 137 */       if (this.iPredicates[i].evaluate(input) == true) {
/* 138 */         this.iClosures[i].execute(input);
/*     */         return;
/*     */       } 
/*     */     } 
/* 142 */     this.iDefault.execute(input);
/*     */   }
/*     */   
/*     */   public Predicate[] getPredicates() {
/* 152 */     return this.iPredicates;
/*     */   }
/*     */   
/*     */   public Closure[] getClosures() {
/* 162 */     return this.iClosures;
/*     */   }
/*     */   
/*     */   public Closure getDefaultClosure() {
/* 172 */     return this.iDefault;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\collections\functors\SwitchClosure.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */