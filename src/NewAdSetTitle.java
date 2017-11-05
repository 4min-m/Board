import org.json.JSONObject;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.bots.AbsSender;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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

        ResultSet cat_id = mySQLAccess.query("SELECT id from categories where name='"+update.getMessage().getText()+"'");

        try {
            if(!cat_id.next()) {
                message.setText("دسته نامعتبر لطفا مجدد انتخاب کنید:"
                        + "\r\n"
                );
            }else{

        JSONObject jsonData = mySQLAccess.getDataOfState(update.getMessage().getChatId());
        jsonData.put("category_id",cat_id.getInt("id"));

        mySQLAccess.setDataOfState(jsonData,update.getMessage().getChatId());

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
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            absSender.sendMessage(message); // Call method to send the message
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean isValid(Update update, int currentState) {
        return currentState==STATE.NEW_AD_SET_TITLE;
    }

}