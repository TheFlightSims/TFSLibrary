/*    */ package ch.qos.logback.core.sift;
/*    */ 
/*    */ public class DefaultDiscriminator<E> extends AbstractDiscriminator<E> {
/*    */   public static final String DEFAULT = "default";
/*    */   
/*    */   public String getDiscriminatingValue(E e) {
/* 25 */     return "default";
/*    */   }
/*    */   
/*    */   public String getKey() {
/* 29 */     return "default";
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\ch\qos\logback\core\sift\DefaultDiscriminator.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */