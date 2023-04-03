/*     */ package org.geotools.feature;
/*     */ 
/*     */ import java.util.Comparator;
/*     */ import org.opengis.feature.simple.SimpleFeature;
/*     */ 
/*     */ public final class FeatureComparators {
/*  31 */   public static final Comparator BY_ID = new Comparator() {
/*     */       public int compare(Object o1, Object o2) {
/*  33 */         SimpleFeature f1 = (SimpleFeature)o1;
/*  34 */         SimpleFeature f2 = (SimpleFeature)o2;
/*  36 */         return f1.getID().compareTo(f2.getID());
/*     */       }
/*     */     };
/*     */   
/*     */   public static Comparator byAttributeIndex(int idx) {
/*  58 */     return new Index(idx);
/*     */   }
/*     */   
/*     */   public static Comparator byAttributeName(String name) {
/*  72 */     return new Name(name);
/*     */   }
/*     */   
/*     */   public static class Index implements Comparator {
/*     */     private final int idx;
/*     */     
/*     */     public Index(int i) {
/*  89 */       this.idx = i;
/*     */     }
/*     */     
/*     */     public int compare(Object o1, Object o2) {
/* 102 */       SimpleFeature f1 = (SimpleFeature)o1;
/* 103 */       SimpleFeature f2 = (SimpleFeature)o2;
/* 105 */       return compareAtts(f1.getAttribute(this.idx), f2.getAttribute(this.idx));
/*     */     }
/*     */     
/*     */     protected int compareAtts(Object att1, Object att2) {
/* 117 */       return ((Comparable<Comparable>)att1).compareTo((Comparable)att2);
/*     */     }
/*     */   }
/*     */   
/*     */   public static class Name implements Comparator {
/*     */     private final String name;
/*     */     
/*     */     public Name(String name) {
/* 135 */       this.name = name;
/*     */     }
/*     */     
/*     */     public int compare(Object o1, Object o2) {
/* 148 */       SimpleFeature f1 = (SimpleFeature)o1;
/* 149 */       SimpleFeature f2 = (SimpleFeature)o2;
/* 151 */       return compareAtts(f1.getAttribute(this.name), f2.getAttribute(this.name));
/*     */     }
/*     */     
/*     */     protected int compareAtts(Object att1, Object att2) {
/* 163 */       if (att1 == null && att2 == null)
/* 164 */         return 0; 
/* 167 */       if (att1 == null)
/* 168 */         return -1; 
/* 171 */       if (att2 == null)
/* 172 */         return 1; 
/* 175 */       return ((Comparable<Comparable>)att1).compareTo((Comparable)att2);
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\feature\FeatureComparators.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */