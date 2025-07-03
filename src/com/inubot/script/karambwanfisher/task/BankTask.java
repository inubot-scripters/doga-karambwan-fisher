package com.inubot.script.karambwanfisher.task;

import org.rspeer.game.adapter.component.inventory.Bank;
import org.rspeer.game.adapter.component.inventory.Inventory;
import org.rspeer.game.component.Item;
import org.rspeer.game.script.Task;
import org.rspeer.game.script.TaskDescriptor;

@TaskDescriptor(name = "Banking")
public class BankTask extends Task {

  private static final String[] KEEP = {
      "Raw karambwanji", "Karambwan vessel", "Small fishing net", "Spirit flakes", "Fish barrel", "Open fish barrel"
  };

  @Override
  public boolean execute() {
    if (!Inventory.backpack().isFull()) {
      return false;
    }

    if (!Bank.isOpen()) {
      Bank.open();
      return true;
    }

    //TODO get a fish barrel and check the option, i dont have 1 to test with
    Item barrel = Inventory.backpack().getItems("Fish barrel").first();
    if (barrel != null) {
      barrel.interact("Empty");
    }

    Inventory.bank().depositAllExcept(iq -> iq.names(KEEP).results());
    return true;
  }
}
