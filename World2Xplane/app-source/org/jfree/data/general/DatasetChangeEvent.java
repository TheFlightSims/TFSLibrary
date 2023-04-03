/*    */ package org.jfree.data.general;
/*    */ 
/*    */ import java.util.EventObject;
/*    */ 
/*    */ public class DatasetChangeEvent extends EventObject {
/*    */   private Dataset dataset;
/*    */   
/*    */   public DatasetChangeEvent(Object source, Dataset dataset) {
/* 73 */     super(source);
/* 74 */     this.dataset = dataset;
/*    */   }
/*    */   
/*    */   public Dataset getDataset() {
/* 85 */     return this.dataset;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\data\general\DatasetChangeEvent.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */