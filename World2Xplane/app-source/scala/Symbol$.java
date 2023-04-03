/*    */ package scala;
/*    */ 
/*    */ public final class Symbol$ extends UniquenessCache<String, Symbol> implements Serializable {
/*    */   public static final Symbol$ MODULE$;
/*    */   
/*    */   private Object readResolve() {
/* 34 */     return MODULE$;
/*    */   }
/*    */   
/*    */   private Symbol$() {
/* 34 */     MODULE$ = this;
/*    */   }
/*    */   
/*    */   public Symbol apply(String name) {
/* 35 */     return super.apply(name);
/*    */   }
/*    */   
/*    */   public Symbol valueFromKey(String name) {
/* 36 */     return new Symbol(name);
/*    */   }
/*    */   
/*    */   public Option<String> keyFromValue(Symbol sym) {
/* 37 */     return new Some<String>(sym.name());
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\Symbol$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */