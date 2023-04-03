/*    */ package org.jfree.chart.entity;
/*    */ 
/*    */ import java.awt.Shape;
/*    */ import java.io.Serializable;
/*    */ 
/*    */ public class TickLabelEntity extends ChartEntity implements Cloneable, Serializable {
/*    */   private static final long serialVersionUID = 681583956588092095L;
/*    */   
/*    */   public TickLabelEntity(Shape area, String toolTipText, String urlText) {
/* 66 */     super(area, toolTipText, urlText);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\chart\entity\TickLabelEntity.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */