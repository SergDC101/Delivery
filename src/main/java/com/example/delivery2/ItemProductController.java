package com.example.delivery2;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;
import java.net.MalformedURLException;

public class ItemProductController {

    @FXML
    private ImageView img;

    @FXML
    private Label nameLabel;

    @FXML
    private Label priceLabel;

    @FXML
    private Label weightLabel;

    private Product product;


    public void setData(Product product) throws MalformedURLException {
        this.product = product;
        nameLabel.setWrapText(true);
        nameLabel.setText(product.getName());
        priceLabel.setText(String.valueOf(product.getPrice()+ " руб."));
        weightLabel.setText(String.valueOf(product.getWeight() + " г."));

        File file = new File("src/main/resources/img/"+product.getImage());
        String urlImage = file.toURI().toURL().toString();
        Image image = new Image(urlImage);

        img.setImage(image);

    }

}
