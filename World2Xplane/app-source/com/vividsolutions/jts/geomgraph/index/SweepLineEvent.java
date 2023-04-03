/*     */ package com.vividsolutions.jts.geomgraph.index;
/*     */ 
/*     */ public class SweepLineEvent implements Comparable {
/*     */   private static final int INSERT = 1;
/*     */   
/*     */   private static final int DELETE = 2;
/*     */   
/*     */   private Object label;
/*     */   
/*     */   private double xValue;
/*     */   
/*     */   private int eventType;
/*     */   
/*  50 */   private SweepLineEvent insertEvent = null;
/*     */   
/*     */   private int deleteEventIndex;
/*     */   
/*     */   private Object obj;
/*     */   
/*     */   public SweepLineEvent(Object label, double x, Object obj) {
/*  63 */     this.eventType = 1;
/*  64 */     this.label = label;
/*  65 */     this.xValue = x;
/*  66 */     this.obj = obj;
/*     */   }
/*     */   
/*     */   public SweepLineEvent(double x, SweepLineEvent insertEvent) {
/*  77 */     this.eventType = 2;
/*  78 */     this.xValue = x;
/*  79 */     this.insertEvent = insertEvent;
/*     */   }
/*     */   
/*     */   public boolean isInsert() {
/*  82 */     return (this.eventType == 1);
/*     */   }
/*     */   
/*     */   public boolean isDelete() {
/*  83 */     return (this.eventType == 2);
/*     */   }
/*     */   
/*     */   public SweepLineEvent getInsertEvent() {
/*  84 */     return this.insertEvent;
/*     */   }
/*     */   
/*     */   public int getDeleteEventIndex() {
/*  85 */     return this.deleteEventIndex;
/*     */   }
/*     */   
/*     */   public void setDeleteEventIndex(int deleteEventIndex) {
/*  86 */     this.deleteEventIndex = deleteEventIndex;
/*     */   }
/*     */   
/*     */   public Object getObject() {
/*  88 */     return this.obj;
/*     */   }
/*     */   
/*     */   public boolean isSameLabel(SweepLineEvent ev) {
/*  93 */     if (this.label == null)
/*  93 */       return false; 
/*  94 */     return (this.label == ev.label);
/*     */   }
/*     */   
/*     */   public int compareTo(Object o) {
/* 103 */     SweepLineEvent pe = (SweepLineEvent)o;
/* 104 */     if (this.xValue < pe.xValue)
/* 104 */       return -1; 
/* 105 */     if (this.xValue > pe.xValue)
/* 105 */       return 1; 
/* 106 */     if (this.eventType < pe.eventType)
/* 106 */       return -1; 
/* 107 */     if (this.eventType > pe.eventType)
/* 107 */       return 1; 
/* 108 */     return 0;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\geomgraph\index\SweepLineEvent.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */