/*    */ package org.geotools.metadata.iso.quality;
/*    */ 
/*    */ import org.opengis.metadata.quality.ConceptualConsistency;
/*    */ import org.opengis.metadata.quality.LogicalConsistency;
/*    */ 
/*    */ public class ConceptualConsistencyImpl extends LogicalConsistencyImpl implements ConceptualConsistency {
/*    */   private static final long serialVersionUID = 7143342570712197486L;
/*    */   
/*    */   public ConceptualConsistencyImpl() {}
/*    */   
/*    */   public ConceptualConsistencyImpl(ConceptualConsistency source) {
/* 57 */     super((LogicalConsistency)source);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\metadata\iso\quality\ConceptualConsistencyImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */