/*     */ package org.apache.commons.math3.ode.events;
/*     */ 
/*     */ import org.apache.commons.math3.analysis.UnivariateFunction;
/*     */ import org.apache.commons.math3.analysis.solvers.AllowedSolution;
/*     */ import org.apache.commons.math3.analysis.solvers.BracketedUnivariateSolver;
/*     */ import org.apache.commons.math3.analysis.solvers.PegasusSolver;
/*     */ import org.apache.commons.math3.analysis.solvers.UnivariateSolver;
/*     */ import org.apache.commons.math3.analysis.solvers.UnivariateSolverUtils;
/*     */ import org.apache.commons.math3.exception.ConvergenceException;
/*     */ import org.apache.commons.math3.ode.sampling.StepInterpolator;
/*     */ import org.apache.commons.math3.util.FastMath;
/*     */ 
/*     */ public class EventState {
/*     */   private final EventHandler handler;
/*     */   
/*     */   private final double maxCheckInterval;
/*     */   
/*     */   private final double convergence;
/*     */   
/*     */   private final int maxIterationCount;
/*     */   
/*     */   private double t0;
/*     */   
/*     */   private double g0;
/*     */   
/*     */   private boolean g0Positive;
/*     */   
/*     */   private boolean pendingEvent;
/*     */   
/*     */   private double pendingEventTime;
/*     */   
/*     */   private double previousEventTime;
/*     */   
/*     */   private boolean forward;
/*     */   
/*     */   private boolean increasing;
/*     */   
/*     */   private EventHandler.Action nextAction;
/*     */   
/*     */   private final UnivariateSolver solver;
/*     */   
/*     */   public EventState(EventHandler handler, double maxCheckInterval, double convergence, int maxIterationCount, UnivariateSolver solver) {
/* 103 */     this.handler = handler;
/* 104 */     this.maxCheckInterval = maxCheckInterval;
/* 105 */     this.convergence = FastMath.abs(convergence);
/* 106 */     this.maxIterationCount = maxIterationCount;
/* 107 */     this.solver = solver;
/* 110 */     this.t0 = Double.NaN;
/* 111 */     this.g0 = Double.NaN;
/* 112 */     this.g0Positive = true;
/* 113 */     this.pendingEvent = false;
/* 114 */     this.pendingEventTime = Double.NaN;
/* 115 */     this.previousEventTime = Double.NaN;
/* 116 */     this.increasing = true;
/* 117 */     this.nextAction = EventHandler.Action.CONTINUE;
/*     */   }
/*     */   
/*     */   public EventHandler getEventHandler() {
/* 125 */     return this.handler;
/*     */   }
/*     */   
/*     */   public double getMaxCheckInterval() {
/* 132 */     return this.maxCheckInterval;
/*     */   }
/*     */   
/*     */   public double getConvergence() {
/* 139 */     return this.convergence;
/*     */   }
/*     */   
/*     */   public int getMaxIterationCount() {
/* 146 */     return this.maxIterationCount;
/*     */   }
/*     */   
/*     */   public void reinitializeBegin(StepInterpolator interpolator) {
/* 154 */     this.t0 = interpolator.getPreviousTime();
/* 155 */     interpolator.setInterpolatedTime(this.t0);
/* 156 */     this.g0 = this.handler.g(this.t0, interpolator.getInterpolatedState());
/* 157 */     if (this.g0 == 0.0D) {
/* 171 */       double epsilon = FastMath.max(this.solver.getAbsoluteAccuracy(), FastMath.abs(this.solver.getRelativeAccuracy() * this.t0));
/* 173 */       double tStart = this.t0 + 0.5D * epsilon;
/* 174 */       interpolator.setInterpolatedTime(tStart);
/* 175 */       this.g0 = this.handler.g(tStart, interpolator.getInterpolatedState());
/*     */     } 
/* 177 */     this.g0Positive = (this.g0 >= 0.0D);
/*     */   }
/*     */   
/*     */   public boolean evaluateStep(final StepInterpolator interpolator) throws ConvergenceException {
/* 190 */     this.forward = interpolator.isForward();
/* 191 */     double t1 = interpolator.getCurrentTime();
/* 192 */     double dt = t1 - this.t0;
/* 193 */     if (FastMath.abs(dt) < this.convergence)
/* 195 */       return false; 
/* 197 */     int n = FastMath.max(1, (int)FastMath.ceil(FastMath.abs(dt) / this.maxCheckInterval));
/* 198 */     double h = dt / n;
/* 200 */     UnivariateFunction f = new UnivariateFunction() {
/*     */         public double value(double t) {
/* 202 */           interpolator.setInterpolatedTime(t);
/* 203 */           return EventState.this.handler.g(t, interpolator.getInterpolatedState());
/*     */         }
/*     */       };
/* 207 */     double ta = this.t0;
/* 208 */     double ga = this.g0;
/* 209 */     for (int i = 0; i < n; i++) {
/* 212 */       double tb = this.t0 + (i + 1) * h;
/* 213 */       interpolator.setInterpolatedTime(tb);
/* 214 */       double gb = this.handler.g(tb, interpolator.getInterpolatedState());
/* 217 */       if ((this.g0Positive ^ ((gb >= 0.0D) ? 1 : 0)) != 0) {
/*     */         double root;
/* 221 */         this.increasing = (gb >= ga);
/* 225 */         if (this.solver instanceof BracketedUnivariateSolver) {
/* 227 */           BracketedUnivariateSolver<UnivariateFunction> bracketing = (BracketedUnivariateSolver<UnivariateFunction>)this.solver;
/* 229 */           root = this.forward ? bracketing.solve(this.maxIterationCount, f, ta, tb, AllowedSolution.RIGHT_SIDE) : bracketing.solve(this.maxIterationCount, f, tb, ta, AllowedSolution.LEFT_SIDE);
/*     */         } else {
/* 233 */           double baseRoot = this.forward ? this.solver.solve(this.maxIterationCount, f, ta, tb) : this.solver.solve(this.maxIterationCount, f, tb, ta);
/* 236 */           int remainingEval = this.maxIterationCount - this.solver.getEvaluations();
/* 237 */           PegasusSolver pegasusSolver = new PegasusSolver(this.solver.getRelativeAccuracy(), this.solver.getAbsoluteAccuracy());
/* 239 */           root = this.forward ? UnivariateSolverUtils.forceSide(remainingEval, f, (BracketedUnivariateSolver)pegasusSolver, baseRoot, ta, tb, AllowedSolution.RIGHT_SIDE) : UnivariateSolverUtils.forceSide(remainingEval, f, (BracketedUnivariateSolver)pegasusSolver, baseRoot, tb, ta, AllowedSolution.LEFT_SIDE);
/*     */         } 
/* 246 */         if (!Double.isNaN(this.previousEventTime) && FastMath.abs(root - ta) <= this.convergence && FastMath.abs(root - this.previousEventTime) <= this.convergence) {
/* 251 */           ta = this.forward ? (ta + this.convergence) : (ta - this.convergence);
/* 252 */           ga = f.value(ta);
/* 253 */           i--;
/*     */         } else {
/* 254 */           if (Double.isNaN(this.previousEventTime) || FastMath.abs(this.previousEventTime - root) > this.convergence) {
/* 256 */             this.pendingEventTime = root;
/* 257 */             this.pendingEvent = true;
/* 258 */             return true;
/*     */           } 
/* 261 */           ta = tb;
/* 262 */           ga = gb;
/*     */         } 
/*     */       } else {
/* 267 */         ta = tb;
/* 268 */         ga = gb;
/*     */       } 
/*     */     } 
/* 274 */     this.pendingEvent = false;
/* 275 */     this.pendingEventTime = Double.NaN;
/* 276 */     return false;
/*     */   }
/*     */   
/*     */   public double getEventTime() {
/* 285 */     return this.pendingEvent ? this.pendingEventTime : (this.forward ? Double.POSITIVE_INFINITY : Double.NEGATIVE_INFINITY);
/*     */   }
/*     */   
/*     */   public void stepAccepted(double t, double[] y) {
/* 298 */     this.t0 = t;
/* 299 */     this.g0 = this.handler.g(t, y);
/* 301 */     if (this.pendingEvent && FastMath.abs(this.pendingEventTime - t) <= this.convergence) {
/* 303 */       this.previousEventTime = t;
/* 304 */       this.g0Positive = this.increasing;
/* 305 */       this.nextAction = this.handler.eventOccurred(t, y, ((this.increasing ^ this.forward) == 0));
/*     */     } else {
/* 307 */       this.g0Positive = (this.g0 >= 0.0D);
/* 308 */       this.nextAction = EventHandler.Action.CONTINUE;
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean stop() {
/* 317 */     return (this.nextAction == EventHandler.Action.STOP);
/*     */   }
/*     */   
/*     */   public boolean reset(double t, double[] y) {
/* 329 */     if (!this.pendingEvent || FastMath.abs(this.pendingEventTime - t) > this.convergence)
/* 330 */       return false; 
/* 333 */     if (this.nextAction == EventHandler.Action.RESET_STATE)
/* 334 */       this.handler.resetState(t, y); 
/* 336 */     this.pendingEvent = false;
/* 337 */     this.pendingEventTime = Double.NaN;
/* 339 */     return (this.nextAction == EventHandler.Action.RESET_STATE || this.nextAction == EventHandler.Action.RESET_DERIVATIVES);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\ode\events\EventState.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */