/*     */ package org.opengis.metadata.identification;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import org.opengis.annotation.Obligation;
/*     */ import org.opengis.annotation.Specification;
/*     */ import org.opengis.annotation.UML;
/*     */ import org.opengis.util.CodeList;
/*     */ 
/*     */ @UML(identifier = "MD_KeywordTypeCode", specification = Specification.ISO_19115)
/*     */ public final class KeywordType extends CodeList<KeywordType> {
/*     */   private static final long serialVersionUID = -4726629268565235927L;
/*     */   
/*  42 */   private static final List<KeywordType> VALUES = new ArrayList<KeywordType>(5);
/*     */   
/*     */   @UML(identifier = "discipline", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19115)
/*  48 */   public static final KeywordType DISCIPLINE = new KeywordType("DISCIPLINE");
/*     */   
/*     */   @UML(identifier = "place", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19115)
/*  54 */   public static final KeywordType PLACE = new KeywordType("PLACE");
/*     */   
/*     */   @UML(identifier = "stratum", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19115)
/*  60 */   public static final KeywordType STRATUM = new KeywordType("STRATUM");
/*     */   
/*     */   @UML(identifier = "temporal", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19115)
/*  66 */   public static final KeywordType TEMPORAL = new KeywordType("TEMPORAL");
/*     */   
/*     */   @UML(identifier = "theme", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19115)
/*  72 */   public static final KeywordType THEME = new KeywordType("THEME");
/*     */   
/*     */   private KeywordType(String name) {
/*  81 */     super(name, VALUES);
/*     */   }
/*     */   
/*     */   public static KeywordType[] values() {
/*  90 */     synchronized (VALUES) {
/*  91 */       return VALUES.<KeywordType>toArray(new KeywordType[VALUES.size()]);
/*     */     } 
/*     */   }
/*     */   
/*     */   public KeywordType[] family() {
/*  99 */     return values();
/*     */   }
/*     */   
/*     */   public static KeywordType valueOf(String code) {
/* 110 */     return (KeywordType)valueOf(KeywordType.class, code);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\metadata\identification\KeywordType.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */