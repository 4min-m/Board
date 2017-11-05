import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.bots.AbsSender;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mohammad on 11/4/2017.
 */
public class StartState implements baseState {

    @Override
    public void excecute(AbsSender absSender, SendMessage message, Update update)
    {
        MySQLAccess mySQLAccess = new MySQLAccess();

        mySQLAccess.setState(STATE.START,update.getMessage().getChatId());
        message.setText("به ربات هوشمند دیوار خوش آمدید!\n\r"
        +"لطفا از منو یکی از آیتم ها انتخاب کنید..."
                +"\r\n"
        );

        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(false);
        List<KeyboardRow> keyboard = new ArrayList<>();
        KeyboardRow keyboardRow1 = new KeyboardRow();
        keyboardRow1.add("آگهی های من");
        keyboardRow1.add("افزودن آگهی");

        KeyboardRow keyboardRow2 = new KeyboardRow();
        keyboardRow2.add("آگهی های من");
        keyboardRow2.add("افزودن آگهی");

        KeyboardRow keyboardRow3 = new KeyboardRow();
        keyboardRow3.add("درباره ما");

        KeyboardRow startKeyboard = new KeyboardRow();
        startKeyboard.add("صفحه اصلی");

        keyboard.add(keyboardRow1);
//        keyboard.add(keyboardRow2);
        keyboard.add(keyboardRow3);
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
        return update.getMessage().getText().equals("/start") || update.getMessage().getText().equals("صفحه اصلی");
    }

}

