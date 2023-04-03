/*     */ package org.apache.commons.beanutils;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.lang.reflect.InvocationTargetException;
/*     */ import java.util.Comparator;
/*     */ import org.apache.commons.collections.comparators.ComparableComparator;
/*     */ 
/*     */ public class BeanComparator implements Comparator, Serializable {
/*     */   private String property;
/*     */   
/*     */   private Comparator comparator;
/*     */   
/*     */   public BeanComparator() {
/*  59 */     this(null);
/*     */   }
/*     */   
/*     */   public BeanComparator(String property) {
/*  81 */     this(property, (Comparator)ComparableComparator.getInstance());
/*     */   }
/*     */   
/*     */   public BeanComparator(String property, Comparator comparator) {
/* 101 */     setProperty(property);
/* 102 */     if (comparator != null) {
/* 103 */       this.comparator = comparator;
/*     */     } else {
/* 105 */       this.comparator = (Comparator)ComparableComparator.getInstance();
/*     */     } 
/*     */   }
/*     */   
/*     */   public void setProperty(String property) {
/* 116 */     this.property = property;
/*     */   }
/*     */   
/*     */   public String getProperty() {
/* 127 */     return this.property;
/*     */   }
/*     */   
/*     */   public Comparator getComparator() {
/* 137 */     return this.comparator;
/*     */   }
/*     */   
/*     */   public int compare(Object o1, Object o2) {
/* 151 */     if (this.property == null)
/* 153 */       return this.comparator.compare(o1, o2); 
/*     */     try {
/* 157 */       Object value1 = PropertyUtils.getProperty(o1, this.property);
/* 158 */       Object value2 = PropertyUtils.getProperty(o2, this.property);
/* 159 */       return this.comparator.compare(value1, value2);
/* 161 */     } catch (IllegalAccessException iae) {
/* 162 */       throw new RuntimeException("IllegalAccessException: " + iae.toString());
/* 164 */     } catch (InvocationTargetException ite) {
/* 165 */       throw new RuntimeException("InvocationTargetException: " + ite.toString());
/* 167 */     } catch (NoSuchMethodException nsme) {
/* 168 */       throw new RuntimeException("NoSuchMethodException: " + nsme.toString());
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean equals(Object o) {
/* 180 */     if (this == o)
/* 181 */       return true; 
/* 183 */     if (!(o instanceof BeanComparator))
/* 184 */       return false; 
/* 187 */     BeanComparator beanComparator = (BeanComparator)o;
/* 189 */     if (!this.comparator.equals(beanComparator.comparator))
/* 190 */       return false; 
/* 192 */     if (this.property != null) {
/* 194 */       if (!this.property.equals(beanComparator.property))
/* 195 */         return false; 
/*     */     } else {
/* 200 */       return (beanComparator.property == null);
/*     */     } 
/* 203 */     return true;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 212 */     int result = this.comparator.hashCode();
/* 213 */     return result;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\beanutils\BeanComparator.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */