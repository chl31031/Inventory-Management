package org.example.view.viewcontroller;

import org.example.view.model.IO;

public interface CreateItemIO {
    void execute(String itemID, IO io, int quantity);
}
