/*     */ package org.geotools.filter;
/*     */ 
/*     */ import java.util.Collection;
/*     */ import java.util.HashSet;
/*     */ import java.util.Iterator;
/*     */ import java.util.Set;
/*     */ import java.util.logging.Level;
/*     */ import java.util.logging.Logger;
/*     */ import org.geotools.factory.CommonFactoryFinder;
/*     */ import org.geotools.util.logging.Logging;
/*     */ import org.opengis.filter.FilterFactory;
/*     */ import org.opengis.filter.FilterVisitor;
/*     */ import org.opengis.filter.identity.Identifier;
/*     */ 
/*     */ public class FidFilterImpl extends AbstractFilterImpl implements FidFilter {
/*  58 */   private static final Logger LOGGER = Logging.getLogger("org.geotools.core");
/*     */   
/*  61 */   private Set<Identifier> fids = new HashSet<Identifier>();
/*     */   
/*  62 */   private Set<String> ids = new HashSet<String>();
/*     */   
/*     */   protected FidFilterImpl() {
/*  70 */     super(CommonFactoryFinder.getFilterFactory(null));
/*  71 */     this.filterType = 22;
/*     */   }
/*     */   
/*     */   protected FidFilterImpl(String initialFid) {
/*  82 */     super(CommonFactoryFinder.getFilterFactory(null));
/*  83 */     this.filterType = 22;
/*  84 */     addFid(initialFid);
/*     */   }
/*     */   
/*     */   protected FidFilterImpl(Set<Identifier> fids) {
/*  93 */     super(CommonFactoryFinder.getFilterFactory(null));
/*  94 */     this.filterType = 22;
/*  96 */     for (Iterator it = fids.iterator(); it.hasNext(); ) {
/*  97 */       Object next = it.next();
/*  98 */       if (!(next instanceof Identifier))
/*  99 */         throw new ClassCastException("Fids must implement Identifier, " + next.getClass() + " does not"); 
/*     */     } 
/* 102 */     this.fids = fids;
/* 103 */     for (Identifier identifier : this.fids)
/* 104 */       this.ids.add(identifier.getID().toString()); 
/*     */   }
/*     */   
/*     */   public final String[] getFids() {
/* 116 */     return (String[])fids().toArray((Object[])new String[0]);
/*     */   }
/*     */   
/*     */   public Set getIDs() {
/* 123 */     return getFidsSet();
/*     */   }
/*     */   
/*     */   public Set getIdentifiers() {
/* 130 */     return this.fids;
/*     */   }
/*     */   
/*     */   public void setIDs(Set ids) {
/* 137 */     this.fids = new HashSet<Identifier>();
/* 138 */     addAllFids(ids);
/*     */   }
/*     */   
/*     */   public Set getFidsSet() {
/* 147 */     return fids();
/*     */   }
/*     */   
/*     */   private Set fids() {
/* 156 */     return new HashSet<String>(this.ids);
/*     */   }
/*     */   
/*     */   public final void addFid(String fid) {
/* 167 */     LOGGER.finest("got fid: " + fid);
/* 168 */     this.fids.add(this.factory.featureId(fid));
/* 169 */     this.ids.add(fid);
/*     */   }
/*     */   
/*     */   public void addAllFids(Collection fidsToAdd) {
/* 179 */     if (fidsToAdd == null)
/*     */       return; 
/* 182 */     for (Iterator<String> i = fidsToAdd.iterator(); i.hasNext(); ) {
/* 183 */       String fid = i.next();
/* 184 */       addFid(fid);
/*     */     } 
/*     */   }
/*     */   
/*     */   public final void removeFid(String fid) {
/* 195 */     if (fid == null)
/*     */       return; 
/* 199 */     for (Iterator<Identifier> f = this.fids.iterator(); f.hasNext(); ) {
/* 200 */       Identifier featureId = f.next();
/* 201 */       if (fid.equals(featureId.getID().toString())) {
/* 202 */         f.remove();
/* 203 */         this.ids.remove(fid);
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public void removeAllFids(Collection fidsToRemove) {
/* 216 */     if (fidsToRemove == null)
/*     */       return; 
/* 219 */     for (Iterator<String> f = fidsToRemove.iterator(); f.hasNext(); ) {
/* 220 */       String fid = f.next();
/* 221 */       removeFid(fid);
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean evaluate(Object feature) {
/* 241 */     if (feature == null)
/* 242 */       return false; 
/* 246 */     FilterFactory ff = CommonFactoryFinder.getFilterFactory(null);
/* 247 */     String evaluate = (String)ff.property("@id").evaluate(feature, String.class);
/* 248 */     if (evaluate == null)
/* 249 */       return false; 
/* 251 */     return this.ids.contains(evaluate);
/*     */   }
/*     */   
/*     */   public String toString() {
/* 261 */     StringBuffer fidFilter = new StringBuffer();
/* 263 */     Iterator<Identifier> fidIterator = this.fids.iterator();
/* 265 */     while (fidIterator.hasNext()) {
/* 266 */       fidFilter.append(fidIterator.next().toString());
/* 268 */       if (fidIterator.hasNext())
/* 269 */         fidFilter.append(", "); 
/*     */     } 
/* 273 */     return "[ " + fidFilter.toString() + " ]";
/*     */   }
/*     */   
/*     */   public Object accept(FilterVisitor visitor, Object extraData) {
/* 288 */     return visitor.visit(this, extraData);
/*     */   }
/*     */   
/*     */   public boolean equals(Object filter) {
/* 300 */     LOGGER.finest("condition: " + filter);
/* 302 */     if (filter != null && filter.getClass() == getClass()) {
/* 303 */       if (LOGGER.isLoggable(Level.FINEST))
/* 304 */         LOGGER.finest("condition: " + ((FidFilterImpl)filter).filterType); 
/* 307 */       if (((FidFilterImpl)filter).filterType == 22)
/* 308 */         return this.fids.equals(((FidFilterImpl)filter).fids); 
/* 310 */       return false;
/*     */     } 
/* 313 */     return false;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 323 */     return this.fids.hashCode();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\filter\FidFilterImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */