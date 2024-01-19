package mintychochip.genesis.util;

import mintychochip.genesis.Genesis;
import mintychochip.genesis.container.ItemData;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.io.*;

public class Serializer {
    public static <T extends ItemData> byte[] serialize(T data) throws IOException {
        try {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            oos.writeObject(data);
            return bos.toByteArray();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static ItemData deserialize(byte[] data) throws IOException {
        try {
            ByteArrayInputStream bis = new ByteArrayInputStream(data);
            ObjectInputStream ois = new ObjectInputStream(bis);
            return (ItemData) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static <T extends ItemData> boolean serializeToItem(T data, ItemStack itemStack) {
        ItemMeta itemMeta = itemStack.getItemMeta();
        if (itemMeta == null) {
            return false;
        }
        try {
            return Genesis.getKeys().assignByteArrayToItemStack(data.getKey(), Serializer.serialize(data), itemStack);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
