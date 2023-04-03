/*    */ package scala.concurrent.impl;
/*    */ 
/*    */ import java.util.concurrent.ExecutionException;
/*    */ import scala.runtime.NonLocalReturnControl;
/*    */ import scala.util.Failure;
/*    */ import scala.util.Success;
/*    */ import scala.util.Try;
/*    */ import scala.util.control.ControlThrowable;
/*    */ 
/*    */ public final class Promise$ {
/*    */   public static final Promise$ MODULE$;
/*    */   
/*    */   private Promise$() {
/* 44 */     MODULE$ = this;
/*    */   }
/*    */   
/*    */   public <T> Try<T> scala$concurrent$impl$Promise$$resolveTry(Try<T> source) {
/*    */     Try<T> try_;
/* 46 */     if (source instanceof Failure) {
/* 46 */       Failure failure = (Failure)source;
/* 47 */       try_ = resolver(failure.exception());
/*    */     } else {
/* 48 */       try_ = source;
/*    */     } 
/*    */     return try_;
/*    */   }
/*    */   
/*    */   private <T> Try<T> resolver(Throwable throwable) {
/*    */     Failure failure;
/* 51 */     if (throwable instanceof NonLocalReturnControl) {
/* 51 */       NonLocalReturnControl nonLocalReturnControl = (NonLocalReturnControl)throwable;
/* 51 */       Success success = new Success(nonLocalReturnControl.value());
/* 53 */     } else if (throwable instanceof ControlThrowable) {
/* 53 */       ControlThrowable controlThrowable = (ControlThrowable)throwable;
/* 53 */       failure = new Failure(new ExecutionException("Boxed ControlThrowable", (Throwable)controlThrowable));
/* 54 */     } else if (throwable instanceof InterruptedException) {
/* 54 */       InterruptedException interruptedException = (InterruptedException)throwable;
/* 54 */       failure = new Failure(new ExecutionException("Boxed InterruptedException", interruptedException));
/* 55 */     } else if (throwable instanceof Error) {
/* 55 */       Error error = (Error)throwable;
/* 55 */       failure = new Failure(new ExecutionException("Boxed Error", error));
/*    */     } else {
/* 56 */       failure = new Failure(throwable);
/*    */     } 
/*    */     return (Try<T>)failure;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\concurrent\impl\Promise$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */