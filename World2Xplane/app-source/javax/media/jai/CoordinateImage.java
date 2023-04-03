/*    */ package javax.media.jai;
/*    */ 
/*    */ public class CoordinateImage {
/*    */   public PlanarImage image;
/*    */   
/*    */   public Object coordinate;
/*    */   
/*    */   public CoordinateImage(PlanarImage pi, Object c) {
/* 44 */     if (pi == null || c == null)
/* 45 */       throw new IllegalArgumentException(JaiI18N.getString("Generic0")); 
/* 48 */     this.image = pi;
/* 49 */     this.coordinate = c;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\media\jai\CoordinateImage.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */