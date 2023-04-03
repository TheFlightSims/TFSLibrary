/*    */ package ch.qos.logback.classic.boolex;
/*    */ 
/*    */ import ch.qos.logback.classic.spi.ILoggingEvent;
/*    */ import groovy.lang.GroovyObject;
/*    */ import groovy.lang.MetaClass;
/*    */ import java.lang.ref.SoftReference;
/*    */ import org.codehaus.groovy.reflection.ClassInfo;
/*    */ import org.codehaus.groovy.runtime.GStringImpl;
/*    */ import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
/*    */ import org.codehaus.groovy.runtime.callsite.CallSite;
/*    */ import org.codehaus.groovy.runtime.callsite.CallSiteArray;
/*    */ import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;
/*    */ 
/*    */ public class EvaluatorTemplate implements IEvaluator, GroovyObject {
/*    */   public EvaluatorTemplate() {
/*    */     CallSite[] arrayOfCallSite = $getCallSiteArray();
/*    */     MetaClass metaClass = $getStaticMetaClass();
/*    */     this.metaClass = metaClass;
/*    */   }
/*    */   
/*    */   public boolean doEvaluate(ILoggingEvent event) {
/* 34 */     CallSite[] arrayOfCallSite = $getCallSiteArray();
/* 34 */     ILoggingEvent e = event;
/* 34 */     return DefaultTypeTransformation.booleanUnbox(e);
/*    */   }
/*    */   
/*    */   static {
/*    */     __$swapInit();
/*    */     long l1 = 0L;
/*    */     __timeStamp__239_neverHappen1368189247374 = l1;
/*    */     long l2 = 1368189247374L;
/*    */     __timeStamp = l2;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\ch\qos\logback\classic\boolex\EvaluatorTemplate.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */