package view.editor.componentwindow;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import javafx.scene.Node;

public class ComponentData {
	public HashMap<String, String> componentdata;
	public List<Node> displayContent;

	public List<Node> getDisplayContent() {
		return displayContent;
	}

	public ComponentData() {
		this.componentdata = new HashMap<String, String>();
		this.displayContent = new ArrayList<Node>();
	}
	

	public void put(Field key, String value) {
		this.componentdata.put(key.toString(), value);
		System.out.println("");
	}
	
	public void put(Type key, String value) {
		this.componentdata.put(key.toString(), value);
	}
	
	public void put(String key, String value) {
		this.componentdata.put(key, value);
	}

	public void remove(String key, String value) {
		this.componentdata.remove(key, value);
	}

	public void remove(Field key, String value) {
		this.componentdata.remove(key.toString(), value);
	}
	public String get(Field key) {
		return this.componentdata.get(key.toString());
	}

	public List<Entry<String, String>> getContent() {
		List<Entry<String, String>> cur = new ArrayList<Entry<String, String>>();
		for (Entry<String, String> e : this.componentdata.entrySet()) {
			cur.add(e);
		}
		return cur;
	}
}
