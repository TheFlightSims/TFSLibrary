/*    */ package org.openstreetmap.osmosis.core.util;
/*    */ 
/*    */ import org.openstreetmap.osmosis.core.OsmosisRuntimeException;
/*    */ 
/*    */ public final class IntAsChar {
/*    */   public static char intToChar(int value) {
/* 31 */     if (value > 65535)
/* 32 */       throw new OsmosisRuntimeException("Cannot represent " + value + " as a char."); 
/* 34 */     if (value < 0)
/* 35 */       throw new OsmosisRuntimeException("Cannot represent " + value + " as a char."); 
/* 38 */     return (char)value;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\openstreetmap\osmosis\cor\\util\IntAsChar.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */