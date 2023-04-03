/*    */ package scala.xml.dtd;
/*    */ 
/*    */ import scala.Function1;
/*    */ import scala.Serializable;
/*    */ import scala.collection.Seq;
/*    */ import scala.collection.immutable.Set;
/*    */ import scala.collection.immutable.StringOps;
/*    */ import scala.collection.mutable.StringBuilder;
/*    */ import scala.runtime.AbstractFunction1;
/*    */ import scala.runtime.BoxedUnit;
/*    */ 
/*    */ public final class MakeValidationException$ {
/*    */   public static final MakeValidationException$ MODULE$;
/*    */   
/*    */   private MakeValidationException$() {
/* 20 */     MODULE$ = this;
/*    */   }
/*    */   
/*    */   public ValidationException fromFixedAttribute(String k, String value, String actual) {
/* 22 */     return new ValidationException((
/* 23 */         new StringBuilder()).append("value of attribute ").append(k).append(" FIXED to \"").append(value).append("\", but document tries \"").append(actual).append("\"").toString());
/*    */   }
/*    */   
/*    */   public ValidationException fromNonEmptyElement() {
/* 26 */     return new ValidationException("element should be *empty*");
/*    */   }
/*    */   
/*    */   public ValidationException fromUndefinedElement(String label) {
/* 29 */     return new ValidationException((new StringBuilder()).append("element \"").append(label).append("\" not allowed here").toString());
/*    */   }
/*    */   
/*    */   public ValidationException fromUndefinedAttribute(String key) {
/* 32 */     return new ValidationException((new StringBuilder()).append("attribute ").append(key).append(" not allowed here").toString());
/*    */   }
/*    */   
/*    */   public ValidationException fromMissingAttribute(Set allKeys) {
/* 35 */     StringBuilder sb = new StringBuilder("missing value for REQUIRED attribute");
/* 36 */     (allKeys.size() > 1) ? sb.append('s') : BoxedUnit.UNIT;
/* 37 */     allKeys.foreach((Function1)new MakeValidationException$$anonfun$fromMissingAttribute$1(sb));
/* 38 */     return new ValidationException(sb.toString());
/*    */   }
/*    */   
/*    */   public static class MakeValidationException$$anonfun$fromMissingAttribute$1 extends AbstractFunction1<String, StringBuilder> implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     private final StringBuilder sb$1;
/*    */     
/*    */     public final StringBuilder apply(String k) {
/*    */       scala.Predef$ predef$ = scala.Predef$.MODULE$;
/*    */       return this.sb$1.append((new StringOps("'%s'")).format((Seq)scala.Predef$.MODULE$.genericWrapArray(new Object[] { k })));
/*    */     }
/*    */     
/*    */     public MakeValidationException$$anonfun$fromMissingAttribute$1(StringBuilder sb$1) {}
/*    */   }
/*    */   
/*    */   public ValidationException fromMissingAttribute(String key, String tpe) {
/* 42 */     scala.Predef$ predef$ = scala.Predef$.MODULE$;
/* 42 */     return new ValidationException((new StringOps("missing value for REQUIRED attribute %s of type %s")).format((Seq)scala.Predef$.MODULE$.genericWrapArray(new Object[] { key, tpe })));
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\xml\dtd\MakeValidationException$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */