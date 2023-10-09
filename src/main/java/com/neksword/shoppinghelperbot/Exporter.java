package com.neksword.shoppinghelperbot;

import java.io.File;

public interface Exporter {

    File export(ShoppingList shoppingList, CallbackQueryData callbackQueryData);
}
