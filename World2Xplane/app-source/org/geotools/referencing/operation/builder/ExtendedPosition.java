/*    */ package org.geotools.referencing.operation.builder;
/*    */ 
/*    */ import org.geotools.geometry.DirectPosition2D;
/*    */ import org.opengis.geometry.DirectPosition;
/*    */ 
/*    */ class ExtendedPosition extends DirectPosition2D {
/*    */   private static final long serialVersionUID = 4400395722009854165L;
/*    */   
/*    */   private DirectPosition mappedposition;
/*    */   
/*    */   public ExtendedPosition(DirectPosition c, DirectPosition mappedposition) {
/* 44 */     super(c);
/* 45 */     this.mappedposition = mappedposition;
/*    */   }
/*    */   
/*    */   public DirectPosition getMappedposition() {
/* 54 */     return this.mappedposition;
/*    */   }
/*    */   
/*    */   public void setMappedposition(DirectPosition mappedCoordinate) {
/* 63 */     this.mappedposition = mappedCoordinate;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\referencing\operation\builder\ExtendedPosition.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */