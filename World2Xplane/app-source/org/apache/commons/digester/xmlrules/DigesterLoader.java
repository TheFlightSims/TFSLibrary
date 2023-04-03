/*     */ package org.apache.commons.digester.xmlrules;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.Reader;
/*     */ import java.net.URL;
/*     */ import org.apache.commons.digester.Digester;
/*     */ import org.apache.commons.digester.RuleSet;
/*     */ import org.xml.sax.InputSource;
/*     */ import org.xml.sax.SAXException;
/*     */ 
/*     */ public class DigesterLoader {
/*     */   public static Digester createDigester(InputSource rulesSource) {
/*  50 */     FromXmlRuleSet fromXmlRuleSet = new FromXmlRuleSet(rulesSource);
/*  51 */     Digester digester = new Digester();
/*  52 */     digester.addRuleSet((RuleSet)fromXmlRuleSet);
/*  53 */     return digester;
/*     */   }
/*     */   
/*     */   public static Digester createDigester(InputSource rulesSource, Digester rulesDigester) {
/*  66 */     FromXmlRuleSet fromXmlRuleSet = new FromXmlRuleSet(rulesSource, rulesDigester);
/*  67 */     Digester digester = new Digester();
/*  68 */     digester.addRuleSet((RuleSet)fromXmlRuleSet);
/*  69 */     return digester;
/*     */   }
/*     */   
/*     */   public static Digester createDigester(URL rulesXml) {
/*  78 */     FromXmlRuleSet fromXmlRuleSet = new FromXmlRuleSet(rulesXml);
/*  79 */     Digester digester = new Digester();
/*  80 */     digester.addRuleSet((RuleSet)fromXmlRuleSet);
/*  81 */     return digester;
/*     */   }
/*     */   
/*     */   public static Digester createDigester(URL rulesXml, Digester rulesDigester) {
/*  96 */     FromXmlRuleSet fromXmlRuleSet = new FromXmlRuleSet(rulesXml, rulesDigester);
/*  97 */     Digester digester = new Digester();
/*  98 */     digester.addRuleSet((RuleSet)fromXmlRuleSet);
/*  99 */     return digester;
/*     */   }
/*     */   
/*     */   public static Object load(URL digesterRules, ClassLoader classLoader, URL fileURL) throws IOException, SAXException, DigesterLoadingException {
/* 114 */     return load(digesterRules, classLoader, fileURL.openStream());
/*     */   }
/*     */   
/*     */   public static Object load(URL digesterRules, ClassLoader classLoader, InputStream input) throws IOException, SAXException, DigesterLoadingException {
/* 129 */     Digester digester = createDigester(digesterRules);
/* 130 */     digester.setClassLoader(classLoader);
/*     */     try {
/* 132 */       return digester.parse(input);
/* 133 */     } catch (XmlLoadException ex) {
/* 137 */       throw new DigesterLoadingException(ex.getMessage(), ex);
/*     */     } 
/*     */   }
/*     */   
/*     */   public static Object load(URL digesterRules, ClassLoader classLoader, Reader reader) throws IOException, SAXException, DigesterLoadingException {
/* 159 */     Digester digester = createDigester(digesterRules);
/* 160 */     digester.setClassLoader(classLoader);
/*     */     try {
/* 162 */       return digester.parse(reader);
/* 163 */     } catch (XmlLoadException ex) {
/* 167 */       throw new DigesterLoadingException(ex.getMessage(), ex);
/*     */     } 
/*     */   }
/*     */   
/*     */   public static Object load(URL digesterRules, ClassLoader classLoader, URL fileURL, Object rootObject) throws IOException, SAXException, DigesterLoadingException {
/* 188 */     return load(digesterRules, classLoader, fileURL.openStream(), rootObject);
/*     */   }
/*     */   
/*     */   public static Object load(URL digesterRules, ClassLoader classLoader, InputStream input, Object rootObject) throws IOException, SAXException, DigesterLoadingException {
/* 206 */     Digester digester = createDigester(digesterRules);
/* 207 */     digester.setClassLoader(classLoader);
/* 208 */     digester.push(rootObject);
/*     */     try {
/* 210 */       return digester.parse(input);
/* 211 */     } catch (XmlLoadException ex) {
/* 215 */       throw new DigesterLoadingException(ex.getMessage(), ex);
/*     */     } 
/*     */   }
/*     */   
/*     */   public static Object load(URL digesterRules, ClassLoader classLoader, Reader input, Object rootObject) throws IOException, SAXException, DigesterLoadingException {
/* 240 */     Digester digester = createDigester(digesterRules);
/* 241 */     digester.setClassLoader(classLoader);
/* 242 */     digester.push(rootObject);
/*     */     try {
/* 244 */       return digester.parse(input);
/* 245 */     } catch (XmlLoadException ex) {
/* 249 */       throw new DigesterLoadingException(ex.getMessage(), ex);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\digester\xmlrules\DigesterLoader.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */