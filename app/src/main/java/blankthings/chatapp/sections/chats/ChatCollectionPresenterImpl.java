package blankthings.chatapp.sections.chats;

import java.util.Calendar;

import blankthings.chatapp.utilities.Utils;

/**
 * Created by iosif on 5/20/17.
 */

public class ChatCollectionPresenterImpl
        implements ChatCollectionContract.ChatCollectionPresenter {

    private ChatCollectionContract.ChatCollectionView view;


    public ChatCollectionPresenterImpl(ChatCollectionContract.ChatCollectionView view) {
        onAttach(view);
        start();
    }


    @Override
    public void onAttach(ChatCollectionContract.ChatCollectionView view) {
        this.view = Utils.checkNotNull(view);
    }


    @Override
    public void onDetach() {}


    @Override
    public void start() {
        fetchChats();
    }


    @Override
    public void fetchChats() {
        view.startLoading();
    }


    @Override
    public void createChat(final String name, final String message) {
        // TODO: 5/21/17 create chat request.

        final ChatItem chatItem = new ChatItem(0, name, message, getTodaysFormattedDate());
        view.addChat(chatItem);
    }


    private String getTodaysFormattedDate() {
        final String daysArray[] = {"Sun","Mon","Tues", "Wed","Thurs","Fri", "Sat"};
        final Calendar c = Calendar.getInstance();
        final int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
        final int dayOfMonth = c.get(Calendar.DAY_OF_MONTH);
        return String.format("%s, %s", daysArray[dayOfWeek], String.valueOf(dayOfMonth));
    }


    @Override
    public void chatSelected(ChatItem chatItem) {
        view.navigateToSelectedChat(chatItem);
    }
}
