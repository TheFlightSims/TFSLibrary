/*    */ package ch.qos.logback.classic.gaffer;
/*    */ 
/*    */ import ch.qos.logback.core.Appender;
/*    */ import ch.qos.logback.core.Context;
/*    */ import ch.qos.logback.core.status.Status;
/*    */ import ch.qos.logback.core.status.StatusManager;
/*    */ import groovy.lang.MetaClass;
/*    */ import java.lang.ref.SoftReference;
/*    */ import java.util.List;
/*    */ import org.codehaus.groovy.reflection.ClassInfo;
/*    */ import org.codehaus.groovy.runtime.GStringImpl;
/*    */ import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
/*    */ import org.codehaus.groovy.runtime.callsite.CallSite;
/*    */ import org.codehaus.groovy.runtime.callsite.CallSiteArray;
/*    */ 
/*    */ public class AppenderDelegate extends ComponentDelegate {
/*    */   public AppenderDelegate(Appender appender) {
/* 25 */     super(appender);
/*    */   }
/*    */   
/*    */   public String getLabel() {
/* 29 */     CallSite[] arrayOfCallSite = $getCallSiteArray();
/* 29 */     return "appender";
/*    */   }
/*    */   
/*    */   static {
/*    */     __$swapInit();
/*    */     long l1 = 0L;
/*    */     __timeStamp__239_neverHappen1368189247445 = l1;
/*    */     long l2 = 1368189247445L;
/*    */     __timeStamp = l2;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\ch\qos\logback\classic\gaffer\AppenderDelegate.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */