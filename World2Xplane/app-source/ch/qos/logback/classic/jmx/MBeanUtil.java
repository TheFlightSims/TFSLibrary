/*    */ package ch.qos.logback.classic.jmx;
/*    */ 
/*    */ import ch.qos.logback.classic.LoggerContext;
/*    */ import ch.qos.logback.core.Context;
/*    */ import ch.qos.logback.core.status.StatusUtil;
/*    */ import javax.management.InstanceNotFoundException;
/*    */ import javax.management.MBeanRegistrationException;
/*    */ import javax.management.MBeanServer;
/*    */ import javax.management.MalformedObjectNameException;
/*    */ import javax.management.ObjectName;
/*    */ 
/*    */ public class MBeanUtil {
/*    */   static final String DOMAIN = "ch.qos.logback.classic";
/*    */   
/*    */   public static String getObjectNameFor(String contextName, Class type) {
/* 31 */     return "ch.qos.logback.classic:Name=" + contextName + ",Type=" + type.getName();
/*    */   }
/*    */   
/*    */   public static ObjectName string2ObjectName(Context context, Object caller, String objectNameAsStr) {
/* 37 */     String msg = "Failed to convert [" + objectNameAsStr + "] to ObjectName";
/* 39 */     StatusUtil statusUtil = new StatusUtil(context);
/*    */     try {
/* 41 */       return new ObjectName(objectNameAsStr);
/* 42 */     } catch (MalformedObjectNameException e) {
/* 43 */       statusUtil.addError(caller, msg, e);
/* 44 */       return null;
/* 45 */     } catch (NullPointerException e) {
/* 46 */       statusUtil.addError(caller, msg, e);
/* 47 */       return null;
/*    */     } 
/*    */   }
/*    */   
/*    */   public static boolean isRegistered(MBeanServer mbs, ObjectName objectName) {
/* 52 */     return mbs.isRegistered(objectName);
/*    */   }
/*    */   
/*    */   public static void createAndRegisterJMXConfigurator(MBeanServer mbs, LoggerContext loggerContext, JMXConfigurator jmxConfigurator, ObjectName objectName, Object caller) {
/*    */     try {
/* 59 */       mbs.registerMBean(jmxConfigurator, objectName);
/* 60 */     } catch (Exception e) {
/* 61 */       StatusUtil statusUtil = new StatusUtil((Context)loggerContext);
/* 62 */       statusUtil.addError(caller, "Failed to create mbean", e);
/*    */     } 
/*    */   }
/*    */   
/*    */   public static void unregister(LoggerContext loggerContext, MBeanServer mbs, ObjectName objectName, Object caller) {
/* 69 */     StatusUtil statusUtil = new StatusUtil((Context)loggerContext);
/* 70 */     if (mbs.isRegistered(objectName)) {
/*    */       try {
/* 72 */         statusUtil.addInfo(caller, "Unregistering mbean [" + objectName + "]");
/* 74 */         mbs.unregisterMBean(objectName);
/* 75 */       } catch (InstanceNotFoundException e) {
/* 77 */         statusUtil.addError(caller, "Failed to unregister mbean" + objectName, e);
/* 79 */       } catch (MBeanRegistrationException e) {
/* 81 */         statusUtil.addError(caller, "Failed to unregister mbean" + objectName, e);
/*    */       } 
/*    */     } else {
/* 85 */       statusUtil.addInfo(caller, "mbean [" + objectName + "] does not seem to be registered");
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\ch\qos\logback\classic\jmx\MBeanUtil.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */