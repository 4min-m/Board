import org.telegram.telegrambots.api.objects.Update;
import states.base;

/**
 * Created by Mohammad on 11/4/2017.
 */
public class About implements base {

    @Override
    public void excecute()
    {
        MySQLAccess mySQLAccess = new MySQLAccess();

        mySQLAccess.setState(1,update.getMessage().getChatId());
        message.setText("برنامه نویسان:\r\n"
                +"محمد زمان زاده\r\n"
                +"امین ملک فر"
        );
        break;
    }

    @Override
    public boolean isValid(Update update) {
        if(update.getMessage().getText()=="درباره ما")
            return true;
        return false;
    }

}
