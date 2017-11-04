import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.bots.AbsSender;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;

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

/*
            ArrayList<KeyboardRow> keyboardRows = new ArrayList<KeyboardRow>();

            keyboardRows.add(new KeyboardRow().add("صفحه اصلی"));
            message.setReplyMarkup(new ReplyKeyboardMarkup().setKeyboard(keyboardRows));
*/

            ArrayList<baseState> all = new ArrayList<baseState>();
            all.add(new About());
            all.add(new StartState());
            for(baseState item:all)
            {
                if(item.isValid(update))
                {
                    item.excecute(this,message,update);
                    break;
                }
            }
          /*  switch (update.getMessage().getText()) {
                case "/start":
                    message.setText("به بات تخته دیواری خوش آمدید!");
                    break;
                case "صفحه اصلی":
                    message.setText("دریافت شد!\r\n" + update.getMessage().getText());
                    break;
                case "درباره ما":
                    mySQLAccess.setState(1,update.getMessage().getChatId());
                    message.setText("برنامه نویسان:\r\n"
                    +"محمد زمان زاده\r\n"
                            +"امین ملک فر"
                    );
                    break;
                case "امکانات":
                    message.setText("صفحه امکانات");
                    break;
            }*/
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
