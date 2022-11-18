package viikingit.emusic.ui;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UIMessage {

	private String type;
	private String title;
	private String icon;
	private String content;

	private List<UILink> links;

	public UIMessage() {
		this("", "");
	}

	public UIMessage(String type, String title, String icon, String content) {
		this.type = type;
		this.title = title;
		this.icon = icon;
		this.content = content;
		links = new ArrayList<>();
	}

	public UIMessage(String title, String content) {
		this("", title, "circular info", content);
	}

	public UIMessage addLinks(UILink... links) {
		this.links.addAll(Arrays.asList(links));
		return this;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public static UIMessage errorConfirmation(String title, String content, String okURL, String cancelURL) {
		return error(title, content).addLinks(new UILink("Oui", okURL), new UILink("Non", cancelURL));
	}

	public static UIMessage error(String title, String content) {
		return new UIMessage("error", title, "remove", content);
	}
}
