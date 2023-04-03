/*    */ package org.geotools.referencing.factory.epsg;
/*    */ 
/*    */ import org.geotools.factory.Hints;
/*    */ 
/*    */ public class DefaultFactory extends ThreadedEpsgFactory {
/*    */   public DefaultFactory() {}
/*    */   
/*    */   public DefaultFactory(Hints userHints) {
/* 46 */     super(userHints);
/*    */   }
/*    */   
/*    */   public DefaultFactory(Hints userHints, int priority) {
/* 55 */     super(userHints, priority);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\referencing\factory\epsg\DefaultFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */