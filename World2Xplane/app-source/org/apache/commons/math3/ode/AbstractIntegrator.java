/*     */ package org.apache.commons.math3.ode;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.Collections;
/*     */ import java.util.Comparator;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.SortedSet;
/*     */ import java.util.TreeSet;
/*     */ import org.apache.commons.math3.analysis.solvers.BracketingNthOrderBrentSolver;
/*     */ import org.apache.commons.math3.analysis.solvers.UnivariateSolver;
/*     */ import org.apache.commons.math3.exception.DimensionMismatchException;
/*     */ import org.apache.commons.math3.exception.MathIllegalArgumentException;
/*     */ import org.apache.commons.math3.exception.MathIllegalStateException;
/*     */ import org.apache.commons.math3.exception.MaxCountExceededException;
/*     */ import org.apache.commons.math3.exception.NumberIsTooSmallException;
/*     */ import org.apache.commons.math3.exception.util.LocalizedFormats;
/*     */ import org.apache.commons.math3.ode.events.EventHandler;
/*     */ import org.apache.commons.math3.ode.events.EventState;
/*     */ import org.apache.commons.math3.ode.sampling.AbstractStepInterpolator;
/*     */ import org.apache.commons.math3.ode.sampling.StepHandler;
/*     */ import org.apache.commons.math3.ode.sampling.StepInterpolator;
/*     */ import org.apache.commons.math3.util.FastMath;
/*     */ import org.apache.commons.math3.util.Incrementor;
/*     */ import org.apache.commons.math3.util.Precision;
/*     */ 
/*     */ public abstract class AbstractIntegrator implements FirstOrderIntegrator {
/*     */   protected Collection<StepHandler> stepHandlers;
/*     */   
/*     */   protected double stepStart;
/*     */   
/*     */   protected double stepSize;
/*     */   
/*     */   protected boolean isLastStep;
/*     */   
/*     */   protected boolean resetOccurred;
/*     */   
/*     */   private Collection<EventState> eventsStates;
/*     */   
/*     */   private boolean statesInitialized;
/*     */   
/*     */   private final String name;
/*     */   
/*     */   private Incrementor evaluations;
/*     */   
/*     */   private transient ExpandableStatefulODE expandable;
/*     */   
/*     */   public AbstractIntegrator(String name) {
/*  86 */     this.name = name;
/*  87 */     this.stepHandlers = new ArrayList<StepHandler>();
/*  88 */     this.stepStart = Double.NaN;
/*  89 */     this.stepSize = Double.NaN;
/*  90 */     this.eventsStates = new ArrayList<EventState>();
/*  91 */     this.statesInitialized = false;
/*  92 */     this.evaluations = new Incrementor();
/*  93 */     setMaxEvaluations(-1);
/*  94 */     this.evaluations.resetCount();
/*     */   }
/*     */   
/*     */   protected AbstractIntegrator() {
/* 100 */     this(null);
/*     */   }
/*     */   
/*     */   public String getName() {
/* 105 */     return this.name;
/*     */   }
/*     */   
/*     */   public void addStepHandler(StepHandler handler) {
/* 110 */     this.stepHandlers.add(handler);
/*     */   }
/*     */   
/*     */   public Collection<StepHandler> getStepHandlers() {
/* 115 */     return Collections.unmodifiableCollection(this.stepHandlers);
/*     */   }
/*     */   
/*     */   public void clearStepHandlers() {
/* 120 */     this.stepHandlers.clear();
/*     */   }
/*     */   
/*     */   public void addEventHandler(EventHandler handler, double maxCheckInterval, double convergence, int maxIterationCount) {
/* 128 */     addEventHandler(handler, maxCheckInterval, convergence, maxIterationCount, (UnivariateSolver)new BracketingNthOrderBrentSolver(convergence, 5));
/*     */   }
/*     */   
/*     */   public void addEventHandler(EventHandler handler, double maxCheckInterval, double convergence, int maxIterationCount, UnivariateSolver solver) {
/* 139 */     this.eventsStates.add(new EventState(handler, maxCheckInterval, convergence, maxIterationCount, solver));
/*     */   }
/*     */   
/*     */   public Collection<EventHandler> getEventHandlers() {
/* 145 */     List<EventHandler> list = new ArrayList<EventHandler>();
/* 146 */     for (EventState state : this.eventsStates)
/* 147 */       list.add(state.getEventHandler()); 
/* 149 */     return Collections.unmodifiableCollection(list);
/*     */   }
/*     */   
/*     */   public void clearEventHandlers() {
/* 154 */     this.eventsStates.clear();
/*     */   }
/*     */   
/*     */   public double getCurrentStepStart() {
/* 159 */     return this.stepStart;
/*     */   }
/*     */   
/*     */   public double getCurrentSignedStepsize() {
/* 164 */     return this.stepSize;
/*     */   }
/*     */   
/*     */   public void setMaxEvaluations(int maxEvaluations) {
/* 169 */     this.evaluations.setMaximalCount((maxEvaluations < 0) ? Integer.MAX_VALUE : maxEvaluations);
/*     */   }
/*     */   
/*     */   public int getMaxEvaluations() {
/* 174 */     return this.evaluations.getMaximalCount();
/*     */   }
/*     */   
/*     */   public int getEvaluations() {
/* 179 */     return this.evaluations.getCount();
/*     */   }
/*     */   
/*     */   protected void initIntegration(double t0, double[] y0, double t) {
/* 189 */     this.evaluations.resetCount();
/* 191 */     for (EventState state : this.eventsStates)
/* 192 */       state.getEventHandler().init(t0, y0, t); 
/* 195 */     for (StepHandler handler : this.stepHandlers)
/* 196 */       handler.init(t0, y0, t); 
/* 199 */     setStateInitialized(false);
/*     */   }
/*     */   
/*     */   protected void setEquations(ExpandableStatefulODE equations) {
/* 207 */     this.expandable = equations;
/*     */   }
/*     */   
/*     */   public double integrate(FirstOrderDifferentialEquations equations, double t0, double[] y0, double t, double[] y) throws MathIllegalStateException, MathIllegalArgumentException {
/* 215 */     if (y0.length != equations.getDimension())
/* 216 */       throw new DimensionMismatchException(y0.length, equations.getDimension()); 
/* 218 */     if (y.length != equations.getDimension())
/* 219 */       throw new DimensionMismatchException(y.length, equations.getDimension()); 
/* 223 */     ExpandableStatefulODE expandableODE = new ExpandableStatefulODE(equations);
/* 224 */     expandableODE.setTime(t0);
/* 225 */     expandableODE.setPrimaryState(y0);
/* 228 */     integrate(expandableODE, t);
/* 231 */     System.arraycopy(expandableODE.getPrimaryState(), 0, y, 0, y.length);
/* 232 */     return expandableODE.getTime();
/*     */   }
/*     */   
/*     */   public abstract void integrate(ExpandableStatefulODE paramExpandableStatefulODE, double paramDouble) throws MathIllegalStateException, MathIllegalArgumentException;
/*     */   
/*     */   public void computeDerivatives(double t, double[] y, double[] yDot) throws MaxCountExceededException {
/* 264 */     this.evaluations.incrementCount();
/* 265 */     this.expandable.computeDerivatives(t, y, yDot);
/*     */   }
/*     */   
/*     */   protected void setStateInitialized(boolean stateInitialized) {
/* 276 */     this.statesInitialized = stateInitialized;
/*     */   }
/*     */   
/*     */   protected double acceptStep(AbstractStepInterpolator interpolator, double[] y, double[] yDot, double tEnd) throws MathIllegalStateException {
/* 293 */     double previousT = interpolator.getGlobalPreviousTime();
/* 294 */     double currentT = interpolator.getGlobalCurrentTime();
/* 297 */     if (!this.statesInitialized) {
/* 298 */       for (EventState state : this.eventsStates)
/* 299 */         state.reinitializeBegin((StepInterpolator)interpolator); 
/* 301 */       this.statesInitialized = true;
/*     */     } 
/* 305 */     final int orderingSign = interpolator.isForward() ? 1 : -1;
/* 306 */     SortedSet<EventState> occuringEvents = new TreeSet<EventState>(new Comparator<EventState>() {
/*     */           public int compare(EventState es0, EventState es1) {
/* 310 */             return orderingSign * Double.compare(es0.getEventTime(), es1.getEventTime());
/*     */           }
/*     */         });
/* 315 */     for (EventState state : this.eventsStates) {
/* 316 */       if (state.evaluateStep((StepInterpolator)interpolator))
/* 318 */         occuringEvents.add(state); 
/*     */     } 
/* 322 */     while (!occuringEvents.isEmpty()) {
/* 325 */       Iterator<EventState> iterator = occuringEvents.iterator();
/* 326 */       EventState currentEvent = iterator.next();
/* 327 */       iterator.remove();
/* 330 */       double eventT = currentEvent.getEventTime();
/* 331 */       interpolator.setSoftPreviousTime(previousT);
/* 332 */       interpolator.setSoftCurrentTime(eventT);
/* 335 */       interpolator.setInterpolatedTime(eventT);
/* 336 */       double[] eventY = (double[])interpolator.getInterpolatedState().clone();
/* 337 */       currentEvent.stepAccepted(eventT, eventY);
/* 338 */       this.isLastStep = currentEvent.stop();
/* 341 */       for (StepHandler handler : this.stepHandlers)
/* 342 */         handler.handleStep((StepInterpolator)interpolator, this.isLastStep); 
/* 345 */       if (this.isLastStep) {
/* 347 */         System.arraycopy(eventY, 0, y, 0, y.length);
/* 348 */         for (EventState remaining : occuringEvents)
/* 349 */           remaining.stepAccepted(eventT, eventY); 
/* 351 */         return eventT;
/*     */       } 
/* 354 */       if (currentEvent.reset(eventT, eventY)) {
/* 357 */         System.arraycopy(eventY, 0, y, 0, y.length);
/* 358 */         computeDerivatives(eventT, y, yDot);
/* 359 */         this.resetOccurred = true;
/* 360 */         for (EventState remaining : occuringEvents)
/* 361 */           remaining.stepAccepted(eventT, eventY); 
/* 363 */         return eventT;
/*     */       } 
/* 367 */       previousT = eventT;
/* 368 */       interpolator.setSoftPreviousTime(eventT);
/* 369 */       interpolator.setSoftCurrentTime(currentT);
/* 372 */       if (currentEvent.evaluateStep((StepInterpolator)interpolator))
/* 374 */         occuringEvents.add(currentEvent); 
/*     */     } 
/* 379 */     interpolator.setInterpolatedTime(currentT);
/* 380 */     double[] currentY = interpolator.getInterpolatedState();
/* 381 */     for (EventState state : this.eventsStates) {
/* 382 */       state.stepAccepted(currentT, currentY);
/* 383 */       this.isLastStep = (this.isLastStep || state.stop());
/*     */     } 
/* 385 */     this.isLastStep = (this.isLastStep || Precision.equals(currentT, tEnd, 1));
/* 388 */     for (StepHandler handler : this.stepHandlers)
/* 389 */       handler.handleStep((StepInterpolator)interpolator, this.isLastStep); 
/* 392 */     return currentT;
/*     */   }
/*     */   
/*     */   protected void sanityChecks(ExpandableStatefulODE equations, double t) throws NumberIsTooSmallException {
/* 404 */     double threshold = 1000.0D * FastMath.ulp(FastMath.max(FastMath.abs(equations.getTime()), FastMath.abs(t)));
/* 406 */     double dt = FastMath.abs(equations.getTime() - t);
/* 407 */     if (dt <= threshold)
/* 408 */       throw new NumberIsTooSmallException(LocalizedFormats.TOO_SMALL_INTEGRATION_INTERVAL, Double.valueOf(dt), Double.valueOf(threshold), false); 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\ode\AbstractIntegrator.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */