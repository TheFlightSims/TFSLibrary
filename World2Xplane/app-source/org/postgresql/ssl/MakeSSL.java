/*    */ package org.postgresql.ssl;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import java.lang.reflect.Constructor;
/*    */ import java.net.Socket;
/*    */ import java.util.Properties;
/*    */ import javax.net.ssl.SSLSocketFactory;
/*    */ import org.postgresql.core.Logger;
/*    */ import org.postgresql.core.PGStream;
/*    */ import org.postgresql.util.GT;
/*    */ import org.postgresql.util.PSQLException;
/*    */ import org.postgresql.util.PSQLState;
/*    */ 
/*    */ public class MakeSSL {
/*    */   public static void convert(PGStream stream, Properties info, Logger logger) throws IOException, PSQLException {
/*    */     SSLSocketFactory factory;
/* 26 */     logger.debug("converting regular socket connection to ssl");
/* 32 */     String classname = info.getProperty("sslfactory");
/* 33 */     if (classname == null) {
/* 35 */       factory = (SSLSocketFactory)SSLSocketFactory.getDefault();
/*    */     } else {
/* 39 */       Object[] args = { info.getProperty("sslfactoryarg") };
/*    */       try {
/*    */         Constructor<?> constructor;
/* 45 */         Class<?> factoryClass = Class.forName(classname);
/*    */         try {
/* 48 */           constructor = factoryClass.getConstructor(new Class[] { String.class });
/* 50 */         } catch (NoSuchMethodException nsme) {
/* 52 */           constructor = factoryClass.getConstructor((Class[])null);
/* 53 */           args = null;
/*    */         } 
/* 55 */         factory = (SSLSocketFactory)constructor.newInstance(args);
/* 57 */       } catch (Exception e) {
/* 59 */         throw new PSQLException(GT.tr("The SSLSocketFactory class provided {0} could not be instantiated.", classname), PSQLState.CONNECTION_FAILURE, e);
/*    */       } 
/*    */     } 
/* 63 */     Socket newConnection = factory.createSocket(stream.getSocket(), stream.getHost(), stream.getPort(), true);
/* 64 */     stream.changeSocket(newConnection);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\postgresql\ssl\MakeSSL.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */