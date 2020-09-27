package de.cerus.bettercomponents.callback;

/**
 * @author Maximilian Dorn
 * @version 1.0.0
 * @since 1.0.0
 */
interface TextComponentCallback<T> {

    /**
     * Performs this operation on the given argument
     *
     * @param t The argument
     */
    void accept(T t);

    /**
     * Subscribes this callback. What the callback subscribes too is open to the implementation.
     */
    void subscribe();

    /**
     * Unsubscribes this callback. What the callback unsubscribes from is open to the implementation.
     */
    void unsubscribe();

    /**
     * @return Whether this callback is subscribed or not
     */
    boolean isSubscribed();

}
