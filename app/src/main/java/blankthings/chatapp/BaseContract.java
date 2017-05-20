package blankthings.chatapp;

/**
 * Describes a base contract between BasePresenter and BaseView.
 *
 * Created by iosif on 5/20/17.
 */

public interface BaseContract {


    interface BaseView<P> {

        /**
         * @param presenter
         */
        void setPresenter(P presenter);

    }


    interface BasePresenter<V extends BaseView> {

        /**
         * Called when the view has been attached.
         * @param view - View being attached.
         */
        void onAttach(V view);

        /**
         * Called when the view has been detached.
         */
        void onDetach();

    }

}
