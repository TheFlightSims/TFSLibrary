/*    */ package org.geotools.referencing.wkt;
/*    */ 
/*    */ import org.geotools.resources.Formattable;
/*    */ import org.geotools.util.UnsupportedImplementationException;
/*    */ 
/*    */ final class Adapter extends Formattable {
/*    */   private final Object object;
/*    */   
/*    */   public Adapter(Object object) {
/* 43 */     this.object = object;
/*    */   }
/*    */   
/*    */   protected String formatWKT(Formatter formatter) {
/* 51 */     if (this.object instanceof Formattable)
/* 52 */       return ((Formattable)this.object).formatWKT(formatter); 
/* 54 */     Class<?> classe = this.object.getClass();
/*    */     try {
/* 57 */       String wkt = (String)classe.getMethod("toWKT", (Class[])null).invoke(this.object, (Object[])null);
/* 58 */     } catch (Exception cause) {
/* 60 */       UnsupportedImplementationException exception = new UnsupportedImplementationException(classe);
/* 61 */       exception.initCause(cause);
/* 62 */       throw exception;
/*    */     } 
/* 66 */     throw new UnsupportedImplementationException(classe);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\referencing\wkt\Adapter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */