/*     */ package org.poly2tri.triangulation;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import org.poly2tri.Poly2Tri;
/*     */ import org.poly2tri.geometry.polygon.Polygon;
/*     */ import org.poly2tri.geometry.polygon.PolygonSet;
/*     */ import org.poly2tri.triangulation.sets.ConstrainedPointSet;
/*     */ import org.poly2tri.triangulation.sets.PointSet;
/*     */ import org.slf4j.Logger;
/*     */ import org.slf4j.LoggerFactory;
/*     */ 
/*     */ public class TriangulationProcess implements Runnable {
/*  44 */   private static final Logger logger = LoggerFactory.getLogger(TriangulationProcess.class);
/*     */   
/*     */   private final TriangulationAlgorithm _algorithm;
/*     */   
/*     */   private TriangulationContext<?> _tcx;
/*     */   
/*     */   private Thread _thread;
/*     */   
/*     */   private boolean _isTerminated = false;
/*     */   
/*  51 */   private int _pointCount = 0;
/*     */   
/*  52 */   private long _timestamp = 0L;
/*     */   
/*  53 */   private double _triangulationTime = 0.0D;
/*     */   
/*     */   private boolean _awaitingTermination;
/*     */   
/*     */   private boolean _restart = false;
/*     */   
/*  58 */   private ArrayList<Triangulatable> _triangulations = new ArrayList<>();
/*     */   
/*  60 */   private ArrayList<TriangulationProcessListener> _listeners = new ArrayList<>();
/*     */   
/*     */   public void addListener(TriangulationProcessListener listener) {
/*  64 */     this._listeners.add(listener);
/*     */   }
/*     */   
/*     */   public void removeListener(TriangulationProcessListener listener) {
/*  69 */     this._listeners.remove(listener);
/*     */   }
/*     */   
/*     */   public void clearListeners() {
/*  74 */     this._listeners.clear();
/*     */   }
/*     */   
/*     */   private void sendEvent(TriangulationProcessEvent event) {
/*  83 */     for (TriangulationProcessListener l : this._listeners)
/*  85 */       l.triangulationEvent(event, this._tcx.getTriangulatable()); 
/*     */   }
/*     */   
/*     */   public int getStepCount() {
/*  91 */     return this._tcx.getStepCount();
/*     */   }
/*     */   
/*     */   public long getTimestamp() {
/*  96 */     return this._timestamp;
/*     */   }
/*     */   
/*     */   public double getTriangulationTime() {
/* 101 */     return this._triangulationTime;
/*     */   }
/*     */   
/*     */   public TriangulationProcess() {
/* 110 */     this(TriangulationAlgorithm.DTSweep);
/*     */   }
/*     */   
/*     */   public TriangulationProcess(TriangulationAlgorithm algorithm) {
/* 115 */     this._algorithm = algorithm;
/* 116 */     this._tcx = Poly2Tri.createContext(algorithm);
/*     */   }
/*     */   
/*     */   public void triangulate(PointSet ps) {
/* 137 */     this._triangulations.clear();
/* 138 */     this._triangulations.add(ps);
/* 139 */     start();
/*     */   }
/*     */   
/*     */   public void triangulate(ConstrainedPointSet cps) {
/* 149 */     this._triangulations.clear();
/* 150 */     this._triangulations.add(cps);
/* 151 */     start();
/*     */   }
/*     */   
/*     */   public void triangulate(PolygonSet ps) {
/* 161 */     this._triangulations.clear();
/* 162 */     this._triangulations.addAll(ps.getPolygons());
/* 163 */     start();
/*     */   }
/*     */   
/*     */   public void triangulate(Polygon polygon) {
/* 173 */     this._triangulations.clear();
/* 174 */     this._triangulations.add(polygon);
/* 175 */     start();
/*     */   }
/*     */   
/*     */   public void triangulate(List<Triangulatable> list) {
/* 185 */     this._triangulations.clear();
/* 186 */     this._triangulations.addAll(list);
/* 187 */     start();
/*     */   }
/*     */   
/*     */   private void start() {
/* 192 */     if (this._thread == null || this._thread.getState() == Thread.State.TERMINATED) {
/* 194 */       this._isTerminated = false;
/* 195 */       this._thread = new Thread(this, this._algorithm.name() + "." + this._tcx.getTriangulationMode());
/* 196 */       this._thread.start();
/* 197 */       sendEvent(TriangulationProcessEvent.Started);
/*     */     } else {
/* 202 */       shutdown();
/* 203 */       this._restart = true;
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean isWaiting() {
/* 209 */     if (this._thread != null && this._thread.getState() == Thread.State.WAITING)
/* 211 */       return true; 
/* 213 */     return false;
/*     */   }
/*     */   
/*     */   public void run() {
/* 218 */     this._pointCount = 0;
/*     */     try {
/* 221 */       long time = System.nanoTime();
/* 222 */       for (Triangulatable t : this._triangulations) {
/* 224 */         this._tcx.clear();
/* 225 */         this._tcx.prepareTriangulation(t);
/* 226 */         this._pointCount += this._tcx._points.size();
/* 227 */         Poly2Tri.triangulate(this._tcx);
/*     */       } 
/* 229 */       this._triangulationTime = (System.nanoTime() - time) / 1000000.0D;
/* 230 */       logger.info("Triangulation of {} points [{}ms]", Integer.valueOf(this._pointCount), Double.valueOf(this._triangulationTime));
/* 231 */       sendEvent(TriangulationProcessEvent.Done);
/* 233 */     } catch (RuntimeException e) {
/* 235 */       if (this._awaitingTermination) {
/* 237 */         this._awaitingTermination = false;
/* 238 */         logger.info("Thread[{}] : {}", this._thread.getName(), e.getMessage());
/* 239 */         sendEvent(TriangulationProcessEvent.Aborted);
/*     */       } else {
/* 243 */         e.printStackTrace();
/* 244 */         sendEvent(TriangulationProcessEvent.Failed);
/*     */       } 
/* 247 */     } catch (Exception e) {
/* 249 */       e.printStackTrace();
/* 250 */       logger.info("Triangulation exception {}", e.getMessage());
/* 251 */       sendEvent(TriangulationProcessEvent.Failed);
/*     */     } finally {
/* 255 */       this._timestamp = System.currentTimeMillis();
/* 256 */       this._isTerminated = true;
/* 257 */       this._thread = null;
/*     */     } 
/* 261 */     if (this._restart) {
/* 263 */       this._restart = false;
/* 264 */       start();
/*     */     } 
/*     */   }
/*     */   
/*     */   public void resume() {
/* 270 */     if (this._thread != null)
/* 273 */       if (this._thread.getState() == Thread.State.WAITING) {
/* 275 */         synchronized (this._tcx) {
/* 277 */           this._tcx.notify();
/*     */         } 
/* 280 */       } else if (this._thread.getState() == Thread.State.TIMED_WAITING) {
/* 282 */         this._tcx.waitUntilNotified(false);
/*     */       }  
/*     */   }
/*     */   
/*     */   public void shutdown() {
/* 289 */     this._awaitingTermination = true;
/* 290 */     this._tcx.terminateTriangulation();
/* 291 */     resume();
/*     */   }
/*     */   
/*     */   public TriangulationContext<?> getContext() {
/* 296 */     return this._tcx;
/*     */   }
/*     */   
/*     */   public boolean isDone() {
/* 301 */     return this._isTerminated;
/*     */   }
/*     */   
/*     */   public void requestRead() {
/* 306 */     this._tcx.waitUntilNotified(true);
/*     */   }
/*     */   
/*     */   public boolean isReadable() {
/* 311 */     if (this._thread == null)
/* 313 */       return true; 
/* 317 */     synchronized (this._thread) {
/* 319 */       if (this._thread.getState() == Thread.State.WAITING)
/* 321 */         return true; 
/* 323 */       if (this._thread.getState() == Thread.State.TIMED_WAITING) {
/* 326 */         this._tcx.waitUntilNotified(true);
/* 327 */         return true;
/*     */       } 
/* 329 */       return false;
/*     */     } 
/*     */   }
/*     */   
/*     */   public int getPointCount() {
/* 336 */     return this._pointCount;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\poly2tri\triangulation\TriangulationProcess.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */