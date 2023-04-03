/*    */ package org.jfree.chart.imagemap;
/*    */ 
/*    */ public class DynamicDriveToolTipTagFragmentGenerator implements ToolTipTagFragmentGenerator {
/* 54 */   protected String title = "";
/*    */   
/* 57 */   protected int style = 1;
/*    */   
/*    */   public DynamicDriveToolTipTagFragmentGenerator() {}
/*    */   
/*    */   public DynamicDriveToolTipTagFragmentGenerator(String title, int style) {
/* 75 */     this.title = title;
/* 76 */     this.style = style;
/*    */   }
/*    */   
/*    */   public String generateToolTipFragment(String toolTipText) {
/* 87 */     return " onMouseOver=\"return stm(['" + this.title + "','" + toolTipText + "'],Style[" + this.style + "]);\"" + " onMouseOut=\"return htm();\"";
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\chart\imagemap\DynamicDriveToolTipTagFragmentGenerator.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */