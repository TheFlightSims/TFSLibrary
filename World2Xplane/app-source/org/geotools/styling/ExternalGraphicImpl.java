/*     */ package org.geotools.styling;
/*     */ 
/*     */ import java.net.MalformedURLException;
/*     */ import java.net.URI;
/*     */ import java.net.URISyntaxException;
/*     */ import java.net.URL;
/*     */ import java.util.Collection;
/*     */ import java.util.Collections;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import java.util.TreeSet;
/*     */ import javax.swing.Icon;
/*     */ import org.geotools.metadata.iso.citation.OnLineResourceImpl;
/*     */ import org.geotools.util.Utilities;
/*     */ import org.opengis.metadata.citation.OnLineResource;
/*     */ import org.opengis.style.ColorReplacement;
/*     */ import org.opengis.style.ExternalGraphic;
/*     */ import org.opengis.style.GraphicalSymbol;
/*     */ import org.opengis.style.StyleVisitor;
/*     */ import org.opengis.util.Cloneable;
/*     */ 
/*     */ public class ExternalGraphicImpl implements ExternalGraphic, Symbol, Cloneable {
/*     */   private Icon inlineContent;
/*     */   
/*     */   private OnLineResource online;
/*     */   
/*  56 */   private URL location = null;
/*     */   
/*  57 */   private String format = null;
/*     */   
/*  58 */   private String uri = null;
/*     */   
/*  59 */   private Map<String, Object> customProps = null;
/*     */   
/*     */   private final Set<ColorReplacement> colorReplacements;
/*     */   
/*     */   public ExternalGraphicImpl() {
/*  64 */     this(null, null, null);
/*     */   }
/*     */   
/*     */   public ExternalGraphicImpl(Icon icon, Collection<ColorReplacement> replaces, OnLineResource source) {
/*  68 */     this.inlineContent = icon;
/*  69 */     if (replaces == null) {
/*  70 */       this.colorReplacements = new TreeSet<ColorReplacement>();
/*     */     } else {
/*  72 */       this.colorReplacements = new TreeSet<ColorReplacement>(replaces);
/*     */     } 
/*  74 */     this.online = source;
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public void setURI(String uri) {
/*  80 */     this.uri = uri;
/*     */   }
/*     */   
/*     */   public String getFormat() {
/*  90 */     return this.format;
/*     */   }
/*     */   
/*     */   public URL getLocation() throws MalformedURLException {
/* 101 */     if (this.uri == null)
/* 102 */       return null; 
/* 104 */     if (this.location == null)
/* 105 */       this.location = new URL(this.uri); 
/* 108 */     return this.location;
/*     */   }
/*     */   
/*     */   public void setFormat(String format) {
/* 117 */     this.format = format;
/*     */   }
/*     */   
/*     */   public void setLocation(URL location) {
/* 126 */     if (location == null)
/* 127 */       throw new IllegalArgumentException("ExternalGraphic location URL cannot be null"); 
/* 129 */     this.uri = location.toString();
/* 130 */     this.location = location;
/*     */   }
/*     */   
/*     */   public Object accept(StyleVisitor visitor, Object data) {
/* 134 */     return visitor.visit(this, data);
/*     */   }
/*     */   
/*     */   public void accept(StyleVisitor visitor) {
/* 138 */     visitor.visit(this);
/*     */   }
/*     */   
/*     */   public Object clone() {
/*     */     try {
/* 148 */       return super.clone();
/* 149 */     } catch (CloneNotSupportedException e) {
/* 151 */       throw new AssertionError(e);
/*     */     } 
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 161 */     int PRIME = 1000003;
/* 162 */     int result = 0;
/* 164 */     if (this.format != null)
/* 165 */       result = 1000003 * result + this.format.hashCode(); 
/* 168 */     if (this.uri != null)
/* 169 */       result = 1000003 * result + this.uri.hashCode(); 
/* 184 */     return result;
/*     */   }
/*     */   
/*     */   public boolean equals(Object oth) {
/* 199 */     if (this == oth)
/* 200 */       return true; 
/* 203 */     if (oth instanceof ExternalGraphicImpl) {
/* 204 */       ExternalGraphicImpl other = (ExternalGraphicImpl)oth;
/* 206 */       return (Utilities.equals(this.uri, other.uri) && Utilities.equals(this.format, other.format));
/*     */     } 
/* 210 */     return false;
/*     */   }
/*     */   
/*     */   public Map<String, Object> getCustomProperties() {
/* 214 */     return this.customProps;
/*     */   }
/*     */   
/*     */   public void setCustomProperties(Map<String, Object> list) {
/* 218 */     this.customProps = list;
/*     */   }
/*     */   
/*     */   public OnLineResource getOnlineResource() {
/* 222 */     if (this.online == null) {
/* 223 */       OnLineResourceImpl impl = new OnLineResourceImpl();
/*     */       try {
/* 225 */         impl.setLinkage(new URI(this.uri));
/* 226 */       } catch (URISyntaxException e) {
/* 227 */         throw new IllegalArgumentException(e);
/*     */       } 
/* 229 */       this.online = (OnLineResource)impl;
/*     */     } 
/* 231 */     return this.online;
/*     */   }
/*     */   
/*     */   public void setOnlineResource(OnLineResource online) {
/* 235 */     this.online = online;
/*     */   }
/*     */   
/*     */   public Icon getInlineContent() {
/* 239 */     return this.inlineContent;
/*     */   }
/*     */   
/*     */   public void setInlineContent(Icon inlineContent) {
/* 243 */     this.inlineContent = inlineContent;
/*     */   }
/*     */   
/*     */   public Collection<ColorReplacement> getColorReplacements() {
/* 247 */     return Collections.unmodifiableCollection(this.colorReplacements);
/*     */   }
/*     */   
/*     */   public Set<ColorReplacement> colorReplacements() {
/* 251 */     return this.colorReplacements;
/*     */   }
/*     */   
/*     */   static GraphicalSymbol cast(GraphicalSymbol item) {
/* 255 */     if (item == null)
/* 256 */       return null; 
/* 258 */     if (item instanceof ExternalGraphicImpl)
/* 259 */       return item; 
/* 261 */     if (item instanceof ExternalGraphic) {
/* 262 */       ExternalGraphic graphic = (ExternalGraphic)item;
/* 263 */       ExternalGraphicImpl copy = new ExternalGraphicImpl();
/* 264 */       copy.colorReplacements().addAll(graphic.getColorReplacements());
/* 265 */       copy.setFormat(graphic.getFormat());
/* 266 */       copy.setInlineContent(graphic.getInlineContent());
/* 267 */       copy.setOnlineResource(graphic.getOnlineResource());
/* 269 */       return copy;
/*     */     } 
/* 271 */     return null;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\styling\ExternalGraphicImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */