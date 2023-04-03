/*    */ package org.geotools.metadata.iso.quality;
/*    */ 
/*    */ import org.opengis.metadata.quality.QuantitativeAttributeAccuracy;
/*    */ import org.opengis.metadata.quality.ThematicAccuracy;
/*    */ 
/*    */ public class QuantitativeAttributeAccuracyImpl extends ThematicAccuracyImpl implements QuantitativeAttributeAccuracy {
/*    */   private static final long serialVersionUID = 7030401943270178746L;
/*    */   
/*    */   public QuantitativeAttributeAccuracyImpl() {}
/*    */   
/*    */   public QuantitativeAttributeAccuracyImpl(QuantitativeAttributeAccuracy source) {
/* 57 */     super((ThematicAccuracy)source);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\metadata\iso\quality\QuantitativeAttributeAccuracyImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */