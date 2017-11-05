import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.AbsSender;
import org.telegram.telegrambots.exceptions.TelegramApiException;

/**
 * Created by Mohammad on 11/4/2017.
 */
public class About implements baseState {

    @Override
    public void excecute(AbsSender absSender,SendMessage message, Update update)
    {
        MySQLAccess mySQLAccess = new MySQLAccess();

        mySQLAccess.setState(1,update.getMessage().getChatId());
        message.setText("برنامه نویسان:\r\n"
                +"محمد زمان زاده\r\n"
                +"امین ملک فر"
        );
        try {
            absSender.sendMessage(message); // Call method to send the message
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean isValid(Update update, int currentState) {
        return update.getMessage().getText().equals("درباره ما");
    }

}
