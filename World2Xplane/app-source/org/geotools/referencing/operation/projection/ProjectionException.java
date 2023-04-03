/*    */ package org.geotools.referencing.operation.projection;
/*    */ 
/*    */ import org.geotools.measure.Latitude;
/*    */ import org.geotools.resources.i18n.Errors;
/*    */ import org.opengis.referencing.operation.TransformException;
/*    */ 
/*    */ public class ProjectionException extends TransformException {
/*    */   private static final long serialVersionUID = 3031350727691500915L;
/*    */   
/*    */   public ProjectionException() {}
/*    */   
/*    */   ProjectionException(int code) {
/* 54 */     this(Errors.format(code));
/*    */   }
/*    */   
/*    */   ProjectionException(int code, Object value) {
/* 64 */     this(Errors.format(code, value));
/*    */   }
/*    */   
/*    */   ProjectionException(double latitude) {
/* 72 */     this(Errors.format(159, new Latitude(Math.toDegrees(latitude))));
/*    */   }
/*    */   
/*    */   public ProjectionException(String message) {
/* 79 */     super(message);
/*    */   }
/*    */   
/*    */   public ProjectionException(Throwable cause) {
/* 88 */     super(cause.getLocalizedMessage(), cause);
/*    */   }
/*    */   
/*    */   public ProjectionException(String message, Throwable cause) {
/* 95 */     super(message, cause);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\referencing\operation\projection\ProjectionException.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */