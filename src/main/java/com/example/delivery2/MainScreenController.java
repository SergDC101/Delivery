package com.example.delivery2;

import com.mysql.cj.x.protobuf.MysqlxExpect;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.MalformedURLException;
import java.security.Key;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MainScreenController {
    DataBaseHandler dbHandler = new DataBaseHandler();

    @FXML
    private TextField fioField1;

    @FXML
    private Button btnClear;
    @FXML
    private GridPane gridCat;

    @FXML
    private GridPane gridPro;

    @FXML
    private Label nameLabel1;

    @FXML
    private ScrollPane scrollCat;

    @FXML
    private ScrollPane scrollPro;

    @FXML
    private Button btmPricrePlus;

    @FXML
    private Button btnAZ;

    @FXML
    private Button btnPriceMinus;

    @FXML
    private Button btnWeightMinus;

    @FXML
    private Button btnWeightPlus;

    @FXML
    private Button btnZA;
    @FXML
    private TextField searchField;

    private List<Category> categories = new ArrayList<>();
    private List<Product> products = new ArrayList<>();

    int rowPro = 1;

    @FXML
    void initialize() throws SQLException {
        nameLabel1.setText(LoginController.NameFooter);
        String select = "SELECT name,price, weight, image FROM " + Const.DB_NAME + "." + Const.PRODUCT_TABLE ;
        //Формирование продуктов
        createProduct(select);



        // Формирование категорий
        categories.addAll(getDataCategories());
        int column = 0;
        int row = 1;
        try {
            for (int i = 0; i < categories.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("itemCategory.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                ItemCategoryController itemCategoryController = fxmlLoader.getController();
                itemCategoryController.setData(categories.get(i));

                gridCat.add(anchorPane, column, row++); //(child, column, row)

                //set item grid width
                gridCat.setMinWidth(Region.USE_COMPUTED_SIZE);
                gridCat.setPrefWidth(Region.USE_COMPUTED_SIZE);
                gridCat.setMaxWidth(Region.USE_PREF_SIZE);

                //set item grid height
                gridCat.setMinHeight(Region.USE_COMPUTED_SIZE);
                gridCat.setPrefHeight(Region.USE_COMPUTED_SIZE);
                gridCat.setMaxHeight(Region.USE_PREF_SIZE);

                GridPane.setMargin(anchorPane, new Insets(10));

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void createProduct(String select) throws SQLException {
        products.addAll(getDataProduct(select));
        int columnPro = 0;
        int in = products.size();
        try {
            for (int i = 0; i < products.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("itemProduct.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                ItemProductController itemProductController = fxmlLoader.getController();
                itemProductController.setData(products.get(i));

                if (columnPro == 3) {
                    columnPro = 0;
                    rowPro++;

                }
                gridPro.add(anchorPane, columnPro++, rowPro); //(child, column, row)
                //set item drid width
                gridPro.setMinWidth(Region.USE_COMPUTED_SIZE);
                gridPro.setPrefWidth(Region.USE_COMPUTED_SIZE);
                gridPro.setMaxWidth(Region.USE_PREF_SIZE);


                //set item drid height
                gridPro.setMinHeight(Region.USE_COMPUTED_SIZE);
                gridPro.setPrefHeight(Region.USE_COMPUTED_SIZE);
                gridPro.setMaxHeight(Region.USE_PREF_SIZE);
                GridPane.setMargin(anchorPane, new Insets(30));
            }
            columnPro = 0;
            rowPro = 1;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Product> getDataProduct(String select) throws SQLException {
        List<Product> productos = new ArrayList<>();
        Product product;
        ResultSet result = dbHandler.getProduct(select);

        while (result.next()){
            product = new Product();
            product.setName(result.getString(1));
            product.setPrice(result.getInt(2));
            product.setWeight(result.getInt(3));
            product.setImage(result.getString(4));
            productos.add(product);
        }

        return productos;
    }

    public void pricePlus() throws SQLException, InterruptedException {
        clearProduct();
        products.clear();
        createProduct("SELECT name,price, weight, image FROM "+Const.DB_NAME + "." + Const.PRODUCT_TABLE+" order by price asc;");

    }

    public void priceMinus() throws InterruptedException, SQLException {
        clearProduct();
        products.clear();
        createProduct("SELECT name,price, weight, image  FROM "+Const.DB_NAME + "." + Const.PRODUCT_TABLE+" order by price desc;");
    }

    public void AZ() throws SQLException {
        clearProduct();
        products.clear();
        createProduct("SELECT name, price, weight, image FROM "+Const.DB_NAME + "." + Const.PRODUCT_TABLE+" order by name asc;");

    }

    public void ZA() throws SQLException {
        clearProduct();
        products.clear();
        createProduct("SELECT name, price, weight, image FROM "+Const.DB_NAME + "." + Const.PRODUCT_TABLE+" order by name desc;");
    }
    public void weightPlus() throws SQLException {
        clearProduct();
        products.clear();
        createProduct("SELECT name,price, weight, image  FROM "+Const.DB_NAME + "." + Const.PRODUCT_TABLE+" order by weight asc;");
    }

    public void weightMinus() throws InterruptedException, SQLException {
        clearProduct();
        products.clear();
        createProduct("SELECT name,price, weight, image  FROM "+Const.DB_NAME + "." + Const.PRODUCT_TABLE+" order by weight desc;");
    }

    public void search(KeyEvent even) throws SQLException, InterruptedException {
        if (even.getCode() == KeyCode.ENTER){
            clearProduct();
            products.clear();
            createProduct("SELECT name,price, weight, image  FROM "+Const.DB_NAME + "." + Const.PRODUCT_TABLE+" WHERE name LIKE '%"+searchField.getText()+"%';");
        }
    }


    public void clearProduct(){
            int i = 1;
            while (i <= rowPro){
                for (int j = 0; j < 3; j++) {
                    gridPro.getChildren().remove(0,j);
                    if (j == 2){
                        i++;
                    }
                }
            }
    }








    public List<Category> getDataCategories() throws SQLException {
        List<Category> categors = new ArrayList<>();
        Category category;
        ResultSet result = dbHandler.getCategory();

        while (result.next()) {
            category = new Category();
            category.setName(result.getString(1));
            categors.add(category);
        }

        return categors;
    }


}
