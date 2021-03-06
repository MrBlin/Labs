package EditingCells;

import Enums.Country;
import Enums.MovieGenre;
import com.company.Movie;
import com.company.Starter;
import javafx.collections.FXCollections;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.TableCell;

public class CountryCBoxEditingCell extends TableCell<Movie, Country> {
    private ComboBox<Country> comboBox;

    public CountryCBoxEditingCell() {
    }

    @Override
    public void startEdit() {
        if (!isEmpty() && ((Movie) getTableRow().getItem()).getOwnerLogin().equals(Starter.curClient.getLogin())) {
            super.startEdit();
            createComboBox();
            setText(null);
            setGraphic(comboBox);
        }
    }

    @Override
    public void cancelEdit() {
        super.cancelEdit();
        setText(getString());
        setGraphic(null);
    }

    @Override
    public void updateItem(Country item, boolean empty) {
        super.updateItem(item, empty);

        if (empty) {
            setText(null);
            setGraphic(null);
        } else {
            if (isEditing()) {
                if (comboBox != null) {
                    comboBox.setValue(item);
                }
                setText(getString());
                setGraphic(comboBox);
            } else {
                setText(getString());
                setGraphic(null);
            }
        }
    }

    private void createComboBox() {
        comboBox = new ComboBox<>(FXCollections.observableArrayList(Country.values()));
        comboBoxConverter(comboBox);
        comboBox.valueProperty().set(getItem());
        comboBox.setMinWidth(this.getWidth() - this.getGraphicTextGap() * 2);
        comboBox.setOnAction((e) -> {
            System.out.println("Committed: " + comboBox.getSelectionModel().getSelectedItem());
            commitEdit(comboBox.getSelectionModel().getSelectedItem());
        });
    }

    private void comboBoxConverter(ComboBox<Country> comboBox) {
        // Define rendering of the list of values in ComboBox drop down.
        comboBox.setCellFactory((c) -> new ListCell<Country>() {
            @Override
            protected void updateItem(Country item, boolean empty) {
                super.updateItem(item, empty);

                if (item == null || empty) {
                    setText(null);
                } else {
                    setText(item.getDescription());
                }
            }
        });
    }

    private String getString() {
        return getItem() == null ? "" : getItem().toString();
    }
}