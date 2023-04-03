/*    */ package com.vividsolutions.jts.index.strtree;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ 
/*    */ public class ItemBoundable implements Boundable, Serializable {
/*    */   private Object bounds;
/*    */   
/*    */   private Object item;
/*    */   
/*    */   public ItemBoundable(Object bounds, Object item) {
/* 49 */     this.bounds = bounds;
/* 50 */     this.item = item;
/*    */   }
/*    */   
/*    */   public Object getBounds() {
/* 54 */     return this.bounds;
/*    */   }
/*    */   
/*    */   public Object getItem() {
/* 57 */     return this.item;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\index\strtree\ItemBoundable.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */