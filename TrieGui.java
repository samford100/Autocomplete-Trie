

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class TrieGui extends Application {
	Trie2 trie = new Trie2();
	TextField input = new TextField();
	TextArea output = new TextArea();
	TextArea definition = new TextArea();
	
	public TrieGui() {
		output.setEditable(false);
		definition.setEditable(false);
	}
	
	@Override
	public void start(Stage stage) {
		stage.setTitle("Autocomplete");
		trie.importWords("words.txt");
		VBox grid = new VBox();
		grid.setPadding(new Insets(25, 25, 25, 25));
		
		input.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent k) {
				if (k.getCode() == KeyCode.BACK_SPACE) {
					if (!input.getText().equals("")) {
						trie.backer(input.getText().length());
						if (input.getText().length() -1 <= trie.hString.length()) {
							trie.possWords.clear();
							trie.autoCompRec(trie.holder, trie.hString.toString());
						}
					}
				} else {
					String s = k.getText();
					Character c = s.charAt(0);
					trie.continualComp(c, input.getText().length());
				}
				String a = trie.possWords.toString();
				int d = a.length();
				output.setWrapText(true);
				output.setText(a.substring(1, d - 1));
				if (!trie.possWords.isEmpty()) {
					definition.setText(trie.possNodes.get(0).def);
				}
			}
		});
		
		

		
		grid.getChildren().addAll(input, output, definition);
		Scene scene = new Scene(grid, 300, 275);
		stage.setScene(scene);
		stage.show();
	}
}
