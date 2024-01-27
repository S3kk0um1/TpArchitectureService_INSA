// ============================================================================
//
// Copyright (c) 2006-2015, Talend SA
//
// Ce code source a été automatiquement généré par_Talend Open Studio for ESB
// / Soumis à la Licence Apache, Version 2.0 (la "Licence") ;
// votre utilisation de ce fichier doit respecter les termes de la Licence.
// Vous pouvez obtenir une copie de la Licence sur
// http://www.apache.org/licenses/LICENSE-2.0
// 
// Sauf lorsqu'explicitement prévu par la loi en vigueur ou accepté par écrit, le logiciel
// distribué sous la Licence est distribué "TEL QUEL",
// SANS GARANTIE OU CONDITION D'AUCUNE SORTE, expresse ou implicite.
// Consultez la Licence pour connaître la terminologie spécifique régissant les autorisations et
// les limites prévues par la Licence.

package tp.getuserbysoap_0_1;

import routines.Numeric;
import routines.DataOperation;
import routines.TalendDataGenerator;
import routines.TalendStringUtil;
import routines.TalendString;
import routines.StringHandling;
import routines.Relational;
import routines.TalendDate;
import routines.Mathematical;
import routines.system.*;
import routines.system.api.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.math.BigDecimal;
import java.io.ByteArrayOutputStream;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.IOException;
import java.util.Comparator;

@SuppressWarnings("unused")

/**
 * Job: getUserbySOAP Purpose: <br>
 * Description: <br>
 * 
 * @author user@talend.com
 * @version 8.0.1.20211109_1610
 * @status
 */
public class getUserbySOAP implements TalendJob, TalendESBJob, TalendESBJobFactory {

	protected static void logIgnoredError(String message, Throwable cause) {
		System.err.println(message);
		if (cause != null) {
			cause.printStackTrace();
		}

	}

	private ESBEndpointRegistry registry = null;

	public void setEndpointRegistry(ESBEndpointRegistry registry) {
		this.registry = registry;
	}

	private ESBProviderCallback callback = null;

	public void setProviderCallback(ESBProviderCallback callback) {

		this.callback = callback;

	}

	public TalendESBJob newTalendESBJob() {
		getUserbySOAP talendESBJob = new getUserbySOAP();
		java.util.Map<String, routines.system.TalendDataSource> talendDataSources = (java.util.Map<String, routines.system.TalendDataSource>) globalMap
				.get(KEY_DB_DATASOURCES);
		if (null != talendDataSources) {
			java.util.Map<String, javax.sql.DataSource> dataSources = new java.util.HashMap<String, javax.sql.DataSource>();
			for (java.util.Map.Entry<String, routines.system.TalendDataSource> talendDataSourceEntry : talendDataSources
					.entrySet()) {
				dataSources.put(talendDataSourceEntry.getKey(), talendDataSourceEntry.getValue().getRawDataSource());
			}
			talendESBJob.setDataSources(dataSources);
		}
		// fix for TESB-7714
		talendESBJob.setEndpointRegistry(registry);

		return talendESBJob;
	}

	public final Object obj = new Object();

	// for transmiting parameters purpose
	private Object valueObject = null;

	public Object getValueObject() {
		return this.valueObject;
	}

	public void setValueObject(Object valueObject) {
		this.valueObject = valueObject;
	}

	private final static String defaultCharset = java.nio.charset.Charset.defaultCharset().name();

	private final static String utf8Charset = "UTF-8";

	// contains type for every context property
	public class PropertiesWithType extends java.util.Properties {
		private static final long serialVersionUID = 1L;
		private java.util.Map<String, String> propertyTypes = new java.util.HashMap<>();

		public PropertiesWithType(java.util.Properties properties) {
			super(properties);
		}

		public PropertiesWithType() {
			super();
		}

		public void setContextType(String key, String type) {
			propertyTypes.put(key, type);
		}

		public String getContextType(String key) {
			return propertyTypes.get(key);
		}
	}

	// create and load default properties
	private java.util.Properties defaultProps = new java.util.Properties();

	// create application properties with default
	public class ContextProperties extends PropertiesWithType {

		private static final long serialVersionUID = 1L;

		public ContextProperties(java.util.Properties properties) {
			super(properties);
		}

		public ContextProperties() {
			super();
		}

		public void synchronizeContext() {

		}

		// if the stored or passed value is "<TALEND_NULL>" string, it mean null
		public String getStringValue(String key) {
			String origin_value = this.getProperty(key);
			if (NULL_VALUE_EXPRESSION_IN_COMMAND_STRING_FOR_CHILD_JOB_ONLY.equals(origin_value)) {
				return null;
			}
			return origin_value;
		}

	}

	protected ContextProperties context = new ContextProperties(); // will be instanciated by MS.

	public ContextProperties getContext() {
		return this.context;
	}

	private final String jobVersion = "0.1";
	private final String jobName = "getUserbySOAP";
	private final String projectName = "TP";
	public Integer errorCode = null;
	private String currentComponent = "";

	private final java.util.Map<String, Object> globalMap = new java.util.HashMap<String, Object>();
	private final static java.util.Map<String, Object> junitGlobalMap = new java.util.HashMap<String, Object>();

	private final java.util.Map<String, Long> start_Hash = new java.util.HashMap<String, Long>();
	private final java.util.Map<String, Long> end_Hash = new java.util.HashMap<String, Long>();
	private final java.util.Map<String, Boolean> ok_Hash = new java.util.HashMap<String, Boolean>();
	public final java.util.List<String[]> globalBuffer = new java.util.ArrayList<String[]>();

	private RunStat runStat = new RunStat();

	// OSGi DataSource
	private final static String KEY_DB_DATASOURCES = "KEY_DB_DATASOURCES";

	private final static String KEY_DB_DATASOURCES_RAW = "KEY_DB_DATASOURCES_RAW";

	public void setDataSources(java.util.Map<String, javax.sql.DataSource> dataSources) {
		java.util.Map<String, routines.system.TalendDataSource> talendDataSources = new java.util.HashMap<String, routines.system.TalendDataSource>();
		for (java.util.Map.Entry<String, javax.sql.DataSource> dataSourceEntry : dataSources.entrySet()) {
			talendDataSources.put(dataSourceEntry.getKey(),
					new routines.system.TalendDataSource(dataSourceEntry.getValue()));
		}
		globalMap.put(KEY_DB_DATASOURCES, talendDataSources);
		globalMap.put(KEY_DB_DATASOURCES_RAW, new java.util.HashMap<String, javax.sql.DataSource>(dataSources));
	}

	public void setDataSourceReferences(List serviceReferences) throws Exception {

		java.util.Map<String, routines.system.TalendDataSource> talendDataSources = new java.util.HashMap<String, routines.system.TalendDataSource>();
		java.util.Map<String, javax.sql.DataSource> dataSources = new java.util.HashMap<String, javax.sql.DataSource>();

		for (java.util.Map.Entry<String, javax.sql.DataSource> entry : BundleUtils
				.getServices(serviceReferences, javax.sql.DataSource.class).entrySet()) {
			dataSources.put(entry.getKey(), entry.getValue());
			talendDataSources.put(entry.getKey(), new routines.system.TalendDataSource(entry.getValue()));
		}

		globalMap.put(KEY_DB_DATASOURCES, talendDataSources);
		globalMap.put(KEY_DB_DATASOURCES_RAW, new java.util.HashMap<String, javax.sql.DataSource>(dataSources));
	}

	private final java.io.ByteArrayOutputStream baos = new java.io.ByteArrayOutputStream();
	private final java.io.PrintStream errorMessagePS = new java.io.PrintStream(new java.io.BufferedOutputStream(baos));

	public String getExceptionStackTrace() {
		if ("failure".equals(this.getStatus())) {
			errorMessagePS.flush();
			return baos.toString();
		}
		return null;
	}

	private Exception exception;

	public Exception getException() {
		if ("failure".equals(this.getStatus())) {
			return this.exception;
		}
		return null;
	}

	private class TalendException extends Exception {

		private static final long serialVersionUID = 1L;

		private java.util.Map<String, Object> globalMap = null;
		private Exception e = null;
		private String currentComponent = null;
		private String virtualComponentName = null;

		public void setVirtualComponentName(String virtualComponentName) {
			this.virtualComponentName = virtualComponentName;
		}

		private TalendException(Exception e, String errorComponent, final java.util.Map<String, Object> globalMap) {
			this.currentComponent = errorComponent;
			this.globalMap = globalMap;
			this.e = e;
		}

		public Exception getException() {
			return this.e;
		}

		public String getCurrentComponent() {
			return this.currentComponent;
		}

		public String getExceptionCauseMessage(Exception e) {
			Throwable cause = e;
			String message = null;
			int i = 10;
			while (null != cause && 0 < i--) {
				message = cause.getMessage();
				if (null == message) {
					cause = cause.getCause();
				} else {
					break;
				}
			}
			if (null == message) {
				message = e.getClass().getName();
			}
			return message;
		}

		@Override
		public void printStackTrace() {
			if (!(e instanceof TalendException || e instanceof TDieException)) {
				if (virtualComponentName != null && currentComponent.indexOf(virtualComponentName + "_") == 0) {
					globalMap.put(virtualComponentName + "_ERROR_MESSAGE", getExceptionCauseMessage(e));
				}
				globalMap.put(currentComponent + "_ERROR_MESSAGE", getExceptionCauseMessage(e));
				System.err.println("Exception in component " + currentComponent + " (" + jobName + ")");
			}
			if (!(e instanceof TDieException)) {
				if (e instanceof TalendException) {
					e.printStackTrace();
				} else {
					e.printStackTrace();
					e.printStackTrace(errorMessagePS);
					getUserbySOAP.this.exception = e;
				}
			}
			if (!(e instanceof TalendException)) {
				try {
					for (java.lang.reflect.Method m : this.getClass().getEnclosingClass().getMethods()) {
						if (m.getName().compareTo(currentComponent + "_error") == 0) {
							m.invoke(getUserbySOAP.this, new Object[] { e, currentComponent, globalMap });
							break;
						}
					}

					if (!(e instanceof TDieException)) {
					}
				} catch (Exception e) {
					this.e.printStackTrace();
				}
			}
		}
	}

	public void tESBProviderResponse_1_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tESBProviderRequest_1_Loop_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tDBInput_2_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tDBInput_2_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tAdvancedXMLMapHash_row2_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tDBInput_2_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tESBProviderRequest_1_Loop_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		tESBProviderRequest_1_In_error(exception, errorComponent, globalMap);

	}

	public void tESBProviderRequest_1_In_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tESBProviderRequest_1_Loop_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tXMLMap_1_TXMLMAP_OUT_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		tXMLMap_1_TXMLMAP_IN_error(exception, errorComponent, globalMap);

	}

	public void tXMLMap_1_TXMLMAP_IN_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tESBProviderRequest_1_Loop_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tDBInput_2_onSubJobError(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		resumeUtil.addLog("SYSTEM_LOG", "NODE:" + errorComponent, "", Thread.currentThread().getId() + "", "FATAL", "",
				exception.getMessage(), ResumeUtil.getExceptionStackTrace(exception), "");

	}

	public void tESBProviderRequest_1_Loop_onSubJobError(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		resumeUtil.addLog("SYSTEM_LOG", "NODE:" + errorComponent, "", Thread.currentThread().getId() + "", "FATAL", "",
				exception.getMessage(), ResumeUtil.getExceptionStackTrace(exception), "");

	}

	public static class ContextBean {
		static String evaluate(String context, String contextExpression)
				throws IOException, javax.script.ScriptException {
			boolean isExpression = contextExpression.contains("+") || contextExpression.contains("(");
			final String prefix = isExpression ? "\"" : "";
			java.util.Properties defaultProps = new java.util.Properties();
			java.io.InputStream inContext = getUserbySOAP.class.getClassLoader()
					.getResourceAsStream("tp/getuserbysoap_0_1/contexts/" + context + ".properties");
			if (inContext == null) {
				inContext = getUserbySOAP.class.getClassLoader()
						.getResourceAsStream("config/contexts/" + context + ".properties");
			}
			try {
				defaultProps.load(inContext);
			} finally {
				inContext.close();
			}
			java.util.regex.Pattern pattern = java.util.regex.Pattern.compile("context.([\\w]+)");
			java.util.regex.Matcher matcher = pattern.matcher(contextExpression);

			while (matcher.find()) {
				contextExpression = contextExpression.replaceAll(matcher.group(0),
						prefix + defaultProps.getProperty(matcher.group(1)) + prefix);
			}
			if (contextExpression.startsWith("/services")) {
				contextExpression = contextExpression.replaceFirst("/services", "");
			}
			return isExpression ? evaluateContextExpression(contextExpression) : contextExpression;
		}

		public static String evaluateContextExpression(String expression) throws javax.script.ScriptException {
			javax.script.ScriptEngineManager manager = new javax.script.ScriptEngineManager();
			javax.script.ScriptEngine engine = manager.getEngineByName("nashorn");
			// Add some import for Java
			expression = expression.replaceAll("System.getProperty", "java.lang.System.getProperty");
			return engine.eval(expression).toString();
		}

		public static String getContext(String context, String contextName, String jobName) throws Exception {

			String currentContext = null;
			String jobNameStripped = jobName.substring(jobName.lastIndexOf(".") + 1);

			boolean inOSGi = routines.system.BundleUtils.inOSGi();

			if (inOSGi) {
				java.util.Dictionary<String, Object> jobProperties = routines.system.BundleUtils
						.getJobProperties(jobNameStripped);

				if (jobProperties != null) {
					currentContext = (String) jobProperties.get("context");
				}
			}

			return contextName.contains("context.")
					? evaluate(currentContext == null ? context : currentContext, contextName)
					: contextName;
		}
	}

	private Object wrapPayload(Object payload) {
		java.util.Map<String, Object> outputWrapped = new java.util.HashMap<String, Object>();
		outputWrapped.put("PAYLOAD", payload);
		return outputWrapped;
	}

	public ESBEndpointInfo getEndpoint() {
		return new ESBEndpointInfo() {
			@SuppressWarnings("serial")
			private java.util.Map<String, Object> props = new java.util.HashMap<String, Object>() {
				{
					// "request-response" or "one-way"
					put("COMMUNICATION_STYLE", "request-response");
					put("dataFormat", "PAYLOAD");
					put("portName", "{http://www.talend.org/service/}UsersWSPort");
					put("serviceName", "{http://www.talend.org/service/}UsersWS");
					put("defaultOperationName", "getUsersbyNom");
					put("defaultOperationNameSpace", "http://www.talend.org/service/");
					put("publishedEndpointUrl", "http://localhost:8090/services/UsersWS");
				}
			};

			public String getEndpointKey() {
				return "cxf";
			}

			public String getEndpointUri() {
				// projectName + "_" + processName
				return "TP_getUserbySOAP";
			}

			public java.util.Map<String, Object> getEndpointProperties() {
				return props;
			}
		};
	}

	/**
	 * queued message exchange
	 */
	public class QueuedExchangeContextImpl<IN, OUT> {

		/**
		 * Exchange timeout in seconds
		 */
		private static final long EXCHANGE_TIMEOUT = 50;

		private final java.util.concurrent.Exchanger<Exception> exceptionExchange = new java.util.concurrent.Exchanger<Exception>();
		private final java.util.concurrent.CountDownLatch latch = new java.util.concurrent.CountDownLatch(1);

		private final IN input;

		private OUT output = null;
		private Throwable fault = null;

		public QueuedExchangeContextImpl(IN inMsg) {
			this.input = inMsg;
		}

		/**
		 * Don't forget to call this method when you are done with processing of the
		 * {@link QueuedExchangeContext}
		 */
		public void release() throws Exception {
			latch.countDown();
			Exception exception = exceptionExchange.exchange(null, EXCHANGE_TIMEOUT,
					java.util.concurrent.TimeUnit.SECONDS);
			if (exception != null) {
				throw exception;
			}
		}

		/**
		 * This operation have to be called on the Web Service thread to send response
		 * if required
		 *
		 * @throws InterruptedException
		 */
		public void completeQueuedProcessing() throws InterruptedException {
			exceptionExchange.exchange(null);
		}

		/**
		 * @throws InterruptedException
		 */
		void waitForRelease(long timeout, java.util.concurrent.TimeUnit unit) throws InterruptedException {
			latch.await(timeout, unit);
		}

		public IN getInputMessage() {
			return input;
		}

		public void serveOutputMessage(OUT response) {
			output = response;
		}

		public void serveFault(Throwable fault) {
			this.fault = fault;
		}

		public boolean isFault() {
			return fault != null;
		}

		public OUT getResponse() {
			return output;
		}

		public Throwable getFault() {
			return fault;
		}
	}

	/**
	 * message exchange controller
	 */
	public class QueuedMessageHandlerImpl<IN, OUT> implements ESBProviderCallback {
		private final int MAX_QUEUE_SIZE = 1000;

		private final int WAIT_TIMEOUT_SECONDS = 120;

		private final java.util.concurrent.BlockingQueue<QueuedExchangeContextImpl<IN, OUT>> queue = new java.util.concurrent.LinkedBlockingQueue<QueuedExchangeContextImpl<IN, OUT>>(
				MAX_QUEUE_SIZE);

		/**
		 * This method add a newly created {@link QueuedExchangeContextImpl} into the
		 * internal blocking queue where consumer thread is waiting for it. Then it
		 * waits until the {@link QueuedExchangeContextImpl} will be completed for
		 * request-response operations
		 */
		public QueuedExchangeContextImpl<IN, OUT> invoke(IN request, boolean wait) {
			QueuedExchangeContextImpl<IN, OUT> context = new QueuedExchangeContextImpl<IN, OUT>(request);
			boolean inserted = queue.offer(context);
			if (!inserted) {
				try {
					context.release();
				} catch (Exception e) {
					e.printStackTrace();
				}
				// context.serveFault("job pool overflow exceed", null);
				throw new RuntimeException("Can't queue request, queue size of " + MAX_QUEUE_SIZE + " is exceeded");
			} else if (wait) {
				try {
					context.waitForRelease(WAIT_TIMEOUT_SECONDS, java.util.concurrent.TimeUnit.SECONDS);
				} catch (InterruptedException ie) {
					// context.serveFault("job execution timeout", ie);
					throw new RuntimeException("job execution timeout: " + ie.getMessage());
				}
			}
			return context;
		}

		QueuedExchangeContextImpl<IN, OUT> currentExchangeContext;

		public IN getRequest() throws ESBJobInterruptedException {
			currentExchangeContext = null;
			try {
				currentExchangeContext = queue.take();
			} catch (InterruptedException e) {
				throw new ESBJobInterruptedException("job interrupted", e);
			}
			return currentExchangeContext.getInputMessage();
		}

		public void sendResponse(Object output) {
			if (null == currentExchangeContext) {
				throw new RuntimeException("sendResponse() invoked before getRequest()");
			}

			if (output instanceof Throwable) {
				// fault
				currentExchangeContext.serveFault((Throwable) output);
			} else {
				// response
				currentExchangeContext.serveOutputMessage((OUT) output);
			}

			try {
				currentExchangeContext.release();
			} catch (Exception e) {
				// e.printStackTrace();
				throw new RuntimeException(e);
			}
		}
	}

	/**
	 * web service provider implementation
	 */
	@javax.xml.ws.ServiceMode(value = javax.xml.ws.Service.Mode.PAYLOAD)
	@javax.xml.ws.WebServiceProvider()
	public class ESBProvider_tESBProviderRequest_1 implements javax.xml.ws.Provider<javax.xml.transform.Source> {

		@javax.annotation.Resource
		private javax.xml.ws.WebServiceContext context;

		private QueuedMessageHandlerImpl<java.util.Map, org.dom4j.Document> messageHandler;

		public ESBProvider_tESBProviderRequest_1(
				QueuedMessageHandlerImpl<java.util.Map, org.dom4j.Document> messageHandler) {
			this.messageHandler = messageHandler;
		}

		public javax.xml.transform.Source invoke(javax.xml.transform.Source request) {

			try {
				java.util.Map<String, Object> esbRequest = new java.util.HashMap<String, Object>();
				esbRequest.put(ESBProviderCallback.HEADERS_SOAP,
						context.getMessageContext().get(org.apache.cxf.headers.Header.HEADER_LIST));
				esbRequest.put(ESBProviderCallback.HEADERS_HTTP,
						context.getMessageContext().get(javax.xml.ws.handler.MessageContext.HTTP_REQUEST_HEADERS));
				org.dom4j.Document requestDoc = null;
				if (request != null) {
					java.io.ByteArrayOutputStream os = new java.io.ByteArrayOutputStream();
					org.apache.cxf.staxutils.StaxUtils.copy(request, os);
					requestDoc = new org.dom4j.io.SAXReader().read(new java.io.ByteArrayInputStream(os.toByteArray()));
				} else {
					requestDoc = org.dom4j.DocumentHelper.createDocument();
					requestDoc.addElement("root", "");
				}
				esbRequest.put(ESBProviderCallback.REQUEST, requestDoc);
				QueuedExchangeContextImpl<java.util.Map, org.dom4j.Document> messageExchange = messageHandler
						.invoke(esbRequest, true);

				try {
					if (messageExchange.isFault()) {
						throw messageExchange.getFault();
					} else {
						org.dom4j.Document responseDoc = messageExchange.getResponse();
						if (null == responseDoc) {
							throw new RuntimeException("no response provided by Talend job");
						}
						// System.out.println("response: " + responseDoc.asXML());

						java.io.ByteArrayOutputStream baos = new java.io.ByteArrayOutputStream();
						new org.dom4j.io.XMLWriter(baos).write(responseDoc);
						return new javax.xml.transform.stream.StreamSource(
								new java.io.ByteArrayInputStream(baos.toByteArray()));
					}
				} finally {
					messageExchange.completeQueuedProcessing();
				}
			} catch (RuntimeException ex) {
				throw ex;
			} catch (Throwable ex) {
				ex.printStackTrace();
				throw new RuntimeException(ex);
			}
		}
	}

	interface ESBProviderCallbackTalendJobInner extends ESBProviderCallback {
		void setCustomProperties(java.util.Map<String, String> props);

		void sendFault(Throwable e);

		void sendFaultByDefault(Throwable e);

		void sendBusinessFault(String faultString, org.dom4j.Document faultDetail);
	}

	public class ESBProviderCallbackTalendJobWrapper_tESBProviderRequest_1
			implements ESBProviderCallbackTalendJobInner {

		private ESBProviderCallback esbProviderCallback;
		private java.util.Map<String, String> customProperty;

		public ESBProviderCallbackTalendJobWrapper_tESBProviderRequest_1(ESBProviderCallback callback) {
			esbProviderCallback = callback;
		}

		public Object getRequest() throws ESBJobInterruptedException {
			return esbProviderCallback.getRequest();
		}

		public void setCustomProperties(java.util.Map<String, String> props) {
			customProperty = props;
		}

		private void doSendResponse(Object obj) {
			if ((Boolean) globalMap.get("wsRequestReceivedAndResponseSentBack")) {
				throw new RuntimeException("Send Response failed! Response already sent with current request.");
			} else {
				globalMap.put("wsRequestReceivedAndResponseSentBack", true);
				esbProviderCallback.sendResponse(obj);
			}
		}

		public void sendResponse(Object response) {
			doSendResponse(wrapOutput(response));
		}

		public void sendFault(Throwable error) {
			RuntimeException talendJobError;
			if (error instanceof RuntimeException) {
				talendJobError = (RuntimeException) error;
			} else {
				String msg = "";
				if (error != null && error.getMessage() != null) {
					msg = error.getMessage();
				}
				talendJobError = new RuntimeException("Talend job execution error: " + msg, error);
			}
			doSendResponse(talendJobError);
		}

		public void sendFaultByDefault(Throwable error) {
			if (!(Boolean) globalMap.get("wsRequestReceivedAndResponseSentBack")) {
				sendFault(error);
			}
		}

		public void sendBusinessFault(String faultString, org.dom4j.Document faultDetail) {
			try {
				org.apache.cxf.interceptor.Fault soapFault = new org.apache.cxf.interceptor.Fault(faultString,
						(java.util.ResourceBundle) null);
				soapFault
						.setFaultCode(new javax.xml.namespace.QName("http://www.talend.org/service/", "businessFault"));

				if (null != faultDetail) {
					// wrap details
					org.dom4j.Element rootElement = faultDetail.getRootElement();
					org.dom4j.tree.DefaultElement newRootElement = new org.dom4j.tree.DefaultElement("details");
					faultDetail.setRootElement(newRootElement);
					newRootElement.add(rootElement);

					org.w3c.dom.Document faultDetailDom = convertDocument(faultDetail);
					soapFault.setDetail(faultDetailDom.getDocumentElement());
				}
				doSendResponse(wrapOutput(soapFault));
			} catch (Exception e) {
				this.sendFault(e);
			}
		}

		private org.w3c.dom.Document convertDocument(org.dom4j.Document document) throws Exception {
			// A special version of DOMWriter that does not write xmlns:foo attributes
			org.dom4j.io.DOMWriter writer = new org.dom4j.io.DOMWriter() {
				protected void writeNamespace(org.w3c.dom.Element domElement, org.dom4j.Namespace namespace) {
					// Do nothing
				}
			};
			return writer.write(document);
		}

		private Object wrapOutput(Object output) {
			if (esbProviderCallback instanceof QueuedMessageHandlerImpl) {
				return output;
			}
			return wrapPayload(output);
		}
	}

	class HandlerThread_tESBProviderRequest_1 extends Thread {

		private org.apache.cxf.endpoint.Server server;
		QueuedMessageHandlerImpl<java.util.Map, org.dom4j.Document> handler;

		String endpointUrl = "http://localhost:8090/services/UsersWS";

		public HandlerThread_tESBProviderRequest_1(
				QueuedMessageHandlerImpl<java.util.Map, org.dom4j.Document> handler) {
			this.handler = handler;
		}

		public void run() {

			// test for busy
			java.net.URI endpointURI = java.net.URI.create(endpointUrl);
			String host = endpointURI.getHost();
			try {
				if (java.net.InetAddress.getByName(host).isLoopbackAddress()) {
					int port = endpointURI.getPort();
					if (-1 == port) {
						port = 80;
					}
					java.net.ServerSocket ss = null;
					try {
						ss = new java.net.ServerSocket(port);
					} catch (IOException e) {
						// rethrow exception
						throw new IllegalArgumentException("Cannot start provider with uri: " + endpointUrl + ". Port "
								+ port + " already in use.");
					} finally {
						if (ss != null) {
							try {
								ss.close();
							} catch (IOException e) {
								// ignore
							}
						}
					}
					try {
						// ok, let's doublecheck for silent listeners
						java.net.Socket cs = new java.net.Socket(host, port);
						// if succeed - somebody silently listening, fail!
						cs.close();
						// rethrow exception
						throw new IllegalArgumentException("Cannot start provider with uri: " + endpointUrl + ". Port "
								+ port + " already in use.");
					} catch (IOException e) {
						// ok, nobody listens, proceed
					}
				}
			} catch (java.net.UnknownHostException e) {
				// ignore
			}

			org.apache.cxf.jaxws.JaxWsServerFactoryBean serverFactory = new org.apache.cxf.jaxws.JaxWsServerFactoryBean();
			serverFactory.setWsdlURL("C:/TOSESB/Studio/workspace/TP/services/UsersWS_0.1.wsdl");
			serverFactory.setServiceName(new javax.xml.namespace.QName("http://www.talend.org/service/", "UsersWS"));
			serverFactory
					.setEndpointName(new javax.xml.namespace.QName("http://www.talend.org/service/", "UsersWSPort"));
			// in case null - WSDL value used
			serverFactory.setAddress(endpointUrl);

			serverFactory.setServiceBean(new ESBProvider_tESBProviderRequest_1(handler));
			if (false) {
				serverFactory.getFeatures().add(new org.apache.cxf.feature.LoggingFeature());
			}
			server = serverFactory.create();

			System.out.println("web service [endpoint: " + endpointUrl + "] published");
		}

		public void stopEndpoint() {
			if (null != server) {
				server.stop();
				server.destroy();
				System.out.println("web service [endpoint: " + endpointUrl + "] unpublished");
			}
		}
	}

	public static class row2Struct implements routines.system.IPersistableComparableLookupRow<row2Struct> {
		final static byte[] commonByteArrayLock_TP_getUserbySOAP = new byte[0];
		static byte[] commonByteArray_TP_getUserbySOAP = new byte[0];
		protected static final int DEFAULT_HASHCODE = 1;
		protected static final int PRIME = 31;
		protected int hashCode = DEFAULT_HASHCODE;
		public boolean hashCodeDirty = true;

		public String loopKey;

		public Integer id;

		public Integer getId() {
			return this.id;
		}

		public String nom;

		public String getNom() {
			return this.nom;
		}

		public String prenom;

		public String getPrenom() {
			return this.prenom;
		}

		public String login;

		public String getLogin() {
			return this.login;
		}

		public String password;

		public String getPassword() {
			return this.password;
		}

		@Override
		public int hashCode() {
			if (this.hashCodeDirty) {
				final int prime = PRIME;
				int result = DEFAULT_HASHCODE;

				result = prime * result + ((this.nom == null) ? 0 : this.nom.hashCode());

				this.hashCode = result;
				this.hashCodeDirty = false;
			}
			return this.hashCode;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			final row2Struct other = (row2Struct) obj;

			if (this.nom == null) {
				if (other.nom != null)
					return false;

			} else if (!this.nom.equals(other.nom))

				return false;

			return true;
		}

		public void copyDataTo(row2Struct other) {

			other.id = this.id;
			other.nom = this.nom;
			other.prenom = this.prenom;
			other.login = this.login;
			other.password = this.password;

		}

		public void copyKeysDataTo(row2Struct other) {

			other.nom = this.nom;

		}

		private Integer readInteger(DataInputStream dis, ObjectInputStream ois) throws IOException {
			Integer intReturn;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = dis.readInt();
			}
			return intReturn;
		}

		private Integer readInteger(DataInputStream dis, org.jboss.marshalling.Unmarshaller unmarshaller)
				throws IOException {
			Integer intReturn;
			int length = 0;
			length = unmarshaller.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = unmarshaller.readInt();
			}
			return intReturn;
		}

		private void writeInteger(Integer intNum, DataOutputStream dos, ObjectOutputStream oos) throws IOException {
			if (intNum == null) {
				dos.writeByte(-1);
			} else {
				dos.writeByte(0);
				dos.writeInt(intNum);
			}
		}

		private void writeInteger(Integer intNum, DataOutputStream dos, org.jboss.marshalling.Marshaller marshaller)
				throws IOException {
			if (intNum == null) {
				marshaller.writeByte(-1);
			} else {
				marshaller.writeByte(0);
				marshaller.writeInt(intNum);
			}
		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_TP_getUserbySOAP.length) {
					if (length < 1024 && commonByteArray_TP_getUserbySOAP.length == 0) {
						commonByteArray_TP_getUserbySOAP = new byte[1024];
					} else {
						commonByteArray_TP_getUserbySOAP = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_TP_getUserbySOAP, 0, length);
				strReturn = new String(commonByteArray_TP_getUserbySOAP, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private String readString(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			String strReturn = null;
			int length = 0;
			length = unmarshaller.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_TP_getUserbySOAP.length) {
					if (length < 1024 && commonByteArray_TP_getUserbySOAP.length == 0) {
						commonByteArray_TP_getUserbySOAP = new byte[1024];
					} else {
						commonByteArray_TP_getUserbySOAP = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_TP_getUserbySOAP, 0, length);
				strReturn = new String(commonByteArray_TP_getUserbySOAP, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private void writeString(String str, ObjectOutputStream dos) throws IOException {
			if (str == null) {
				dos.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				dos.writeInt(byteArray.length);
				dos.write(byteArray);
			}
		}

		private void writeString(String str, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (str == null) {
				marshaller.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				marshaller.writeInt(byteArray.length);
				marshaller.write(byteArray);
			}
		}

		private String readString(DataInputStream dis, ObjectInputStream ois) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				byte[] byteArray = new byte[length];
				dis.read(byteArray);
				strReturn = new String(byteArray, utf8Charset);
			}
			return strReturn;
		}

		private String readString(DataInputStream dis, org.jboss.marshalling.Unmarshaller unmarshaller)
				throws IOException {
			String strReturn = null;
			int length = 0;
			length = unmarshaller.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				byte[] byteArray = new byte[length];
				unmarshaller.read(byteArray);
				strReturn = new String(byteArray, utf8Charset);
			}
			return strReturn;
		}

		private void writeString(String str, DataOutputStream dos, org.jboss.marshalling.Marshaller marshaller)
				throws IOException {
			if (str == null) {
				marshaller.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				marshaller.writeInt(byteArray.length);
				marshaller.write(byteArray);
			}
		}

		private void writeString(String str, DataOutputStream dos, ObjectOutputStream oos) throws IOException {
			if (str == null) {
				dos.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				dos.writeInt(byteArray.length);
				dos.write(byteArray);
			}
		}

		public void readKeysData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_TP_getUserbySOAP) {

				try {

					int length = 0;

					this.nom = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readKeysData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_TP_getUserbySOAP) {

				try {

					int length = 0;

					this.nom = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeKeysData(ObjectOutputStream dos) {
			try {

				// String

				writeString(this.nom, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeKeysData(org.jboss.marshalling.Marshaller dos) {
			try {

				// String

				writeString(this.nom, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		/**
		 * Fill Values data by reading ObjectInputStream.
		 */
		public void readValuesData(DataInputStream dis, ObjectInputStream ois) {
			try {

				int length = 0;

				this.id = readInteger(dis, ois);

				this.prenom = readString(dis, ois);

				this.login = readString(dis, ois);

				this.password = readString(dis, ois);

			} catch (IOException e) {
				throw new RuntimeException(e);

			}

		}

		public void readValuesData(DataInputStream dis, org.jboss.marshalling.Unmarshaller objectIn) {
			try {
				int length = 0;

				this.id = readInteger(dis, objectIn);

				this.prenom = readString(dis, objectIn);

				this.login = readString(dis, objectIn);

				this.password = readString(dis, objectIn);

			} catch (IOException e) {
				throw new RuntimeException(e);

			}

		}

		/**
		 * Return a byte array which represents Values data.
		 */
		public void writeValuesData(DataOutputStream dos, ObjectOutputStream oos) {
			try {

				writeInteger(this.id, dos, oos);

				writeString(this.prenom, dos, oos);

				writeString(this.login, dos, oos);

				writeString(this.password, dos, oos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeValuesData(DataOutputStream dos, org.jboss.marshalling.Marshaller objectOut) {
			try {

				writeInteger(this.id, dos, objectOut);

				writeString(this.prenom, dos, objectOut);

				writeString(this.login, dos, objectOut);

				writeString(this.password, dos, objectOut);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}

		public boolean supportMarshaller() {
			return true;
		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("id=" + String.valueOf(id));
			sb.append(",nom=" + nom);
			sb.append(",prenom=" + prenom);
			sb.append(",login=" + login);
			sb.append(",password=" + password);
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(row2Struct other) {

			int returnValue = -1;

			returnValue = checkNullsAndCompare(this.nom, other.nom);
			if (returnValue != 0) {
				return returnValue;
			}

			return returnValue;
		}

		private int checkNullsAndCompare(Object object1, Object object2) {
			int returnValue = 0;
			if (object1 instanceof Comparable && object2 instanceof Comparable) {
				returnValue = ((Comparable) object1).compareTo(object2);
			} else if (object1 != null && object2 != null) {
				returnValue = compareStrings(object1.toString(), object2.toString());
			} else if (object1 == null && object2 != null) {
				returnValue = 1;
			} else if (object1 != null && object2 == null) {
				returnValue = -1;
			} else {
				returnValue = 0;
			}

			return returnValue;
		}

		private int compareStrings(String string1, String string2) {
			return string1.compareTo(string2);
		}

	}

	public void tDBInput_2Process(final java.util.Map<String, Object> globalMap) throws TalendException {
		globalMap.put("tDBInput_2_SUBPROCESS_STATE", 0);

		final boolean execStat = this.execStat;

		String iterateId = "";

		String currentComponent = "";
		java.util.Map<String, Object> resourceMap = new java.util.HashMap<String, Object>();

		try {
			// TDI-39566 avoid throwing an useless Exception
			boolean resumeIt = true;
			if (globalResumeTicket == false && resumeEntryMethodName != null) {
				String currentMethodName = new java.lang.Exception().getStackTrace()[0].getMethodName();
				resumeIt = resumeEntryMethodName.equals(currentMethodName);
			}
			if (resumeIt || globalResumeTicket) { // start the resume
				globalResumeTicket = true;

				row2Struct row2 = new row2Struct();

				/**
				 * [tAdvancedXMLMapHash_row2 begin ] start
				 */

				ok_Hash.put("tAdvancedXMLMapHash_row2", false);
				start_Hash.put("tAdvancedXMLMapHash_row2", System.currentTimeMillis());

				currentComponent = "tAdvancedXMLMapHash_row2";

				if (execStat) {
					runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "row2");
				}

				int tos_count_tAdvancedXMLMapHash_row2 = 0;

				// connection name:row2
				// source node:tDBInput_2 - inputs:() outputs:(row2,row2) | target
				// node:tAdvancedXMLMapHash_row2 - inputs:(row2) outputs:()
				// linked node: tXMLMap_1 - inputs:(row1,row2) outputs:(response)

				org.talend.designer.components.lookup.common.ICommonLookup.MATCHING_MODE matchingModeEnum_row2 = org.talend.designer.components.lookup.common.ICommonLookup.MATCHING_MODE.ALL_MATCHES;

				org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row2Struct> tHash_Lookup_row2 = org.talend.designer.components.lookup.memory.AdvancedMemoryLookup
						.<row2Struct>getLookup(matchingModeEnum_row2);

				globalMap.put("tHash_Lookup_row2", tHash_Lookup_row2);

				/**
				 * [tAdvancedXMLMapHash_row2 begin ] stop
				 */

				/**
				 * [tDBInput_2 begin ] start
				 */

				ok_Hash.put("tDBInput_2", false);
				start_Hash.put("tDBInput_2", System.currentTimeMillis());

				currentComponent = "tDBInput_2";

				int tos_count_tDBInput_2 = 0;

				java.util.Calendar calendar_tDBInput_2 = java.util.Calendar.getInstance();
				calendar_tDBInput_2.set(0, 0, 0, 0, 0, 0);
				java.util.Date year0_tDBInput_2 = calendar_tDBInput_2.getTime();
				int nb_line_tDBInput_2 = 0;
				java.sql.Connection conn_tDBInput_2 = null;
				String driverClass_tDBInput_2 = "com.mysql.cj.jdbc.Driver";
				java.lang.Class jdbcclazz_tDBInput_2 = java.lang.Class.forName(driverClass_tDBInput_2);
				String dbUser_tDBInput_2 = "root";

				final String decryptedPassword_tDBInput_2 = routines.system.PasswordEncryptUtil.decryptPassword(
						"enc:routine.encryption.key.v1:VpVUg04P/IimAoOmLh8po6S+vuWaeMH4hADNX/yuywpwtc2yUw==");

				String dbPwd_tDBInput_2 = decryptedPassword_tDBInput_2;

				String properties_tDBInput_2 = "noDatetimeStringSync=true";
				if (properties_tDBInput_2 == null || properties_tDBInput_2.trim().length() == 0) {
					properties_tDBInput_2 = "";
				}
				String url_tDBInput_2 = "jdbc:mysql://" + "localhost" + ":" + "3306" + "/" + "talend" + "?"
						+ properties_tDBInput_2;

				conn_tDBInput_2 = java.sql.DriverManager.getConnection(url_tDBInput_2, dbUser_tDBInput_2,
						dbPwd_tDBInput_2);

				java.sql.Statement stmt_tDBInput_2 = conn_tDBInput_2.createStatement();

				String dbquery_tDBInput_2 = "SELECT\n\"public\".\"users\".\"id\",\n\"public\".\"users\".\"nom\",\n\"public\".\"users\".\"prenom\",\n\"public\".\"use"
						+ "rs\".\"login\",\n\"public\".\"users\".\"password\"\nFROM \"public\".\"users\"\nWHERE \"public\".\"users\".\"nom\" LIKE '"
						+ "%" + globalMap.get("nom") + "%'";

				globalMap.put("tDBInput_2_QUERY", dbquery_tDBInput_2);
				java.sql.ResultSet rs_tDBInput_2 = null;

				try {
					rs_tDBInput_2 = stmt_tDBInput_2.executeQuery(dbquery_tDBInput_2);
					java.sql.ResultSetMetaData rsmd_tDBInput_2 = rs_tDBInput_2.getMetaData();
					int colQtyInRs_tDBInput_2 = rsmd_tDBInput_2.getColumnCount();

					String tmpContent_tDBInput_2 = null;

					while (rs_tDBInput_2.next()) {
						nb_line_tDBInput_2++;

						if (colQtyInRs_tDBInput_2 < 1) {
							row2.id = null;
						} else {

							row2.id = rs_tDBInput_2.getInt(1);
							if (rs_tDBInput_2.wasNull()) {
								row2.id = null;
							}
						}
						if (colQtyInRs_tDBInput_2 < 2) {
							row2.nom = null;
						} else {

							row2.nom = routines.system.JDBCUtil.getString(rs_tDBInput_2, 2, false);
						}
						if (colQtyInRs_tDBInput_2 < 3) {
							row2.prenom = null;
						} else {

							row2.prenom = routines.system.JDBCUtil.getString(rs_tDBInput_2, 3, false);
						}
						if (colQtyInRs_tDBInput_2 < 4) {
							row2.login = null;
						} else {

							row2.login = routines.system.JDBCUtil.getString(rs_tDBInput_2, 4, false);
						}
						if (colQtyInRs_tDBInput_2 < 5) {
							row2.password = null;
						} else {

							row2.password = routines.system.JDBCUtil.getString(rs_tDBInput_2, 5, false);
						}

						/**
						 * [tDBInput_2 begin ] stop
						 */

						/**
						 * [tDBInput_2 main ] start
						 */

						currentComponent = "tDBInput_2";

						tos_count_tDBInput_2++;

						/**
						 * [tDBInput_2 main ] stop
						 */

						/**
						 * [tDBInput_2 process_data_begin ] start
						 */

						currentComponent = "tDBInput_2";

						/**
						 * [tDBInput_2 process_data_begin ] stop
						 */

						/**
						 * [tAdvancedXMLMapHash_row2 main ] start
						 */

						currentComponent = "tAdvancedXMLMapHash_row2";

						if (execStat) {
							runStat.updateStatOnConnection(iterateId, 1, 1

									, "row2"

							);
						}

						row2Struct row2_HashRow = new row2Struct();

						row2_HashRow.id = row2.id;

						row2_HashRow.nom = row2.nom;

						row2_HashRow.prenom = row2.prenom;

						row2_HashRow.login = row2.login;

						row2_HashRow.password = row2.password;

						tHash_Lookup_row2.put(row2_HashRow);

						tos_count_tAdvancedXMLMapHash_row2++;

						/**
						 * [tAdvancedXMLMapHash_row2 main ] stop
						 */

						/**
						 * [tAdvancedXMLMapHash_row2 process_data_begin ] start
						 */

						currentComponent = "tAdvancedXMLMapHash_row2";

						/**
						 * [tAdvancedXMLMapHash_row2 process_data_begin ] stop
						 */

						/**
						 * [tAdvancedXMLMapHash_row2 process_data_end ] start
						 */

						currentComponent = "tAdvancedXMLMapHash_row2";

						/**
						 * [tAdvancedXMLMapHash_row2 process_data_end ] stop
						 */

						/**
						 * [tDBInput_2 process_data_end ] start
						 */

						currentComponent = "tDBInput_2";

						/**
						 * [tDBInput_2 process_data_end ] stop
						 */

						/**
						 * [tDBInput_2 end ] start
						 */

						currentComponent = "tDBInput_2";

					}
				} finally {
					if (rs_tDBInput_2 != null) {
						rs_tDBInput_2.close();
					}
					if (stmt_tDBInput_2 != null) {
						stmt_tDBInput_2.close();
					}
					if (conn_tDBInput_2 != null && !conn_tDBInput_2.isClosed()) {

						conn_tDBInput_2.close();

						if ("com.mysql.cj.jdbc.Driver".equals((String) globalMap.get("driverClass_"))
								&& routines.system.BundleUtils.inOSGi()) {
							Class.forName("com.mysql.cj.jdbc.AbandonedConnectionCleanupThread")
									.getMethod("checkedShutdown").invoke(null, (Object[]) null);
						}

					}

				}

				globalMap.put("tDBInput_2_NB_LINE", nb_line_tDBInput_2);

				ok_Hash.put("tDBInput_2", true);
				end_Hash.put("tDBInput_2", System.currentTimeMillis());

				/**
				 * [tDBInput_2 end ] stop
				 */

				/**
				 * [tAdvancedXMLMapHash_row2 end ] start
				 */

				currentComponent = "tAdvancedXMLMapHash_row2";

				tHash_Lookup_row2.endPut();

				if (execStat) {
					runStat.updateStat(resourceMap, iterateId, 2, 0, "row2");
				}

				ok_Hash.put("tAdvancedXMLMapHash_row2", true);
				end_Hash.put("tAdvancedXMLMapHash_row2", System.currentTimeMillis());

				/**
				 * [tAdvancedXMLMapHash_row2 end ] stop
				 */

			} // end the resume

		} catch (java.lang.Exception e) {

			TalendException te = new TalendException(e, currentComponent, globalMap);

			throw te;
		} catch (java.lang.Error error) {

			runStat.stopThreadStat();

			throw error;
		} finally {

			try {

				/**
				 * [tDBInput_2 finally ] start
				 */

				currentComponent = "tDBInput_2";

				/**
				 * [tDBInput_2 finally ] stop
				 */

				/**
				 * [tAdvancedXMLMapHash_row2 finally ] start
				 */

				currentComponent = "tAdvancedXMLMapHash_row2";

				/**
				 * [tAdvancedXMLMapHash_row2 finally ] stop
				 */

			} catch (java.lang.Exception e) {
				// ignore
			} catch (java.lang.Error error) {
				// ignore
			}
			resourceMap = null;
		}

		globalMap.put("tDBInput_2_SUBPROCESS_STATE", 1);
	}

	public static class responseStruct implements routines.system.IPersistableRow<responseStruct> {
		final static byte[] commonByteArrayLock_TP_getUserbySOAP = new byte[0];
		static byte[] commonByteArray_TP_getUserbySOAP = new byte[0];

		public routines.system.Document payload;

		public routines.system.Document getPayload() {
			return this.payload;
		}

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_TP_getUserbySOAP) {

				try {

					int length = 0;

					this.payload = (routines.system.Document) dis.readObject();

				} catch (IOException e) {
					throw new RuntimeException(e);

				} catch (ClassNotFoundException eCNFE) {
					throw new RuntimeException(eCNFE);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_TP_getUserbySOAP) {

				try {

					int length = 0;

					this.payload = (routines.system.Document) dis.readObject();

				} catch (IOException e) {
					throw new RuntimeException(e);

				} catch (ClassNotFoundException eCNFE) {
					throw new RuntimeException(eCNFE);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// Document

				dos.writeObject(this.payload);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// Document

				dos.writeObject(this.payload);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("payload=" + String.valueOf(payload));
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(responseStruct other) {

			int returnValue = -1;

			return returnValue;
		}

		private int checkNullsAndCompare(Object object1, Object object2) {
			int returnValue = 0;
			if (object1 instanceof Comparable && object2 instanceof Comparable) {
				returnValue = ((Comparable) object1).compareTo(object2);
			} else if (object1 != null && object2 != null) {
				returnValue = compareStrings(object1.toString(), object2.toString());
			} else if (object1 == null && object2 != null) {
				returnValue = 1;
			} else if (object1 != null && object2 == null) {
				returnValue = -1;
			} else {
				returnValue = 0;
			}

			return returnValue;
		}

		private int compareStrings(String string1, String string2) {
			return string1.compareTo(string2);
		}

	}

	public static class row1Struct implements routines.system.IPersistableRow<row1Struct> {
		final static byte[] commonByteArrayLock_TP_getUserbySOAP = new byte[0];
		static byte[] commonByteArray_TP_getUserbySOAP = new byte[0];

		public routines.system.Document payload;

		public routines.system.Document getPayload() {
			return this.payload;
		}

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_TP_getUserbySOAP) {

				try {

					int length = 0;

					this.payload = (routines.system.Document) dis.readObject();

				} catch (IOException e) {
					throw new RuntimeException(e);

				} catch (ClassNotFoundException eCNFE) {
					throw new RuntimeException(eCNFE);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_TP_getUserbySOAP) {

				try {

					int length = 0;

					this.payload = (routines.system.Document) dis.readObject();

				} catch (IOException e) {
					throw new RuntimeException(e);

				} catch (ClassNotFoundException eCNFE) {
					throw new RuntimeException(eCNFE);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// Document

				dos.writeObject(this.payload);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// Document

				dos.writeObject(this.payload);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("payload=" + String.valueOf(payload));
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(row1Struct other) {

			int returnValue = -1;

			return returnValue;
		}

		private int checkNullsAndCompare(Object object1, Object object2) {
			int returnValue = 0;
			if (object1 instanceof Comparable && object2 instanceof Comparable) {
				returnValue = ((Comparable) object1).compareTo(object2);
			} else if (object1 != null && object2 != null) {
				returnValue = compareStrings(object1.toString(), object2.toString());
			} else if (object1 == null && object2 != null) {
				returnValue = 1;
			} else if (object1 != null && object2 == null) {
				returnValue = -1;
			} else {
				returnValue = 0;
			}

			return returnValue;
		}

		private int compareStrings(String string1, String string2) {
			return string1.compareTo(string2);
		}

	}

	public void tESBProviderRequest_1_LoopProcess(final java.util.Map<String, Object> globalMap)
			throws TalendException {
		globalMap.put("tESBProviderRequest_1_Loop_SUBPROCESS_STATE", 0);

		final boolean execStat = this.execStat;
		String currentVirtualComponent = null;

		String iterateId = "";

		String currentComponent = "";
		java.util.Map<String, Object> resourceMap = new java.util.HashMap<String, Object>();

		try {
			// TDI-39566 avoid throwing an useless Exception
			boolean resumeIt = true;
			if (globalResumeTicket == false && resumeEntryMethodName != null) {
				String currentMethodName = new java.lang.Exception().getStackTrace()[0].getMethodName();
				resumeIt = resumeEntryMethodName.equals(currentMethodName);
			}
			if (resumeIt || globalResumeTicket) { // start the resume
				globalResumeTicket = true;

				row1Struct row1 = new row1Struct();
				responseStruct response = new responseStruct();

				/**
				 * [tESBProviderRequest_1_Loop begin ] start
				 */

				int NB_ITERATE_tESBProviderRequest_1_In = 0; // for statistics

				ok_Hash.put("tESBProviderRequest_1_Loop", false);
				start_Hash.put("tESBProviderRequest_1_Loop", System.currentTimeMillis());

				currentVirtualComponent = "tESBProviderRequest_1";

				currentComponent = "tESBProviderRequest_1_Loop";

				int tos_count_tESBProviderRequest_1_Loop = 0;

				if (execStat) {
					runStat.updateStatOnConnection(iterateId, 0, 0, "row1", "response");
				}

//*** external processor(s) initialization
				ESBProviderCallbackTalendJobInner providerCallback_tESBProviderRequest_1;
				HandlerThread_tESBProviderRequest_1 handlerThread_tESBProviderRequest_1 = null;
				if (null == this.callback) {
					final QueuedMessageHandlerImpl<java.util.Map, org.dom4j.Document> handler_tESBProviderRequest_1 = new QueuedMessageHandlerImpl<java.util.Map, org.dom4j.Document>();
					handlerThread_tESBProviderRequest_1 = new HandlerThread_tESBProviderRequest_1(
							handler_tESBProviderRequest_1); //
					handlerThread_tESBProviderRequest_1.start();
					providerCallback_tESBProviderRequest_1 = new ESBProviderCallbackTalendJobWrapper_tESBProviderRequest_1(
							handler_tESBProviderRequest_1);
				} else {
					providerCallback_tESBProviderRequest_1 = new ESBProviderCallbackTalendJobWrapper_tESBProviderRequest_1(
							this.callback);
				}
				globalMap.put("esbHandler", providerCallback_tESBProviderRequest_1);
//*** external processor(s) initialization finish

				int nb_line_tESBProviderRequest_1 = 0;

				try {
					// This is a beginning of the ESB provider request component cycle
					globalMap.put("wsRequestReceivedAndResponseSentBack", true);
					while (true) {
						try {
							ESBProviderCallbackTalendJobInner esbHandler_tESBProviderRequest_1 = (ESBProviderCallbackTalendJobInner) globalMap
									.get("esbHandler");

							if (!(Boolean) globalMap.get("wsRequestReceivedAndResponseSentBack")) {
								// esbHandler_tESBProviderRequest_1.sendFault(new
								// RuntimeException("response/fault is not provided by Talend job"));
								esbHandler_tESBProviderRequest_1
										.sendResponse(org.dom4j.DocumentHelper.createDocument());
							}

							java.util.Map<String, Object> request_tESBProviderRequest_1 = (java.util.Map<String, Object>) esbHandler_tESBProviderRequest_1
									.getRequest();
							globalMap.put("tESBProviderRequest_1_" + ESBProviderCallback.HEADERS_SOAP,
									request_tESBProviderRequest_1.get(ESBProviderCallback.HEADERS_SOAP));
							globalMap.put("tESBProviderRequest_1_" + ESBProviderCallback.HEADERS_HTTP,
									request_tESBProviderRequest_1.get(ESBProviderCallback.HEADERS_HTTP));
							java.util.Collection<org.apache.cxf.headers.Header> existingSoapHeaders_tESBProviderRequest_1 = (java.util.Collection<org.apache.cxf.headers.Header>) request_tESBProviderRequest_1
									.get(ESBProviderCallback.HEADERS_SOAP);
							if (null != existingSoapHeaders_tESBProviderRequest_1) {
								for (org.apache.cxf.headers.Header existingHeader : existingSoapHeaders_tESBProviderRequest_1) {
									if (org.apache.wss4j.common.WSS4JConstants.WSSE_NS
											.equals(existingHeader.getName().getNamespaceURI())
											&& org.apache.wss4j.common.WSS4JConstants.WSSE_LN
													.equals(existingHeader.getName().getLocalPart())) {
										org.w3c.dom.NodeList nl = ((org.w3c.dom.Element) existingHeader.getObject())
												.getElementsByTagNameNS(org.apache.wss4j.common.WSS4JConstants.SAML2_NS,
														org.apache.wss4j.common.WSS4JConstants.ASSERTION_LN);
										if (nl.getLength() > 0) {
											globalMap.put("tESBProviderRequest_1_SECURITY_TOKEN", nl.item(0));
											break;
										}
									}
								}
							}
							globalMap.put("tESBProviderRequest_1_" + "CORRELATION_ID",
									request_tESBProviderRequest_1.get("CorrelationID"));
							org.dom4j.Document requestMessage_tESBProviderRequest_1 = (org.dom4j.Document) request_tESBProviderRequest_1
									.get(ESBProviderCallback.REQUEST);

							globalMap.put("wsRequestReceivedAndResponseSentBack", false);

							/**
							 * [tESBProviderRequest_1_Loop begin ] stop
							 */

							/**
							 * [tESBProviderRequest_1_Loop main ] start
							 */

							currentVirtualComponent = "tESBProviderRequest_1";

							currentComponent = "tESBProviderRequest_1_Loop";

							resourceMap.put("inIterateVComp", true);

							tos_count_tESBProviderRequest_1_Loop++;

							/**
							 * [tESBProviderRequest_1_Loop main ] stop
							 */

							/**
							 * [tESBProviderRequest_1_Loop process_data_begin ] start
							 */

							currentVirtualComponent = "tESBProviderRequest_1";

							currentComponent = "tESBProviderRequest_1_Loop";

							/**
							 * [tESBProviderRequest_1_Loop process_data_begin ] stop
							 */
							NB_ITERATE_tESBProviderRequest_1_In++;

							if (execStat) {
								runStat.updateStatOnConnection("Iterate", 1,
										"exec" + NB_ITERATE_tESBProviderRequest_1_In);
								// Thread.sleep(1000);
							}

							/**
							 * [tXMLMap_1_TXMLMAP_OUT begin ] start
							 */

							ok_Hash.put("tXMLMap_1_TXMLMAP_OUT", false);
							start_Hash.put("tXMLMap_1_TXMLMAP_OUT", System.currentTimeMillis());

							currentVirtualComponent = "tXMLMap_1";

							currentComponent = "tXMLMap_1_TXMLMAP_OUT";

							if (execStat) {
								runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "row1");
							}

							int tos_count_tXMLMap_1_TXMLMAP_OUT = 0;

							org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row2Struct> tHash_Lookup_row2 = null;

							row2Struct row2HashKey = new row2Struct();
							row2Struct row2Default = new row2Struct();
							row2Struct row2 = new row2Struct();

//===============================input xml init part===============================
							class XML_API_tXMLMap_1_TXMLMAP_OUT {
								public boolean isDefNull(org.dom4j.Node node)
										throws javax.xml.transform.TransformerException {
									if (node != null && node instanceof org.dom4j.Element) {
										org.dom4j.Attribute attri = ((org.dom4j.Element) node).attribute("nil");
										if (attri != null && ("true").equals(attri.getText())) {
											return true;
										}
									}
									return false;
								}

								public boolean isMissing(org.dom4j.Node node)
										throws javax.xml.transform.TransformerException {
									return node == null ? true : false;
								}

								public boolean isEmpty(org.dom4j.Node node)
										throws javax.xml.transform.TransformerException {
									if (node != null) {
										return node.getText().length() == 0;
									}
									return false;
								}
							}
							class Var__tXMLMap_1_TXMLMAP_OUT__Struct {
							}
							Var__tXMLMap_1_TXMLMAP_OUT__Struct Var__tXMLMap_1_TXMLMAP_OUT = new Var__tXMLMap_1_TXMLMAP_OUT__Struct();
// ###############################
// # Outputs initialization
							responseStruct response_tmp = new responseStruct();
							responseStruct response_save = null;
//the aggregate variable
							responseStruct response_aggregate = null;
//init the resultset for aggregate
							java.util.List<Object> allOutsForAggregate_tXMLMap_1 = new java.util.ArrayList<Object>();
							globalMap.put("allOutsForAggregate_tXMLMap_1", allOutsForAggregate_tXMLMap_1);
// ###############################
							class TreeNode_API_tXMLMap_1_TXMLMAP_OUT {
								java.util.Map<String, String> xpath_value_map = new java.util.HashMap<String, String>();

								void clear() {
									xpath_value_map.clear();
								}

								void put(String xpath, String value) {
									xpath_value_map.put(xpath, value);
								}

								String get_null(String xpath) {
									return null;
								}

								String get_String(String xpath) {
									return xpath_value_map.get(xpath);
								}
							}
							TreeNode_API_tXMLMap_1_TXMLMAP_OUT treeNodeAPI_tXMLMap_1_TXMLMAP_OUT = new TreeNode_API_tXMLMap_1_TXMLMAP_OUT();
							NameSpaceTool nsTool_tXMLMap_1_TXMLMAP_OUT = new NameSpaceTool();
							int nb_line_tXMLMap_1_TXMLMAP_OUT = 0;

							XML_API_tXMLMap_1_TXMLMAP_OUT xml_api_tXMLMap_1_TXMLMAP_OUT = new XML_API_tXMLMap_1_TXMLMAP_OUT();

							// the map store the previous value of aggregate columns
							java.util.Map<String, Object> aggregateCacheMap_tXMLMap_1_TXMLMAP_OUT = new java.util.HashMap<String, Object>();

							class GenerateDocument_response {

								TreeNode_API_tXMLMap_1_TXMLMAP_OUT treeNodeAPI = null;
								java.util.Map<String, Object> valueMap = null;

								routines.system.DocumentGenerateOrderHelper orderHelper = new routines.system.DocumentGenerateOrderHelper(
										1);

								org.dom4j.Document doc = null;

								org.dom4j.Element root4Group = null;

								org.dom4j.io.OutputFormat format = null;

								java.util.List<java.util.List<String>> groupbyList = null;
								java.util.List<org.dom4j.Element> groupElementList = null;
								int order = 0;

								boolean isFirst = true;

								boolean needRoot = true;

								String currentValue = null;

								org.dom4j.Element subTreeLoopParent0 = null;
								public boolean subTreeLoop0 = false;

								public GenerateDocument_response() {
//    	this.treeNodeAPI = treeNodeAPI;

									valueMap = new java.util.HashMap<String, Object>();

									groupbyList = new java.util.ArrayList<java.util.List<String>>();
									groupElementList = new java.util.ArrayList<org.dom4j.Element>();

									doc = org.dom4j.DocumentHelper.createDocument();
									format = org.dom4j.io.OutputFormat.createPrettyPrint();
									format.setTrimText(false);
								}

								public org.dom4j.Document getDocument() {
									return this.doc;
								}

								// We generate the TreeNode_API object only if there is a document in the main
								// input table.
								void generateElements(TreeNode_API_tXMLMap_1_TXMLMAP_OUT treeNodeAPI,
										boolean isInnerJoin, row1Struct row1, row2Struct row2,
										Var__tXMLMap_1_TXMLMAP_OUT__Struct Var) {

									/*
									 * if(this.treeNodeAPI==null) { this.treeNodeAPI = treeNodeAPI; }
									 */

									org.dom4j.Element subTreeRootParent = null;
// build root xml tree 
									if (needRoot) {
										needRoot = false;
										org.dom4j.Element root = null;
										root = doc.addElement("tns:getUserbyNomResponse");
										subTreeRootParent = root;
										root.addNamespace("tns", TalendString
												.replaceSpecialCharForXML("http://www.talend.org/service/"));
										routines.system.DocumentHelper.applyNamespace2(root, "getUserbyNomResponse",
												"tns", "http://www.talend.org/service/");
										org.dom4j.Element root_1 = null;
										root_1 = root.addElement("users");
										subTreeRootParent = root_1;
										subTreeLoopParent0 = root_1;
										root4Group = subTreeRootParent;
									} else {
										subTreeRootParent = root4Group;
									}
									/* build group xml tree */
									boolean isNewElement = false;
									isNewElement = false;
									org.dom4j.Element loop = null;
									loop = org.dom4j.DocumentHelper.createElement("user");
									subTreeRootParent.elements().add(orderHelper.getInsertLocation(0, 0), loop);
									subTreeRootParent = loop;
									currentValue = null;
									valueMap.put("loop", row2.id);
									if (valueMap.get("loop") != null) {
										currentValue = FormatterUtils.fm(valueMap.get("loop"), null);
									} else {
										currentValue = "";
									}

									routines.system.DocumentHelper.applyNamespace2Attribute(loop, null, "id",
											currentValue);
									org.dom4j.Element loop_3 = null;
									loop_3 = loop.addElement("nom");
									valueMap.put("loop_3", row2.nom);
									if (valueMap.get("loop_3") != null) {
										routines.system.NestXMLTool.setText(loop_3,
												FormatterUtils.fm(valueMap.get("loop_3"), null));
									}
									org.dom4j.Element loop_4 = null;
									loop_4 = loop.addElement("prenom");
									valueMap.put("loop_4", row2.prenom);
									if (valueMap.get("loop_4") != null) {
										routines.system.NestXMLTool.setText(loop_4,
												FormatterUtils.fm(valueMap.get("loop_4"), null));
									}
									org.dom4j.Element loop_5 = null;
									loop_5 = loop.addElement("login");
									valueMap.put("loop_5", row2.login);
									if (valueMap.get("loop_5") != null) {
										routines.system.NestXMLTool.setText(loop_5,
												FormatterUtils.fm(valueMap.get("loop_5"), null));
									}
									org.dom4j.Element loop_6 = null;
									loop_6 = loop.addElement("password");
									valueMap.put("loop_6", row2.password);
									if (valueMap.get("loop_6") != null) {
										routines.system.NestXMLTool.setText(loop_6,
												FormatterUtils.fm(valueMap.get("loop_6"), null));
									}
								}
							}

							GenerateDocument_response gen_Doc_response_tXMLMap_1_TXMLMAP_OUT = new GenerateDocument_response();
							boolean docAlreadyInstanciate_response = false;

							/**
							 * [tXMLMap_1_TXMLMAP_OUT begin ] stop
							 */

							/**
							 * [tESBProviderRequest_1_In begin ] start
							 */

							ok_Hash.put("tESBProviderRequest_1_In", false);
							start_Hash.put("tESBProviderRequest_1_In", System.currentTimeMillis());

							currentVirtualComponent = "tESBProviderRequest_1";

							currentComponent = "tESBProviderRequest_1_In";

							int tos_count_tESBProviderRequest_1_In = 0;

							resourceMap.remove("inIterateVComp");

							/**
							 * [tESBProviderRequest_1_In begin ] stop
							 */

							/**
							 * [tESBProviderRequest_1_In main ] start
							 */

							currentVirtualComponent = "tESBProviderRequest_1";

							currentComponent = "tESBProviderRequest_1_In";

							routines.system.Document talendDocument_tESBProviderRequest_1 = new routines.system.Document();
							talendDocument_tESBProviderRequest_1.setDocument(requestMessage_tESBProviderRequest_1);
							row1.payload = talendDocument_tESBProviderRequest_1;

							tos_count_tESBProviderRequest_1_In++;

							/**
							 * [tESBProviderRequest_1_In main ] stop
							 */

							/**
							 * [tESBProviderRequest_1_In process_data_begin ] start
							 */

							currentVirtualComponent = "tESBProviderRequest_1";

							currentComponent = "tESBProviderRequest_1_In";

							/**
							 * [tESBProviderRequest_1_In process_data_begin ] stop
							 */

							/**
							 * [tXMLMap_1_TXMLMAP_OUT main ] start
							 */

							currentVirtualComponent = "tXMLMap_1";

							currentComponent = "tXMLMap_1_TXMLMAP_OUT";

							if (execStat) {
								runStat.updateStatOnConnection(iterateId, 1, 1

										, "row1"

								);
							}

							boolean rejectedInnerJoin_tXMLMap_1_TXMLMAP_OUT = false;
							boolean rejectedDocInnerJoin_tXMLMap_1_TXMLMAP_OUT = false;
							boolean mainRowRejected_tXMLMap_1_TXMLMAP_OUT = false;
							boolean isMatchDocRowtXMLMap_1_TXMLMAP_OUT = false;

							response_tmp.payload = null;

							// init document to flat tool
							routines.system.DocumentToFlat docToFlat_tXMLMap_1_TXMLMAP_OUT = new routines.system.DocumentToFlat();
							docToFlat_tXMLMap_1_TXMLMAP_OUT.setOriginalLoop("/tns:getUserbyNom/nom");
							docToFlat_tXMLMap_1_TXMLMAP_OUT.setIsOptional(false);
							if (row1.payload == null || row1.payload.getDocument() == null) {
								throw new RuntimeException("row1.payload can't be empty");
							}
							org.dom4j.Document doc_tXMLMap_1_TXMLMAP_OUT = row1.payload.getDocument();
							docToFlat_tXMLMap_1_TXMLMAP_OUT.setDoc(doc_tXMLMap_1_TXMLMAP_OUT);
							docToFlat_tXMLMap_1_TXMLMAP_OUT.setDefineNS(true);
							docToFlat_tXMLMap_1_TXMLMAP_OUT.setNamespaceTool(nsTool_tXMLMap_1_TXMLMAP_OUT);

							java.util.HashMap<String, String> xmlNameSpaceMap_tXMLMap_1_TXMLMAP_OUT = new java.util.HashMap<String, String>();
							xmlNameSpaceMap_tXMLMap_1_TXMLMAP_OUT.put("tns", "http://www.talend.org/service/");

							docToFlat_tXMLMap_1_TXMLMAP_OUT.setXmlNameSpaceMap(xmlNameSpaceMap_tXMLMap_1_TXMLMAP_OUT);

							String[] absolutePathMappings_tXMLMap_1_TXMLMAP_OUT = new String[1];
							String[] relativePathMappings_tXMLMap_1_TXMLMAP_OUT = new String[1];

							absolutePathMappings_tXMLMap_1_TXMLMAP_OUT[0] = "row1.payload:/tns:getUserbyNom/nom";
							relativePathMappings_tXMLMap_1_TXMLMAP_OUT[0] = ".";

							docToFlat_tXMLMap_1_TXMLMAP_OUT
									.setAbsolutePathMappings(absolutePathMappings_tXMLMap_1_TXMLMAP_OUT);
							docToFlat_tXMLMap_1_TXMLMAP_OUT
									.setCurrentRelativePathMappings(relativePathMappings_tXMLMap_1_TXMLMAP_OUT);
							// generate document to flat data
							docToFlat_tXMLMap_1_TXMLMAP_OUT.flat();
							// get flat data
							java.util.List<java.util.Map<String, String>> resultSet_tXMLMap_1_TXMLMAP_OUT = docToFlat_tXMLMap_1_TXMLMAP_OUT
									.getResultSet();

							for (java.util.Map<String, String> oneRow_tXMLMap_1_TXMLMAP_OUT : resultSet_tXMLMap_1_TXMLMAP_OUT) { // G_TXM_M_001
								nb_line_tXMLMap_1_TXMLMAP_OUT++;
								rejectedInnerJoin_tXMLMap_1_TXMLMAP_OUT = false;
								rejectedDocInnerJoin_tXMLMap_1_TXMLMAP_OUT = false;
								mainRowRejected_tXMLMap_1_TXMLMAP_OUT = false;
								isMatchDocRowtXMLMap_1_TXMLMAP_OUT = false;

								treeNodeAPI_tXMLMap_1_TXMLMAP_OUT.clear();
								for (java.util.Map.Entry<String, String> entry_tXMLMap_1_TXMLMAP_OUT : oneRow_tXMLMap_1_TXMLMAP_OUT
										.entrySet()) {
									treeNodeAPI_tXMLMap_1_TXMLMAP_OUT.put(entry_tXMLMap_1_TXMLMAP_OUT.getKey(),
											entry_tXMLMap_1_TXMLMAP_OUT.getValue());
								}

								///////////////////////////////////////////////
								// Starting Lookup Table
								/////////////////////////////////////////////// "org.talend.designer.xmlmap.model.emf.xmlmap.impl.InputXmlTreeImpl@62e08aa4
								/////////////////////////////////////////////// (expressionFilter: null,
								/////////////////////////////////////////////// activateExpressionFilter: false,
								/////////////////////////////////////////////// activateCondensedTool: true,
								/////////////////////////////////////////////// minimized: false, name: row2,
								/////////////////////////////////////////////// multiLoops: false) (lookup:
								/////////////////////////////////////////////// true, matchingMode: ALL_MATCHES,
								/////////////////////////////////////////////// lookupMode: RELOAD, innerJoin:
								/////////////////////////////////////////////// false, persistent: false,
								/////////////////////////////////////////////// activateGlobalMap: true)"
								///////////////////////////////////////////////
								// tHash_Lookup_row2.initGet();

								boolean forceLooprow2 = false;
								row2Struct row2ObjectFromLookup = null;
								boolean hasResultFromLookupCacherow2 = false;
								java.util.Map<String, Object> cacheResultrow2 = new java.util.HashMap<String, Object>();

								row2HashKey.nom = treeNodeAPI_tXMLMap_1_TXMLMAP_OUT
										.get_String("row1.payload:/tns:getUserbyNom/nom");
								row2HashKey.hashCodeDirty = true;

								if (!rejectedInnerJoin_tXMLMap_1_TXMLMAP_OUT) {// TD120

									globalMap.put("nom", treeNodeAPI_tXMLMap_1_TXMLMAP_OUT
											.get_String("row1.payload:/tns:getUserbyNom/nom"));

									tDBInput_2Process(globalMap);

									tHash_Lookup_row2 = (org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row2Struct>) ((org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row2Struct>) globalMap
											.get("tHash_Lookup_row2"));

									tHash_Lookup_row2.initGet();

									tHash_Lookup_row2.lookup(row2HashKey);

									if (hasResultFromLookupCacherow2 || tHash_Lookup_row2.hasNext()) {

									} else {

										row2 = row2Default;

										forceLooprow2 = true;

									}
								} // TD120

								if (rejectedInnerJoin_tXMLMap_1_TXMLMAP_OUT) {
									forceLooprow2 = true;
								}

								row2Struct fromLookup_row2 = null;
								row2 = row2Default;

								//////////////////////////////////////////////////////////////////////////////////////////////

								boolean fromCacherow2 = hasResultFromLookupCacherow2;
								List<java.util.Map<String, Object>> multipleResultSetrow2 = new java.util.ArrayList<java.util.Map<String, Object>>();
								// the var for cache the row2Struct
								java.util.Map<String, Object> oneRow_row2 = null;
								if (hasResultFromLookupCacherow2) {

								} else if (!rejectedInnerJoin_tXMLMap_1_TXMLMAP_OUT) {

									while (tHash_Lookup_row2.hasNext()) {
										fromLookup_row2 = null;
										row2 = row2Default;
										fromLookup_row2 = tHash_Lookup_row2.next();
										if (fromLookup_row2 != null) {

											row2 = fromLookup_row2;
											// construct the resultset for mutiple lookup when no document lookup.
											oneRow_row2 = new java.util.HashMap<String, Object>();
											oneRow_row2.put("row2", row2);
											multipleResultSetrow2.add(oneRow_row2);

										}
									} // end while

								} // end else if
									// now not support cache all_matches lookup

								java.util.Iterator<java.util.Map<String, Object>> iterrow2 = multipleResultSetrow2
										.iterator();
								while (iterrow2.hasNext() || forceLooprow2) { // G_TM_M_002
									row2 = row2Default;
									row2Struct tempLookuprow2 = null;
									if (!forceLooprow2) {
										java.util.Map<String, Object> oneRowrow2 = iterrow2.next();

										tempLookuprow2 = (row2Struct) oneRowrow2.get("row2");
										if (tempLookuprow2 != null) {
											row2 = tempLookuprow2;
										}

									}
									forceLooprow2 = false;

									/////////////////////////////////////////////////////////////////////////////////////////////////

									{ // start of Var scope

										// ###############################
										// # Vars tables

										Var__tXMLMap_1_TXMLMAP_OUT__Struct Var = Var__tXMLMap_1_TXMLMAP_OUT;
										// ###############################
										// # Output tables

										response = null;

// # Output table : 'response'

										if (!docAlreadyInstanciate_response) {
											docAlreadyInstanciate_response = true;
											gen_Doc_response_tXMLMap_1_TXMLMAP_OUT = new GenerateDocument_response();
//init one new out struct for cache the result.
											response_aggregate = new responseStruct();
											response_aggregate.payload = new routines.system.Document();
											response_aggregate.payload
													.setDocument(gen_Doc_response_tXMLMap_1_TXMLMAP_OUT.getDocument());

//construct the resultset
											allOutsForAggregate_tXMLMap_1.add(response_aggregate);
										}

										gen_Doc_response_tXMLMap_1_TXMLMAP_OUT.generateElements(
												treeNodeAPI_tXMLMap_1_TXMLMAP_OUT,
												rejectedDocInnerJoin_tXMLMap_1_TXMLMAP_OUT, row1, row2, Var);

// ###############################

									} // end of Var scope

									rejectedInnerJoin_tXMLMap_1_TXMLMAP_OUT = false;

								} // G_TM_M_002
							} // G_TXM_M_001 close

							tos_count_tXMLMap_1_TXMLMAP_OUT++;

							/**
							 * [tXMLMap_1_TXMLMAP_OUT main ] stop
							 */

							/**
							 * [tXMLMap_1_TXMLMAP_OUT process_data_begin ] start
							 */

							currentVirtualComponent = "tXMLMap_1";

							currentComponent = "tXMLMap_1_TXMLMAP_OUT";

							/**
							 * [tXMLMap_1_TXMLMAP_OUT process_data_begin ] stop
							 */

							/**
							 * [tXMLMap_1_TXMLMAP_OUT process_data_end ] start
							 */

							currentVirtualComponent = "tXMLMap_1";

							currentComponent = "tXMLMap_1_TXMLMAP_OUT";

							/**
							 * [tXMLMap_1_TXMLMAP_OUT process_data_end ] stop
							 */

							/**
							 * [tESBProviderRequest_1_In process_data_end ] start
							 */

							currentVirtualComponent = "tESBProviderRequest_1";

							currentComponent = "tESBProviderRequest_1_In";

							/**
							 * [tESBProviderRequest_1_In process_data_end ] stop
							 */

							/**
							 * [tESBProviderRequest_1_In end ] start
							 */

							currentVirtualComponent = "tESBProviderRequest_1";

							currentComponent = "tESBProviderRequest_1_In";

							resourceMap.put("inIterateVComp", true);

							ok_Hash.put("tESBProviderRequest_1_In", true);
							end_Hash.put("tESBProviderRequest_1_In", System.currentTimeMillis());

							/**
							 * [tESBProviderRequest_1_In end ] stop
							 */

							/**
							 * [tXMLMap_1_TXMLMAP_OUT end ] start
							 */

							currentVirtualComponent = "tXMLMap_1";

							currentComponent = "tXMLMap_1_TXMLMAP_OUT";

							if (execStat) {
								runStat.updateStat(resourceMap, iterateId, 2, 0, "row1");
							}

							ok_Hash.put("tXMLMap_1_TXMLMAP_OUT", true);
							end_Hash.put("tXMLMap_1_TXMLMAP_OUT", System.currentTimeMillis());

							/**
							 * [tXMLMap_1_TXMLMAP_OUT end ] stop
							 */

							/**
							 * [tESBProviderResponse_1 begin ] start
							 */

							ok_Hash.put("tESBProviderResponse_1", false);
							start_Hash.put("tESBProviderResponse_1", System.currentTimeMillis());

							currentComponent = "tESBProviderResponse_1";

							if (execStat) {
								runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "response");
							}

							int tos_count_tESBProviderResponse_1 = 0;

							/**
							 * [tESBProviderResponse_1 begin ] stop
							 */

							/**
							 * [tXMLMap_1_TXMLMAP_IN begin ] start
							 */

							ok_Hash.put("tXMLMap_1_TXMLMAP_IN", false);
							start_Hash.put("tXMLMap_1_TXMLMAP_IN", System.currentTimeMillis());

							currentVirtualComponent = "tXMLMap_1";

							currentComponent = "tXMLMap_1_TXMLMAP_IN";

							int tos_count_tXMLMap_1_TXMLMAP_IN = 0;

							java.util.List<Object> outs_tXMLMap_1 = (java.util.List<Object>) globalMap
									.get("allOutsForAggregate_tXMLMap_1");
							for (Object row_out_tXMLMap_1_TXMLMAP_IN : outs_tXMLMap_1) {// TD512

								/**
								 * [tXMLMap_1_TXMLMAP_IN begin ] stop
								 */

								/**
								 * [tXMLMap_1_TXMLMAP_IN main ] start
								 */

								currentVirtualComponent = "tXMLMap_1";

								currentComponent = "tXMLMap_1_TXMLMAP_IN";

								response = null;
								if (row_out_tXMLMap_1_TXMLMAP_IN != null
										&& row_out_tXMLMap_1_TXMLMAP_IN instanceof responseStruct) {
									response = (responseStruct) row_out_tXMLMap_1_TXMLMAP_IN;
									routines.system.NestXMLTool.generateOk(response.payload, false);
								}

								tos_count_tXMLMap_1_TXMLMAP_IN++;

								/**
								 * [tXMLMap_1_TXMLMAP_IN main ] stop
								 */

								/**
								 * [tXMLMap_1_TXMLMAP_IN process_data_begin ] start
								 */

								currentVirtualComponent = "tXMLMap_1";

								currentComponent = "tXMLMap_1_TXMLMAP_IN";

								/**
								 * [tXMLMap_1_TXMLMAP_IN process_data_begin ] stop
								 */
// Start of branch "response"
								if (response != null) {

									/**
									 * [tESBProviderResponse_1 main ] start
									 */

									currentComponent = "tESBProviderResponse_1";

									if (execStat) {
										runStat.updateStatOnConnection(iterateId, 1, 1

												, "response"

										);
									}

									routines.system.Document esbProviderResponseDoc_tESBProviderResponse_1 = response.payload;

									ESBProviderCallbackTalendJobInner esbProviderCallback_tESBProviderResponse_1 = (ESBProviderCallbackTalendJobInner) globalMap
											.get("esbHandler");
									if (null != esbProviderCallback_tESBProviderResponse_1) {
										java.util.Map<String, String> customProps_tESBProviderResponse_1 = null;

										esbProviderCallback_tESBProviderResponse_1
												.setCustomProperties(customProps_tESBProviderResponse_1);
										esbProviderCallback_tESBProviderResponse_1.sendResponse(
												esbProviderResponseDoc_tESBProviderResponse_1.getDocument());
									}

									tos_count_tESBProviderResponse_1++;

									/**
									 * [tESBProviderResponse_1 main ] stop
									 */

									/**
									 * [tESBProviderResponse_1 process_data_begin ] start
									 */

									currentComponent = "tESBProviderResponse_1";

									/**
									 * [tESBProviderResponse_1 process_data_begin ] stop
									 */

									/**
									 * [tESBProviderResponse_1 process_data_end ] start
									 */

									currentComponent = "tESBProviderResponse_1";

									/**
									 * [tESBProviderResponse_1 process_data_end ] stop
									 */

								} // End of branch "response"

								/**
								 * [tXMLMap_1_TXMLMAP_IN process_data_end ] start
								 */

								currentVirtualComponent = "tXMLMap_1";

								currentComponent = "tXMLMap_1_TXMLMAP_IN";

								/**
								 * [tXMLMap_1_TXMLMAP_IN process_data_end ] stop
								 */

								/**
								 * [tXMLMap_1_TXMLMAP_IN end ] start
								 */

								currentVirtualComponent = "tXMLMap_1";

								currentComponent = "tXMLMap_1_TXMLMAP_IN";

							} // TD512

							ok_Hash.put("tXMLMap_1_TXMLMAP_IN", true);
							end_Hash.put("tXMLMap_1_TXMLMAP_IN", System.currentTimeMillis());

							/**
							 * [tXMLMap_1_TXMLMAP_IN end ] stop
							 */

							/**
							 * [tESBProviderResponse_1 end ] start
							 */

							currentComponent = "tESBProviderResponse_1";

							if (execStat) {
								runStat.updateStat(resourceMap, iterateId, 2, 0, "response");
							}

							ok_Hash.put("tESBProviderResponse_1", true);
							end_Hash.put("tESBProviderResponse_1", System.currentTimeMillis());

							/**
							 * [tESBProviderResponse_1 end ] stop
							 */

							if (execStat) {
								runStat.updateStatOnConnection("Iterate", 2,
										"exec" + NB_ITERATE_tESBProviderRequest_1_In);
							}

							/**
							 * [tESBProviderRequest_1_Loop process_data_end ] start
							 */

							currentVirtualComponent = "tESBProviderRequest_1";

							currentComponent = "tESBProviderRequest_1_Loop";

							/**
							 * [tESBProviderRequest_1_Loop process_data_end ] stop
							 */

							/**
							 * [tESBProviderRequest_1_Loop end ] start
							 */

							currentVirtualComponent = "tESBProviderRequest_1";

							currentComponent = "tESBProviderRequest_1_Loop";

							resourceMap.remove("inIterateVComp");

						} catch (ESBJobInterruptedException e_tESBProviderRequest_1) {
							// job interrupted from outside
							((java.util.Map) threadLocal.get()).put("JobInterrupted", "true");
							break;
						} catch (Throwable e_tESBProviderRequest_1) {
							// handle exception by 'On Component Error' trigger or/and tLogCatcher
							if (e_tESBProviderRequest_1 instanceof Exception) {
								new TalendException((Exception) e_tESBProviderRequest_1, currentComponent, globalMap)
										.printStackTrace();
							} else {
								new TalendException(new RuntimeException(e_tESBProviderRequest_1), currentComponent,
										globalMap).printStackTrace();
							}
							((ESBProviderCallbackTalendJobInner) globalMap.get("esbHandler"))
									.sendFaultByDefault(e_tESBProviderRequest_1);
						} finally {
							// close DB connections
							try {
								java.util.Map<String, routines.system.TalendDataSource> talendDataSources = (java.util.Map<String, routines.system.TalendDataSource>) globalMap
										.get(KEY_DB_DATASOURCES);
								if (null != talendDataSources) {
									for (routines.system.TalendDataSource talendDataSource : talendDataSources
											.values()) {
										talendDataSource.close();
									}
								}
							} catch (Throwable e_tESBProviderRequest_1) {
								e_tESBProviderRequest_1.printStackTrace(System.err);
							}

							// Exit from this loop is made by the configuring "Keep listening"
							// parameter to false. Then we will have a break before.
							if ("false".equals("true")) {
								break;
							}
						}
						nb_line_tESBProviderRequest_1++;
						globalMap.put("tESBProviderRequest_1_NB_LINE", nb_line_tESBProviderRequest_1);
					} // This is the end of the ESB Service Provider loop
				} finally {
					// unsubscribe
					if (null != handlerThread_tESBProviderRequest_1) {
						// stop endpoint in case it was opened by job
						handlerThread_tESBProviderRequest_1.stopEndpoint();
					}
				}

				if (execStat) {
					runStat.updateStatOnConnection(iterateId, 2, 0, "row1", "response");
				}

				ok_Hash.put("tESBProviderRequest_1_Loop", true);
				end_Hash.put("tESBProviderRequest_1_Loop", System.currentTimeMillis());

				/**
				 * [tESBProviderRequest_1_Loop end ] stop
				 */
			} // end the resume

		} catch (java.lang.Exception e) {

			TalendException te = new TalendException(e, currentComponent, globalMap);

			te.setVirtualComponentName(currentVirtualComponent);

			throw te;
		} catch (java.lang.Error error) {

			runStat.stopThreadStat();

			throw error;
		} finally {

			try {

				/**
				 * [tESBProviderRequest_1_Loop finally ] start
				 */

				currentVirtualComponent = "tESBProviderRequest_1";

				currentComponent = "tESBProviderRequest_1_Loop";

				/**
				 * [tESBProviderRequest_1_Loop finally ] stop
				 */

				/**
				 * [tESBProviderRequest_1_In finally ] start
				 */

				currentVirtualComponent = "tESBProviderRequest_1";

				currentComponent = "tESBProviderRequest_1_In";

				/**
				 * [tESBProviderRequest_1_In finally ] stop
				 */

				/**
				 * [tXMLMap_1_TXMLMAP_OUT finally ] start
				 */

				currentVirtualComponent = "tXMLMap_1";

				currentComponent = "tXMLMap_1_TXMLMAP_OUT";

				/**
				 * [tXMLMap_1_TXMLMAP_OUT finally ] stop
				 */

				/**
				 * [tXMLMap_1_TXMLMAP_IN finally ] start
				 */

				currentVirtualComponent = "tXMLMap_1";

				currentComponent = "tXMLMap_1_TXMLMAP_IN";

				/**
				 * [tXMLMap_1_TXMLMAP_IN finally ] stop
				 */

				/**
				 * [tESBProviderResponse_1 finally ] start
				 */

				currentComponent = "tESBProviderResponse_1";

				/**
				 * [tESBProviderResponse_1 finally ] stop
				 */

			} catch (java.lang.Exception e) {
				// ignore
			} catch (java.lang.Error error) {
				// ignore
			}
			resourceMap = null;
		}

		globalMap.put("tESBProviderRequest_1_Loop_SUBPROCESS_STATE", 1);
	}

	public String resuming_logs_dir_path = null;
	public String resuming_checkpoint_path = null;
	public String parent_part_launcher = null;
	private String resumeEntryMethodName = null;
	private boolean globalResumeTicket = false;

	public boolean watch = false;
	// portStats is null, it means don't execute the statistics
	public Integer portStats = null;
	public int portTraces = 4334;
	public String clientHost;
	public String defaultClientHost = "localhost";
	public String contextStr = "Default";
	public boolean isDefaultContext = true;
	public String pid = "0";
	public String rootPid = null;
	public String fatherPid = null;
	public String fatherNode = null;
	public long startTime = 0;
	public boolean isChildJob = false;
	public String log4jLevel = "";

	private boolean enableLogStash;

	private boolean execStat = true;

	private ThreadLocal<java.util.Map<String, String>> threadLocal = new ThreadLocal<java.util.Map<String, String>>() {
		protected java.util.Map<String, String> initialValue() {
			java.util.Map<String, String> threadRunResultMap = new java.util.HashMap<String, String>();
			threadRunResultMap.put("errorCode", null);
			threadRunResultMap.put("status", "");
			return threadRunResultMap;
		};
	};

	protected PropertiesWithType context_param = new PropertiesWithType();
	public java.util.Map<String, Object> parentContextMap = new java.util.HashMap<String, Object>();

	public String status = "";

	public static void main(String[] args) {
		final getUserbySOAP getUserbySOAPClass = new getUserbySOAP();

		int exitCode = getUserbySOAPClass.runJobInTOS(args);

		System.exit(exitCode);
	}

	public String[][] runJob(String[] args) {

		int exitCode = runJobInTOS(args);
		String[][] bufferValue = new String[][] { { Integer.toString(exitCode) } };

		return bufferValue;
	}

	public boolean hastBufferOutputComponent() {
		boolean hastBufferOutput = false;

		return hastBufferOutput;
	}

	public int runJobInTOS(String[] args) {
		// reset status
		status = "";

		String lastStr = "";
		for (String arg : args) {
			if (arg.equalsIgnoreCase("--context_param")) {
				lastStr = arg;
			} else if (lastStr.equals("")) {
				evalParam(arg);
			} else {
				evalParam(lastStr + " " + arg);
				lastStr = "";
			}
		}
		enableLogStash = "true".equalsIgnoreCase(System.getProperty("audit.enabled"));

		if (clientHost == null) {
			clientHost = defaultClientHost;
		}

		if (pid == null || "0".equals(pid)) {
			pid = TalendString.getAsciiRandomString(6);
		}

		if (rootPid == null) {
			rootPid = pid;
		}
		if (fatherPid == null) {
			fatherPid = pid;
		} else {
			isChildJob = true;
		}

		if (portStats != null) {
			// portStats = -1; //for testing
			if (portStats < 0 || portStats > 65535) {
				// issue:10869, the portStats is invalid, so this client socket can't open
				System.err.println("The statistics socket port " + portStats + " is invalid.");
				execStat = false;
			}
		} else {
			execStat = false;
		}
		boolean inOSGi = routines.system.BundleUtils.inOSGi();

		if (inOSGi) {
			java.util.Dictionary<String, Object> jobProperties = routines.system.BundleUtils.getJobProperties(jobName);

			if (jobProperties != null && jobProperties.get("context") != null) {
				contextStr = (String) jobProperties.get("context");
			}
		}

		try {
			// call job/subjob with an existing context, like: --context=production. if
			// without this parameter, there will use the default context instead.
			java.io.InputStream inContext = getUserbySOAP.class.getClassLoader()
					.getResourceAsStream("tp/getuserbysoap_0_1/contexts/" + contextStr + ".properties");
			if (inContext == null) {
				inContext = getUserbySOAP.class.getClassLoader()
						.getResourceAsStream("config/contexts/" + contextStr + ".properties");
			}
			if (inContext != null) {
				try {
					// defaultProps is in order to keep the original context value
					if (context != null && context.isEmpty()) {
						defaultProps.load(inContext);
						context = new ContextProperties(defaultProps);
					}
				} finally {
					inContext.close();
				}
			} else if (!isDefaultContext) {
				// print info and job continue to run, for case: context_param is not empty.
				System.err.println("Could not find the context " + contextStr);
			}

			if (!context_param.isEmpty()) {
				context.putAll(context_param);
				// set types for params from parentJobs
				for (Object key : context_param.keySet()) {
					String context_key = key.toString();
					String context_type = context_param.getContextType(context_key);
					context.setContextType(context_key, context_type);

				}
			}
			class ContextProcessing {
				private void processContext_0() {
				}

				public void processAllContext() {
					processContext_0();
				}
			}

			new ContextProcessing().processAllContext();
		} catch (java.io.IOException ie) {
			System.err.println("Could not load context " + contextStr);
			ie.printStackTrace();
		}

		// get context value from parent directly
		if (parentContextMap != null && !parentContextMap.isEmpty()) {
		}

		// Resume: init the resumeUtil
		resumeEntryMethodName = ResumeUtil.getResumeEntryMethodName(resuming_checkpoint_path);
		resumeUtil = new ResumeUtil(resuming_logs_dir_path, isChildJob, rootPid);
		resumeUtil.initCommonInfo(pid, rootPid, fatherPid, projectName, jobName, contextStr, jobVersion);

		List<String> parametersToEncrypt = new java.util.ArrayList<String>();
		// Resume: jobStart
		resumeUtil.addLog("JOB_STARTED", "JOB:" + jobName, parent_part_launcher, Thread.currentThread().getId() + "",
				"", "", "", "", resumeUtil.convertToJsonText(context, parametersToEncrypt));

		if (execStat) {
			try {
				runStat.openSocket(!isChildJob);
				runStat.setAllPID(rootPid, fatherPid, pid, jobName);
				runStat.startThreadStat(clientHost, portStats);
				runStat.updateStatOnJob(RunStat.JOBSTART, fatherNode);
			} catch (java.io.IOException ioException) {
				ioException.printStackTrace();
			}
		}

		java.util.concurrent.ConcurrentHashMap<Object, Object> concurrentHashMap = new java.util.concurrent.ConcurrentHashMap<Object, Object>();
		globalMap.put("concurrentHashMap", concurrentHashMap);

		long startUsedMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
		long endUsedMemory = 0;
		long end = 0;

		startTime = System.currentTimeMillis();

		this.globalResumeTicket = true;// to run tPreJob

		this.globalResumeTicket = false;// to run others jobs

		try {
			errorCode = null;
			tESBProviderRequest_1_LoopProcess(globalMap);
			if (!"failure".equals(status)) {
				status = "end";
			}
		} catch (TalendException e_tESBProviderRequest_1_Loop) {
			globalMap.put("tESBProviderRequest_1_Loop_SUBPROCESS_STATE", -1);

			e_tESBProviderRequest_1_Loop.printStackTrace();

		}

		this.globalResumeTicket = true;// to run tPostJob

		end = System.currentTimeMillis();

		if (watch) {
			System.out.println((end - startTime) + " milliseconds");
		}

		endUsedMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
		if (false) {
			System.out
					.println((endUsedMemory - startUsedMemory) + " bytes memory increase when running : getUserbySOAP");
		}

		if (execStat) {
			runStat.updateStatOnJob(RunStat.JOBEND, fatherNode);
			runStat.stopThreadStat();
		}
		int returnCode = 0;

		if (errorCode == null) {
			returnCode = status != null && status.equals("failure") ? 1 : 0;
		} else {
			returnCode = errorCode.intValue();
		}
		resumeUtil.addLog("JOB_ENDED", "JOB:" + jobName, parent_part_launcher, Thread.currentThread().getId() + "", "",
				"" + returnCode, "", "", "");

		return returnCode;

	}

	// only for OSGi env
	public void destroy() {

	}

	private java.util.Map<String, Object> getSharedConnections4REST() {
		java.util.Map<String, Object> connections = new java.util.HashMap<String, Object>();

		return connections;
	}

	private void evalParam(String arg) {
		if (arg.startsWith("--resuming_logs_dir_path")) {
			resuming_logs_dir_path = arg.substring(25);
		} else if (arg.startsWith("--resuming_checkpoint_path")) {
			resuming_checkpoint_path = arg.substring(27);
		} else if (arg.startsWith("--parent_part_launcher")) {
			parent_part_launcher = arg.substring(23);
		} else if (arg.startsWith("--watch")) {
			watch = true;
		} else if (arg.startsWith("--stat_port=")) {
			String portStatsStr = arg.substring(12);
			if (portStatsStr != null && !portStatsStr.equals("null")) {
				portStats = Integer.parseInt(portStatsStr);
			}
		} else if (arg.startsWith("--trace_port=")) {
			portTraces = Integer.parseInt(arg.substring(13));
		} else if (arg.startsWith("--client_host=")) {
			clientHost = arg.substring(14);
		} else if (arg.startsWith("--context=")) {
			contextStr = arg.substring(10);
			isDefaultContext = false;
		} else if (arg.startsWith("--father_pid=")) {
			fatherPid = arg.substring(13);
		} else if (arg.startsWith("--root_pid=")) {
			rootPid = arg.substring(11);
		} else if (arg.startsWith("--father_node=")) {
			fatherNode = arg.substring(14);
		} else if (arg.startsWith("--pid=")) {
			pid = arg.substring(6);
		} else if (arg.startsWith("--context_type")) {
			String keyValue = arg.substring(15);
			int index = -1;
			if (keyValue != null && (index = keyValue.indexOf('=')) > -1) {
				if (fatherPid == null) {
					context_param.setContextType(keyValue.substring(0, index),
							replaceEscapeChars(keyValue.substring(index + 1)));
				} else { // the subjob won't escape the especial chars
					context_param.setContextType(keyValue.substring(0, index), keyValue.substring(index + 1));
				}

			}

		} else if (arg.startsWith("--context_param")) {
			String keyValue = arg.substring(16);
			int index = -1;
			if (keyValue != null && (index = keyValue.indexOf('=')) > -1) {
				if (fatherPid == null) {
					context_param.put(keyValue.substring(0, index), replaceEscapeChars(keyValue.substring(index + 1)));
				} else { // the subjob won't escape the especial chars
					context_param.put(keyValue.substring(0, index), keyValue.substring(index + 1));
				}
			}
		} else if (arg.startsWith("--log4jLevel=")) {
			log4jLevel = arg.substring(13);
		} else if (arg.startsWith("--audit.enabled") && arg.contains("=")) {// for trunjob call
			final int equal = arg.indexOf('=');
			final String key = arg.substring("--".length(), equal);
			System.setProperty(key, arg.substring(equal + 1));
		}
	}

	private static final String NULL_VALUE_EXPRESSION_IN_COMMAND_STRING_FOR_CHILD_JOB_ONLY = "<TALEND_NULL>";

	private final String[][] escapeChars = { { "\\\\", "\\" }, { "\\n", "\n" }, { "\\'", "\'" }, { "\\r", "\r" },
			{ "\\f", "\f" }, { "\\b", "\b" }, { "\\t", "\t" } };

	private String replaceEscapeChars(String keyValue) {

		if (keyValue == null || ("").equals(keyValue.trim())) {
			return keyValue;
		}

		StringBuilder result = new StringBuilder();
		int currIndex = 0;
		while (currIndex < keyValue.length()) {
			int index = -1;
			// judege if the left string includes escape chars
			for (String[] strArray : escapeChars) {
				index = keyValue.indexOf(strArray[0], currIndex);
				if (index >= 0) {

					result.append(keyValue.substring(currIndex, index + strArray[0].length()).replace(strArray[0],
							strArray[1]));
					currIndex = index + strArray[0].length();
					break;
				}
			}
			// if the left string doesn't include escape chars, append the left into the
			// result
			if (index < 0) {
				result.append(keyValue.substring(currIndex));
				currIndex = currIndex + keyValue.length();
			}
		}

		return result.toString();
	}

	public Integer getErrorCode() {
		return errorCode;
	}

	public String getStatus() {
		return status;
	}

	ResumeUtil resumeUtil = null;
}
/************************************************************************************************
 * 104662 characters generated by Talend Open Studio for ESB on the 27 janvier
 * 2024 à 20:24:01 CET
 ************************************************************************************************/