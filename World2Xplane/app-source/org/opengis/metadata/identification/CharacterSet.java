/*     */ package org.opengis.metadata.identification;
/*     */ 
/*     */ import java.nio.charset.Charset;
/*     */ import java.nio.charset.UnsupportedCharsetException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import org.opengis.annotation.Obligation;
/*     */ import org.opengis.annotation.Specification;
/*     */ import org.opengis.annotation.UML;
/*     */ import org.opengis.util.CodeList;
/*     */ 
/*     */ @UML(identifier = "MD_CharacterSetCode", specification = Specification.ISO_19115)
/*     */ public final class CharacterSet extends CodeList<CharacterSet> {
/*     */   private static final long serialVersionUID = -4726629268456735927L;
/*     */   
/*  45 */   private static final List<CharacterSet> VALUES = new ArrayList<CharacterSet>(29);
/*     */   
/*     */   @UML(identifier = "ucs2", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19115)
/*  51 */   public static final CharacterSet UCS_2 = new CharacterSet("UCS_2", "UCS-2");
/*     */   
/*     */   @UML(identifier = "ucs4", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19115)
/*  57 */   public static final CharacterSet UCS_4 = new CharacterSet("UCS_4", "UCS-4");
/*     */   
/*     */   @UML(identifier = "utf7", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19115)
/*  63 */   public static final CharacterSet UTF_7 = new CharacterSet("UTF_7", "UTF-7");
/*     */   
/*     */   @UML(identifier = "utf8", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19115)
/*  69 */   public static final CharacterSet UTF_8 = new CharacterSet("UTF_8", "UTF-8");
/*     */   
/*     */   @UML(identifier = "utf16", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19115)
/*  75 */   public static final CharacterSet UTF_16 = new CharacterSet("UTF_16", "UTF-16");
/*     */   
/*     */   @UML(identifier = "8859part1", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19115)
/*  82 */   public static final CharacterSet ISO_8859_1 = new CharacterSet("ISO_8859_1", "ISO-8859-1");
/*     */   
/*     */   @UML(identifier = "8859part2", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19115)
/*  89 */   public static final CharacterSet ISO_8859_2 = new CharacterSet("ISO_8859_2", "ISO-8859-2");
/*     */   
/*     */   @UML(identifier = "8859part3", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19115)
/*  96 */   public static final CharacterSet ISO_8859_3 = new CharacterSet("ISO_8859_3", "ISO-8859-3");
/*     */   
/*     */   @UML(identifier = "8859part4", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19115)
/* 103 */   public static final CharacterSet ISO_8859_4 = new CharacterSet("ISO_8859_4", "ISO-8859-4");
/*     */   
/*     */   @UML(identifier = "8859part5", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19115)
/* 110 */   public static final CharacterSet ISO_8859_5 = new CharacterSet("ISO_8859_5", "ISO-8859-5");
/*     */   
/*     */   @UML(identifier = "8859part6", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19115)
/* 117 */   public static final CharacterSet ISO_8859_6 = new CharacterSet("ISO_8859_6", "ISO-8859-6");
/*     */   
/*     */   @UML(identifier = "8859part7", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19115)
/* 124 */   public static final CharacterSet ISO_8859_7 = new CharacterSet("ISO_8859_7", "ISO-8859-7");
/*     */   
/*     */   @UML(identifier = "8859part8", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19115)
/* 131 */   public static final CharacterSet ISO_8859_8 = new CharacterSet("ISO_8859_8", "ISO-8859-8");
/*     */   
/*     */   @UML(identifier = "8859part9", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19115)
/* 138 */   public static final CharacterSet ISO_8859_9 = new CharacterSet("ISO_8859_9", "ISO-8859-9");
/*     */   
/*     */   @UML(identifier = "8859part10", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19115)
/* 145 */   public static final CharacterSet ISO_8859_10 = new CharacterSet("ISO_8859_10", "ISO-8859-10");
/*     */   
/*     */   @UML(identifier = "8859part11", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19115)
/* 152 */   public static final CharacterSet ISO_8859_11 = new CharacterSet("ISO_8859_11", "ISO-8859-11");
/*     */   
/*     */   @UML(identifier = "8859part12", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19115)
/* 158 */   public static final CharacterSet ISO_8859_12 = new CharacterSet("ISO_8859_12", "ISO-8859-12");
/*     */   
/*     */   @UML(identifier = "8859part13", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19115)
/* 165 */   public static final CharacterSet ISO_8859_13 = new CharacterSet("ISO_8859_13", "ISO-8859-13");
/*     */   
/*     */   @UML(identifier = "8859part14", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19115)
/* 172 */   public static final CharacterSet ISO_8859_14 = new CharacterSet("ISO_8859_14", "ISO-8859-14");
/*     */   
/*     */   @UML(identifier = "8859part15", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19115)
/* 179 */   public static final CharacterSet ISO_8859_15 = new CharacterSet("ISO_8859_15", "ISO-8859-15");
/*     */   
/*     */   @UML(identifier = "8859part16", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19115)
/* 186 */   public static final CharacterSet ISO_8859_16 = new CharacterSet("ISO_8859_16", "ISO-8859-16");
/*     */   
/*     */   @UML(identifier = "jis", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19115)
/* 192 */   public static final CharacterSet JIS = new CharacterSet("JIS", "JIS_X0201");
/*     */   
/*     */   @UML(identifier = "shiftJIS", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19115)
/* 198 */   public static final CharacterSet SHIFT_JIS = new CharacterSet("SHIFT_JIS", "Shift_JIS");
/*     */   
/*     */   @UML(identifier = "eucJP", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19115)
/* 204 */   public static final CharacterSet EUC_JP = new CharacterSet("EUC_JP", "EUC-JP");
/*     */   
/*     */   @UML(identifier = "usAscii", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19115)
/* 210 */   public static final CharacterSet US_ASCII = new CharacterSet("US_ASCII", "US-ASCII");
/*     */   
/*     */   @UML(identifier = "ebcdic", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19115)
/* 216 */   public static final CharacterSet EBCDIC = new CharacterSet("EBCDIC", null);
/*     */   
/*     */   @UML(identifier = "eucKR", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19115)
/* 222 */   public static final CharacterSet EUC_KR = new CharacterSet("EUC_KR", "EUC-KR");
/*     */   
/*     */   @UML(identifier = "big5", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19115)
/* 228 */   public static final CharacterSet BIG_5 = new CharacterSet("BIG_5", "Big5");
/*     */   
/*     */   @UML(identifier = "GB2312", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19115)
/* 234 */   public static final CharacterSet GB2312 = new CharacterSet("GB2312", "GB2312");
/*     */   
/*     */   private final String charset;
/*     */   
/*     */   private CharacterSet(String name, String charset) {
/* 249 */     super(name, VALUES);
/* 250 */     this.charset = (charset != null) ? charset : name;
/*     */   }
/*     */   
/*     */   private CharacterSet(String name) {
/* 258 */     this(name, name);
/*     */   }
/*     */   
/*     */   public Charset toCharset() throws UnsupportedCharsetException {
/* 270 */     return Charset.forName(this.charset);
/*     */   }
/*     */   
/*     */   public static CharacterSet[] values() {
/* 279 */     synchronized (VALUES) {
/* 280 */       return VALUES.<CharacterSet>toArray(new CharacterSet[VALUES.size()]);
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean matches(String name) {
/* 289 */     if (super.matches(name))
/* 290 */       return true; 
/* 292 */     return (name != null && name.equalsIgnoreCase(this.charset));
/*     */   }
/*     */   
/*     */   public CharacterSet[] family() {
/* 299 */     return values();
/*     */   }
/*     */   
/*     */   public static CharacterSet valueOf(String code) {
/* 310 */     return (CharacterSet)valueOf(CharacterSet.class, code);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\metadata\identification\CharacterSet.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */