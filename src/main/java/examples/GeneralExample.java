package examples;

import de.cerus.bettercomponents.TextComponentBuilder;
import de.cerus.bettercomponents.callback.TextComponentClickCallback;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.entity.Player;

public class GeneralExample {

    public void doSomething(final Player player) {
        final TextComponent component = new TextComponentBuilder()
                .setText("§7This text is not clickable! ") // Set component text
                .setHoverText("§bHover text example") // Set text that will be shown when player hovers over the component
                .addExtra(new TextComponentBuilder() // Append another text component
                        .setText("§aThis text is clickable! ") // Set text of the new component
                        .setClickCallback(new TextComponentClickCallback(player.getUniqueId()) { // Will be called when the player clicks the component
                            private int counter = 5;

                            @Override
                            public void accept(final Player player) {
                                player.sendMessage("§aYou clicked the text component! You can click it " + (--this.counter) + " more times.");

                                // Remove callback when counter hits zero
                                if (this.counter == 0) {
                                    this.unsubscribe();
                                }
                            }
                        }))
                .build();
        player.spigot().sendMessage(component);
    }

}
