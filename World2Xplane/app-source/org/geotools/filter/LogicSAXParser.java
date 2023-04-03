/*     */ package org.geotools.filter;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.logging.Logger;
/*     */ import org.geotools.util.logging.Logging;
/*     */ import org.opengis.filter.Filter;
/*     */ 
/*     */ public class LogicSAXParser {
/* 126 */   private static final Logger LOGGER = Logging.getLogger("org.geotools.filter");
/*     */   
/*     */   private FilterFactory ff;
/*     */   
/* 132 */   private short logicType = -1;
/*     */   
/* 135 */   private List subFilters = new ArrayList();
/*     */   
/* 138 */   private LogicSAXParser logicFactory = null;
/*     */   
/*     */   private boolean isComplete = false;
/*     */   
/*     */   public LogicSAXParser() {
/* 147 */     this(FilterFactoryFinder.createFilterFactory());
/*     */   }
/*     */   
/*     */   public LogicSAXParser(FilterFactory factory) {
/* 151 */     this.ff = factory;
/* 152 */     LOGGER.finer("made new logic factory");
/*     */   }
/*     */   
/*     */   public void setFilterFactory(FilterFactory factory) {
/* 156 */     this.ff = factory;
/*     */   }
/*     */   
/*     */   public void start(short logicType) throws IllegalFilterException {
/* 170 */     LOGGER.finest("got a start element: " + logicType);
/* 172 */     if (this.logicType != -1) {
/* 174 */       if (this.logicFactory == null)
/* 175 */         this.logicFactory = new LogicSAXParser(); 
/* 177 */       this.logicFactory.start(logicType);
/*     */     } else {
/* 178 */       if (!AbstractFilter.isLogicFilter(logicType))
/* 179 */         throw new IllegalFilterException("Add logic filter type does not match declared type."); 
/* 182 */       this.logicType = logicType;
/*     */     } 
/*     */   }
/*     */   
/*     */   public void end(short logicType) throws IllegalFilterException {
/* 197 */     LOGGER.finer("got an end element: " + logicType);
/* 199 */     if (this.logicFactory != null) {
/* 200 */       LOGGER.finer("sending end element to nested logic filter: " + logicType);
/* 202 */       this.logicFactory.end(logicType);
/* 204 */       if (this.logicFactory.isComplete()) {
/* 205 */         this.subFilters.add(this.logicFactory.create());
/* 206 */         this.logicFactory = null;
/*     */       } 
/* 208 */     } else if (this.logicType == logicType) {
/* 209 */       LOGGER.finer("end element matched internal type: " + this.logicType);
/* 210 */       this.isComplete = true;
/*     */     } else {
/* 212 */       throw new IllegalFilterException("Logic Factory got an end message that it can't process.");
/*     */     } 
/*     */   }
/*     */   
/*     */   public void add(Filter filter) {
/* 223 */     LOGGER.finer("added a filter: " + filter.toString());
/* 225 */     if (this.logicFactory != null) {
/* 226 */       LOGGER.finer("adding to nested logic filter: " + filter.toString());
/* 227 */       this.logicFactory.add(filter);
/*     */     } else {
/* 229 */       LOGGER.finer("added to sub filters: " + filter.toString());
/* 230 */       this.subFilters.add(filter);
/*     */     } 
/*     */   }
/*     */   
/*     */   public Filter create() throws IllegalFilterException {
/* 242 */     LogicFilter filter = null;
/* 244 */     LOGGER.finer("creating a logic filter");
/* 246 */     if (isComplete()) {
/* 247 */       LOGGER.finer("filter is complete, with type: " + this.logicType);
/* 249 */       if (this.logicType == 3) {
/* 250 */         filter = this.ff.createLogicFilter(this.subFilters.get(0), this.logicType);
/*     */       } else {
/* 253 */         filter = this.ff.createLogicFilter(this.logicType);
/* 255 */         Iterator<Filter> iterator = this.subFilters.iterator();
/* 257 */         while (iterator.hasNext())
/* 258 */           filter.addFilter(iterator.next()); 
/*     */       } 
/* 263 */       this.subFilters = new ArrayList();
/* 264 */       this.logicType = -1;
/* 265 */       this.isComplete = false;
/* 267 */       return filter;
/*     */     } 
/* 269 */     throw new IllegalFilterException("Attempted to generate incomplete logic filter.");
/*     */   }
/*     */   
/*     */   public boolean isComplete() {
/* 281 */     return this.isComplete;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\filter\LogicSAXParser.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */