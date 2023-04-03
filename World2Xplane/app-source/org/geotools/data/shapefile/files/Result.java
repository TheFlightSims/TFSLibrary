/*    */ package org.geotools.data.shapefile.files;
/*    */ 
/*    */ public class Result<V, S> {
/*    */   public final V value;
/*    */   
/*    */   public final S state;
/*    */   
/*    */   public Result(V value, S state) {
/* 38 */     this.value = value;
/* 39 */     this.state = state;
/*    */   }
/*    */   
/*    */   public String toString() {
/* 44 */     return "State: " + this.state + " value: " + this.value;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\data\shapefile\files\Result.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */