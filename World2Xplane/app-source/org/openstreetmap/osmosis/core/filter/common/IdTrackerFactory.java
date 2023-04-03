/*    */ package org.openstreetmap.osmosis.core.filter.common;
/*    */ 
/*    */ import org.openstreetmap.osmosis.core.OsmosisRuntimeException;
/*    */ 
/*    */ public final class IdTrackerFactory {
/*    */   public static IdTracker createInstance(IdTrackerType idTrackerType) {
/* 30 */     if (IdTrackerType.BitSet.equals(idTrackerType))
/* 31 */       return new BitSetIdTracker(); 
/* 32 */     if (IdTrackerType.IdList.equals(idTrackerType))
/* 33 */       return new ListIdTracker(); 
/* 34 */     if (IdTrackerType.Dynamic.equals(idTrackerType))
/* 35 */       return new DynamicIdTracker(); 
/* 37 */     throw new OsmosisRuntimeException("The IdTrackerType " + idTrackerType + " is not recognised.");
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\openstreetmap\osmosis\core\filter\common\IdTrackerFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */