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

            }catch (Exception e){
                e.printStackTrace();
            }
            SendMessage message = new SendMessage().setChatId(update.getMessage().getChatId());

            switch (update.getMessage().getText()) {
                case "/start":
                    message.setText("به بات تخته دیواری خوش آمدید!");
                    break;
                case "صفحه اصلی":
                    message.setText("دریافت شد!\r\n" + update.getMessage().getText());
                    break;
                case "درباره ما":
                    message.setText("برنامه نویسان:\r\n"
                    +"محمد زمان زاده\r\n"
                            +"امین ملک فر"
                    );
                    break;
                case "امکانات":
                    message.setText("صفحه امکانات");
                    break;
            }
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
