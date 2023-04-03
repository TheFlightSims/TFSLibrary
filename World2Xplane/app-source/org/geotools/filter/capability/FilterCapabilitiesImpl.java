/*     */ package org.geotools.filter.capability;
/*     */ 
/*     */ import org.opengis.filter.capability.FilterCapabilities;
/*     */ import org.opengis.filter.capability.IdCapabilities;
/*     */ import org.opengis.filter.capability.ScalarCapabilities;
/*     */ import org.opengis.filter.capability.SpatialCapabilities;
/*     */ import org.opengis.filter.capability.TemporalCapabilities;
/*     */ 
/*     */ public class FilterCapabilitiesImpl implements FilterCapabilities {
/*     */   String version;
/*     */   
/*     */   IdCapabilitiesImpl id;
/*     */   
/*     */   ScalarCapabilitiesImpl scalar;
/*     */   
/*     */   SpatialCapabiltiesImpl spatial;
/*     */   
/*     */   TemporalCapabilitiesImpl temporal;
/*     */   
/*     */   public FilterCapabilitiesImpl() {
/*  44 */     this("1.0.0");
/*     */   }
/*     */   
/*     */   public FilterCapabilitiesImpl(String version) {
/*  47 */     this.version = version;
/*     */   }
/*     */   
/*     */   public FilterCapabilitiesImpl(String version, ScalarCapabilities scalar, SpatialCapabilities spatial, IdCapabilities id) {
/*  51 */     this.version = version;
/*  52 */     this.id = toIdCapabilitiesImpl(id);
/*  53 */     this.scalar = toScalarCapabilitiesImpl(scalar);
/*  54 */     this.spatial = toSpatialCapabiltiesImpl(spatial);
/*  55 */     this.temporal = toTemporalCapabilitiesImpl(null);
/*     */   }
/*     */   
/*     */   public FilterCapabilitiesImpl(String version, ScalarCapabilities scalar, SpatialCapabilities spatial, IdCapabilities id, TemporalCapabilities temporal) {
/*  59 */     this.version = version;
/*  60 */     this.id = toIdCapabilitiesImpl(id);
/*  61 */     this.scalar = toScalarCapabilitiesImpl(scalar);
/*  62 */     this.spatial = toSpatialCapabiltiesImpl(spatial);
/*  63 */     this.temporal = toTemporalCapabilitiesImpl(temporal);
/*     */   }
/*     */   
/*     */   public FilterCapabilitiesImpl(FilterCapabilities copy) {
/*  66 */     this.version = copy.getVersion();
/*  67 */     this.id = (copy.getIdCapabilities() == null) ? null : new IdCapabilitiesImpl(copy.getIdCapabilities());
/*  68 */     this.scalar = toScalarCapabilitiesImpl(copy.getScalarCapabilities());
/*  69 */     this.spatial = toSpatialCapabiltiesImpl(copy.getSpatialCapabilities());
/*  70 */     this.temporal = toTemporalCapabilitiesImpl(copy.getTemporalCapabilities());
/*     */   }
/*     */   
/*     */   private ScalarCapabilitiesImpl toScalarCapabilitiesImpl(ScalarCapabilities scalarCapabilities) {
/*  74 */     if (scalarCapabilities == null)
/*  75 */       return new ScalarCapabilitiesImpl(); 
/*  77 */     if (scalarCapabilities instanceof ScalarCapabilitiesImpl)
/*  78 */       return (ScalarCapabilitiesImpl)scalarCapabilities; 
/*  80 */     return new ScalarCapabilitiesImpl(scalarCapabilities);
/*     */   }
/*     */   
/*     */   private IdCapabilitiesImpl toIdCapabilitiesImpl(IdCapabilities idCapabilities) {
/*  84 */     if (idCapabilities == null)
/*  85 */       return new IdCapabilitiesImpl(); 
/*  87 */     if (idCapabilities instanceof IdCapabilitiesImpl)
/*  88 */       return (IdCapabilitiesImpl)idCapabilities; 
/*  90 */     return new IdCapabilitiesImpl(idCapabilities);
/*     */   }
/*     */   
/*     */   private static SpatialCapabiltiesImpl toSpatialCapabiltiesImpl(SpatialCapabilities spatialCapabilities) {
/*  93 */     if (spatialCapabilities == null)
/*  94 */       return new SpatialCapabiltiesImpl(); 
/*  96 */     if (spatialCapabilities instanceof SpatialCapabiltiesImpl)
/*  97 */       return (SpatialCapabiltiesImpl)spatialCapabilities; 
/*  99 */     return new SpatialCapabiltiesImpl(spatialCapabilities);
/*     */   }
/*     */   
/*     */   private static TemporalCapabilitiesImpl toTemporalCapabilitiesImpl(TemporalCapabilities temporalCapabilities) {
/* 103 */     if (temporalCapabilities == null)
/* 104 */       return new TemporalCapabilitiesImpl(); 
/* 106 */     if (temporalCapabilities instanceof TemporalCapabilitiesImpl)
/* 107 */       return (TemporalCapabilitiesImpl)temporalCapabilities; 
/* 109 */     return new TemporalCapabilitiesImpl(temporalCapabilities);
/*     */   }
/*     */   
/*     */   public void setVersion(String version) {
/* 123 */     this.version = version;
/*     */   }
/*     */   
/*     */   public String getVersion() {
/* 127 */     return this.version;
/*     */   }
/*     */   
/*     */   public void setId(IdCapabilities id) {
/* 131 */     this.id = toIdCapabilitiesImpl(id);
/*     */   }
/*     */   
/*     */   public IdCapabilitiesImpl getIdCapabilities() {
/* 135 */     if (this.id == null)
/* 136 */       this.id = new IdCapabilitiesImpl(); 
/* 138 */     return this.id;
/*     */   }
/*     */   
/*     */   public void setScalar(ScalarCapabilities scalar) {
/* 142 */     this.scalar = toScalarCapabilitiesImpl(scalar);
/*     */   }
/*     */   
/*     */   public ScalarCapabilitiesImpl getScalarCapabilities() {
/* 145 */     if (this.scalar == null)
/* 146 */       this.scalar = new ScalarCapabilitiesImpl(); 
/* 148 */     return this.scalar;
/*     */   }
/*     */   
/*     */   public void setSpatial(SpatialCapabilities spatial) {
/* 152 */     this.spatial = toSpatialCapabiltiesImpl(spatial);
/*     */   }
/*     */   
/*     */   public SpatialCapabiltiesImpl getSpatialCapabilities() {
/* 156 */     if (this.spatial == null)
/* 157 */       this.spatial = new SpatialCapabiltiesImpl(); 
/* 159 */     return this.spatial;
/*     */   }
/*     */   
/*     */   public void setTemporal(TemporalCapabilitiesImpl temporal) {
/* 163 */     this.temporal = temporal;
/*     */   }
/*     */   
/*     */   public TemporalCapabilities getTemporalCapabilities() {
/* 168 */     return null;
/*     */   }
/*     */   
/*     */   public void addAll(FilterCapabilities copy) {
/* 172 */     getIdCapabilities().addAll(copy.getIdCapabilities());
/* 173 */     getScalarCapabilities().addAll(copy.getScalarCapabilities());
/* 174 */     getSpatialCapabilities().addAll(copy.getSpatialCapabilities());
/* 175 */     if (getVersion().compareTo(copy.getVersion()) < 0)
/* 176 */       setVersion(copy.getVersion()); 
/*     */   }
/*     */   
/*     */   public String toString() {
/* 181 */     StringBuffer buf = new StringBuffer();
/* 182 */     buf.append("FilterCapabilities [");
/* 183 */     buf.append(this.version);
/* 184 */     buf.append("]");
/* 185 */     if (this.id != null) {
/* 186 */       buf.append("\n idCapabilities=");
/* 187 */       buf.append(this.id);
/*     */     } 
/* 189 */     if (this.scalar != null) {
/* 190 */       buf.append("\n scalarCapabilities=");
/* 191 */       buf.append(this.scalar);
/*     */     } 
/* 193 */     if (this.spatial != null) {
/* 194 */       buf.append("\n spatialCapabilities=");
/* 195 */       buf.append(this.spatial);
/*     */     } 
/* 197 */     return buf.toString();
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 201 */     int prime = 31;
/* 202 */     int result = 1;
/* 203 */     result = 31 * result + ((this.id == null) ? 0 : this.id.hashCode());
/* 204 */     result = 31 * result + ((this.scalar == null) ? 0 : this.scalar.hashCode());
/* 205 */     result = 31 * result + ((this.spatial == null) ? 0 : this.spatial.hashCode());
/* 206 */     result = 31 * result + ((this.temporal == null) ? 0 : this.temporal.hashCode());
/* 207 */     result = 31 * result + ((this.version == null) ? 0 : this.version.hashCode());
/* 208 */     return result;
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/* 212 */     if (this == obj)
/* 213 */       return true; 
/* 214 */     if (obj == null)
/* 215 */       return false; 
/* 216 */     if (getClass() != obj.getClass())
/* 217 */       return false; 
/* 218 */     FilterCapabilitiesImpl other = (FilterCapabilitiesImpl)obj;
/* 219 */     if (this.id == null) {
/* 220 */       if (other.id != null)
/* 221 */         return false; 
/* 222 */     } else if (!this.id.equals(other.id)) {
/* 223 */       return false;
/*     */     } 
/* 224 */     if (this.scalar == null) {
/* 225 */       if (other.scalar != null)
/* 226 */         return false; 
/* 227 */     } else if (!this.scalar.equals(other.scalar)) {
/* 228 */       return false;
/*     */     } 
/* 229 */     if (this.spatial == null) {
/* 230 */       if (other.spatial != null)
/* 231 */         return false; 
/* 232 */     } else if (!this.spatial.equals(other.spatial)) {
/* 233 */       return false;
/*     */     } 
/* 234 */     if (this.temporal == null) {
/* 235 */       if (other.temporal != null)
/* 236 */         return false; 
/* 237 */     } else if (!this.temporal.equals(other.temporal)) {
/* 238 */       return false;
/*     */     } 
/* 239 */     if (this.version == null) {
/* 240 */       if (other.version != null)
/* 241 */         return false; 
/* 242 */     } else if (!this.version.equals(other.version)) {
/* 243 */       return false;
/*     */     } 
/* 244 */     return true;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\filter\capability\FilterCapabilitiesImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */