/*    */ package org.opengis.filter;
/*    */ 
/*    */ import org.opengis.annotation.XmlElement;
/*    */ 
/*    */ public interface MultiValuedFilter extends Filter {
/*    */   @XmlElement("matchAction")
/*    */   MatchAction getMatchAction();
/*    */   
/*    */   public enum MatchAction {
/* 40 */     ANY, ALL, ONE;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\filter\MultiValuedFilter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */