package scala.concurrent;

import scala.Function0;
import scala.reflect.ScalaSignature;

@ScalaSignature(bytes = "\006\001\021<Q!\001\002\t\002\035\tq\001]1dW\006<WM\003\002\004\t\005Q1m\0348dkJ\024XM\034;\013\003\025\tQa]2bY\006\034\001\001\005\002\t\0235\t!AB\003\013\005!\0051BA\004qC\016\\\027mZ3\024\005%a\001CA\007\017\033\005!\021BA\b\005\005\031\te.\037*fM\")\021#\003C\001%\0051A(\0338jiz\"\022aB\003\005)%\001QC\001\nFq\026\034W\017^5p]\026C8-\0329uS>t\007C\001\f\035\033\0059\"BA\002\031\025\tI\"$\001\003vi&d'\"A\016\002\t)\fg/Y\005\003)])AAH\005\001?\t)2)\0318dK2d\027\r^5p]\026C8-\0329uS>t\007C\001\f!\023\tqr#\002\003#\023\001\031#\001\005+j[\026|W\017^#yG\026\004H/[8o!\t1B%\003\002#/!)a%\003C\001O\0051a-\036;ve\026,\"\001K\030\025\005%jDC\001\0269!\rA1&L\005\003Y\t\021aAR;ukJ,\007C\001\0300\031\001!Q\001M\023C\002E\022\021\001V\t\003eU\002\"!D\032\n\005Q\"!a\002(pi\"Lgn\032\t\003\033YJ!a\016\003\003\007\005s\027\020C\003:K\001\017!(A\004fq\026\0347\r\036=\021\005!Y\024B\001\037\003\005A)\0050Z2vi&|gnQ8oi\026DH\017\003\004?K\021\005\raP\001\005E>$\027\020E\002\016\0016J!!\021\003\003\021q\022\027P\\1nKzBQaQ\005\005\002\021\013q\001\035:p[&\034X-\006\002F\025R\ta\tE\002\t\017&K!\001\023\002\003\017A\023x.\\5tKB\021aF\023\003\006a\t\023\r!\r\005\006\031&!\t!T\001\tE2|7m[5oOV\021a\n\025\013\003\037F\003\"A\f)\005\013AZ%\031A\031\t\ryZE\0211\001S!\ri\001i\024\025\004\027R\003\007cA\007V/&\021a\013\002\002\007i\"\024xn^:\021\0059BF!\002\031\001\005\004I\026C\001\032[!\tYVL\004\002\0169&\021\021\001B\005\003=~\023\021\002\0265s_^\f'\r\\3\013\005\005!1%A1\021\005m\023\027BA2`\005%)\005pY3qi&|g\016")
public final class package {
  public static <T> T blocking(Function0<T> paramFunction0) throws Exception {
    return package$.MODULE$.blocking(paramFunction0);
  }
  
  public static <T> Promise<T> promise() {
    return package$.MODULE$.promise();
  }
  
  public static <T> Future<T> future(Function0<T> paramFunction0, ExecutionContext paramExecutionContext) {
    return package$.MODULE$.future(paramFunction0, paramExecutionContext);
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\concurrent\package.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */