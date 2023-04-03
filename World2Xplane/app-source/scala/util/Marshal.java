package scala.util;

import java.io.IOException;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;

@ScalaSignature(bytes = "\006\001e<Q!\001\002\t\002\035\tq!T1sg\"\fGN\003\002\004\t\005!Q\017^5m\025\005)\021!B:dC2\f7\001\001\t\003\021%i\021A\001\004\006\025\tA\ta\003\002\b\033\006\0248\017[1m'\tIA\002\005\002\016\0355\tA!\003\002\020\t\t1\021I\\=SK\032DQ!E\005\005\002I\ta\001P5oSRtD#A\004\t\013QIA\021A\013\002\t\021,X\016]\013\003-!\"\"aF\031\025\005aq\002cA\007\0327%\021!\004\002\002\006\003J\024\030-\037\t\003\033qI!!\b\003\003\t\tKH/\032\005\006?M\001\035\001I\001\002iB\031\021\005\n\024\016\003\tR!a\t\003\002\017I,g\r\\3di&\021QE\t\002\t\0072\f7o\035+bOB\021q\005\013\007\001\t\025I3C1\001+\005\005\t\025CA\026/!\tiA&\003\002.\t\t9aj\034;iS:<\007CA\0070\023\t\001DAA\002B]fDQAM\nA\002\031\n\021a\034\005\006i%!\t!N\001\005Y>\fG-\006\0027sQ\021q'\020\013\003qi\002\"aJ\035\005\013%\032$\031\001\026\t\013m\032\0049\001\037\002\021\025D\b/Z2uK\022\0042!\t\0239\021\025q4\0071\001\031\003\031\021WO\0324fe\"\0321\007\021(\021\0075\t5)\003\002C\t\t1A\017\033:poN\004\"a\n#\005\013\025\003!\031\001$\003\003Q\013\"aK$\021\005![eBA\007J\023\tQE!A\004qC\016\\\027mZ3\n\0051k%!\003+ie><\030M\0317f\025\tQEaI\001P!\t\001V+D\001R\025\t\0216+\001\003mC:<'\"\001+\002\t)\fg/Y\005\003-F\023ac\0217bgNtu\016\036$pk:$W\t_2faRLwN\034\025\004ga[\006cA\007B3B\021qE\027\003\006\013\002\021\rAR\022\0029B\021Ql\031\b\003=&s!a\0302\016\003\001T!!\031\004\002\rq\022xn\034;?\023\005)\021B\0013N\005I\031E.Y:t\007\006\034H/\022=dKB$\030n\0348)\007M2\027\016E\002\016\003\036\004\"a\n5\005\013\025\003!\031\001$$\003)\004\"a\0338\016\0031T!!\\*\002\005%|\027BA8m\005-Iu*\022=dKB$\030n\0348)\t%\tHO\036\t\003\033IL!a\035\003\003\025\021,\007O]3dCR,G-I\001v\003i!\006.[:!G2\f7o\035\021xS2d\007EY3!e\026lwN^3eC\0059\030A\002\032/cAr\003\007\013\003\001cR4\b")
public final class Marshal {
  public static <A> A load(byte[] paramArrayOfbyte, ClassTag<A> paramClassTag) throws IOException, ClassCastException, ClassNotFoundException {
    return Marshal$.MODULE$.load(paramArrayOfbyte, paramClassTag);
  }
  
  public static <A> byte[] dump(A paramA, ClassTag<A> paramClassTag) {
    return Marshal$.MODULE$.dump(paramA, paramClassTag);
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scal\\util\Marshal.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */