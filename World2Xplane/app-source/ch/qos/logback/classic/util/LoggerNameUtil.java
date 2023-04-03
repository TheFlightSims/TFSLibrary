/*    */ package ch.qos.logback.classic.util;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ 
/*    */ public class LoggerNameUtil {
/*    */   public static int getFirstSeparatorIndexOf(String name) {
/* 28 */     return getSeparatorIndexOf(name, 0);
/*    */   }
/*    */   
/*    */   public static int getSeparatorIndexOf(String name, int fromIndex) {
/* 40 */     int i = name.indexOf('.', fromIndex);
/* 41 */     if (i != -1)
/* 42 */       return i; 
/* 44 */     return name.indexOf('$', fromIndex);
/*    */   }
/*    */   
/*    */   public static List<String> computeNameParts(String loggerName) {
/* 49 */     List<String> partList = new ArrayList<String>();
/* 51 */     int fromIndex = 0;
/*    */     while (true) {
/* 53 */       int index = getSeparatorIndexOf(loggerName, fromIndex);
/* 54 */       if (index == -1) {
/* 55 */         partList.add(loggerName.substring(fromIndex));
/*    */         break;
/*    */       } 
/* 58 */       partList.add(loggerName.substring(fromIndex, index));
/* 59 */       fromIndex = index + 1;
/*    */     } 
/* 61 */     return partList;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\ch\qos\logback\classi\\util\LoggerNameUtil.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */