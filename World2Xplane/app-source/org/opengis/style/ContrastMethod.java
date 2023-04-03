/*     */ package org.opengis.style;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import org.opengis.annotation.XmlElement;
/*     */ import org.opengis.util.CodeList;
/*     */ 
/*     */ @XmlElement("ContrastEnchancement:type")
/*     */ public final class ContrastMethod extends CodeList<ContrastMethod> {
/*     */   private static final long serialVersionUID = -7328502367911363577L;
/*     */   
/*  50 */   private static final List<ContrastMethod> VALUES = new ArrayList<ContrastMethod>(3);
/*     */   
/*     */   @XmlElement("Normalize")
/*  59 */   public static final ContrastMethod NORMALIZE = new ContrastMethod("NORMALIZE");
/*     */   
/*     */   @XmlElement("Histogram")
/*  68 */   public static final ContrastMethod HISTOGRAM = new ContrastMethod("HISTOGRAM");
/*     */   
/*  74 */   public static final ContrastMethod NONE = new ContrastMethod("NONE");
/*     */   
/*     */   private ContrastMethod(String name) {
/*  83 */     super(name, VALUES);
/*     */   }
/*     */   
/*     */   public static ContrastMethod[] values() {
/*  92 */     synchronized (VALUES) {
/*  93 */       return VALUES.<ContrastMethod>toArray(new ContrastMethod[VALUES.size()]);
/*     */     } 
/*     */   }
/*     */   
/*     */   public ContrastMethod[] family() {
/* 101 */     return values();
/*     */   }
/*     */   
/*     */   public static ContrastMethod valueOf(String code) {
/* 112 */     return (ContrastMethod)valueOf(ContrastMethod.class, code);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\style\ContrastMethod.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */