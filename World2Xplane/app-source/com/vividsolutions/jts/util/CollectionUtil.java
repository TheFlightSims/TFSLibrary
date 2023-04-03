/*    */ package com.vividsolutions.jts.util;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.Collection;
/*    */ import java.util.Iterator;
/*    */ import java.util.List;
/*    */ 
/*    */ public class CollectionUtil {
/*    */   public static List transform(Collection coll, Function func) {
/* 60 */     List<Object> result = new ArrayList();
/* 61 */     for (Iterator i = coll.iterator(); i.hasNext();)
/* 62 */       result.add(func.execute(i.next())); 
/* 64 */     return result;
/*    */   }
/*    */   
/*    */   public static void apply(Collection coll, Function func) {
/* 76 */     for (Iterator i = coll.iterator(); i.hasNext();)
/* 77 */       func.execute(i.next()); 
/*    */   }
/*    */   
/*    */   public static List select(Collection collection, Function func) {
/* 91 */     List<Object> result = new ArrayList();
/* 92 */     for (Iterator i = collection.iterator(); i.hasNext(); ) {
/* 93 */       Object item = i.next();
/* 94 */       if (Boolean.TRUE.equals(func.execute(item)))
/* 95 */         result.add(item); 
/*    */     } 
/* 98 */     return result;
/*    */   }
/*    */   
/*    */   public static interface Function {
/*    */     Object execute(Object param1Object);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jt\\util\CollectionUtil.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */