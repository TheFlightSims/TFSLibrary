/*    */ package org.geotools.styling;
/*    */ 
/*    */ public class StyledLayerImpl implements StyledLayer {
/*    */   protected String name;
/*    */   
/*    */   public String getName() {
/* 33 */     return this.name;
/*    */   }
/*    */   
/*    */   public void setName(String name) {
/* 37 */     if (name == this.name || (name != null && name.equals(this.name)))
/*    */       return; 
/* 40 */     this.name = name;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\styling\StyledLayerImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */