/*-
 * ---license-start
 * Corona-Warn-App
 * ---
 * Copyright (C) 2020 SAP SE and all other contributors
 * ---
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * ---license-end
 */

package app.coronawarn.server.services.distribution.objectstore.client;

import io.minio.messages.Item;
import java.util.HashMap;
import java.util.Map;

/**
 * Represents an object as discovered on S3.
 */
public class S3Object {

  /**
   * the name of the object.
   */
  private final String objectName;

  /**
   * the available meta information.
   */
  private Map<String, String> metadata = new HashMap<>();

  /** The e-Tag of this S3 Object. */
  private String etag;

  /**
   * Constructs a new S3Object for the given object name.
   *
   * @param objectName the target object name
   */
  public S3Object(String objectName) {
    this.objectName = objectName;
  }

  /**
   * Constructs a new S3Object for the given object name.
   *
   * @param objectName the target object name
   * @param etag the e-etag
   */
  public S3Object(String objectName, String etag) {
    this(objectName);
    this.etag = etag;
  }

  public String getObjectName() {
    return objectName;
  }

  public String getEtag() {
    return etag;
  }

  /**
   * Returns a new instance of an S3Object based on the given item.
   *
   * @param item the item (as provided by MinIO)
   * @return the S3Object representation
   */
  public static S3Object of(Item item) {
    String etag = item.etag().replaceAll("\"", "");
    return new S3Object(item.objectName(), etag);
  }
}
