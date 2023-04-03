/*    */ package scala;
/*    */ 
/*    */ public final class Tuple2$ implements Serializable {
/*    */   public static final Tuple2$ MODULE$;
/*    */   
/*    */   public final String toString() {
/* 19 */     return "Tuple2";
/*    */   }
/*    */   
/*    */   public <T1, T2> Tuple2<T1, T2> apply(Object _1, Object _2) {
/* 19 */     return new Tuple2<T1, T2>((T1)_1, (T2)_2);
/*    */   }
/*    */   
/*    */   public <T1, T2> Option<Tuple2<T1, T2>> unapply(Tuple2 x$0) {
/* 19 */     return (x$0 == null) ? None$.MODULE$ : new Some<Tuple2<T1, T2>>(new Tuple2<T1, T2>((T1)x$0._1(), (T2)x$0._2()));
/*    */   }
/*    */   
/*    */   private Object readResolve() {
/* 19 */     return MODULE$;
/*    */   }
/*    */   
/*    */   public Tuple2<Object, Object> apply$mZZc$sp(boolean _1, boolean _2) {
/* 19 */     return new Tuple2$mcZZ$sp(_1, _2);
/*    */   }
/*    */   
/*    */   public Tuple2<Object, Object> apply$mZCc$sp(boolean _1, char _2) {
/* 19 */     return new Tuple2$mcZC$sp(_1, _2);
/*    */   }
/*    */   
/*    */   public Tuple2<Object, Object> apply$mZDc$sp(boolean _1, double _2) {
/* 19 */     return new Tuple2$mcZD$sp(_1, _2);
/*    */   }
/*    */   
/*    */   public Tuple2<Object, Object> apply$mZIc$sp(boolean _1, int _2) {
/* 19 */     return new Tuple2$mcZI$sp(_1, _2);
/*    */   }
/*    */   
/*    */   public Tuple2<Object, Object> apply$mZJc$sp(boolean _1, long _2) {
/* 19 */     return new Tuple2$mcZJ$sp(_1, _2);
/*    */   }
/*    */   
/*    */   public Tuple2<Object, Object> apply$mCZc$sp(char _1, boolean _2) {
/* 19 */     return new Tuple2$mcCZ$sp(_1, _2);
/*    */   }
/*    */   
/*    */   public Tuple2<Object, Object> apply$mCCc$sp(char _1, char _2) {
/* 19 */     return new Tuple2$mcCC$sp(_1, _2);
/*    */   }
/*    */   
/*    */   public Tuple2<Object, Object> apply$mCDc$sp(char _1, double _2) {
/* 19 */     return new Tuple2$mcCD$sp(_1, _2);
/*    */   }
/*    */   
/*    */   public Tuple2<Object, Object> apply$mCIc$sp(char _1, int _2) {
/* 19 */     return new Tuple2$mcCI$sp(_1, _2);
/*    */   }
/*    */   
/*    */   public Tuple2<Object, Object> apply$mCJc$sp(char _1, long _2) {
/* 19 */     return new Tuple2$mcCJ$sp(_1, _2);
/*    */   }
/*    */   
/*    */   public Tuple2<Object, Object> apply$mDZc$sp(double _1, boolean _2) {
/* 19 */     return new Tuple2$mcDZ$sp(_1, _2);
/*    */   }
/*    */   
/*    */   public Tuple2<Object, Object> apply$mDCc$sp(double _1, char _2) {
/* 19 */     return new Tuple2$mcDC$sp(_1, _2);
/*    */   }
/*    */   
/*    */   public Tuple2<Object, Object> apply$mDDc$sp(double _1, double _2) {
/* 19 */     return new Tuple2$mcDD$sp(_1, _2);
/*    */   }
/*    */   
/*    */   public Tuple2<Object, Object> apply$mDIc$sp(double _1, int _2) {
/* 19 */     return new Tuple2$mcDI$sp(_1, _2);
/*    */   }
/*    */   
/*    */   public Tuple2<Object, Object> apply$mDJc$sp(double _1, long _2) {
/* 19 */     return new Tuple2$mcDJ$sp(_1, _2);
/*    */   }
/*    */   
/*    */   public Tuple2<Object, Object> apply$mIZc$sp(int _1, boolean _2) {
/* 19 */     return new Tuple2$mcIZ$sp(_1, _2);
/*    */   }
/*    */   
/*    */   public Tuple2<Object, Object> apply$mICc$sp(int _1, char _2) {
/* 19 */     return new Tuple2$mcIC$sp(_1, _2);
/*    */   }
/*    */   
/*    */   public Tuple2<Object, Object> apply$mIDc$sp(int _1, double _2) {
/* 19 */     return new Tuple2$mcID$sp(_1, _2);
/*    */   }
/*    */   
/*    */   public Tuple2<Object, Object> apply$mIIc$sp(int _1, int _2) {
/* 19 */     return new Tuple2$mcII$sp(_1, _2);
/*    */   }
/*    */   
/*    */   public Tuple2<Object, Object> apply$mIJc$sp(int _1, long _2) {
/* 19 */     return new Tuple2$mcIJ$sp(_1, _2);
/*    */   }
/*    */   
/*    */   public Tuple2<Object, Object> apply$mJZc$sp(long _1, boolean _2) {
/* 19 */     return new Tuple2$mcJZ$sp(_1, _2);
/*    */   }
/*    */   
/*    */   public Tuple2<Object, Object> apply$mJCc$sp(long _1, char _2) {
/* 19 */     return new Tuple2$mcJC$sp(_1, _2);
/*    */   }
/*    */   
/*    */   public Tuple2<Object, Object> apply$mJDc$sp(long _1, double _2) {
/* 19 */     return new Tuple2$mcJD$sp(_1, _2);
/*    */   }
/*    */   
/*    */   public Tuple2<Object, Object> apply$mJIc$sp(long _1, int _2) {
/* 19 */     return new Tuple2$mcJI$sp(_1, _2);
/*    */   }
/*    */   
/*    */   public Tuple2<Object, Object> apply$mJJc$sp(long _1, long _2) {
/* 19 */     return new Tuple2$mcJJ$sp(_1, _2);
/*    */   }
/*    */   
/*    */   public Option<Tuple2<Object, Object>> unapply$mZZc$sp(Tuple2 x$0) {
/* 19 */     return (x$0 == null) ? None$.MODULE$ : new Some<Tuple2<Object, Object>>(new Tuple2$mcZZ$sp(x$0._1$mcZ$sp(), x$0._2$mcZ$sp()));
/*    */   }
/*    */   
/*    */   public Option<Tuple2<Object, Object>> unapply$mZCc$sp(Tuple2 x$0) {
/* 19 */     return (x$0 == null) ? None$.MODULE$ : new Some<Tuple2<Object, Object>>(new Tuple2$mcZC$sp(x$0._1$mcZ$sp(), x$0._2$mcC$sp()));
/*    */   }
/*    */   
/*    */   public Option<Tuple2<Object, Object>> unapply$mZDc$sp(Tuple2 x$0) {
/* 19 */     return (x$0 == null) ? None$.MODULE$ : new Some<Tuple2<Object, Object>>(new Tuple2$mcZD$sp(x$0._1$mcZ$sp(), x$0._2$mcD$sp()));
/*    */   }
/*    */   
/*    */   public Option<Tuple2<Object, Object>> unapply$mZIc$sp(Tuple2 x$0) {
/* 19 */     return (x$0 == null) ? None$.MODULE$ : new Some<Tuple2<Object, Object>>(new Tuple2$mcZI$sp(x$0._1$mcZ$sp(), x$0._2$mcI$sp()));
/*    */   }
/*    */   
/*    */   public Option<Tuple2<Object, Object>> unapply$mZJc$sp(Tuple2 x$0) {
/* 19 */     return (x$0 == null) ? None$.MODULE$ : new Some<Tuple2<Object, Object>>(new Tuple2$mcZJ$sp(x$0._1$mcZ$sp(), x$0._2$mcJ$sp()));
/*    */   }
/*    */   
/*    */   public Option<Tuple2<Object, Object>> unapply$mCZc$sp(Tuple2 x$0) {
/* 19 */     return (x$0 == null) ? None$.MODULE$ : new Some<Tuple2<Object, Object>>(new Tuple2$mcCZ$sp(x$0._1$mcC$sp(), x$0._2$mcZ$sp()));
/*    */   }
/*    */   
/*    */   public Option<Tuple2<Object, Object>> unapply$mCCc$sp(Tuple2 x$0) {
/* 19 */     return (x$0 == null) ? None$.MODULE$ : new Some<Tuple2<Object, Object>>(new Tuple2$mcCC$sp(x$0._1$mcC$sp(), x$0._2$mcC$sp()));
/*    */   }
/*    */   
/*    */   public Option<Tuple2<Object, Object>> unapply$mCDc$sp(Tuple2 x$0) {
/* 19 */     return (x$0 == null) ? None$.MODULE$ : new Some<Tuple2<Object, Object>>(new Tuple2$mcCD$sp(x$0._1$mcC$sp(), x$0._2$mcD$sp()));
/*    */   }
/*    */   
/*    */   public Option<Tuple2<Object, Object>> unapply$mCIc$sp(Tuple2 x$0) {
/* 19 */     return (x$0 == null) ? None$.MODULE$ : new Some<Tuple2<Object, Object>>(new Tuple2$mcCI$sp(x$0._1$mcC$sp(), x$0._2$mcI$sp()));
/*    */   }
/*    */   
/*    */   public Option<Tuple2<Object, Object>> unapply$mCJc$sp(Tuple2 x$0) {
/* 19 */     return (x$0 == null) ? None$.MODULE$ : new Some<Tuple2<Object, Object>>(new Tuple2$mcCJ$sp(x$0._1$mcC$sp(), x$0._2$mcJ$sp()));
/*    */   }
/*    */   
/*    */   public Option<Tuple2<Object, Object>> unapply$mDZc$sp(Tuple2 x$0) {
/* 19 */     return (x$0 == null) ? None$.MODULE$ : new Some<Tuple2<Object, Object>>(new Tuple2$mcDZ$sp(x$0._1$mcD$sp(), x$0._2$mcZ$sp()));
/*    */   }
/*    */   
/*    */   public Option<Tuple2<Object, Object>> unapply$mDCc$sp(Tuple2 x$0) {
/* 19 */     return (x$0 == null) ? None$.MODULE$ : new Some<Tuple2<Object, Object>>(new Tuple2$mcDC$sp(x$0._1$mcD$sp(), x$0._2$mcC$sp()));
/*    */   }
/*    */   
/*    */   public Option<Tuple2<Object, Object>> unapply$mDDc$sp(Tuple2 x$0) {
/* 19 */     return (x$0 == null) ? None$.MODULE$ : new Some<Tuple2<Object, Object>>(new Tuple2$mcDD$sp(x$0._1$mcD$sp(), x$0._2$mcD$sp()));
/*    */   }
/*    */   
/*    */   public Option<Tuple2<Object, Object>> unapply$mDIc$sp(Tuple2 x$0) {
/* 19 */     return (x$0 == null) ? None$.MODULE$ : new Some<Tuple2<Object, Object>>(new Tuple2$mcDI$sp(x$0._1$mcD$sp(), x$0._2$mcI$sp()));
/*    */   }
/*    */   
/*    */   public Option<Tuple2<Object, Object>> unapply$mDJc$sp(Tuple2 x$0) {
/* 19 */     return (x$0 == null) ? None$.MODULE$ : new Some<Tuple2<Object, Object>>(new Tuple2$mcDJ$sp(x$0._1$mcD$sp(), x$0._2$mcJ$sp()));
/*    */   }
/*    */   
/*    */   public Option<Tuple2<Object, Object>> unapply$mIZc$sp(Tuple2 x$0) {
/* 19 */     return (x$0 == null) ? None$.MODULE$ : new Some<Tuple2<Object, Object>>(new Tuple2$mcIZ$sp(x$0._1$mcI$sp(), x$0._2$mcZ$sp()));
/*    */   }
/*    */   
/*    */   public Option<Tuple2<Object, Object>> unapply$mICc$sp(Tuple2 x$0) {
/* 19 */     return (x$0 == null) ? None$.MODULE$ : new Some<Tuple2<Object, Object>>(new Tuple2$mcIC$sp(x$0._1$mcI$sp(), x$0._2$mcC$sp()));
/*    */   }
/*    */   
/*    */   public Option<Tuple2<Object, Object>> unapply$mIDc$sp(Tuple2 x$0) {
/* 19 */     return (x$0 == null) ? None$.MODULE$ : new Some<Tuple2<Object, Object>>(new Tuple2$mcID$sp(x$0._1$mcI$sp(), x$0._2$mcD$sp()));
/*    */   }
/*    */   
/*    */   public Option<Tuple2<Object, Object>> unapply$mIIc$sp(Tuple2 x$0) {
/* 19 */     return (x$0 == null) ? None$.MODULE$ : new Some<Tuple2<Object, Object>>(new Tuple2$mcII$sp(x$0._1$mcI$sp(), x$0._2$mcI$sp()));
/*    */   }
/*    */   
/*    */   public Option<Tuple2<Object, Object>> unapply$mIJc$sp(Tuple2 x$0) {
/* 19 */     return (x$0 == null) ? None$.MODULE$ : new Some<Tuple2<Object, Object>>(new Tuple2$mcIJ$sp(x$0._1$mcI$sp(), x$0._2$mcJ$sp()));
/*    */   }
/*    */   
/*    */   public Option<Tuple2<Object, Object>> unapply$mJZc$sp(Tuple2 x$0) {
/* 19 */     return (x$0 == null) ? None$.MODULE$ : new Some<Tuple2<Object, Object>>(new Tuple2$mcJZ$sp(x$0._1$mcJ$sp(), x$0._2$mcZ$sp()));
/*    */   }
/*    */   
/*    */   public Option<Tuple2<Object, Object>> unapply$mJCc$sp(Tuple2 x$0) {
/* 19 */     return (x$0 == null) ? None$.MODULE$ : new Some<Tuple2<Object, Object>>(new Tuple2$mcJC$sp(x$0._1$mcJ$sp(), x$0._2$mcC$sp()));
/*    */   }
/*    */   
/*    */   public Option<Tuple2<Object, Object>> unapply$mJDc$sp(Tuple2 x$0) {
/* 19 */     return (x$0 == null) ? None$.MODULE$ : new Some<Tuple2<Object, Object>>(new Tuple2$mcJD$sp(x$0._1$mcJ$sp(), x$0._2$mcD$sp()));
/*    */   }
/*    */   
/*    */   public Option<Tuple2<Object, Object>> unapply$mJIc$sp(Tuple2 x$0) {
/* 19 */     return (x$0 == null) ? None$.MODULE$ : new Some<Tuple2<Object, Object>>(new Tuple2$mcJI$sp(x$0._1$mcJ$sp(), x$0._2$mcI$sp()));
/*    */   }
/*    */   
/*    */   public Option<Tuple2<Object, Object>> unapply$mJJc$sp(Tuple2 x$0) {
/* 19 */     return (x$0 == null) ? None$.MODULE$ : new Some<Tuple2<Object, Object>>(new Tuple2$mcJJ$sp(x$0._1$mcJ$sp(), x$0._2$mcJ$sp()));
/*    */   }
/*    */   
/*    */   private Tuple2$() {
/* 19 */     MODULE$ = this;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\Tuple2$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */