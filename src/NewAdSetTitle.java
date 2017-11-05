import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.bots.AbsSender;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mohammad on 11/5/2017.
 */
public class NewAdSetTitle implements baseState {

    @Override
    public void excecute(AbsSender absSender, SendMessage message, Update update)
    {
        MySQLAccess mySQLAccess = new MySQLAccess();

        mySQLAccess.setState(STATE.NEW_AD_SET_TITLE,update.getMessage().getChatId());
        message.setText("لطفا عنوان آگهی را وارد کنید:"
                +"\r\n"
        );


        List<KeyboardRow> keyboard = new ArrayList<>();

        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(false);

        KeyboardRow startKeyboard = new KeyboardRow();

        startKeyboard.add("صفحه اصلی");

        keyboard.add(startKeyboard);

        replyKeyboardMarkup.setKeyboard(keyboard);
        message.setReplyMarkup(replyKeyboardMarkup);

        try {
            absSender.sendMessage(message); // Call method to send the message
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean isValid(Update update, int currentState) {
        return update.getMessage().getText().equals("افزودن آگهی");
    }

}