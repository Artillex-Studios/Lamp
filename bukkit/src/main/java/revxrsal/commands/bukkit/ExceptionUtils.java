package revxrsal.commands.bukkit;

import org.jetbrains.annotations.Nullable;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public final class ExceptionUtils {

    @Nullable
    public static <T> T catching(Supplier<T> supplier, Function<Exception, @Nullable T> function) {
        try {
            return supplier.get();
        } catch (Exception exception) {
            return function.apply(exception);
        }
    }

    @Nullable
    public static <T> T orElse(ThrowingSupplier<T> supplier, ThrowingSupplier<T> other, Function<Exception, @Nullable T> function) {
        try {
            return supplier.get();
        } catch (Exception exception) {
            try {
                return other.get();
            } catch (Exception otherException) {
                return function.apply(otherException);
            }
        }
    }


    public static void catching(Runnable runnable, Consumer<Exception> consumer) {
        try {
            runnable.run();
        } catch (Exception exception) {
            consumer.accept(exception);
        }
    }
}
