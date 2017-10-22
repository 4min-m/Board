import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

/**
 * Created by Mohammad on 10/15/2017.
 */
public class onBoardBot extends TelegramLongPollingBot {

    @Override
    public void onUpdateReceived(Update update) {
        System.out.println(update.getMessage());
        if (update.hasMessage() && update.getMessage().hasText()) {

            MySQLAccess mySQLAccess = new MySQLAccess();

            try {
                mySQLAccess.excecute(
                        "INSERT INTO `states`(`id`, `message`) VALUES (NULL,'"+update.getMessage().getText()+"')");

            }catch (Exception e){
                e.printStackTrace();
            }

            SendMessage message = new SendMessage() // Create a SendMessage object with mandatory fields
                    .setChatId(update.getMessage().getChatId())
                    .setText("دریافت شد!\r\n"+update.getMessage().getText());



            try {
                sendMessage(message); // Call method to send the message
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public String getBotUsername() {
        return "onBoard_bot";
    }

    @Override
    public String getBotToken() {
        return "458250761:AAGTuqvQ_32vwij9lAJdYIZAyCilHEQwrSY";
    }


}
