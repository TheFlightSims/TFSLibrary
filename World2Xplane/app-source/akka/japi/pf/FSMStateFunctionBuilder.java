/*     */ package akka.japi.pf;
/*     */ 
/*     */ import akka.actor.FSM;
/*     */ import java.util.List;
/*     */ import scala.PartialFunction;
/*     */ 
/*     */ public class FSMStateFunctionBuilder<S, D> {
/*  22 */   private PFBuilder<FSM.Event<D>, FSM.State<S, D>> builder = new PFBuilder<FSM.Event<D>, FSM.State<S, D>>();
/*     */   
/*     */   private FSMStateFunctionBuilder<S, D> erasedEvent(final Object eventOrType, final Object dataOrType, final FI.TypedPredicate2 predicate, final FI.Apply2 apply) {
/*  46 */     this.builder.match(FSM.Event.class, new FI.TypedPredicate<FSM.Event>() {
/*     */           public boolean defined(FSM.Event param1Event) {
/*  50 */             boolean bool = true;
/*  51 */             if (eventOrType != null)
/*  52 */               if (eventOrType instanceof Class) {
/*  53 */                 Class clazz = (Class)eventOrType;
/*  54 */                 bool = clazz.isInstance(param1Event.event());
/*     */               } else {
/*  57 */                 bool = eventOrType.equals(param1Event.event());
/*     */               }  
/*  60 */             if (bool && dataOrType != null)
/*  61 */               if (dataOrType instanceof Class) {
/*  62 */                 Class clazz = (Class)dataOrType;
/*  63 */                 bool = clazz.isInstance(param1Event.stateData());
/*     */               } else {
/*  66 */                 bool = dataOrType.equals(param1Event.stateData());
/*     */               }  
/*  69 */             if (bool && predicate != null) {
/*  71 */               boolean bool1 = predicate.defined(param1Event.event(), param1Event.stateData());
/*  72 */               bool = bool1;
/*     */             } 
/*  74 */             return bool;
/*     */           }
/*     */         }new FI.Apply<FSM.Event, FSM.State<S, D>>() {
/*     */           public FSM.State<S, D> apply(FSM.Event param1Event) throws Exception {
/*  80 */             return apply.apply(param1Event.event(), param1Event.stateData());
/*     */           }
/*     */         });
/*  86 */     return this;
/*     */   }
/*     */   
/*     */   public final <P, Q> FSMStateFunctionBuilder<S, D> event(Class<P> paramClass, Class<Q> paramClass1, FI.TypedPredicate2<P, Q> paramTypedPredicate2, FI.Apply2<P, Q, FSM.State<S, D>> paramApply2) {
/* 104 */     erasedEvent(paramClass, paramClass1, paramTypedPredicate2, paramApply2);
/* 105 */     return this;
/*     */   }
/*     */   
/*     */   public <P, Q> FSMStateFunctionBuilder<S, D> event(Class<P> paramClass, Class<Q> paramClass1, FI.Apply2<P, Q, FSM.State<S, D>> paramApply2) {
/* 121 */     return erasedEvent(paramClass, paramClass1, null, paramApply2);
/*     */   }
/*     */   
/*     */   public <P> FSMStateFunctionBuilder<S, D> event(Class<P> paramClass, FI.TypedPredicate2<P, D> paramTypedPredicate2, FI.Apply2<P, D, FSM.State<S, D>> paramApply2) {
/* 135 */     return erasedEvent(paramClass, null, paramTypedPredicate2, paramApply2);
/*     */   }
/*     */   
/*     */   public <P> FSMStateFunctionBuilder<S, D> event(Class<P> paramClass, FI.Apply2<P, D, FSM.State<S, D>> paramApply2) {
/* 147 */     return erasedEvent(paramClass, null, null, paramApply2);
/*     */   }
/*     */   
/*     */   public FSMStateFunctionBuilder<S, D> event(FI.TypedPredicate2<Object, D> paramTypedPredicate2, FI.Apply2<Object, D, FSM.State<S, D>> paramApply2) {
/* 159 */     return erasedEvent(null, null, paramTypedPredicate2, paramApply2);
/*     */   }
/*     */   
/*     */   public <Q> FSMStateFunctionBuilder<S, D> event(final List<Object> eventMatches, final Class<Q> dataType, final FI.Apply2<Object, Q, FSM.State<S, D>> apply) {
/* 175 */     this.builder.match(FSM.Event.class, new FI.TypedPredicate<FSM.Event>() {
/*     */           public boolean defined(FSM.Event param1Event) {
/* 179 */             if (dataType != null && !dataType.isInstance(param1Event.stateData()))
/* 180 */               return false; 
/* 182 */             boolean bool = false;
/* 183 */             Object object = param1Event.event();
/* 184 */             for (Class clazz : eventMatches) {
/* 185 */               if (clazz instanceof Class) {
/* 186 */                 Class clazz1 = clazz;
/* 187 */                 bool = clazz1.isInstance(object);
/*     */               } else {
/* 189 */                 bool = object.equals(clazz);
/*     */               } 
/* 191 */               if (bool)
/*     */                 break; 
/*     */             } 
/* 194 */             return bool;
/*     */           }
/*     */         },  new FI.Apply<FSM.Event, FSM.State<S, D>>() {
/*     */           public FSM.State<S, D> apply(FSM.Event param1Event) throws Exception {
/* 200 */             Object object = param1Event.stateData();
/* 201 */             return apply.apply(param1Event.event(), object);
/*     */           }
/*     */         });
/* 206 */     return this;
/*     */   }
/*     */   
/*     */   public FSMStateFunctionBuilder<S, D> event(List<Object> paramList, FI.Apply2<Object, D, FSM.State<S, D>> paramApply2) {
/* 219 */     return event(paramList, (Class<D>)null, paramApply2);
/*     */   }
/*     */   
/*     */   public <P, Q> FSMStateFunctionBuilder<S, D> eventEquals(P paramP, Class<Q> paramClass, FI.Apply2<P, Q, FSM.State<S, D>> paramApply2) {
/* 234 */     return erasedEvent(paramP, paramClass, null, paramApply2);
/*     */   }
/*     */   
/*     */   public <P> FSMStateFunctionBuilder<S, D> eventEquals(P paramP, FI.Apply2<P, D, FSM.State<S, D>> paramApply2) {
/* 246 */     return erasedEvent(paramP, null, null, paramApply2);
/*     */   }
/*     */   
/*     */   public FSMStateFunctionBuilder<S, D> anyEvent(FI.Apply2<Object, D, FSM.State<S, D>> paramApply2) {
/* 256 */     return erasedEvent(null, null, null, paramApply2);
/*     */   }
/*     */   
/*     */   public PartialFunction<FSM.Event<D>, FSM.State<S, D>> build() {
/* 266 */     return this.builder.build();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\japi\pf\FSMStateFunctionBuilder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */