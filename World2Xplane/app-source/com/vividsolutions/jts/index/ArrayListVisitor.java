/*    */ package com.vividsolutions.jts.index;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ public class ArrayListVisitor implements ItemVisitor {
/* 46 */   private ArrayList items = new ArrayList();
/*    */   
/*    */   public void visitItem(Object item) {
/* 52 */     this.items.add(item);
/*    */   }
/*    */   
/*    */   public ArrayList getItems() {
/* 55 */     return this.items;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\index\ArrayListVisitor.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */