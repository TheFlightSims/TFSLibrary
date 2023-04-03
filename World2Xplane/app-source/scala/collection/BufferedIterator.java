package scala.collection;

import scala.reflect.ScalaSignature;

@ScalaSignature(bytes = "\006\001%2q!\001\002\021\002\007\005qA\001\tCk\0324WM]3e\023R,'/\031;pe*\0211\001B\001\013G>dG.Z2uS>t'\"A\003\002\013M\034\027\r\\1\004\001U\021\001bE\n\004\001%i\001C\001\006\f\033\005!\021B\001\007\005\005\031\te.\037*fMB\031abD\t\016\003\tI!\001\005\002\003\021%#XM]1u_J\004\"AE\n\r\001\0211A\003\001CC\002U\021\021!Q\t\003-e\001\"AC\f\n\005a!!a\002(pi\"Lgn\032\t\003\025iI!a\007\003\003\007\005s\027\020C\003\036\001\021\005a$\001\004%S:LG\017\n\013\002?A\021!\002I\005\003C\021\021A!\0268ji\")1\005\001D\001I\005!\001.Z1e+\005\t\002\"\002\024\001\t\003:\023\001\0032vM\032,'/\0323\026\003!j\021\001\001")
public interface BufferedIterator<A> extends Iterator<A> {
  A head();
  
  BufferedIterator<A> buffered();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\BufferedIterator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */