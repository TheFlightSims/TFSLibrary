/*    */ package ch.qos.logback.classic.joran.action;
/*    */ 
/*    */ import ch.qos.logback.classic.Logger;
/*    */ import ch.qos.logback.classic.LoggerContext;
/*    */ import ch.qos.logback.classic.net.SocketAppender;
/*    */ import ch.qos.logback.core.Appender;
/*    */ import ch.qos.logback.core.Context;
/*    */ import ch.qos.logback.core.joran.action.Action;
/*    */ import ch.qos.logback.core.joran.spi.ActionException;
/*    */ import ch.qos.logback.core.joran.spi.InterpretationContext;
/*    */ import org.xml.sax.Attributes;
/*    */ 
/*    */ public class ConsolePluginAction extends Action {
/*    */   private static final String PORT_ATTR = "port";
/*    */   
/* 28 */   private static final Integer DEFAULT_PORT = Integer.valueOf(4321);
/*    */   
/*    */   public void begin(InterpretationContext ec, String name, Attributes attributes) throws ActionException {
/* 33 */     String portStr = attributes.getValue("port");
/* 34 */     Integer port = null;
/* 36 */     if (portStr == null) {
/* 37 */       port = DEFAULT_PORT;
/*    */     } else {
/*    */       try {
/* 40 */         port = Integer.valueOf(portStr);
/* 41 */       } catch (NumberFormatException ex) {
/* 42 */         addError("Port " + portStr + " in ConsolePlugin config is not a correct number");
/*    */       } 
/*    */     } 
/* 47 */     LoggerContext lc = (LoggerContext)ec.getContext();
/* 48 */     SocketAppender appender = new SocketAppender();
/* 49 */     appender.setContext((Context)lc);
/* 50 */     appender.setIncludeCallerData(true);
/* 51 */     appender.setRemoteHost("localhost");
/* 52 */     appender.setPort(port.intValue());
/* 53 */     appender.start();
/* 54 */     Logger root = lc.getLogger("ROOT");
/* 55 */     root.addAppender((Appender)appender);
/* 57 */     addInfo("Sending LoggingEvents to the plugin using port " + port);
/*    */   }
/*    */   
/*    */   public void end(InterpretationContext ec, String name) throws ActionException {}
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\ch\qos\logback\classic\joran\action\ConsolePluginAction.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */