package com.deblox;

import io.vertx.core.json.JsonObject;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;

import java.io.*;

public class Util {

  private static final Logger logger = LoggerFactory.getLogger(Util.class);

  /*
  Config loader
   */
  static public JsonObject loadConfig(String file) throws IOException {
    logger.info("loading file: " + file);
    try (InputStream stream = new FileInputStream(file)) {
      StringBuilder sb = new StringBuilder();
      BufferedReader reader = new BufferedReader(new InputStreamReader(stream, "UTF-8"));

      String line = reader.readLine();
      while (line != null) {
        sb.append(line).append('\n');
        line = reader.readLine();
        logger.debug(line);
      }

      return new JsonObject(sb.toString());

    } catch (IOException e) {
      logger.error("Unable to load config file: " + file);
      e.printStackTrace();
      throw new IOException("Unable to open file: " + file );
    }
  }

  /*
  classpath resource hunting config loader
   */
  static public JsonObject loadConfig(Object o, String file) {

    try (InputStream stream = o.getClass().getResourceAsStream(file)) {
      StringBuilder sb = new StringBuilder();
      BufferedReader reader = new BufferedReader(new InputStreamReader(stream, "UTF-8"));

      String line = reader.readLine();
      while (line != null) {
        sb.append(line).append('\n');
        line = reader.readLine();
      }

      return new JsonObject(sb.toString());

    } catch (IOException e) {
      System.err.println("Unable to load config, returning with nothing");
      e.printStackTrace();
      return new JsonObject();
    }

  }

}