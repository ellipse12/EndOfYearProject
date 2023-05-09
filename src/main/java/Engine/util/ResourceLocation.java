package Engine.util;

import org.jetbrains.annotations.Nullable;

import java.io.InputStream;
import java.nio.file.Path;

public class ResourceLocation {

    @Nullable
    public static InputStream getFileStream(String name){
        InputStream stream = ResourceLocation.class.getClassLoader().getResourceAsStream(name);
        return stream;
    }

}
