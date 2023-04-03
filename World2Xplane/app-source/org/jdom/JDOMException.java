/*     */ package org.jdom;
/*     */ 
/*     */ import java.io.PrintStream;
/*     */ import java.io.PrintWriter;
/*     */ import java.lang.reflect.Field;
/*     */ import java.lang.reflect.InvocationTargetException;
/*     */ import java.lang.reflect.Method;
/*     */ import java.sql.SQLException;
/*     */ import org.xml.sax.SAXException;
/*     */ 
/*     */ public class JDOMException extends Exception {
/*     */   private static final String CVS_ID = "@(#) $RCSfile: JDOMException.java,v $ $Revision: 1.26 $ $Date: 2008/12/10 00:59:51 $ $Name:  $";
/*     */   
/*     */   private Throwable cause;
/*     */   
/*     */   public JDOMException() {
/*  87 */     super("Error occurred in JDOM application.");
/*     */   }
/*     */   
/*     */   public JDOMException(String message) {
/*  97 */     super(message);
/*     */   }
/*     */   
/*     */   public JDOMException(String message, Throwable cause) {
/* 111 */     super(message);
/* 112 */     this.cause = cause;
/*     */   }
/*     */   
/*     */   public Throwable initCause(Throwable cause) {
/* 124 */     this.cause = cause;
/* 125 */     return this;
/*     */   }
/*     */   
/*     */   public String getMessage() {
/* 137 */     String msg = super.getMessage();
/* 139 */     Throwable parent = this;
/*     */     Throwable child;
/* 143 */     while ((child = getNestedException(parent)) != null) {
/* 145 */       String msg2 = child.getMessage();
/* 150 */       if (child instanceof SAXException) {
/* 151 */         Throwable grandchild = ((SAXException)child).getException();
/* 155 */         if (grandchild != null && msg2 != null && msg2.equals(grandchild.getMessage()))
/* 156 */           msg2 = null; 
/*     */       } 
/* 161 */       if (msg2 != null)
/* 162 */         if (msg != null) {
/* 163 */           msg = msg + ": " + msg2;
/*     */         } else {
/* 165 */           msg = msg2;
/*     */         }  
/* 171 */       if (child instanceof JDOMException)
/*     */         break; 
/* 174 */       parent = child;
/*     */     } 
/* 178 */     return msg;
/*     */   }
/*     */   
/*     */   public void printStackTrace() {
/* 188 */     super.printStackTrace();
/* 190 */     Throwable parent = this;
/*     */     Throwable child;
/* 194 */     while ((child = getNestedException(parent)) != null) {
/* 195 */       System.err.print("Caused by: ");
/* 196 */       child.printStackTrace();
/* 199 */       if (child instanceof JDOMException)
/*     */         break; 
/* 202 */       parent = child;
/*     */     } 
/*     */   }
/*     */   
/*     */   public void printStackTrace(PrintStream s) {
/* 215 */     super.printStackTrace(s);
/* 217 */     Throwable parent = this;
/*     */     Throwable child;
/* 221 */     while ((child = getNestedException(parent)) != null) {
/* 222 */       s.print("Caused by: ");
/* 223 */       child.printStackTrace(s);
/* 226 */       if (child instanceof JDOMException)
/*     */         break; 
/* 229 */       parent = child;
/*     */     } 
/*     */   }
/*     */   
/*     */   public void printStackTrace(PrintWriter w) {
/* 242 */     super.printStackTrace(w);
/* 244 */     Throwable parent = this;
/*     */     Throwable child;
/* 248 */     while ((child = getNestedException(parent)) != null) {
/* 249 */       w.print("Caused by: ");
/* 250 */       child.printStackTrace(w);
/* 253 */       if (child instanceof JDOMException)
/*     */         break; 
/* 256 */       parent = child;
/*     */     } 
/*     */   }
/*     */   
/*     */   public Throwable getCause() {
/* 267 */     return this.cause;
/*     */   }
/*     */   
/*     */   private static Throwable getNestedException(Throwable parent) {
/* 273 */     if (parent instanceof JDOMException)
/* 274 */       return ((JDOMException)parent).getCause(); 
/* 277 */     if (parent instanceof SAXException)
/* 278 */       return ((SAXException)parent).getException(); 
/* 281 */     if (parent instanceof SQLException)
/* 282 */       return ((SQLException)parent).getNextException(); 
/* 285 */     if (parent instanceof InvocationTargetException)
/* 286 */       return ((InvocationTargetException)parent).getTargetException(); 
/* 289 */     if (parent instanceof ExceptionInInitializerError)
/* 290 */       return ((ExceptionInInitializerError)parent).getException(); 
/* 295 */     Throwable nestedException = getNestedExceptionFromField(parent, "java.rmi.RemoteException", "detail");
/* 296 */     if (nestedException != null)
/* 297 */       return nestedException; 
/* 302 */     nestedException = getNestedException(parent, "javax.naming.NamingException", "getRootCause");
/* 303 */     if (nestedException != null)
/* 304 */       return nestedException; 
/* 307 */     nestedException = getNestedException(parent, "javax.servlet.ServletException", "getRootCause");
/* 308 */     if (nestedException != null)
/* 309 */       return nestedException; 
/* 312 */     return null;
/*     */   }
/*     */   
/*     */   private static Throwable getNestedException(Throwable parent, String className, String methodName) {
/*     */     try {
/* 321 */       Class testClass = Class.forName(className);
/* 322 */       Class objectClass = parent.getClass();
/* 323 */       if (testClass.isAssignableFrom(objectClass)) {
/* 325 */         Class[] argClasses = new Class[0];
/* 326 */         Method method = testClass.getMethod(methodName, argClasses);
/* 327 */         Object[] args = new Object[0];
/* 328 */         return (Throwable)method.invoke(parent, args);
/*     */       } 
/* 331 */     } catch (Exception ex) {}
/* 338 */     return null;
/*     */   }
/*     */   
/*     */   private static Throwable getNestedExceptionFromField(Throwable parent, String className, String fieldName) {
/*     */     try {
/* 347 */       Class testClass = Class.forName(className);
/* 348 */       Class objectClass = parent.getClass();
/* 349 */       if (testClass.isAssignableFrom(objectClass)) {
/* 351 */         Class[] argClasses = new Class[0];
/* 352 */         Field field = testClass.getField(fieldName);
/* 353 */         return (Throwable)field.get(parent);
/*     */       } 
/* 356 */     } catch (Exception ex) {}
/* 365 */     return null;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jdom\JDOMException.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */