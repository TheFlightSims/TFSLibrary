/*     */ package org.geotools.filter.capability;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.Collection;
/*     */ import java.util.HashSet;
/*     */ import java.util.Set;
/*     */ import org.opengis.filter.capability.FunctionName;
/*     */ import org.opengis.filter.capability.Functions;
/*     */ 
/*     */ public class FunctionsImpl implements Functions {
/*     */   Set<FunctionName> functionNames;
/*     */   
/*     */   public FunctionsImpl() {
/*  43 */     this(new ArrayList<FunctionName>());
/*     */   }
/*     */   
/*     */   public FunctionsImpl(Collection<FunctionName> functionNames) {
/*  46 */     this.functionNames = new HashSet<FunctionName>(functionNames);
/*     */   }
/*     */   
/*     */   public FunctionsImpl(FunctionName[] functionNames) {
/*  49 */     if (functionNames == null)
/*  50 */       functionNames = new FunctionName[0]; 
/*  53 */     this.functionNames = new HashSet<FunctionName>(Arrays.asList(functionNames));
/*     */   }
/*     */   
/*     */   public FunctionsImpl(Functions copy) {
/*  58 */     this.functionNames = new HashSet<FunctionName>();
/*  59 */     if (copy.getFunctionNames() != null)
/*  60 */       for (FunctionName functionName : copy.getFunctionNames())
/*  61 */         this.functionNames.add(new FunctionNameImpl(functionName));  
/*     */   }
/*     */   
/*     */   public Collection<FunctionName> getFunctionNames() {
/*  67 */     return this.functionNames;
/*     */   }
/*     */   
/*     */   public void setFunctionNames(Collection<FunctionName> functionNames) {
/*  71 */     this.functionNames = new HashSet<FunctionName>(functionNames);
/*     */   }
/*     */   
/*     */   public FunctionName getFunctionName(String name) {
/*  75 */     if (name == null || this.functionNames == null)
/*  76 */       return null; 
/*  79 */     for (FunctionName functionName : this.functionNames) {
/*  80 */       if (name.equals(functionName.getName()))
/*  81 */         return functionName; 
/*     */     } 
/*  84 */     return null;
/*     */   }
/*     */   
/*     */   public void addAll(Functions copy) {
/*  88 */     if (copy == null)
/*     */       return; 
/*  89 */     if (copy.getFunctionNames() != null)
/*  90 */       for (FunctionName functionName : copy.getFunctionNames())
/*  91 */         this.functionNames.add(new FunctionNameImpl(functionName));  
/*     */   }
/*     */   
/*     */   public String toString() {
/*  97 */     StringBuffer buf = new StringBuffer();
/*  98 */     buf.append("FunctionsImpl[");
/*  99 */     if (this.functionNames != null) {
/* 100 */       buf.append("with ");
/* 101 */       buf.append(this.functionNames.size());
/* 102 */       buf.append(" functions");
/*     */     } 
/* 104 */     buf.append("]");
/* 105 */     return buf.toString();
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 109 */     int prime = 31;
/* 110 */     int result = 1;
/* 111 */     result = 31 * result + ((this.functionNames == null) ? 0 : this.functionNames.hashCode());
/* 112 */     return result;
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/* 116 */     if (this == obj)
/* 117 */       return true; 
/* 118 */     if (obj == null)
/* 119 */       return false; 
/* 120 */     if (getClass() != obj.getClass())
/* 121 */       return false; 
/* 122 */     FunctionsImpl other = (FunctionsImpl)obj;
/* 123 */     if (this.functionNames == null) {
/* 124 */       if (other.functionNames != null)
/* 125 */         return false; 
/* 126 */     } else if (!this.functionNames.equals(other.functionNames)) {
/* 127 */       return false;
/*     */     } 
/* 128 */     return true;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\filter\capability\FunctionsImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */