/*     */ package org.opengis.metadata.citation;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import org.opengis.annotation.Obligation;
/*     */ import org.opengis.annotation.Specification;
/*     */ import org.opengis.annotation.UML;
/*     */ import org.opengis.util.CodeList;
/*     */ 
/*     */ @UML(identifier = "CI_OnLineFunctionCode", specification = Specification.ISO_19115)
/*     */ public final class OnLineFunction extends CodeList<OnLineFunction> {
/*     */   private static final long serialVersionUID = 2333803519583053407L;
/*     */   
/*  43 */   private static final List<OnLineFunction> VALUES = new ArrayList<OnLineFunction>(5);
/*     */   
/*     */   @UML(identifier = "download", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19115)
/*  49 */   public static final OnLineFunction DOWNLOAD = new OnLineFunction("DOWNLOAD");
/*     */   
/*     */   @UML(identifier = "information", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19115)
/*  55 */   public static final OnLineFunction INFORMATION = new OnLineFunction("INFORMATION");
/*     */   
/*     */   @UML(identifier = "offlineAccess", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19115)
/*  61 */   public static final OnLineFunction OFFLINE_ACCESS = new OnLineFunction("OFFLINE_ACCESS");
/*     */   
/*     */   @UML(identifier = "order", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19115)
/*  67 */   public static final OnLineFunction ORDER = new OnLineFunction("ORDER");
/*     */   
/*     */   @UML(identifier = "search", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19115)
/*  73 */   public static final OnLineFunction SEARCH = new OnLineFunction("SEARCH");
/*     */   
/*     */   private OnLineFunction(String name) {
/*  82 */     super(name, VALUES);
/*     */   }
/*     */   
/*     */   public static OnLineFunction[] values() {
/*  91 */     synchronized (VALUES) {
/*  92 */       return VALUES.<OnLineFunction>toArray(new OnLineFunction[VALUES.size()]);
/*     */     } 
/*     */   }
/*     */   
/*     */   public OnLineFunction[] family() {
/* 100 */     return values();
/*     */   }
/*     */   
/*     */   public static OnLineFunction valueOf(String code) {
/* 111 */     return (OnLineFunction)valueOf(OnLineFunction.class, code);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\metadata\citation\OnLineFunction.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */