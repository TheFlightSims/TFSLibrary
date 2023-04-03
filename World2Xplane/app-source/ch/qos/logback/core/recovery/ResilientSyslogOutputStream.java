/*    */ package ch.qos.logback.core.recovery;
/*    */ 
/*    */ import ch.qos.logback.core.net.SyslogOutputStream;
/*    */ import java.io.IOException;
/*    */ import java.io.OutputStream;
/*    */ import java.net.SocketException;
/*    */ import java.net.UnknownHostException;
/*    */ 
/*    */ public class ResilientSyslogOutputStream extends ResilientOutputStreamBase {
/*    */   String syslogHost;
/*    */   
/*    */   int port;
/*    */   
/*    */   public ResilientSyslogOutputStream(String syslogHost, int port) throws UnknownHostException, SocketException {
/* 31 */     this.syslogHost = syslogHost;
/* 32 */     this.port = port;
/* 33 */     this.os = (OutputStream)new SyslogOutputStream(syslogHost, port);
/* 34 */     this.presumedClean = true;
/*    */   }
/*    */   
/*    */   String getDescription() {
/* 39 */     return "syslog [" + this.syslogHost + ":" + this.port + "]";
/*    */   }
/*    */   
/*    */   OutputStream openNewOutputStream() throws IOException {
/* 44 */     return (OutputStream)new SyslogOutputStream(this.syslogHost, this.port);
/*    */   }
/*    */   
/*    */   public String toString() {
/* 49 */     return "c.q.l.c.recovery.ResilientSyslogOutputStream@" + System.identityHashCode(this);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\ch\qos\logback\core\recovery\ResilientSyslogOutputStream.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */