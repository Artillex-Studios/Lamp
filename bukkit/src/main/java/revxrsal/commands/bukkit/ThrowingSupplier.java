package revxrsal.commands.bukkit;

@FunctionalInterface
public interface ThrowingSupplier<T> {

    T get() throws Exception;
}
