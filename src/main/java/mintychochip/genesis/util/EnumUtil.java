package mintychochip.genesis.util;

public class EnumUtil {
    public static <E extends Enum<E>> boolean isInEnum(Class<E> enumClass, String value) {
        for (E e : enumClass.getEnumConstants()) {
            if (e.name().equals(value)) {
                return true;
            }
        }
        return false;
    }
}
