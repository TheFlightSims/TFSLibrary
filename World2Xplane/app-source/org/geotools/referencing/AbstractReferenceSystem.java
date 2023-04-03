/*     */ package org.geotools.referencing;
/*     */ 
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import org.geotools.util.Utilities;
/*     */ import org.opengis.metadata.extent.Extent;
/*     */ import org.opengis.referencing.IdentifiedObject;
/*     */ import org.opengis.referencing.ReferenceSystem;
/*     */ import org.opengis.util.InternationalString;
/*     */ 
/*     */ public class AbstractReferenceSystem extends AbstractIdentifiedObject implements ReferenceSystem {
/*     */   private static final long serialVersionUID = 3337659819553899435L;
/*     */   
/*  56 */   private static final String[] LOCALIZABLES = new String[] { "scope" };
/*     */   
/*     */   private final Extent domainOfValidity;
/*     */   
/*     */   private final InternationalString scope;
/*     */   
/*     */   public AbstractReferenceSystem(ReferenceSystem object) {
/*  81 */     super((IdentifiedObject)object);
/*  82 */     this.domainOfValidity = object.getDomainOfValidity();
/*  83 */     this.scope = object.getScope();
/*     */   }
/*     */   
/*     */   public AbstractReferenceSystem(Map<String, ?> properties) {
/* 113 */     this(properties, new HashMap<String, Object>());
/*     */   }
/*     */   
/*     */   private AbstractReferenceSystem(Map<String, ?> properties, Map<String, Object> subProperties) {
/* 121 */     super(properties, subProperties, LOCALIZABLES);
/* 122 */     this.domainOfValidity = (Extent)subProperties.get("domainOfValidity");
/* 123 */     this.scope = (InternationalString)subProperties.get("scope");
/*     */   }
/*     */   
/*     */   public Extent getDomainOfValidity() {
/* 133 */     return this.domainOfValidity;
/*     */   }
/*     */   
/*     */   public Extent getValidArea() {
/* 143 */     return this.domainOfValidity;
/*     */   }
/*     */   
/*     */   public InternationalString getScope() {
/* 152 */     return this.scope;
/*     */   }
/*     */   
/*     */   public boolean equals(AbstractIdentifiedObject object, boolean compareMetadata) {
/* 167 */     if (super.equals(object, compareMetadata)) {
/* 168 */       if (!compareMetadata)
/* 169 */         return true; 
/* 171 */       AbstractReferenceSystem that = (AbstractReferenceSystem)object;
/* 172 */       return (Utilities.equals(this.domainOfValidity, that.domainOfValidity) && Utilities.equals(this.scope, that.scope));
/*     */     } 
/* 175 */     return false;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\referencing\AbstractReferenceSystem.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */