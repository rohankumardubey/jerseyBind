package com.sodonnell.jerseyBind;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SimpleClass {

  private static Logger LOG = LoggerFactory.getLogger(SimpleClass.class);

  public SimpleClass() {
    LOG.info("Call the simple class constructor");
  }

}
