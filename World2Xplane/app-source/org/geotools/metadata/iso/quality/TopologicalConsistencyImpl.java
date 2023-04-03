/*    */ package org.geotools.metadata.iso.quality;
/*    */ 
/*    */ import org.opengis.metadata.quality.LogicalConsistency;
/*    */ import org.opengis.metadata.quality.TopologicalConsistency;
/*    */ 
/*    */ public class TopologicalConsistencyImpl extends LogicalConsistencyImpl implements TopologicalConsistency {
/*    */   private static final long serialVersionUID = -255014076679068944L;
/*    */   
/*    */   public TopologicalConsistencyImpl() {}
/*    */   
/*    */   public TopologicalConsistencyImpl(TopologicalConsistency source) {
/* 58 */     super((LogicalConsistency)source);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\metadata\iso\quality\TopologicalConsistencyImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */