package org.sryl.shortener.client;

import org.sryl.shortener.shared.FieldVerifier;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Frame;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.HasVerticalAlignment;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Shortener implements EntryPoint {
	/**
	 * The message displayed to the user when the server cannot be reached or
	 * returns an error.
	 */
	private static final String SERVER_ERROR = "An error occurred while "
			+ "attempting to contact the server. Please check your network "
			+ "connection and try again.";

	/**
	 * Create a remote service proxy to talk to the server-side Shorten service.
	 */
	private final ShortenServiceAsync shortenService = GWT
			.create(ShortenService.class);

	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {

		// Add the nameField and sendButton to the RootPanel
		// Use RootPanel.get() to get the entire body element
		RootPanel rootPanel = RootPanel.get();
		rootPanel.setSize("100%", "100%");
		
		VerticalPanel verticalPanel = new VerticalPanel();
		rootPanel.add(verticalPanel);
		
		final Label errorLabel = new Label();
		verticalPanel.add(errorLabel);
		
		HorizontalPanel horizontalPanel = new HorizontalPanel();
		horizontalPanel.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
		verticalPanel.add(horizontalPanel);
		
		final TextBox urlField = new TextBox();
		urlField.setText("URL to be shortened");
		urlField.setFocus(true);
		horizontalPanel.add(urlField);
		urlField.setSize("360px", "");
		
		final Button btnShorten = new Button();
		horizontalPanel.add(btnShorten);
		btnShorten.setText("Shorten!");
		
		HorizontalPanel horizontalPanel_1 = new HorizontalPanel();
		horizontalPanel_1.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
		verticalPanel.add(horizontalPanel_1);
		
		final TextBox shortCodeField = new TextBox();
		shortCodeField.setMaxLength(6);
		shortCodeField.setText("Short Code");
		horizontalPanel_1.add(shortCodeField);
		
		final Button btnRetrieve = new Button();
		horizontalPanel_1.add(btnRetrieve);
		btnRetrieve.setText("Retrieve!");
		
		final Frame frame = new Frame();
		verticalPanel.add(frame);
		frame.setSize("100%", "100%");

		// Create a handler for the sendButton and nameField
		class ShortenHandler implements ClickHandler, KeyUpHandler {
			/**
			 * Fired when the user clicks on the sendButton.
			 */
			public void onClick(ClickEvent event) {
				shortenThroughServer();
			}

			/**
			 * Fired when the user types in the nameField.
			 */
			public void onKeyUp(KeyUpEvent event) {
				if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
					shortenThroughServer();
				}
			}

			/**
			 * Send the name from the nameField to the server and wait for a
			 * response.
			 */
			private void shortenThroughServer() {
				// First, we validate the input.
				errorLabel.setText("");
				String textToServer = urlField.getText();
				if (!FieldVerifier.isValidUrl(textToServer)) {
					errorLabel.setText("Please enter a valid URL");
					return;
				}

				// Then, we send the input to the server.
				btnShorten.setEnabled(false);

				shortenService.shorten(textToServer,
						new AsyncCallback<String>() {
							public void onFailure(Throwable caught) {
								errorLabel.setText(SERVER_ERROR);
							}

							public void onSuccess(String shortCode) {

								shortCodeField.setText(shortCode);
								btnShorten.setEnabled(true);
							}
						});
			}
		}

		// Add a handler to send the name to the server
		ShortenHandler shortenHandler = new ShortenHandler();
		btnShorten.addClickHandler(shortenHandler);
		btnShorten.addKeyUpHandler(shortenHandler);

		// Create a handler for the sendButton and nameField
		class RetrieveHandler implements ClickHandler, KeyUpHandler {
			/**
			 * Fired when the user clicks on the sendButton.
			 */
			public void onClick(ClickEvent event) {
				retrieveURLThroughServer();
			}

			/**
			 * Fired when the user types in the nameField.
			 */
			public void onKeyUp(KeyUpEvent event) {
				if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
					retrieveURLThroughServer();
				}
			}

			/**
			 * Send the name from the nameField to the server and wait for a
			 * response.
			 */
			private void retrieveURLThroughServer() {
				// First, we validate the input.
				errorLabel.setText("");
				if (!FieldVerifier.isValidShortCode(shortCodeField.getText())) {
					errorLabel.setText("Please enter a valid Short code");
					return;
				}

				// Then, we send the input to the server.
				btnRetrieve.setEnabled(false);

				shortenService.retrieve(shortCodeField.getText(),
						new AsyncCallback<String>() {
							public void onFailure(Throwable caught) {
								errorLabel.setText(SERVER_ERROR);
							}

							public void onSuccess(String url) {

								if (url != null) {
									frame.setUrl(url);
								} else {
									errorLabel.setText("WTF did that short code came from? Never heard about it!");
								}

								btnRetrieve.setEnabled(true);
							}
						});
			}
		}

		// Add a handler to retrieve a unshortened URL
		RetrieveHandler retrieveHandler = new RetrieveHandler();
		btnRetrieve.addClickHandler(retrieveHandler);
		btnRetrieve.addKeyUpHandler(retrieveHandler);
	}
}
