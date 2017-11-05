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
            int currentState = mySQLAccess.getState(update.getMessage().getChatId());
            System.out.println("current="+currentState);
            if(currentState == -1) {
                mySQLAccess.createUserState(update.getMessage().getChatId(), update.getMessage().getChat().getFirstName() + " " + update.getMessage().getChat().getLastName(), update.getMessage().getChat().getUserName());
                currentState = STATE.START;
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
            all.add(new NewAdChooseCategory());
            all.add(new NewAdSetTitle());

            for(baseState item:all)
            {
                if(item.isValid(update,currentState))
                {
                    item.excecute(this,message,update);
                    break;
                }
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
