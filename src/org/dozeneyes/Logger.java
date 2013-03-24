package org.dozeneyes;

import java.util.Date;

/** placeholder logger
 */
public class Logger {

  protected String component;

  public Logger(Class clazz) {
     component = clazz.getSimpleName();
  }

  public void d(String args) {
    doLog(args, "DEBUG");
  }

  public void i(String args) {
    doLog(args, "INFO");
  }

  public void e(String args) {
    doLog(args, "ERROR");
  }

  protected void doLog(String args, String level) {
    System.out.println(new Date() + " " + component + " " + level +" " + args);
  }

}


