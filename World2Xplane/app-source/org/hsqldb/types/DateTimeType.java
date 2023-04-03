package org.hsqldb.types;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import org.hsqldb.HsqlDateTime;
import org.hsqldb.HsqlException;
import org.hsqldb.Session;
import org.hsqldb.SessionInterface;
import org.hsqldb.error.Error;
import org.hsqldb.lib.StringConverter;

public final class DateTimeType extends DTIType {
  public final boolean withTimeZone;
  
  private String nameString;
  
  public static final long epochSeconds = HsqlDateTime.getDateSeconds("1-01-01");
  
  public static final TimestampData epochTimestamp = new TimestampData(epochSeconds);
  
  public DateTimeType(int paramInt1, int paramInt2, int paramInt3) {
    super(paramInt1, paramInt2, 0L, paramInt3);
    this.withTimeZone = (paramInt2 == 94 || paramInt2 == 95);
    this.nameString = getNameStringPrivate();
  }
  
  public int displaySize() {
    switch (this.typeCode) {
      case 91:
        return 10;
      case 92:
        return 8 + ((this.scale == 0) ? 0 : (this.scale + 1));
      case 94:
        return 8 + ((this.scale == 0) ? 0 : (this.scale + 1)) + 6;
      case 93:
        return 19 + ((this.scale == 0) ? 0 : (this.scale + 1));
      case 95:
        return 19 + ((this.scale == 0) ? 0 : (this.scale + 1)) + 6;
    } 
    throw Error.runtimeError(201, "DateTimeType");
  }
  
  public int getJDBCTypeCode() {
    return this.typeCode;
  }
  
  public Class getJDBCClass() {
    switch (this.typeCode) {
      case 91:
        return Date.class;
      case 92:
      case 94:
        return Time.class;
      case 93:
      case 95:
        return Timestamp.class;
    } 
    throw Error.runtimeError(201, "DateTimeType");
  }
  
  public String getJDBCClassName() {
    switch (this.typeCode) {
      case 91:
        return "java.sql.Date";
      case 92:
      case 94:
        return "java.sql.Time";
      case 93:
      case 95:
        return "java.sql.Timestamp";
    } 
    throw Error.runtimeError(201, "DateTimeType");
  }
  
  public int getJDBCPrecision() {
    return displaySize();
  }
  
  public int getSQLGenericTypeCode() {
    return 9;
  }
  
  public String getNameString() {
    return this.nameString;
  }
  
  private String getNameStringPrivate() {
    switch (this.typeCode) {
      case 91:
        return "DATE";
      case 92:
        return "TIME";
      case 94:
        return "TIME WITH TIME ZONE";
      case 93:
        return "TIMESTAMP";
      case 95:
        return "TIMESTAMP WITH TIME ZONE";
    } 
    throw Error.runtimeError(201, "DateTimeType");
  }
  
  public String getDefinition() {
    String str;
    switch (this.typeCode) {
      case 91:
        return "DATE";
      case 92:
      case 94:
        if (this.scale == 0)
          return getNameString(); 
        str = "TIME";
        break;
      case 93:
      case 95:
        if (this.scale == 6)
          return getNameString(); 
        str = "TIMESTAMP";
        break;
      default:
        throw Error.runtimeError(201, "DateTimeType");
    } 
    StringBuffer stringBuffer = new StringBuffer(16);
    stringBuffer.append(str);
    stringBuffer.append('(');
    stringBuffer.append(this.scale);
    stringBuffer.append(')');
    if (this.withTimeZone)
      stringBuffer.append(" WITH TIME ZONE"); 
    return stringBuffer.toString();
  }
  
  public boolean isDateTimeType() {
    return true;
  }
  
  public boolean isDateOrTimestampType() {
    switch (this.typeCode) {
      case 91:
      case 93:
      case 95:
        return true;
      case 92:
      case 94:
        return false;
    } 
    throw Error.runtimeError(201, "DateTimeType");
  }
  
  public boolean isDateTimeTypeWithZone() {
    return this.withTimeZone;
  }
  
  public boolean acceptsFractionalPrecision() {
    return (this.typeCode != 91);
  }
  
  public Type getAggregateType(Type paramType) {
    if (paramType == null)
      return this; 
    if (paramType == SQL_ALL_TYPES)
      return this; 
    if (this.typeCode == paramType.typeCode)
      return (this.scale >= paramType.scale) ? this : paramType; 
    if (paramType.typeCode == 0)
      return this; 
    if (paramType.isCharacterType())
      return paramType.getAggregateType(this); 
    if (!paramType.isDateTimeType())
      throw Error.error(5562); 
    DateTimeType dateTimeType = (DateTimeType)paramType;
    if (dateTimeType.startIntervalType > this.endIntervalType || this.startIntervalType > dateTimeType.endIntervalType)
      throw Error.error(5562); 
    int i = this.typeCode;
    int j = (this.scale > dateTimeType.scale) ? this.scale : dateTimeType.scale;
    boolean bool = (this.withTimeZone || dateTimeType.withTimeZone) ? true : false;
    int k = (dateTimeType.startIntervalType > this.startIntervalType) ? this.startIntervalType : dateTimeType.startIntervalType;
    if (k == 104) {
      i = bool ? 94 : 92;
    } else {
      i = bool ? 95 : 93;
    } 
    return getDateTimeType(i, j);
  }
  
  public Type getCombinedType(Session paramSession, Type paramType, int paramInt) {
    DateTimeType dateTimeType;
    int i;
    int j;
    boolean bool;
    int k;
    switch (paramInt) {
      case 40:
      case 41:
      case 43:
      case 44:
      case 45:
      case 46:
        if (this.typeCode == paramType.typeCode)
          return this; 
        if (paramType.typeCode == 0)
          return this; 
        if (!paramType.isDateTimeType())
          throw Error.error(5562); 
        dateTimeType = (DateTimeType)paramType;
        if (dateTimeType.startIntervalType > this.endIntervalType || this.startIntervalType > dateTimeType.endIntervalType)
          throw Error.error(5562); 
        i = this.typeCode;
        j = (this.scale > dateTimeType.scale) ? this.scale : dateTimeType.scale;
        bool = (this.withTimeZone || dateTimeType.withTimeZone) ? true : false;
        k = (dateTimeType.startIntervalType > this.startIntervalType) ? this.startIntervalType : dateTimeType.startIntervalType;
        if (k == 104) {
          i = bool ? 94 : 92;
        } else {
          i = bool ? 95 : 93;
        } 
        return getDateTimeType(i, j);
      case 32:
      case 33:
        if (paramType.isIntervalType())
          return (this.typeCode != 91 && paramType.scale > this.scale) ? getDateTimeType(this.typeCode, paramType.scale) : this; 
        if (paramType.isDateTimeType()) {
          if (paramInt == 33 && paramType.typeComparisonGroup == this.typeComparisonGroup)
            return (this.typeCode == 91) ? Type.SQL_INTERVAL_DAY_MAX_PRECISION : Type.SQL_INTERVAL_DAY_TO_SECOND_MAX_PRECISION; 
          break;
        } 
        if (paramType.isNumberType())
          return this; 
        break;
    } 
    throw Error.error(5562);
  }
  
  public int compare(Session paramSession, Object paramObject1, Object paramObject2) {
    long l;
    if (paramObject1 == paramObject2)
      return 0; 
    if (paramObject1 == null)
      return -1; 
    if (paramObject2 == null)
      return 1; 
    switch (this.typeCode) {
      case 92:
      case 94:
        l = (((TimeData)paramObject1).getSeconds() - ((TimeData)paramObject2).getSeconds());
        if (l == 0L)
          l = (((TimeData)paramObject1).getNanos() - ((TimeData)paramObject2).getNanos()); 
        return (l == 0L) ? 0 : ((l > 0L) ? 1 : -1);
      case 91:
      case 93:
      case 95:
        l = ((TimestampData)paramObject1).getSeconds() - ((TimestampData)paramObject2).getSeconds();
        if (l == 0L)
          l = (((TimestampData)paramObject1).getNanos() - ((TimestampData)paramObject2).getNanos()); 
        return (l == 0L) ? 0 : ((l > 0L) ? 1 : -1);
    } 
    throw Error.runtimeError(201, "DateTimeType");
  }
  
  public Object convertToTypeLimits(SessionInterface paramSessionInterface, Object paramObject) {
    TimeData timeData;
    TimestampData timestampData;
    int i;
    int j;
    if (paramObject == null)
      return null; 
    if (this.scale == 9)
      return paramObject; 
    switch (this.typeCode) {
      case 91:
        return paramObject;
      case 92:
      case 94:
        timeData = (TimeData)paramObject;
        i = timeData.getNanos();
        j = scaleNanos(i);
        return (j == i) ? timeData : new TimeData(timeData.getSeconds(), j, timeData.getZone());
      case 93:
      case 95:
        timestampData = (TimestampData)paramObject;
        i = timestampData.getNanos();
        j = scaleNanos(i);
        return (j == i) ? timestampData : new TimestampData(timestampData.getSeconds(), j, timestampData.getZone());
    } 
    throw Error.runtimeError(201, "DateTimeType");
  }
  
  int scaleNanos(int paramInt) {
    int i = nanoScaleFactors[this.scale];
    return paramInt / i * i;
  }
  
  public Object convertToType(SessionInterface paramSessionInterface, Object paramObject, Type paramType) {
    long l1;
    TimeData timeData4;
    TimestampData timestampData4;
    TimeData timeData3;
    TimestampData timestampData3;
    TimeData timeData2;
    TimestampData timestampData2;
    TimeData timeData1;
    TimestampData timestampData1;
    long l2;
    long l3;
    if (paramObject == null)
      return paramObject; 
    switch (paramType.typeCode) {
      case 40:
        paramObject = paramObject.toString();
      case 1:
      case 12:
        switch (this.typeCode) {
          case 91:
          case 92:
          case 93:
          case 94:
          case 95:
            try {
              return paramSessionInterface.getScanner().convertToDatetimeInterval(paramSessionInterface, (String)paramObject, this);
            } catch (HsqlException hsqlException) {
              return convertToDatetimeSpecial(paramSessionInterface, (String)paramObject, this);
            } 
        } 
        break;
      case 91:
      case 92:
      case 93:
      case 94:
      case 95:
        break;
      default:
        throw Error.error(5561);
    } 
    switch (this.typeCode) {
      case 91:
        switch (paramType.typeCode) {
          case 91:
            return paramObject;
          case 95:
            l1 = ((TimestampData)paramObject).getSeconds() + ((TimestampData)paramObject).getZone();
            l3 = HsqlDateTime.getNormalisedDate(l1 * 1000L);
            return new TimestampData(l3 / 1000L);
          case 93:
            l1 = HsqlDateTime.getNormalisedDate(((TimestampData)paramObject).getSeconds() * 1000L);
            return new TimestampData(l1 / 1000L);
        } 
        throw Error.error(5561);
      case 94:
        switch (paramType.typeCode) {
          case 94:
            return convertToTypeLimits(paramSessionInterface, paramObject);
          case 92:
            timeData4 = (TimeData)paramObject;
            return new TimeData(timeData4.getSeconds() - paramSessionInterface.getZoneSeconds(), scaleNanos(timeData4.getNanos()), paramSessionInterface.getZoneSeconds());
          case 95:
            timestampData4 = (TimestampData)paramObject;
            l2 = HsqlDateTime.convertToNormalisedTime(timestampData4.getSeconds() * 1000L) / 1000L;
            return new TimeData((int)l2, scaleNanos(timestampData4.getNanos()), timestampData4.getZone());
          case 93:
            timestampData4 = (TimestampData)paramObject;
            l2 = timestampData4.getSeconds() - paramSessionInterface.getZoneSeconds();
            l2 = HsqlDateTime.convertToNormalisedTime(l2 * 1000L) / 1000L;
            return new TimeData((int)l2, scaleNanos(timestampData4.getNanos()), paramSessionInterface.getZoneSeconds());
        } 
        throw Error.error(5561);
      case 92:
        switch (paramType.typeCode) {
          case 94:
            timeData3 = (TimeData)paramObject;
            return new TimeData(timeData3.getSeconds() + timeData3.getZone(), scaleNanos(timeData3.getNanos()), 0);
          case 92:
            return convertToTypeLimits(paramSessionInterface, paramObject);
          case 95:
            timestampData3 = (TimestampData)paramObject;
            l2 = timestampData3.getSeconds() + timestampData3.getZone();
            l2 = HsqlDateTime.convertToNormalisedTime(l2 * 1000L) / 1000L;
            return new TimeData((int)l2, scaleNanos(timestampData3.getNanos()), 0);
          case 93:
            timestampData3 = (TimestampData)paramObject;
            l2 = HsqlDateTime.convertToNormalisedTime(timestampData3.getSeconds() * 1000L) / 1000L;
            return new TimeData((int)l2, scaleNanos(timestampData3.getNanos()));
        } 
        throw Error.error(5561);
      case 95:
        switch (paramType.typeCode) {
          case 94:
            timeData2 = (TimeData)paramObject;
            l2 = paramSessionInterface.getCurrentDate().getSeconds() + timeData2.getSeconds();
            return new TimestampData(l2, scaleNanos(timeData2.getNanos()), timeData2.getZone());
          case 92:
            timeData2 = (TimeData)paramObject;
            l2 = paramSessionInterface.getCurrentDate().getSeconds() + timeData2.getSeconds() - paramSessionInterface.getZoneSeconds();
            return new TimestampData(l2, scaleNanos(timeData2.getNanos()), paramSessionInterface.getZoneSeconds());
          case 95:
            return convertToTypeLimits(paramSessionInterface, paramObject);
          case 93:
            timestampData2 = (TimestampData)paramObject;
            l2 = timestampData2.getSeconds() - paramSessionInterface.getZoneSeconds();
            return new TimestampData(l2, scaleNanos(timestampData2.getNanos()), paramSessionInterface.getZoneSeconds());
          case 91:
            timestampData2 = (TimestampData)paramObject;
            return new TimestampData(timestampData2.getSeconds(), 0, paramSessionInterface.getZoneSeconds());
        } 
        throw Error.error(5561);
      case 93:
        switch (paramType.typeCode) {
          case 94:
            timeData1 = (TimeData)paramObject;
            l2 = paramSessionInterface.getCurrentDate().getSeconds() + timeData1.getSeconds() - paramSessionInterface.getZoneSeconds();
            return new TimestampData(l2, scaleNanos(timeData1.getNanos()), paramSessionInterface.getZoneSeconds());
          case 92:
            timeData1 = (TimeData)paramObject;
            l2 = paramSessionInterface.getCurrentDate().getSeconds() + timeData1.getSeconds();
            return new TimestampData(l2, scaleNanos(timeData1.getNanos()));
          case 95:
            timestampData1 = (TimestampData)paramObject;
            l2 = timestampData1.getSeconds() + timestampData1.getZone();
            return new TimestampData(l2, scaleNanos(timestampData1.getNanos()));
          case 93:
            return convertToTypeLimits(paramSessionInterface, paramObject);
          case 91:
            return paramObject;
        } 
        throw Error.error(5561);
    } 
    throw Error.runtimeError(201, "DateTimeType");
  }
  
  public Object convertToDefaultType(SessionInterface paramSessionInterface, Object paramObject) {
    DateTimeType dateTimeType = (paramObject instanceof TimeData) ? Type.SQL_TIME : Type.SQL_TIMESTAMP;
    return convertToType(paramSessionInterface, paramObject, dateTimeType);
  }
  
  public Object convertJavaToSQL(SessionInterface paramSessionInterface, Object paramObject) {
    if (paramObject == null)
      return null; 
    switch (this.typeCode) {
      case 92:
      case 94:
        if (!(paramObject instanceof Date) && paramObject instanceof Date) {
          int i = 0;
          int j = 0;
          if (this.typeCode == 92) {
            l = HsqlDateTime.convertMillisFromCalendar(paramSessionInterface.getCalendar(), ((Date)paramObject).getTime());
          } else {
            l = ((Date)paramObject).getTime();
            j = paramSessionInterface.getZoneSeconds();
          } 
          long l = HsqlDateTime.getNormalisedTime(l);
          if (paramObject instanceof Timestamp) {
            i = ((Timestamp)paramObject).getNanos();
            i = normaliseFraction(i, this.scale);
          } 
          return new TimeData((int)l / 1000, i, j);
        } 
        break;
      case 91:
        if (!(paramObject instanceof Time) && paramObject instanceof Date) {
          long l = HsqlDateTime.convertMillisFromCalendar(paramSessionInterface.getCalendar(), ((Date)paramObject).getTime());
          l = HsqlDateTime.getNormalisedDate(l);
          return new TimestampData(l / 1000L);
        } 
        break;
      case 93:
      case 95:
        if (!(paramObject instanceof Time) && paramObject instanceof Date) {
          long l;
          int i = 0;
          int j = 0;
          if (this.typeCode == 93) {
            l = HsqlDateTime.convertMillisFromCalendar(paramSessionInterface.getCalendar(), ((Date)paramObject).getTime());
          } else {
            l = ((Date)paramObject).getTime();
            j = HsqlDateTime.getZoneMillis(paramSessionInterface.getCalendar(), l) / 1000;
          } 
          if (paramObject instanceof Timestamp) {
            i = ((Timestamp)paramObject).getNanos();
            this;
            i = normaliseFraction(i, this.scale);
          } 
          return new TimestampData(l / 1000L, i, j);
        } 
        break;
    } 
    throw Error.error(5561);
  }
  
  public Object convertSQLToJavaGMT(SessionInterface paramSessionInterface, Object paramObject) {
    long l;
    Timestamp timestamp;
    switch (this.typeCode) {
      case 92:
      case 94:
        l = (((TimeData)paramObject).getSeconds() * 1000);
        l += (((TimeData)paramObject).getNanos() / 1000000);
        return new Time(l);
      case 91:
        l = ((TimestampData)paramObject).getSeconds() * 1000L;
        return new Date(l);
      case 93:
      case 95:
        l = ((TimestampData)paramObject).getSeconds() * 1000L;
        timestamp = new Timestamp(l);
        timestamp.setNanos(((TimestampData)paramObject).getNanos());
        return timestamp;
    } 
    throw Error.runtimeError(201, "DateTimeType");
  }
  
  public Object convertSQLToJava(SessionInterface paramSessionInterface, Object paramObject) {
    Calendar calendar2;
    int i;
    Calendar calendar1;
    long l1;
    long l2;
    Timestamp timestamp1;
    Timestamp timestamp2;
    if (paramObject == null)
      return null; 
    switch (this.typeCode) {
      case 92:
        calendar2 = paramSessionInterface.getCalendar();
        l2 = HsqlDateTime.convertMillisToCalendar(calendar2, (((TimeData)paramObject).getSeconds() * 1000));
        l2 = HsqlDateTime.getNormalisedTime(calendar2, l2);
        return new Time(l2);
      case 94:
        i = ((TimeData)paramObject).getSeconds();
        return new Time((i * 1000));
      case 91:
        calendar1 = paramSessionInterface.getCalendar();
        l2 = HsqlDateTime.convertMillisToCalendar(calendar1, ((TimestampData)paramObject).getSeconds() * 1000L);
        return new Date(l2);
      case 93:
        calendar1 = paramSessionInterface.getCalendar();
        l2 = HsqlDateTime.convertMillisToCalendar(calendar1, ((TimestampData)paramObject).getSeconds() * 1000L);
        timestamp2 = new Timestamp(l2);
        timestamp2.setNanos(((TimestampData)paramObject).getNanos());
        return timestamp2;
      case 95:
        l1 = ((TimestampData)paramObject).getSeconds();
        timestamp1 = new Timestamp(l1 * 1000L);
        timestamp1.setNanos(((TimestampData)paramObject).getNanos());
        return timestamp1;
    } 
    throw Error.runtimeError(201, "DateTimeType");
  }
  
  public static int normaliseTime(int paramInt) {
    while (paramInt < 0)
      paramInt += 86400; 
    if (paramInt > 86400)
      paramInt %= 86400; 
    return paramInt;
  }
  
  public String convertToString(Object paramObject) {
    String str;
    StringBuffer stringBuffer;
    TimeData timeData;
    TimestampData timestampData;
    int i;
    boolean bool = false;
    if (paramObject == null)
      return null; 
    switch (this.typeCode) {
      case 91:
        return HsqlDateTime.getDateString(((TimestampData)paramObject).getSeconds());
      case 92:
      case 94:
        timeData = (TimeData)paramObject;
        i = normaliseTime(timeData.getSeconds() + timeData.getZone());
        str = intervalSecondToString(i, timeData.getNanos(), false);
        if (!this.withTimeZone)
          return str; 
        stringBuffer = new StringBuffer(str);
        str = Type.SQL_INTERVAL_HOUR_TO_MINUTE.intervalSecondToString(((TimeData)paramObject).getZone(), 0, true);
        stringBuffer.append(str);
        return stringBuffer.toString();
      case 93:
      case 95:
        timestampData = (TimestampData)paramObject;
        stringBuffer = new StringBuffer();
        HsqlDateTime.getTimestampString(stringBuffer, timestampData.getSeconds() + timestampData.getZone(), timestampData.getNanos(), this.scale);
        if (!this.withTimeZone)
          return stringBuffer.toString(); 
        str = Type.SQL_INTERVAL_HOUR_TO_MINUTE.intervalSecondToString(((TimestampData)paramObject).getZone(), 0, true);
        stringBuffer.append(str);
        return stringBuffer.toString();
    } 
    throw Error.runtimeError(201, "DateTimeType");
  }
  
  public String convertToSQLString(Object paramObject) {
    if (paramObject == null)
      return "NULL"; 
    StringBuffer stringBuffer = new StringBuffer(32);
    switch (this.typeCode) {
      case 91:
        stringBuffer.append("DATE");
        break;
      case 92:
      case 94:
        stringBuffer.append("TIME");
        break;
      case 93:
      case 95:
        stringBuffer.append("TIMESTAMP");
        break;
    } 
    stringBuffer.append(StringConverter.toQuotedString(convertToString(paramObject), '\'', false));
    return stringBuffer.toString();
  }
  
  public boolean canConvertFrom(Type paramType) {
    return (paramType.typeCode == 0) ? true : (paramType.isCharacterType() ? true : (!paramType.isDateTimeType() ? false : ((paramType.typeCode == 91) ? ((this.typeCode != 92)) : ((paramType.typeCode == 92) ? ((this.typeCode != 91)) : true))));
  }
  
  public int canMoveFrom(Type paramType) {
    return (paramType == this) ? 0 : ((this.typeCode == paramType.typeCode) ? ((this.scale >= paramType.scale) ? 0 : -1) : -1);
  }
  
  public Object add(Session paramSession, Object paramObject1, Object paramObject2, Type paramType) {
    if (paramObject1 == null || paramObject2 == null)
      return null; 
    if (paramType.isNumberType()) {
      if (this.typeCode == 91)
        paramObject2 = ((NumberType)paramType).floor(paramObject2); 
      paramObject2 = Type.SQL_INTERVAL_SECOND_MAX_PRECISION.multiply(IntervalSecondData.oneDay, paramObject2);
    } 
    switch (this.typeCode) {
      case 92:
      case 94:
        if (paramObject2 instanceof IntervalMonthData)
          throw Error.runtimeError(201, "DateTimeType"); 
        if (paramObject2 instanceof IntervalSecondData)
          return addSeconds((TimeData)paramObject1, ((IntervalSecondData)paramObject2).units, ((IntervalSecondData)paramObject2).nanos); 
        break;
      case 91:
      case 93:
      case 95:
        if (paramObject2 instanceof IntervalMonthData)
          return addMonths(paramSession, (TimestampData)paramObject1, ((IntervalMonthData)paramObject2).units); 
        if (paramObject2 instanceof IntervalSecondData)
          return addSeconds((TimestampData)paramObject1, ((IntervalSecondData)paramObject2).units, ((IntervalSecondData)paramObject2).nanos); 
        break;
    } 
    throw Error.runtimeError(201, "DateTimeType");
  }
  
  public Object subtract(Session paramSession, Object paramObject1, Object paramObject2, Type paramType) {
    if (paramObject1 == null || paramObject2 == null)
      return null; 
    if (paramType.isNumberType()) {
      if (this.typeCode == 91)
        paramObject2 = ((NumberType)paramType).floor(paramObject2); 
      paramObject2 = Type.SQL_INTERVAL_SECOND_MAX_PRECISION.multiply(IntervalSecondData.oneDay, paramObject2);
    } 
    switch (this.typeCode) {
      case 92:
      case 94:
        if (paramObject2 instanceof IntervalMonthData)
          throw Error.runtimeError(201, "DateTimeType"); 
        if (paramObject2 instanceof IntervalSecondData)
          return addSeconds((TimeData)paramObject1, -((IntervalSecondData)paramObject2).units, -((IntervalSecondData)paramObject2).nanos); 
        break;
      case 91:
      case 93:
      case 95:
        if (paramObject2 instanceof IntervalMonthData)
          return addMonths(paramSession, (TimestampData)paramObject1, -((IntervalMonthData)paramObject2).units); 
        if (paramObject2 instanceof IntervalSecondData)
          return addSeconds((TimestampData)paramObject1, -((IntervalSecondData)paramObject2).units, -((IntervalSecondData)paramObject2).nanos); 
        break;
    } 
    throw Error.runtimeError(201, "DateTimeType");
  }
  
  public Object truncate(Object paramObject, int paramInt) {
    if (paramObject == null)
      return null; 
    long l = getMillis(paramObject);
    l = HsqlDateTime.getTruncatedPart(l, paramInt);
    l -= getZoneMillis(paramObject);
    switch (this.typeCode) {
      case 94:
        l = HsqlDateTime.getNormalisedTime(l);
      case 92:
        return new TimeData((int)(l / 1000L), 0, ((TimeData)paramObject).getZone());
      case 91:
      case 93:
      case 95:
        return new TimestampData(l / 1000L, 0, ((TimestampData)paramObject).getZone());
    } 
    throw Error.runtimeError(201, "DateTimeType");
  }
  
  public Object round(Object paramObject, int paramInt) {
    if (paramObject == null)
      return null; 
    long l = getMillis(paramObject);
    l = HsqlDateTime.getRoundedPart(l, paramInt);
    l -= getZoneMillis(paramObject);
    switch (this.typeCode) {
      case 92:
      case 94:
        l = HsqlDateTime.getNormalisedTime(l);
        return new TimeData((int)(l / 1000L), 0, ((TimeData)paramObject).getZone());
      case 91:
      case 93:
      case 95:
        return new TimestampData(l / 1000L, 0, ((TimestampData)paramObject).getZone());
    } 
    throw Error.runtimeError(201, "DateTimeType");
  }
  
  public boolean equals(Object paramObject) {
    return (paramObject instanceof Type) ? ((super.equals(paramObject) && ((DateTimeType)paramObject).withTimeZone == this.withTimeZone)) : false;
  }
  
  public int getPart(Session paramSession, Object paramObject, int paramInt) {
    byte b1;
    long l;
    byte b2 = 0;
    byte b3 = 1;
    switch (paramInt) {
      case 101:
        b1 = 1;
        l = getMillis(paramObject);
        return HsqlDateTime.getDateTimePart(l, b1) / b3 + b2;
      case 102:
        b2 = 1;
        b1 = 2;
        l = getMillis(paramObject);
        return HsqlDateTime.getDateTimePart(l, b1) / b3 + b2;
      case 103:
      case 260:
        b1 = 5;
        l = getMillis(paramObject);
        return HsqlDateTime.getDateTimePart(l, b1) / b3 + b2;
      case 104:
        b1 = 11;
        l = getMillis(paramObject);
        return HsqlDateTime.getDateTimePart(l, b1) / b3 + b2;
      case 105:
        b1 = 12;
        l = getMillis(paramObject);
        return HsqlDateTime.getDateTimePart(l, b1) / b3 + b2;
      case 106:
        b1 = 13;
        l = getMillis(paramObject);
        return HsqlDateTime.getDateTimePart(l, b1) / b3 + b2;
      case 259:
        b1 = 7;
        l = getMillis(paramObject);
        return HsqlDateTime.getDateTimePart(l, b1) / b3 + b2;
      case 262:
        b1 = 3;
        l = getMillis(paramObject);
        return HsqlDateTime.getDateTimePart(l, b1) / b3 + b2;
      case 266:
        if (this.typeCode != 92 && this.typeCode != 94)
          try {
            DateTimeType dateTimeType = this.withTimeZone ? Type.SQL_TIME_WITH_TIME_ZONE : Type.SQL_TIME;
            paramObject = dateTimeType.castToType((SessionInterface)paramSession, paramObject, this);
          } catch (HsqlException hsqlException) {} 
        return ((TimeData)paramObject).getSeconds();
      case 257:
        return (this.typeCode == 95) ? (((TimestampData)paramObject).getZone() / 3600) : (((TimeData)paramObject).getZone() / 3600);
      case 258:
        return (this.typeCode == 95) ? (((TimestampData)paramObject).getZone() / 60 % 60) : (((TimeData)paramObject).getZone() / 60 % 60);
      case 263:
        b2 = 1;
        b3 = 3;
        b1 = 2;
        l = getMillis(paramObject);
        return HsqlDateTime.getDateTimePart(l, b1) / b3 + b2;
      case 261:
        b1 = 6;
        l = getMillis(paramObject);
        return HsqlDateTime.getDateTimePart(l, b1) / b3 + b2;
    } 
    throw Error.runtimeError(201, "DateTimeType - " + paramInt);
  }
  
  public Object addMonthsSpecial(Session paramSession, Object paramObject, int paramInt) {
    TimestampData timestampData = (TimestampData)paramObject;
    Calendar calendar = paramSession.getCalendarGMT();
    long l = (timestampData.getSeconds() + timestampData.getZone()) * 1000L;
    HsqlDateTime.setTimeInMillis(calendar, l);
    calendar.set(5, 1);
    calendar.add(2, 1);
    calendar.add(5, -1);
    boolean bool = (l == calendar.getTimeInMillis()) ? true : false;
    HsqlDateTime.setTimeInMillis(calendar, l);
    calendar.add(2, paramInt);
    if (bool) {
      calendar.set(5, 1);
      calendar.add(2, 1);
      calendar.add(5, -1);
    } 
    l = calendar.getTimeInMillis();
    return new TimestampData(l / 1000L, 0, 0);
  }
  
  public Object getLastDayOfMonth(Session paramSession, Object paramObject) {
    TimestampData timestampData = (TimestampData)paramObject;
    Calendar calendar = paramSession.getCalendarGMT();
    long l = (timestampData.getSeconds() + timestampData.getZone()) * 1000L;
    HsqlDateTime.setTimeInMillis(calendar, l);
    calendar.set(5, 1);
    calendar.add(2, 1);
    calendar.add(5, -1);
    l = calendar.getTimeInMillis();
    return new TimestampData(l / 1000L, 0, 0);
  }
  
  long getMillis(Object paramObject) {
    long l;
    if (this.typeCode == 92 || this.typeCode == 94) {
      l = ((((TimeData)paramObject).getSeconds() + ((TimeData)paramObject).getZone()) * 1000);
    } else {
      l = (((TimestampData)paramObject).getSeconds() + ((TimestampData)paramObject).getZone()) * 1000L;
    } 
    return l;
  }
  
  long getZoneMillis(Object paramObject) {
    long l;
    if (this.typeCode == 92 || this.typeCode == 94) {
      l = (((TimeData)paramObject).getZone() * 1000);
    } else {
      l = (((TimestampData)paramObject).getZone() * 1000);
    } 
    return l;
  }
  
  public BigDecimal getSecondPart(Object paramObject) {
    long l = getPart((Session)null, paramObject, 106);
    int i = 0;
    if (this.typeCode == 93) {
      i = ((TimestampData)paramObject).getNanos();
    } else if (this.typeCode == 92) {
      i = ((TimeData)paramObject).getNanos();
    } 
    return getSecondPart(l, i);
  }
  
  public String getPartString(Session paramSession, Object paramObject, int paramInt) {
    String str = "";
    switch (paramInt) {
      case 264:
        str = "EEEE";
        break;
      case 265:
        str = "MMMM";
        break;
    } 
    SimpleDateFormat simpleDateFormat = paramSession.getSimpleDateFormatGMT();
    try {
      simpleDateFormat.applyPattern(str);
    } catch (Exception exception) {}
    Date date = (Date)convertSQLToJavaGMT((SessionInterface)paramSession, paramObject);
    return simpleDateFormat.format(date);
  }
  
  public Object getValue(long paramLong, int paramInt1, int paramInt2) {
    switch (this.typeCode) {
      case 91:
        paramLong = HsqlDateTime.getNormalisedDate((paramLong + paramInt2) * 1000L) / 1000L;
        return new TimestampData(paramLong);
      case 94:
        paramLong = HsqlDateTime.getNormalisedDate(paramLong * 1000L) / 1000L;
        return new TimeData((int)paramLong, paramInt1, paramInt2);
      case 92:
        paramLong = HsqlDateTime.getNormalisedTime((paramLong + paramInt2) * 1000L) / 1000L;
        return new TimeData((int)paramLong, paramInt1);
      case 95:
        return new TimestampData(paramLong, paramInt1, paramInt2);
      case 93:
        return new TimestampData(paramLong + paramInt2, paramInt1);
    } 
    throw Error.runtimeError(201, "DateTimeType");
  }
  
  public DateTimeType getDateTimeTypeWithoutZone() {
    if (this.withTimeZone) {
      DateTimeType dateTimeType;
      switch (this.typeCode) {
        case 94:
          dateTimeType = new DateTimeType(92, 92, this.scale);
          dateTimeType.nameString = this.nameString;
          return dateTimeType;
        case 95:
          dateTimeType = new DateTimeType(93, 93, this.scale);
          dateTimeType.nameString = this.nameString;
          return dateTimeType;
      } 
      throw Error.runtimeError(201, "DateTimeType");
    } 
    return this;
  }
  
  public static DateTimeType getDateTimeType(int paramInt1, int paramInt2) {
    if (paramInt2 > 9)
      throw Error.error(5592); 
    switch (paramInt1) {
      case 91:
        return SQL_DATE;
      case 92:
        return (paramInt2 == 0) ? SQL_TIME : new DateTimeType(92, paramInt1, paramInt2);
      case 94:
        return (paramInt2 == 0) ? SQL_TIME_WITH_TIME_ZONE : new DateTimeType(92, paramInt1, paramInt2);
      case 93:
        return (paramInt2 == 6) ? SQL_TIMESTAMP : new DateTimeType(93, paramInt1, paramInt2);
      case 95:
        return (paramInt2 == 6) ? SQL_TIMESTAMP_WITH_TIME_ZONE : new DateTimeType(93, paramInt1, paramInt2);
    } 
    throw Error.runtimeError(201, "DateTimeType");
  }
  
  public Object changeZone(Object paramObject, Type paramType, int paramInt1, int paramInt2) {
    TimeData timeData;
    TimestampData timestampData;
    int i;
    long l;
    if (paramObject == null)
      return null; 
    if (paramInt1 > 50400 || -paramInt1 > 50400)
      throw Error.error(3409); 
    switch (this.typeCode) {
      case 94:
        timeData = (TimeData)paramObject;
        if (paramType.isDateTimeTypeWithZone()) {
          if (timeData.zone != paramInt1)
            return new TimeData(timeData.getSeconds(), timeData.getNanos(), paramInt1); 
          break;
        } 
        i = timeData.getSeconds() - paramInt2;
        i = (int)(HsqlDateTime.getNormalisedTime((i * 1000)) / 1000L);
        return new TimeData(i, timeData.getNanos(), paramInt1);
      case 95:
        timestampData = (TimestampData)paramObject;
        l = timestampData.getSeconds();
        if (!paramType.isDateTimeTypeWithZone())
          l -= paramInt2; 
        if (timestampData.getSeconds() != l || timestampData.zone != paramInt1)
          return new TimestampData(l, timestampData.getNanos(), paramInt1); 
        break;
    } 
    return paramObject;
  }
  
  public boolean canAdd(IntervalType paramIntervalType) {
    return (paramIntervalType.startPartIndex >= this.startPartIndex && paramIntervalType.endPartIndex <= this.endPartIndex);
  }
  
  public int getSqlDateTimeSub() {
    switch (this.typeCode) {
      case 91:
        return 1;
      case 92:
        return 2;
      case 93:
        return 3;
    } 
    return 0;
  }
  
  public static Boolean overlaps(Session paramSession, Object[] paramArrayOfObject1, Type[] paramArrayOfType1, Object[] paramArrayOfObject2, Type[] paramArrayOfType2) {
    if (paramArrayOfObject1 == null || paramArrayOfObject2 == null)
      return null; 
    if (paramArrayOfObject1[0] == null || paramArrayOfObject2[0] == null)
      return null; 
    if (paramArrayOfObject1[1] == null)
      paramArrayOfObject1[1] = paramArrayOfObject1[0]; 
    if (paramArrayOfObject2[1] == null)
      paramArrayOfObject2[1] = paramArrayOfObject2[0]; 
    Type type = paramArrayOfType1[0].getCombinedType(paramSession, paramArrayOfType2[0], 40);
    paramArrayOfObject1[0] = type.castToType((SessionInterface)paramSession, paramArrayOfObject1[0], paramArrayOfType1[0]);
    paramArrayOfObject2[0] = type.castToType((SessionInterface)paramSession, paramArrayOfObject2[0], paramArrayOfType2[0]);
    if (paramArrayOfType1[1].isIntervalType()) {
      paramArrayOfObject1[1] = type.add(paramSession, paramArrayOfObject1[0], paramArrayOfObject1[1], paramArrayOfType1[1]);
    } else {
      paramArrayOfObject1[1] = type.castToType((SessionInterface)paramSession, paramArrayOfObject1[1], paramArrayOfType1[1]);
    } 
    if (paramArrayOfType2[1].isIntervalType()) {
      paramArrayOfObject2[1] = type.add(paramSession, paramArrayOfObject2[0], paramArrayOfObject2[1], paramArrayOfType2[1]);
    } else {
      paramArrayOfObject2[1] = type.castToType((SessionInterface)paramSession, paramArrayOfObject2[1], paramArrayOfType2[1]);
    } 
    if (type.compare(paramSession, paramArrayOfObject1[0], paramArrayOfObject1[1]) > 0) {
      Object object = paramArrayOfObject1[0];
      paramArrayOfObject1[0] = paramArrayOfObject1[1];
      paramArrayOfObject1[1] = object;
    } 
    if (type.compare(paramSession, paramArrayOfObject2[0], paramArrayOfObject2[1]) > 0) {
      Object object = paramArrayOfObject2[0];
      paramArrayOfObject2[0] = paramArrayOfObject2[1];
      paramArrayOfObject2[1] = object;
    } 
    if (type.compare(paramSession, paramArrayOfObject1[0], paramArrayOfObject2[0]) > 0) {
      Object[] arrayOfObject = paramArrayOfObject1;
      paramArrayOfObject1 = paramArrayOfObject2;
      paramArrayOfObject2 = arrayOfObject;
    } 
    return (type.compare(paramSession, paramArrayOfObject1[1], paramArrayOfObject2[0]) > 0) ? Boolean.TRUE : Boolean.FALSE;
  }
  
  public static BigDecimal subtractMonthsSpecial(Session paramSession, TimestampData paramTimestampData1, TimestampData paramTimestampData2) {
    long l1 = (paramTimestampData1.getSeconds() + paramTimestampData1.getZone()) * 1000L;
    long l2 = (paramTimestampData2.getSeconds() + paramTimestampData2.getZone()) * 1000L;
    boolean bool = false;
    if (l1 < l2) {
      bool = true;
      long l = l1;
      l1 = l2;
      l2 = l;
    } 
    l1 = HsqlDateTime.getNormalisedDate(paramSession.getCalendarGMT(), l1);
    l2 = HsqlDateTime.getNormalisedDate(paramSession.getCalendarGMT(), l2);
    Calendar calendar = paramSession.getCalendarGMT();
    calendar.setTimeInMillis(l1);
    int j = calendar.get(2) + calendar.get(1) * 12;
    int k = calendar.get(5);
    calendar.set(5, 1);
    long l3 = calendar.getTimeInMillis();
    calendar.add(2, 1);
    l3 = calendar.getTimeInMillis();
    calendar.add(5, -1);
    l3 = calendar.getTimeInMillis();
    int i = calendar.get(5);
    calendar.setTimeInMillis(l2);
    int n = calendar.get(2) + calendar.get(1) * 12;
    int i1 = calendar.get(5);
    calendar.set(5, 1);
    l3 = calendar.getTimeInMillis();
    calendar.add(2, 1);
    l3 = calendar.getTimeInMillis();
    calendar.add(5, -1);
    l3 = calendar.getTimeInMillis();
    int m = calendar.get(5);
    if (k == i1 || (k == i && i1 == m)) {
      double d = (j - n);
      if (bool)
        d = -d; 
      return BigDecimal.valueOf(d);
    } 
    if (i1 > k) {
      double d3 = (j - n - 1);
      double d4 = (m - i1 + k);
      d3 += d4 / 31.0D;
      if (bool)
        d3 = -d3; 
      return BigDecimal.valueOf(d3);
    } 
    double d1 = (j - n);
    double d2 = (k - i1);
    d1 += d2 / 31.0D;
    if (bool)
      d1 = -d1; 
    return BigDecimal.valueOf(d1);
  }
  
  public static int subtractMonths(TimestampData paramTimestampData1, TimestampData paramTimestampData2, boolean paramBoolean) {
    synchronized (HsqlDateTime.tempCalGMT) {
      boolean bool = false;
      if (paramTimestampData2.getSeconds() > paramTimestampData1.getSeconds()) {
        bool = true;
        TimestampData timestampData = paramTimestampData1;
        paramTimestampData1 = paramTimestampData2;
        paramTimestampData2 = timestampData;
      } 
      HsqlDateTime.setTimeInMillis(HsqlDateTime.tempCalGMT, paramTimestampData1.getSeconds() * 1000L);
      int i = HsqlDateTime.tempCalGMT.get(2);
      int j = HsqlDateTime.tempCalGMT.get(1);
      HsqlDateTime.setTimeInMillis(HsqlDateTime.tempCalGMT, paramTimestampData2.getSeconds() * 1000L);
      i -= HsqlDateTime.tempCalGMT.get(2);
      j -= HsqlDateTime.tempCalGMT.get(1);
      if (paramBoolean) {
        i = j * 12;
      } else {
        if (i < 0) {
          i += 12;
          j--;
        } 
        i += j * 12;
      } 
      if (bool)
        i = -i; 
      return i;
    } 
  }
  
  public static TimeData addSeconds(TimeData paramTimeData, long paramLong, int paramInt) {
    paramInt += paramTimeData.getNanos();
    paramLong += (paramInt / 1000000000);
    paramInt %= 1000000000;
    if (paramInt < 0) {
      paramInt += 1000000000;
      paramLong--;
    } 
    paramLong += paramTimeData.getSeconds();
    paramLong %= 86400L;
    return new TimeData((int)paramLong, paramInt, paramTimeData.getZone());
  }
  
  public static TimestampData addMonths(Session paramSession, TimestampData paramTimestampData, int paramInt) {
    int i = paramTimestampData.getNanos();
    Calendar calendar = paramSession.getCalendarGMT();
    HsqlDateTime.setTimeInMillis(calendar, paramTimestampData.getSeconds() * 1000L);
    calendar.add(2, paramInt);
    return new TimestampData(calendar.getTimeInMillis() / 1000L, i, paramTimestampData.getZone());
  }
  
  public static TimestampData addSeconds(TimestampData paramTimestampData, long paramLong, int paramInt) {
    paramInt += paramTimestampData.getNanos();
    paramLong += (paramInt / 1000000000);
    paramInt %= 1000000000;
    if (paramInt < 0) {
      paramInt += 1000000000;
      paramLong--;
    } 
    long l = paramTimestampData.getSeconds() + paramLong;
    return new TimestampData(l, paramInt, paramTimestampData.getZone());
  }
  
  public static TimestampData convertToDatetimeSpecial(SessionInterface paramSessionInterface, String paramString, DateTimeType paramDateTimeType) {
    switch (paramDateTimeType.typeCode) {
      case 93:
        if (paramSessionInterface instanceof Session && ((Session)paramSessionInterface).database.sqlSyntaxOra) {
          String str;
          if (paramString.length() == 9) {
            str = "DD-MON-YY";
          } else if (paramString.length() == 11) {
            str = "DD-MON-YYYY";
          } else if (paramString.length() == 20) {
            str = "DD-MON-YYYY HH24:MI:SS";
          } else {
            if (paramString.length() > 20) {
              str = "DD-MON-YYYY HH24:MI:SS.FF";
              SimpleDateFormat simpleDateFormat1 = paramSessionInterface.getSimpleDateFormatGMT();
              return HsqlDateTime.toDate(paramString, str, simpleDateFormat1);
            } 
            break;
          } 
          SimpleDateFormat simpleDateFormat = paramSessionInterface.getSimpleDateFormatGMT();
          return HsqlDateTime.toDate(paramString, str, simpleDateFormat);
        } 
        break;
    } 
    throw Error.error(3407);
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\hsqldb\types\DateTimeType.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */