/*    */ package org.openstreetmap.osmosis.core.domain.common;
/*    */ 
/*    */ import java.util.Date;
/*    */ 
/*    */ public abstract class TimestampFormat {
/*    */   public abstract String formatTimestamp(Date paramDate);
/*    */   
/*    */   public abstract Date parseTimestamp(String paramString);
/*    */   
/*    */   public boolean isEquivalent(TimestampFormat timestampFormat) {
/* 50 */     return getClass().equals(timestampFormat.getClass());
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\openstreetmap\osmosis\core\domain\common\TimestampFormat.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */