/*     */ package org.apache.commons.math3.util;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.Collection;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import org.apache.commons.math3.exception.MathIllegalArgumentException;
/*     */ 
/*     */ public class TransformerMap implements NumberTransformer, Serializable {
/*     */   private static final long serialVersionUID = 4605318041528645258L;
/*     */   
/*  42 */   private NumberTransformer defaultTransformer = null;
/*     */   
/*  47 */   private Map<Class<?>, NumberTransformer> map = null;
/*     */   
/*     */   public TransformerMap() {
/*  53 */     this.map = new HashMap<Class<?>, NumberTransformer>();
/*  54 */     this.defaultTransformer = new DefaultTransformer();
/*     */   }
/*     */   
/*     */   public boolean containsClass(Class<?> key) {
/*  63 */     return this.map.containsKey(key);
/*     */   }
/*     */   
/*     */   public boolean containsTransformer(NumberTransformer value) {
/*  72 */     return this.map.containsValue(value);
/*     */   }
/*     */   
/*     */   public NumberTransformer getTransformer(Class<?> key) {
/*  82 */     return this.map.get(key);
/*     */   }
/*     */   
/*     */   public NumberTransformer putTransformer(Class<?> key, NumberTransformer transformer) {
/*  94 */     return this.map.put(key, transformer);
/*     */   }
/*     */   
/*     */   public NumberTransformer removeTransformer(Class<?> key) {
/* 104 */     return this.map.remove(key);
/*     */   }
/*     */   
/*     */   public void clear() {
/* 111 */     this.map.clear();
/*     */   }
/*     */   
/*     */   public Set<Class<?>> classes() {
/* 119 */     return this.map.keySet();
/*     */   }
/*     */   
/*     */   public Collection<NumberTransformer> transformers() {
/* 128 */     return this.map.values();
/*     */   }
/*     */   
/*     */   public double transform(Object o) throws MathIllegalArgumentException {
/* 142 */     double value = Double.NaN;
/* 144 */     if (o instanceof Number || o instanceof String) {
/* 145 */       value = this.defaultTransformer.transform(o);
/*     */     } else {
/* 147 */       NumberTransformer trans = getTransformer(o.getClass());
/* 148 */       if (trans != null)
/* 149 */         value = trans.transform(o); 
/*     */     } 
/* 153 */     return value;
/*     */   }
/*     */   
/*     */   public boolean equals(Object other) {
/* 159 */     if (this == other)
/* 160 */       return true; 
/* 162 */     if (other instanceof TransformerMap) {
/* 163 */       TransformerMap rhs = (TransformerMap)other;
/* 164 */       if (!this.defaultTransformer.equals(rhs.defaultTransformer))
/* 165 */         return false; 
/* 167 */       if (this.map.size() != rhs.map.size())
/* 168 */         return false; 
/* 170 */       for (Map.Entry<Class<?>, NumberTransformer> entry : this.map.entrySet()) {
/* 171 */         if (!((NumberTransformer)entry.getValue()).equals(rhs.map.get(entry.getKey())))
/* 172 */           return false; 
/*     */       } 
/* 175 */       return true;
/*     */     } 
/* 177 */     return false;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 183 */     int hash = this.defaultTransformer.hashCode();
/* 184 */     for (NumberTransformer t : this.map.values())
/* 185 */       hash = hash * 31 + t.hashCode(); 
/* 187 */     return hash;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math\\util\TransformerMap.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */