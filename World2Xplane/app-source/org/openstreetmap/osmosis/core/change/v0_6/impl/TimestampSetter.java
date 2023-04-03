/*    */ package org.openstreetmap.osmosis.core.change.v0_6.impl;
/*    */ 
/*    */ import java.util.Calendar;
/*    */ import org.openstreetmap.osmosis.core.container.v0_6.EntityContainer;
/*    */ import org.openstreetmap.osmosis.core.domain.common.SimpleTimestampContainer;
/*    */ import org.openstreetmap.osmosis.core.domain.common.TimestampContainer;
/*    */ 
/*    */ public class TimestampSetter {
/*    */   private TimestampContainer timestamp;
/*    */   
/*    */   public TimestampSetter() {
/* 25 */     Calendar calendar = Calendar.getInstance();
/* 26 */     calendar.set(14, 0);
/* 27 */     this.timestamp = (TimestampContainer)new SimpleTimestampContainer(calendar.getTime());
/*    */   }
/*    */   
/*    */   public EntityContainer updateTimestamp(EntityContainer entityContainer) {
/* 42 */     EntityContainer resultContainer = entityContainer.getWriteableInstance();
/* 43 */     resultContainer.getEntity().setTimestampContainer(this.timestamp);
/* 45 */     return resultContainer;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\openstreetmap\osmosis\core\change\v0_6\impl\TimestampSetter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */