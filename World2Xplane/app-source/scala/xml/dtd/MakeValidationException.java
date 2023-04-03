/*    */ package scala.xml.dtd;
/*    */ 
/*    */ import scala.Predef$;
/*    */ import scala.Serializable;
/*    */ import scala.collection.Seq;
/*    */ import scala.collection.immutable.Set;
/*    */ import scala.collection.immutable.StringOps;
/*    */ import scala.collection.mutable.StringBuilder;
/*    */ import scala.reflect.ScalaSignature;
/*    */ import scala.runtime.AbstractFunction1;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001\t;Q!\001\002\t\002%\tq#T1lKZ\013G.\0333bi&|g.\022=dKB$\030n\0348\013\005\r!\021a\0013uI*\021QAB\001\004q6d'\"A\004\002\013M\034\027\r\\1\004\001A\021!bC\007\002\005\031)AB\001E\001\033\t9R*Y6f-\006d\027\016Z1uS>tW\t_2faRLwN\\\n\003\0279\001\"a\004\t\016\003\031I!!\005\004\003\r\005s\027PU3g\021\025\0312\002\"\001\025\003\031a\024N\\5u}Q\t\021\002C\003\027\027\021\005q#\001\nge>lg)\033=fI\006#HO]5ckR,G\003\002\r\034I\031\002\"AC\r\n\005i\021!a\005,bY&$\027\r^5p]\026C8-\0329uS>t\007\"\002\017\026\001\004i\022!A6\021\005y\tcBA\b \023\t\001c!\001\004Qe\026$WMZ\005\003E\r\022aa\025;sS:<'B\001\021\007\021\025)S\0031\001\036\003\0251\030\r\\;f\021\0259S\0031\001\036\003\031\t7\r^;bY\")\021f\003C\001U\005\031bM]8n\035>tW)\0349us\026cW-\\3oiR\t\001\004C\003-\027\021\005Q&\001\013ge>lWK\0343fM&tW\rZ#mK6,g\016\036\013\00319BQaL\026A\002u\tQ\001\\1cK2DQ!M\006\005\002I\naC\032:p[VsG-\0324j]\026$\027\t\036;sS\n,H/\032\013\0031MBQ\001\016\031A\002u\t1a[3z\021\02514\002\"\0018\003Q1'o\\7NSN\034\030N\\4BiR\024\030NY;uKR\021\001\004\017\005\006sU\002\rAO\001\bC2d7*Z=t!\rq2(H\005\003y\r\0221aU3u\021\02514\002\"\001?)\rAr\b\021\005\006iu\002\r!\b\005\006\003v\002\r!H\001\004iB,\007")
/*    */ public final class MakeValidationException {
/*    */   public static ValidationException fromMissingAttribute(String paramString1, String paramString2) {
/*    */     return MakeValidationException$.MODULE$.fromMissingAttribute(paramString1, paramString2);
/*    */   }
/*    */   
/*    */   public static ValidationException fromMissingAttribute(Set<String> paramSet) {
/*    */     return MakeValidationException$.MODULE$.fromMissingAttribute(paramSet);
/*    */   }
/*    */   
/*    */   public static ValidationException fromUndefinedAttribute(String paramString) {
/*    */     return MakeValidationException$.MODULE$.fromUndefinedAttribute(paramString);
/*    */   }
/*    */   
/*    */   public static ValidationException fromUndefinedElement(String paramString) {
/*    */     return MakeValidationException$.MODULE$.fromUndefinedElement(paramString);
/*    */   }
/*    */   
/*    */   public static ValidationException fromNonEmptyElement() {
/*    */     return MakeValidationException$.MODULE$.fromNonEmptyElement();
/*    */   }
/*    */   
/*    */   public static ValidationException fromFixedAttribute(String paramString1, String paramString2, String paramString3) {
/*    */     return MakeValidationException$.MODULE$.fromFixedAttribute(paramString1, paramString2, paramString3);
/*    */   }
/*    */   
/*    */   public static class MakeValidationException$$anonfun$fromMissingAttribute$1 extends AbstractFunction1<String, StringBuilder> implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     private final StringBuilder sb$1;
/*    */     
/*    */     public final StringBuilder apply(String k) {
/* 37 */       Predef$ predef$ = Predef$.MODULE$;
/* 37 */       return this.sb$1.append((new StringOps("'%s'")).format((Seq)Predef$.MODULE$.genericWrapArray(new Object[] { k })));
/*    */     }
/*    */     
/*    */     public MakeValidationException$$anonfun$fromMissingAttribute$1(StringBuilder sb$1) {}
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\xml\dtd\MakeValidationException.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */