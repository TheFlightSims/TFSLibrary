/*    */ package org.geotools.metadata.iso.quality;
/*    */ 
/*    */ import org.opengis.metadata.quality.AbsoluteExternalPositionalAccuracy;
/*    */ import org.opengis.metadata.quality.PositionalAccuracy;
/*    */ import org.opengis.metadata.quality.Result;
/*    */ 
/*    */ public class AbsoluteExternalPositionalAccuracyImpl extends PositionalAccuracyImpl implements AbsoluteExternalPositionalAccuracy {
/*    */   private static final long serialVersionUID = 4116627805950579738L;
/*    */   
/*    */   public AbsoluteExternalPositionalAccuracyImpl() {}
/*    */   
/*    */   public AbsoluteExternalPositionalAccuracyImpl(Result result) {
/* 56 */     super(result);
/*    */   }
/*    */   
/*    */   public AbsoluteExternalPositionalAccuracyImpl(AbsoluteExternalPositionalAccuracy source) {
/* 65 */     super((PositionalAccuracy)source);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\metadata\iso\quality\AbsoluteExternalPositionalAccuracyImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */