package de.cerus.bettercomponents;

import de.cerus.bettercomponents.callback.TextComponentClickCallback;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;

/**
 * @author Maximilian Dorn
 * @version 1.0.0
 * @since 1.0.0
 */
public class TextComponentBuilder {

    private final TextComponent baseComponent = new TextComponent("");

    /**
     * Sets the text of the base component
     *
     * @param text The new text
     *
     * @return The builder instance
     */
    public TextComponentBuilder setText(final String text) {
        this.baseComponent.setText(text);
        return this;
    }

    /**
     * Sets a {@link TextComponentClickCallback} as the click event of the base component
     *
     * @param callback The click callback
     *
     * @return The builder instance
     */
    public TextComponentBuilder setClickCallback(final TextComponentClickCallback callback) {
        if (!callback.isSubscribed()) {
            callback.subscribe();
        }
        return this.setClickCommand(callback.getCommandName());
    }

    /**
     * Sets the command parameter as a 'RUN_COMMAND' click event
     *
     * @param command The command
     *
     * @return The builder instance
     */
    public TextComponentBuilder setClickCommand(final String command) {
        return this.setClickAction(ClickEvent.Action.RUN_COMMAND, (command.startsWith("/") ? "" : "/") + command);
    }

    /**
     * Sets the suggestion parameter as a 'SUGGEST_COMMAND' click event
     *
     * @param suggestion The command
     *
     * @return The builder instance
     */
    public TextComponentBuilder setClickSuggestion(final String suggestion) {
        return this.setClickAction(ClickEvent.Action.SUGGEST_COMMAND, suggestion);
    }

    /**
     * Sets the provided action and the provided value as the click event of the base component
     *
     * @param action The click event action
     * @param value  The click event value
     *
     * @return The builder instance
     */
    public TextComponentBuilder setClickAction(final ClickEvent.Action action, final String value) {
        return this.setClickAction(new ClickEvent(action, value));
    }

    /**
     * Sets the click event for the base component
     *
     * @param event The click event
     *
     * @return The builder instance
     */
    public TextComponentBuilder setClickAction(final ClickEvent event) {
        this.baseComponent.setClickEvent(event);
        return this;
    }

    /**
     * Sets the text parameter as a 'SHOW_TEXT' hover event
     *
     * @param value The text value
     *
     * @return The builder instance
     */
    public TextComponentBuilder setHoverText(final String value) {
        return this.setHoverAction(HoverEvent.Action.SHOW_TEXT, new TextComponent(value));
    }

    /**
     * Sets the provided action and the provided text component as the hover event of the base component
     *
     * @param value The text component
     *
     * @return The builder instance
     */
    public TextComponentBuilder setHoverAction(final HoverEvent.Action action, final TextComponentBuilder value) {
        return this.setHoverAction(action, value.build());
    }

    /**
     * Sets the provided action and the provided components as the hover event of the base component
     *
     * @param value The components
     *
     * @return The builder instance
     */
    public TextComponentBuilder setHoverAction(final HoverEvent.Action action, final BaseComponent... value) {
        return this.setHoverAction(new HoverEvent(action, value));
    }

    /**
     * Sets the hover event for the base component
     *
     * @param event The hover event
     *
     * @return The builder instance
     */
    public TextComponentBuilder setHoverAction(final HoverEvent event) {
        this.baseComponent.setHoverEvent(event);
        return this;
    }

    /**
     * Adds a text component as an extra to the base component
     *
     * @param value The text component
     *
     * @return The builder instance
     */
    public TextComponentBuilder addExtra(final TextComponentBuilder value) {
        this.baseComponent.addExtra(value.build());
        return this;
    }

    /**
     * Adds a string as an extra to the base component
     *
     * @param value The text
     *
     * @return The builder instance
     */
    public TextComponentBuilder addExtra(final String value) {
        this.baseComponent.addExtra(value);
        return this;
    }

    /**
     * Adds a component as an extra to the base component
     *
     * @param value The component
     *
     * @return The builder instance
     */
    public TextComponentBuilder addExtra(final BaseComponent value) {
        this.baseComponent.addExtra(value);
        return this;
    }

    /**
     * @return The built text component
     */
    public TextComponent build() {
        return this.baseComponent;
    }

}
