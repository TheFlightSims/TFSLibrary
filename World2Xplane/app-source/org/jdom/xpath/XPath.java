/*     */ package org.jdom.xpath;
/*     */ 
/*     */ import java.io.InvalidObjectException;
/*     */ import java.io.ObjectStreamException;
/*     */ import java.io.Serializable;
/*     */ import java.lang.reflect.Constructor;
/*     */ import java.lang.reflect.InvocationTargetException;
/*     */ import java.lang.reflect.Modifier;
/*     */ import java.util.List;
/*     */ import org.jdom.JDOMException;
/*     */ import org.jdom.Namespace;
/*     */ 
/*     */ public abstract class XPath implements Serializable {
/*     */   private static final String CVS_ID = "@(#) $RCSfile: XPath.java,v $ $Revision: 1.17 $ $Date: 2007/11/10 05:29:02 $ $Name:  $";
/*     */   
/*     */   private static final String XPATH_CLASS_PROPERTY = "org.jdom.xpath.class";
/*     */   
/*     */   private static final String DEFAULT_XPATH_CLASS = "org.jdom.xpath.JaxenXPath";
/*     */   
/*     */   public static final String JDOM_OBJECT_MODEL_URI = "http://jdom.org/jaxp/xpath/jdom";
/*     */   
/* 111 */   private static Constructor constructor = null;
/*     */   
/*     */   public static XPath newInstance(String path) throws JDOMException {
/*     */     try {
/* 123 */       if (constructor == null) {
/*     */         String str;
/*     */         try {
/* 127 */           str = System.getProperty("org.jdom.xpath.class", "org.jdom.xpath.JaxenXPath");
/* 130 */         } catch (SecurityException ex1) {
/* 132 */           str = "org.jdom.xpath.JaxenXPath";
/*     */         } 
/* 134 */         setXPathClass(Class.forName(str));
/*     */       } 
/* 137 */       return constructor.newInstance(new Object[] { path });
/* 139 */     } catch (JDOMException ex1) {
/* 140 */       throw ex1;
/* 142 */     } catch (InvocationTargetException ex2) {
/* 144 */       Throwable t = ex2.getTargetException();
/* 146 */       throw (t instanceof JDOMException) ? (JDOMException)t : new JDOMException(t.toString(), t);
/* 149 */     } catch (Exception ex3) {
/* 151 */       throw new JDOMException(ex3.toString(), ex3);
/*     */     } 
/*     */   }
/*     */   
/*     */   public static void setXPathClass(Class aClass) throws JDOMException {
/* 168 */     if (aClass == null)
/* 169 */       throw new IllegalArgumentException("aClass"); 
/*     */     try {
/* 173 */       if (XPath.class.isAssignableFrom(aClass) && !Modifier.isAbstract(aClass.getModifiers())) {
/* 176 */         constructor = aClass.getConstructor(new Class[] { String.class });
/*     */       } else {
/* 179 */         throw new JDOMException(aClass.getName() + " is not a concrete JDOM XPath implementation");
/*     */       } 
/* 183 */     } catch (JDOMException ex1) {
/* 184 */       throw ex1;
/* 186 */     } catch (Exception ex2) {
/* 188 */       throw new JDOMException(ex2.toString(), ex2);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void addNamespace(String prefix, String uri) {
/* 306 */     addNamespace(Namespace.getNamespace(prefix, uri));
/*     */   }
/*     */   
/*     */   public static List selectNodes(Object context, String path) throws JDOMException {
/* 345 */     return newInstance(path).selectNodes(context);
/*     */   }
/*     */   
/*     */   public static Object selectSingleNode(Object context, String path) throws JDOMException {
/* 376 */     return newInstance(path).selectSingleNode(context);
/*     */   }
/*     */   
/*     */   protected final Object writeReplace() throws ObjectStreamException {
/* 401 */     return new XPathString(getXPath());
/*     */   }
/*     */   
/*     */   public abstract List selectNodes(Object paramObject) throws JDOMException;
/*     */   
/*     */   public abstract Object selectSingleNode(Object paramObject) throws JDOMException;
/*     */   
/*     */   public abstract String valueOf(Object paramObject) throws JDOMException;
/*     */   
/*     */   public abstract Number numberValueOf(Object paramObject) throws JDOMException;
/*     */   
/*     */   public abstract void setVariable(String paramString, Object paramObject);
/*     */   
/*     */   public abstract void addNamespace(Namespace paramNamespace);
/*     */   
/*     */   public abstract String getXPath();
/*     */   
/*     */   private static final class XPathString implements Serializable {
/* 417 */     private String xPath = null;
/*     */     
/*     */     public XPathString(String xpath) {
/* 428 */       this.xPath = xpath;
/*     */     }
/*     */     
/*     */     private Object readResolve() throws ObjectStreamException {
/*     */       try {
/* 443 */         return XPath.newInstance(this.xPath);
/* 445 */       } catch (JDOMException ex1) {
/* 446 */         throw new InvalidObjectException("Can't create XPath object for expression \"" + this.xPath + "\": " + ex1.toString());
/*     */       } 
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jdom\xpath\XPath.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */