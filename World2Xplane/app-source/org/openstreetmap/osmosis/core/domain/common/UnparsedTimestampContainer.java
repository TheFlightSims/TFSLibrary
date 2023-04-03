/*    */ package org.openstreetmap.osmosis.core.domain.common;
/*    */ 
/*    */ import java.util.Date;
/*    */ import org.openstreetmap.osmosis.core.OsmosisRuntimeException;
/*    */ 
/*    */ public class UnparsedTimestampContainer implements TimestampContainer {
/*    */   private TimestampFormat managedTimestampFormat;
/*    */   
/*    */   private String timestampString;
/*    */   
/*    */   private Date timestamp;
/*    */   
/*    */   public UnparsedTimestampContainer(TimestampFormat timestampFormat, String timestampString) {
/* 30 */     this.managedTimestampFormat = timestampFormat;
/* 31 */     this.timestampString = timestampString;
/* 33 */     if (timestampString == null)
/* 34 */       throw new OsmosisRuntimeException("The entity timestamp attribute is missing."); 
/*    */   }
/*    */   
/*    */   public String getFormattedTimestamp(TimestampFormat timestampFormat) {
/* 44 */     if (this.timestampString != null && this.managedTimestampFormat.isEquivalent(timestampFormat))
/* 45 */       return this.timestampString; 
/* 49 */     getTimestamp();
/* 51 */     if (this.timestamp != null)
/* 52 */       return timestampFormat.formatTimestamp(this.timestamp); 
/* 54 */     return "";
/*    */   }
/*    */   
/*    */   public Date getTimestamp() {
/* 64 */     if (this.timestamp == null && this.timestampString != null && this.timestampString.length() > 0)
/* 65 */       this.timestamp = this.managedTimestampFormat.parseTimestamp(this.timestampString); 
/* 68 */     return this.timestamp;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\openstreetmap\osmosis\core\domain\common\UnparsedTimestampContainer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */