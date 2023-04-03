/*     */ package org.apache.commons.math3.exception.util;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.ObjectOutputStream;
/*     */ import java.io.Serializable;
/*     */ import java.text.MessageFormat;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Locale;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ 
/*     */ public class ExceptionContext implements Serializable {
/*     */   private static final long serialVersionUID = -6024911025449780478L;
/*     */   
/*     */   private Throwable throwable;
/*     */   
/*     */   private List<Localizable> msgPatterns;
/*     */   
/*     */   private List<Object[]> msgArguments;
/*     */   
/*     */   private Map<String, Object> context;
/*     */   
/*     */   public ExceptionContext(Throwable throwable) {
/*  65 */     this.throwable = throwable;
/*  66 */     this.msgPatterns = new ArrayList<Localizable>();
/*  67 */     this.msgArguments = new ArrayList();
/*  68 */     this.context = new HashMap<String, Object>();
/*     */   }
/*     */   
/*     */   public Throwable getThrowable() {
/*  75 */     return this.throwable;
/*     */   }
/*     */   
/*     */   public void addMessage(Localizable pattern, Object... arguments) {
/*  87 */     this.msgPatterns.add(pattern);
/*  88 */     this.msgArguments.add(ArgUtils.flatten(arguments));
/*     */   }
/*     */   
/*     */   public void setValue(String key, Object value) {
/* 100 */     this.context.put(key, value);
/*     */   }
/*     */   
/*     */   public Object getValue(String key) {
/* 110 */     return this.context.get(key);
/*     */   }
/*     */   
/*     */   public Set<String> getKeys() {
/* 119 */     return this.context.keySet();
/*     */   }
/*     */   
/*     */   public String getMessage() {
/* 128 */     return getMessage(Locale.US);
/*     */   }
/*     */   
/*     */   public String getLocalizedMessage() {
/* 137 */     return getMessage(Locale.getDefault());
/*     */   }
/*     */   
/*     */   public String getMessage(Locale locale) {
/* 147 */     return buildMessage(locale, ": ");
/*     */   }
/*     */   
/*     */   public String getMessage(Locale locale, String separator) {
/* 159 */     return buildMessage(locale, separator);
/*     */   }
/*     */   
/*     */   private String buildMessage(Locale locale, String separator) {
/* 171 */     StringBuilder sb = new StringBuilder();
/* 172 */     int count = 0;
/* 173 */     int len = this.msgPatterns.size();
/* 174 */     for (int i = 0; i < len; i++) {
/* 175 */       Localizable pat = this.msgPatterns.get(i);
/* 176 */       Object[] args = this.msgArguments.get(i);
/* 177 */       MessageFormat fmt = new MessageFormat(pat.getLocalizedString(locale), locale);
/* 179 */       sb.append(fmt.format(args));
/* 180 */       if (++count < len)
/* 182 */         sb.append(separator); 
/*     */     } 
/* 186 */     return sb.toString();
/*     */   }
/*     */   
/*     */   private void writeObject(ObjectOutputStream out) throws IOException {
/* 197 */     out.writeObject(this.throwable);
/* 198 */     serializeMessages(out);
/* 199 */     serializeContext(out);
/*     */   }
/*     */   
/*     */   private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
/* 211 */     this.throwable = (Throwable)in.readObject();
/* 212 */     deSerializeMessages(in);
/* 213 */     deSerializeContext(in);
/*     */   }
/*     */   
/*     */   private void serializeMessages(ObjectOutputStream out) throws IOException {
/* 225 */     int len = this.msgPatterns.size();
/* 226 */     out.writeInt(len);
/* 228 */     for (int i = 0; i < len; i++) {
/* 229 */       Localizable pat = this.msgPatterns.get(i);
/* 231 */       out.writeObject(pat);
/* 232 */       Object[] args = this.msgArguments.get(i);
/* 233 */       int aLen = args.length;
/* 235 */       out.writeInt(aLen);
/* 236 */       for (int j = 0; j < aLen; j++) {
/* 237 */         if (args[j] instanceof Serializable) {
/* 239 */           out.writeObject(args[j]);
/*     */         } else {
/* 242 */           out.writeObject(nonSerializableReplacement(args[j]));
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void deSerializeMessages(ObjectInputStream in) throws IOException, ClassNotFoundException {
/* 259 */     int len = in.readInt();
/* 260 */     this.msgPatterns = new ArrayList<Localizable>(len);
/* 261 */     this.msgArguments = new ArrayList(len);
/* 263 */     for (int i = 0; i < len; i++) {
/* 265 */       Localizable pat = (Localizable)in.readObject();
/* 266 */       this.msgPatterns.add(pat);
/* 268 */       int aLen = in.readInt();
/* 269 */       Object[] args = new Object[aLen];
/* 270 */       for (int j = 0; j < aLen; j++)
/* 272 */         args[j] = in.readObject(); 
/* 274 */       this.msgArguments.add(args);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void serializeContext(ObjectOutputStream out) throws IOException {
/* 287 */     int len = this.context.keySet().size();
/* 288 */     out.writeInt(len);
/* 289 */     for (String key : this.context.keySet()) {
/* 291 */       out.writeObject(key);
/* 292 */       Object value = this.context.get(key);
/* 293 */       if (value instanceof Serializable) {
/* 295 */         out.writeObject(value);
/*     */         continue;
/*     */       } 
/* 298 */       out.writeObject(nonSerializableReplacement(value));
/*     */     } 
/*     */   }
/*     */   
/*     */   private void deSerializeContext(ObjectInputStream in) throws IOException, ClassNotFoundException {
/* 314 */     int len = in.readInt();
/* 315 */     this.context = new HashMap<String, Object>();
/* 316 */     for (int i = 0; i < len; i++) {
/* 318 */       String key = (String)in.readObject();
/* 320 */       Object value = in.readObject();
/* 321 */       this.context.put(key, value);
/*     */     } 
/*     */   }
/*     */   
/*     */   private String nonSerializableReplacement(Object obj) {
/* 333 */     return "[Object could not be serialized: " + obj.getClass().getName() + "]";
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\exceptio\\util\ExceptionContext.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */