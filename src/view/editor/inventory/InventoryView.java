package view.editor.inventory;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import gameobject.Template;
import javafx.scene.control.TabPane;
import javafx.scene.control.TabPane.TabClosingPolicy;
import model.ChampionPool;
import model.Inventory;
import util.Observer;
import view.ViewBase;

/**
 * InventoryView stores all the available items in the game and is the main
 * controller for the inventory in UI
 * 
 * @author Feng
 * @author Mike Liu
 * @author Wei-Ting Yeh
 *
 */
public class InventoryView extends ViewBase<TabPane> implements Observer<Inventory> {

	public static final String HERO = "Hero";
	private ChampionPool myChampions;

	public InventoryView(double width, double height, Inventory inventory, ChampionPool championpool) {
		super(new TabPane());
		myChampions = championpool;
		getView().setId("inventory");
		getView().setPrefSize(width, height);
		getView().setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);
		inventory.addObserver(this);
	}

	private void setContents(Inventory inventory) {
		getView().getTabs().clear();
		for (String type : sorted(inventory.getTypes())) {
			createChampionPool(type, inventory);
			InventoryTab tabView = new InventoryTab(getView().getPrefWidth(), getView().getPrefHeight(), inventory,
					type);
			getView().getTabs().add(tabView.getView());
		}
	}

	private void createChampionPool(String type, Inventory inventory) {
		if (type.equals(HERO)) {
			//myChampions.clearAll();
			for (String name : inventory.getNamesOfType(type)) {
				Template template = inventory.getTemplate(name);
				myChampions.addChampion(template);
			}
		}

	}

	private Collection<String> sorted(Iterable<String> types) {
		ArrayList<String> sortedTypes = new ArrayList<String>();
		for (String type : types) {
			sortedTypes.add(type);
		}
		Collections.sort(sortedTypes);
		return sortedTypes;
	}

	@Override
	public void update(Inventory arg) {
		setContents(arg);
	}

}
