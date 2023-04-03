/*    */ package org.geotools.metadata.iso.quality;
/*    */ 
/*    */ import org.opengis.metadata.quality.Completeness;
/*    */ import org.opengis.metadata.quality.CompletenessOmission;
/*    */ 
/*    */ public class CompletenessOmissionImpl extends CompletenessImpl implements CompletenessOmission {
/*    */   private static final long serialVersionUID = 6614084398532053054L;
/*    */   
/*    */   public CompletenessOmissionImpl() {}
/*    */   
/*    */   public CompletenessOmissionImpl(CompletenessOmission source) {
/* 55 */     super((Completeness)source);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\metadata\iso\quality\CompletenessOmissionImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */