package com.inubot.script.karambwanfisher.task;

import org.rspeer.game.adapter.component.inventory.Inventory;
import org.rspeer.game.adapter.scene.Npc;
import org.rspeer.game.adapter.scene.Player;
import org.rspeer.game.movement.Movement;
import org.rspeer.game.position.Position;
import org.rspeer.game.scene.Npcs;
import org.rspeer.game.scene.Players;
import org.rspeer.game.script.Task;
import org.rspeer.game.script.TaskDescriptor;

@TaskDescriptor(name = "Fishing")
public class FishTask extends Task {

  private static final Position POSITION = new Position(2899, 3117);

  @Override
  public boolean execute() {
    Player self = Players.self();
    if (self == null) {
      return false;
    }

    if (Inventory.backpack().isFull()) {
      return false;
    }

    if (POSITION.distance() > 20) {
      Movement.walkTo(POSITION);
      return true;
    }

    if (self.getTarget() != null) {
      return true;
    }

    Npc spot = Npcs.query().names("Fishing spot").actions("Fish").results().nearest();
    return spot != null && spot.interact("Fish");
  }
}
