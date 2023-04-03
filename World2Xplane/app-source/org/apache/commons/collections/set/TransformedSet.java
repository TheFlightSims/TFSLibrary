/*    */ package org.apache.commons.collections.set;
/*    */ 
/*    */ import java.util.Set;
/*    */ import org.apache.commons.collections.Transformer;
/*    */ import org.apache.commons.collections.collection.TransformedCollection;
/*    */ 
/*    */ public class TransformedSet extends TransformedCollection implements Set {
/*    */   private static final long serialVersionUID = 306127383500410386L;
/*    */   
/*    */   public static Set decorate(Set set, Transformer transformer) {
/* 55 */     return new TransformedSet(set, transformer);
/*    */   }
/*    */   
/*    */   protected TransformedSet(Set set, Transformer transformer) {
/* 70 */     super(set, transformer);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\collections\set\TransformedSet.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */