/*    */ package org.apache.commons.collections.functors;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import org.apache.commons.collections.Closure;
/*    */ 
/*    */ public class NOPClosure implements Closure, Serializable {
/*    */   private static final long serialVersionUID = 3518477308466486130L;
/*    */   
/* 37 */   public static final Closure INSTANCE = new NOPClosure();
/*    */   
/*    */   public static Closure getInstance() {
/* 46 */     return INSTANCE;
/*    */   }
/*    */   
/*    */   public void execute(Object input) {}
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\collections\functors\NOPClosure.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */