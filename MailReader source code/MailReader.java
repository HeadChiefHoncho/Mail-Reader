import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.Button;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import java.util.Collections;
import javafx.scene.control.TextArea;
import javafx.geometry.Pos;
import javafx.geometry.Insets;
import java.time.format.DateTimeFormatter;

/**
 * This class represents a MailReader application.
 * @author Alex Loomis
 * @version 1.0
*/

public class MailReader extends Application {

    private Mailbox inbox = new Mailbox("Inbox");
    private Mailbox important = new Mailbox("Important");
    private Mailbox trash = new Mailbox("Trash");

    private BorderPane layout = new BorderPane();
    private HBox buttonBoxL = new HBox();
    private HBox buttonBoxM = new HBox();
    private HBox buttonBoxR = new HBox();
    private VBox mailboxBox = new VBox();
    private VBox messagesBox = new VBox();
    private VBox messageBox = new VBox();

    private Mailbox currentMailbox;

    private TextArea curSubject = new TextArea();
    private TextArea curSender = new TextArea();
    private TextArea curDateTime = new TextArea();
    private TextArea curMessageBody = new TextArea();

    private Button refreshButton = new Button("Refresh");
    private Button trashButton = new Button("Trash");
    private Button flagButton = new Button("Flag");
    private Button sortSenderButton = new Button("Sort by Sender");
    private Button sortDateButton = new Button("Sort by Date");
    private Button sortSubjectButton = new Button("Sort by Subject");
    private Button changeStyleSheet = new Button("Switch");

    private boolean kittyOn = true;
    private int timesThrough = 0;

    /**
     * This method runs the MailReader application.
     * @param stage The stage for the application.
    */

    @Override
    public void start(Stage stage) {
        initialize();
        ObservableList<Mailbox> mbList = FXCollections.observableArrayList();
        ObservableList<Message> mList = FXCollections.observableArrayList();
        ObservableList<String> messageContents
            = FXCollections.observableArrayList();
        mbList.add(inbox);
        mbList.add(important);
        mbList.add(trash);
        ListView<Mailbox> mailboxList = new ListView<Mailbox>(mbList);
        ListView<Message> messageList = new ListView<Message>(mList);
        mailboxList.getStyleClass().add("unfocus");
        messageList.getStyleClass().add("unfocus");
        mailboxList.getSelectionModel().selectedItemProperty().addListener(
            (observable, deselected, selected) -> {
                mList.clear();
                for (Message message : selected.getMessages()) {
                    mList.add(message);
                }
                Collections.sort(mList, (m1, m2) -> {
                        return m1.getDateTime().compareTo(m2.getDateTime());
                    });
                curSubject.clear();
                curSender.clear();
                curDateTime.clear();
                curMessageBody.clear();
            });
        messageList.getSelectionModel().selectedItemProperty().addListener(
            (observable, deselected, selected) -> {
                if (!(selected == null)) {
                    curSubject.setText(selected.getSubject());
                    curSender.setText(selected.getSender().toString());
                    curMessageBody.setText(selected.getMessageBody());
                    curDateTime.setText(selected.getDateTime(
                        ).format(DateTimeFormatter.ofPattern(
                            "KK:mm a, MMMM dd, yyyy")));
                } else {
                    curSubject.clear();
                    curSender.clear();
                    curDateTime.clear();
                    curMessageBody.clear();
                }
            });
        refreshButton.setOnAction(e -> {
                inbox.add(Server.getMessage());
                if (mailboxList.getSelectionModel().getSelectedItem()
                    == inbox) {
                    mList.clear();
                    for (Message message : mailboxList.getSelectionModel(
                        ).getSelectedItem().getMessages()) {
                        mList.add(message);
                    }
                    Collections.sort(mList, (m1, m2) -> {
                            return m1.getDateTime().compareTo(m2.getDateTime());
                        });
                }
            });
        trashButton.setOnAction(e -> {
                if (!(messageList.getSelectionModel().getSelectedItem()
                    == null)) {
                    Message currentMessage = messageList.getSelectionModel(
                        ).getSelectedItem();
                    mailboxList.getSelectionModel().getSelectedItem(
                        ).remove(currentMessage);
                    trash.add(currentMessage);
                    mList.clear();
                    for (Message message : mailboxList.getSelectionModel(
                        ).getSelectedItem().getMessages()) {
                        mList.add(message);
                    }
                }
            });
        flagButton.setOnAction(e -> {
                if (!(messageList.getSelectionModel().getSelectedItem()
                    == null)) {
                    Message currentMessage = messageList.getSelectionModel(
                        ).getSelectedItem();
                    mailboxList.getSelectionModel().getSelectedItem(
                        ).remove(currentMessage);
                    important.add(currentMessage);
                    mList.clear();
                    for (Message message : mailboxList.getSelectionModel(
                        ).getSelectedItem().getMessages()) {
                        mList.add(message);
                    }
                }
            });
        sortSenderButton.setOnAction(e -> {
                Collections.sort(mList, (m1, m2) -> {
                        return m1.getSender().compareTo(m2.getSender());
                    });
            });
        sortSubjectButton.setOnAction(e -> {
                Collections.sort(mList, (m1, m2) -> {
                        return m1.getSubject().compareTo(m2.getSubject());
                    });
            });
        sortDateButton.setOnAction(e -> {
                Collections.sort(mList, (m1, m2) -> {
                        return m1.getDateTime().compareTo(m2.getDateTime());
                    });
            });
        buttonBoxL.getChildren().addAll(refreshButton);
        buttonBoxM.getChildren().addAll(sortSenderButton, sortDateButton,
            sortSubjectButton);
        buttonBoxR.getChildren().addAll(trashButton, flagButton,
            changeStyleSheet);
        messageBox.getChildren().addAll(buttonBoxR, curSubject, curSender,
            curDateTime, curMessageBody);
        mailboxBox.getChildren().addAll(buttonBoxL, mailboxList);
        messagesBox.getChildren().addAll(buttonBoxM, messageList);
        layout.setRight(messageBox);
        layout.setLeft(mailboxBox);
        layout.setCenter(messagesBox);
        Scene scene = new Scene(layout);
        layout.getStyleClass().add("bg");
        changeStyleSheet.setOnAction(e -> {
                kittyOn = !kittyOn;
                if (kittyOn && timesThrough > 0) {
                    scene.getStylesheets().remove("beluga_style.css");
                    scene.getStylesheets().add("kitty_style.css");
                } else {
                    scene.getStylesheets().remove("kitty_style.css");
                    scene.getStylesheets().add("beluga_style.css");
                }
            });
        scene.getStylesheets().add("kitty_style.css");
        stage.setScene(scene);
        stage.setTitle("Mail Reader");
        stage.show();
        timesThrough++;
    }

    /**
     * This method initializes style values for certain objects.
    */
    private void initialize() {
        mailboxBox.setPadding(new Insets(0, 2, 0, 0));
        messagesBox.setPadding(new Insets(0, 2, 0, 2));
        messageBox.setPadding(new Insets(0, 0, 0, 2));
        buttonBoxL.setPadding(new Insets(2, 0, 2, 0));
        buttonBoxM.setPadding(new Insets(2, 0, 2, 0));
        buttonBoxR.setPadding(new Insets(2, 0, 2, 0));
        layout.setPadding(new Insets(0, 10, 0, 10));
        buttonBoxL.setAlignment(Pos.BASELINE_CENTER);
        buttonBoxM.setAlignment(Pos.BASELINE_CENTER);
        buttonBoxR.setSpacing(10);
        buttonBoxM.setSpacing(10);
        buttonBoxL.setSpacing(10);
        curMessageBody.setWrapText(true);
        curSender.setPrefHeight(5);
        curDateTime.setPrefHeight(5);
        curSubject.setPrefHeight(10);
        mailboxBox.setPrefWidth(100);
        mailboxBox.setPrefHeight(200);
        messagesBox.setPrefWidth(400);
        messageBox.setPrefWidth(400);
        curSubject.setEditable(false);
        curMessageBody.setEditable(false);
        curSender.setEditable(false);
        curDateTime.setEditable(false);
        curMessageBody.getStyleClass().add("unfocus");
        curSender.getStyleClass().add("unfocus");
        curSubject.getStyleClass().add("unfocus");
        curDateTime.getStyleClass().add("unfocus");
        refreshButton.getStyleClass().add("unfocus");
        trashButton.getStyleClass().add("unfocus");
        flagButton.getStyleClass().add("unfocus");
        sortSubjectButton.getStyleClass().add("unfocus");
        sortSenderButton.getStyleClass().add("unfocus");
        sortDateButton.getStyleClass().add("unfocus");
        changeStyleSheet.getStyleClass().add("unfocus");
        curSubject.getStyleClass().add("subject");
    }
}