/*     */ package org.geotools.data.ows;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.OutputStream;
/*     */ import java.net.MalformedURLException;
/*     */ import java.net.URL;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ import java.util.Properties;
/*     */ import java.util.StringTokenizer;
/*     */ 
/*     */ public abstract class AbstractRequest implements Request {
/*     */   public static final String EXCEPTION_XML = "application/vnd.ogc.se_xml";
/*     */   
/*     */   protected URL onlineResource;
/*     */   
/*     */   protected Properties properties;
/*     */   
/*     */   public AbstractRequest(URL onlineResource, Properties properties) {
/*  69 */     if (properties == null) {
/*  70 */       this.properties = new Properties();
/*     */     } else {
/*  72 */       this.properties = properties;
/*     */     } 
/*  78 */     int index = onlineResource.toExternalForm().lastIndexOf("?");
/*  79 */     String urlWithoutQuery = null;
/*  81 */     if (index <= 0) {
/*  82 */       urlWithoutQuery = onlineResource.toExternalForm() + "?";
/*     */     } else {
/*  84 */       urlWithoutQuery = onlineResource.toExternalForm().substring(0, index);
/*  85 */       boolean once = true;
/*  88 */       if (onlineResource.getQuery() != null) {
/*  89 */         StringTokenizer tokenizer = new StringTokenizer(onlineResource.getQuery(), "&");
/*  92 */         while (tokenizer.hasMoreTokens()) {
/*  93 */           String token = tokenizer.nextToken();
/*  94 */           String[] param = token.split("=");
/*  95 */           if (param != null && param.length > 0 && param[0] != null) {
/*  96 */             String key = param[0];
/*  98 */             if (param.length == 1) {
/* 102 */               if (once) {
/* 103 */                 urlWithoutQuery = urlWithoutQuery + "?" + param[0] + "&";
/* 104 */                 once = false;
/*     */                 continue;
/*     */               } 
/* 107 */               urlWithoutQuery = urlWithoutQuery + param[0] + "&";
/*     */               continue;
/*     */             } 
/* 111 */             String value = param[1];
/* 112 */             setProperty(key.toUpperCase(), value);
/*     */           } 
/*     */         } 
/*     */       } 
/* 117 */       if (once)
/* 118 */         urlWithoutQuery = urlWithoutQuery + "?"; 
/*     */     } 
/*     */     try {
/* 122 */       this.onlineResource = new URL(urlWithoutQuery);
/* 123 */     } catch (MalformedURLException e) {
/* 124 */       throw new RuntimeException("Error parsing URL. This is likely a bug in the code.");
/*     */     } 
/* 126 */     initService();
/* 127 */     initRequest();
/* 128 */     initVersion();
/*     */   }
/*     */   
/*     */   public URL getFinalURL() {
/* 135 */     if (this.onlineResource.getProtocol().equalsIgnoreCase("file"))
/* 136 */       return this.onlineResource; 
/* 138 */     String url = this.onlineResource.toExternalForm();
/* 140 */     if (!url.contains("?"))
/* 141 */       url = url.concat("?"); 
/* 144 */     Iterator<Map.Entry<Object, Object>> iter = this.properties.entrySet().iterator();
/* 145 */     while (iter.hasNext()) {
/* 146 */       Map.Entry entry = iter.next();
/* 148 */       String value = (String)entry.getValue();
/* 154 */       String param = processKey((String)entry.getKey());
/* 155 */       if (value != null && param.length() != 0)
/* 156 */         param = param + "=" + value; 
/* 158 */       if (iter.hasNext())
/* 159 */         param = param.concat("&"); 
/* 162 */       url = url.concat(param);
/*     */     } 
/*     */     try {
/* 166 */       return new URL(url);
/* 167 */     } catch (MalformedURLException e) {
/* 168 */       e.printStackTrace();
/* 172 */       return null;
/*     */     } 
/*     */   }
/*     */   
/*     */   protected String processKey(String key) {
/* 186 */     return key;
/*     */   }
/*     */   
/*     */   public void setProperty(String name, String value) {
/* 190 */     if (value == null) {
/* 191 */       this.properties.remove(name);
/*     */     } else {
/* 193 */       this.properties.setProperty(name, value);
/*     */     } 
/*     */   }
/*     */   
/*     */   public Properties getProperties() {
/* 201 */     return (Properties)this.properties.clone();
/*     */   }
/*     */   
/*     */   protected abstract void initRequest();
/*     */   
/*     */   protected abstract void initService();
/*     */   
/*     */   protected abstract void initVersion();
/*     */   
/*     */   public String getPostContentType() {
/* 222 */     return "application/xml";
/*     */   }
/*     */   
/*     */   public void performPostOutput(OutputStream outputStream) throws IOException {}
/*     */   
/*     */   public boolean requiresPost() {
/* 236 */     return false;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\data\ows\AbstractRequest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */