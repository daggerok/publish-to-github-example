package daggerok.impl;

import daggerok.Service;
import lombok.extern.slf4j.Slf4j;
import lombok.val;

import static java.lang.String.format;

@Slf4j
public class ServiceImpl implements Service {

  @Override
  public String getOutput() {

    if (log.isDebugEnabled()) log.debug("execution without arg...");

    return "Hello, World!";
  }

  @Override
  public String getOutput(final String name) {

    if (log.isDebugEnabled()) log.debug("execution wit arg: {}", name);

    return null == name || name.trim().length() < 1
        ? getOutput() : format("Hello, %s!", capitalize(name));
  }

  private static String capitalize(final String name) {

    if (null == name) return "";

    val head = format("%s", name.charAt(0)).toUpperCase();
    val tail = name.length() > 1
        ? name.substring(1) : "";

    return head + tail;
  }
}
