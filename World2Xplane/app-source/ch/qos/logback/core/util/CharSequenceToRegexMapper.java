/*    */ package ch.qos.logback.core.util;
/*    */ 
/*    */ class CharSequenceToRegexMapper {
/*    */   String toRegex(CharSequenceState css) {
/* 26 */     int occurrences = css.occurrences;
/* 27 */     char c = css.c;
/* 28 */     switch (css.c) {
/*    */       case 'G':
/*    */       case 'z':
/* 31 */         return ".*";
/*    */       case 'M':
/* 33 */         if (occurrences >= 3)
/* 34 */           return ".{3,12}"; 
/* 36 */         return number(occurrences);
/*    */       case 'D':
/*    */       case 'F':
/*    */       case 'H':
/*    */       case 'K':
/*    */       case 'S':
/*    */       case 'W':
/*    */       case 'd':
/*    */       case 'h':
/*    */       case 'k':
/*    */       case 'm':
/*    */       case 's':
/*    */       case 'w':
/*    */       case 'y':
/* 51 */         return number(occurrences);
/*    */       case 'E':
/* 53 */         return ".{2,12}";
/*    */       case 'a':
/* 55 */         return ".{2}";
/*    */       case 'Z':
/* 57 */         return "(\\+|-)\\d{4}";
/*    */       case '.':
/* 59 */         return "\\.";
/*    */       case '\\':
/* 61 */         throw new IllegalStateException("Forward slashes are not allowed");
/*    */       case '\'':
/* 63 */         if (occurrences == 1)
/* 64 */           return ""; 
/* 66 */         throw new IllegalStateException("Too many single quotes");
/*    */     } 
/* 68 */     if (occurrences == 1)
/* 69 */       return "" + c; 
/* 71 */     return c + "{" + occurrences + "}";
/*    */   }
/*    */   
/*    */   private String number(int occurrences) {
/* 77 */     return "\\d{" + occurrences + "}";
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\ch\qos\logback\cor\\util\CharSequenceToRegexMapper.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */