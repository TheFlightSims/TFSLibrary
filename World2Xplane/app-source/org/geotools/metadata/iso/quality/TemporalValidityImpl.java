/*    */ package org.geotools.metadata.iso.quality;
/*    */ 
/*    */ import org.opengis.metadata.quality.TemporalAccuracy;
/*    */ import org.opengis.metadata.quality.TemporalValidity;
/*    */ 
/*    */ public class TemporalValidityImpl extends TemporalAccuracyImpl implements TemporalValidity {
/*    */   private static final long serialVersionUID = 2866684429712027839L;
/*    */   
/*    */   public TemporalValidityImpl() {}
/*    */   
/*    */   public TemporalValidityImpl(TemporalValidity source) {
/* 55 */     super((TemporalAccuracy)source);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\metadata\iso\quality\TemporalValidityImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */