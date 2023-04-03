/*    */ package com.vividsolutions.jts.index.sweepline;
/*    */ 
/*    */ public class SweepLineEvent implements Comparable {
/*    */   public static final int INSERT = 1;
/*    */   
/*    */   public static final int DELETE = 2;
/*    */   
/*    */   private double xValue;
/*    */   
/*    */   private int eventType;
/*    */   
/*    */   private SweepLineEvent insertEvent;
/*    */   
/*    */   private int deleteEventIndex;
/*    */   
/*    */   SweepLineInterval sweepInt;
/*    */   
/*    */   public SweepLineEvent(double x, SweepLineEvent insertEvent, SweepLineInterval sweepInt) {
/* 55 */     this.xValue = x;
/* 56 */     this.insertEvent = insertEvent;
/* 57 */     this.eventType = 1;
/* 58 */     if (insertEvent != null)
/* 59 */       this.eventType = 2; 
/* 60 */     this.sweepInt = sweepInt;
/*    */   }
/*    */   
/*    */   public boolean isInsert() {
/* 63 */     return (this.insertEvent == null);
/*    */   }
/*    */   
/*    */   public boolean isDelete() {
/* 64 */     return (this.insertEvent != null);
/*    */   }
/*    */   
/*    */   public SweepLineEvent getInsertEvent() {
/* 65 */     return this.insertEvent;
/*    */   }
/*    */   
/*    */   public int getDeleteEventIndex() {
/* 66 */     return this.deleteEventIndex;
/*    */   }
/*    */   
/*    */   public void setDeleteEventIndex(int deleteEventIndex) {
/* 67 */     this.deleteEventIndex = deleteEventIndex;
/*    */   }
/*    */   
/*    */   SweepLineInterval getInterval() {
/* 69 */     return this.sweepInt;
/*    */   }
/*    */   
/*    */   public int compareTo(Object o) {
/* 78 */     SweepLineEvent pe = (SweepLineEvent)o;
/* 79 */     if (this.xValue < pe.xValue)
/* 79 */       return -1; 
/* 80 */     if (this.xValue > pe.xValue)
/* 80 */       return 1; 
/* 81 */     if (this.eventType < pe.eventType)
/* 81 */       return -1; 
/* 82 */     if (this.eventType > pe.eventType)
/* 82 */       return 1; 
/* 83 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\index\sweepline\SweepLineEvent.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */