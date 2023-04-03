/*    */ package ch.qos.logback.classic.joran.action;
/*    */ 
/*    */ import ch.qos.logback.classic.LoggerContext;
/*    */ import ch.qos.logback.classic.turbo.ReconfigureOnChangeFilter;
/*    */ import ch.qos.logback.classic.turbo.TurboFilter;
/*    */ import ch.qos.logback.classic.util.EnvUtil;
/*    */ import ch.qos.logback.core.joran.action.Action;
/*    */ import ch.qos.logback.core.joran.spi.InterpretationContext;
/*    */ import ch.qos.logback.core.status.OnConsoleStatusListener;
/*    */ import ch.qos.logback.core.util.ContextUtil;
/*    */ import ch.qos.logback.core.util.Duration;
/*    */ import ch.qos.logback.core.util.OptionHelper;
/*    */ import org.xml.sax.Attributes;
/*    */ 
/*    */ public class ConfigurationAction extends Action {
/*    */   static final String INTERNAL_DEBUG_ATTR = "debug";
/*    */   
/*    */   static final String SCAN_ATTR = "scan";
/*    */   
/*    */   static final String SCAN_PERIOD_ATTR = "scanPeriod";
/*    */   
/*    */   static final String DEBUG_SYSTEM_PROPERTY_KEY = "logback.debug";
/*    */   
/* 34 */   long threshold = 0L;
/*    */   
/*    */   public void begin(InterpretationContext ic, String name, Attributes attributes) {
/* 37 */     this.threshold = System.currentTimeMillis();
/* 42 */     String debugAttrib = System.getProperty("logback.debug");
/* 43 */     if (debugAttrib == null)
/* 44 */       debugAttrib = ic.subst(attributes.getValue("debug")); 
/* 47 */     if (OptionHelper.isEmpty(debugAttrib) || debugAttrib.equalsIgnoreCase("false") || debugAttrib.equalsIgnoreCase("null")) {
/* 49 */       addInfo("debug attribute not set");
/*    */     } else {
/* 51 */       OnConsoleStatusListener.addNewInstanceToContext(this.context);
/*    */     } 
/* 54 */     processScanAttrib(ic, attributes);
/* 56 */     ContextUtil contextUtil = new ContextUtil(this.context);
/* 57 */     contextUtil.addHostNameAsProperty();
/* 59 */     if (EnvUtil.isGroovyAvailable()) {
/* 60 */       LoggerContext lc = (LoggerContext)this.context;
/* 61 */       contextUtil.addGroovyPackages(lc.getFrameworkPackages());
/*    */     } 
/* 66 */     ic.pushObject(getContext());
/*    */   }
/*    */   
/*    */   void processScanAttrib(InterpretationContext ic, Attributes attributes) {
/* 70 */     String scanAttrib = ic.subst(attributes.getValue("scan"));
/* 71 */     if (!OptionHelper.isEmpty(scanAttrib) && !"false".equalsIgnoreCase(scanAttrib)) {
/* 73 */       ReconfigureOnChangeFilter rocf = new ReconfigureOnChangeFilter();
/* 74 */       rocf.setContext(this.context);
/* 75 */       String scanPeriodAttrib = ic.subst(attributes.getValue("scanPeriod"));
/* 76 */       if (!OptionHelper.isEmpty(scanPeriodAttrib))
/*    */         try {
/* 78 */           Duration duration = Duration.valueOf(scanPeriodAttrib);
/* 79 */           rocf.setRefreshPeriod(duration.getMilliseconds());
/* 80 */           addInfo("Setting ReconfigureOnChangeFilter scanning period to " + duration);
/* 82 */         } catch (NumberFormatException nfe) {
/* 83 */           addError("Error while converting [" + scanAttrib + "] to long", nfe);
/*    */         }  
/* 86 */       rocf.start();
/* 87 */       LoggerContext lc = (LoggerContext)this.context;
/* 88 */       addInfo("Adding ReconfigureOnChangeFilter as a turbo filter");
/* 89 */       lc.addTurboFilter((TurboFilter)rocf);
/*    */     } 
/*    */   }
/*    */   
/*    */   public void end(InterpretationContext ec, String name) {
/* 94 */     addInfo("End of configuration.");
/* 95 */     ec.popObject();
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\ch\qos\logback\classic\joran\action\ConfigurationAction.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */