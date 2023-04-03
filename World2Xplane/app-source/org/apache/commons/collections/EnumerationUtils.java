/*    */ package org.apache.commons.collections;
/*    */ 
/*    */ import java.util.Enumeration;
/*    */ import java.util.Iterator;
/*    */ import java.util.List;
/*    */ import org.apache.commons.collections.iterators.EnumerationIterator;
/*    */ 
/*    */ public class EnumerationUtils {
/*    */   public static List toList(Enumeration enumeration) {
/* 51 */     return IteratorUtils.toList((Iterator)new EnumerationIterator(enumeration));
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\collections\EnumerationUtils.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */