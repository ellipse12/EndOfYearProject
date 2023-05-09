package Engine.util;

import java.io.File;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ResourceLocation {


    public static InputStream getFileStream(final String file){
        InputStream io = ResourceLocation.class.getClassLoader().getResourceAsStream(file);
        if(io == null){
            throw new IllegalStateException(file + " is not found");
        }
        return io;
    }

    public static URL getURL(final String file) throws URISyntaxException {
        return ResourceLocation.class.getClassLoader().getResource(file);
    }


}
