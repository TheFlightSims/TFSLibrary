/*     */ package org.apache.commons.digester.plugins;
/*     */ 
/*     */ import java.util.Properties;
/*     */ import org.apache.commons.digester.Digester;
/*     */ import org.apache.commons.digester.Rule;
/*     */ import org.xml.sax.Attributes;
/*     */ 
/*     */ public class PluginDeclarationRule extends Rule {
/*     */   public void begin(String namespace, String name, Attributes attributes) throws Exception {
/*  68 */     int nAttrs = attributes.getLength();
/*  69 */     Properties props = new Properties();
/*  70 */     for (int i = 0; i < nAttrs; i++) {
/*  71 */       String key = attributes.getLocalName(i);
/*  72 */       if (key == null || key.length() == 0)
/*  73 */         key = attributes.getQName(i); 
/*  75 */       String value = attributes.getValue(i);
/*  76 */       props.setProperty(key, value);
/*     */     } 
/*     */     try {
/*  80 */       declarePlugin(this.digester, props);
/*  81 */     } catch (PluginInvalidInputException ex) {
/*  82 */       throw new PluginInvalidInputException("Error on element [" + this.digester.getMatch() + "]: " + ex.getMessage());
/*     */     } 
/*     */   }
/*     */   
/*     */   public static void declarePlugin(Digester digester, Properties props) throws PluginException {
/*  91 */     String id = props.getProperty("id");
/*  92 */     String pluginClassName = props.getProperty("class");
/*  94 */     if (id == null)
/*  95 */       throw new PluginInvalidInputException("mandatory attribute id not present on plugin declaration"); 
/*  99 */     if (pluginClassName == null)
/* 100 */       throw new PluginInvalidInputException("mandatory attribute class not present on plugin declaration"); 
/* 104 */     Declaration newDecl = new Declaration(pluginClassName);
/* 105 */     newDecl.setId(id);
/* 106 */     newDecl.setProperties(props);
/* 108 */     PluginRules rc = (PluginRules)digester.getRules();
/* 109 */     PluginManager pm = rc.getPluginManager();
/* 111 */     newDecl.init(digester, pm);
/* 112 */     pm.addDeclaration(newDecl);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\digester\plugins\PluginDeclarationRule.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */