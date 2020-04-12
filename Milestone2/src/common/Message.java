package common;

import java.io.Serializable;

/**
 * Basic Message Class to allow for messages to be sent across sockets.
 * type should be a final String derived from MessageTypes. 
 * @author C. Faith, L. Garland, G. Raymond-Fauteux
 * 
 *
 */
public class Message implements MessageTypes, Serializable
{


	private static final long serialVersionUID = 1L;
	private final String type;
	private final String content;
	
	public Message(String type, String content)
	{
		this.type = type;
		this.content = content;
	}

	public String getType() {
		return type;
	}

	public String getContent() {
		return content;
	}
	
}
