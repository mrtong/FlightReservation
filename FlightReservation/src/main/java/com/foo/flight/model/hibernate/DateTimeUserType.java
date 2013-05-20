package com.foo.flight.model.hibernate;
import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;

import org.hibernate.HibernateException;
import org.hibernate.usertype.UserType;
import org.joda.time.DateTime;
public class DateTimeUserType implements UserType {

	  public int[] sqlTypes() {
	    return new int[] { Types.TIMESTAMP };
	  }

	  public Class<DateTime> returnedClass() {
	    return DateTime.class;
	  }

	  public boolean isMutable() {
	    return false;
	  }

	  public Object deepCopy(Object value) throws HibernateException {
	    return value;
	  }

	  public Serializable disassemble(Object value) throws HibernateException {
	    return (Serializable) value;
	  }

	  public Object assemble(Serializable cached, Object owner) throws HibernateException {
	    return cached;
	  }

	  public Object replace(Object original, Object target, Object owner) throws HibernateException {
	    return original;
	  }

	  public boolean equals(Object x, Object y) throws HibernateException {
	    return x == y || !(x == null || y == null) && x.equals(y);
	  }

	  public int hashCode(Object x) throws HibernateException {
	    return x.hashCode();
	  }

	  public Object nullSafeGet(ResultSet rs, String[] names, Object owner) throws HibernateException,
	    SQLException {
	    Timestamp timestamp = rs.getTimestamp(names[0]);
	    if (rs.wasNull()) {
	      return null;
	    }
	    return new DateTime(timestamp);
	  }

	  public void nullSafeSet(PreparedStatement st, Object value, int index) throws HibernateException,
	    SQLException {
	    if (value == null) {
	      st.setNull(index, Types.TIMESTAMP);
	    }
	    else {
	      DateTime dateTime = (DateTime) value;
	      Timestamp timestamp = new Timestamp(dateTime.getMillis());
	      st.setTimestamp(index, timestamp);
	    }
	  }

	}
