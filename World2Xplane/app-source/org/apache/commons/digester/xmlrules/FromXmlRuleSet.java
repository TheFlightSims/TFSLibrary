/*     */ package org.apache.commons.digester.xmlrules;
/*     */ 
/*     */ import java.net.URL;
/*     */ import org.apache.commons.digester.Digester;
/*     */ import org.apache.commons.digester.RuleSet;
/*     */ import org.apache.commons.digester.RuleSetBase;
/*     */ import org.xml.sax.InputSource;
/*     */ 
/*     */ public class FromXmlRuleSet extends RuleSetBase {
/*     */   public static final String DIGESTER_DTD_PATH = "org/apache/commons/digester/xmlrules/digester-rules.dtd";
/*     */   
/*     */   private XMLRulesLoader rulesLoader;
/*     */   
/*     */   private DigesterRuleParser parser;
/*     */   
/*     */   private Digester rulesDigester;
/*     */   
/*     */   public FromXmlRuleSet(URL rulesXml) {
/*  60 */     this(rulesXml, new DigesterRuleParser(), new Digester());
/*     */   }
/*     */   
/*     */   public FromXmlRuleSet(URL rulesXml, Digester rulesDigester) {
/*  70 */     this(rulesXml, new DigesterRuleParser(), rulesDigester);
/*     */   }
/*     */   
/*     */   public FromXmlRuleSet(URL rulesXml, DigesterRuleParser parser) {
/*  78 */     this(rulesXml, parser, new Digester());
/*     */   }
/*     */   
/*     */   public FromXmlRuleSet(URL rulesXml, DigesterRuleParser parser, Digester rulesDigester) {
/*  87 */     init(new URLXMLRulesLoader(this, rulesXml), parser, rulesDigester);
/*     */   }
/*     */   
/*     */   public FromXmlRuleSet(InputSource inputSource) {
/*  96 */     this(inputSource, new DigesterRuleParser(), new Digester());
/*     */   }
/*     */   
/*     */   public FromXmlRuleSet(InputSource inputSource, Digester rulesDigester) {
/* 106 */     this(inputSource, new DigesterRuleParser(), rulesDigester);
/*     */   }
/*     */   
/*     */   public FromXmlRuleSet(InputSource inputSource, DigesterRuleParser parser) {
/* 114 */     this(inputSource, parser, new Digester());
/*     */   }
/*     */   
/*     */   public FromXmlRuleSet(InputSource inputSource, DigesterRuleParser parser, Digester rulesDigester) {
/* 123 */     init(new InputSourceXMLRulesLoader(this, inputSource), parser, rulesDigester);
/*     */   }
/*     */   
/*     */   private void init(XMLRulesLoader rulesLoader, DigesterRuleParser parser, Digester rulesDigester) {
/* 130 */     this.rulesLoader = rulesLoader;
/* 131 */     this.parser = parser;
/* 132 */     this.rulesDigester = rulesDigester;
/*     */   }
/*     */   
/*     */   public void addRuleInstances(Digester digester) throws XmlLoadException {
/* 141 */     addRuleInstances(digester, null);
/*     */   }
/*     */   
/*     */   public void addRuleInstances(Digester digester, String basePath) throws XmlLoadException {
/* 164 */     URL dtdURL = getClass().getClassLoader().getResource("org/apache/commons/digester/xmlrules/digester-rules.dtd");
/* 165 */     if (dtdURL == null)
/* 166 */       throw new XmlLoadException("Cannot find resource \"org/apache/commons/digester/xmlrules/digester-rules.dtd\""); 
/* 169 */     this.parser.setDigesterRulesDTD(dtdURL.toString());
/* 170 */     this.parser.setTarget(digester);
/* 171 */     this.parser.setBasePath(basePath);
/* 173 */     this.rulesDigester.addRuleSet((RuleSet)this.parser);
/* 174 */     this.rulesDigester.push(this.parser);
/* 176 */     this.rulesLoader.loadRules();
/*     */   }
/*     */   
/*     */   private static abstract class XMLRulesLoader {
/*     */     private XMLRulesLoader() {}
/*     */     
/*     */     public abstract void loadRules() throws XmlLoadException;
/*     */   }
/*     */   
/*     */   private class URLXMLRulesLoader extends XMLRulesLoader {
/*     */     private URL url;
/*     */     
/*     */     private final FromXmlRuleSet this$0;
/*     */     
/*     */     public URLXMLRulesLoader(FromXmlRuleSet this$0, URL url) {
/* 191 */       this.this$0 = this$0;
/* 192 */       this.url = url;
/*     */     }
/*     */     
/*     */     public void loadRules() throws XmlLoadException {
/*     */       try {
/* 197 */         this.this$0.rulesDigester.parse(this.url.openStream());
/* 198 */       } catch (Exception ex) {
/* 199 */         throw new XmlLoadException(ex);
/*     */       } 
/*     */     }
/*     */   }
/*     */   
/*     */   private class InputSourceXMLRulesLoader extends XMLRulesLoader {
/*     */     private InputSource inputSource;
/*     */     
/*     */     private final FromXmlRuleSet this$0;
/*     */     
/*     */     public InputSourceXMLRulesLoader(FromXmlRuleSet this$0, InputSource inputSource) {
/* 207 */       this.this$0 = this$0;
/* 208 */       this.inputSource = inputSource;
/*     */     }
/*     */     
/*     */     public void loadRules() throws XmlLoadException {
/*     */       try {
/* 213 */         this.this$0.rulesDigester.parse(this.inputSource);
/* 214 */       } catch (Exception ex) {
/* 215 */         throw new XmlLoadException(ex);
/*     */       } 
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\digester\xmlrules\FromXmlRuleSet.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */