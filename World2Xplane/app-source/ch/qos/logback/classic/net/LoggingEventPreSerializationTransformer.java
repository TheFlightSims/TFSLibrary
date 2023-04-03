/*    */ package ch.qos.logback.classic.net;
/*    */ 
/*    */ import ch.qos.logback.classic.spi.ILoggingEvent;
/*    */ import ch.qos.logback.classic.spi.LoggingEventVO;
/*    */ import ch.qos.logback.core.spi.PreSerializationTransformer;
/*    */ import java.io.Serializable;
/*    */ 
/*    */ public class LoggingEventPreSerializationTransformer implements PreSerializationTransformer<ILoggingEvent> {
/*    */   public Serializable transform(ILoggingEvent event) {
/* 27 */     if (event == null)
/* 28 */       return null; 
/* 30 */     if (event instanceof ch.qos.logback.classic.spi.LoggingEvent)
/* 31 */       return (Serializable)LoggingEventVO.build(event); 
/* 32 */     if (event instanceof LoggingEventVO)
/* 33 */       return (Serializable)event; 
/* 35 */     throw new IllegalArgumentException("Unsupported type " + event.getClass().getName());
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\ch\qos\logback\classic\net\LoggingEventPreSerializationTransformer.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */