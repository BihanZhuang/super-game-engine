package util.view;

import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;

/**
 * Generates layout constraints for <code>GridPane</code>
 * @author Mike Liu
 *
 */
public class ConstraintsFactory {

    public static ColumnConstraints getColumnConstraints(double percent) {
        ColumnConstraints ret = new ColumnConstraints();
        ret.setHgrow(Priority.NEVER);
        ret.setPercentWidth(percent);
        return ret;
    }

    public static RowConstraints getRowConstraints(double percent) {
        RowConstraints ret = new RowConstraints();
        ret.setVgrow(Priority.NEVER);
        ret.setPercentHeight(percent);
        return ret;
    }
}
