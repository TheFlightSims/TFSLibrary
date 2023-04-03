/*     */ package org.geotools.temporal.reference;
/*     */ 
/*     */ import java.util.Collection;
/*     */ import java.util.Date;
/*     */ import org.geotools.util.Utilities;
/*     */ import org.opengis.temporal.OrdinalEra;
/*     */ import org.opengis.util.InternationalString;
/*     */ 
/*     */ public class DefaultOrdinalEra implements OrdinalEra {
/*     */   private InternationalString name;
/*     */   
/*     */   private Date beginning;
/*     */   
/*     */   private Date end;
/*     */   
/*     */   private Collection<OrdinalEra> composition;
/*     */   
/*     */   private DefaultOrdinalEra group;
/*     */   
/*     */   public DefaultOrdinalEra(InternationalString name, Date beginning, Date end) {
/*  51 */     if (!beginning.before(end))
/*  52 */       throw new IllegalArgumentException("The beginning date of the OrdinalEra must be less than (i.e. earlier than) the end date of this OrdinalEra."); 
/*  53 */     this.name = name;
/*  54 */     this.beginning = beginning;
/*  55 */     this.end = end;
/*     */   }
/*     */   
/*     */   public DefaultOrdinalEra(InternationalString name, Date beginning, Date end, Collection<OrdinalEra> composition) {
/*  59 */     this.name = name;
/*  60 */     this.beginning = beginning;
/*  61 */     this.end = end;
/*  63 */     for (OrdinalEra ordinalEra : composition)
/*  64 */       ((DefaultOrdinalEra)ordinalEra).setGroup(this); 
/*     */   }
/*     */   
/*     */   public InternationalString getName() {
/*  69 */     return this.name;
/*     */   }
/*     */   
/*     */   public Date getBeginning() {
/*  73 */     return this.beginning;
/*     */   }
/*     */   
/*     */   public Date getEnd() {
/*  77 */     return this.end;
/*     */   }
/*     */   
/*     */   public Collection<OrdinalEra> getComposition() {
/*  81 */     return this.composition;
/*     */   }
/*     */   
/*     */   public void setName(InternationalString name) {
/*  85 */     this.name = name;
/*     */   }
/*     */   
/*     */   public void setBeginning(Date beginning) {
/*  89 */     this.beginning = beginning;
/*     */   }
/*     */   
/*     */   public void setEnd(Date end) {
/*  93 */     this.end = end;
/*     */   }
/*     */   
/*     */   public DefaultOrdinalEra getGroup() {
/*  97 */     return this.group;
/*     */   }
/*     */   
/*     */   public void setGroup(DefaultOrdinalEra group) {
/* 101 */     this.group = group;
/*     */   }
/*     */   
/*     */   public boolean equals(Object object) {
/* 106 */     if (object instanceof DefaultOrdinalEra) {
/* 107 */       DefaultOrdinalEra that = (DefaultOrdinalEra)object;
/* 109 */       return (Utilities.equals(this.beginning, that.beginning) && Utilities.equals(this.end, that.end) && Utilities.equals(this.composition, that.composition) && Utilities.equals(this.group, that.group) && Utilities.equals(this.name, that.name));
/*     */     } 
/* 115 */     return false;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 120 */     int hash = 5;
/* 121 */     hash = 37 * hash + ((this.beginning != null) ? this.beginning.hashCode() : 0);
/* 122 */     hash = 37 * hash + ((this.end != null) ? this.end.hashCode() : 0);
/* 123 */     hash = 37 * hash + ((this.composition != null) ? this.composition.hashCode() : 0);
/* 124 */     hash = 37 * hash + ((this.group != null) ? this.group.hashCode() : 0);
/* 125 */     hash = 37 * hash + ((this.name != null) ? this.name.hashCode() : 0);
/* 126 */     return hash;
/*     */   }
/*     */   
/*     */   public String toString() {
/* 131 */     StringBuilder s = (new StringBuilder("OrdinalEra:")).append('\n');
/* 132 */     if (this.name != null)
/* 133 */       s.append("name:").append((CharSequence)this.name).append('\n'); 
/* 135 */     if (this.beginning != null)
/* 136 */       s.append("beginning:").append(this.beginning).append('\n'); 
/* 138 */     if (this.end != null)
/* 139 */       s.append("end:").append(this.end).append('\n'); 
/* 141 */     if (this.composition != null)
/* 142 */       s.append("composition:").append(this.composition).append('\n'); 
/* 144 */     if (this.group != null)
/* 145 */       s.append("group:").append(this.group).append('\n'); 
/* 147 */     return s.toString();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\temporal\reference\DefaultOrdinalEra.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */