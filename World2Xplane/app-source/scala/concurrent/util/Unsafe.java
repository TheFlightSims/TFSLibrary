/*    */ package scala.concurrent.util;
/*    */ 
/*    */ import java.lang.reflect.Field;
/*    */ 
/*    */ public final class Unsafe {
/*    */   public static final sun.misc.Unsafe instance;
/*    */   
/*    */   static {
/*    */     try {
/* 21 */       sun.misc.Unsafe found = null;
/* 22 */       for (Field field : sun.misc.Unsafe.class.getDeclaredFields()) {
/* 23 */         if (field.getType() == sun.misc.Unsafe.class) {
/* 24 */           field.setAccessible(true);
/* 25 */           found = (sun.misc.Unsafe)field.get(null);
/*    */           break;
/*    */         } 
/*    */       } 
/* 29 */       if (found == null)
/* 29 */         throw new IllegalStateException("Can't find instance of sun.misc.Unsafe"); 
/* 30 */       instance = found;
/* 31 */     } catch (Throwable t) {
/* 32 */       throw new ExceptionInInitializerError(t);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\concurren\\util\Unsafe.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */