import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.bots.AbsSender;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mohammad on 11/5/2017.
 */
public class NewAdChooseCategory implements baseState {

    @Override
    public void excecute(AbsSender absSender, SendMessage message, Update update)
    {
        MySQLAccess mySQLAccess = new MySQLAccess();

        mySQLAccess.setState(STATE.NEW_AD_SET_TITLE,update.getMessage().getChatId());
        message.setText("لطفا دسته بندی مورد نظر خود را انتخاب کنید:"
                +"\r\n"
        );

        ResultSet categories = mySQLAccess.query("SELECT * FROM categories");

        List<KeyboardRow> keyboard = new ArrayList<>();

        try {
            while (categories.next()) {
                KeyboardRow temp = new KeyboardRow();
                temp.add(categories.getString("name"));
                keyboard.add(temp);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(false);

        KeyboardRow startKeyboard = new KeyboardRow();

        startKeyboard.add("صفحه اصلی");

//        keyboard.add(keyboardRow2);
//        keyboard.add(keyboardRow3);
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