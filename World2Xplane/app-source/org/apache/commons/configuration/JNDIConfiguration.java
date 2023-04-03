/*     */ package org.apache.commons.configuration;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashSet;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Set;
/*     */ import javax.naming.Context;
/*     */ import javax.naming.InitialContext;
/*     */ import javax.naming.NameClassPair;
/*     */ import javax.naming.NameNotFoundException;
/*     */ import javax.naming.NamingEnumeration;
/*     */ import javax.naming.NamingException;
/*     */ import javax.naming.NotContextException;
/*     */ import org.apache.commons.lang.StringUtils;
/*     */ import org.apache.commons.logging.LogFactory;
/*     */ 
/*     */ public class JNDIConfiguration extends AbstractConfiguration {
/*     */   private String prefix;
/*     */   
/*     */   private Context context;
/*     */   
/*     */   private Context baseContext;
/*     */   
/*  58 */   private Set clearedProperties = new HashSet();
/*     */   
/*     */   public JNDIConfiguration() throws NamingException {
/*  68 */     this((String)null);
/*     */   }
/*     */   
/*     */   public JNDIConfiguration(String prefix) throws NamingException {
/*  81 */     this(new InitialContext(), prefix);
/*     */   }
/*     */   
/*     */   public JNDIConfiguration(Context context) {
/*  92 */     this(context, (String)null);
/*     */   }
/*     */   
/*     */   public JNDIConfiguration(Context context, String prefix) {
/* 104 */     this.context = context;
/* 105 */     this.prefix = prefix;
/* 106 */     setLogger(LogFactory.getLog(getClass()));
/* 107 */     addErrorLogListener();
/*     */   }
/*     */   
/*     */   private void recursiveGetKeys(Set keys, Context context, String prefix, Set processedCtx) throws NamingException {
/* 123 */     processedCtx.add(context);
/* 124 */     NamingEnumeration elements = null;
/*     */     try {
/* 128 */       elements = context.list("");
/* 131 */       while (elements.hasMore()) {
/* 133 */         NameClassPair nameClassPair = elements.next();
/* 134 */         String name = nameClassPair.getName();
/* 135 */         Object object = context.lookup(name);
/* 138 */         StringBuffer key = new StringBuffer();
/* 139 */         key.append(prefix);
/* 140 */         if (key.length() > 0)
/* 142 */           key.append("."); 
/* 144 */         key.append(name);
/* 146 */         if (object instanceof Context) {
/* 149 */           Context subcontext = (Context)object;
/* 150 */           if (!processedCtx.contains(subcontext))
/* 152 */             recursiveGetKeys(keys, subcontext, key.toString(), processedCtx); 
/*     */           continue;
/*     */         } 
/* 159 */         keys.add(key.toString());
/*     */       } 
/*     */     } finally {
/* 166 */       if (elements != null)
/* 168 */         elements.close(); 
/*     */     } 
/*     */   }
/*     */   
/*     */   public Iterator getKeys() {
/* 180 */     return getKeys("");
/*     */   }
/*     */   
/*     */   public Iterator getKeys(String prefix) {
/* 193 */     String[] splitPath = StringUtils.split(prefix, ".");
/* 195 */     List path = new ArrayList();
/* 197 */     for (int i = 0; i < splitPath.length; i++)
/* 199 */       path.add(splitPath[i]); 
/*     */     try {
/* 205 */       Context context = getContext(path, getBaseContext());
/* 208 */       Set keys = new HashSet();
/* 209 */       if (context != null) {
/* 211 */         recursiveGetKeys(keys, context, prefix, new HashSet());
/* 213 */       } else if (containsKey(prefix)) {
/* 216 */         keys.add(prefix);
/*     */       } 
/* 219 */       return keys.iterator();
/* 221 */     } catch (NameNotFoundException e) {
/* 224 */       return (new ArrayList()).iterator();
/* 226 */     } catch (NamingException e) {
/* 228 */       fireError(5, null, null, e);
/* 229 */       return (new ArrayList()).iterator();
/*     */     } 
/*     */   }
/*     */   
/*     */   private Context getContext(List path, Context context) throws NamingException {
/* 246 */     if (path == null || path.isEmpty())
/* 248 */       return context; 
/* 251 */     String key = path.get(0);
/* 254 */     NamingEnumeration elements = null;
/*     */     try {
/* 258 */       elements = context.list("");
/* 259 */       while (elements.hasMore()) {
/* 261 */         NameClassPair nameClassPair = elements.next();
/* 262 */         String name = nameClassPair.getName();
/* 263 */         Object object = context.lookup(name);
/* 265 */         if (object instanceof Context && name.equals(key)) {
/* 267 */           Context subcontext = (Context)object;
/* 270 */           return getContext(path.subList(1, path.size()), subcontext);
/*     */         } 
/*     */       } 
/*     */     } finally {
/* 276 */       if (elements != null)
/* 278 */         elements.close(); 
/*     */     } 
/* 282 */     return null;
/*     */   }
/*     */   
/*     */   public boolean isEmpty() {
/*     */     try {
/* 294 */       NamingEnumeration enumeration = null;
/*     */       try {
/* 298 */         enumeration = getBaseContext().list("");
/* 299 */         return !enumeration.hasMore();
/*     */       } finally {
/* 304 */         if (enumeration != null)
/* 306 */           enumeration.close(); 
/*     */       } 
/* 310 */     } catch (NamingException e) {
/* 312 */       fireError(5, null, null, e);
/* 313 */       return true;
/*     */     } 
/*     */   }
/*     */   
/*     */   public void setProperty(String key, Object value) {
/* 327 */     throw new UnsupportedOperationException("This operation is not supported");
/*     */   }
/*     */   
/*     */   public void clearProperty(String key) {
/* 337 */     this.clearedProperties.add(key);
/*     */   }
/*     */   
/*     */   public boolean containsKey(String key) {
/* 348 */     if (this.clearedProperties.contains(key))
/* 350 */       return false; 
/* 352 */     key = StringUtils.replace(key, ".", "/");
/*     */     try {
/* 356 */       getBaseContext().lookup(key);
/* 357 */       return true;
/* 359 */     } catch (NameNotFoundException e) {
/* 362 */       return false;
/* 364 */     } catch (NamingException e) {
/* 366 */       fireError(5, key, null, e);
/* 367 */       return false;
/*     */     } 
/*     */   }
/*     */   
/*     */   public String getPrefix() {
/* 377 */     return this.prefix;
/*     */   }
/*     */   
/*     */   public void setPrefix(String prefix) {
/* 387 */     this.prefix = prefix;
/* 390 */     this.baseContext = null;
/*     */   }
/*     */   
/*     */   public Object getProperty(String key) {
/* 401 */     if (this.clearedProperties.contains(key))
/* 403 */       return null; 
/*     */     try {
/* 408 */       key = StringUtils.replace(key, ".", "/");
/* 409 */       return getBaseContext().lookup(key);
/* 411 */     } catch (NameNotFoundException e) {
/* 414 */       return null;
/* 416 */     } catch (NotContextException nctxex) {
/* 419 */       return null;
/* 421 */     } catch (NamingException e) {
/* 423 */       fireError(5, key, null, e);
/* 424 */       return null;
/*     */     } 
/*     */   }
/*     */   
/*     */   protected void addPropertyDirect(String key, Object obj) {
/* 438 */     throw new UnsupportedOperationException("This operation is not supported");
/*     */   }
/*     */   
/*     */   public Context getBaseContext() throws NamingException {
/* 449 */     if (this.baseContext == null)
/* 451 */       this.baseContext = (Context)getContext().lookup((this.prefix == null) ? "" : this.prefix); 
/* 454 */     return this.baseContext;
/*     */   }
/*     */   
/*     */   public Context getContext() {
/* 465 */     return this.context;
/*     */   }
/*     */   
/*     */   public void setContext(Context context) {
/* 476 */     this.clearedProperties.clear();
/* 479 */     this.context = context;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\configuration\JNDIConfiguration.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */