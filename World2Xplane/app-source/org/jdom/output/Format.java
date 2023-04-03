/*     */ package org.jdom.output;
/*     */ 
/*     */ import java.lang.reflect.Method;
/*     */ import org.jdom.Verifier;
/*     */ 
/*     */ public class Format implements Cloneable {
/*     */   private static final String CVS_ID = "@(#) $RCSfile: Format.java,v $ $Revision: 1.14 $ $Date: 2009/07/23 05:54:23 $ $Name:  $";
/*     */   
/*     */   private static final String STANDARD_INDENT = "  ";
/*     */   
/*     */   private static final String STANDARD_LINE_SEPARATOR = "\r\n";
/*     */   
/*     */   private static final String STANDARD_ENCODING = "UTF-8";
/*     */   
/*     */   public static Format getRawFormat() {
/*  90 */     return new Format();
/*     */   }
/*     */   
/*     */   public static Format getPrettyFormat() {
/* 104 */     Format f = new Format();
/* 105 */     f.setIndent("  ");
/* 106 */     f.setTextMode(TextMode.TRIM);
/* 107 */     return f;
/*     */   }
/*     */   
/*     */   public static Format getCompactFormat() {
/* 120 */     Format f = new Format();
/* 121 */     f.setTextMode(TextMode.NORMALIZE);
/* 122 */     return f;
/*     */   }
/*     */   
/* 136 */   String indent = null;
/*     */   
/* 139 */   String lineSeparator = "\r\n";
/*     */   
/* 142 */   String encoding = "UTF-8";
/*     */   
/*     */   boolean omitDeclaration = false;
/*     */   
/*     */   boolean omitEncoding = false;
/*     */   
/*     */   boolean expandEmptyElements = false;
/*     */   
/*     */   boolean ignoreTrAXEscapingPIs = false;
/*     */   
/* 161 */   TextMode mode = TextMode.PRESERVE;
/*     */   
/* 164 */   EscapeStrategy escapeStrategy = new DefaultEscapeStrategy(this, this.encoding);
/*     */   
/*     */   static Class class$java$lang$String;
/*     */   
/*     */   public Format setEscapeStrategy(EscapeStrategy strategy) {
/* 178 */     this.escapeStrategy = strategy;
/* 179 */     return this;
/*     */   }
/*     */   
/*     */   public EscapeStrategy getEscapeStrategy() {
/* 188 */     return this.escapeStrategy;
/*     */   }
/*     */   
/*     */   public Format setLineSeparator(String separator) {
/* 224 */     this.lineSeparator = separator;
/* 225 */     return this;
/*     */   }
/*     */   
/*     */   public String getLineSeparator() {
/* 234 */     return this.lineSeparator;
/*     */   }
/*     */   
/*     */   public Format setOmitEncoding(boolean omitEncoding) {
/* 249 */     this.omitEncoding = omitEncoding;
/* 250 */     return this;
/*     */   }
/*     */   
/*     */   public boolean getOmitEncoding() {
/* 259 */     return this.omitEncoding;
/*     */   }
/*     */   
/*     */   public Format setOmitDeclaration(boolean omitDeclaration) {
/* 273 */     this.omitDeclaration = omitDeclaration;
/* 274 */     return this;
/*     */   }
/*     */   
/*     */   public boolean getOmitDeclaration() {
/* 283 */     return this.omitDeclaration;
/*     */   }
/*     */   
/*     */   public Format setExpandEmptyElements(boolean expandEmptyElements) {
/* 296 */     this.expandEmptyElements = expandEmptyElements;
/* 297 */     return this;
/*     */   }
/*     */   
/*     */   public boolean getExpandEmptyElements() {
/* 306 */     return this.expandEmptyElements;
/*     */   }
/*     */   
/*     */   public void setIgnoreTrAXEscapingPIs(boolean ignoreTrAXEscapingPIs) {
/* 337 */     this.ignoreTrAXEscapingPIs = ignoreTrAXEscapingPIs;
/*     */   }
/*     */   
/*     */   public boolean getIgnoreTrAXEscapingPIs() {
/* 347 */     return this.ignoreTrAXEscapingPIs;
/*     */   }
/*     */   
/*     */   public Format setTextMode(TextMode mode) {
/* 357 */     this.mode = mode;
/* 358 */     return this;
/*     */   }
/*     */   
/*     */   public TextMode getTextMode() {
/* 367 */     return this.mode;
/*     */   }
/*     */   
/*     */   public Format setIndent(String indent) {
/* 381 */     this.indent = indent;
/* 382 */     return this;
/*     */   }
/*     */   
/*     */   public String getIndent() {
/* 391 */     return this.indent;
/*     */   }
/*     */   
/*     */   public Format setEncoding(String encoding) {
/* 403 */     this.encoding = encoding;
/* 404 */     this.escapeStrategy = new DefaultEscapeStrategy(this, encoding);
/* 405 */     return this;
/*     */   }
/*     */   
/*     */   public String getEncoding() {
/* 414 */     return this.encoding;
/*     */   }
/*     */   
/*     */   public Object clone() {
/* 418 */     Format format = null;
/*     */     try {
/* 421 */       format = (Format)super.clone();
/* 423 */     } catch (CloneNotSupportedException ce) {}
/* 426 */     return format;
/*     */   }
/*     */   
/*     */   class DefaultEscapeStrategy implements EscapeStrategy {
/*     */     private int bits;
/*     */     
/*     */     Object encoder;
/*     */     
/*     */     Method canEncode;
/*     */     
/*     */     private final Format this$0;
/*     */     
/*     */     public DefaultEscapeStrategy(Format this$0, String encoding) {
/* 440 */       this.this$0 = this$0;
/* 441 */       if ("UTF-8".equalsIgnoreCase(encoding) || "UTF-16".equalsIgnoreCase(encoding)) {
/* 443 */         this.bits = 16;
/* 445 */       } else if ("ISO-8859-1".equalsIgnoreCase(encoding) || "Latin1".equalsIgnoreCase(encoding)) {
/* 447 */         this.bits = 8;
/* 449 */       } else if ("US-ASCII".equalsIgnoreCase(encoding) || "ASCII".equalsIgnoreCase(encoding)) {
/* 451 */         this.bits = 7;
/*     */       } else {
/* 454 */         this.bits = 0;
/*     */         try {
/* 457 */           Class charsetClass = Class.forName("java.nio.charset.Charset");
/* 458 */           Class encoderClass = Class.forName("java.nio.charset.CharsetEncoder");
/* 459 */           Method forName = charsetClass.getMethod("forName", new Class[] { (Format.class$java$lang$String == null) ? (Format.class$java$lang$String = Format.class$("java.lang.String")) : Format.class$java$lang$String });
/* 460 */           Object charsetObj = forName.invoke(null, new Object[] { encoding });
/* 461 */           Method newEncoder = charsetClass.getMethod("newEncoder", null);
/* 462 */           this.encoder = newEncoder.invoke(charsetObj, null);
/* 463 */           this.canEncode = encoderClass.getMethod("canEncode", new Class[] { char.class });
/* 465 */         } catch (Exception ignored) {}
/*     */       } 
/*     */     }
/*     */     
/*     */     public boolean shouldEscape(char ch) {
/* 471 */       if (this.bits == 16) {
/* 472 */         if (Verifier.isHighSurrogate(ch))
/* 473 */           return true; 
/* 475 */         return false;
/*     */       } 
/* 477 */       if (this.bits == 8) {
/* 478 */         if (ch > 'ÿ')
/* 479 */           return true; 
/* 481 */         return false;
/*     */       } 
/* 483 */       if (this.bits == 7) {
/* 484 */         if (ch > '')
/* 485 */           return true; 
/* 487 */         return false;
/*     */       } 
/* 490 */       if (Verifier.isHighSurrogate(ch))
/* 491 */         return true; 
/* 493 */       if (this.canEncode != null && this.encoder != null)
/*     */         try {
/* 495 */           Boolean val = (Boolean)this.canEncode.invoke(this.encoder, new Object[] { new Character(ch) });
/* 496 */           return !val.booleanValue();
/* 498 */         } catch (Exception ignored) {} 
/* 504 */       return false;
/*     */     }
/*     */   }
/*     */   
/*     */   static Class class$(String x0) {
/*     */     try {
/*     */       return Class.forName(x0);
/*     */     } catch (ClassNotFoundException x1) {
/*     */       throw new NoClassDefFoundError(x1.getMessage());
/*     */     } 
/*     */   }
/*     */   
/*     */   public static class TextMode {
/* 584 */     public static final TextMode PRESERVE = new TextMode("PRESERVE");
/*     */     
/* 589 */     public static final TextMode TRIM = new TextMode("TRIM");
/*     */     
/* 596 */     public static final TextMode NORMALIZE = new TextMode("NORMALIZE");
/*     */     
/* 602 */     public static final TextMode TRIM_FULL_WHITE = new TextMode("TRIM_FULL_WHITE");
/*     */     
/*     */     private final String name;
/*     */     
/*     */     private TextMode(String name) {
/* 608 */       this.name = name;
/*     */     }
/*     */     
/*     */     public String toString() {
/* 612 */       return this.name;
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jdom\output\Format.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */