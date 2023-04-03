/*    */ package scala.util.parsing.input;
/*    */ 
/*    */ import scala.reflect.ScalaSignature;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001%3Q!\001\002\002\002-\021aAU3bI\026\024(BA\002\005\003\025Ig\016];u\025\t)a!A\004qCJ\034\030N\\4\013\005\035A\021\001B;uS2T\021!C\001\006g\016\fG.Y\002\001+\ta\001d\005\002\001\033A\021abD\007\002\021%\021\001\003\003\002\007\003:L(+\0324\t\013I\001A\021A\n\002\rqJg.\033;?)\005!\002cA\013\001-5\t!\001\005\002\03011\001AAB\r\001\t\013\007!DA\001U#\tYb\004\005\002\0179%\021Q\004\003\002\b\035>$\b.\0338h!\tqq$\003\002!\021\t\031\021I\\=\t\013\t\002A\021A\022\002\rM|WO]2f+\005!\003CA\023+\033\0051#BA\024)\003\021a\027M\\4\013\003%\nAA[1wC&\0211F\n\002\r\007\"\f'oU3rk\026t7-\032\005\006[\001!\tAL\001\007_\03247/\032;\026\003=\002\"A\004\031\n\005EB!aA%oi\")1\007\001D\001i\005)a-\033:tiV\ta\003C\0037\001\031\005q'\001\003sKN$X#\001\013\t\013e\002A\021\001\036\002\t\021\024x\016\035\013\003)mBQ\001\020\035A\002=\n\021A\034\005\006}\0011\taP\001\004a>\034X#\001!\021\005U\t\025B\001\"\003\005!\001vn]5uS>t\007\"\002#\001\r\003)\025!B1u\013:$W#\001$\021\00599\025B\001%\t\005\035\021un\0347fC:\004")
/*    */ public abstract class Reader<T> {
/*    */   public CharSequence source() {
/* 27 */     throw new NoSuchMethodError("not a char sequence reader");
/*    */   }
/*    */   
/*    */   public int offset() {
/* 30 */     throw new NoSuchMethodError("not a char sequence reader");
/*    */   }
/*    */   
/*    */   public abstract T first();
/*    */   
/*    */   public abstract Reader<T> rest();
/*    */   
/*    */   public Reader<T> drop(int n) {
/* 46 */     Reader<T> r = this;
/* 47 */     int cnt = n;
/* 48 */     while (cnt > 0) {
/* 49 */       r = r.rest();
/* 49 */       cnt--;
/*    */     } 
/* 51 */     return r;
/*    */   }
/*    */   
/*    */   public abstract Position pos();
/*    */   
/*    */   public abstract boolean atEnd();
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scal\\util\parsing\input\Reader.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */