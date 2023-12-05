package com.inubot.script.karambwanfisher.task;

import org.rspeer.game.adapter.component.InterfaceComponent;
import org.rspeer.game.adapter.component.inventory.Inventory;
import org.rspeer.game.component.InterfaceComposite;
import org.rspeer.game.component.Interfaces;
import org.rspeer.game.script.Task;
import org.rspeer.game.script.TaskDescriptor;

@TaskDescriptor(name = "Stopping", stoppable = true)
public class StopTask extends Task {

  @Override
  public boolean execute() {
    InterfaceComponent component = Interfaces.getDirect(InterfaceComposite.BACKPACK, 0);
    if (component == null || !component.isVisible()) {
      return false;
    }

    return !Inventory.backpack().contains(iq -> iq.names("Raw karambwanji").results());
  }
}
