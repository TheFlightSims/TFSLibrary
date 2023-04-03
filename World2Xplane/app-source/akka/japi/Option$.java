/*     */ package akka.japi;
/*     */ 
/*     */ import scala.MatchError;
/*     */ import scala.Option;
/*     */ import scala.Some;
/*     */ 
/*     */ public final class Option$ {
/*     */   public static final Option$ MODULE$;
/*     */   
/*     */   private Option$() {
/* 139 */     MODULE$ = this;
/*     */   }
/*     */   
/*     */   public <A> Option<A> some(Object v) {
/* 143 */     return new Option.Some<A>((A)v);
/*     */   }
/*     */   
/*     */   public <A> Option<A> none() {
/* 148 */     return Option.None$.MODULE$;
/*     */   }
/*     */   
/*     */   public <A> Option<A> option(Object v) {
/* 154 */     return (v == null) ? none() : some((A)v);
/*     */   }
/*     */   
/*     */   public <T> Option<T> fromScalaOption(Option scalaOption) {
/*     */     Option<?> option1;
/* 159 */     Option option = scalaOption;
/* 160 */     if (option instanceof Some) {
/* 160 */       Some some = (Some)option;
/* 160 */       Object r = some.x();
/* 160 */       option1 = some(r);
/*     */     } else {
/* 161 */       Option option2 = option;
/* 161 */       if (scala.None$.MODULE$ == null) {
/* 161 */         if (option2 != null)
/*     */           throw new MatchError(option); 
/* 161 */       } else if (!scala.None$.MODULE$.equals(option2)) {
/*     */         throw new MatchError(option);
/*     */       } 
/* 161 */       option1 = none();
/*     */     } 
/*     */     return (Option)option1;
/*     */   }
/*     */   
/*     */   public <A> Option<A> java2ScalaOption(Option<A> o) {
/* 183 */     return o.asScala();
/*     */   }
/*     */   
/*     */   public <A> Option<A> scala2JavaOption(Option o) {
/* 184 */     return o.isDefined() ? some((A)o.get()) : none();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\japi\Option$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */