package org.example.view.model;

import java.time.ZonedDateTime;

public record ItemIO(
        String id,
        int quantity,
        IO io,
        ZonedDateTime time
) {
}
