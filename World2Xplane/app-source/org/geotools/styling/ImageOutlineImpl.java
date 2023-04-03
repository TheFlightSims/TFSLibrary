/*    */ package org.geotools.styling;
/*    */ 
/*    */ import org.geotools.util.Utilities;
/*    */ 
/*    */ public class ImageOutlineImpl implements ImageOutline {
/*    */   Symbolizer symbolizer;
/*    */   
/*    */   public Symbolizer getSymbolizer() {
/* 31 */     return this.symbolizer;
/*    */   }
/*    */   
/*    */   public void setSymbolizer(Symbolizer symbolizer) {
/* 35 */     if (symbolizer instanceof LineSymbolizer || symbolizer instanceof PolygonSymbolizer) {
/* 39 */       this.symbolizer = symbolizer;
/*    */     } else {
/* 42 */       throw new IllegalArgumentException("Symbolizer must be Line or Polygon.");
/*    */     } 
/*    */   }
/*    */   
/*    */   public boolean equals(Object obj) {
/* 47 */     if (this == obj)
/* 48 */       return true; 
/* 51 */     if (obj instanceof ImageOutlineImpl) {
/* 52 */       ImageOutlineImpl other = (ImageOutlineImpl)obj;
/* 53 */       return Utilities.equals(this.symbolizer, other.symbolizer);
/*    */     } 
/* 56 */     return false;
/*    */   }
/*    */   
/*    */   public int hashCode() {
/* 60 */     int PRIME = 1000003;
/* 61 */     int result = 0;
/* 63 */     if (this.symbolizer != null)
/* 64 */       result = 1000003 * result + this.symbolizer.hashCode(); 
/* 67 */     return result;
/*    */   }
/*    */   
/*    */   public void accept(StyleVisitor visitor) {
/* 71 */     visitor.visit(this);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\styling\ImageOutlineImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */