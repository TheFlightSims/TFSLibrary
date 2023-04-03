/*     */ package org.geotools.resources;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.InputStreamReader;
/*     */ import java.io.OutputStream;
/*     */ import java.io.OutputStreamWriter;
/*     */ import java.io.PrintStream;
/*     */ import java.io.PrintWriter;
/*     */ import java.io.Reader;
/*     */ import java.io.Writer;
/*     */ import java.util.Locale;
/*     */ import java.util.regex.Pattern;
/*     */ import org.geotools.resources.i18n.Errors;
/*     */ import org.geotools.resources.i18n.Vocabulary;
/*     */ 
/*     */ public class Arguments {
/*     */   @Deprecated
/*     */   public static final int ILLEGAL_ARGUMENT_EXIT_CODE = 1;
/*     */   
/*     */   private final String[] arguments;
/*     */   
/*     */   public final PrintWriter out;
/*     */   
/*     */   public final PrintWriter err;
/*     */   
/*     */   public final Locale locale;
/*     */   
/*     */   private final String encoding;
/*     */   
/*     */   public Arguments(String[] args) {
/* 105 */     this.arguments = (String[])args.clone();
/* 106 */     this.locale = getLocale(getOptionalString("-locale"));
/* 107 */     this.encoding = getOptionalString("-encoding");
/* 108 */     PrintWriter out = null;
/* 109 */     Exception error = null;
/* 110 */     if (this.encoding != null)
/*     */       try {
/* 111 */         out = new PrintWriter(new OutputStreamWriter(System.out, this.encoding), true);
/* 112 */       } catch (IOException exception) {
/* 113 */         error = exception;
/*     */       }  
/* 115 */     if (out == null)
/* 116 */       out = getPrintWriter(System.out); 
/* 118 */     this.out = out;
/* 119 */     this.err = getPrintWriter(System.err);
/* 120 */     if (error != null)
/* 121 */       illegalArgument(error); 
/*     */   }
/*     */   
/*     */   private Locale getLocale(String locale) throws IllegalArgumentException {
/* 133 */     if (locale != null) {
/* 134 */       String[] s = Pattern.compile("_").split(locale);
/* 135 */       switch (s.length) {
/*     */         case 1:
/* 136 */           return new Locale(s[0]);
/*     */         case 2:
/* 137 */           return new Locale(s[0], s[1]);
/*     */         case 3:
/* 138 */           return new Locale(s[0], s[1], s[2]);
/*     */       } 
/* 139 */       illegalArgument(new IllegalArgumentException(Errors.format(11, locale)));
/*     */     } 
/* 143 */     return Locale.getDefault();
/*     */   }
/*     */   
/*     */   public String getOptionalString(String name) {
/* 160 */     for (int i = 0; i < this.arguments.length; i++) {
/* 161 */       String arg = this.arguments[i];
/* 162 */       if (arg != null) {
/* 163 */         arg = arg.trim();
/* 164 */         String value = "";
/* 165 */         int split = arg.indexOf('=');
/* 166 */         if (split >= 0) {
/* 167 */           value = arg.substring(split + 1).trim();
/* 168 */           arg = arg.substring(0, split).trim();
/*     */         } 
/* 170 */         if (arg.equalsIgnoreCase(name)) {
/* 171 */           this.arguments[i] = null;
/* 172 */           if (value.length() != 0)
/* 173 */             return value; 
/* 175 */           while (++i < this.arguments.length) {
/* 176 */             value = this.arguments[i];
/* 177 */             this.arguments[i] = null;
/* 178 */             if (value == null)
/*     */               break; 
/* 181 */             value = value.trim();
/* 182 */             if (split >= 0)
/* 183 */               return value; 
/* 185 */             if (!value.equals("="))
/* 186 */               return value.startsWith("=") ? value.substring(1).trim() : value; 
/* 188 */             split = 0;
/*     */           } 
/* 190 */           illegalArgument(new IllegalArgumentException(Errors.getResources(this.locale).getString(100, arg)));
/* 192 */           return null;
/*     */         } 
/*     */       } 
/*     */     } 
/* 196 */     return null;
/*     */   }
/*     */   
/*     */   public String getRequiredString(String name) {
/* 209 */     String value = getOptionalString(name);
/* 210 */     if (value == null)
/* 211 */       illegalArgument(new IllegalArgumentException(Errors.getResources(this.locale).getString(99, name))); 
/* 214 */     return value;
/*     */   }
/*     */   
/*     */   public Integer getOptionalInteger(String name) {
/* 228 */     String value = getOptionalString(name);
/* 229 */     if (value != null)
/*     */       try {
/* 230 */         return Integer.valueOf(value);
/* 231 */       } catch (NumberFormatException exception) {
/* 232 */         illegalArgument(exception);
/*     */       }  
/* 234 */     return null;
/*     */   }
/*     */   
/*     */   public int getRequiredInteger(String name) {
/* 247 */     String value = getRequiredString(name);
/* 248 */     if (value != null)
/*     */       try {
/* 249 */         return Integer.parseInt(value);
/* 250 */       } catch (NumberFormatException exception) {
/* 251 */         illegalArgument(exception);
/*     */       }  
/* 253 */     return 0;
/*     */   }
/*     */   
/*     */   public Double getOptionalDouble(String name) {
/* 267 */     String value = getOptionalString(name);
/* 268 */     if (value != null)
/*     */       try {
/* 269 */         return Double.valueOf(value);
/* 270 */       } catch (NumberFormatException exception) {
/* 271 */         illegalArgument(exception);
/*     */       }  
/* 273 */     return null;
/*     */   }
/*     */   
/*     */   public double getRequiredDouble(String name) {
/* 286 */     String value = getRequiredString(name);
/* 287 */     if (value != null)
/*     */       try {
/* 288 */         return Double.parseDouble(value);
/* 289 */       } catch (NumberFormatException exception) {
/* 290 */         illegalArgument(exception);
/*     */       }  
/* 292 */     return Double.NaN;
/*     */   }
/*     */   
/*     */   public Boolean getOptionalBoolean(String name) {
/* 304 */     String value = getOptionalString(name);
/* 305 */     if (value != null) {
/* 306 */       if (value.equalsIgnoreCase("true"))
/* 306 */         return Boolean.TRUE; 
/* 307 */       if (value.equalsIgnoreCase("false"))
/* 307 */         return Boolean.FALSE; 
/* 308 */       illegalArgument(new IllegalArgumentException(value));
/*     */     } 
/* 310 */     return null;
/*     */   }
/*     */   
/*     */   public boolean getRequiredBoolean(String name) {
/* 321 */     String value = getRequiredString(name);
/* 322 */     if (value != null) {
/* 323 */       if (value.equalsIgnoreCase("true"))
/* 323 */         return true; 
/* 324 */       if (value.equalsIgnoreCase("false"))
/* 324 */         return false; 
/* 325 */       illegalArgument(new IllegalArgumentException(value));
/*     */     } 
/* 327 */     return false;
/*     */   }
/*     */   
/*     */   public boolean getFlag(String name) {
/* 341 */     for (int i = 0; i < this.arguments.length; i++) {
/* 342 */       String arg = this.arguments[i];
/* 343 */       if (arg != null) {
/* 344 */         arg = arg.trim();
/* 345 */         if (arg.equalsIgnoreCase(name)) {
/* 346 */           this.arguments[i] = null;
/* 347 */           return true;
/*     */         } 
/*     */       } 
/*     */     } 
/* 351 */     return false;
/*     */   }
/*     */   
/*     */   public static Reader getReader(InputStream in) {
/* 361 */     if (in == System.in) {
/* 362 */       Reader candidate = Java6.consoleReader();
/* 363 */       if (candidate != null)
/* 364 */         return candidate; 
/*     */     } 
/* 367 */     return new InputStreamReader(in);
/*     */   }
/*     */   
/*     */   public static Writer getWriter(OutputStream out) {
/* 377 */     if (out == System.out || out == System.err) {
/* 378 */       PrintWriter candidate = Java6.consoleWriter();
/* 379 */       if (candidate != null)
/* 380 */         return candidate; 
/*     */     } 
/* 383 */     return new OutputStreamWriter(out);
/*     */   }
/*     */   
/*     */   public static PrintWriter getPrintWriter(PrintStream out) {
/* 393 */     if (out == System.out || out == System.err) {
/* 394 */       PrintWriter candidate = Java6.consoleWriter();
/* 395 */       if (candidate != null)
/* 396 */         return candidate; 
/*     */     } 
/* 399 */     return new PrintWriter(out, true);
/*     */   }
/*     */   
/*     */   public String[] getRemainingArguments(int max) {
/* 410 */     int count = 0;
/* 411 */     String[] left = new String[Math.min(max, this.arguments.length)];
/* 412 */     for (int i = 0; i < this.arguments.length; i++) {
/* 413 */       String arg = this.arguments[i];
/* 414 */       if (arg != null) {
/* 415 */         if (count >= max)
/* 416 */           illegalArgument(new IllegalArgumentException(Errors.getResources(this.locale).getString(176, this.arguments[i]))); 
/* 419 */         left[count++] = arg;
/*     */       } 
/*     */     } 
/* 422 */     return XArray.<String>resize(left, count);
/*     */   }
/*     */   
/*     */   public String[] getRemainingArguments(int max, char forbiddenPrefix) {
/* 439 */     String[] arguments = getRemainingArguments(max);
/* 440 */     for (int i = 0; i < arguments.length; i++) {
/* 441 */       String argument = arguments[i];
/* 442 */       if (argument != null) {
/* 443 */         argument = argument.trim();
/* 444 */         if (argument.length() != 0 && 
/* 445 */           argument.charAt(0) == forbiddenPrefix)
/* 446 */           illegalArgument(new IllegalArgumentException(Errors.getResources(this.locale).getString(184, argument))); 
/*     */       } 
/*     */     } 
/* 452 */     return arguments;
/*     */   }
/*     */   
/*     */   public void printSummary(Exception exception) {
/* 465 */     String type = Classes.getShortClassName(exception);
/* 466 */     String message = exception.getLocalizedMessage();
/* 467 */     if (message == null) {
/* 468 */       message = Vocabulary.format(152, type);
/*     */     } else {
/* 470 */       this.err.print(type);
/* 471 */       this.err.print(": ");
/*     */     } 
/* 473 */     this.err.println(message);
/* 474 */     this.err.flush();
/*     */   }
/*     */   
/*     */   protected void illegalArgument(Exception exception) {
/* 490 */     printSummary(exception);
/* 491 */     System.exit(1);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\resources\Arguments.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */