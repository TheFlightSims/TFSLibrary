/*    */ package org.geotools.metadata.iso.quality;
/*    */ 
/*    */ import org.opengis.metadata.quality.NonQuantitativeAttributeAccuracy;
/*    */ import org.opengis.metadata.quality.ThematicAccuracy;
/*    */ 
/*    */ public class NonQuantitativeAttributeAccuracyImpl extends ThematicAccuracyImpl implements NonQuantitativeAttributeAccuracy {
/*    */   private static final long serialVersionUID = 2659617465862554345L;
/*    */   
/*    */   public NonQuantitativeAttributeAccuracyImpl() {}
/*    */   
/*    */   public NonQuantitativeAttributeAccuracyImpl(NonQuantitativeAttributeAccuracy source) {
/* 56 */     super((ThematicAccuracy)source);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\metadata\iso\quality\NonQuantitativeAttributeAccuracyImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */