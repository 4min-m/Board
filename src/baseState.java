import org.telegram.telegrambots.api.objects.Update;

/**
 * Created by Mohammad on 11/4/2017.
 */
public interface baseState {

    void excecute();

    boolean isValid(Update update);

}
