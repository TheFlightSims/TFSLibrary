/*    */ package ch.qos.logback.core.util;
/*    */ 
/*    */ class CharSequenceState {
/*    */   final char c;
/*    */   
/*    */   int occurrences;
/*    */   
/*    */   public CharSequenceState(char c) {
/* 25 */     this.c = c;
/* 26 */     this.occurrences = 1;
/*    */   }
/*    */   
/*    */   void incrementOccurrences() {
/* 30 */     this.occurrences++;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\ch\qos\logback\cor\\util\CharSequenceState.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */