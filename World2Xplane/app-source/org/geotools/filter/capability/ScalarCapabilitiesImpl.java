/*     */ package org.geotools.filter.capability;
/*     */ 
/*     */ import org.opengis.filter.capability.ArithmeticOperators;
/*     */ import org.opengis.filter.capability.ComparisonOperators;
/*     */ import org.opengis.filter.capability.ScalarCapabilities;
/*     */ 
/*     */ public class ScalarCapabilitiesImpl implements ScalarCapabilities {
/*     */   ArithmeticOperatorsImpl arithmeticOperators;
/*     */   
/*     */   ComparisonOperatorsImpl comparisonOperators;
/*     */   
/*     */   boolean logicalOperators;
/*     */   
/*     */   public ScalarCapabilitiesImpl() {
/*  39 */     this.arithmeticOperators = new ArithmeticOperatorsImpl();
/*  40 */     this.comparisonOperators = new ComparisonOperatorsImpl();
/*  41 */     this.logicalOperators = false;
/*     */   }
/*     */   
/*     */   public ScalarCapabilitiesImpl(ComparisonOperators comparisonOperators, ArithmeticOperators arithmeticOperators, boolean logicalOperators) {
/*  46 */     this.arithmeticOperators = toArithmeticOperatorsImpl(arithmeticOperators);
/*  47 */     this.comparisonOperators = toComparisonOperatorsImpl(comparisonOperators);
/*  48 */     this.logicalOperators = logicalOperators;
/*     */   }
/*     */   
/*     */   public ScalarCapabilitiesImpl(ScalarCapabilities copy) {
/*  52 */     this.arithmeticOperators = (copy.getArithmeticOperators() == null) ? new ArithmeticOperatorsImpl() : new ArithmeticOperatorsImpl(copy.getArithmeticOperators());
/*  56 */     this.comparisonOperators = (copy.getComparisonOperators() == null) ? new ComparisonOperatorsImpl() : new ComparisonOperatorsImpl(copy.getComparisonOperators());
/*  60 */     this.logicalOperators = copy.hasLogicalOperators();
/*     */   }
/*     */   
/*     */   public void setArithmeticOperators(ArithmeticOperatorsImpl arithmeticOperators) {
/*  64 */     this.arithmeticOperators = arithmeticOperators;
/*     */   }
/*     */   
/*     */   public ArithmeticOperatorsImpl getArithmeticOperators() {
/*  68 */     return this.arithmeticOperators;
/*     */   }
/*     */   
/*     */   public void setComparisonOperators(ComparisonOperatorsImpl comparisonOperators) {
/*  72 */     this.comparisonOperators = comparisonOperators;
/*     */   }
/*     */   
/*     */   public ComparisonOperatorsImpl getComparisonOperators() {
/*  75 */     return this.comparisonOperators;
/*     */   }
/*     */   
/*     */   public void setLogicalOperators(boolean logicalOperators) {
/*  79 */     this.logicalOperators = logicalOperators;
/*     */   }
/*     */   
/*     */   public boolean hasLogicalOperators() {
/*  82 */     return this.logicalOperators;
/*     */   }
/*     */   
/*     */   public static ComparisonOperatorsImpl toComparisonOperatorsImpl(ComparisonOperators comparisonOperators) {
/*  87 */     if (comparisonOperators == null)
/*  88 */       return new ComparisonOperatorsImpl(); 
/*  90 */     if (comparisonOperators instanceof ComparisonOperatorsImpl)
/*  91 */       return (ComparisonOperatorsImpl)comparisonOperators; 
/*  93 */     return new ComparisonOperatorsImpl(comparisonOperators);
/*     */   }
/*     */   
/*     */   private static ArithmeticOperatorsImpl toArithmeticOperatorsImpl(ArithmeticOperators arithmeticOperators) {
/*  98 */     if (arithmeticOperators == null)
/*  99 */       return new ArithmeticOperatorsImpl(); 
/* 100 */     if (arithmeticOperators instanceof ArithmeticOperatorsImpl)
/* 101 */       return (ArithmeticOperatorsImpl)arithmeticOperators; 
/* 103 */     return new ArithmeticOperatorsImpl(arithmeticOperators);
/*     */   }
/*     */   
/*     */   public void addAll(ScalarCapabilities copy) {
/* 107 */     if (copy == null)
/*     */       return; 
/* 108 */     if (copy.getArithmeticOperators() != null)
/* 109 */       this.arithmeticOperators.addAll(copy.getArithmeticOperators()); 
/* 111 */     if (copy.getComparisonOperators() != null)
/* 112 */       this.comparisonOperators.addAll(copy.getComparisonOperators()); 
/* 114 */     if (copy.hasLogicalOperators() == true)
/* 115 */       this.logicalOperators = true; 
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 121 */     int prime = 31;
/* 122 */     int result = 1;
/* 123 */     result = 31 * result + ((this.arithmeticOperators == null) ? 0 : this.arithmeticOperators.hashCode());
/* 125 */     result = 31 * result + ((this.comparisonOperators == null) ? 0 : this.comparisonOperators.hashCode());
/* 127 */     result = 31 * result + (this.logicalOperators ? 1231 : 1237);
/* 128 */     return result;
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/* 133 */     if (this == obj)
/* 134 */       return true; 
/* 135 */     if (obj == null)
/* 136 */       return false; 
/* 137 */     if (getClass() != obj.getClass())
/* 138 */       return false; 
/* 139 */     ScalarCapabilitiesImpl other = (ScalarCapabilitiesImpl)obj;
/* 140 */     if (this.arithmeticOperators == null) {
/* 141 */       if (other.arithmeticOperators != null)
/* 142 */         return false; 
/* 143 */     } else if (!this.arithmeticOperators.equals(other.arithmeticOperators)) {
/* 144 */       return false;
/*     */     } 
/* 145 */     if (this.comparisonOperators == null) {
/* 146 */       if (other.comparisonOperators != null)
/* 147 */         return false; 
/* 148 */     } else if (!this.comparisonOperators.equals(other.comparisonOperators)) {
/* 149 */       return false;
/*     */     } 
/* 150 */     if (this.logicalOperators != other.logicalOperators)
/* 151 */       return false; 
/* 152 */     return true;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\filter\capability\ScalarCapabilitiesImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */