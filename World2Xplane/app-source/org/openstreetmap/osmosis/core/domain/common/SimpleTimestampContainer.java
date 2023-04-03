/*    */ package org.openstreetmap.osmosis.core.domain.common;
/*    */ 
/*    */ import java.util.Date;
/*    */ 
/*    */ public class SimpleTimestampContainer implements TimestampContainer {
/*    */   private Date timestamp;
/*    */   
/*    */   public SimpleTimestampContainer(Date timestamp) {
/* 24 */     this.timestamp = timestamp;
/*    */   }
/*    */   
/*    */   public String getFormattedTimestamp(TimestampFormat timestampFormat) {
/* 33 */     return timestampFormat.formatTimestamp(this.timestamp);
/*    */   }
/*    */   
/*    */   public Date getTimestamp() {
/* 42 */     return this.timestamp;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\openstreetmap\osmosis\core\domain\common\SimpleTimestampContainer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */