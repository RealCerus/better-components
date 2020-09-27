package de.cerus.bettercomponents.callback;

import de.cerus.bettercomponents.ReflectionUtil;
import java.util.UUID;
import org.bukkit.command.Command;
import org.bukkit.command.CommandMap;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * @author Maximilian Dorn
 * @version 1.0.0
 * @since 1.0.0
 */
public abstract class TextComponentClickCallback implements TextComponentCallback<Player> {

    private final CommandMap commandMap = ReflectionUtil.getCommandMap();
    private final String commandName = UUID.randomUUID().toString().replace("-", "");
    private final UUID playerUuid;

    public TextComponentClickCallback() {
        this(null);
    }

    public TextComponentClickCallback(final UUID playerUuid) {
        this.playerUuid = playerUuid;
    }

    /**
     * Subscribes this click callback to the Bukkit command map.
     * TODO: Add BungeeCord compatibility
     */
    @Override
    public void subscribe() {
        // Throw exception if the utility failed to fetch the Bukkit command map
        if (this.commandMap == null) {
            throw new IllegalStateException("Command map is null");
        }

        // Throw exception if the generated command name is already registered to a command
        if (this.commandMap.getCommand(this.commandName) != null) {
            throw new IllegalStateException("Callback is already subscribed");
        }

        // Register a new command to the generated command name
        this.commandMap.register("bettercomponents", new Command(this.commandName) {
            @Override
            public boolean execute(final CommandSender sender, final String commandLabel, final String[] args) {
                if (sender instanceof Player && (TextComponentClickCallback.this.playerUuid == null
                        || ((Player) sender).getUniqueId().equals(TextComponentClickCallback.this.playerUuid))) {
                    // Perform the operation on the player
                    TextComponentClickCallback.this.accept((Player) sender);
                }
                return true;
            }
        });
    }

    /**
     * Unsubscribes this click callback from the Bukkit command map
     */
    @Override
    public void unsubscribe() {
        if (this.commandMap == null) {
            throw new IllegalStateException("Command map is null");
        }

        final Command command = this.commandMap.getCommand(this.commandName);
        if (command == null) {
            throw new IllegalStateException("Callback is not subscribed");
        }

        command.unregister(this.commandMap);
    }

    @Override
    public boolean isSubscribed() {
        return this.commandMap != null && this.commandMap.getCommand(this.commandName) != null;
    }

    public String getCommandName() {
        return this.commandName;
    }

}
