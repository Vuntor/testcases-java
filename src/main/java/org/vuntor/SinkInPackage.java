package org.vuntor;

import org.apache.commons.lang.SerializationUtils;

public class SinkInPackage {
    public static void main(String[] args) {
        deseSerializationUtils(new byte[]{});
    }

    public static void deseSerializationUtils(byte[] data) {
        SerializationUtils.deserialize(data);
    }
}
