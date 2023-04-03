/*     */ package org.jfree.data;
/*     */ 
/*     */ import java.util.Comparator;
/*     */ import org.jfree.util.SortOrder;
/*     */ 
/*     */ public class KeyedValueComparator implements Comparator {
/*     */   private KeyedValueComparatorType type;
/*     */   
/*     */   private SortOrder order;
/*     */   
/*     */   public KeyedValueComparator(KeyedValueComparatorType type, SortOrder order) {
/*  72 */     if (order == null)
/*  73 */       throw new IllegalArgumentException("Null 'order' argument."); 
/*  75 */     this.type = type;
/*  76 */     this.order = order;
/*     */   }
/*     */   
/*     */   public KeyedValueComparatorType getType() {
/*  85 */     return this.type;
/*     */   }
/*     */   
/*     */   public SortOrder getOrder() {
/*  94 */     return this.order;
/*     */   }
/*     */   
/*     */   public int compare(Object o1, Object o2) {
/*     */     int result;
/* 108 */     if (o2 == null)
/* 109 */       return -1; 
/* 111 */     if (o1 == null)
/* 112 */       return 1; 
/* 117 */     KeyedValue kv1 = (KeyedValue)o1;
/* 118 */     KeyedValue kv2 = (KeyedValue)o2;
/* 120 */     if (this.type == KeyedValueComparatorType.BY_KEY) {
/* 121 */       if (this.order.equals(SortOrder.ASCENDING)) {
/* 122 */         result = kv1.getKey().compareTo(kv2.getKey());
/* 124 */       } else if (this.order.equals(SortOrder.DESCENDING)) {
/* 125 */         result = kv2.getKey().compareTo(kv1.getKey());
/*     */       } else {
/* 128 */         throw new IllegalArgumentException("Unrecognised sort order.");
/*     */       } 
/* 131 */     } else if (this.type == KeyedValueComparatorType.BY_VALUE) {
/* 132 */       Number n1 = kv1.getValue();
/* 133 */       Number n2 = kv2.getValue();
/* 134 */       if (n2 == null)
/* 135 */         return -1; 
/* 137 */       if (n1 == null)
/* 138 */         return 1; 
/* 140 */       double d1 = n1.doubleValue();
/* 141 */       double d2 = n2.doubleValue();
/* 142 */       if (this.order.equals(SortOrder.ASCENDING)) {
/* 143 */         if (d1 > d2) {
/* 144 */           result = 1;
/* 146 */         } else if (d1 < d2) {
/* 147 */           result = -1;
/*     */         } else {
/* 150 */           result = 0;
/*     */         } 
/* 153 */       } else if (this.order.equals(SortOrder.DESCENDING)) {
/* 154 */         if (d1 > d2) {
/* 155 */           result = -1;
/* 157 */         } else if (d1 < d2) {
/* 158 */           result = 1;
/*     */         } else {
/* 161 */           result = 0;
/*     */         } 
/*     */       } else {
/* 165 */         throw new IllegalArgumentException("Unrecognised sort order.");
/*     */       } 
/*     */     } else {
/* 169 */       throw new IllegalArgumentException("Unrecognised type.");
/*     */     } 
/* 172 */     return result;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\data\KeyedValueComparator.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */