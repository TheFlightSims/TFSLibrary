/*     */ package org.opengis.style;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import org.opengis.annotation.XmlElement;
/*     */ import org.opengis.util.CodeList;
/*     */ 
/*     */ @XmlElement("SemanticTypeIdentifier")
/*     */ public final class SemanticType extends CodeList<SemanticType> {
/*     */   private static final long serialVersionUID = -7328502367911363577L;
/*     */   
/*  53 */   private static final List<SemanticType> VALUES = new ArrayList<SemanticType>(6);
/*     */   
/*     */   @XmlElement("generic:point")
/*  59 */   public static final SemanticType POINT = new SemanticType("POINT");
/*     */   
/*     */   @XmlElement("generic:line")
/*  65 */   public static final SemanticType LINE = new SemanticType("LINE");
/*     */   
/*     */   @XmlElement("generic:polygon")
/*  71 */   public static final SemanticType POLYGON = new SemanticType("POLYGON");
/*     */   
/*     */   @XmlElement("generic:text")
/*  77 */   public static final SemanticType TEXT = new SemanticType("TEXT");
/*     */   
/*     */   @XmlElement("generic:raster")
/*  83 */   public static final SemanticType RASTER = new SemanticType("RASTER");
/*     */   
/*     */   @XmlElement("generic:any")
/*  89 */   public static final SemanticType ANY = new SemanticType("ANY");
/*     */   
/*     */   private SemanticType(String name) {
/*  98 */     super(name, VALUES);
/*     */   }
/*     */   
/*     */   public static SemanticType[] values() {
/* 107 */     synchronized (VALUES) {
/* 108 */       return VALUES.<SemanticType>toArray(new SemanticType[VALUES.size()]);
/*     */     } 
/*     */   }
/*     */   
/*     */   public SemanticType[] family() {
/* 116 */     return values();
/*     */   }
/*     */   
/*     */   public static SemanticType valueOf(String code) {
/* 127 */     return (SemanticType)valueOf(SemanticType.class, code);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\style\SemanticType.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */