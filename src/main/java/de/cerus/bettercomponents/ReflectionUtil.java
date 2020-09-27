package de.cerus.bettercomponents;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandMap;

/**
 * @author Maximilian Dorn
 * @version 1.0.0
 * @since 1.0.0
 */
public class ReflectionUtil {

    private ReflectionUtil() {
    }

    /**
     * Tries to get the Bukkit command map
     *
     * @return The Bukkit command map or null
     */
    public static CommandMap getCommandMap() {
        try {
            final Method getCommandMap = Bukkit.getServer().getClass().getMethod("getCommandMap");
            final Object commandMap = getCommandMap.invoke(Bukkit.getServer());
            return (CommandMap) commandMap;
        } catch (final NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }

}
