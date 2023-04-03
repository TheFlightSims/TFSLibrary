package scala.collection.parallel;

import scala.Function0;
import scala.collection.Seq;
import scala.collection.mutable.ArrayBuffer;
import scala.reflect.ScalaSignature;

@ScalaSignature(bytes = "\006\001\0055aaB\001\003!\003\r\t!\003\002\006)\006\0348n\035\006\003\007\021\t\001\002]1sC2dW\r\034\006\003\013\031\t!bY8mY\026\034G/[8o\025\0059\021!B:dC2\f7\001A\n\003\001)\001\"a\003\007\016\003\031I!!\004\004\003\r\005s\027PU3g\021\025y\001\001\"\001\021\003\031!\023N\\5uIQ\t\021\003\005\002\f%%\0211C\002\002\005+:LG\017\003\005\026\001\t\007I\021\001\002\027\0035!WMY;h\033\026\0348/Y4fgV\tq\003E\002\0317ui\021!\007\006\0035\021\tq!\\;uC\ndW-\003\002\0353\tY\021I\035:bs\n+hMZ3s!\tq\022E\004\002\f?%\021\001EB\001\007!J,G-\0324\n\005\t\032#AB*ue&twM\003\002!\r!1Q\005\001Q\001\n]\ta\002Z3ck\036lUm]:bO\026\034\b\005\003\004(\001\021\005!\001K\001\tI\026\024Wo\0327pOR\021q#\013\005\006U\031\002\r!H\001\002g\0329A\006\001I\001\004\003i#aC,sCB\004X\r\032+bg.,2AL\035D'\tY#\002C\003\020W\021\005\001\003C\0042W\t\007i\021\001\032\002\t\t|G-_\013\002gA!A'N\034C\033\005\021\021B\001\034\003\005\021!\026m]6\021\005aJD\002\001\003\006u-\022\ra\017\002\002%F\021Ah\020\t\003\027uJ!A\020\004\003\0179{G\017[5oOB\0211\002Q\005\003\003\032\0211!\0218z!\tA4\t\002\004EW\021\025\ra\017\002\003)BDQAR\026\007\002\035\013Qa\0359mSR,\022\001\023\t\004\023F#fB\001&P\035\tYe*D\001M\025\ti\005\"\001\004=e>|GOP\005\002\017%\021\001KB\001\ba\006\0347.Y4f\023\t\0216KA\002TKFT!\001\025\004\021\tU[sGQ\007\002\001!)qk\013D\001!\00591m\\7qkR,\007\"B-,\r\003\001\022!B:uCJ$\b\"B.,\r\003\001\022\001B:z]\016DQ!X\026\007\002y\013\021\002\036:z\007\006t7-\0327\025\003}\003\"a\0031\n\005\0054!a\002\"p_2,\027M\034\005\006G.\"\t\001E\001\be\026dW-Y:f\021\035)\007A1A\007\002\031\f1\"\0328wSJ|g.\\3oiV\t!\002C\003i\001\031\005\021.A\004fq\026\034W\017^3\026\007)|G\017\006\002laB\0311\002\0348\n\00554!!\003$v]\016$\030n\03481!\tAt\016B\003;O\n\0071\bC\003rO\002\007!/\001\004gUR\f7o\033\t\005iUr7\017\005\0029i\022)Ai\032b\001w!)a\017\001D\001o\006!R\r_3dkR,\027I\0343XC&$(+Z:vYR,2\001\037>\000)\tI8\020\005\0029u\022)!(\036b\001w!)A0\036a\001{\006!A/Y:l!\021!T'\037@\021\005azH!\002#v\005\004Y\004bBA\002\001\031\005\021QA\001\021a\006\024\030\r\0347fY&\034X\016T3wK2,\"!a\002\021\007-\tI!C\002\002\f\031\0211!\0238u\001")
public interface Tasks {
  void scala$collection$parallel$Tasks$_setter_$debugMessages_$eq(ArrayBuffer paramArrayBuffer);
  
  ArrayBuffer<String> debugMessages();
  
  ArrayBuffer<String> debuglog(String paramString);
  
  Object environment();
  
  <R, Tp> Function0<R> execute(Task<R, Tp> paramTask);
  
  <R, Tp> R executeAndWaitResult(Task<R, Tp> paramTask);
  
  int parallelismLevel();
  
  public interface WrappedTask<R, Tp> {
    Task<R, Tp> body();
    
    Seq<WrappedTask<R, Tp>> split();
    
    void compute();
    
    void start();
    
    void sync();
    
    boolean tryCancel();
    
    void release();
  }
  
  public abstract class WrappedTask$class {
    public static void $init$(Tasks.WrappedTask $this) {}
    
    public static void release(Tasks.WrappedTask $this) {}
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\parallel\Tasks.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */