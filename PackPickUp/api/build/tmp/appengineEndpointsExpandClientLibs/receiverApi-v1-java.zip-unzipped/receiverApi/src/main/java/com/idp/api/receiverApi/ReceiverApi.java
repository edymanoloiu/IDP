/*
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */
/*
 * This code was generated by https://code.google.com/p/google-apis-client-generator/
 * (build: 2015-03-26 20:30:19 UTC)
 * on 2015-04-19 at 12:32:06 UTC 
 * Modify at your own risk.
 */

package com.idp.api.receiverApi;

/**
 * Service definition for ReceiverApi (v1).
 *
 * <p>
 * This is an API
 * </p>
 *
 * <p>
 * For more information about this service, see the
 * <a href="" target="_blank">API Documentation</a>
 * </p>
 *
 * <p>
 * This service uses {@link ReceiverApiRequestInitializer} to initialize global parameters via its
 * {@link Builder}.
 * </p>
 *
 * @since 1.3
 * @author Google, Inc.
 */
@SuppressWarnings("javadoc")
public class ReceiverApi extends com.google.api.client.googleapis.services.json.AbstractGoogleJsonClient {

  // Note: Leave this static initializer at the top of the file.
  static {
    com.google.api.client.util.Preconditions.checkState(
        com.google.api.client.googleapis.GoogleUtils.MAJOR_VERSION == 1 &&
        com.google.api.client.googleapis.GoogleUtils.MINOR_VERSION >= 15,
        "You are currently running with version %s of google-api-client. " +
        "You need at least version 1.15 of google-api-client to run version " +
        "1.20.0 of the receiverApi library.", com.google.api.client.googleapis.GoogleUtils.VERSION);
  }

  /**
   * The default encoded root URL of the service. This is determined when the library is generated
   * and normally should not be changed.
   *
   * @since 1.7
   */
  public static final String DEFAULT_ROOT_URL = "https://myApplicationId.appspot.com/_ah/api/";

  /**
   * The default encoded service path of the service. This is determined when the library is
   * generated and normally should not be changed.
   *
   * @since 1.7
   */
  public static final String DEFAULT_SERVICE_PATH = "receiverApi/v1/";

  /**
   * The default encoded base URL of the service. This is determined when the library is generated
   * and normally should not be changed.
   */
  public static final String DEFAULT_BASE_URL = DEFAULT_ROOT_URL + DEFAULT_SERVICE_PATH;

  /**
   * Constructor.
   *
   * <p>
   * Use {@link Builder} if you need to specify any of the optional parameters.
   * </p>
   *
   * @param transport HTTP transport, which should normally be:
   *        <ul>
   *        <li>Google App Engine:
   *        {@code com.google.api.client.extensions.appengine.http.UrlFetchTransport}</li>
   *        <li>Android: {@code newCompatibleTransport} from
   *        {@code com.google.api.client.extensions.android.http.AndroidHttp}</li>
   *        <li>Java: {@link com.google.api.client.googleapis.javanet.GoogleNetHttpTransport#newTrustedTransport()}
   *        </li>
   *        </ul>
   * @param jsonFactory JSON factory, which may be:
   *        <ul>
   *        <li>Jackson: {@code com.google.api.client.json.jackson2.JacksonFactory}</li>
   *        <li>Google GSON: {@code com.google.api.client.json.gson.GsonFactory}</li>
   *        <li>Android Honeycomb or higher:
   *        {@code com.google.api.client.extensions.android.json.AndroidJsonFactory}</li>
   *        </ul>
   * @param httpRequestInitializer HTTP request initializer or {@code null} for none
   * @since 1.7
   */
  public ReceiverApi(com.google.api.client.http.HttpTransport transport, com.google.api.client.json.JsonFactory jsonFactory,
      com.google.api.client.http.HttpRequestInitializer httpRequestInitializer) {
    this(new Builder(transport, jsonFactory, httpRequestInitializer));
  }

  /**
   * @param builder builder
   */
  ReceiverApi(Builder builder) {
    super(builder);
  }

  @Override
  protected void initialize(com.google.api.client.googleapis.services.AbstractGoogleClientRequest<?> httpClientRequest) throws java.io.IOException {
    super.initialize(httpClientRequest);
  }

  /**
   * Create a request for the method "insertReceiver".
   *
   * This request holds the parameters needed by the receiverApi server.  After setting any optional
   * parameters, call the {@link InsertReceiver#execute()} method to invoke the remote operation.
   *
   * @param content the {@link com.idp.api.receiverApi.model.Receiver}
   * @return the request
   */
  public InsertReceiver insertReceiver(com.idp.api.receiverApi.model.Receiver content) throws java.io.IOException {
    InsertReceiver result = new InsertReceiver(content);
    initialize(result);
    return result;
  }

  public class InsertReceiver extends ReceiverApiRequest<com.idp.api.receiverApi.model.Receiver> {

    private static final String REST_PATH = "receiver";

    /**
     * Create a request for the method "insertReceiver".
     *
     * This request holds the parameters needed by the the receiverApi server.  After setting any
     * optional parameters, call the {@link InsertReceiver#execute()} method to invoke the remote
     * operation. <p> {@link InsertReceiver#initialize(com.google.api.client.googleapis.services.Abstr
     * actGoogleClientRequest)} must be called to initialize this instance immediately after invoking
     * the constructor. </p>
     *
     * @param content the {@link com.idp.api.receiverApi.model.Receiver}
     * @since 1.13
     */
    protected InsertReceiver(com.idp.api.receiverApi.model.Receiver content) {
      super(ReceiverApi.this, "POST", REST_PATH, content, com.idp.api.receiverApi.model.Receiver.class);
    }

    @Override
    public InsertReceiver setAlt(java.lang.String alt) {
      return (InsertReceiver) super.setAlt(alt);
    }

    @Override
    public InsertReceiver setFields(java.lang.String fields) {
      return (InsertReceiver) super.setFields(fields);
    }

    @Override
    public InsertReceiver setKey(java.lang.String key) {
      return (InsertReceiver) super.setKey(key);
    }

    @Override
    public InsertReceiver setOauthToken(java.lang.String oauthToken) {
      return (InsertReceiver) super.setOauthToken(oauthToken);
    }

    @Override
    public InsertReceiver setPrettyPrint(java.lang.Boolean prettyPrint) {
      return (InsertReceiver) super.setPrettyPrint(prettyPrint);
    }

    @Override
    public InsertReceiver setQuotaUser(java.lang.String quotaUser) {
      return (InsertReceiver) super.setQuotaUser(quotaUser);
    }

    @Override
    public InsertReceiver setUserIp(java.lang.String userIp) {
      return (InsertReceiver) super.setUserIp(userIp);
    }

    @Override
    public InsertReceiver set(String parameterName, Object value) {
      return (InsertReceiver) super.set(parameterName, value);
    }
  }

  /**
   * Create a request for the method "listReceivers".
   *
   * This request holds the parameters needed by the receiverApi server.  After setting any optional
   * parameters, call the {@link ListReceivers#execute()} method to invoke the remote operation.
   *
   * @return the request
   */
  public ListReceivers listReceivers() throws java.io.IOException {
    ListReceivers result = new ListReceivers();
    initialize(result);
    return result;
  }

  public class ListReceivers extends ReceiverApiRequest<com.idp.api.receiverApi.model.CollectionResponseReceiver> {

    private static final String REST_PATH = "receiver";

    /**
     * Create a request for the method "listReceivers".
     *
     * This request holds the parameters needed by the the receiverApi server.  After setting any
     * optional parameters, call the {@link ListReceivers#execute()} method to invoke the remote
     * operation. <p> {@link ListReceivers#initialize(com.google.api.client.googleapis.services.Abstra
     * ctGoogleClientRequest)} must be called to initialize this instance immediately after invoking
     * the constructor. </p>
     *
     * @since 1.13
     */
    protected ListReceivers() {
      super(ReceiverApi.this, "GET", REST_PATH, null, com.idp.api.receiverApi.model.CollectionResponseReceiver.class);
    }

    @Override
    public com.google.api.client.http.HttpResponse executeUsingHead() throws java.io.IOException {
      return super.executeUsingHead();
    }

    @Override
    public com.google.api.client.http.HttpRequest buildHttpRequestUsingHead() throws java.io.IOException {
      return super.buildHttpRequestUsingHead();
    }

    @Override
    public ListReceivers setAlt(java.lang.String alt) {
      return (ListReceivers) super.setAlt(alt);
    }

    @Override
    public ListReceivers setFields(java.lang.String fields) {
      return (ListReceivers) super.setFields(fields);
    }

    @Override
    public ListReceivers setKey(java.lang.String key) {
      return (ListReceivers) super.setKey(key);
    }

    @Override
    public ListReceivers setOauthToken(java.lang.String oauthToken) {
      return (ListReceivers) super.setOauthToken(oauthToken);
    }

    @Override
    public ListReceivers setPrettyPrint(java.lang.Boolean prettyPrint) {
      return (ListReceivers) super.setPrettyPrint(prettyPrint);
    }

    @Override
    public ListReceivers setQuotaUser(java.lang.String quotaUser) {
      return (ListReceivers) super.setQuotaUser(quotaUser);
    }

    @Override
    public ListReceivers setUserIp(java.lang.String userIp) {
      return (ListReceivers) super.setUserIp(userIp);
    }

    @com.google.api.client.util.Key
    private java.lang.Integer count;

    /**

     */
    public java.lang.Integer getCount() {
      return count;
    }

    public ListReceivers setCount(java.lang.Integer count) {
      this.count = count;
      return this;
    }

    @com.google.api.client.util.Key
    private java.lang.String cursor;

    /**

     */
    public java.lang.String getCursor() {
      return cursor;
    }

    public ListReceivers setCursor(java.lang.String cursor) {
      this.cursor = cursor;
      return this;
    }

    @Override
    public ListReceivers set(String parameterName, Object value) {
      return (ListReceivers) super.set(parameterName, value);
    }
  }

  /**
   * Create a request for the method "removeReceiver".
   *
   * This request holds the parameters needed by the receiverApi server.  After setting any optional
   * parameters, call the {@link RemoveReceiver#execute()} method to invoke the remote operation.
   *
   * @param id
   * @return the request
   */
  public RemoveReceiver removeReceiver(java.lang.Long id) throws java.io.IOException {
    RemoveReceiver result = new RemoveReceiver(id);
    initialize(result);
    return result;
  }

  public class RemoveReceiver extends ReceiverApiRequest<Void> {

    private static final String REST_PATH = "receiver/{id}";

    /**
     * Create a request for the method "removeReceiver".
     *
     * This request holds the parameters needed by the the receiverApi server.  After setting any
     * optional parameters, call the {@link RemoveReceiver#execute()} method to invoke the remote
     * operation. <p> {@link RemoveReceiver#initialize(com.google.api.client.googleapis.services.Abstr
     * actGoogleClientRequest)} must be called to initialize this instance immediately after invoking
     * the constructor. </p>
     *
     * @param id
     * @since 1.13
     */
    protected RemoveReceiver(java.lang.Long id) {
      super(ReceiverApi.this, "DELETE", REST_PATH, null, Void.class);
      this.id = com.google.api.client.util.Preconditions.checkNotNull(id, "Required parameter id must be specified.");
    }

    @Override
    public RemoveReceiver setAlt(java.lang.String alt) {
      return (RemoveReceiver) super.setAlt(alt);
    }

    @Override
    public RemoveReceiver setFields(java.lang.String fields) {
      return (RemoveReceiver) super.setFields(fields);
    }

    @Override
    public RemoveReceiver setKey(java.lang.String key) {
      return (RemoveReceiver) super.setKey(key);
    }

    @Override
    public RemoveReceiver setOauthToken(java.lang.String oauthToken) {
      return (RemoveReceiver) super.setOauthToken(oauthToken);
    }

    @Override
    public RemoveReceiver setPrettyPrint(java.lang.Boolean prettyPrint) {
      return (RemoveReceiver) super.setPrettyPrint(prettyPrint);
    }

    @Override
    public RemoveReceiver setQuotaUser(java.lang.String quotaUser) {
      return (RemoveReceiver) super.setQuotaUser(quotaUser);
    }

    @Override
    public RemoveReceiver setUserIp(java.lang.String userIp) {
      return (RemoveReceiver) super.setUserIp(userIp);
    }

    @com.google.api.client.util.Key
    private java.lang.Long id;

    /**

     */
    public java.lang.Long getId() {
      return id;
    }

    public RemoveReceiver setId(java.lang.Long id) {
      this.id = id;
      return this;
    }

    @Override
    public RemoveReceiver set(String parameterName, Object value) {
      return (RemoveReceiver) super.set(parameterName, value);
    }
  }

  /**
   * Create a request for the method "updateReceiver".
   *
   * This request holds the parameters needed by the receiverApi server.  After setting any optional
   * parameters, call the {@link UpdateReceiver#execute()} method to invoke the remote operation.
   *
   * @param content the {@link com.idp.api.receiverApi.model.Receiver}
   * @return the request
   */
  public UpdateReceiver updateReceiver(com.idp.api.receiverApi.model.Receiver content) throws java.io.IOException {
    UpdateReceiver result = new UpdateReceiver(content);
    initialize(result);
    return result;
  }

  public class UpdateReceiver extends ReceiverApiRequest<com.idp.api.receiverApi.model.Receiver> {

    private static final String REST_PATH = "receiver";

    /**
     * Create a request for the method "updateReceiver".
     *
     * This request holds the parameters needed by the the receiverApi server.  After setting any
     * optional parameters, call the {@link UpdateReceiver#execute()} method to invoke the remote
     * operation. <p> {@link UpdateReceiver#initialize(com.google.api.client.googleapis.services.Abstr
     * actGoogleClientRequest)} must be called to initialize this instance immediately after invoking
     * the constructor. </p>
     *
     * @param content the {@link com.idp.api.receiverApi.model.Receiver}
     * @since 1.13
     */
    protected UpdateReceiver(com.idp.api.receiverApi.model.Receiver content) {
      super(ReceiverApi.this, "PUT", REST_PATH, content, com.idp.api.receiverApi.model.Receiver.class);
    }

    @Override
    public UpdateReceiver setAlt(java.lang.String alt) {
      return (UpdateReceiver) super.setAlt(alt);
    }

    @Override
    public UpdateReceiver setFields(java.lang.String fields) {
      return (UpdateReceiver) super.setFields(fields);
    }

    @Override
    public UpdateReceiver setKey(java.lang.String key) {
      return (UpdateReceiver) super.setKey(key);
    }

    @Override
    public UpdateReceiver setOauthToken(java.lang.String oauthToken) {
      return (UpdateReceiver) super.setOauthToken(oauthToken);
    }

    @Override
    public UpdateReceiver setPrettyPrint(java.lang.Boolean prettyPrint) {
      return (UpdateReceiver) super.setPrettyPrint(prettyPrint);
    }

    @Override
    public UpdateReceiver setQuotaUser(java.lang.String quotaUser) {
      return (UpdateReceiver) super.setQuotaUser(quotaUser);
    }

    @Override
    public UpdateReceiver setUserIp(java.lang.String userIp) {
      return (UpdateReceiver) super.setUserIp(userIp);
    }

    @Override
    public UpdateReceiver set(String parameterName, Object value) {
      return (UpdateReceiver) super.set(parameterName, value);
    }
  }

  /**
   * Builder for {@link ReceiverApi}.
   *
   * <p>
   * Implementation is not thread-safe.
   * </p>
   *
   * @since 1.3.0
   */
  public static final class Builder extends com.google.api.client.googleapis.services.json.AbstractGoogleJsonClient.Builder {

    /**
     * Returns an instance of a new builder.
     *
     * @param transport HTTP transport, which should normally be:
     *        <ul>
     *        <li>Google App Engine:
     *        {@code com.google.api.client.extensions.appengine.http.UrlFetchTransport}</li>
     *        <li>Android: {@code newCompatibleTransport} from
     *        {@code com.google.api.client.extensions.android.http.AndroidHttp}</li>
     *        <li>Java: {@link com.google.api.client.googleapis.javanet.GoogleNetHttpTransport#newTrustedTransport()}
     *        </li>
     *        </ul>
     * @param jsonFactory JSON factory, which may be:
     *        <ul>
     *        <li>Jackson: {@code com.google.api.client.json.jackson2.JacksonFactory}</li>
     *        <li>Google GSON: {@code com.google.api.client.json.gson.GsonFactory}</li>
     *        <li>Android Honeycomb or higher:
     *        {@code com.google.api.client.extensions.android.json.AndroidJsonFactory}</li>
     *        </ul>
     * @param httpRequestInitializer HTTP request initializer or {@code null} for none
     * @since 1.7
     */
    public Builder(com.google.api.client.http.HttpTransport transport, com.google.api.client.json.JsonFactory jsonFactory,
        com.google.api.client.http.HttpRequestInitializer httpRequestInitializer) {
      super(
          transport,
          jsonFactory,
          DEFAULT_ROOT_URL,
          DEFAULT_SERVICE_PATH,
          httpRequestInitializer,
          false);
    }

    /** Builds a new instance of {@link ReceiverApi}. */
    @Override
    public ReceiverApi build() {
      return new ReceiverApi(this);
    }

    @Override
    public Builder setRootUrl(String rootUrl) {
      return (Builder) super.setRootUrl(rootUrl);
    }

    @Override
    public Builder setServicePath(String servicePath) {
      return (Builder) super.setServicePath(servicePath);
    }

    @Override
    public Builder setHttpRequestInitializer(com.google.api.client.http.HttpRequestInitializer httpRequestInitializer) {
      return (Builder) super.setHttpRequestInitializer(httpRequestInitializer);
    }

    @Override
    public Builder setApplicationName(String applicationName) {
      return (Builder) super.setApplicationName(applicationName);
    }

    @Override
    public Builder setSuppressPatternChecks(boolean suppressPatternChecks) {
      return (Builder) super.setSuppressPatternChecks(suppressPatternChecks);
    }

    @Override
    public Builder setSuppressRequiredParameterChecks(boolean suppressRequiredParameterChecks) {
      return (Builder) super.setSuppressRequiredParameterChecks(suppressRequiredParameterChecks);
    }

    @Override
    public Builder setSuppressAllChecks(boolean suppressAllChecks) {
      return (Builder) super.setSuppressAllChecks(suppressAllChecks);
    }

    /**
     * Set the {@link ReceiverApiRequestInitializer}.
     *
     * @since 1.12
     */
    public Builder setReceiverApiRequestInitializer(
        ReceiverApiRequestInitializer receiverapiRequestInitializer) {
      return (Builder) super.setGoogleClientRequestInitializer(receiverapiRequestInitializer);
    }

    @Override
    public Builder setGoogleClientRequestInitializer(
        com.google.api.client.googleapis.services.GoogleClientRequestInitializer googleClientRequestInitializer) {
      return (Builder) super.setGoogleClientRequestInitializer(googleClientRequestInitializer);
    }
  }
}
