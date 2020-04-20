package common;

import java.io.Serializable;

/**
 * Basic Message Class to allow for messages to be sent across sockets.
 * 
 * Type should be a final String derived from MessageTypes. Content contains
 * any data, if necessary, that the message type might need.
 * 
 * @author C. Faith, L. Garland, G. Raymond-Fauteux
 * @since April 19 2020
 * @version 1.1
 *
 */
public class Message implements MessageTypes, Serializable
{
	/**
	 * Default serial ID.
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Message type of the instance.
	 */
	private final String type;
	/**
	 * Contains any data, if necessary, that the message type might need.
	 */
	private final String content;
	/**
	 * Constructs a Message with the given values for message type and content.
	 * @param type message type
	 * @param content extra data to be sent
	 */
	public Message(String type, String content)
	{
		this.type = type;
		this.content = content;
	}
	/**
	 * Gets the type of the message.
	 * @return the message type
	 */
	public String getType() {
		return type;
	}
	/**
	 * Gets the content of the message.
	 * @return the content
	 */
	public String getContent() {
		return content;
	}
	
}
