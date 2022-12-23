package com.example.delivery2;

import javafx.fxml.FXML;
import javafx.scene.control.Label;


public class ItemCategoryController {
    @FXML
    private Label nameLabel;

    private Category category;

    public void setData(Category category) {
        this.category = category;
        nameLabel.setText(category.getName());

    }

}
