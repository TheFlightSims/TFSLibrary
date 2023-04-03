/*    */ package org.jfree.chart.imagemap;
/*    */ 
/*    */ public class OverLIBToolTipTagFragmentGenerator implements ToolTipTagFragmentGenerator {
/*    */   public String generateToolTipFragment(String toolTipText) {
/* 61 */     return " onMouseOver=\"return overlib('" + toolTipText + "');\" onMouseOut=\"return nd();\"";
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\chart\imagemap\OverLIBToolTipTagFragmentGenerator.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */