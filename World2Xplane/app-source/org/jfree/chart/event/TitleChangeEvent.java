/*    */ package org.jfree.chart.event;
/*    */ 
/*    */ import org.jfree.chart.title.Title;
/*    */ 
/*    */ public class TitleChangeEvent extends ChartChangeEvent {
/*    */   private Title title;
/*    */   
/*    */   public TitleChangeEvent(Title title) {
/* 66 */     super(title);
/* 67 */     this.title = title;
/*    */   }
/*    */   
/*    */   public Title getTitle() {
/* 76 */     return this.title;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\chart\event\TitleChangeEvent.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */