/*    */ package com.jhlabs.map.proj;
/*    */ 
/*    */ public class OtherProjections {
/*    */   public Object createPlugin() {
/* 27 */     return new Object[] { new SimpleConicProjection(5), new SimpleConicProjection(1), new SimpleConicProjection(2), new SimpleConicProjection(3), new SimpleConicProjection(4), new SimpleConicProjection(6), new MolleweideProjection(1), new MolleweideProjection(2) };
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\jhlabs\map\proj\OtherProjections.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */