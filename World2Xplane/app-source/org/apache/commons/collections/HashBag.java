/*    */ package org.apache.commons.collections;
/*    */ 
/*    */ import java.util.Collection;
/*    */ import java.util.HashMap;
/*    */ 
/*    */ public class HashBag extends DefaultMapBag implements Bag {
/*    */   public HashBag() {
/* 37 */     super(new HashMap());
/*    */   }
/*    */   
/*    */   public HashBag(Collection coll) {
/* 47 */     this();
/* 48 */     addAll(coll);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\collections\HashBag.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */