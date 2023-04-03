/*    */ package org.apache.commons.math3.exception.util;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ 
/*    */ public class ArgUtils {
/*    */   public static Object[] flatten(Object[] array) {
/* 42 */     List<Object> list = new ArrayList();
/* 43 */     if (array != null)
/* 44 */       for (Object o : array) {
/* 45 */         if (o instanceof Object[]) {
/* 46 */           for (Object oR : flatten((Object[])o))
/* 47 */             list.add(oR); 
/*    */         } else {
/* 50 */           list.add(o);
/*    */         } 
/*    */       }  
/* 54 */     return list.toArray();
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\exceptio\\util\ArgUtils.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */