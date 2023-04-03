/*    */ package org.geotools.metadata.iso.quality;
/*    */ 
/*    */ import org.opengis.metadata.quality.ThematicAccuracy;
/*    */ import org.opengis.metadata.quality.ThematicClassificationCorrectness;
/*    */ 
/*    */ public class ThematicClassificationCorrectnessImpl extends ThematicAccuracyImpl implements ThematicClassificationCorrectness {
/*    */   private static final long serialVersionUID = -5484398738783800915L;
/*    */   
/*    */   public ThematicClassificationCorrectnessImpl() {}
/*    */   
/*    */   public ThematicClassificationCorrectnessImpl(ThematicClassificationCorrectness source) {
/* 57 */     super((ThematicAccuracy)source);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\metadata\iso\quality\ThematicClassificationCorrectnessImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */