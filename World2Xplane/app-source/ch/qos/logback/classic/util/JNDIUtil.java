/*    */ package ch.qos.logback.classic.util;
/*    */ 
/*    */ import javax.naming.Context;
/*    */ import javax.naming.InitialContext;
/*    */ import javax.naming.NamingException;
/*    */ 
/*    */ public class JNDIUtil {
/*    */   public static Context getInitialContext() throws NamingException {
/* 30 */     return new InitialContext();
/*    */   }
/*    */   
/*    */   public static String lookup(Context ctx, String name) {
/* 34 */     if (ctx == null)
/* 35 */       return null; 
/*    */     try {
/* 38 */       return (String)ctx.lookup(name);
/* 39 */     } catch (NamingException e) {
/* 40 */       return null;
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\ch\qos\logback\classi\\util\JNDIUtil.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */