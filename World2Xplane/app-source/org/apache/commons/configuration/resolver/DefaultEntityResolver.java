/*     */ package org.apache.commons.configuration.resolver;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.net.URL;
/*     */ import java.net.URLConnection;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import org.xml.sax.EntityResolver;
/*     */ import org.xml.sax.InputSource;
/*     */ import org.xml.sax.SAXException;
/*     */ 
/*     */ public class DefaultEntityResolver implements EntityResolver, EntityRegistry {
/*  41 */   private Map registeredEntities = new HashMap();
/*     */   
/*     */   public void registerEntityId(String publicId, URL entityURL) {
/*  67 */     if (publicId == null)
/*  69 */       throw new IllegalArgumentException("Public ID must not be null!"); 
/*  71 */     getRegisteredEntities().put(publicId, entityURL);
/*     */   }
/*     */   
/*     */   public InputSource resolveEntity(String publicId, String systemId) throws SAXException {
/*  89 */     URL entityURL = null;
/*  90 */     if (publicId != null)
/*  92 */       entityURL = (URL)getRegisteredEntities().get(publicId); 
/*  95 */     if (entityURL != null)
/*     */       try {
/* 101 */         URLConnection connection = entityURL.openConnection();
/* 102 */         connection.setUseCaches(false);
/* 103 */         InputStream stream = connection.getInputStream();
/* 104 */         InputSource source = new InputSource(stream);
/* 105 */         source.setSystemId(entityURL.toExternalForm());
/* 106 */         return source;
/* 108 */       } catch (IOException e) {
/* 110 */         throw new SAXException(e);
/*     */       }  
/* 116 */     return null;
/*     */   }
/*     */   
/*     */   public Map getRegisteredEntities() {
/* 128 */     return this.registeredEntities;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\configuration\resolver\DefaultEntityResolver.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */