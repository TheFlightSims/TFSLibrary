/*    */ package org.apache.commons.collections.comparators;
/*    */ 
/*    */ import java.util.Comparator;
/*    */ import org.apache.commons.collections.Transformer;
/*    */ 
/*    */ public class TransformingComparator implements Comparator {
/*    */   protected Comparator decorated;
/*    */   
/*    */   protected Transformer transformer;
/*    */   
/*    */   public TransformingComparator(Transformer transformer) {
/* 49 */     this(transformer, new ComparableComparator());
/*    */   }
/*    */   
/*    */   public TransformingComparator(Transformer transformer, Comparator decorated) {
/* 59 */     this.decorated = decorated;
/* 60 */     this.transformer = transformer;
/*    */   }
/*    */   
/*    */   public int compare(Object obj1, Object obj2) {
/* 72 */     Object value1 = this.transformer.transform(obj1);
/* 73 */     Object value2 = this.transformer.transform(obj2);
/* 74 */     return this.decorated.compare(value1, value2);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\collections\comparators\TransformingComparator.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */