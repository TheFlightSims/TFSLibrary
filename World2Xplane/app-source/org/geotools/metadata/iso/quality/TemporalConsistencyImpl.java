/*    */ package org.geotools.metadata.iso.quality;
/*    */ 
/*    */ import org.opengis.metadata.quality.TemporalAccuracy;
/*    */ import org.opengis.metadata.quality.TemporalConsistency;
/*    */ 
/*    */ public class TemporalConsistencyImpl extends TemporalAccuracyImpl implements TemporalConsistency {
/*    */   private static final long serialVersionUID = -2549290466982699190L;
/*    */   
/*    */   public TemporalConsistencyImpl() {}
/*    */   
/*    */   public TemporalConsistencyImpl(TemporalConsistency source) {
/* 55 */     super((TemporalAccuracy)source);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\metadata\iso\quality\TemporalConsistencyImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */