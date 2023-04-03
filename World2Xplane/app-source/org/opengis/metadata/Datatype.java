/*     */ package org.opengis.metadata;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import org.opengis.annotation.Obligation;
/*     */ import org.opengis.annotation.Specification;
/*     */ import org.opengis.annotation.UML;
/*     */ import org.opengis.util.CodeList;
/*     */ 
/*     */ @UML(identifier = "MD_DatatypeCode", specification = Specification.ISO_19115)
/*     */ public final class Datatype extends CodeList<Datatype> {
/*     */   private static final long serialVersionUID = -307310382687629669L;
/*     */   
/*  42 */   private static final List<Datatype> VALUES = new ArrayList<Datatype>(15);
/*     */   
/*     */   @UML(identifier = "class", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19115)
/*  49 */   public static final Datatype CLASS = new Datatype("CLASS");
/*     */   
/*     */   @UML(identifier = "codelist", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19115)
/*  55 */   public static final Datatype CODE_LIST = new Datatype("CODE_LIST");
/*     */   
/*     */   @UML(identifier = "enumeration", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19115)
/*  61 */   public static final Datatype ENUMERATION = new Datatype("ENUMERATION");
/*     */   
/*     */   @UML(identifier = "codelistElement", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19115)
/*  67 */   public static final Datatype CODE_LIST_ELEMENT = new Datatype("CODE_LIST_ELEMENT");
/*     */   
/*     */   @UML(identifier = "abstractClass", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19115)
/*  73 */   public static final Datatype ABSTRACT_CLASS = new Datatype("ABSTRACT_CLASS");
/*     */   
/*     */   @UML(identifier = "aggregateClass", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19115)
/*  79 */   public static final Datatype AGGREGATE_CLASS = new Datatype("AGGREGATE_CLASS");
/*     */   
/*     */   @UML(identifier = "specifiedClass", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19115)
/*  85 */   public static final Datatype SPECIFIED_CLASS = new Datatype("SPECIFIED_CLASS");
/*     */   
/*     */   @UML(identifier = "datatypeClass", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19115)
/*  92 */   public static final Datatype DATATYPE_CLASS = new Datatype("DATATYPE_CLASS");
/*     */   
/*     */   @UML(identifier = "interfaceClass", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19115)
/*  98 */   public static final Datatype INTERFACE_CLASS = new Datatype("INTERFACE_CLASS");
/*     */   
/*     */   @UML(identifier = "unionClass", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19115)
/* 104 */   public static final Datatype UNION_CLASS = new Datatype("UNION_CLASS");
/*     */   
/*     */   @UML(identifier = "metaclass", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19115)
/* 110 */   public static final Datatype META_CLASS = new Datatype("META_CLASS");
/*     */   
/*     */   @UML(identifier = "typeClass", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19115)
/* 117 */   public static final Datatype TYPE_CLASS = new Datatype("TYPE_CLASS");
/*     */   
/*     */   @UML(identifier = "characterString", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19115)
/* 123 */   public static final Datatype CHARACTER_STRING = new Datatype("CHARACTER_STRING");
/*     */   
/*     */   @UML(identifier = "integer", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19115)
/* 129 */   public static final Datatype INTEGER = new Datatype("INTEGER");
/*     */   
/*     */   @UML(identifier = "association", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19115)
/* 135 */   public static final Datatype ASSOCIATION = new Datatype("ASSOCIATION");
/*     */   
/*     */   private Datatype(String name) {
/* 144 */     super(name, VALUES);
/*     */   }
/*     */   
/*     */   public static Datatype[] values() {
/* 153 */     synchronized (VALUES) {
/* 154 */       return VALUES.<Datatype>toArray(new Datatype[VALUES.size()]);
/*     */     } 
/*     */   }
/*     */   
/*     */   public Datatype[] family() {
/* 162 */     return values();
/*     */   }
/*     */   
/*     */   public static Datatype valueOf(String code) {
/* 173 */     return (Datatype)valueOf(Datatype.class, code);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\metadata\Datatype.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */