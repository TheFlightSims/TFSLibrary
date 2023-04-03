/*     */ package org.geotools.filter.capability;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.HashSet;
/*     */ import java.util.Set;
/*     */ import org.opengis.filter.capability.SpatialOperator;
/*     */ import org.opengis.filter.capability.SpatialOperators;
/*     */ 
/*     */ public class SpatialOperatorsImpl implements SpatialOperators {
/*     */   Set<SpatialOperator> operators;
/*     */   
/*     */   public SpatialOperatorsImpl() {
/*  44 */     this(new ArrayList<SpatialOperator>());
/*     */   }
/*     */   
/*     */   public SpatialOperatorsImpl(Collection<SpatialOperator> operators) {
/*  47 */     this.operators = new HashSet<SpatialOperator>();
/*  48 */     if (operators != null)
/*  49 */       for (SpatialOperator operator : operators)
/*  50 */         this.operators.add(new SpatialOperatorImpl(operator));  
/*     */   }
/*     */   
/*     */   public SpatialOperatorsImpl(SpatialOperator[] operators) {
/*  55 */     this.operators = new HashSet<SpatialOperator>();
/*  56 */     if (operators != null)
/*  57 */       for (SpatialOperator operator : operators)
/*  58 */         this.operators.add(new SpatialOperatorImpl(operator));  
/*     */   }
/*     */   
/*     */   public SpatialOperatorsImpl(SpatialOperators copy) {
/*  64 */     this.operators = new HashSet<SpatialOperator>();
/*  65 */     if (copy.getOperators() != null)
/*  66 */       for (SpatialOperator operator : copy.getOperators())
/*  67 */         this.operators.add(new SpatialOperatorImpl(operator));  
/*     */   }
/*     */   
/*     */   public void setOperators(Collection<SpatialOperator> operators) {
/*  73 */     this.operators = new HashSet<SpatialOperator>();
/*  74 */     if (operators != null)
/*  75 */       for (SpatialOperator operator : operators)
/*  76 */         this.operators.add(new SpatialOperatorImpl(operator));  
/*     */   }
/*     */   
/*     */   public Collection<SpatialOperator> getOperators() {
/*  82 */     return this.operators;
/*     */   }
/*     */   
/*     */   public SpatialOperator getOperator(String name) {
/*  85 */     if (name == null || this.operators == null)
/*  86 */       return null; 
/*  89 */     for (SpatialOperator spatialOperator : this.operators) {
/*  90 */       if (name.equals(spatialOperator.getName()))
/*  91 */         return spatialOperator; 
/*     */     } 
/*  95 */     return null;
/*     */   }
/*     */   
/*     */   public void addAll(SpatialOperators copy) {
/*  99 */     if (copy == null)
/*     */       return; 
/* 100 */     if (copy.getOperators() != null)
/* 101 */       for (SpatialOperator operator : copy.getOperators())
/* 102 */         this.operators.add(new SpatialOperatorImpl(operator));  
/*     */   }
/*     */   
/*     */   public String toString() {
/* 108 */     if (this.operators == null)
/* 109 */       return "SpatialOperators: none"; 
/* 111 */     return "SpatialOperators:" + this.operators;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 115 */     int prime = 31;
/* 116 */     int result = 1;
/* 117 */     result = 31 * result + ((this.operators == null) ? 0 : this.operators.hashCode());
/* 118 */     return result;
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/* 122 */     if (this == obj)
/* 123 */       return true; 
/* 124 */     if (obj == null)
/* 125 */       return false; 
/* 126 */     if (getClass() != obj.getClass())
/* 127 */       return false; 
/* 128 */     SpatialOperatorsImpl other = (SpatialOperatorsImpl)obj;
/* 129 */     if (this.operators == null) {
/* 130 */       if (other.operators != null)
/* 131 */         return false; 
/* 132 */     } else if (!this.operators.equals(other.operators)) {
/* 133 */       return false;
/*     */     } 
/* 134 */     return true;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\filter\capability\SpatialOperatorsImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */