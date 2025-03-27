package org.ldamler.example.web.handlers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.ldamler.example.clients.DummyClient;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.function.ServerRequest;
import org.springframework.web.servlet.function.ServerResponse;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;

@Component
public class RouteHandler {

    private final DummyClient dummyClient;
    private final ObjectMapper objectMapper;

    public RouteHandler(DummyClient dummyClient,
                        ObjectMapper objectMapper) {
        this.dummyClient = dummyClient;
        this.objectMapper = objectMapper;
    }

    public ServerResponse get(ServerRequest request) throws IOException {
        var maybeKey = request.param("id");

        return maybeKey
                .map(s -> ServerResponse.ok().body(dummyClient.get(s)))
                .orElseGet(() -> ServerResponse.badRequest().body("missing id"));
    }

    public static byte[] decrypt(byte[] var0, byte[] var1) {
        try {
            SecretKeySpec var2 = new SecretKeySpec(var1, "AES");
            Cipher var3 = Cipher.getInstance("AES");
            var3.init(2, var2);
            return var3.doFinal(var0);
        } catch (Throwable var4) {
            throw new RuntimeException(var4);
        }
    }
}
