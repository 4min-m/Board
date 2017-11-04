import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.AbsSender;

/**
 * Created by Mohammad on 11/4/2017.
 */
public interface baseState {

    void excecute(AbsSender absSender, SendMessage sendMessage, Update update);

    boolean isValid(Update update);

}
