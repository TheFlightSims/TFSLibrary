/*     */ package org.geotools.filter.capability;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.Collection;
/*     */ import java.util.HashSet;
/*     */ import java.util.Set;
/*     */ import org.opengis.filter.capability.ComparisonOperators;
/*     */ import org.opengis.filter.capability.Operator;
/*     */ 
/*     */ public class ComparisonOperatorsImpl implements ComparisonOperators {
/*     */   Set<Operator> operators;
/*     */   
/*     */   public ComparisonOperatorsImpl() {
/*  43 */     this(new ArrayList<Operator>());
/*     */   }
/*     */   
/*     */   public ComparisonOperatorsImpl(ComparisonOperators copy) {
/*  51 */     this.operators = new HashSet<Operator>(copy.getOperators());
/*     */   }
/*     */   
/*     */   public ComparisonOperatorsImpl(Collection<Operator> operators) {
/*  55 */     this.operators = new HashSet<Operator>(operators);
/*     */   }
/*     */   
/*     */   public ComparisonOperatorsImpl(Operator[] operators) {
/*  59 */     if (operators == null)
/*  60 */       operators = new Operator[0]; 
/*  62 */     this.operators = new HashSet<Operator>(Arrays.asList(operators));
/*     */   }
/*     */   
/*     */   public Collection<Operator> getOperators() {
/*  66 */     if (this.operators == null)
/*  67 */       this.operators = new HashSet<Operator>(); 
/*  69 */     return this.operators;
/*     */   }
/*     */   
/*     */   public void setOperators(Collection<Operator> operators) {
/*  73 */     this.operators = new HashSet<Operator>(operators);
/*     */   }
/*     */   
/*     */   public Operator getOperator(String name) {
/*  79 */     if (name == null || this.operators == null)
/*  80 */       return null; 
/*  82 */     for (Operator operator : this.operators) {
/*  83 */       if (name.equals(operator.getName()))
/*  84 */         return operator; 
/*     */     } 
/*  87 */     return null;
/*     */   }
/*     */   
/*     */   public void addAll(ComparisonOperators copy) {
/*  91 */     if (copy.getOperators() != null)
/*  92 */       getOperators().addAll(copy.getOperators()); 
/*     */   }
/*     */   
/*     */   public String toString() {
/*  97 */     if (this.operators == null)
/*  98 */       return "ComparisonOperators: none"; 
/* 100 */     return "ComparisonOperators:" + this.operators;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 105 */     int prime = 31;
/* 106 */     int result = 1;
/* 107 */     result = 31 * result + ((this.operators == null) ? 0 : this.operators.hashCode());
/* 108 */     return result;
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/* 113 */     if (this == obj)
/* 114 */       return true; 
/* 115 */     if (obj == null)
/* 116 */       return false; 
/* 117 */     if (getClass() != obj.getClass())
/* 118 */       return false; 
/* 119 */     ComparisonOperatorsImpl other = (ComparisonOperatorsImpl)obj;
/* 120 */     if (this.operators == null) {
/* 121 */       if (other.operators != null)
/* 122 */         return false; 
/* 123 */     } else if (!this.operators.equals(other.operators)) {
/* 124 */       return false;
/*     */     } 
/* 125 */     return true;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\filter\capability\ComparisonOperatorsImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */