/*    */ package scala.beans;
/*    */ 
/*    */ import scala.annotation.Annotation;
/*    */ import scala.reflect.ScalaSignature;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001\0052A!\001\002\001\017\ty!)Z1o\t\026\0348M]5qi&|gN\003\002\004\t\005)!-Z1og*\tQ!A\003tG\006d\027m\001\001\024\005\001A\001CA\005\r\033\005Q!BA\006\005\003)\tgN\\8uCRLwN\\\005\003\033)\021!\"\0218o_R\fG/[8o\021!y\001A!b\001\n\003\001\022a\0033fg\016\024\030\016\035;j_:,\022!\005\t\003%Yq!a\005\013\016\003\021I!!\006\003\002\rA\023X\rZ3g\023\t9\002D\001\004TiJLgn\032\006\003+\021A\001B\007\001\003\002\003\006I!E\001\rI\026\0348M]5qi&|g\016\t\005\0069\001!\t!H\001\007y%t\027\016\036 \025\005y\001\003CA\020\001\033\005\021\001\"B\b\034\001\004\t\002")
/*    */ public class BeanDescription extends Annotation {
/*    */   private final String description;
/*    */   
/*    */   public String description() {
/* 18 */     return this.description;
/*    */   }
/*    */   
/*    */   public BeanDescription(String description) {}
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\beans\BeanDescription.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */