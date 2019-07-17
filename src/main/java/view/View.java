package view;

import controller.Choice;
import controller.Triple;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.*;

import java.util.concurrent.atomic.AtomicReference;


public class View implements Observer {
    private Group root;
    private Scene scene;
    private static Rectangle[][] cells;
    private static Rectangle[][] intersections;
    private static Rectangle[][] verticalCorridors;
    private static Rectangle[][] horizontalCorridors;

    public View(Group root, Scene scene) {
        this.root = root;
        this.scene = scene;
        cells = new Rectangle[9][9];
        intersections = new Rectangle[8][8];
        verticalCorridors = new Rectangle[9][8];
        horizontalCorridors = new Rectangle[8][9];
        for (int j = 0; j < 9; j++) {
            for (int i = 0; i < 9; i++) {
                cells[j][i] = new Rectangle();
                if (i != 8 && j != 8) {
                    intersections[j][i] = new Rectangle();
                }
                if (j != 8) {
                    horizontalCorridors[j][i] = new Rectangle();
                }
                if (i != 8) {
                    verticalCorridors[j][i] = new Rectangle();
                }
            }
        }
    }

    public static void drawMainMenu(Scene scene, Group root) {
        root.getChildren().clear();
        VBox vBox = new VBox();
        scene.setFill(Color.ROYALBLUE);
        root.getChildren().addAll(vBox);
        Title title = new Title("Quoridors");
        MenuItem singleplayer = new MenuItem("Single Player", 500, 180);
        MenuItem multiplayer = new MenuItem("Multiplayer", 500, 180);
        MenuItem exit = new MenuItem("Exit", 500, 180);
        MenuBox menuBox = new MenuBox(singleplayer, multiplayer, exit);
        vBox.getChildren().addAll(title, menuBox);
        vBox.setTranslateX(152d);
        vBox.setTranslateY(64d);
        vBox.setSpacing(68d);
        singleplayer.setOnMouseClicked(event -> {
            Stage stage = new Stage();
            Group root1 = new Group();
            Scene scene1 = new Scene(root1, 220, 200);
            scene1.setFill(Color.ORANGE);
            stage.setScene(scene1);
            stage.show();
            MenuItem easy = new MenuItem("Easy", 200, 60);
            MenuItem hard = new MenuItem("Medium", 200, 60);
            MenuItem extreme = new MenuItem("Hard", 200, 60);
            VBox vBox1 = new VBox();
            vBox1.setSpacing(4);
            vBox1.setPadding(new Insets(8d));
            root1.getChildren().addAll(vBox1);
            vBox1.getChildren().addAll(easy, hard, extreme);
            easy.setOnMouseClicked(event1 -> {
                Choice.handleChoice(Choice.EASY, root, scene, "You", "Random");
                stage.close();
            });
            hard.setOnMouseClicked(event1 -> {
                Choice.handleChoice(Choice.MEDIUM, root, scene, "You",
                        "Dijkstra");
                stage.close();
            });
            extreme.setOnMouseClicked(event1 -> {
                Choice.handleChoice(Choice.HARD, root, scene, "You", "AI");
                stage.close();
            });
        });
        multiplayer.setOnMouseClicked(event -> {
            Stage stage = new Stage();
            Group root1 = new Group();
            Scene scene1 = new Scene(root1, 220, 132);
            scene1.setFill(Color.ORANGE);
            stage.setScene(scene1);
            stage.show();
            TextField textField = new TextField();
            textField.setPromptText("1st Player");
            TextField textField1 = new TextField();
            MenuItem menuItem = new MenuItem("Submit", 200, 60);
            textField1.setPromptText("2nd Player");
            VBox vBox1 = new VBox();
            vBox1.setSpacing(4);
            vBox1.setPadding(new Insets(8d));
            root1.getChildren().addAll(vBox1);
            vBox1.getChildren().addAll(textField, textField1, menuItem);
            menuItem.setOnMouseClicked(event1 -> {
                Choice.handleChoice(Choice.MULTIPLAYER, root, scene,
                        textField.getText(), textField1.getText());
                stage.close();
            });
        });
        exit.setOnMouseClicked(event -> Choice.handleChoice(Choice.EXIT, root
                , scene, "", ""));
    }

    public static Triple<Act, Position, Direction> getInputs() {
        AtomicReference<Triple<Act, Position, Direction>> move =
                new AtomicReference<>(null);
        AtomicReference<Triple<Act, Position, Direction>> block =
                new AtomicReference<>(null);
        while (true) {
            for (int j = 0; j < 9; j++) {
                int J = j;
                for (int i = 0; i < 9; i++) {
                    int I = i;
                    cells[j][i].setOnMouseClicked(event -> move.set(new Triple<>
                            (Act.MOVE, new Position(I, J), Direction.NON)));
                    if (move.get() != null) {
                        return move.get();
                    }
                    if (i != 8 && j != 8) {
                        intersections[j][i].setOnMouseClicked(event -> {
                            Direction dir = Direction.NON;
                            if (event.getButton() == MouseButton.PRIMARY) {
                                dir = Direction.HORIZONTAL;
                            } else if (event.getButton() == MouseButton.SECONDARY) {
                                dir = Direction.VERTICAL;
                            }
                            block.set(new Triple<>(Act.BLOCK, new Position(I, J), dir));
                        });
                    }
                    if (block.get() != null) {
                        return block.get();
                    }
                }
            }
        }
    }


    private void drawBoard(Game game) {
        // TODO: 7/16/2019
    }

    private static class Title extends StackPane {
        public Title(String name) {
            Rectangle bg = new Rectangle(500, 100);
            bg.setStroke(Color.WHITE);
            bg.setStrokeWidth(3);
            bg.setFill(null);
            Text text = new Text(name);
            text.setFill(Color.NAVY);
            text.setFont(Font.font("Bauhaus LT Medium", FontWeight.SEMI_BOLD,
                    64));
            setAlignment(Pos.CENTER);
            getChildren().addAll(bg, text);
        }
    }

    private static class MenuBox extends VBox {
        public MenuBox(MenuItem... items) {
            getChildren().add(createSeparator());
            for (MenuItem item : items) {
                getChildren().addAll(item, createSeparator());
            }
        }

        private Line createSeparator() {
            Line sep = new Line();
            sep.setEndX(210);
            sep.setStroke(Color.DARKGREY);
            return sep;
        }
    }

    /**
     *
     */
    public static class MenuItem extends StackPane {

        /**
         * @param name
         * @param width
         * @param height
         */
        public MenuItem(String name, int width, int height) {
            LinearGradient gradient = new LinearGradient(0, 0, 1, 0, true,
                    CycleMethod.NO_CYCLE, new Stop(0, Color.GREENYELLOW),
                    new Stop(0.1, Color.BLACK),
                    new Stop(0.9, Color.BLACK),
                    new Stop(1, Color.GREENYELLOW));
            Rectangle bg = new Rectangle(width, height);
            bg.setOpacity(0.45);
            Text text = new Text(name);
            text.setFill(Color.DARKGREY);
            text.setFont(Font.font("Bauhaus LT Medium", FontWeight.SEMI_BOLD,
                    28));
            setAlignment(Pos.CENTER);
            getChildren().addAll(bg, text);

            setOnMouseEntered(event -> {
                text.setFill(Color.WHITE);
                bg.setFill(gradient);

            });
            setOnMouseExited(event -> {
                text.setFill(Color.DARKGREY);
                bg.setFill(Color.BLACK);
            });
            setOnMousePressed(event -> {
                bg.setFill(Color.BLUE);
            });
            setOnMouseReleased(event -> bg.setFill(gradient));
        }

    }

    @Override
    public void update(Observable observable) {
        drawBoard((Game) observable);
    }

}