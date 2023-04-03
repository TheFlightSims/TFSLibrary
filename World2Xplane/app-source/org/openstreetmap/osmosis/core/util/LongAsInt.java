/*    */ package org.openstreetmap.osmosis.core.util;
/*    */ 
/*    */ import org.openstreetmap.osmosis.core.OsmosisRuntimeException;
/*    */ 
/*    */ public final class LongAsInt {
/*    */   public static int longToInt(long value) {
/* 32 */     if (value > 2147483647L)
/* 33 */       throw new OsmosisRuntimeException("Cannot represent " + value + " as an integer."); 
/* 35 */     if (value < -2147483648L)
/* 36 */       throw new OsmosisRuntimeException("Cannot represent " + value + " as an integer."); 
/* 39 */     return (int)value;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\openstreetmap\osmosis\cor\\util\LongAsInt.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */