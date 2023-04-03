/*    */ package javax.media.jai;
/*    */ 
/*    */ public class SequentialImage {
/*    */   public PlanarImage image;
/*    */   
/*    */   public float timeStamp;
/*    */   
/*    */   public Object cameraPosition;
/*    */   
/*    */   public SequentialImage(PlanarImage pi, float ts, Object cp) {
/* 74 */     if (pi == null)
/* 75 */       throw new IllegalArgumentException(JaiI18N.getString("Generic0")); 
/* 78 */     this.image = pi;
/* 79 */     this.timeStamp = ts;
/* 80 */     this.cameraPosition = cp;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\media\jai\SequentialImage.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */