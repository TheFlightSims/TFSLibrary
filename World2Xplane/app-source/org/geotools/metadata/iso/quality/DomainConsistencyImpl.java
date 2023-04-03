/*    */ package org.geotools.metadata.iso.quality;
/*    */ 
/*    */ import org.opengis.metadata.quality.DomainConsistency;
/*    */ import org.opengis.metadata.quality.LogicalConsistency;
/*    */ 
/*    */ public class DomainConsistencyImpl extends LogicalConsistencyImpl implements DomainConsistency {
/*    */   private static final long serialVersionUID = -358082990944183859L;
/*    */   
/*    */   public DomainConsistencyImpl() {}
/*    */   
/*    */   public DomainConsistencyImpl(DomainConsistency source) {
/* 55 */     super((LogicalConsistency)source);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\metadata\iso\quality\DomainConsistencyImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */