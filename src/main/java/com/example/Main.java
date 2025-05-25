package com.example;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.animation.FadeTransition;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;
import java.text.NumberFormat;
import java.util.Locale;

public class Main extends Application {

    private VBox productDetailBox;
    private ImageView mainImageView;
    private Label nameLabel;
    private Label priceLabel;
    private Label descriptionLabel;
    private Label brandLabel;
    private List<Product> products;
    private List<Pane> productCards = new ArrayList<>();
    private int selectedIndex = 0;

    @Override
    public void start(Stage primaryStage) {
        initializeProducts();

        BorderPane root = new BorderPane();
        root.setPadding(new Insets(24, 24, 24, 24));
        root.setStyle("-fx-background-color: #f7f8fa;");

        // Chi tiết sản phẩm bên trái
        productDetailBox = createProductDetailBox();
        root.setLeft(productDetailBox);
        BorderPane.setMargin(productDetailBox, new Insets(0, 32, 0, 0));

        // Danh sách sản phẩm dạng lưới bên phải
        VBox productListBox = createProductListBox();
        root.setCenter(productListBox);

        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("/styles.css").toExternalForm());

        showProduct(products.get(0), 0);

        primaryStage.setTitle("Product Showcase");
        primaryStage.setScene(scene);
        primaryStage.setMaximized(true);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    private void initializeProducts() {
        products = new ArrayList<>();
        products.add(new Product("4DFWD PULSE SHOES",
                "This product is excluded from all promotional discounts and otters.", "Adidas", 120,
                "/asset/img1.png"));

        products.add(new Product("FORUM MID SHOES",
                "This product is excluded from all promotional discounts and otters.", "Adidas", 150,
                "/asset/img2.png"));

        products.add(new Product("SUPERNOVA SHOES",
                "NMD City Stock 2", "Adidas", 200, "/asset/img3.png"));

        products.add(
                new Product("Adidas", "This product is excluded from all promotional discounts and otters.", "Adidas",
                        230, "/asset/img4.png"));
        products.add(new Product("4DFWD PULSE SHOES",
                "NMD City Stock 2", "Adidas", 150, "/asset/img5.png"));

        products.add(new Product("FORUM MID SHOES",
                "This product is excluded from all promotional discounts and otters.", "Adidas", 120,
                "/asset/img6.png"));
        products.add(new Product("4DFWD PULSE SHOES",
                "This product is excluded from all promotional discounts and otters.", "Adidas", 190,
                "/asset/img1.png"));

        products.add(new Product("FORUM MID SHOES",
                "This product is excluded from all promotional discounts and otters.", "Adidas", 200,
                "/asset/img2.png"));

        products.add(new Product("SUPERNOVA SHOES",
                "NMD City Stock 2", "Adidas", 200, "/asset/img3.png"));

        products.add(
                new Product("Adidas", "This product is excluded from all promotional discounts and otters.", "Adidas",
                        120, "/asset/img4.png"));
        products.add(new Product("4DFWD PULSE SHOES",
                "NMD City Stock 2", "Adidas", 160, "/asset/img5.png"));

        products.add(new Product("FORUM MID SHOES",
                "This product is excluded from all promotional discounts and otters.", "Adidas", 160,
                "/asset/img6.png"));
        products.add(new Product("4DFWD PULSE SHOES",
                "This product is excluded from all promotional discounts and otters.", "Adidas", 160,
                "/asset/img1.png"));

        products.add(new Product("FORUM MID SHOES",
                "This product is excluded from all promotional discounts and otters.", "Adidas", 130,
                "/asset/img2.png"));

        products.add(new Product("SUPERNOVA SHOES",
                "NMD City Stock 2", "Adidas", 180, "/asset/img3.png"));

        products.add(
                new Product("Adidas", "This product is excluded from all promotional discounts and otters.", "Adidas",
                        200, "/asset/img4.png"));
        products.add(new Product("4DFWD PULSE SHOES",
                "NMD City Stock 2", "Adidas", 133, "/asset/img5.png"));

        products.add(new Product("FORUM MID SHOES",
                "This product is excluded from all promotional discounts and otters.", "Adidas", 150,
                "/asset/img6.png"));

    }

    private String formatUSD(double price) {
        NumberFormat usdFormat = NumberFormat.getCurrencyInstance(Locale.US);
        return usdFormat.format(price);
    }

    private VBox createProductDetailBox() {
        VBox detailBox = new VBox(12);
        detailBox.getStyleClass().add("product-detail");
        detailBox.setAlignment(Pos.TOP_LEFT);
        detailBox.setPrefWidth(420);

        mainImageView = new ImageView();
        mainImageView.setFitWidth(340);
        mainImageView.setFitHeight(240);
        mainImageView.setPreserveRatio(true);
        mainImageView.getStyleClass().add("product-detail-image");

        nameLabel = new Label();
        nameLabel.getStyleClass().add("product-detail-title");

        priceLabel = new Label();
        priceLabel.getStyleClass().add("product-detail-price");

        brandLabel = new Label("Adidas");
        brandLabel.getStyleClass().add("product-detail-brand");

        descriptionLabel = new Label();
        descriptionLabel.getStyleClass().add("product-detail-desc");
        descriptionLabel.setWrapText(true);

        detailBox.getChildren().addAll(mainImageView, nameLabel, priceLabel, brandLabel, descriptionLabel);
        return detailBox;
    }

    private VBox createProductListBox() {
        VBox listBox = new VBox();
        listBox.setFillWidth(true);
        listBox.getStyleClass().add("product-list");

        GridPane grid = new GridPane();
        grid.getStyleClass().add("product-grid");
        grid.setPadding(new Insets(0, 0, 0, 0));
        grid.setHgap(20);
        grid.setVgap(20);

        int col = 0, row = 0;
        int maxCols = 4;
        for (int i = 0; i < products.size(); i++) {
            Product product = products.get(i);
            Pane card = createProductCard(product, i);
            productCards.add(card);
            grid.add(card, col, row);
            col++;
            if (col == maxCols) {
                col = 0;
                row++;
            }
        }

        ScrollPane scrollPane = new ScrollPane(grid);
        scrollPane.setFitToWidth(true);
        scrollPane.setStyle("-fx-background:transparent; -fx-background-color:transparent;");
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        scrollPane.setPannable(true);
        scrollPane.setPrefViewportWidth(980);
        scrollPane.setPrefViewportHeight(700);
        listBox.getChildren().add(scrollPane);
        VBox.setVgrow(scrollPane, Priority.ALWAYS);
        return listBox;
    }

    private Pane createProductCard(Product product, int index) {
        VBox card = new VBox(8);
        card.getStyleClass().add("product-card");
        card.setAlignment(Pos.TOP_LEFT);
        card.setPadding(new Insets(12));
        card.setMinWidth(220);
        card.setMaxWidth(220);
        card.setMinHeight(180);
        card.setMaxHeight(220);

        ImageView img = new ImageView(new Image(getClass().getResourceAsStream(product.getImagePath())));
        img.setFitWidth(110);
        img.setFitHeight(70);
        img.setPreserveRatio(true);

        Label title = new Label(product.getName());
        title.getStyleClass().add("product-card-title");
        title.setMaxWidth(180);
        title.setWrapText(true);

        Label desc = new Label(product.getDescription());
        desc.getStyleClass().add("product-card-desc");
        desc.setMaxWidth(180);
        desc.setWrapText(true);

        Label brand = new Label("Adidas");
        brand.getStyleClass().add("product-card-brand");

        Label price = new Label(formatUSD(product.getPrice()));
        price.getStyleClass().add("product-card-price");

        HBox priceRow = new HBox();
        priceRow.setAlignment(Pos.CENTER_RIGHT);
        priceRow.getChildren().add(price);

        card.getChildren().addAll(img, title, desc, brand, priceRow);

        card.setOnMouseClicked(e -> showProduct(product, index));

        return card;
    }

    private void showProduct(Product product, int index) {
        // Hiệu ứng fade
        FadeTransition fadeOut = new FadeTransition(Duration.millis(150), productDetailBox);
        fadeOut.setFromValue(1.0);
        fadeOut.setToValue(0.0);
        fadeOut.setOnFinished(e -> {
            mainImageView.setImage(new Image(getClass().getResourceAsStream(product.getImagePath())));
            nameLabel.setText(product.getName());
            priceLabel.setText(formatUSD(product.getPrice()));
            descriptionLabel.setText(product.getDescription());
            // Đổi trạng thái chọn card
            for (int i = 0; i < productCards.size(); i++) {
                if (i == index)
                    productCards.get(i).getStyleClass().add("selected");
                else
                    productCards.get(i).getStyleClass().remove("selected");
            }
            selectedIndex = index;
            FadeTransition fadeIn = new FadeTransition(Duration.millis(150), productDetailBox);
            fadeIn.setFromValue(0.0);
            fadeIn.setToValue(1.0);
            fadeIn.play();
        });
        fadeOut.play();
    }

    public static void main(String[] args) {
        launch(args);
    }
}