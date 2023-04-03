/*    */ package org.geotools.filter.function;
/*    */ 
/*    */ import java.util.Set;
/*    */ 
/*    */ public final class ExplicitClassifier extends Classifier {
/* 31 */   Set[] values = null;
/*    */   
/*    */   public ExplicitClassifier(Set[] values) {
/* 34 */     this.values = values;
/* 36 */     this.titles = new String[values.length];
/* 37 */     for (int i = 0; i < this.titles.length; i++) {
/* 38 */       Object[] set = values[i].toArray();
/* 39 */       if (set.length > 0) {
/* 40 */         StringBuffer title = new StringBuffer((set[0] == null) ? "null" : set[0].toString());
/* 41 */         for (int j = 1; j < set.length; j++) {
/* 42 */           title.append(", ");
/* 43 */           title.append((set[j] == null) ? "null" : set[j].toString());
/*    */         } 
/* 45 */         this.titles[i] = title.toString();
/*    */       } else {
/* 47 */         this.titles[i] = "";
/*    */       } 
/*    */     } 
/*    */   }
/*    */   
/*    */   public int getSize() {
/* 53 */     return this.values.length;
/*    */   }
/*    */   
/*    */   public Set getValues(int index) {
/* 63 */     return this.values[index];
/*    */   }
/*    */   
/*    */   public int classify(Object value) {
/* 68 */     for (int i = 0; i < this.values.length; i++) {
/* 69 */       if (this.values[i].contains(value))
/* 70 */         return i; 
/*    */     } 
/* 73 */     return -1;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\filter\function\ExplicitClassifier.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */