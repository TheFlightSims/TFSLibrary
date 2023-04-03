/*     */ package org.geotools.filter.capability;
/*     */ 
/*     */ import org.opengis.filter.capability.ArithmeticOperators;
/*     */ import org.opengis.filter.capability.Functions;
/*     */ 
/*     */ public class ArithmeticOperatorsImpl implements ArithmeticOperators {
/*     */   boolean simpleArithmetic;
/*     */   
/*     */   FunctionsImpl functions;
/*     */   
/*     */   public ArithmeticOperatorsImpl() {
/*  38 */     this.simpleArithmetic = false;
/*  39 */     this.functions = new FunctionsImpl();
/*     */   }
/*     */   
/*     */   public ArithmeticOperatorsImpl(boolean simpleArtithmetic, Functions functions) {
/*  43 */     this.simpleArithmetic = simpleArtithmetic;
/*  44 */     this.functions = toFunctionsImpl(functions);
/*     */   }
/*     */   
/*     */   public ArithmeticOperatorsImpl(ArithmeticOperators copy) {
/*  48 */     this.simpleArithmetic = copy.hasSimpleArithmetic();
/*  49 */     this.functions = (copy.getFunctions() == null) ? new FunctionsImpl() : new FunctionsImpl(copy.getFunctions());
/*     */   }
/*     */   
/*     */   public void setSimpleArithmetic(boolean simpleArithmetic) {
/*  54 */     this.simpleArithmetic = simpleArithmetic;
/*     */   }
/*     */   
/*     */   public boolean hasSimpleArithmetic() {
/*  57 */     return this.simpleArithmetic;
/*     */   }
/*     */   
/*     */   public FunctionsImpl getFunctions() {
/*  61 */     return this.functions;
/*     */   }
/*     */   
/*     */   private static FunctionsImpl toFunctionsImpl(Functions functions) {
/*  65 */     if (functions == null)
/*  66 */       return new FunctionsImpl(); 
/*  68 */     if (functions instanceof FunctionsImpl)
/*  69 */       return (FunctionsImpl)functions; 
/*  72 */     return new FunctionsImpl(functions);
/*     */   }
/*     */   
/*     */   public void addAll(ArithmeticOperators copy) {
/*  77 */     if (copy == null)
/*     */       return; 
/*  78 */     getFunctions().addAll(copy.getFunctions());
/*  79 */     if (copy.hasSimpleArithmetic())
/*  80 */       this.simpleArithmetic = true; 
/*     */   }
/*     */   
/*     */   public String toString() {
/*  85 */     StringBuffer buf = new StringBuffer();
/*  86 */     buf.append("ArithmeticOperators[");
/*  87 */     if (this.simpleArithmetic)
/*  88 */       buf.append("simpleArithmetic=true"); 
/*  90 */     buf.append("]");
/*  91 */     if (this.functions != null)
/*  92 */       buf.append(" with functions"); 
/*  94 */     return buf.toString();
/*     */   }
/*     */   
/*     */   public int hashCode() {
/*  99 */     int prime = 31;
/* 100 */     int result = 1;
/* 101 */     result = 31 * result + ((this.functions == null) ? 0 : this.functions.hashCode());
/* 102 */     result = 31 * result + (this.simpleArithmetic ? 1231 : 1237);
/* 103 */     return result;
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/* 108 */     if (this == obj)
/* 109 */       return true; 
/* 110 */     if (obj == null)
/* 111 */       return false; 
/* 112 */     if (getClass() != obj.getClass())
/* 113 */       return false; 
/* 114 */     ArithmeticOperatorsImpl other = (ArithmeticOperatorsImpl)obj;
/* 115 */     if (this.functions == null) {
/* 116 */       if (other.functions != null)
/* 117 */         return false; 
/* 118 */     } else if (!this.functions.equals(other.functions)) {
/* 119 */       return false;
/*     */     } 
/* 120 */     if (this.simpleArithmetic != other.simpleArithmetic)
/* 121 */       return false; 
/* 122 */     return true;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\filter\capability\ArithmeticOperatorsImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */